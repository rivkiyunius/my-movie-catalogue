package com.example.data.di

import com.example.data.RepositoryImpl
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
import com.example.data.source.remote.RemoteDataSource
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        discoverMovieLocalDataMapper: DiscoverMovieLocalDataMapper,
        movieLocalDataMapper: PopularMovieRemoteDataMapper,
        nowPlayingMovieLocalDataMapper: NowPlayingMovieLocalDataMapper,
        topRatedMovieLocalDataMapper: TopRatedMovieLocalDataMapper,
        upcomingMovieLocalDataMapper: UpcomingMovieLocalDataMapper,
        popularMovieLocalDataMapper: PopularMovieLocalDataMapper,
        nowPlayingMovieRemoteDataMapper: NowPlayingMovieRemoteDataMapper,
        topRatedMovieRemoteDataMapper: TopRatedMovieRemoteDataMapper,
        upcomingMovieRemoteDataMapper: UpcomingMovieRemoteDataMapper,
        collectionDetailRemoteDataMapper: CollectionDetailRemoteDataMapper,
        genresRemoteDataMapper: GenresRemoteDataMapper,
        detailMovieLocalDataMapper: DetailMovieLocalDataMapper,
        detailMovieRemoteDataMapper: DetailMovieRemoteDataMapper,
        detailMovieDomainDataMapper: DetailMovieDomainDataMapper,
        searchMovieRemoteDataMapper: SearchMovieRemoteDataMapper,
        searchMovieLocalDataMapper: SearchMovieLocalDataMapper,
        creditsMovieRemoteDataMapper: CreditsMovieRemoteDataMapper,
        creditsMovieLocalDataMapper: CreditsMovieLocalDataMapper,
        favoriteMovieLocalDataMapper: FavoriteMovieLocalDataMapper
    ): Repository = RepositoryImpl(
        remoteDataSource,
        localDataSource,
        discoverMovieLocalDataMapper,
        popularMovieLocalDataMapper,
        nowPlayingMovieLocalDataMapper,
        topRatedMovieLocalDataMapper,
        upcomingMovieLocalDataMapper,
        movieLocalDataMapper,
        nowPlayingMovieRemoteDataMapper,
        topRatedMovieRemoteDataMapper,
        upcomingMovieRemoteDataMapper,
        collectionDetailRemoteDataMapper,
        genresRemoteDataMapper,
        detailMovieLocalDataMapper,
        detailMovieRemoteDataMapper,
        detailMovieDomainDataMapper,
        searchMovieLocalDataMapper,
        searchMovieRemoteDataMapper,
        creditsMovieRemoteDataMapper,
        creditsMovieLocalDataMapper,
        favoriteMovieLocalDataMapper
    )
}