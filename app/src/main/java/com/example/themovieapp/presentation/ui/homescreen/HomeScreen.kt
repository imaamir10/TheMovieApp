package com.example.themovieapp.presentation.ui.homescreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.utils.RequestState
import com.example.core.domain.entities.searchmovies.GroupedItems
import com.example.themovieapp.navigation.Screen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val searchMoviesResult by viewModel.searchMoviesResult.collectAsStateWithLifecycle()
    var searchTextValue by rememberSaveable { mutableStateOf("") }

    Scaffold {innerpadding->
        Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerpadding)
                    .background(Color.Black)
                    .fillMaxSize()
                    .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                    )
        ) {
            SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    text = searchTextValue,
                    onTextChange = { searchTextValue = it
                        viewModel.onSearchQueryChanged(searchTextValue) },

            )
            if (searchMoviesResult is RequestState.Loading) {
                LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                )
            }
            when(searchMoviesResult){
                is RequestState.Success -> {
                    val result = (searchMoviesResult as RequestState.Success<List<GroupedItems>>).data
                    if (result.isNotEmpty()) {
                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(result.size) { position ->
                                val groupedItems = result[position]
                                Carousel(
                                        carouselTitle = groupedItems.mediaType,
                                        items = groupedItems.items
                                ){movie->
                                    val movieJson = Uri.encode(Json.encodeToString(movie))
                                    navController.navigate(Screen.Detail.route+"/$movieJson")
                                }
                            }
                        }
                    }
                }

                is RequestState.Error->{
//                    val error = (searchMoviesResult as RequestState.Error).getErrorMessage()
//                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }

//                is RequestState.Idle->{
//
//                }
                else ->{

                }
            }


        }
    }

}

@Composable
fun ErrorSnackBar(
        errorMessage: String,
        onDismiss: () -> Unit
) {
    var showSnackbar by rememberSaveable { mutableStateOf(true) }

    if (showSnackbar) {
        Snackbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                action = {
                    TextButton(onClick = {
                        showSnackbar = false
                        onDismiss()
                    }) {
                        Text(text = "Dismiss", color = MaterialTheme.colorScheme.primary)
                    }
                },
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
        ) {
            Text(text = errorMessage)
        }
    }
}

@Composable
fun LoadingView() {
    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}
@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}