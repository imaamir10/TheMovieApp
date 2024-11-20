package com.example.themovieapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.themovieapp.navigation.SetUpNavGraph
import com.example.themovieapp.theme.TheMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.SystemBarStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.themovieapp.presentation.MyApp

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

