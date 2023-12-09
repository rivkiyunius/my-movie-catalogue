package com.example.mymoviecatalogue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetDiscoverMovieUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val discoverMovieUseCase: GetDiscoverMovieUseCase) :
    ViewModel() {

    fun getDiscoverMovie() {
        viewModelScope.launch {
            discoverMovieUseCase().collect {
                when(it) {
                    is Resource.Success -> {
                        Log.d("TAG_SUCCESS", "${it.data?.size}")
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Error -> {
                        Log.e("TAG_ERROR", "${it.message}")
                    }
                }
            }
        }
    }

}