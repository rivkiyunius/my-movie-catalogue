package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CreditsMovieDataEntity
import com.example.domain.model.CreditsMovie
import javax.inject.Inject

class CreditsMovieLocalDataMapper @Inject constructor() :
    BaseDataMapper<CreditsMovie, CreditsMovieDataEntity>() {
    override fun mapToEntity(model: CreditsMovieDataEntity): CreditsMovie {
        return CreditsMovie(
            adult = model.adult,
            creditId = model.creditId,
            castId = model.castId,
            character = model.character,
            gender = model.gender,
            id = model.id,
            name = model.name,
            order = model.order,
            originalName = model.originalName,
            popularity = model.popularity,
            profilePath = model.profilePath,
            knownForDepartment = model.knownForDepartment
        )
    }
}