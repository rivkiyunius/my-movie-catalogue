package com.example.data.di

import com.example.data.RepositoryImpl
import com.example.data.mapper.DiscoverMovieDataMapper
import com.example.data.source.local.LocalDataSource
import com.example.data.source.remote.RemoteDataSource
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        discoverMovieDataMapper: DiscoverMovieDataMapper
    ): Repository = RepositoryImpl(remoteDataSource, localDataSource, discoverMovieDataMapper)
}