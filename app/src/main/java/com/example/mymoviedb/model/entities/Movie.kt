package com.example.mymoviedb.model.entities

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
)
