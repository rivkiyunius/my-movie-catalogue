package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteMovie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movie")
    fun getFavorites(): Flow<List<FavoriteMovieEntity>>

    @Query("DELETE FROM favorite_movie WHERE favorite_movie_id = :movieId")
    suspend fun deleteFavorites(movieId: Int)
}