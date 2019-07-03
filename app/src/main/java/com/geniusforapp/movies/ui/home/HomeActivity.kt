package com.geniusforapp.movies.ui.home

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import com.geniusforapp.movies.R
import com.geniusforapp.movies.ui.base.BaseActivity
import com.geniusforapp.movies.ui.home.adapters.pagers.HomePagerAdapter
import com.geniusforapp.movies.ui.home.ktx.updateTitleOnTabChange
import com.geniusforapp.movies.ui.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : BaseActivity() {
    private val pagerAdapter: HomePagerAdapter by lazy { HomePagerAdapter(this@HomeActivity, supportFragmentManager) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initPager()
        supportActionBar?.updateTitleOnTabChange(R.string.tab_title_now_playing, tabsHome, onTabReselected = { MoviesFragment.publisher.onNext(true) })
        //floatingSearchView.updateHintOnTabChange(R.string.tab_title_now_playing, tabsHome, onTabReselected = { MoviesFragment.publisher.onNext(true) })
    }

    private fun initPager() {
        with(homeViewPager) {
            adapter = pagerAdapter
            offscreenPageLimit = 4
        }
        with(tabsHome) { setupWithViewPager(homeViewPager) }
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                recreate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}



