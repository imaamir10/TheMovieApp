package com.example.themovieapp.presentation.ui.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.core.domain.entities.searchmovies.Movie
import com.example.themovieapp.R
import com.example.themovieapp.navigation.Screen
import com.example.themovieapp.presentation.commonui.TopBar
import com.example.themovieapp.utils.imageURl

@Composable
fun DetailScreen(navController: NavHostController, movie : Movie, onToggleLayoutDirection: () -> Unit) {
    Scaffold(
            topBar = {
                TopBar(
                        title = stringResource(id = R.string.detail_screen),
                        isBackButtonVisible = true,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onActionClick = {
                            onToggleLayoutDirection()
                        }
                )
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Black,
    ) { padding ->
        Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
        ) {
            // Top Image Section
            Box(
                    modifier = Modifier
                        .fillMaxWidth()

            ) {
                AsyncImage(
                        model = movie.posterPath?.let { imageURl(it) },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        error = painterResource(id = R.drawable.ic_error)
                )
                if(movie.mediaType == "tv" || movie.mediaType == "movie") {
                    Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = "play_icon",
                            tint = Color.Red,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Player.route)

                            }.size(90.dp).align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Movie Title
            Text(
                    text = movie.name?:"",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Movie Description
            Text(
                    text = movie.overview?:"",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
            )
        }
    }
}