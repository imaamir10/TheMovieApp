package com.example.core.domain.usecases

import com.example.utils.RequestState
import com.example.core.domain.entities.searchmovies.SearchMoviesResponse
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val remoteRepository: MovieRepository) {
    suspend operator fun invoke(headers: HashMap<String, String>) : Flow<RequestState<SearchMoviesResponse>> {
        return remoteRepository.getMovies(headers)

    }
}