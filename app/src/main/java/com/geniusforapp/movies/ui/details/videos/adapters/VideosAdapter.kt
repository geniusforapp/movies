package com.geniusforapp.movies.ui.details.videos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.ui.details.videos.adapters.callback.VideosItemCallback
import com.geniusforapp.movies.ui.details.videos.adapters.holders.VideosViewHolder
import javax.inject.Inject

class VideosAdapter @Inject constructor(videosDiffUtil: VideosItemCallback) : ListAdapter<MovieVideos.Result, VideosViewHolder>(videosDiffUtil) {

    lateinit var onVideoClicked: (movieVideos: MovieVideos.Result) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        return VideosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false))
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bindView(getItem(position), onVideoClicked)
    }
}