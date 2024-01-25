package com.example.favorite.ui.favoritelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.FavoriteMovie
import com.example.domain.usecase.GetFavoriteMoviesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteMovieUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<FavoriteMovie>>()
    val favoriteMovies: LiveData<List<FavoriteMovie>> = _favoriteMovies

    fun getFavoriteMovie() {
        viewModelScope.launch {
            getFavoriteMovieUseCase().collectLatest {
                _favoriteMovies.value = it
            }
        }
    }
}