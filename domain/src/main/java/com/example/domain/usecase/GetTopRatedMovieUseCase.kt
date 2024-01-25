package com.example.domain.usecase

import com.example.domain.model.DiscoverMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCase
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetTopRatedMovieUseCase : BaseUseCase<Flow<Resource<List<DiscoverMovie>>>> {
    override suspend fun invoke(): Flow<Resource<List<DiscoverMovie>>>
}

class GetTopRatedMovieUseCaseImpl(private val repository: Repository) : GetTopRatedMovieUseCase {
    override suspend fun invoke(): Flow<Resource<List<DiscoverMovie>>> =
        repository.getTopRatedMovie()
}