package com.eternal.framework.binding.viewadapter.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public final class ViewAdapter {
    public static void loadThumbnail(ImageView imageView, String str, int i, float f) {
        Glide.with(imageView.getContext()).asBitmap().load(str).apply(new RequestOptions().placeholder(i).centerCrop()).into(imageView);
    }

    public static void setRes(ImageView imageView, int i) {
        imageView.setImageResource(i);
    }

    public static void glideRes(ImageView imageView, int i) {
        if (i != 0) {
            Glide.with(imageView.getContext()).load(Integer.valueOf(i)).into(imageView);
        }
    }

    public static void setColorFilter(ImageView imageView, int i) {
        imageView.setColorFilter(i);
    }

    public static void loadUrl(ImageView imageView, Drawable drawable) {
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }
}
