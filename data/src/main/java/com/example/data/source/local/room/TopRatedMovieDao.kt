package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.TopRatedMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedMovieDao {
    @Query("SELECT * FROM top_rated_movie")
    fun getAllTopRatedMovie(): Flow<List<TopRatedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovie(movies: List<TopRatedMovieEntity>)
}