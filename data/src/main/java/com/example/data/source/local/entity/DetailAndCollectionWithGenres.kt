package com.example.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DetailAndCollectionWithGenres(
    @Embedded val detailMovieEntity: DetailMovieEntity,
    @Relation(parentColumn = "detail_movie_id", entityColumn = "collection_detail_movie_id")
    val collectionDetailMovieEntity: CollectionDetailMovieEntity,
    @Relation(parentColumn = "detail_movie_id", entityColumn = "genres_detail_movie_id")
    val genresEntity: List<GenresEntity>
)
