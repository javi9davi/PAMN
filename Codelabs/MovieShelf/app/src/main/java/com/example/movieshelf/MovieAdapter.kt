package com.example.movieshelf

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshelf.models.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.moviePoster)
        val title: TextView = itemView.findViewById(R.id.movieTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.title

        // Carga la imagen del póster
        val posterUrl = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Log.d("MovieAdapter", "Title: ${holder.title.text}, Poster URL: ${movie.posterPath}")
        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .error(R.drawable.error_image)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
