package p015it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;

/* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouch */
public class ImageViewTouch extends ImageViewTouchBase {
    static final float SCROLL_DELTA_THRESHOLD = 1.0f;
    protected int mDoubleTapDirection;
    protected boolean mDoubleTapEnabled;
    /* access modifiers changed from: private */
    public OnImageViewTouchDoubleTapListener mDoubleTapListener;
    protected GestureDetector mGestureDetector;
    protected GestureDetector.OnGestureListener mGestureListener;
    protected ScaleGestureDetector mScaleDetector;
    protected boolean mScaleEnabled;
    protected float mScaleFactor;
    protected ScaleGestureDetector.OnScaleGestureListener mScaleListener;
    protected boolean mScrollEnabled;
    /* access modifiers changed from: private */
    public OnImageViewTouchSingleTapListener mSingleTapListener;
    protected int mTouchSlop;

    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouch$OnImageViewTouchDoubleTapListener */
    public interface OnImageViewTouchDoubleTapListener {
        void onDoubleTap();
    }

    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouch$OnImageViewTouchSingleTapListener */
    public interface OnImageViewTouchSingleTapListener {
        void onSingleTapConfirmed();
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.mDoubleTapEnabled = true;
        this.mScaleEnabled = true;
        this.mScrollEnabled = true;
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDoubleTapEnabled = true;
        this.mScaleEnabled = true;
        this.mScrollEnabled = true;
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mGestureListener = getGestureListener();
        this.mScaleListener = getScaleListener();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), this.mScaleListener);
        this.mGestureDetector = new GestureDetector(getContext(), this.mGestureListener, (Handler) null, true);
        this.mDoubleTapDirection = 1;
    }

    public void setDoubleTapListener(OnImageViewTouchDoubleTapListener onImageViewTouchDoubleTapListener) {
        this.mDoubleTapListener = onImageViewTouchDoubleTapListener;
    }

    public void setSingleTapListener(OnImageViewTouchSingleTapListener onImageViewTouchSingleTapListener) {
        this.mSingleTapListener = onImageViewTouchSingleTapListener;
    }

    public void setDoubleTapEnabled(boolean z) {
        this.mDoubleTapEnabled = z;
    }

    public void setScaleEnabled(boolean z) {
        this.mScaleEnabled = z;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public boolean getDoubleTapEnabled() {
        return this.mDoubleTapEnabled;
    }

    /* access modifiers changed from: protected */
    public GestureDetector.OnGestureListener getGestureListener() {
        return new GestureListener();
    }

    /* access modifiers changed from: protected */
    public ScaleGestureDetector.OnScaleGestureListener getScaleListener() {
        return new ScaleListener();
    }

    /* access modifiers changed from: protected */
    public void _setImageDrawable(Drawable drawable, Matrix matrix, float f, float f2) {
        super._setImageDrawable(drawable, matrix, f, f2);
        this.mScaleFactor = getMaxScale() / 3.0f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mScaleDetector.onTouchEvent(motionEvent);
        if (!this.mScaleDetector.isInProgress()) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if ((motionEvent.getAction() & 255) != 1) {
            return true;
        }
        return onUp(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onZoomAnimationCompleted(float f) {
        if (f < getMinScale()) {
            zoomTo(getMinScale(), 50.0f);
        }
    }

    /* access modifiers changed from: protected */
    public float onDoubleTapPost(float f, float f2) {
        if (this.mDoubleTapDirection == 1) {
            float f3 = this.mScaleFactor;
            if ((2.0f * f3) + f <= f2) {
                return f + f3;
            }
            this.mDoubleTapDirection = -1;
            return f2;
        }
        this.mDoubleTapDirection = 1;
        return SCROLL_DELTA_THRESHOLD;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (getScale() == SCROLL_DELTA_THRESHOLD) {
            return false;
        }
        this.mUserScaled = true;
        scrollBy(-f, -f2);
        invalidate();
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = motionEvent2.getX() - motionEvent.getX();
        float y = motionEvent2.getY() - motionEvent.getY();
        if (Math.abs(f) <= 800.0f && Math.abs(f2) <= 800.0f) {
            return false;
        }
        this.mUserScaled = true;
        scrollBy(x / 2.0f, y / 2.0f, 300.0d);
        invalidate();
        return true;
    }

    public boolean onUp(MotionEvent motionEvent) {
        if (getScale() >= getMinScale()) {
            return true;
        }
        zoomTo(getMinScale(), 50.0f);
        return true;
    }

    public boolean canScroll(int i) {
        RectF bitmapRect = getBitmapRect();
        updateRect(bitmapRect, this.mScrollRect);
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        if (bitmapRect == null) {
            return false;
        }
        if (bitmapRect.right < ((float) rect.right) || i >= 0) {
            if (((double) Math.abs(bitmapRect.left - this.mScrollRect.left)) > 1.0d) {
                return true;
            }
            return false;
        } else if (Math.abs(bitmapRect.right - ((float) rect.right)) > SCROLL_DELTA_THRESHOLD) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouch$GestureListener */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public GestureListener() {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (ImageViewTouch.this.mSingleTapListener != null) {
                ImageViewTouch.this.mSingleTapListener.onSingleTapConfirmed();
            }
            return ImageViewTouch.this.onSingleTapConfirmed(motionEvent);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            Log.i(ImageViewTouchBase.LOG_TAG, "onDoubleTap. double tap enabled? " + ImageViewTouch.this.mDoubleTapEnabled);
            if (ImageViewTouch.this.mDoubleTapEnabled) {
                ImageViewTouch.this.mUserScaled = true;
                float scale = ImageViewTouch.this.getScale();
                ImageViewTouch imageViewTouch = ImageViewTouch.this;
                ImageViewTouch.this.zoomTo(Math.min(ImageViewTouch.this.getMaxScale(), Math.max(imageViewTouch.onDoubleTapPost(scale, imageViewTouch.getMaxScale()), ImageViewTouch.this.getMinScale())), motionEvent.getX(), motionEvent.getY(), 200.0f);
                ImageViewTouch.this.invalidate();
            }
            if (ImageViewTouch.this.mDoubleTapListener != null) {
                ImageViewTouch.this.mDoubleTapListener.onDoubleTap();
            }
            return super.onDoubleTap(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (ImageViewTouch.this.isLongClickable() && !ImageViewTouch.this.mScaleDetector.isInProgress()) {
                ImageViewTouch.this.setPressed(true);
                ImageViewTouch.this.performLongClick();
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ImageViewTouch.this.mScrollEnabled && motionEvent != null && motionEvent2 != null && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1 && !ImageViewTouch.this.mScaleDetector.isInProgress()) {
                return ImageViewTouch.this.onScroll(motionEvent, motionEvent2, f, f2);
            }
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ImageViewTouch.this.mScrollEnabled && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1 && !ImageViewTouch.this.mScaleDetector.isInProgress() && ImageViewTouch.this.getScale() != ImageViewTouch.SCROLL_DELTA_THRESHOLD) {
                return ImageViewTouch.this.onFling(motionEvent, motionEvent2, f, f2);
            }
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return ImageViewTouch.this.onSingleTapUp(motionEvent);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return ImageViewTouch.this.onDown(motionEvent);
        }
    }

    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouch$ScaleListener */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        protected boolean mScaled = false;

        public ScaleListener() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float currentSpan = scaleGestureDetector.getCurrentSpan() - scaleGestureDetector.getPreviousSpan();
            float scale = ImageViewTouch.this.getScale() * scaleGestureDetector.getScaleFactor();
            if (ImageViewTouch.this.mScaleEnabled) {
                boolean z = this.mScaled;
                if (z && currentSpan != 0.0f) {
                    ImageViewTouch.this.mUserScaled = true;
                    ImageViewTouch.this.zoomTo(Math.min(ImageViewTouch.this.getMaxScale(), Math.max(scale, ImageViewTouch.this.getMinScale() - 0.1f)), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    ImageViewTouch.this.mDoubleTapDirection = 1;
                    ImageViewTouch.this.invalidate();
                    return true;
                } else if (!z) {
                    this.mScaled = true;
                }
            }
            return true;
        }
    }
}
