package com.example.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_rated_movie")
data class TopRatedMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo("id")
    var id: Int,
    @ColumnInfo("adult")
    var adult: Boolean?,
    @ColumnInfo("backdrop_path")
    var backdropPath: String?,
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
    @ColumnInfo("title")
    var title: String?,
    @ColumnInfo("video")
    var video: Boolean?,
    @ColumnInfo("vote_average")
    var voteAverage: Double?,
    @ColumnInfo("vote_count")
    var voteCount: Int?
)
