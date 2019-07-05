package com.geniusforapp.movies.ui.home

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.geniusforapp.movies.R
import com.geniusforapp.movies.ui.base.BaseActivity
import com.geniusforapp.movies.ui.home.adapters.pagers.HomePagerAdapter
import com.geniusforapp.movies.ui.home.ktx.setupDrawerWithActionBar
import com.geniusforapp.movies.ui.home.ktx.setupTabsWithActionBar
import com.geniusforapp.movies.ui.movies.MoviesFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : BaseActivity() {
    private val pagerAdapter: HomePagerAdapter by lazy { HomePagerAdapter(this@HomeActivity, supportFragmentManager) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initPager()
        initToolbar()
        navigationActions()
    }

    private fun initToolbar() {
        setupTabsWithActionBar(supportActionBar, R.string.tab_title_now_playing, tabsHome, onTabReselected = ::onTabReSelected, onTabSelected = ::changeMenuItem)
        setupDrawerWithActionBar(drawerLayout, this, toolbar)
    }

    private fun onTabReSelected(tab: TabLayout.Tab) {
        MoviesFragment.publisher.onNext(true)
    }

    private fun changeMenuItem(tab: TabLayout.Tab) {
        when (tab.position) {
            HomePagerAdapter.ONE -> navigationView.menu.findItem(R.id.actionNowPlaying).isChecked = true
            HomePagerAdapter.TWO -> navigationView.menu.findItem(R.id.actionPopular).isChecked = true
            HomePagerAdapter.THREE -> navigationView.menu.findItem(R.id.actionUpComing).isChecked = true
            HomePagerAdapter.FOUR -> navigationView.menu.findItem(R.id.actionTopRated).isChecked = true
        }
    }

    private fun navigationActions() {
        navigationView.setNavigationItemSelectedListener { handleCategoriesActions(it) }
    }

    private fun handleCategoriesActions(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.actionNowPlaying -> homeViewPager.setCurrentItem(HomePagerAdapter.ONE, true)
            R.id.actionPopular -> homeViewPager.setCurrentItem(HomePagerAdapter.TWO, true)
            R.id.actionUpComing -> homeViewPager.setCurrentItem(HomePagerAdapter.THREE, true)
            R.id.actionTopRated -> homeViewPager.setCurrentItem(HomePagerAdapter.NUMBER_OF_ITEMS, true)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initPager() {
        homeViewPager.adapter = pagerAdapter
        homeViewPager.offscreenPageLimit = 4
        tabsHome.setupWithViewPager(homeViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                with(PreferenceManager.getDefaultSharedPreferences(this)) {
                    edit().putInt("theme", if (getInt("theme", R.style.AppTheme) == R.style.AppTheme) {
                        R.style.AppTheme_Light
                    } else {
                        R.style.AppTheme
                    }).apply()
                }
                recreate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}



