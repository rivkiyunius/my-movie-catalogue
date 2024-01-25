package com.example.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("credits")
data class CreditsMovieDataEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("movie_id")
    val movieId: Int,
    @ColumnInfo("adult")
    val adult: Boolean,
    @ColumnInfo("cast_id")
    val castId: Int,
    @ColumnInfo("character")
    val character: String,
    @ColumnInfo("credit_id")
    val creditId: String,
    @ColumnInfo("gender")
    val gender: Int,
    @ColumnInfo("known_for_department")
    val knownForDepartment: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("order")
    val order: Int,
    @ColumnInfo("original_name")
    val originalName: String,
    @ColumnInfo("popularity")
    val popularity: Double,
    @ColumnInfo("profile_path")
    val profilePath: String
)