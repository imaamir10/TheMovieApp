package com.example.core.domain.entities.searchmovies

data class SearchMoviesResponse(
        val page: Int,
        val results: List<Movie>,
        val totalPages: Int,
        val totalResults: Int
)
