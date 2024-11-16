package com.example.themovieapp.presentation.ui.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier,viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.searchMovies("Mission impossible")
    }
    Box(modifier = modifier.fillMaxSize()) {

        Text(text = "Home Screen",modifier = Modifier.align(Alignment.Center), color = Color.White)

    }
}