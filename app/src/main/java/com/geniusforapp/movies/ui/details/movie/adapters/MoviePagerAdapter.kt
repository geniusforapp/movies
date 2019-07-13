package com.geniusforapp.movies.ui.details.movie.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.ui.details.about.MovieAboutFragment
import com.geniusforapp.movies.ui.details.company.CompanyFragment
import com.geniusforapp.movies.ui.details.similar.SimilarMoviesFragment
import com.geniusforapp.movies.ui.details.videos.MovieVideosFragment

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
class MoviePagerAdapter(private val context: Context, private val movieDetails: MovieDetails, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            ABOUT -> MovieAboutFragment.newInstance(movieDetails)
            VIDEOS -> MovieVideosFragment.newInstance(movieDetails.id)
            SIMILAR -> SimilarMoviesFragment.newInstance(movieDetails.id)
            else -> CompanyFragment.newInstance(movieDetails.productionCompanies)
        }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            ABOUT -> context.getString(R.string.tab_title_about)
            VIDEOS -> context.getString(R.string.tab_title_videos)
            SIMILAR -> context.getString(R.string.tab_title_similar)
            else -> context.getString(R.string.tab_title_companies)
        }
    }

    companion object {
        const val ABOUT = 0
        const val VIDEOS = 1
        const val SIMILAR = 2
    }
}