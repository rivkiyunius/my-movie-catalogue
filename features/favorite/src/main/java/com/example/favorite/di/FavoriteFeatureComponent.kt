package com.example.favorite.di

import android.content.Context
import com.example.data.di.DataDependencies
import com.example.data.di.DataSourceModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.MovieUseCaseModule
import com.example.favorite.ui.detail.DetailFavoriteMovieFragment
import com.example.favorite.ui.favoritelist.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DataDependencies::class], modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        MovieUseCaseModule::class,
        FavoriteFeatureModule::class
    ]
)
interface FavoriteFeatureComponent {
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(detailFavoriteMovieFragment: DetailFavoriteMovieFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dataDependencies(dataDependencies: DataDependencies): Builder
        fun build(): FavoriteFeatureComponent
    }
}