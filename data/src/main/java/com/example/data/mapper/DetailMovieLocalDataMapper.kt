package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.CollectionDetailMovieEntity
import com.example.data.source.local.entity.DetailAndCollectionWithGenres
import com.example.domain.model.DetailMovie
import javax.inject.Inject

class DetailMovieLocalDataMapper @Inject constructor(
    private val genreLocalDataMapper: GenresLocalDataMapper,
    private val collectionLocalDataMapper: CollectionDetailLocalDataMapper
) : BaseDataMapper<DetailMovie, DetailAndCollectionWithGenres?>() {
    override fun mapToEntity(model: DetailAndCollectionWithGenres?): DetailMovie {
        return DetailMovie(
            id = model?.detailMovieEntity?.id ?: 0,
            adult = model?.detailMovieEntity?.adult ?: false,
            voteCount = model?.detailMovieEntity?.voteCount ?: 0,
            voteAverage = model?.detailMovieEntity?.voteAverage ?: 0.0,
            video = model?.detailMovieEntity?.video ?: false,
            title = model?.detailMovieEntity?.title ?: "",
            tagline = model?.detailMovieEntity?.tagline ?: "",
            runtime = model?.detailMovieEntity?.runtime ?: 0,
            revenue = model?.detailMovieEntity?.revenue ?: 0,
            releaseDate = model?.detailMovieEntity?.releaseDate ?: "",
            popularity = model?.detailMovieEntity?.popularity ?: 0.0,
            overview = model?.detailMovieEntity?.overview ?: "",
            originalTitle = model?.detailMovieEntity?.originalTitle ?: "",
            imdbId = model?.detailMovieEntity?.imdbId ?: "",
            originalLanguage = model?.detailMovieEntity?.originalLanguage ?: "",
            homepage = model?.detailMovieEntity?.homepage ?: "",
            budget = model?.detailMovieEntity?.budget ?: 0,
            backdropPath = model?.detailMovieEntity?.backdropPath ?: "",
            posterPath = model?.detailMovieEntity?.posterPath ?: "",
            favorite = model?.detailMovieEntity?.favoriteMovie ?: false,
            belongsToCollection = collectionLocalDataMapper.mapToEntity(
                model?.collectionDetailMovieEntity ?: CollectionDetailMovieEntity(
                    0,
                    0,
                    0,
                    "",
                    "",
                    ""
                )
            ),
            genres = genreLocalDataMapper.mapListToEntity(model?.genresEntity ?: mutableListOf())
        )
    }
}