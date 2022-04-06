package com.example.mymoviedb.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.size.Scale
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.LayoutDescriptionBinding
import com.example.mymoviedb.ui.list.POSTER_URL

class DescriptionFragment : Fragment() {

    private var _binding: LayoutDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        imageView.load(POSTER_URL + requireArguments().getString(POSTER_PATH)) {
            crossfade(750)
            placeholder(R.drawable.placeholder_image)
            error(R.drawable.placeholder_error)
            scale(Scale.FILL)
        }
        movieTitle.text = requireArguments().getString(TITLE)
        movieDescription.text = requireArguments().getString(OVERVIEW)
    }

    companion object {
        const val OVERVIEW = "overview"
        const val POSTER_PATH = "poster_path"
        const val TITLE = "title"

        fun newInstance(bundle: Bundle): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}