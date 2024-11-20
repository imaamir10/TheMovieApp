package com.example.themovieapp.presentation.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.core.domain.entities.searchmovies.Movie
import com.example.themovieapp.R
import com.example.themovieapp.utils.imageURl


@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: Movie, onItemClick: (Movie) -> Unit) {
    val context = LocalContext.current
    val imageLoader = context.imageLoader
    Surface(
            modifier = modifier
                .clickable { onItemClick(movie) }
                .width(150.dp) // Adjust width here (e.g., 70-80% of screen width)
                .height(200.dp) // Adjust height as needed
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (!movie.posterPath.isNullOrEmpty()) {
                AsyncImage(
                        model = imageURl(movie.posterPath!!),
                        contentDescription = movie.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        error = painterResource(id = R.drawable.ic_error),
                        imageLoader = imageLoader

                )
            } else {
                Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                ) {
                    Text(
                            text = stringResource(R.string.no_image_available),
                            color = Color.Black,
                            fontSize = 12.sp
                    )
                    movie.name?.let {
                        Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
