package com.example.core.domain.entities.searchmovies

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val name: String?,
    val mediaType: String,
    val posterPath: String?,
//    val backdropPath: String?,
    val overview: String?,
)
