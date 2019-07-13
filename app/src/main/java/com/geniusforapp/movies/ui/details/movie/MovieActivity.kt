package com.geniusforapp.movies.ui.details.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.ui.base.BaseActivity
import com.geniusforapp.movies.ui.details.movie.adapters.MoviePagerAdapter
import com.geniusforapp.movies.ui.details.movie.vm.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*
import javax.inject.Inject
import kotlin.math.roundToInt


class MovieActivity : BaseActivity() {

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


    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private val movieViewModel: MovieViewModel by lazy { ViewModelProviders.of(this, viewModeFactory)[MovieViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        showBack()
        getMovieDetails()
    }

    private fun getMovieDetails() {
        movieViewModel.getMovieDetails(intent.getIntExtra(MOVIE_ID, 0)).observe(this, getDetails())
        movieViewModel.getLoaderLiveData().observe(this, Observer { })
        movieViewModel.getErrorLiveData().observe(this, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_logo)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(it?.message).show()
    }

    private fun getDetails(): Observer<MovieDetails> {
        return Observer {
            handleTextData(it)
            handleImages(it)
            handlePager(it)
        }
    }

    private fun handlePager(details: MovieDetails) {
        val adapter = MoviePagerAdapter(this, details, supportFragmentManager)
        movieViewPager.adapter = adapter
        movieViewPager.offscreenPageLimit = 4
        tabsMovie.setupWithViewPager(movieViewPager)
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
        ratingBar.rating = details.voteAverage.toFloat()
        textAvgRating.text = getString(R.string.text_avg_rating, details.voteAverage.roundToInt())
        textReleaseDate.text = getString(R.string.text_release_date, details.releaseDate)
    }
}

