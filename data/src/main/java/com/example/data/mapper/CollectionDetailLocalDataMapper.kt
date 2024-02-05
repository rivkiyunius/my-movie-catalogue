package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.domain.model.BelongsToCollectionMovie
import javax.inject.Inject

class CollectionDetailLocalDataMapper @Inject constructor() :
    BaseDataMapper<BelongsToCollectionMovie, CollectionDetailMovieEntity>() {
    override fun mapToEntity(model: CollectionDetailMovieEntity): BelongsToCollectionMovie {
        return BelongsToCollectionMovie(
            id = model.id,
            name = model.name ?: "",
            backdropPath = model.backdropPath ?: "",
            posterPath = model.posterPath ?: "",
        )
    }
}