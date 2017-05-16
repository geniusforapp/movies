package com.geniusforapp.movies.ui.activites;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.geniusforapp.movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anajar on 5/14/17.
 */

public class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    public void setContentViewWithoutInjections(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }


    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolBar();
    }

    protected void setupToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void showBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
