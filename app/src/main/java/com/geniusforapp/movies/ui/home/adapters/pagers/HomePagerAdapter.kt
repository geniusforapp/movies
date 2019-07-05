package com.geniusforapp.movies.ui.home.adapters.pagers


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.ui.movies.MoviesFragment

class HomePagerAdapter(val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    companion object {
        const val ONE = 0
        const val TWO = 1
        const val THREE = 2
        const val FOUR = 3
        const val NUMBER_OF_ITEMS = 4
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            ONE -> MoviesFragment.newInstance(context.getString(R.string.key_now_playing))
            TWO -> MoviesFragment.newInstance(context.getString(R.string.key_popular))
            THREE -> MoviesFragment.newInstance(context.getString(R.string.key_upcoming))
            else -> MoviesFragment.newInstance(context.getString(R.string.key_top_rated))
        }
    }

    override fun getCount(): Int {
        return NUMBER_OF_ITEMS
    }


    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            ONE -> context.getString(R.string.tab_title_now_playing)
            TWO -> context.getString(R.string.tab_title_popular)
            THREE -> context.getString(R.string.tab_title_up_coming)
            else -> context.getString(R.string.tab_title_top_rated)
        }
    }


}
