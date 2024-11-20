package com.example.themovieapp.presentation.ui.homescreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.themovieapp.R
import com.example.themovieapp.navigation.Screen
import com.example.themovieapp.presentation.commonui.TopBar
import com.example.themovieapp.presentation.vm.HomeViewModel
import com.example.utils.RequestState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun HomeScreen(
        navController: NavHostController,
        viewModel: HomeViewModel = hiltViewModel(),
        onToggleLayoutDirection: () -> Unit
) {
    val keyboardController =
        LocalSoftwareKeyboardController.current // Access the keyboard controller

    val searchMoviesResult by viewModel.searchMoviesResult.collectAsStateWithLifecycle()
    var searchTextValue by rememberSaveable { mutableStateOf("") }

    Scaffold(
            topBar = {
                TopBar(
                        title = stringResource(id = R.string.app_name),
                        isBackButtonVisible = false,
                        onActionClick = {
                            onToggleLayoutDirection()
                        }
                )
            }
    ) { innerpadding ->
        Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                    )
        ) {
            SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    text = searchTextValue,
                    onTextChange = {
                        searchTextValue = it
                        viewModel.onSearchQueryChanged(searchTextValue)
                    },

                    )

            when (searchMoviesResult) {
                is RequestState.Loading -> {
                    LaunchedEffect(Unit) {
                        keyboardController?.hide()
                    }
                    LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                    )
                }

                is RequestState.Success -> {

                    val result = searchMoviesResult.getSuccessData()
                    result?.isNotEmpty()?.let {
                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(result.size) { position ->
                                val groupedItems = result[position]
                                Carousel(
                                        modifier = Modifier,
                                        carouselTitle = groupedItems.mediaType,
                                        items = groupedItems.items
                                ) { movie ->
                                    val movieJson = Uri.encode(Json.encodeToString(movie))
                                    navController.navigate(Screen.Detail.route + "/$movieJson")
                                }
                            }
                        }
                    }
                }

                is RequestState.Error -> {
                    val message = searchMoviesResult.getErrorMessage()
                    message?.let { CenterMessageText(it) }
                }

                is RequestState.Idle -> {
                    CenterMessageText(stringResource(R.string.type_in_the_search_bar_to_search_movies))
                }
            }
        }
    }
}

@Composable
fun CenterMessageText(message: String) {
    Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
    ) {
        Text(
                text = message,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
            rememberNavController(),
            viewModel = hiltViewModel(),
            onToggleLayoutDirection = {}
    )
}