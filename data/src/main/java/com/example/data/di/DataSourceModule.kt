package com.example.data.di

import com.example.data.source.local.LocalDataSource
import com.example.data.source.local.LocalDataSourceImpl
import com.example.data.source.local.room.CreditsMovieDao
import com.example.data.source.local.room.DetailMovieDao
import com.example.data.source.local.room.DiscoverMovieDao
import com.example.data.source.local.room.FavoriteMovieDao
import com.example.data.source.local.room.NowPlayingMovieDao
import com.example.data.source.local.room.PopularMovieDao
import com.example.data.source.local.room.SearchMovieDao
import com.example.data.source.local.room.TopRatedMovieDao
import com.example.data.source.local.room.UpcomingMovieDao
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.RemoteDataSourceImpl
import com.example.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesLocalDataSource(
        discoverMovieDao: DiscoverMovieDao,
        popularMovieDao: PopularMovieDao,
        nowPlayingMovieDao: NowPlayingMovieDao,
        topRatedMovieDao: TopRatedMovieDao,
        upcomingMovieDao: UpcomingMovieDao,
        detailMovieDao: DetailMovieDao,
        searchMovieDao: SearchMovieDao,
        creditsMovieDao: CreditsMovieDao,
        favoriteMovieDao: FavoriteMovieDao
    ): LocalDataSource =
        LocalDataSourceImpl(
            discoverMovieDao,
            popularMovieDao,
            nowPlayingMovieDao,
            topRatedMovieDao,
            upcomingMovieDao,
            detailMovieDao,
            searchMovieDao,
            creditsMovieDao,
            favoriteMovieDao
        )

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSourceImpl(apiService)
}