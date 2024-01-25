package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.FavoriteMovieEntity
import com.example.domain.model.DetailMovie
import javax.inject.Inject

class DetailMovieDomainDataMapper @Inject constructor() :
    BaseDataMapper<FavoriteMovieEntity, DetailMovie>() {
    override fun mapToEntity(model: DetailMovie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = model.id ?: 0,
            adult = model.adult ?: false,
            voteCount = model.voteCount ?: 0,
            voteAverage = model.voteAverage ?: 0.0,
            video = model.video ?: false,
            title = model.title ?: "",
            tagline = model.tagline ?: "",
            runtime = model.runtime ?: 0,
            revenue = model.revenue ?: 0,
            releaseDate = model.releaseDate ?: "",
            popularity = model.popularity ?: 0.0,
            overview = model.overview ?: "",
            originalTitle = model.originalTitle ?: "",
            imdbId = model.imdbId ?: "",
            originalLanguage = model.originalLanguage ?: "",
            homepage = model.homepage ?: "",
            budget = model.budget ?: 0,
            backdropPath = model.backdropPath ?: "",
            posterPath = model.posterPath ?: "",
            favoriteMovie = model.favorite
        )
    }
}