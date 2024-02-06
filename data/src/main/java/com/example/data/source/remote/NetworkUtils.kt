package com.example.data.source.remote

import com.example.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

internal suspend fun <T> getResponse(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> Response<T>
): Flow<ApiResponse<T>> {
    return flow {
        try {
            val response = request()
            val data = response.body()
            if (data != null) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(errorMessage = e.toString()))
        }
    }.flowOn(dispatcher)
}