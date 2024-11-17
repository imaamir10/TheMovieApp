package com.example.themovieapp.presentation.ui.homescreen

import androidx.lifecycle.ViewModel
import com.example.utils.RequestState
import com.example.core.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.searchmovies.GroupedItems
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _searchMoviesResult = MutableStateFlow<RequestState<List<GroupedItems>>>(RequestState.Idle)
    val searchMoviesResult: StateFlow<RequestState<List<GroupedItems>>> get() = _searchMoviesResult

    fun searchMovies(query: String) {
        val params = HashMap<String, String>()
        params["query"] = query
        params["include_adult"] = "false"
        params["language"] = "en-US"
        params["page"] = "1"

        viewModelScope.launch {
            useCases.getMoviesUseCase(params).collect { response ->
                when(response){
                    is RequestState.Success -> {
                        val result = response.data.results.groupBy {
                            it.mediaType
                        }.map { entry->
                            val sortedItems = entry.value.sortedBy { it.name }
                            GroupedItems(entry.key, sortedItems)
                        }
                        _searchMoviesResult.value = RequestState.Success(result)
                    }
                    is RequestState.Error -> {
                        _searchMoviesResult.value = RequestState.Error(response.message)
                    }
                    is RequestState.Loading -> {
                        _searchMoviesResult.value = RequestState.Loading
                    }
                    is RequestState.Idle->{
                        _searchMoviesResult.value = RequestState.Idle
                    }
                }
            }
        }
    }


}