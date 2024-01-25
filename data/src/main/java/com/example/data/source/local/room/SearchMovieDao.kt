package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.local.entity.SearchMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchMovieDao {
    @Query("SELECT * FROM search_movie")
    fun getResultSearch(): Flow<List<SearchMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultSearch(movies: List<SearchMovieEntity>)

    @Query("DELETE FROM search_movie")
    suspend fun clearTable()
}