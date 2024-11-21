package com.example.core.domain.models.searchmovies

data class SearchMoviesResponse(
        val page: Int,
        val results: List<Movie>,
        val totalPages: Int,
        val totalResults: Int
)
