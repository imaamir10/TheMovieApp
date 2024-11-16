package com.example.core.data.dto.searchmovieresponse

data class SearchMoviesResponseDTO(
        val page: Int,
        val results: List<ResultDTO>,
        val total_pages: Int,
        val total_results: Int
)