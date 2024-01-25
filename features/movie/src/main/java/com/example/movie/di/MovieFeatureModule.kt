package com.example.movie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.ui.detail.DetailMovieViewModel
import com.example.movie.ui.movielist.MovieViewModel
import com.example.mymoviecatalogue.base.ViewModelFactory
import com.example.mymoviecatalogue.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class MovieFeatureModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailMovieViewModel): ViewModel

//    @Binds
//    abstract fun bindDiffCallback(diffUtil: DiffUtilsConfig): DiffUtil.Callback
}