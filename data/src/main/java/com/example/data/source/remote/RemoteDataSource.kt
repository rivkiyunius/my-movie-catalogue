package com.example.data.source.remote

import com.example.data.source.remote.network.ApiResponse
import com.example.data.source.remote.network.ApiService
import com.example.data.source.remote.response.DiscoverMovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDiscoveryMovies(): Flow<ApiResponse<DiscoverMovieData>> {
        return flow {
            try {
                val response = apiService.getDiscoverMovies()
                val data = response.results
                if (data?.isEmpty() == true) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(errorMessage = e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}