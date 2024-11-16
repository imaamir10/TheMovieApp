package com.example.themovieapp.navigation

sealed class Screen(val route: String) {
    data object Home:Screen("home")
    data object Detail:Screen("detail")
    data object Player:Screen("player")
}