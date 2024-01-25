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
import com.example.data.source.local.room.CreditsMovieDao
import com.example.data.source.local.room.DetailMovieDao
import com.example.data.source.local.room.DiscoverMovieDao
import com.example.data.source.local.room.FavoriteMovieDao
import com.example.data.source.local.room.NowPlayingMovieDao
import com.example.data.source.local.room.PopularMovieDao
import com.example.data.source.local.room.SearchMovieDao
import com.example.data.source.local.room.TopRatedMovieDao
import com.example.data.source.local.room.UpcomingMovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val discoverMovieDao: DiscoverMovieDao,
    private val popularMovieDao: PopularMovieDao,
    private val nowPlayingMovieDao: NowPlayingMovieDao,
    private val topRatedMovieDao: TopRatedMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao,
    private val detailMovieDao: DetailMovieDao,
    private val searchMovieDao: SearchMovieDao,
    private val creditsMovieDao: CreditsMovieDao,
    private val favoriteMovieDao: FavoriteMovieDao
) : LocalDataSource {
    override fun getAllMovieDiscover(): Flow<List<DiscoverMovieEntity>> =
        discoverMovieDao.getAllDiscoverMovie()

    override suspend fun insertAddDiscoverMovie(movie: List<DiscoverMovieEntity>) =
        discoverMovieDao.insertDiscoverMovie(movie)

    override fun getAllPopularMovie(): Flow<List<PopularMovieEntity>> =
        popularMovieDao.getAllPopularMovie()

    override suspend fun insertPopularMovie(movies: List<PopularMovieEntity>) =
        popularMovieDao.insertPopularMovie(movies)

    override fun getNowPlayingMovie(): Flow<List<NowPlayingMovieEntity>> =
        nowPlayingMovieDao.getAllNowPlayingMovie()

    override suspend fun insertNowPlayingMovie(movies: List<NowPlayingMovieEntity>) =
        nowPlayingMovieDao.insertNowPlayingMovie(movies)

    override fun getTopRatedMovie(): Flow<List<TopRatedMovieEntity>> =
        topRatedMovieDao.getAllTopRatedMovie()

    override suspend fun insertTopRatedMovie(movies: List<TopRatedMovieEntity>) =
        topRatedMovieDao.insertTopRatedMovie(movies)

    override fun getUpcomingMovie(): Flow<List<UpcomingMovieEntity>> =
        upcomingMovieDao.getAllUpcomingMovie()

    override suspend fun insertUpcomingMovie(movies: List<UpcomingMovieEntity>) =
        upcomingMovieDao.insertUpcomingMovie(movies)

    override suspend fun insertCollectionDetailMovie(collectionDetailMovieEntity: CollectionDetailMovieEntity) =
        detailMovieDao.insertCollectionDetail(collectionDetailMovieEntity)

    override suspend fun insertGenres(genresEntity: List<GenresEntity>) =
        detailMovieDao.insertGenres(genresEntity)

    override suspend fun insertDetailMovie(detailMovieEntity: DetailMovieEntity) {
        detailMovieDao.insertDetailMovie(detailMovieEntity)
    }

    override fun getDetailMovie(id: Int): Flow<DetailAndCollectionWithGenres> =
        detailMovieDao.getDetailMovie(id)

    override fun getResultSearch(): Flow<List<SearchMovieEntity>> = searchMovieDao.getResultSearch()

    override suspend fun insertResultSearch(data: List<SearchMovieEntity>) {
        searchMovieDao.clearTable()
        searchMovieDao.insertResultSearch(data)
    }

    override suspend fun insertCast(data: List<CreditsMovieDataEntity>) {
        creditsMovieDao.insertCast(data)
    }

    override fun getCasts(movieId: Int): Flow<List<CreditsMovieDataEntity>> =
        creditsMovieDao.getCasts(movieId)

    override suspend fun insertFavoriteMovie(data: FavoriteMovieEntity) {
        favoriteMovieDao.insertFavorite(data)
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        favoriteMovieDao.deleteFavorites(movieId)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> =
        favoriteMovieDao.getFavorites()

    override suspend fun updateFavoriteData(movieId: Int, isFavorite: Boolean) {
        detailMovieDao.updateFavoriteData(movieId, isFavorite)
    }

}