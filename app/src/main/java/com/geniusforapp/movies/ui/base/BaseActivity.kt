package com.geniusforapp.movies.ui.base

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.geniusforapp.movies.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by anajar on 5/14/17.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    private var isBackArrowEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(this).getInt("theme", R.style.AppTheme))
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        bindViews()
    }

    private fun bindViews() {
        setupToolBar()
    }

    private fun setupToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

    fun showBack() {
        isBackArrowEnabled = true
        supportActionBar?.setDisplayHomeAsUpEnabled(isBackArrowEnabled)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                if (isBackArrowEnabled) {
                    onBackPressed()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
