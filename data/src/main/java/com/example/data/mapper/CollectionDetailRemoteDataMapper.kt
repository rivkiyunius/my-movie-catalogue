package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.remote.response.DetailMovieData
import javax.inject.Inject

class CollectionDetailRemoteDataMapper @Inject constructor() :
    BaseDataMapper<CollectionDetailMovieEntity, DetailMovieData.BelongsToCollection>() {
    private var movieId: Int = 0

    override fun mapToEntity(model: DetailMovieData.BelongsToCollection): CollectionDetailMovieEntity {
        return CollectionDetailMovieEntity(
            collectionId = model.id,
            name = model.name ?: "",
            backdropPath = model.backdropPath ?: "",
            posterPath = model.posterPath ?: "",
            detailMovieId = movieId
        )
    }

    fun setDetailMovieId(movieId: Int) {
        this.movieId = movieId
    }
}