package com.example.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        transform: (T) -> R
): Flow<RequestState<R>> = flow {

    // Emit loading state
    emit(RequestState.Loading)

    try {
        // Execute the API call
        val response = apiCall()

        // Handle the API response
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(RequestState.Success(transform(body))) // Apply the transformation and emit success state
            } else {
                emit(RequestState.Error("No data found")) // Emit error for empty response
            }
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error occurred"
            emit(RequestState.Error(errorMessage)) // Emit error for API failure
        }
    } catch (e: Throwable) {
        // Handle exceptions and emit error state
        emit(mapToErrorState(e))
    }
}.flowOn(Dispatchers.IO) // Ensure the flow runs on the IO dispatcher


private fun mapToErrorState(exception: Throwable): RequestState.Error {
    val errorMessage = when (exception) {
        is UnknownHostException -> "Unable to resolve host. Please check your connection."
        is IOException -> "Network error. Please check your internet connection."
        else -> exception.message ?: "An unexpected error occurred."
    }
    Log.e("ApiResponse", "API Exception: $errorMessage")
    return RequestState.Error(errorMessage)
}