package com.example.themovieapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.example.themovieapp.navigation.SetUpNavGraph
import com.example.themovieapp.theme.TheMovieAppTheme

@Composable
fun MyApp() {
    var isRtl by remember { mutableStateOf(false) }
    val layoutDirection = if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr


    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        TheMovieAppTheme {
            val navController = rememberNavController()
            SetUpNavGraph(navController = navController, onToggleLayoutDirection = {isRtl = !isRtl})
        }
    }
}