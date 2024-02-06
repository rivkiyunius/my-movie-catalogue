package com.example.data.source.remote

import com.example.data.di.IoDispatcher
import com.example.data.source.remote.network.ApiResponse
import com.example.data.source.remote.network.ApiService
import com.example.data.source.remote.response.CreditsMovieData
import com.example.data.source.remote.response.DetailMovieData
import com.example.data.source.remote.response.MovieData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    RemoteDataSource {

    override suspend fun getDiscoveryMovies(): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.getDiscoverMovies() }

    override suspend fun getPopularMovies(): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.getPopularMovies() }

    override suspend fun getNowPlayingMovie(): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.getNowPlayingMovie() }

    override suspend fun getTopRatedMovie(): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.getTopRatedMovie() }

    override suspend fun getUpcomingMovie(): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.getUpcomingMovie() }

    override suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<DetailMovieData>> =
        getResponse(ioDispatcher) { apiService.getDetailMovie(movieId) }

    override suspend fun searchMovie(query: String): Flow<ApiResponse<MovieData>> =
        getResponse(ioDispatcher) { apiService.searchMovies(query, false, "en-US", 1) }

    override suspend fun getCast(movieId: Int): Flow<ApiResponse<CreditsMovieData>> =
        getResponse(ioDispatcher) { apiService.getCast(movieId) }

}