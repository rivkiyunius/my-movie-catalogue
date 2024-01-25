package com.example.search.ui.listsearch

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.di.DataDependencies
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.mymoviecatalogue.MainActivity
import com.example.mymoviecatalogue.base.BaseFragment
import com.example.mymoviecatalogue.base.DiffUtilsConfig
import com.example.mymoviecatalogue.base.MovieOnClickListener
import com.example.search.databinding.FragmentSearchMovieBinding
import com.example.search.di.DaggerSearchMovieFeatureComponent
import com.example.search.ui.listsearch.adapter.ResultMovieAdapter
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate),
    MovieOnClickListener {
    @Inject
    lateinit var diffUtilsConfig: DiffUtilsConfig

    private var resultAdapter: ResultMovieAdapter? = null

    private val vmSearch: SearchMovieViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchMovieViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSearchMovieFeatureComponent
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

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initView() {
        viewBinding?.apply {
            resultAdapter = ResultMovieAdapter(diffUtilsConfig, this@SearchMovieFragment)
            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    lifecycleScope.launch {
                        vmSearch.queryChannel.value = p0.toString()
                    }
                }
            })
            rvResult.layoutManager = GridLayoutManager(requireContext(), 3)
            rvResult.adapter = resultAdapter
        }
    }

    private fun observeData() {
        vmSearch.apply {
            searchResult.observe(viewLifecycleOwner) {}
            searchMovie.observe(viewLifecycleOwner) {
                resultAdapter?.setMovies(it ?: mutableListOf())
            }
            isLoading.observe(viewLifecycleOwner) {
                viewBinding?.apply {
                    groupEmptyData.visibility = View.GONE
                    rvResult.visibility = if (it) View.GONE else View.VISIBLE
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

    override fun onClick(movieId: Int) {
        val action =
            SearchMovieFragmentDirections.actionSearchMovieFragmentToFragmentDetailSearch(movieId)
        findNavController().navigate(action)
    }
}