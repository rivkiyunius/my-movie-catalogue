package com.example.favorite.ui.favoritelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FavoriteMovie
import com.example.favorite.databinding.ItemFavoriteMovieBinding
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import com.example.mymoviecatalogue.utils.dateFormat
import com.example.mymoviecatalogue.utils.showImage

class FavoriteMovieAdapter(
    private val diffUtilsConfig: DiffUtilsConfig = DiffUtilsConfig(),
    private val listener: MovieOnClickListener
) : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieVH>() {

    private var favoriteMovie = mutableListOf<FavoriteMovie>()

    fun setFavoriteMovie(favorite: List<FavoriteMovie>) {
        calculateDiff(favorite)
    }

    private fun calculateDiff(newData: List<FavoriteMovie>) {
        diffUtilsConfig.setList(favoriteMovie, newData)
        val result = DiffUtil.calculateDiff(diffUtilsConfig)
        with(favoriteMovie) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieVH {
        val itemBinding =
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieVH(itemBinding)
    }

    override fun getItemCount(): Int = favoriteMovie.size

    override fun onBindViewHolder(holder: FavoriteMovieVH, position: Int) {
        holder.bindItem(favoriteMovie[position], listener)
    }

    inner class FavoriteMovieVH(private val itemBinding: ItemFavoriteMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(favorite: FavoriteMovie, listener: MovieOnClickListener) {
            itemBinding.apply {
                imgFavorite.showImage(favorite.posterPath ?: "")
                tvTitleFavorite.text = favorite.title
                tvReleaseDate.text = favorite.releaseDate?.dateFormat("MMMM yyyy")
                itemView.setOnClickListener { listener.onClick(favorite.id ?: 0) }
            }
        }
    }
}