package com.geniusforapp.movies.ui.details.similar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.details.similar.adapters.holders.SimilarMoviesViewHolder
import com.geniusforapp.movies.ui.movies.adapters.callback.MoviesItemCallback
import javax.inject.Inject

class SimilarMoviesAdapter @Inject constructor(moviesItemCallback: MoviesItemCallback) : PagedListAdapter<MoviesResponse.Result, SimilarMoviesViewHolder>(moviesItemCallback) {


    lateinit var onItemClick: ((itemView: View, result: MoviesResponse.Result) -> Unit)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_similar_movie, parent, false))
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        holder.bindView(getItem(position), onItemClick)
    }
}