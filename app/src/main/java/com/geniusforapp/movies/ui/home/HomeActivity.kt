package com.geniusforapp.movies.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.geniusforapp.movies.R
import com.geniusforapp.movies.ui.base.BaseActivity
import com.geniusforapp.movies.ui.home.adapters.pagers.HomePagerAdapter
import com.geniusforapp.movies.ui.home.ktx.updateHintOnTabChange
import com.geniusforapp.movies.ui.home.ktx.updateTitleOnTabChange
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        with(homeViewPager) {
            adapter = HomePagerAdapter(this@HomeActivity, supportFragmentManager)
        }

        with(tabsHome) {
            setupWithViewPager(homeViewPager)
        }

        supportActionBar?.updateTitleOnTabChange(R.string.tab_title_now_playing, tabsHome)
        floatingSearchView.updateHintOnTabChange(R.string.tab_title_now_playing, tabsHome)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}



