package com.example.movie.di

import android.content.Context
import com.example.data.di.DataDependencies
import com.example.data.di.DataSourceModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.MovieUseCaseModule
import com.example.movie.ui.detail.DetailMovieFragment
import com.example.movie.ui.movielist.MovieFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DataDependencies::class],
    modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        MovieUseCaseModule::class,
        MovieFeatureModule::class
    ]
)
interface MovieFeatureComponent {
    fun inject(movieFragment: MovieFragment)
    fun inject(detailMovieFragment: DetailMovieFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dataDependencies(dataDependencies: DataDependencies): Builder
        fun build(): MovieFeatureComponent
    }
}