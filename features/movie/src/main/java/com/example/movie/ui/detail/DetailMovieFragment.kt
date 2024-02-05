package com.example.movie.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.di.DataDependencies
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.movie.di.DaggerMovieFeatureComponent
import com.example.mymoviecatalogue.MainActivity
import com.example.mymoviecatalogue.base.BaseFragment
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.databinding.FragmentDetailMovieBinding
import com.example.mymoviecatalogue.sharedview.starsadapter.StarsAdapter
import com.example.mymoviecatalogue.utils.dateFormat
import com.example.mymoviecatalogue.utils.showImage
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailMovieFragment :
    BaseFragment<FragmentDetailMovieBinding>(FragmentDetailMovieBinding::inflate) {

    @Inject
    lateinit var diffUtilsConfig: DiffUtilsConfig
    private val args: DetailMovieFragmentArgs by navArgs()
    private val vmDetail: DetailMovieViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailMovieViewModel::class.java]
    }
    private val starsAdapter: StarsAdapter by lazy {
        StarsAdapter(diffUtilsConfig)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMovieFeatureComponent
            .builder()
            .context(requireContext())
            .dataDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    DataDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideBottomNav()
        val id = args.movieId
        initView(id)
        observeData()
    }

    private fun initView(id: Int) {
        vmDetail.getDetailMovie(id)
        vmDetail.getCreditMovie(id)
        viewBinding?.apply {
            rvStars.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvStars.adapter = starsAdapter
        }
    }

    private fun observeData() {
        vmDetail.apply {
            isLoading.observe(viewLifecycleOwner) {
                viewBinding?.groupDetail?.visibility = if (it) View.GONE else View.VISIBLE
                viewBinding?.progressCircular?.visibility = if (it) View.VISIBLE else View.GONE
            }
            detailMovie.observe(viewLifecycleOwner) {
                viewBinding?.apply {
                    collapsingLayout.title = it?.title
                    expandedImage.showImage(it?.backdropPath ?: "")
                    tvSynopsis.text = it?.overview
                    tvGenre.text = it?.genres?.joinToString { it?.name ?: "" }
                    tvReleaseDate.text = it?.releaseDate?.dateFormat("MMMM yyyy")
                    changeIconFavorite(it?.favorite == true)
                }
            }
            creditMovie.observe(viewLifecycleOwner) {
                starsAdapter.setCreditsMovie(it ?: mutableListOf())
            }
            isFavorite.observe(viewLifecycleOwner) { isFavorite ->
                changeIconFavorite(isFavorite)
                viewBinding?.imgFavorite?.setOnClickListener {
                    if (isFavorite) {
                        vmDetail.deleteFavoriteMovie()
                    } else {
                        vmDetail.saveFavoriteMovie()
                    }
                }
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(
                    requireContext(),
                    getString(com.example.mymoviecatalogue.R.string.error_message, it),
                    Toast.LENGTH_SHORT
                ).show()
            }
            networkStatus.observe(viewLifecycleOwner) {
                if (it == ConnectivityObserver.Status.Lost.name) {
                    Toast.makeText(
                        requireContext(),
                        getString(com.example.mymoviecatalogue.R.string.error_network, it),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun changeIconFavorite(isFavorite: Boolean) {
        viewBinding?.apply {
            if (isFavorite) {
                imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        com.example.mymoviecatalogue.R.drawable.ic_favorite_movie_filled
                    )
                )
            } else {
                imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        com.example.mymoviecatalogue.R.drawable.ic_favorite_movie
                    )
                )
            }
        }
    }
}