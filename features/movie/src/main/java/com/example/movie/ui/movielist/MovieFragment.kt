package com.example.movie.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.di.DataDependencies
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.di.DaggerMovieFeatureComponent
import com.example.movie.ui.movielist.adapter.NowPlayingMoviesAdapter
import com.example.movie.ui.movielist.adapter.PopularMoviesAdapter
import com.example.movie.ui.movielist.adapter.TopRatedMoviesAdapter
import com.example.movie.ui.movielist.adapter.UpcomingMoviesAdapter
import com.example.mymoviecatalogue.MainActivity
import com.example.mymoviecatalogue.base.BaseFragment
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate),
    MovieOnClickListener {
    @Inject
    lateinit var diffUtil: DiffUtilsConfig

    private val vmMovie: MovieViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
    }

    private var popularMovieAdapter: PopularMoviesAdapter? = null
    private var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter? = null
    private var topRatedMoviesAdapter: TopRatedMoviesAdapter? = null
    private var upcomingMoviesAdapter: UpcomingMoviesAdapter? = null

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
        initRv()
        observeData()
        vmMovie.getPopularMovie()
        vmMovie.getNowPlayingMovie()
        vmMovie.getTopRatedMovie()
        vmMovie.getUpcomingMovie()
    }

    override fun onDestroyView() {
        nowPlayingMoviesAdapter = null
        popularMovieAdapter = null
        upcomingMoviesAdapter = null
        topRatedMoviesAdapter = null
        super.onDestroyView()
    }

    private fun observeData() {
        (activity as MainActivity).showBottomNav()
        vmMovie.apply {
            popularMovie.observe(viewLifecycleOwner) {
                popularMovieAdapter?.setMovies(it ?: mutableListOf())
            }
            nowPlayingMovie.observe(viewLifecycleOwner) {
                nowPlayingMoviesAdapter?.setMovies(it ?: mutableListOf())
            }
            topRatedMovie.observe(viewLifecycleOwner) {
                topRatedMoviesAdapter?.setMovies(it ?: mutableListOf())
            }
            upcomingMovie.observe(viewLifecycleOwner) {
                upcomingMoviesAdapter?.setMovies(it ?: mutableListOf())
            }
            isLoading.observe(viewLifecycleOwner) {
                viewBinding?.apply {
                    groupMovies.visibility = if (it) View.GONE else View.VISIBLE
                    progressCircular.visibility = if (it) View.VISIBLE else View.GONE
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

    private fun initRv() {
        viewBinding?.apply {
            popularMovieAdapter = PopularMoviesAdapter(diffUtil, this@MovieFragment)
            nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(diffUtil, this@MovieFragment)
            topRatedMoviesAdapter = TopRatedMoviesAdapter(diffUtil, this@MovieFragment)
            upcomingMoviesAdapter = UpcomingMoviesAdapter(diffUtil, this@MovieFragment)
            vpBanner.adapter = popularMovieAdapter

            rvNowPlaying.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvNowPlaying.adapter = nowPlayingMoviesAdapter

            rvTopRatedMovie.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvTopRatedMovie.adapter = topRatedMoviesAdapter

            rvUpcomingMovie.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvUpcomingMovie.adapter = upcomingMoviesAdapter
        }
    }

    override fun onClick(movieId: Int) {
        val action = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(movieId)
        findNavController().navigate(action)
    }
}