package com.geniusforapp.movies.ui.details.about

import android.view.View
import androidx.core.os.bundleOf
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about_movie.*

/**
 * @name movies
 * Copyrights (c) 2019-07-06 Created By Ahmad Najar
 **/
class MovieAboutFragment : BaseFragment() {


    companion object {
        fun newInstance(movieDetails: MovieDetails): MovieAboutFragment {
            return MovieAboutFragment().apply { arguments = bundleOf(Pair(MovieDetails::class.java.simpleName, movieDetails)) }
        }
    }

    override fun setContentView(): Int = R.layout.fragment_about_movie

    override fun bindView(view: View) {
        val movieDetails: MovieDetails? = arguments?.getParcelable<MovieDetails>(MovieDetails::class.java.simpleName)

        movieDetails?.let {
            textTitle.text = it.originalTitle
            textDescription.text = it.overview
            textGenres.text = getString(R.string.text_genres, it.printGenres())
        }
    }
}