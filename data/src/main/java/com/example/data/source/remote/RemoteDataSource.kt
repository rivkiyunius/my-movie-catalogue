package com.example.data.source.remote

import com.example.data.source.remote.network.ApiResponse
import com.example.data.source.remote.response.CreditsMovieData
import com.example.data.source.remote.response.DetailMovieData
import com.example.data.source.remote.response.MovieData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getDiscoveryMovies(): Flow<ApiResponse<MovieData>>
    suspend fun getPopularMovies(): Flow<ApiResponse<MovieData>>
    suspend fun getNowPlayingMovie(): Flow<ApiResponse<MovieData>>
    suspend fun getTopRatedMovie(): Flow<ApiResponse<MovieData>>
    suspend fun getUpcomingMovie(): Flow<ApiResponse<MovieData>>
    suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<DetailMovieData>>
    suspend fun searchMovie(query: String): Flow<ApiResponse<MovieData>>
    suspend fun getCast(movieId: Int): Flow<ApiResponse<CreditsMovieData>>
}