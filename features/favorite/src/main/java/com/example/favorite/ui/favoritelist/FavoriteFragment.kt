package com.example.favorite.ui.favoritelist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.di.DataDependencies
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteFeatureComponent
import com.example.favorite.ui.favoritelist.adapter.FavoriteMovieAdapter
import com.example.mymoviecatalogue.MainActivity
import com.example.mymoviecatalogue.base.BaseFragment
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate),
    MovieOnClickListener {
    @Inject
    lateinit var diffUtilsConfig: DiffUtilsConfig
    private var favoriteAdapter: FavoriteMovieAdapter? = null
    private val vmFavorite: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteFeatureComponent
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
        initView()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNav()
    }

    override fun onClick(movieId: Int) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFavoriteMovieFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        favoriteAdapter = null
        super.onDestroyView()
    }

    private fun initView() {
        vmFavorite.getFavoriteMovie()
        viewBinding?.apply {
            favoriteAdapter = FavoriteMovieAdapter(diffUtilsConfig, this@FavoriteFragment)
            rvFavorite.layoutManager = LinearLayoutManager(requireContext())
            rvFavorite.adapter = favoriteAdapter
        }
    }

    private fun observeData() {
        vmFavorite.apply {
            favoriteMovies.observe(viewLifecycleOwner) {
                favoriteAdapter?.setFavoriteMovie(it)
            }
        }
    }
}