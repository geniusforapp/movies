package com.geniusforapp.movies.ui.details.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.ui.base.BaseActivity
import com.geniusforapp.movies.ui.details.movie.adapters.SimilarMoviesAdapter
import com.geniusforapp.movies.ui.details.movie.adapters.VideosAdapter
import com.geniusforapp.movies.ui.details.movie.vm.MovieViewModel
import com.geniusforapp.movies.ui.details.movie.vm.MovieViewModelFactory
import com.thefinestartist.ytpa.utils.YouTubeApp
import com.thefinestartist.ytpa.utils.YouTubeUrlParser
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*
import javax.inject.Inject
import kotlin.math.roundToInt


class MovieActivity : BaseActivity() {


    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory

    @Inject
    lateinit var videosAdapter: VideosAdapter

    @Inject
    lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    private val movieViewModel: MovieViewModel by lazy { ViewModelProviders.of(this, movieViewModelFactory)[MovieViewModel::class.java] }


    companion object {
        private const val MOVIE_ID = "movieId"
        fun showMovieActivity(context: Context, movieId: Int) {
            context.startActivity(getIntent(context, movieId))
        }

        private fun getIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieActivity::class.java)
                    .apply { putExtra(MOVIE_ID, movieId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        showBack()
        initList()
        initActions()
        getMovieDetails()
    }

    private fun initActions() {
        videosAdapter.onVideoClicked = { YouTubeApp.startVideo(this, YouTubeUrlParser.getVideoId(YouTubeUrlParser.getVideoUrl(it.key))) }
        similarMoviesAdapter.onItemClick = { itemView, result -> showMovieActivity(this, result.id) }
    }

    private fun initList() {
        with(listVideos) { adapter = videosAdapter }
        with(listRelatedMovies) { adapter = similarMoviesAdapter }
    }

    private fun getMovieDetails() {
        movieViewModel.getMovieDetails(intent.getIntExtra(MOVIE_ID, 0)).observe(this, getDetails())
        movieViewModel.getMovieVideos().observe(this, getVideos())
        movieViewModel.getSimilarMovies().observe(this, Observer { similarMoviesAdapter.submitList(it) })
        movieViewModel.getLoaderLiveData().observe(this, Observer { if (it) progressBar.show() else progressBar.hide() })
    }

    private fun getVideos(): Observer<MovieVideos> {
        return Observer { handleVideos(it.results) }
    }

    private fun handleVideos(results: List<MovieVideos.Result>) {
        videosAdapter.submitList(results)
    }

    private fun getDetails(): Observer<MovieDetails> {
        return Observer {
            handleTextData(it)
            handleImages(it)
            handleGenres(it)
        }
    }

    private fun handleGenres(details: MovieDetails) {
        textGenres.text = getString(R.string.text_genres, details.printGenres())
    }

    private fun handleImages(details: MovieDetails) {
        Glide.with(this)
                .load("${getString(R.string.image)}${details.posterPath}")
                .apply(RequestOptions()
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .transform(RoundedCorners(20))).into(imagePoster)


        Glide.with(this)
                .load("${getString(R.string.image)}${details.backdropPath}")
                .apply(RequestOptions()
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder))
                .into(imageBackdrop)

    }

    private fun handleTextData(details: MovieDetails) {
        title = details.title
        textTitle.text = details.originalTitle
        textDescription.text = details.overview
        ratingBar.rating = details.voteAverage.toFloat()
        textAvgRating.text = getString(R.string.text_avg_rating, details.voteAverage.roundToInt())
        textReleaseDate.text = getString(R.string.text_release_date, details.releaseDate)
    }
}

