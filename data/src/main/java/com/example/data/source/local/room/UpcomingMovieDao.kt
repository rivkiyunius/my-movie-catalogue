package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMovieDao {
    @Query("SELECT * FROM upcoming_movie")
    fun getAllUpcomingMovie(): Flow<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(movies: List<UpcomingMovieEntity>)
}