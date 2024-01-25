package com.example.search.di

import android.content.Context
import com.example.data.di.DataDependencies
import com.example.data.di.DataSourceModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.MovieUseCaseModule
import com.example.search.ui.detail.DetailSearchFragment
import com.example.search.ui.listsearch.SearchMovieFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DataDependencies::class],
    modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        MovieUseCaseModule::class,
        SearchMovieFeatureModule::class
    ]
)
interface SearchMovieFeatureComponent {
    fun inject(searchMovieFragment: SearchMovieFragment)
    fun inject(detailSearch: DetailSearchFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dataDependencies(dataDependencies: DataDependencies): Builder
        fun build(): SearchMovieFeatureComponent
    }
}