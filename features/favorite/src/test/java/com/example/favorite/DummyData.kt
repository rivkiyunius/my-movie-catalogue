package com.example.favorite

import com.example.domain.model.BelongsToCollectionMovie
import com.example.domain.model.CreditsMovie
import com.example.domain.model.DetailMovie
import com.example.domain.model.FavoriteMovie
import com.example.domain.model.GenreMovie

val dummyDetailMovie = DetailMovie(
    voteCount = 10,
    voteAverage = 10.0,
    video = false,
    releaseDate = "",
    overview = "",
    title = "",
    posterPath = "",
    originalTitle = "",
    adult = false,
    backdropPath = "",
    popularity = 10.0,
    favorite = false,
    originalLanguage = "",
    id = 10,
    budget = 1000,
    homepage = "",
    imdbId = "",
    revenue = 0,
    runtime = 0,
    tagline = "",
    genres = listOf(
        GenreMovie(
            id = 1,
            name = "comedy"
        )
    ),
    belongsToCollection = BelongsToCollectionMovie(
        id = 10,
        posterPath = "",
        name = "",
        backdropPath = ""
    )
)

val dummyFavoriteMovie = FavoriteMovie(
    voteCount = 10,
    voteAverage = 10.0,
    video = false,
    releaseDate = "",
    overview = "",
    title = "",
    posterPath = "",
    originalTitle = "",
    adult = false,
    backdropPath = "",
    popularity = 10.0,
    favorite = false,
    originalLanguage = "",
    id = 10,
    budget = 1000,
    homepage = "",
    imdbId = "",
    revenue = 0,
    runtime = 0,
    tagline = ""
)

val dummyCredits = CreditsMovie(
    name = "",
    creditId = "",
    id = 10,
    adult = false,
    popularity = 10.0,
    knownForDepartment = "",
    profilePath = "",
    originalName = "",
    order = 0,
    gender = 1,
    character = "",
    castId = 10
)