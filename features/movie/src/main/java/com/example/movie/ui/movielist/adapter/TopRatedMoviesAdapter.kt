package com.example.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DiscoverMovie
import com.example.movie.databinding.ItemPopularLayoutBinding
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import com.example.mymoviecatalogue.utils.showImage

class TopRatedMoviesAdapter(
    private val diffUtilsConfig: DiffUtilsConfig = DiffUtilsConfig(),
    private val listener: MovieOnClickListener
) :
    RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMovieViewVH>() {

    private var popularMovies = mutableListOf<DiscoverMovie>()

    fun setMovies(movies: List<DiscoverMovie>) {
        calculateDiff(movies)
    }

    private fun calculateDiff(newData: List<DiscoverMovie>) {
        diffUtilsConfig.setList(popularMovies, newData)
        val result = DiffUtil.calculateDiff(diffUtilsConfig)
        with(popularMovies) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieViewVH {
        val itemBinding =
            ItemPopularLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedMovieViewVH(itemBinding)
    }

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: TopRatedMovieViewVH, position: Int) {
        val movies = popularMovies[position]
        holder.bind(movies, listener)
    }

    inner class TopRatedMovieViewVH(private val binding: ItemPopularLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DiscoverMovie, listener: MovieOnClickListener) {
            with(binding) {
                imgMovie.showImage(movie.posterPath.toString())
                itemView.setOnClickListener { movie.id?.let { id -> listener.onClick(id) } }
            }
        }
    }
}