package com.example.data

import android.util.Log
import com.example.data.mapper.CollectionDetailRemoteDataMapper
import com.example.data.mapper.CreditsMovieLocalDataMapper
import com.example.data.mapper.CreditsMovieRemoteDataMapper
import com.example.data.mapper.DetailMovieDomainDataMapper
import com.example.data.mapper.DetailMovieLocalDataMapper
import com.example.data.mapper.DetailMovieRemoteDataMapper
import com.example.data.mapper.DiscoverMovieLocalDataMapper
import com.example.data.mapper.FavoriteMovieLocalDataMapper
import com.example.data.mapper.GenresRemoteDataMapper
import com.example.data.mapper.NowPlayingMovieLocalDataMapper
import com.example.data.mapper.NowPlayingMovieRemoteDataMapper
import com.example.data.mapper.PopularMovieLocalDataMapper
import com.example.data.mapper.PopularMovieRemoteDataMapper
import com.example.data.mapper.SearchMovieLocalDataMapper
import com.example.data.mapper.SearchMovieRemoteDataMapper
import com.example.data.mapper.TopRatedMovieLocalDataMapper
import com.example.data.mapper.TopRatedMovieRemoteDataMapper
import com.example.data.mapper.UpcomingMovieLocalDataMapper
import com.example.data.mapper.UpcomingMovieRemoteDataMapper
import com.example.data.source.local.LocalDataSource
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.local.entity.GenresEntity
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.network.ApiResponse
import com.example.data.source.remote.response.CreditsMovieData
import com.example.data.source.remote.response.DetailMovieData
import com.example.data.source.remote.response.MovieData
import com.example.data.utils.NetworkBoundSource
import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.model.DiscoverMovie
import com.example.domain.model.FavoriteMovie
import com.example.domain.repository.Repository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val discoverMovieLocalDataMapper: DiscoverMovieLocalDataMapper,
    private val popularMovieLocalDataMapper: PopularMovieLocalDataMapper,
    private val nowPlayingMovieLocalDataMapper: NowPlayingMovieLocalDataMapper,
    private val topRatedMovieLocalDataMapper: TopRatedMovieLocalDataMapper,
    private val upcomingMovieLocalDataMapper: UpcomingMovieLocalDataMapper,
    private val popularMovieRemoteDataMapper: PopularMovieRemoteDataMapper,
    private val nowPlayingMovieRemoteDataMapper: NowPlayingMovieRemoteDataMapper,
    private val topRatedMovieRemoteDataMapper: TopRatedMovieRemoteDataMapper,
    private val upcomingMovieRemoteDataMapper: UpcomingMovieRemoteDataMapper,
    private val collectionDetailRemoteDataMapper: CollectionDetailRemoteDataMapper,
    private val genresRemoteDataMapper: GenresRemoteDataMapper,
    private val detailMovieLocalDataMapper: DetailMovieLocalDataMapper,
    private val detailMovieRemoteDataMapper: DetailMovieRemoteDataMapper,
    private val detailMovieDomainDataMapper: DetailMovieDomainDataMapper,
    private val searchMovieLocalDataMapper: SearchMovieLocalDataMapper,
    private val searchMovieRemoteDataMapper: SearchMovieRemoteDataMapper,
    private val creditsMovieRemoteDataMapper: CreditsMovieRemoteDataMapper,
    private val creditsMovieLocalDataMapper: CreditsMovieLocalDataMapper,
    private val favoriteMovieLocalDataMapper: FavoriteMovieLocalDataMapper
) : Repository {
    override suspend fun getDiscoverMovies(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getAllMovieDiscover()
                    .map { discoverMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.getDiscoveryMovies()
            }

            override suspend fun saveCallResult(data: MovieData) {}

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true

        }.asFlow()

    override suspend fun getPopularMovies(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getAllPopularMovie()
                    .map { popularMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: MovieData) {
                val movies = popularMovieRemoteDataMapper.mapListToEntity(data = data.results)
                localDataSource.insertPopularMovie(movies)
            }

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true
        }.asFlow()

    override suspend fun getNowPlayingMovie(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getNowPlayingMovie()
                    .map { nowPlayingMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.getNowPlayingMovie()
            }

            override suspend fun saveCallResult(data: MovieData) {
                val movies = nowPlayingMovieRemoteDataMapper.mapListToEntity(data = data.results)
                localDataSource.insertNowPlayingMovie(movies)
            }

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true
        }.asFlow()

    override suspend fun getTopRatedMovie(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getTopRatedMovie()
                    .map { topRatedMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.getTopRatedMovie()
            }

            override suspend fun saveCallResult(data: MovieData) {
                val movies = topRatedMovieRemoteDataMapper.mapListToEntity(data = data.results)
                localDataSource.insertTopRatedMovie(movies)
            }

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true
        }.asFlow()

    override suspend fun getUpcomingMovie(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getUpcomingMovie()
                    .map { upcomingMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.getUpcomingMovie()
            }

            override suspend fun saveCallResult(data: MovieData) {
                val movies = upcomingMovieRemoteDataMapper.mapListToEntity(data = data.results)
                localDataSource.insertUpcomingMovie(movies)
            }

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true
        }.asFlow()

    override suspend fun getDetailMovie(id: Int): Flow<Resource<DetailMovie>> =
        object : NetworkBoundSource<DetailMovie, DetailMovieData>() {
            override fun loadFromDB(): Flow<DetailMovie> {
                return localDataSource.getDetailMovie(id)
                    .map { detailMovieLocalDataMapper.mapToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieData>> {
                Log.d("TAG_REMOTE_CALLED", id.toString())
                return remoteDataSource.getDetailMovie(id)
            }

            override suspend fun saveCallResult(data: DetailMovieData) {
                collectionDetailRemoteDataMapper.setDetailMovieId(data.id ?: 0)
                genresRemoteDataMapper.setDetailMovieId(data.id ?: 0)
                insertCollectionDetailMovie(
                    collectionDetailRemoteDataMapper.mapToEntity(
                        data.belongsToCollection ?: DetailMovieData.BelongsToCollection(
                            "",
                            0,
                            "",
                            ""
                        )
                    )
                )
                insertGenres(genresRemoteDataMapper.mapListToEntity(data.genres ?: mutableListOf()))
                localDataSource.insertDetailMovie(detailMovieRemoteDataMapper.mapToEntity(data))
            }

            override fun shouldFetch(data: DetailMovie?): Boolean = data?.id == 0
        }.asFlow()

    override suspend fun searchMovie(query: String): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, MovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getResultSearch()
                    .map { searchMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieData>> {
                return remoteDataSource.searchMovie(query)
            }

            override suspend fun saveCallResult(data: MovieData) {
                val movies = searchMovieRemoteDataMapper.mapListToEntity(data = data.results)
                localDataSource.insertResultSearch(movies)
            }

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true
        }.asFlow()

    override suspend fun getCasts(movieId: Int): Flow<Resource<List<CreditsMovie>>> =
        object : NetworkBoundSource<List<CreditsMovie>, CreditsMovieData>() {
            override fun loadFromDB(): Flow<List<CreditsMovie>> {
                return localDataSource.getCasts(movieId)
                    .map { creditsMovieLocalDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<CreditsMovieData>> {
                return remoteDataSource.getCast(movieId)
            }

            override suspend fun saveCallResult(data: CreditsMovieData) {
                creditsMovieRemoteDataMapper.setMovieId(data.id)
                val credits = creditsMovieRemoteDataMapper.mapListToEntity(data = data.cast)
                localDataSource.insertCast(credits)
            }

            override fun shouldFetch(data: List<CreditsMovie>?): Boolean = true
        }.asFlow()

    override suspend fun insertFavorite(detailMovie: DetailMovie) {
        val data = detailMovieDomainDataMapper.mapToEntity(detailMovie)
        localDataSource.insertFavoriteMovie(data)
        localDataSource.updateFavoriteData(data.id ?: 0, true)
    }

    override suspend fun deleteFavorite(movieId: Int) {
        localDataSource.deleteFavoriteMovie(movieId)
        localDataSource.updateFavoriteData(movieId, false)
    }

    override suspend fun getFavorite(): Flow<List<FavoriteMovie>> {
        return localDataSource.getFavoriteMovies().map {
            favoriteMovieLocalDataMapper.mapListToEntity(it)
        }
    }

    private suspend fun insertCollectionDetailMovie(collectionDetailMovieEntity: CollectionDetailMovieEntity) {
        localDataSource.insertCollectionDetailMovie(collectionDetailMovieEntity)
    }

    private suspend fun insertGenres(genresEntity: List<GenresEntity>) {
        localDataSource.insertGenres(genresEntity)
    }
}