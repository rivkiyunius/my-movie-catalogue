package com.example.domain.usecase

import com.example.domain.model.CreditsMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCaseWithRequest
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetCreditsMovieUseCase: BaseUseCaseWithRequest<Int, Flow<Resource<List<CreditsMovie>>>> {
    override suspend fun invoke(param: Int): Flow<Resource<List<CreditsMovie>>>
}

class GetCreditsMovieUseCaseImpl(private val repository: Repository): GetCreditsMovieUseCase {
    override suspend fun invoke(param: Int): Flow<Resource<List<CreditsMovie>>> {
        return repository.getCasts(param)
    }
}