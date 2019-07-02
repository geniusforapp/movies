package com.geniusforapp.movies.applications.helpers

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils


/**
 * Created by anajar on 5/16/17.
 */

object AnimationsHelper {


    fun startAnimation(parent: View, myView: View) {
        val cx = parent.measuredWidth / 2
        val cy = parent.measuredHeight / 2
        // get the final radius for the clipping circle
        val finalRadius = Math.hypot(parent.width.toDouble(), parent.height.toDouble()).toInt()
        // create the animator for this view (the start radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius.toFloat())
        // make the view visible and start the animation
        myView.visibility = View.VISIBLE
        anim.start()
    }

    fun endAnimation(parent: View, myView: View) {

        // get the center for the clipping circle
        val cx = parent.measuredWidth / 2
        val cy = parent.measuredHeight / 2

        // get the initial radius for the clipping circle
        val initialRadius = parent.width / 2

        // create the animation (the final radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius.toFloat(), 0f)

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                myView.visibility = View.INVISIBLE
            }
        })
        // start the animation
        anim.start()
    }
}
