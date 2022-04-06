package com.example.mymoviedb

import com.example.mymoviedb.model.entities.MoviesAPI
import com.example.mymoviedb.model.entities.MoviesList
import com.example.mymoviedb.model.rest.RetrofitClientInstance
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Test


class ExampleUnitTest {

    @Test
    fun retrofitRxTest() {
        val retrofit = RetrofitClientInstance
        val service: Single<MoviesList> = retrofit.instance.create(MoviesAPI::class.java)
            .getMovies(retrofit.API_KEY, retrofit.PAGE)
        Assert.assertEquals(service.blockingGet()::class.java, MoviesList::class.java)
    }
}