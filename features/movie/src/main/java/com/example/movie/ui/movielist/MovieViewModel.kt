package com.example.movie.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DiscoverMovie
import com.example.domain.usecase.GetNowPlayingMovieUseCase
import com.example.domain.usecase.GetPopularMovieUseCase
import com.example.domain.usecase.GetTopRatedMovieUseCase
import com.example.domain.usecase.GetUpcomingMovieUseCase
import com.example.domain.utils.Resource
import com.example.mymoviecatalogue.base.BaseViewModel
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val popularMovieUseCase: GetPopularMovieUseCase,
    private val nowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val topRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val upcomingMovieUseCase: GetUpcomingMovieUseCase
) : BaseViewModel(connectivityObserver) {

    private val _popularMovie = MutableLiveData<List<DiscoverMovie>?>()
    private val _nowPlayingMovie = MutableLiveData<List<DiscoverMovie>?>()
    private val _topRatedMovie = MutableLiveData<List<DiscoverMovie>?>()
    private val _upcomingMovie = MutableLiveData<List<DiscoverMovie>?>()

    val popularMovie: LiveData<List<DiscoverMovie>?> = _popularMovie
    val nowPlayingMovie: LiveData<List<DiscoverMovie>?> = _nowPlayingMovie
    val topRatedMovie: LiveData<List<DiscoverMovie>?> = _topRatedMovie
    val upcomingMovie: LiveData<List<DiscoverMovie>?> = _upcomingMovie

    fun getPopularMovie() {
        viewModelScope.launch {
            popularMovieUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _popularMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message
                    }
                }
            }
        }
    }

    fun getNowPlayingMovie() {
        viewModelScope.launch {
            nowPlayingMovieUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _nowPlayingMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message
                    }
                }
            }
        }
    }

    fun getTopRatedMovie() {
        viewModelScope.launch {
            topRatedMovieUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _topRatedMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message
                    }
                }
            }
        }
    }


    fun getUpcomingMovie() {
        viewModelScope.launch {
            upcomingMovieUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _upcomingMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message
                    }
                }
            }
        }
    }
}