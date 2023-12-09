package com.example.data

import com.example.data.mapper.DiscoverMovieDataMapper
import com.example.data.source.NetworkBoundSource
import com.example.data.source.local.LocalDataSource
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.network.ApiResponse
import com.example.data.source.remote.response.DiscoverMovieData
import com.example.domain.model.DiscoverMovie
import com.example.domain.repository.Repository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val discoverMovieDataMapper: DiscoverMovieDataMapper
) : Repository {
    override suspend fun getDiscoverMovies(): Flow<Resource<List<DiscoverMovie>>> =
        object : NetworkBoundSource<List<DiscoverMovie>, DiscoverMovieData>() {
            override fun loadFromDB(): Flow<List<DiscoverMovie>> {
                return localDataSource.getAllMovieDiscover()
                    .map { discoverMovieDataMapper.mapListToEntity(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<DiscoverMovieData>> {
                return remoteDataSource.getDiscoveryMovies()
            }

            override suspend fun saveCallResult(data: DiscoverMovieData) {}

            override fun shouldFetch(data: List<DiscoverMovie>?): Boolean = true

        }.asFlow()
}