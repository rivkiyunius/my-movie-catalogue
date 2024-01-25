package com.example.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviecatalogue.base.ViewModelFactory
import com.example.mymoviecatalogue.base.ViewModelKey
import com.example.search.ui.detail.DetailMovieViewModel
import com.example.search.ui.listsearch.SearchMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class SearchMovieFeatureModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieViewModel::class)
    abstract fun bindSearchMovieViewModel(viewModel: SearchMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailMovieViewModel(viewModel: DetailMovieViewModel): ViewModel
}