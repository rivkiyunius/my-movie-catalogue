package com.example.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DiscoverMovie
import com.example.movie.databinding.ItemBannerMovieBinding
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import com.example.mymoviecatalogue.utils.dateFormat
import com.example.mymoviecatalogue.utils.showImage

class PopularMoviesAdapter(
    private val diffUtilsConfig: DiffUtilsConfig = DiffUtilsConfig(),
    private val listener: MovieOnClickListener
) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieViewVH>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewVH {
        val itemBinding =
            ItemBannerMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieViewVH(itemBinding)
    }

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: PopularMovieViewVH, position: Int) {
        val movies = popularMovies[position]
        holder.bind(movies, listener)
    }

    inner class PopularMovieViewVH(private val binding: ItemBannerMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DiscoverMovie, listener: MovieOnClickListener) {
            with(binding) {
                imgBanner.showImage(movie.backdropPath.toString())
                tvName.text = movie.title
                tvYearAndGenre.text = movie.releaseDate?.dateFormat("yyyy")
                tvRating.text = movie.voteAverage.toString()
                itemView.setOnClickListener { movie.id?.let { id -> listener.onClick(id) } }
            }
        }
    }
}
