package com.example.themovieapp.navigation

import com.example.themovieapp.navigation.NavArgsConstants.MOVIE_DATA

sealed class Screen(val route: String) {
    data object Home:Screen("home")
    data object Detail:Screen("detail")
    data object Player:Screen("player")
}