package com.example.data.mapper

import com.example.data.mapper.base.BaseDataMapper
import com.example.data.source.local.entity.GenresEntity
import com.example.data.source.remote.response.DetailMovieData
import javax.inject.Inject

class GenresRemoteDataMapper @Inject constructor() :
    BaseDataMapper<GenresEntity, DetailMovieData.Genre>() {

    private var movieId: Int = 0

    override fun mapToEntity(model: DetailMovieData.Genre): GenresEntity {
        return GenresEntity(
            genreId = model.id ?: 0,
            name = model.name ?: "",
            detailMovieId = movieId
        )
    }

    fun setDetailMovieId(movieId: Int) {
        this.movieId = movieId
    }
}