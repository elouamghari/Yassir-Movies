package com.elouamghari.yassirmovies.ui.discover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elouamghari.yassirmovies.R
import com.elouamghari.yassirmovies.core.listeners.OnItemClickListener
import com.elouamghari.yassirmovies.databinding.ListItemMovieBinding
import com.elouamghari.yassirmovies.network.models.Movie

class MoviesAdapter(
    val itemClickListener: OnItemClickListener<Movie>
) : ListAdapter<Movie?, RecyclerView.ViewHolder>(MovieItemComparator()) {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding: ListItemMovieBinding = ListItemMovieBinding.bind(itemView)

        init {
            binding.movieCard.setOnClickListener {
                itemClickListener.onItemClick(getItem(layoutPosition)!!)
            }
        }

        fun bind(movie: Movie){
            binding.movie = movie
        }
    }

    inner class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class MovieItemComparator : DiffUtil.ItemCallback<Movie?>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if(viewType == VIEW_TYPE_ITEM){
            MovieViewHolder(inflater.inflate(R.layout.list_item_movie, parent, false))
        } else{
            LoadingViewHolder(inflater.inflate(R.layout.list_item_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder){
            holder.bind(getItem(position)!!)
        }
    }
}