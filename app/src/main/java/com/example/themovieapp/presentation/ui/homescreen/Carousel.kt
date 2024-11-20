package com.example.themovieapp.presentation.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.entities.searchmovies.Movie

@Composable
fun Carousel(modifier: Modifier = Modifier, carouselTitle: String, items: List<Movie>, onItemClick: (Movie) -> Unit) {
    Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
    ) {

        Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = carouselTitle,
                color = Color.White,
                fontSize = 20.sp
        )
        LazyRow(

                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                    items = items,
                    key = { _, item -> item.id  }
            ) {_, movie ->
                MovieItem(
                        modifier = Modifier.animateItem(),
                        movie = movie,
                        onItemClick = onItemClick
                )
            }
        }
    }
}