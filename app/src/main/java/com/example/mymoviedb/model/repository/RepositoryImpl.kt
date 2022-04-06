package com.example.mymoviedb.model.repository

import com.example.mymoviedb.model.entities.MoviesAPI
import com.example.mymoviedb.model.entities.MoviesList
import com.example.mymoviedb.model.rest.RetrofitClientInstance
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override fun getMovies(lastUploadedPage: String): Single<MoviesList> {
        val retrofit = RetrofitClientInstance
        return retrofit.instance.create(MoviesAPI::class.java)
            .getMovies(retrofit.API_KEY, lastUploadedPage)
    }
}