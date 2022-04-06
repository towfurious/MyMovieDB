package com.example.mymoviedb.model.repository

import com.example.mymoviedb.model.entities.MoviesList
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getMovies(lastUploadedPage: String = "1"): Single<MoviesList>
}