package com.example.themovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.themovieapp.presentation.MyApp
import com.example.themovieapp.theme.TheMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(Color.Black.toArgb()),
                navigationBarStyle = SystemBarStyle.dark(Color.Black.toArgb())
        )
        setContent {
            TheMovieAppTheme {
                MyApp()
            }
        }
    }
}

