package com.example.domain.model

data class DetailMovie(
    var adult: Boolean?,
    var backdropPath: String?,
    var belongsToCollection: BelongsToCollection?,
    var budget: Int?,
    var genres: List<Genre?>?,
    var homepage: String?,
    var id: Int?,
    var imdbId: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var releaseDate: String?,
    var revenue: Int?,
    var runtime: Int?,
    var tagline: String?,
    var title: String?,
    var video: Boolean?,
    var voteAverage: Double?,
    var voteCount: Int?,
    var favorite: Boolean
) {
    data class BelongsToCollection(
        var backdropPath: String?,
        var id: Int?,
        var name: String?,
        var posterPath: String?
    )

    data class Genre(
        var id: Int?,
        var name: String?
    )
}