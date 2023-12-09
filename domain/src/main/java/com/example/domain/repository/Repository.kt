package com.example.domain.repository

import com.example.domain.model.DiscoverMovie
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getDiscoverMovies(): Flow<Resource<List<DiscoverMovie>>>
}