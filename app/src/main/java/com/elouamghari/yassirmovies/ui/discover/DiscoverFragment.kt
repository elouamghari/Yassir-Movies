package com.elouamghari.yassirmovies.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elouamghari.yassirmovies.R
import com.elouamghari.yassirmovies.core.extensions.loadMore
import com.elouamghari.yassirmovies.databinding.FragmentDiscoverBinding
import com.elouamghari.yassirmovies.network.models.Movie
import com.elouamghari.yassirmovies.ui.details.DetailsFragment
import com.elouamghari.yassirmovies.ui.discover.adapters.MoviesAdapter
import com.elouamghari.yassirmovies.ui.discover.viewmodels.DiscoverViewModel


class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    private val adapter = MoviesAdapter(this::onMovieClicked)
    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProvider(requireActivity())[DiscoverViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.moviesRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.moviesRecyclerView.loadMore{
            viewModel.loadMore()
        }
        viewModel.movies.observe(viewLifecycleOwner, this::onMoviesLoaded)
    }

    private fun onMoviesLoaded(movies: List<Movie>) {
        val list: MutableList<Movie?> = movies.toMutableList()
        list.add(null)
        adapter.submitList(list)
    }

    private fun onMovieClicked(movie: Movie){
        val args = Bundle()
        args.putInt(DetailsFragment.MOVIE_ID, movie.id)
        Navigation.findNavController(requireActivity(), R.id.app_nav_host)
            .navigate(R.id.action_discoverFragment_to_detailsFragment, args)
    }

}