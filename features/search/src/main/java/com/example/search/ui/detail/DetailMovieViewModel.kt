package com.example.search.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.usecase.GetCreditsMovieUseCase
import com.example.domain.usecase.GetDetailMovieUseCase
import com.example.domain.usecase.InsertFavoriteMovieUseCase
import com.example.domain.utils.Resource
import com.example.mymoviecatalogue.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    connectivityObserver: ConnectivityObserver,
    private val detailMovieUseCase: GetDetailMovieUseCase,
    private val getCreditsMovieUseCase: GetCreditsMovieUseCase,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase
) : BaseViewModel(connectivityObserver) {
    private val _detailMovie = MutableLiveData<DetailMovie?>()
    private val _creditMovie = MutableLiveData<List<CreditsMovie>?>()
    val detailMovie: LiveData<DetailMovie?> = _detailMovie
    val creditMovie: LiveData<List<CreditsMovie>?> = _creditMovie

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            detailMovieUseCase(movieId).collect {
                when (it) {
                    is Resource.Success -> {
                        _detailMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message ?: ""
                    }

                    else -> {}
                }
            }
        }
    }

    fun getCreditMovie(movieId: Int) {
        viewModelScope.launch {
            getCreditsMovieUseCase(movieId).collect {
                when (it) {
                    is Resource.Success -> {
                        _creditMovie.value = it.data
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message ?: ""
                    }

                    else -> {}
                }
            }
        }
    }

    fun saveFavoriteMovie() {
        viewModelScope.launch {
            _detailMovie.value?.let { insertFavoriteMovieUseCase.invoke(it) }
        }
    }
}