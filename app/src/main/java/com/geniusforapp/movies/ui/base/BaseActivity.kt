package com.geniusforapp.movies.ui.base

import android.view.MenuItem
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by anajar on 5/14/17.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {


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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
