package com.example.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.local.entity.CreditsMovieDataEntity
import com.example.data.source.local.entity.DetailMovieEntity
import com.example.data.source.local.entity.DiscoverMovieEntity
import com.example.data.source.local.entity.FavoriteMovieEntity
import com.example.data.source.local.entity.GenresEntity
import com.example.data.source.local.entity.NowPlayingMovieEntity
import com.example.data.source.local.entity.PopularMovieEntity
import com.example.data.source.local.entity.SearchMovieEntity
import com.example.data.source.local.entity.TopRatedMovieEntity
import com.example.data.source.local.entity.UpcomingMovieEntity

@Database(
    entities = [
        DiscoverMovieEntity::class,
        PopularMovieEntity::class,
        NowPlayingMovieEntity::class,
        TopRatedMovieEntity::class,
        UpcomingMovieEntity::class,
        CollectionDetailMovieEntity::class,
        GenresEntity::class,
        DetailMovieEntity::class,
        SearchMovieEntity::class,
        CreditsMovieDataEntity::class,
        FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun discoverMovieDao(): DiscoverMovieDao
    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun nowPlayingMovieDao(): NowPlayingMovieDao
    abstract fun topRatedMovieDao(): TopRatedMovieDao
    abstract fun upcomingMovieDao(): UpcomingMovieDao
    abstract fun detailMovieDao(): DetailMovieDao
    abstract fun searchMovieDao(): SearchMovieDao
    abstract fun creditsMovieDao(): CreditsMovieDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}