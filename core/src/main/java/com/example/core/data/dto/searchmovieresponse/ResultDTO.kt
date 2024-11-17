package com.example.core.data.dto.searchmovieresponse

data class ResultDTO(
        val adult: Boolean,
        val backdrop_path: String,
        val first_air_date: String,
        val genre_ids: List<Int>,
        val id: Int,
        val media_type: String,
        val name: String?,
        val title:String?,
        val origin_country: List<String>,
        val original_language: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
)