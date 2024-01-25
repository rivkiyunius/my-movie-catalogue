package com.example.search.ui.listsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DiscoverMovie
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import com.example.mymoviecatalogue.utils.showImage
import com.example.search.databinding.ItemResultMovieLayoutBinding

class ResultMovieAdapter(
    private val diffUtil: DiffUtilsConfig = DiffUtilsConfig(),
    private val listener: MovieOnClickListener
) :
    RecyclerView.Adapter<ResultMovieAdapter.ResultMovieVH>() {

    private var resultMovies = mutableListOf<DiscoverMovie>()

    fun setMovies(movies: List<DiscoverMovie>) {
        calculateDiff(movies)
    }

    private fun calculateDiff(newData: List<DiscoverMovie>) {
        diffUtil.setList(resultMovies, newData)
        val result = DiffUtil.calculateDiff(diffUtil)
        with(resultMovies) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultMovieVH {
        val itemBinding =
            ItemResultMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultMovieVH(itemBinding)
    }

    override fun getItemCount(): Int = resultMovies.size

    override fun onBindViewHolder(holder: ResultMovieVH, position: Int) {
        val movies = resultMovies[position]
        holder.bind(movies, listener)
    }

    inner class ResultMovieVH(private val itemResultMovieLayoutBinding: ItemResultMovieLayoutBinding) :
        RecyclerView.ViewHolder(itemResultMovieLayoutBinding.root) {
        fun bind(movie: DiscoverMovie, listener: MovieOnClickListener) {
            itemResultMovieLayoutBinding.imgMovie.showImage(movie.posterPath ?: "")
            itemView.setOnClickListener {
                movie.id?.let { id -> listener.onClick(id) }
            }
        }
    }
}