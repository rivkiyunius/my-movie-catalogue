package com.example.mymoviecatalogue.sharedview.starsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CreditsMovie
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.databinding.LayoutItemStarsBinding
import com.example.mymoviecatalogue.utils.showImage

class StarsAdapter(private val diffUtilsConfig: DiffUtilsConfig = DiffUtilsConfig()) :
    RecyclerView.Adapter<StarsAdapter.StarsVH>() {

    private var stars = mutableListOf<CreditsMovie>()

    fun setCreditsMovie(credits: List<CreditsMovie>) {
        calculateDiff(credits)
    }

    private fun calculateDiff(newData: List<CreditsMovie>) {
        diffUtilsConfig.setList(stars, newData)
        val result = DiffUtil.calculateDiff(diffUtilsConfig)
        with(stars) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarsVH {
        val itemBinding =
            LayoutItemStarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StarsVH(itemBinding)
    }

    override fun getItemCount(): Int = stars.size

    override fun onBindViewHolder(holder: StarsVH, position: Int) {
        holder.bindingView(stars[position])
    }

    inner class StarsVH(private val itemBinding: LayoutItemStarsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindingView(star: CreditsMovie) {
            itemBinding.apply {
                star.profilePath?.let { imgMovie.showImage(it) }
                tvCharacter.text = star.character
                tvName.text = star.originalName
            }
        }
    }
}