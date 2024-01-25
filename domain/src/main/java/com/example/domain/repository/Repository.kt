package com.example.domain.repository

import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.model.DiscoverMovie
import com.example.domain.model.FavoriteMovie
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getDiscoverMovies(): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getPopularMovies(): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getNowPlayingMovie(): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getTopRatedMovie(): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getUpcomingMovie(): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getDetailMovie(id: Int): Flow<Resource<DetailMovie>>
    suspend fun searchMovie(query: String): Flow<Resource<List<DiscoverMovie>>>
    suspend fun getCasts(movieId: Int): Flow<Resource<List<CreditsMovie>>>
    suspend fun insertFavorite(detailMovie: DetailMovie)
    suspend fun deleteFavorite(movieId: Int)
    suspend fun getFavorite(): Flow<List<FavoriteMovie>>
}