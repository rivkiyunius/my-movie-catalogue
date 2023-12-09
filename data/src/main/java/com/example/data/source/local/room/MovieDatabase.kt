package com.example.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.source.local.entity.DiscoverMovieEntity

@Database(entities = [DiscoverMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun discoverMovieDao(): DiscoverMovieDao
}