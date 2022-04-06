package com.example.mymoviedb.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.FragmentMovieItemBinding
import com.example.mymoviedb.model.entities.Movie

const val POSTER_URL = "https://image.tmdb.org/t/p/original"

class MovieListAdapter(private val outsideInterface: MovieListFragment.OutsideInterface) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var movieList: List<Movie> = listOf()
    private lateinit var binding: FragmentMovieItemBinding

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movie_title)
        private val voteAverage: TextView = itemView.findViewById(R.id.movie_rating)
        private val posterPath: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Movie) = with(binding) {
            title.text = movie.title
            voteAverage.text = movie.voteAverage.toString()
            posterPath.load(POSTER_URL + movie.posterPath) {
                crossfade(750)
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.placeholder_error)
                scale(Scale.FIT)
            }
            root.setOnClickListener { outsideInterface.onItemViewClick(movie) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<Movie>) {
        movieList = data
        notifyDataSetChanged()
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = FragmentMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding.root)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return movieList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
        if (position == movieList.size - 3) {
            outsideInterface.onDataEnding()
        }
    }
}