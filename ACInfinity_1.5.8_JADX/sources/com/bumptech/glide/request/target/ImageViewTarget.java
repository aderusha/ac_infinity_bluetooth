package com.bumptech.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.transition.Transition;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements Transition.ViewAdapter {
    private Animatable animatable;

    /* access modifiers changed from: protected */
    public abstract void setResource(Z z);

    public ImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public ImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    public Drawable getCurrentDrawable() {
        return ((ImageView) this.view).getDrawable();
    }

    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public void onLoadStarted(Drawable drawable) {
        super.onLoadStarted(drawable);
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onLoadFailed(Drawable drawable) {
        super.onLoadFailed(drawable);
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
        setResourceInternal((Object) null);
        setDrawable(drawable);
    }

    public void onResourceReady(Z z, Transition<? super Z> transition) {
        if (transition == null || !transition.transition(z, this)) {
            setResourceInternal(z);
        } else {
            maybeUpdateAnimatable(z);
        }
    }

    public void onStart() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.start();
        }
    }

    public void onStop() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
    }

    private void setResourceInternal(Z z) {
        setResource(z);
        maybeUpdateAnimatable(z);
    }

    private void maybeUpdateAnimatable(Z z) {
        if (z instanceof Animatable) {
            Animatable animatable2 = (Animatable) z;
            this.animatable = animatable2;
            animatable2.start();
            return;
        }
        this.animatable = null;
    }
}
