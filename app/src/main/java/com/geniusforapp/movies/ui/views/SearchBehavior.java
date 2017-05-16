package com.geniusforapp.movies.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.arlib.floatingsearchview.FloatingSearchView;


// https://lab.getbase.com/nested-scrolling-with-coordinatorlayout-on-android/
public class SearchBehavior extends CoordinatorLayout.Behavior<FloatingSearchView> {

    private AppBarLayout mAppBarLayout;
    private AppBarLayout.Behavior mAppBarLayoutBehavior;
    private ValueAnimator mValueAnimator;
    private FloatingSearchView mFloatingSearchView;
    private boolean isScrolling;

    public SearchBehavior() {
        super();
    }

    public SearchBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingSearchView child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            mFloatingSearchView = child;
            mAppBarLayout = (AppBarLayout) dependency;
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                /*StateListAnimator stateListAnimator = new StateListAnimator();
                stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(dependency, "elevation", 4));
                mAppBarLayout.setStateListAnimator(stateListAnimator);*/
                // A bug that makes the floating search view disappear
                mAppBarLayout.setStateListAnimator(null);
                // TODO no shadow
            }
            mAppBarLayoutBehavior = (AppBarLayout.Behavior) params.getBehavior();
            return true;
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingSearchView child, View dependency) {
        /*if (needsToAdjustSearchBar()) {
            float offset = getMinExpandHeight() + appBarBehavior.getTopAndBottomOffset();
            child.setY(offset);
            return true;
        }
        return super.onDependentViewChanged(parent, child, dependency);*/
        // boolean returnValue = super.onDependentViewChanged(parent, FloatingSearchView, appbarLayout);
        if (dependency instanceof AppBarLayout) {
            mFloatingSearchView.setTranslationY(dependency.getY());
            return true;
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout parent, FloatingSearchView child, View target, int dx, int dy, int[] consumed) {
        if (dy >= 0 || dy > -10 || isScrolling) {
            return;
        }
        isScrolling = true;
        if (needsToAdjustSearchBar() && !isRunningAnimation()) {
            int offset = getMinExpandHeight();
            getValueAnimator(parent, child, -offset).start();
        }

        /*public void setAppBarElevation(final boolean visible) {
            if (appBar != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appBar.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this, (visible) ? R.animator.appbar_elevated : R.animator.appbar_not_elevated));
            }
        }*/
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingSearchView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingSearchView child, View target) {
        this.isScrolling = false;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingSearchView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    private int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            return 0;
        }
        int result = 0;
        int resourceId = mFloatingSearchView.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mFloatingSearchView.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private ValueAnimator getValueAnimator(CoordinatorLayout parent, FloatingSearchView FloatingSearchView, int offset) {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofInt();
        } else if (mValueAnimator.isRunning()) {
            return mValueAnimator;
        }
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        // valueAnimator.addUpdateListener(animation -> appBarBehavior.setHeaderTopBottomOffset(parent, appBarLayout,(int) animation.getAnimatedValue()));
        mValueAnimator.setIntValues(mAppBarLayoutBehavior.getTopAndBottomOffset(), offset);
        return mValueAnimator;
    }

    private boolean isRunningAnimation() {
        return mValueAnimator != null && mValueAnimator.isRunning();
    }

    private boolean needsToAdjustSearchBar() {
        float y = Math.abs(mAppBarLayoutBehavior.getTopAndBottomOffset());
        return y > getMinExpandHeight();
    }

    private int getMinExpandHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return mAppBarLayout.getTotalScrollRange() - mFloatingSearchView.getMinimumHeight() - (getStatusBarHeight() / 2);
        } else {
            return mAppBarLayout.getTotalScrollRange() - mFloatingSearchView.getHeight() - (getStatusBarHeight() / 2);
        }
    }

}