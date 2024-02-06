package com.example.data.di

import com.example.data.source.local.room.CreditsMovieDao
import com.example.data.source.local.room.DetailMovieDao
import com.example.data.source.local.room.DiscoverMovieDao
import com.example.data.source.local.room.FavoriteMovieDao
import com.example.data.source.local.room.NowPlayingMovieDao
import com.example.data.source.local.room.PopularMovieDao
import com.example.data.source.local.room.SearchMovieDao
import com.example.data.source.local.room.TopRatedMovieDao
import com.example.data.source.local.room.UpcomingMovieDao
import com.example.data.source.remote.network.ApiService
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DataDependencies {
    fun provideApiService(): ApiService
    fun provideDiscoverMovieDao(): DiscoverMovieDao
    fun providePopularMovieDao(): PopularMovieDao
    fun provideUpcomingMovieDao(): UpcomingMovieDao
    fun provideNowPlayingMovieDao(): NowPlayingMovieDao
    fun provideTopRatedMovieDao(): TopRatedMovieDao
    fun provideDetailMovieDao(): DetailMovieDao
    fun provideSearchMovieDao(): SearchMovieDao
    fun provideCreditsMovieDao(): CreditsMovieDao
    fun providesFavoriteMovieDao(): FavoriteMovieDao
    fun provideConnectivityObserver(): ConnectivityObserver
    @IoDispatcher fun providesIoDispatcher() : CoroutineDispatcher
}