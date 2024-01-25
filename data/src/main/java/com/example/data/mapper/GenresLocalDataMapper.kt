package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.GenresEntity
import com.example.domain.model.DetailMovie
import javax.inject.Inject

class GenresLocalDataMapper @Inject constructor() :
    BaseDataMapper<DetailMovie.Genre, GenresEntity>() {
    override fun mapToEntity(model: GenresEntity): DetailMovie.Genre {
        return DetailMovie.Genre(
            id = model.id ?: 0,
            name = model.name ?: ""
        )
    }

}