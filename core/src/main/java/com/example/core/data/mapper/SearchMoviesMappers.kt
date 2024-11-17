package com.example.core.data.mapper

import com.example.core.data.dto.searchmovieresponse.ResultDTO
import com.example.core.data.dto.searchmovieresponse.SearchMoviesResponseDTO
import com.example.core.domain.entities.searchmovies.Movie
import com.example.core.domain.entities.searchmovies.SearchMoviesResponse

fun ResultDTO.toEntity(): Movie {
    return Movie(
            id = this.id,
            name = this.name?:this.title,
            mediaType = this.media_type,
            posterPath = this.poster_path,
            backdropPath = this.backdrop_path,
            overview = this.overview,

    )
}



fun SearchMoviesResponseDTO.toEntity(): SearchMoviesResponse {
    return SearchMoviesResponse(
            page = this.page,
            results = this.results.map { it.toEntity() }, // Map each ResultDTO to MovieEntity
            totalPages = this.total_pages,
            totalResults = this.total_results
    )
}
