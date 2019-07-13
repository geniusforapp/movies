package com.geniusforapp.movies.ui.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {


    lateinit var activityContext: BaseActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context != null) {
            activityContext = context as BaseActivity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    protected abstract fun setContentView(): Int

    protected abstract fun bindView(view: View)

}
