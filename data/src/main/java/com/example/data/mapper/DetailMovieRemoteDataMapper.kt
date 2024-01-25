package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.DetailMovieEntity
import com.example.data.source.remote.response.DetailMovieData
import javax.inject.Inject

class DetailMovieRemoteDataMapper @Inject constructor() :
    BaseDataMapper<DetailMovieEntity, DetailMovieData>() {
    override fun mapToEntity(model: DetailMovieData): DetailMovieEntity {
        return DetailMovieEntity(
            id = model.id ?: 0,
            posterPath = model.posterPath ?: "",
            backdropPath = model.backdropPath ?: "",
            adult = model.adult ?: false,
            budget = model.budget ?: 0,
            homepage = model.homepage ?: "",
            imdbId = model.imdbId ?: "",
            originalLanguage = model.originalLanguage ?: "",
            originalTitle = model.originalTitle ?: "",
            overview = model.overview ?: "",
            popularity = model.popularity ?: 0.0,
            releaseDate = model.releaseDate ?: "",
            revenue = model.revenue ?: 0,
            runtime = model.runtime ?: 0,
            tagline = model.tagline ?: "",
            title = model.title ?: "",
            video = model.video ?: false,
            voteAverage = model.voteAverage ?: 0.0,
            voteCount = model.voteCount ?: 0,
            favoriteMovie = false
        )
    }
}