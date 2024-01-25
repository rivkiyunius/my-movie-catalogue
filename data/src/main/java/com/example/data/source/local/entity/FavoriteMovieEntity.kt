package com.example.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo("favorite_movie_id")
    var id: Int?,
    @ColumnInfo("adult")
    var adult: Boolean?,
    @ColumnInfo("backdrop_path")
    var backdropPath: String?,
    @ColumnInfo("budget")
    var budget: Int?,
    @ColumnInfo("homepage")
    var homepage: String?,
    @ColumnInfo("imdb_id")
    var imdbId: String?,
    @ColumnInfo("original_language")
    var originalLanguage: String?,
    @ColumnInfo("original_title")
    var originalTitle: String?,
    @ColumnInfo("overview")
    var overview: String?,
    @ColumnInfo("popularity")
    var popularity: Double?,
    @ColumnInfo("poster_path")
    var posterPath: String?,
    @ColumnInfo("release_date")
    var releaseDate: String?,
    @ColumnInfo("revenue")
    var revenue: Int?,
    @ColumnInfo("runtime")
    var runtime: Int?,
    @ColumnInfo("tagline")
    var tagline: String?,
    @ColumnInfo("title")
    var title: String?,
    @ColumnInfo("video")
    var video: Boolean?,
    @ColumnInfo("vote_average")
    var voteAverage: Double?,
    @ColumnInfo("vote_count")
    var voteCount: Int?,
    @ColumnInfo("favorite")
    var favoriteMovie: Boolean?
)