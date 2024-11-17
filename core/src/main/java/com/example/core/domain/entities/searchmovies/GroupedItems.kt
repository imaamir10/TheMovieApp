package com.example.core.domain.entities.searchmovies

data class GroupedItems(
    val mediaType: String,
    val items: List<Movie>
)