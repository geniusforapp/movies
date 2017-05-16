package com.geniusforapp.movies.ui.activites;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.geniusforapp.movies.R;
import com.geniusforapp.movies.ui.adapters.pagers.PagerAdapter;
import com.geniusforapp.movies.ui.fragemnts.MoviesFragment;

import java.util.ArrayList;

import butterknife.BindView;

import static com.geniusforapp.movies.ui.adapters.pagers.PagerAdapter.PagerItem;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.home)
    ViewPager home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        addPages(pagerAdapter);
        home.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(home);


    }


    private void addPages(PagerAdapter pagerAdapter) {
        ArrayList<PagerItem> list = new ArrayList<>();
        list.add(new PagerItem(getString(R.string.tab_title_now_playing), MoviesFragment.newInstance(getString(R.string.key_now_playing))));
        list.add(new PagerItem(getString(R.string.tab_title_up_comeing), MoviesFragment.newInstance(getString(R.string.key_upcoming))));
        list.add(new PagerItem(getString(R.string.tab_title_popular), MoviesFragment.newInstance(getString(R.string.key_popular))));
        list.add(new PagerItem(getString(R.string.tab_title_top_rated), MoviesFragment.newInstance(getString(R.string.key_top_rated))));

        pagerAdapter.addPages(list);
    }


}



