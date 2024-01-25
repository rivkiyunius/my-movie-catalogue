package com.example.domain.usecase

import com.example.domain.model.FavoriteMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesUseCase: BaseUseCase<Flow<List<FavoriteMovie>>> {
    override suspend fun invoke(): Flow<List<FavoriteMovie>>
}

class GetFavoriteMovieUseCaseImpl(private val repository: Repository): GetFavoriteMoviesUseCase {
    override suspend fun invoke(): Flow<List<FavoriteMovie>> {
        return repository.getFavorite()
    }

}