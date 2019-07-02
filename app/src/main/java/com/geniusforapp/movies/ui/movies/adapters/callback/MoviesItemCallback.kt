package com.geniusforapp.movies.ui.movies.adapters.callback

import androidx.recyclerview.widget.DiffUtil
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import javax.inject.Inject

class MoviesItemCallback @Inject constructor() : DiffUtil.ItemCallback<MoviesResponse.Result>() {


    override fun areItemsTheSame(oldItem: MoviesResponse.Result, newItem: MoviesResponse.Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviesResponse.Result, newItem: MoviesResponse.Result): Boolean {
        return oldItem == newItem
    }
}