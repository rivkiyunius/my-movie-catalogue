package com.example.data.source.remote.network

import com.example.data.source.remote.response.DiscoverMovieData
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getDiscoverMovies(): DiscoverMovieData
}