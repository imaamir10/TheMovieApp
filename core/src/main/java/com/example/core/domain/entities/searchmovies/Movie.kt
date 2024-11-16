package com.example.core.domain.entities.searchmovies

data class Movie(
    val id: Int,
    val title: String?,
    val name: String?,
    val mediaType: String,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String?,
    val firstAirDate: String?,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)
