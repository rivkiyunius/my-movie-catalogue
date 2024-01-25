package com.example.favorite.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.usecase.DeleteFavoriteMovieUseCase
import com.example.domain.usecase.GetCreditsMovieUseCase
import com.example.domain.usecase.GetDetailMovieUseCase
import com.example.domain.usecase.InsertFavoriteMovieUseCase
import com.example.domain.utils.Resource
import com.example.mymoviecatalogue.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val detailMovieUseCase: GetDetailMovieUseCase,
    private val getCreditsMovieUseCase: GetCreditsMovieUseCase,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase
) : BaseViewModel(connectivityObserver) {
    private val _detailMovie = MutableLiveData<DetailMovie?>()
    private val _creditMovie = MutableLiveData<List<CreditsMovie>?>()
    private val _isFavorite = MutableLiveData<Boolean>()
    val detailMovie: LiveData<DetailMovie?> = _detailMovie
    val creditMovie: LiveData<List<CreditsMovie>?> = _creditMovie
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            detailMovieUseCase(movieId).collect {
                when (it) {
                    is Resource.Success -> {
                        _detailMovie.value = it.data
                        _isFavorite.value = it.data?.favorite ?: false
                        _isLoading.value = false
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Error -> {
                        _error.value = it.message
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
                        _error.value = it.message
                    }

                    else -> {}
                }
            }
        }
    }

    fun saveFavoriteMovie() {
        viewModelScope.launch {
            _detailMovie.value?.let { insertFavoriteMovieUseCase.invoke(it) }
            _isFavorite.value = true
        }
    }

    fun deleteFavoriteMovie() {
        viewModelScope.launch {
            _detailMovie.value?.id?.let { deleteFavoriteMovieUseCase(it) }
            _isFavorite.value = false
        }
    }
}