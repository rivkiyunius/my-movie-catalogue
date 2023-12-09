package com.example.domain.model

data class DiscoverMovie(
    var adult: Boolean?,
    var backdropPath: String?,
    var id: Int?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String?,
    var video: Boolean?,
    var voteAverage: Double?,
    var voteCount: Int?
)