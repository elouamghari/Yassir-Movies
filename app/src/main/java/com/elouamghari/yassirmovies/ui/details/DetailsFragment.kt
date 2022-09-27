package com.elouamghari.yassirmovies.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elouamghari.yassirmovies.databinding.FragmentDetailsBinding
import com.elouamghari.yassirmovies.network.models.MovieDetails
import com.elouamghari.yassirmovies.ui.details.viewmodels.MovieDetailsViewModel

class DetailsFragment : Fragment() {

    companion object{
        const val MOVIE_ID = "com.elouamghari.yassirmovies.ui.details.MOVIE_ID"
    }

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.progressCircular.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val id = it.getInt(MOVIE_ID, 0)
            if (id > 0){
                viewModel.loadDetails(id)
            }
        }

        viewModel.movie.observe(viewLifecycleOwner, this::onMovieLoaded)
    }

    private fun onMovieLoaded(movie: MovieDetails){
        binding.progressCircular.visibility = View.GONE
        binding.movie = movie
    }

}