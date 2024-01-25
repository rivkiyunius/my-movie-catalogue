package com.example.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("genres")
data class GenresEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int? = null,
    @ColumnInfo("genre_id")
    var genreId: Int?,
    @ColumnInfo("genres_detail_movie_id")
    var detailMovieId: Int?,
    @ColumnInfo("name")
    var name: String?
)
