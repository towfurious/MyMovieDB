package com.example.mymoviedb.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mymoviedb.R
import com.example.mymoviedb.appComponent
import com.example.mymoviedb.databinding.FragmentMoviesListBinding
import com.example.mymoviedb.model.entities.Movie
import com.example.mymoviedb.ui.description.DescriptionFragment
import javax.inject.Inject


class MovieListFragment : Fragment() {
    @Inject
    lateinit var listViewModel: MovieListViewModel
    private var _binding: FragmentMoviesListBinding? = null
    private var adapter: MovieListAdapter? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.getData()
        listViewModel.getLiveData().observe(viewLifecycleOwner) { setData(it) }
    }

    private fun setData(state: AppViewState) = with(binding) {
        when (state) {
            is AppViewState.Success -> {
                progressBar.visibility = View.GONE
                // Put data here
                adapter = MovieListAdapter(object : OutsideInterface {
                    override fun onItemViewClick(data: Movie) {
                        val bundle = Bundle().apply {
                            putString(DescriptionFragment.OVERVIEW, data.overview)
                            putString(DescriptionFragment.POSTER_PATH, data.posterPath)
                            putString(DescriptionFragment.TITLE, data.title)
                        }
                        findNavController().navigate(R.id.nav_desc, bundle)
                    }

                    override fun onDataEnding() {
                        listViewModel.getDataByPage(state.page)
                    }
                })
                    .apply { updateData(state.movieData) }
                moviesListRecyclerView.adapter = adapter
            }
            is AppViewState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is AppViewState.Error -> {
                progressBar.visibility = View.GONE
                val toast = Toast.makeText(context, R.string.toast_error, Toast.LENGTH_LONG)
                toast.show()
                listViewModel.getFromLocal()
            }
        }

    }

    interface OutsideInterface {
        fun onItemViewClick(data: Movie)
        fun onDataEnding()
    }
}

sealed class AppViewState {
    data class Success(val movieData: List<Movie>, val page: Int) : AppViewState()
    data class Error(val error: Throwable) : AppViewState()
    object Loading : AppViewState()
}