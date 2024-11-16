package com.example.core.data.repository

import com.example.common.state.RequestState
import com.example.core.data.mapper.toEntity
import com.example.core.data.remote.RetrofitApi
import com.example.core.domain.entities.searchmovies.SearchMoviesResponse
import com.example.core.domain.repository.MovieRepository
import com.example.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val api: RetrofitApi) :MovieRepository {

    override suspend fun getMovies(params: HashMap<String,String>): Flow<RequestState<SearchMoviesResponse>> {
        return safeApiCall(
                apiCall = { api.searchMulti(params) },
                transform = { it.toEntity() } // Convert DTO to Entity using the mapper
        )
    }

}