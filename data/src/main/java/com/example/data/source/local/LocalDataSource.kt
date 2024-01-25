package com.example.data.source.local

import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.local.entity.CreditsMovieDataEntity
import com.example.data.source.local.entity.DetailAndCollectionWithGenres
import com.example.data.source.local.entity.DetailMovieEntity
import com.example.data.source.local.entity.DiscoverMovieEntity
import com.example.data.source.local.entity.FavoriteMovieEntity
import com.example.data.source.local.entity.GenresEntity
import com.example.data.source.local.entity.NowPlayingMovieEntity
import com.example.data.source.local.entity.PopularMovieEntity
import com.example.data.source.local.entity.SearchMovieEntity
import com.example.data.source.local.entity.TopRatedMovieEntity
import com.example.data.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMovieDiscover(): Flow<List<DiscoverMovieEntity>>
    suspend fun insertAddDiscoverMovie(movie: List<DiscoverMovieEntity>)
    fun getAllPopularMovie(): Flow<List<PopularMovieEntity>>
    suspend fun insertPopularMovie(movies: List<PopularMovieEntity>)
    fun getNowPlayingMovie(): Flow<List<NowPlayingMovieEntity>>
    suspend fun insertNowPlayingMovie(movies: List<NowPlayingMovieEntity>)
    fun getTopRatedMovie(): Flow<List<TopRatedMovieEntity>>
    suspend fun insertTopRatedMovie(movies: List<TopRatedMovieEntity>)
    fun getUpcomingMovie(): Flow<List<UpcomingMovieEntity>>
    suspend fun insertUpcomingMovie(movies: List<UpcomingMovieEntity>)
    suspend fun insertCollectionDetailMovie(collectionDetailMovieEntity: CollectionDetailMovieEntity)
    suspend fun insertGenres(genresEntity: List<GenresEntity>)
    suspend fun insertDetailMovie(detailMovieEntity: DetailMovieEntity)
    fun getDetailMovie(id: Int): Flow<DetailAndCollectionWithGenres>
    fun getResultSearch(): Flow<List<SearchMovieEntity>>
    suspend fun insertResultSearch(data: List<SearchMovieEntity>)
    suspend fun insertCast(data: List<CreditsMovieDataEntity>)
    fun getCasts(movieId: Int): Flow<List<CreditsMovieDataEntity>>
    suspend fun insertFavoriteMovie(data: FavoriteMovieEntity)
    suspend fun deleteFavoriteMovie(movieId: Int)
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun updateFavoriteData(movieId: Int, isFavorite: Boolean)
}