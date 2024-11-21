package com.example.core.domain.repository

import com.example.utils.RequestState
import com.example.core.domain.models.searchmovies.SearchMoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(params: HashMap<String,String>): Flow<RequestState<SearchMoviesResponse>>
}