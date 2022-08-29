package com.eternal.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class ExpandableLayout extends FrameLayout {
    private static final int DEFAULT_DURATION = 300;
    public static final int HORIZONTAL = 0;
    public static final String KEY_EXPANSION = "expansion";
    public static final String KEY_SUPER_STATE = "super_state";
    public static final int VERTICAL = 1;
    private ValueAnimator animator;
    private int duration;
    private float expansion;
    private Interpolator interpolator;
    private OnExpansionUpdateListener listener;
    private int orientation;
    private float parallax;
    /* access modifiers changed from: private */
    public int state;

    public interface OnExpansionUpdateListener {
        void onExpansionUpdate(float f, int i);
    }

    public interface State {
        public static final int COLLAPSED = 0;
        public static final int COLLAPSING = 1;
        public static final int EXPANDED = 3;
        public static final int EXPANDING = 2;
    }

    public ExpandableLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExpandableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.duration = 300;
        this.interpolator = new FastOutSlowInInterpolator();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2779R.styleable.ExpandableLayout);
            this.duration = obtainStyledAttributes.getInt(C2779R.styleable.ExpandableLayout_el_duration, 300);
            int i = 0;
            this.expansion = obtainStyledAttributes.getBoolean(C2779R.styleable.ExpandableLayout_el_expanded, false) ? 1.0f : 0.0f;
            this.orientation = obtainStyledAttributes.getInt(C2779R.styleable.ExpandableLayout_android_orientation, 1);
            this.parallax = obtainStyledAttributes.getFloat(C2779R.styleable.ExpandableLayout_el_parallax, 1.0f);
            obtainStyledAttributes.recycle();
            this.state = this.expansion != 0.0f ? 3 : i;
            setParallax(this.parallax);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        float f = isExpanded() ? 1.0f : 0.0f;
        this.expansion = f;
        bundle.putFloat(KEY_EXPANSION, f);
        bundle.putParcelable(KEY_SUPER_STATE, onSaveInstanceState);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        float f = bundle.getFloat(KEY_EXPANSION);
        this.expansion = f;
        this.state = f == 1.0f ? 3 : 0;
        super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i3 = this.orientation == 0 ? measuredWidth : measuredHeight;
        setVisibility((this.expansion == 0.0f && i3 == 0) ? 8 : 0);
        int round = i3 - Math.round(((float) i3) * this.expansion);
        float f = this.parallax;
        if (f > 0.0f) {
            float f2 = ((float) round) * f;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (this.orientation == 0) {
                    int i5 = -1;
                    if (Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1) {
                        i5 = 1;
                    }
                    childAt.setTranslationX(((float) i5) * f2);
                } else {
                    childAt.setTranslationY(-f2);
                }
            }
        }
        if (this.orientation == 0) {
            setMeasuredDimension(measuredWidth - round, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredHeight - round);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        super.onConfigurationChanged(configuration);
    }

    public int getState() {
        return this.state;
    }

    public boolean isExpanded() {
        int i = this.state;
        return i == 2 || i == 3;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        if (isExpanded()) {
            collapse(z);
        } else {
            expand(z);
        }
    }

    public void expand() {
        expand(true);
    }

    public void expand(boolean z) {
        setExpanded(true, z);
    }

    public void collapse() {
        collapse(true);
    }

    public void collapse(boolean z) {
        setExpanded(false, z);
    }

    public void setExpanded(boolean z) {
        setExpanded(z, true);
    }

    public void setExpanded(boolean z, boolean z2) {
        if (z != isExpanded()) {
            if (z2) {
                animateSize(z);
            } else {
                setExpansion(z ? 1.0f : 0.0f);
            }
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void setInterpolator(Interpolator interpolator2) {
        this.interpolator = interpolator2;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public float getExpansion() {
        return this.expansion;
    }

    public void setExpansion(float f) {
        float f2 = this.expansion;
        if (f2 != f) {
            float f3 = f - f2;
            if (f == 0.0f) {
                this.state = 0;
            } else if (f == 1.0f) {
                this.state = 3;
            } else if (f3 < 0.0f) {
                this.state = 1;
            } else if (f3 > 0.0f) {
                this.state = 2;
            }
            setVisibility(this.state == 0 ? 8 : 0);
            this.expansion = f;
            if (getChildAt(0) == null) {
                requestLayout();
            } else {
                getChildAt(0).requestLayout();
            }
            OnExpansionUpdateListener onExpansionUpdateListener = this.listener;
            if (onExpansionUpdateListener != null) {
                onExpansionUpdateListener.onExpansionUpdate(f, this.state);
            }
        }
    }

    public float getParallax() {
        return this.parallax;
    }

    public void setParallax(float f) {
        this.parallax = Math.min(1.0f, Math.max(0.0f, f));
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Orientation must be either 0 (horizontal) or 1 (vertical)");
        }
        this.orientation = i;
    }

    public void setOnExpansionUpdateListener(OnExpansionUpdateListener onExpansionUpdateListener) {
        this.listener = onExpansionUpdateListener;
    }

    private void animateSize(int i) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator = null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.expansion, (float) i});
        this.animator = ofFloat;
        ofFloat.setInterpolator(this.interpolator);
        this.animator.setDuration((long) this.duration);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableLayout.this.setExpansion(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.animator.addListener(new ExpansionListener(i));
        this.animator.start();
    }

    private class ExpansionListener implements Animator.AnimatorListener {
        private boolean canceled;
        private int targetExpansion;

        public void onAnimationRepeat(Animator animator) {
        }

        public ExpansionListener(int i) {
            this.targetExpansion = i;
        }

        public void onAnimationStart(Animator animator) {
            int unused = ExpandableLayout.this.state = this.targetExpansion == 0 ? 1 : 2;
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.canceled) {
                int unused = ExpandableLayout.this.state = this.targetExpansion == 0 ? 0 : 3;
                ExpandableLayout.this.setExpansion((float) this.targetExpansion);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.canceled = true;
        }
    }

    public static void setExpanded(ExpandableLayout expandableLayout, boolean z) {
        expandableLayout.setExpanded(z);
    }
}
