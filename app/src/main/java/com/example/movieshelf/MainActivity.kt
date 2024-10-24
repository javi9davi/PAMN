package com.example.movieshelf

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshelf.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val instance: MovieApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieApiService::class.java)
    }
}



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNmMxY2VlYjBjN2I5ODdkNmM4YWYwZWRjNjgzZGYyYiIsIm5iZiI6MTcyOTc2NTI0OS4yMjI4NTgsInN1YiI6IjY3MTk0NGZiOWZmNjgxZDllMGEzYTc4NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.m_tKOuyiipZLfEiS9HnjVStxnu9cWhJgmpjMe-hOSR8" // Tu token aquí

        RetrofitClient.instance.getPopularMovies(authToken).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    movieAdapter = MovieAdapter(movies)
                    recyclerView.adapter = movieAdapter

                    Log.d("Movies", "Número de películas: ${movies.size}")
                } else {
                    Log.e("Movies", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Movies", "Error: ${t.message}")
            }
        })
    }
}

