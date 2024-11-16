package com.example.core.data.remote

import com.example.core.data.dto.searchmovieresponse.SearchMoviesResponseDTO
import com.example.utils.ENDPOINT_SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitApi {
    @GET(ENDPOINT_SEARCH)
    suspend fun searchMulti(
            @QueryMap parameters: Map<String, String>
    ): Response<SearchMoviesResponseDTO>
}