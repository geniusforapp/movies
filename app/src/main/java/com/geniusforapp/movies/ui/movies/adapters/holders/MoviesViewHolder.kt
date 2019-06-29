package com.geniusforapp.movies.ui.movies.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(item: MoviesResponse.Result?) {
        item?.let {
            initImages(it)
            initTexts(it)
        }

    }

    private fun initTexts(it: MoviesResponse.Result) {
        itemView.textTitle.text = it.title
        itemView.textDescription.text = it.overview
        itemView.ratingBar.rating = it.voteAverage.toFloat()
    }

    private fun initImages(it: MoviesResponse.Result) {
        Glide.with(itemView.context).load("${itemView.context.getString(R.string.image)}${it.posterPath}")
                .apply(RequestOptions()
                        .transforms(RoundedCorners(20))
                        .useAnimationPool(true)).into(itemView.imagePoster)
        Glide.with(itemView.context).load("${itemView.context.getString(R.string.image)}${it.backdropPath}").into(itemView.imageBackdrop)
    }
}