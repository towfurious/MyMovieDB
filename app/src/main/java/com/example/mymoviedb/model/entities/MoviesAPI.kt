package com.example.mymoviedb.model.entities

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
    ): Single<MoviesList>
}