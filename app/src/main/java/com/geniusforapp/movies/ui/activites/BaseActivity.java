package com.geniusforapp.movies.ui.activites;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.geniusforapp.movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(@StringRes int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
