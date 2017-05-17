package com.geniusforapp.movies.ui.adapters.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Video;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.thefinestartist.ytpa.enums.Quality;
import com.thefinestartist.ytpa.utils.YouTubeThumbnail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by anajar on 5/17/17.
 */

public class ItemVideo extends AbstractItem<ItemVideo, ItemVideo.VideoViewHolder> {


    protected Video video;


    public Video getVideo() {
        return video;
    }

    public ItemVideo setVideo(Video video) {
        this.video = video;
        return this;
    }

    @Override
    public VideoViewHolder getViewHolder(View v) {
        return new VideoViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.item_video;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_video;
    }

    @Override
    public void bindView(VideoViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        Context context = holder.itemView.getContext();
        Glide.with(context).load(YouTubeThumbnail.getUrlFromVideoId(video.getKey(), Quality.HIGH)).bitmapTransform(new CropCircleTransformation(context)).into(holder.imageThumb);


    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_thumb)
        ImageView imageThumb;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
