package com.example.core.domain.usecases

import com.example.core.domain.entities.searchmovies.GroupedItems
import com.example.core.domain.entities.searchmovies.Movie
import com.example.utils.RequestState
import com.example.core.domain.repository.MovieRepository
import com.example.utils.MAX_RESULT_PAGE_LIMIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException

class GetMoviesUseCase(private val remoteRepository: MovieRepository) {

    suspend operator fun invoke(params: HashMap<String, String>): Flow<RequestState<List<GroupedItems>>> =
        flow {
            emit(RequestState.Loading)
            val allMovies = mutableMapOf<String, MutableList<Movie>>()
            var currentPage = 1
            var totalPages = 1

            do {

                params["page"] = currentPage.toString()
                val response = remoteRepository.getMovies(params)
                    .filter { it !is RequestState.Loading } // Skip Loading state
                    .first() // Wait for Success or Error // Fetch first emitted value

                when (response) {
                    is RequestState.Success -> {
                        totalPages = response.data.totalPages
                        val fetchedMovies = response.data.results

                        // Break the loop if the fetched list is empty
                        if (currentPage ==1 && fetchedMovies.isEmpty()) {
                            emit(RequestState.Error("No result found"))
                            break
                        }

                        fetchedMovies.forEach { movie ->
                            val movieList = allMovies.computeIfAbsent(movie.mediaType) { mutableListOf() }
                            if (movieList.none() { it.id == movie.id }) {
                                movieList.add(movie)
                            }
                        }

                        emit(RequestState.Success(allMovies.toGroupedItems()))
                        currentPage++
                    }

                    is RequestState.Error -> {
                        emit(RequestState.Error(response.message))
                        return@flow
                    }

                    else -> {

                    }
                }
            } while (currentPage <= totalPages && currentPage <= MAX_RESULT_PAGE_LIMIT)

        }.catch { e ->
            if (e is CancellationException) throw e
            emit(RequestState.Error(e.message ?: "An unexpected error occurred"))
        }
}

private fun Map<String, MutableList<Movie>>.toGroupedItems(): List<GroupedItems> {
    return this.map { (mediaType, movies) ->
        GroupedItems(mediaType, movies.sortedBy { it.name }) // Sort each group by name
    }
}