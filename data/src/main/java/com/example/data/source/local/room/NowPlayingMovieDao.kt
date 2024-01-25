package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.NowPlayingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingMovieDao {
    @Query("SELECT * FROM now_playing_movie")
    fun getAllNowPlayingMovie(): Flow<List<NowPlayingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovie(movies: List<NowPlayingMovieEntity>)
}