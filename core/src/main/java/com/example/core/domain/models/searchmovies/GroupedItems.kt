package com.example.core.domain.models.searchmovies

data class GroupedItems(
    val mediaType: String,
    val items: List<Movie>
)