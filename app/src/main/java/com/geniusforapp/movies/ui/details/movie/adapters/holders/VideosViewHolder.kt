package com.geniusforapp.movies.ui.details.movie.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieVideos
import kotlinx.android.synthetic.main.item_video.view.*

class VideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindView(movieVideos: MovieVideos.Result?, onVideoClicked: (movieVideos: MovieVideos.Result) -> Unit) {
        movieVideos?.let {
            loadImages(it)
            itemView.setOnClickListener { onVideoClicked(movieVideos) }
        }


    }

    private fun loadImages(movieVideos: MovieVideos.Result) {
        Glide.with(itemView.context)
                .load("https://img.youtube.com/vi/${movieVideos.key}/maxresdefault.jpg")
                .apply(RequestOptions()
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .transform(CircleCrop(), CenterCrop())).into(itemView.imageVideo)
    }
}