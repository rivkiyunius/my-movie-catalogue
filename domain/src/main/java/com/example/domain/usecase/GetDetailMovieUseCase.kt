package com.example.domain.usecase

import com.example.domain.model.DetailMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCaseWithRequest
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetDetailMovieUseCase : BaseUseCaseWithRequest<Int, Flow<Resource<DetailMovie>>> {
    override suspend fun invoke(param: Int): Flow<Resource<DetailMovie>>
}

class GetDetailMovieUseCaseImpl(private val repository: Repository) : GetDetailMovieUseCase {
    override suspend fun invoke(param: Int): Flow<Resource<DetailMovie>> {
        return repository.getDetailMovie(param)
    }
}