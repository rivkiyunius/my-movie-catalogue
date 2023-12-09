package com.example.data.source.local

import com.example.data.source.local.entity.DiscoverMovieEntity
import com.example.data.source.local.room.DiscoverMovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val discoverMovieDao: DiscoverMovieDao) {
    fun getAllMovieDiscover(): Flow<List<DiscoverMovieEntity>> =
        discoverMovieDao.getAllDiscoverMovie()

    fun insertAddDiscoverMovie(movie: List<DiscoverMovieEntity>) =
        discoverMovieDao.insertDiscoverMovie(movie)
}