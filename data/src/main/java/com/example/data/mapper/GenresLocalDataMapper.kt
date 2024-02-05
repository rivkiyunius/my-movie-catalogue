package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.GenresEntity
import com.example.domain.model.GenreMovie
import javax.inject.Inject

class GenresLocalDataMapper @Inject constructor() :
    BaseDataMapper<GenreMovie, GenresEntity>() {
    override fun mapToEntity(model: GenresEntity): GenreMovie {
        return GenreMovie(
            id = model.id ?: 0,
            name = model.name ?: ""
        )
    }

}