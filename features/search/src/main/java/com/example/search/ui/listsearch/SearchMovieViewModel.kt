package com.example.search.ui.listsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.domain.model.DiscoverMovie
import com.example.domain.usecase.SearchMovieUseCase
import com.example.domain.utils.Resource
import com.example.mymoviecatalogue.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SearchMovieViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    connectivityObserver: ConnectivityObserver
) :
    BaseViewModel(connectivityObserver) {
    private val _searchMovie = MutableLiveData<List<DiscoverMovie>?>()
    val searchMovie: LiveData<List<DiscoverMovie>?> = _searchMovie
    val queryChannel = MutableStateFlow("")

    val searchResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .mapLatest { searchMovie(it) }
        .asLiveData()

    private fun searchMovie(query: String) {
        viewModelScope.launch {
            searchMovieUseCase(query).collect {
                when (it) {
                    is Resource.Success -> {
                        _searchMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message ?: ""
                    }
                }
            }
        }
    }
}