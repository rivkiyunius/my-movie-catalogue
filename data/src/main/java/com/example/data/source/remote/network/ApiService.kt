package com.example.data.source.remote.network

import com.example.data.source.remote.response.CreditsMovieData
import com.example.data.source.remote.response.DetailMovieData
import com.example.data.source.remote.response.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getDiscoverMovies(): Response<MovieData>

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): Response<MovieData>

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovie(): Response<MovieData>

    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovie(): Response<MovieData>

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovie(): Response<MovieData>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Int): Response<DetailMovieData>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") request: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieData>

    @GET("movie/{movie_id}/credits?language=en-US")
    suspend fun getCast(@Path("movie_id") movieId: Int): Response<CreditsMovieData>
}