package com.geniusforapp.movies.ui.details.movie.adapters.callback

import androidx.recyclerview.widget.DiffUtil
import com.geniusforapp.movies.shared.data.model.MovieVideos
import javax.inject.Inject

class VideosItemCallback @Inject constructor() : DiffUtil.ItemCallback<MovieVideos.Result>() {

    override fun areItemsTheSame(oldItem: MovieVideos.Result, newItem: MovieVideos.Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieVideos.Result, newItem: MovieVideos.Result): Boolean {
        return oldItem == newItem
    }
}