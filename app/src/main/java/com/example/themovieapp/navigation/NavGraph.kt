package com.example.themovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.themovieapp.presentation.ui.detailscreen.DetailScreen
import com.example.themovieapp.presentation.ui.homescreen.HomeScreen
import com.example.themovieapp.presentation.ui.homescreen.HomeViewModel
import com.example.themovieapp.presentation.ui.playerscreen.PlayerScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
            navController = navController,
            startDestination = Screen.Home.route

    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Detail.route) {
            DetailScreen()
        }
        composable(route = Screen.Player.route) {
            PlayerScreen()
        }
    }
}