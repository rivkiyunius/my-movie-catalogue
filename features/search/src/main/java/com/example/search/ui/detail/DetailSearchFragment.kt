package com.example.search.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.di.DataDependencies
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.mymoviecatalogue.MainActivity
import com.example.mymoviecatalogue.base.BaseFragment
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.databinding.FragmentDetailMovieBinding
import com.example.mymoviecatalogue.sharedview.starsadapter.StarsAdapter
import com.example.mymoviecatalogue.utils.dateFormat
import com.example.mymoviecatalogue.utils.showImage
import com.example.search.di.DaggerSearchMovieFeatureComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailSearchFragment :
    BaseFragment<FragmentDetailMovieBinding>(FragmentDetailMovieBinding::inflate) {
    @Inject
    lateinit var diffUtilsConfig: DiffUtilsConfig
    private val args: DetailSearchFragmentArgs by navArgs()
    private val vmDetail: DetailMovieViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailMovieViewModel::class.java]
    }
    private val starsAdapter: StarsAdapter by lazy {
        StarsAdapter(diffUtilsConfig)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSearchMovieFeatureComponent
            .builder()
            .context(requireContext())
            .dataDependencies(
                EntryPointAccessors.fromApplication(requireContext(), DataDependencies::class.java)
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideBottomNav()
        val id = args.movieId
        setupMenu()
        initView(id)
        observeData()
    }

    private fun initView(id: Int) {
        vmDetail.getDetailMovie(id)
        vmDetail.getCreditMovie(id)
        viewBinding?.apply {
            toolbar.setOnMenuItemClickListener { menu ->
                if (menu.itemId == com.example.mymoviecatalogue.R.id.save_menu) {
                    vmDetail.saveFavoriteMovie()
                    true
                } else {
                    false
                }
            }
            rvStars.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvStars.adapter = starsAdapter
        }
    }

    private fun observeData() {
        vmDetail.apply {
            detailMovie.observe(viewLifecycleOwner) {
                viewBinding?.apply {
                    collapsingLayout.title = it?.title
                    expandedImage.showImage(it?.backdropPath ?: "")
                    tvSynopsis.text = it?.overview
                    tvGenre.text = it?.genres?.joinToString { it?.name ?: "" }
                    tvReleaseDate.text = it?.releaseDate?.dateFormat("MMMM yyyy")
                }
            }
            creditMovie.observe(viewLifecycleOwner) {
                starsAdapter.setCreditsMovie(it ?: mutableListOf())
            }
            isLoading.observe(viewLifecycleOwner) {
                viewBinding?.groupDetail?.visibility = if (it) View.GONE else View.VISIBLE
                viewBinding?.progressCircular?.visibility = if (it) View.VISIBLE else View.GONE
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), getString(com.example.mymoviecatalogue.R.string.error_message, it), Toast.LENGTH_SHORT).show()
            }
            networkStatus.observe(viewLifecycleOwner) {
                if (it == ConnectivityObserver.Status.Lost.name) {
                    Toast.makeText(requireContext(), getString(com.example.mymoviecatalogue.R.string.error_network, it), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(com.example.mymoviecatalogue.R.menu.menu_save, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = true

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}