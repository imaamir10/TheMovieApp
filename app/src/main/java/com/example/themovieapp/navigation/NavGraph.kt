package com.example.themovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.domain.entities.searchmovies.Movie
import com.example.themovieapp.navigation.NavArgsConstants.MOVIE_DATA
import com.example.themovieapp.presentation.ui.detailscreen.DetailScreen
import com.example.themovieapp.presentation.ui.homescreen.HomeScreen
import com.example.themovieapp.presentation.ui.homescreen.HomeViewModel
import com.example.themovieapp.presentation.ui.playerscreen.PlayerScreen
import kotlinx.serialization.json.Json

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
            navController = navController,
            startDestination = Screen.Home.route

    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
                route = Screen.Detail.route + "/{$MOVIE_DATA}",
                arguments = listOf(navArgument(MOVIE_DATA) { type = NavType.StringType })
        ) { backStackEntry ->
            val movieData = backStackEntry.arguments?.getString(MOVIE_DATA)
            val movie = movieData?.let { Json.decodeFromString<Movie>(it) }
            movie?.let { DetailScreen(navController,movie= it) }
        }
        composable(route = Screen.Player.route) {
            PlayerScreen(navController)
        }
    }
}