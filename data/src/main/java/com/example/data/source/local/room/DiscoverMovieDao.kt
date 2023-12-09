package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.DiscoverMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscoverMovieDao {
    @Query("SELECT * FROM discover_movie")
    fun getAllDiscoverMovie(): Flow<List<DiscoverMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiscoverMovie(movies: List<DiscoverMovieEntity>)
}