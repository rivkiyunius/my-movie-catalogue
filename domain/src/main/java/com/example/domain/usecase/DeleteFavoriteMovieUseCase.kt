package com.example.domain.usecase

import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCaseWithoutResponse

interface DeleteFavoriteMovieUseCase : BaseUseCaseWithoutResponse<Int> {
    override suspend fun invoke(param: Int)
}

class DeleteFavoriteMovieUseCaseImpl(private val repository: Repository) :
    DeleteFavoriteMovieUseCase {
    override suspend fun invoke(param: Int) {
        repository.deleteFavorite(param)
    }

}