package com.geniusforapp.movies.ui.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.movies.adapters.callback.MoviesItemCallback
import com.geniusforapp.movies.ui.movies.adapters.holders.MoviesViewHolder
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesAdapter @Inject constructor(moviesItemCallback: MoviesItemCallback) : PagedListAdapter<MoviesResponse.Result, MoviesViewHolder>(moviesItemCallback) {


    lateinit var onItemClick: ((itemView: View, result: MoviesResponse.Result) -> Unit)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindView(getItem(position), onItemClick)
    }
}