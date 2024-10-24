package com.example.movieshelf

import com.example.movieshelf.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") authToken: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}

