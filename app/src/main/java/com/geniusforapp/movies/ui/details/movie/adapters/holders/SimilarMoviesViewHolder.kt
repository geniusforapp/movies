package com.geniusforapp.movies.ui.details.movie.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import kotlinx.android.synthetic.main.item_similar_movie.view.*


class SimilarMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: MoviesResponse.Result?, onItemClick: (itemView: View, MoviesResponse.Result) -> Unit) {
        item?.let { result ->
            initImages(result)
            itemView.setOnClickListener { onItemClick(itemView, result) }
        }

    }

    private fun initImages(result: MoviesResponse.Result) {
        Glide.with(itemView.context).load("${itemView.context.getString(R.string.image)}${result.backdropPath}")
                .apply(RequestOptions()
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder))
                .into(itemView.imageBackdrop)
    }
}