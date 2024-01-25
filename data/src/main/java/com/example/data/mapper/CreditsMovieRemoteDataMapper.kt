package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CreditsMovieDataEntity
import com.example.data.source.remote.response.CreditsMovieData
import javax.inject.Inject

class CreditsMovieRemoteDataMapper @Inject constructor() :
    BaseDataMapper<CreditsMovieDataEntity, CreditsMovieData.Cast>() {
    private var movieId: Int = 0
    override fun mapToEntity(model: CreditsMovieData.Cast): CreditsMovieDataEntity {
        return CreditsMovieDataEntity(
            movieId = movieId,
            adult = model.adult ?: false,
            creditId = model.creditId ?: "",
            castId = model.castId ?: 0,
            character = model.character ?: "",
            gender = model.gender ?: 0,
            id = model.id ?: 0,
            name = model.name ?: "",
            order = model.order ?: 0,
            originalName = model.originalName ?: "",
            popularity = model.popularity ?: 0.0,
            profilePath = model.profilePath ?: "",
            knownForDepartment = model.knownForDepartment ?: ""
        )
    }

    fun setMovieId(movieId: Int) {
        this.movieId = movieId
    }
}