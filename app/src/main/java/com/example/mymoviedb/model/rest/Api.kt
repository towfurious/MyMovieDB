package com.example.mymoviedb.model.rest

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    // https://api.themoviedb.org/3/movie/top_rated?api_key=297235911a5be7c0fce6e8907dc5f8d9&language=en-US&page=1
    private lateinit var retrofit: Retrofit

    const val API_KEY = "297235911a5be7c0fce6e8907dc5f8d9"
    const val PAGE = "1"
    private const val VERSION = "3/"
    private const val BASE_URL = "https://api.themoviedb.org/$VERSION"
    val instance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) {
                val headersInterceptor = Interceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    chain.proceed(requestBuilder.build())
                }
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
                    .addInterceptor(headersInterceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
}