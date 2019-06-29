package com.geniusforapp.movies.ui.details.movie

import android.os.Bundle

import com.geniusforapp.movies.R
import com.geniusforapp.movies.ui.base.BaseActivity

import ooo.oxo.library.widget.PullBackLayout

class MovieActivity : BaseActivity(), PullBackLayout.Callback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }


    override fun onPullStart() {

    }

    override fun onPull(v: Float) {

    }

    override fun onPullCancel() {

    }

    override fun onPullComplete() {
        supportFinishAfterTransition()
    }


}

