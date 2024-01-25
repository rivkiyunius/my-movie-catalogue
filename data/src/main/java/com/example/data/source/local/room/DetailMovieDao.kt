package com.example.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.local.entity.DetailAndCollectionWithGenres
import com.example.data.source.local.entity.DetailMovieEntity
import com.example.data.source.local.entity.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionDetail(belongsToCollectionDetailEntity: CollectionDetailMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genresEntity: List<GenresEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovie: DetailMovieEntity)

    @Query("UPDATE detail_movie SET favorite_movie=:isFavorite WHERE detail_movie_id=:movieId")
    suspend fun updateFavoriteData(movieId: Int, isFavorite: Boolean)

    @Transaction
    @Query("SELECT * FROM detail_movie where detail_movie_id=:id")
    fun getDetailMovie(id: Int): Flow<DetailAndCollectionWithGenres>

}