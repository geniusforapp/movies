package com.geniusforapp.movies.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.ui.views.PileLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by anajar on 5/17/17.
 */

public class PileAdapter extends PileLayout.Adapter {
    @BindView(R.id.image_poster)
    ImageView imagePoster;
    @BindView(R.id.title)
    TextView title;

    List<Movie> list = new ArrayList<>();

    public PileAdapter(Collection<Movie> newMovies) {
        list.clear();
        list.addAll(newMovies);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_related_movie;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void bindView(View view, int index) {
        super.bindView(view, index);
        ButterKnife.bind(this, view);

        Movie movie = list.get(index);
        Context context = view.getContext();
        Glide.with(context).load(context.getString(R.string.image) + movie.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).bitmapTransform(new BlurTransformation(context, 1)).into(imagePoster);
        title.setText(movie.getOriginalTitle());
    }


}
