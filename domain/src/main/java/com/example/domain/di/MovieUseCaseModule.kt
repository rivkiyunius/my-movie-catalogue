package com.example.domain.di

import com.example.domain.repository.Repository
import com.example.domain.usecase.DeleteFavoriteMovieUseCase
import com.example.domain.usecase.DeleteFavoriteMovieUseCaseImpl
import com.example.domain.usecase.GetCreditsMovieUseCase
import com.example.domain.usecase.GetCreditsMovieUseCaseImpl
import com.example.domain.usecase.GetDetailMovieUseCase
import com.example.domain.usecase.GetDetailMovieUseCaseImpl
import com.example.domain.usecase.GetDiscoverMovieUseCase
import com.example.domain.usecase.GetDiscoverMovieUseCaseImpl
import com.example.domain.usecase.GetFavoriteMovieUseCaseImpl
import com.example.domain.usecase.GetFavoriteMoviesUseCase
import com.example.domain.usecase.GetNowPlayingMovieUseCase
import com.example.domain.usecase.GetNowPlayingMovieUseCaseImpl
import com.example.domain.usecase.GetPopularMovieUseCase
import com.example.domain.usecase.GetPopularMovieUseCaseImpl
import com.example.domain.usecase.GetTopRatedMovieUseCase
import com.example.domain.usecase.GetTopRatedMovieUseCaseImpl
import com.example.domain.usecase.GetUpcomingMovieUseCase
import com.example.domain.usecase.GetUpcomingMovieUseCaseImpl
import com.example.domain.usecase.InsertFavoriteMovieUseCase
import com.example.domain.usecase.InsertFavoriteMovieUseCaseImpl
import com.example.domain.usecase.SearchMovieUseCase
import com.example.domain.usecase.SearchMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieUseCaseModule {

    @Provides
    fun provideGetDiscoverMovieUseCase(repository: Repository): GetDiscoverMovieUseCase =
        GetDiscoverMovieUseCaseImpl(repository)

    @Provides
    fun provideGetPopularMovieUseCase(repository: Repository): GetPopularMovieUseCase =
        GetPopularMovieUseCaseImpl(repository)

    @Provides
    fun provideNowPlayingMovieUseCase(repository: Repository): GetNowPlayingMovieUseCase =
        GetNowPlayingMovieUseCaseImpl(repository)

    @Provides
    fun provideTopRatedMovieUseCase(repository: Repository): GetTopRatedMovieUseCase =
        GetTopRatedMovieUseCaseImpl(repository)

    @Provides
    fun provideUpcomingMovieUseCase(repository: Repository): GetUpcomingMovieUseCase =
        GetUpcomingMovieUseCaseImpl(repository)

    @Provides
    fun provideDetailMovieUseCase(repository: Repository): GetDetailMovieUseCase =
        GetDetailMovieUseCaseImpl(repository)

    @Provides
    fun provideSearchMovieUseCase(repository: Repository): SearchMovieUseCase =
        SearchMovieUseCaseImpl(repository)

    @Provides
    fun provideGetCreditsMovieUseCase(repository: Repository): GetCreditsMovieUseCase =
        GetCreditsMovieUseCaseImpl(repository)

    @Provides
    fun provideInsertFavoriteMovieUseCase(repository: Repository): InsertFavoriteMovieUseCase =
        InsertFavoriteMovieUseCaseImpl(repository)

    @Provides
    fun provideDeleteFavoriteMovieUseCase(repository: Repository): DeleteFavoriteMovieUseCase =
        DeleteFavoriteMovieUseCaseImpl(repository)

    @Provides
    fun provideGetFavoriteMoviesUseCase(repository: Repository): GetFavoriteMoviesUseCase =
        GetFavoriteMovieUseCaseImpl(repository)
}