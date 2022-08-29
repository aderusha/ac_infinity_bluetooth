package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.C0875R;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    private static final String TAG = "CustomViewTarget";
    private static final int VIEW_TAG_ID = C0875R.C0878id.glide_custom_view_target_tag;
    private View.OnAttachStateChangeListener attachStateListener;
    private boolean isAttachStateListenerAdded;
    private boolean isClearedByUs;
    private int overrideTag;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public abstract void onResourceCleared(Drawable drawable);

    /* access modifiers changed from: protected */
    public void onResourceLoading(Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public CustomViewTarget(T t) {
        this.view = (View) Preconditions.checkNotNull(t);
        this.sizeDeterminer = new SizeDeterminer(t);
    }

    public final CustomViewTarget<T, Z> waitForLayout() {
        this.sizeDeterminer.waitForLayout = true;
        return this;
    }

    public final CustomViewTarget<T, Z> clearOnDetach() {
        if (this.attachStateListener != null) {
            return this;
        }
        this.attachStateListener = new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                CustomViewTarget.this.resumeMyRequest();
            }

            public void onViewDetachedFromWindow(View view) {
                CustomViewTarget.this.pauseMyRequest();
            }
        };
        maybeAddAttachStateListener();
        return this;
    }

    public final CustomViewTarget<T, Z> useTagId(int i) {
        if (this.overrideTag == 0) {
            this.overrideTag = i;
            return this;
        }
        throw new IllegalArgumentException("You cannot change the tag id once it has been set.");
    }

    public final T getView() {
        return this.view;
    }

    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        this.sizeDeterminer.getSize(sizeReadyCallback);
    }

    public final void removeCallback(SizeReadyCallback sizeReadyCallback) {
        this.sizeDeterminer.removeCallback(sizeReadyCallback);
    }

    public final void onLoadStarted(Drawable drawable) {
        maybeAddAttachStateListener();
        onResourceLoading(drawable);
    }

    public final void onLoadCleared(Drawable drawable) {
        this.sizeDeterminer.clearCallbacksAndListener();
        onResourceCleared(drawable);
        if (!this.isClearedByUs) {
            maybeRemoveAttachStateListener();
        }
    }

    public final void setRequest(Request request) {
        setTag(request);
    }

    public final Request getRequest() {
        Object tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    /* access modifiers changed from: package-private */
    public final void resumeMyRequest() {
        Request request = getRequest();
        if (request != null && request.isCleared()) {
            request.begin();
        }
    }

    /* access modifiers changed from: package-private */
    public final void pauseMyRequest() {
        Request request = getRequest();
        if (request != null) {
            this.isClearedByUs = true;
            request.clear();
            this.isClearedByUs = false;
        }
    }

    private void setTag(Object obj) {
        T t = this.view;
        int i = this.overrideTag;
        if (i == 0) {
            i = VIEW_TAG_ID;
        }
        t.setTag(i, obj);
    }

    private Object getTag() {
        T t = this.view;
        int i = this.overrideTag;
        if (i == 0) {
            i = VIEW_TAG_ID;
        }
        return t.getTag(i);
    }

    private void maybeAddAttachStateListener() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.attachStateListener;
        if (onAttachStateChangeListener != null && !this.isAttachStateListenerAdded) {
            this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
            this.isAttachStateListenerAdded = true;
        }
    }

    private void maybeRemoveAttachStateListener() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.attachStateListener;
        if (onAttachStateChangeListener != null && this.isAttachStateListenerAdded) {
            this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
            this.isAttachStateListenerAdded = false;
        }
    }

    static final class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        static Integer maxDisplayLength;
        private final List<SizeReadyCallback> cbs = new ArrayList();
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;
        boolean waitForLayout;

        private boolean isDimensionValid(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        SizeDeterminer(View view2) {
            this.view = view2;
        }

        private static int getMaxDisplayLength(Context context) {
            if (maxDisplayLength == null) {
                Display defaultDisplay = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                maxDisplayLength = Integer.valueOf(Math.max(point.x, point.y));
            }
            return maxDisplayLength.intValue();
        }

        private void notifyCbs(int i, int i2) {
            Iterator it = new ArrayList(this.cbs).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(i, i2);
            }
        }

        /* access modifiers changed from: package-private */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int targetWidth = getTargetWidth();
                int targetHeight = getTargetHeight();
                if (isViewStateAndSizeValid(targetWidth, targetHeight)) {
                    notifyCbs(targetWidth, targetHeight);
                    clearCallbacksAndListener();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void getSize(SizeReadyCallback sizeReadyCallback) {
            int targetWidth = getTargetWidth();
            int targetHeight = getTargetHeight();
            if (isViewStateAndSizeValid(targetWidth, targetHeight)) {
                sizeReadyCallback.onSizeReady(targetWidth, targetHeight);
                return;
            }
            if (!this.cbs.contains(sizeReadyCallback)) {
                this.cbs.add(sizeReadyCallback);
            }
            if (this.layoutListener == null) {
                ViewTreeObserver viewTreeObserver = this.view.getViewTreeObserver();
                SizeDeterminerLayoutListener sizeDeterminerLayoutListener = new SizeDeterminerLayoutListener(this);
                this.layoutListener = sizeDeterminerLayoutListener;
                viewTreeObserver.addOnPreDrawListener(sizeDeterminerLayoutListener);
            }
        }

        /* access modifiers changed from: package-private */
        public void removeCallback(SizeReadyCallback sizeReadyCallback) {
            this.cbs.remove(sizeReadyCallback);
        }

        /* access modifiers changed from: package-private */
        public void clearCallbacksAndListener() {
            ViewTreeObserver viewTreeObserver = this.view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.layoutListener);
            }
            this.layoutListener = null;
            this.cbs.clear();
        }

        private boolean isViewStateAndSizeValid(int i, int i2) {
            return isDimensionValid(i) && isDimensionValid(i2);
        }

        private int getTargetHeight() {
            int paddingTop = this.view.getPaddingTop() + this.view.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int getTargetWidth() {
            int paddingLeft = this.view.getPaddingLeft() + this.view.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private int getTargetDimen(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.waitForLayout && this.view.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.view.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable(CustomViewTarget.TAG, 4)) {
                Log.i(CustomViewTarget.TAG, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return getMaxDisplayLength(this.view.getContext());
        }

        private static final class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable(CustomViewTarget.TAG, 2)) {
                    Log.v(CustomViewTarget.TAG, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.checkCurrentDimens();
                return true;
            }
        }
    }
}
