package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.PopularMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao {
    @Query("SELECT * FROM popular_movie")
    fun getAllPopularMovie(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(movies: List<PopularMovieEntity>)
}