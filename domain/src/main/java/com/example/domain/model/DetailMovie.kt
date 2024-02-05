package com.example.domain.model

data class DetailMovie(
    var adult: Boolean?,
    var backdropPath: String?,
    var belongsToCollection: BelongsToCollectionMovie?,
    var budget: Int?,
    var genres: List<GenreMovie?>?,
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
)