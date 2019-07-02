package com.geniusforapp.movies.ui.home.ktx

import androidx.appcompat.app.ActionBar
import com.arlib.floatingsearchview.FloatingSearchView
import com.geniusforapp.movies.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_similar_movie.view.*

fun ActionBar.updateTitleOnTabChange(current: Int = 0, tabLayout: TabLayout,
                                     onTabReselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                     onTabUnselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                     onTabSelected: ((tab: TabLayout.Tab) -> Unit)? = null) {
    if (current != 0) {
        setTitle(current)
    }
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {
            onTabReselected?.let { it(tab) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            onTabUnselected?.let { it(tab) }
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            title = tab.text
            onTabSelected?.let { it(tab) }
        }

    })
}


fun FloatingSearchView.updateHintOnTabChange(current: Int = 0, tabLayout: TabLayout,
                                             onTabReselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                             onTabUnselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                             onTabSelected: ((tab: TabLayout.Tab) -> Unit)? = null

) {
    if (current != 0) {
        setSearchHint(context.getString(R.string.hint_search, context.getString(current)))
    }
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {
            onTabReselected?.let { it(tab) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            onTabUnselected?.let { it(tab) }
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            setSearchHint(context.getString(R.string.hint_search, tab.text.toString()))
            onTabSelected?.let { it(tab) }
        }

    })
}