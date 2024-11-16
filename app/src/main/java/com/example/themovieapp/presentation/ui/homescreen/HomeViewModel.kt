package com.example.themovieapp.presentation.ui.homescreen

import androidx.lifecycle.ViewModel
import com.example.common.state.RequestState
import com.example.core.domain.entities.searchmovies.SearchMoviesResponse
import com.example.core.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    private val _searchMoviesResult = MutableStateFlow<RequestState<SearchMoviesResponse>>(RequestState.Idle)
    val searchMoviesResult: StateFlow<RequestState<SearchMoviesResponse>> get() = _searchMoviesResult


    fun searchMovies(query: String) {
        val params = HashMap<String, String>()
        params["query"] = query
        params["include_adult"] = "false"
        params["language"] = "en-US"
        params["page"] = "1"

        viewModelScope.launch {
            useCases.getMoviesUseCase(params).collect {
                _searchMoviesResult.value = it
            }
        }
    }


}