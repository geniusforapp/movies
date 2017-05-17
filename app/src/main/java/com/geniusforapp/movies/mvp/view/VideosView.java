package com.geniusforapp.movies.mvp.view;

import com.geniusforapp.movies.mvp.model.Video;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by anajar on 5/17/17.
 */

public interface VideosView extends BaseView<String> {

    void showVideos(Collection<Video> videos);
}
