package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.domain.model.DetailMovie
import javax.inject.Inject

class CollectionDetailLocalDataMapper @Inject constructor() :
    BaseDataMapper<DetailMovie.BelongsToCollection, CollectionDetailMovieEntity>() {
    override fun mapToEntity(model: CollectionDetailMovieEntity): DetailMovie.BelongsToCollection {
        return DetailMovie.BelongsToCollection(
            id = model.id,
            name = model.name ?: "",
            backdropPath = model.backdropPath ?: "",
            posterPath = model.posterPath ?: "",
        )
    }
}