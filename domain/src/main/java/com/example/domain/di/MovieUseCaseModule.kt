package com.example.domain.di

import com.example.domain.repository.Repository
import com.example.domain.usecase.GetDiscoverMovieUseCase
import com.example.domain.usecase.GetDiscoverMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieUseCaseModule {

    @Provides
    @Singleton
    fun provideGetDiscoverMovieUseCase(repository: Repository): GetDiscoverMovieUseCase =
        GetDiscoverMovieUseCaseImpl(repository)
}