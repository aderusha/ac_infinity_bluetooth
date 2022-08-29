package com.zhihu.matisse.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

public class GlideEngine implements ImageEngine {
    public boolean supportAnimatedGif() {
        return true;
    }

    public void loadThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        Glide.with(context).asBitmap().load(uri).apply(new RequestOptions().override(i, i).placeholder(drawable).centerCrop()).into(imageView);
    }

    public void loadGifThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        Glide.with(context).asBitmap().load(uri).apply(new RequestOptions().override(i, i).placeholder(drawable).centerCrop()).into(imageView);
    }

    public void loadImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).apply(new RequestOptions().override(i, i2).priority(Priority.HIGH).fitCenter()).into(imageView);
    }

    public void loadGifImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        Glide.with(context).asGif().load(uri).apply(new RequestOptions().override(i, i2).priority(Priority.HIGH).fitCenter()).into(imageView);
    }
}
