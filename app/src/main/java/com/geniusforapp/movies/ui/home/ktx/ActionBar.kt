package com.geniusforapp.movies.ui.home.ktx

import android.app.Activity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.geniusforapp.movies.R
import com.google.android.material.tabs.TabLayout

fun AppCompatActivity.setupTabsWithActionBar(actionBar: ActionBar?, current: Int = 0, tabLayout: TabLayout,
                                             onTabReselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                             onTabUnselected: ((tab: TabLayout.Tab) -> Unit)? = null,
                                             onTabSelected: ((tab: TabLayout.Tab) -> Unit)? = null) {
    if (current != 0) {
        actionBar?.setTitle(current)
    }
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {
            onTabReselected?.let { it(tab) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            onTabUnselected?.let { it(tab) }
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            actionBar?.title = tab.text
            onTabSelected?.let { it(tab) }
        }

    })
}


fun AppCompatActivity.setupDrawerWithActionBar(drawerLayout: DrawerLayout, activity: Activity, toolbar: Toolbar) {
    with(ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.text_open_drawer, R.string.text_close_drawer)) {
        drawerLayout.addDrawerListener(this)
        syncState()
    }
}