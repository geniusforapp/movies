package com.geniusforapp.movies.ui.details.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie.*
import androidx.core.util.Pair as UtilPair

class MovieActivity : BaseActivity() {


    companion object {
        fun showMovieActivity(activity: Activity, movie: MoviesResponse.Result, imageBackdrop: ImageView, imagePoster: ImageView) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    UtilPair.create<View, String>(imageBackdrop, imageBackdrop.transitionName),
                    UtilPair.create<View, String>(imagePoster, imagePoster.transitionName)).toBundle()

            activity.startActivity(Intent(activity, MovieActivity::class.java)
                    .apply { putExtra(MoviesResponse.Result::class.java.simpleName, movie) }, options)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        showBack()
        val movie = intent.getSerializableExtra(MoviesResponse.Result::class.java.simpleName) as MoviesResponse.Result
        collapsingToolbar.title = movie.title
        Glide.with(this).load("${getString(R.string.image)}${movie.posterPath}")
                .apply(RequestOptions()
                        .transforms(RoundedCorners(20))
                        .useAnimationPool(true)).into(imagePoster)


        Glide.with(this).load("${getString(R.string.image)}${movie.backdropPath}").into(imageBackdrop)
    }


    override fun onBackPressed() {
        supportFinishAfterTransition()
    }
}

