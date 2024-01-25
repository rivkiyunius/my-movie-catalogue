package com.example.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("collection_detail_movie")
data class CollectionDetailMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int? = null,
    @ColumnInfo("collection_id")
    var collectionId: Int?,
    @ColumnInfo("collection_detail_movie_id")
    var detailMovieId: Int?,
    @ColumnInfo("backdrop_path")
    var backdropPath: String?,
    @ColumnInfo("name")
    var name: String?,
    @ColumnInfo("poster_path")
    var posterPath: String?
)
