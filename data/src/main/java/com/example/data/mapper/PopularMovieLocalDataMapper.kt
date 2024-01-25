package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.PopularMovieEntity
import com.example.domain.model.DiscoverMovie
import javax.inject.Inject

class PopularMovieLocalDataMapper @Inject constructor() :
    BaseDataMapper<DiscoverMovie, PopularMovieEntity>() {
    override fun mapToEntity(model: PopularMovieEntity): DiscoverMovie {
        return DiscoverMovie(
            adult = model.adult ?: false,
            backdropPath = model.backdropPath ?: "",
            id = model.id ?: 0,
            originalLanguage = model.originalLanguage ?: "",
            originalTitle = model.originalTitle ?: "",
            overview = model.overview ?: "",
            popularity = model.popularity ?: 0.0,
            posterPath = model.posterPath ?: "",
            releaseDate = model.releaseDate ?: "",
            title = model.title ?: "",
            video = model.video ?: false,
            voteAverage = model.voteAverage ?: 0.0,
            voteCount = model.voteCount ?: 0
        )
    }
}