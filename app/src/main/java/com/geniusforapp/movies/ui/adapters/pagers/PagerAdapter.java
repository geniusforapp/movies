package com.geniusforapp.movies.ui.adapters.pagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by anajar on 5/15/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    List<PagerItem> list = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public PagerAdapter addPage(PagerItem pagerItem) {
        list.add(pagerItem);
        return this;
    }

    public PagerAdapter addPages(Collection<PagerItem> pagerItems) {
        list.clear();
        list.addAll(pagerItems);
        notifyDataSetChanged();
        return this;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }

    public static class PagerItem {
        private String title;
        private Fragment fragment;

        public PagerItem(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }

}
