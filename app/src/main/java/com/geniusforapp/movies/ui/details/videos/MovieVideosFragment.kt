package com.geniusforapp.movies.ui.details.videos

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.ui.base.BaseFragment
import com.geniusforapp.movies.ui.details.videos.adapters.VideosAdapter
import com.thefinestartist.ytpa.utils.YouTubeApp
import com.thefinestartist.ytpa.utils.YouTubeUrlParser
import kotlinx.android.synthetic.main.fragment_movie_videos.*
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
class MovieVideosFragment : BaseFragment() {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var videosAdapter: VideosAdapter

    private val viewModel: MovieVideosViewModel by lazy { ViewModelProviders.of(this, factory)[MovieVideosViewModel::class.java] }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun newInstance(movieId: Int): MovieVideosFragment = MovieVideosFragment().apply { arguments = bundleOf(Pair(MOVIE_ID, movieId)) }
    }

    override fun setContentView(): Int {
        return R.layout.fragment_movie_videos
    }

    override fun bindView(view: View) {
        videosAdapter.onVideoClicked = { YouTubeApp.startVideo(activityContext, YouTubeUrlParser.getVideoId(YouTubeUrlParser.getVideoUrl(it.key))) }
        listVideos.adapter = videosAdapter
        arguments?.getInt(MOVIE_ID, 0)?.let { viewModel.getMovieVideos(it).observe(this, handleVideos()) }
    }

    private fun handleVideos(): Observer<in MovieVideos> {
        return Observer {
            videosAdapter.submitList(it.results)
        }
    }
}