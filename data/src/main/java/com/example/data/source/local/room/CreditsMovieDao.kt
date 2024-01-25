package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.CreditsMovieDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditsMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(casts: List<CreditsMovieDataEntity>)

    @Query("SELECT * FROM credits where movie_id=:id")
    fun getCasts(id: Int): Flow<List<CreditsMovieDataEntity>>
}