package com.geniusforapp.movies.applications.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;


import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by anajar on 5/16/17.
 */

public class AnimationsHelper {


    public static void startAnimation(View parent, View myView) {
        int cx = parent.getMeasuredWidth() / 2;
        int cy = parent.getMeasuredHeight() / 2;
        // get the final radius for the clipping circle
        int finalRadius = (int) Math.hypot(parent.getWidth(), parent.getHeight());
        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    public static void endAnimation(View parent, final View myView) {

        // get the center for the clipping circle
        int cx = parent.getMeasuredWidth() / 2;
        int cy = parent.getMeasuredHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = parent.getWidth() / 2;

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });
        // start the animation
        anim.start();
    }
}
