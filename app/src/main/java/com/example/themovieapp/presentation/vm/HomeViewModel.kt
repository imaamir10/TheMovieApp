@file:OptIn(FlowPreview::class)

package com.example.themovieapp.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.utils.RequestState
import com.example.core.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.searchmovies.GroupedItems
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _searchMoviesResult =
        MutableStateFlow<RequestState<List<GroupedItems>>>(RequestState.Idle)
    val searchMoviesResult: StateFlow<RequestState<List<GroupedItems>>> get() = _searchMoviesResult

    private val searchQuery = MutableStateFlow("") // Holds the current search query

    private var searchJob: Job? = null // Keep track of the current search job


    init {
        searchJob = viewModelScope.launch {
            searchQuery
                .debounce(1000) // Wait for 500ms of inactivity
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        _searchMoviesResult.value = RequestState.Loading
                        searchMovies(query)
                    } else {
                        _searchMoviesResult.value = RequestState.Idle
                    }
                }
        }
    }

    // Function to update the search query
    fun onSearchQueryChanged(newQuery: String) {
        searchQuery.value = newQuery


    }


    private suspend fun searchMovies(query: String) {
        val params = HashMap<String, String>().apply {
            this["query"] = query
            this["include_adult"] = "true"
            this["language"] = "en-US"
        }


        useCases.getMoviesUseCase(params).collect { response ->
            _searchMoviesResult.value = response
        }

    }

}