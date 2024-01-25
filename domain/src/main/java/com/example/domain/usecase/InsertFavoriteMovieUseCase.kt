package com.example.domain.usecase

import com.example.domain.model.DetailMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCaseWithoutResponse

interface InsertFavoriteMovieUseCase : BaseUseCaseWithoutResponse<DetailMovie> {
    override suspend fun invoke(param: DetailMovie)
}

class InsertFavoriteMovieUseCaseImpl(private val repository: Repository) :
    InsertFavoriteMovieUseCase {
    override suspend fun invoke(param: DetailMovie) {
        repository.insertFavorite(param)
    }

}