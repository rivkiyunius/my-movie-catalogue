package com.example.domain.usecase

import com.example.domain.model.DiscoverMovie
import com.example.domain.repository.Repository
import com.example.domain.usecase.base.BaseUseCaseWithRequest
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchMovieUseCase : BaseUseCaseWithRequest<String, Flow<Resource<List<DiscoverMovie>>>> {
    override suspend fun invoke(param: String): Flow<Resource<List<DiscoverMovie>>>
}

class SearchMovieUseCaseImpl(private val repository: Repository) : SearchMovieUseCase {
    override suspend fun invoke(param: String): Flow<Resource<List<DiscoverMovie>>> {
        return repository.searchMovie(param)
    }

}