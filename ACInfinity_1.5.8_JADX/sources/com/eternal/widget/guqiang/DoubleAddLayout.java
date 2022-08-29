package com.eternal.widget.guqiang;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.eternal.widget.guqiang.IEffectBar;

public class DoubleAddLayout extends LinearLayout {
    private boolean fillWhenEqual;
    /* access modifiers changed from: private */
    public DoubleCloseAddLayoutListener listener;
    /* access modifiers changed from: private */
    public IEffectBar one;
    /* access modifiers changed from: private */
    public IEffectBar two;

    public DoubleAddLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DoubleAddLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DoubleAddLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DoubleAddLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        post(new Runnable() {
            public void run() {
                DoubleAddLayout.this.init();
            }
        });
    }

    /* access modifiers changed from: private */
    public void init() {
        this.one = (IEffectBar) getChildAt(0);
        this.two = (IEffectBar) getChildAt(2);
        this.one.setFillWhenEqual(this.fillWhenEqual);
        this.two.setFillWhenEqual(this.fillWhenEqual);
        initOne();
        initTow();
    }

    private void initOne() {
        this.one.setListener(new IEffectBar.Listener() {
            public void onDown(boolean z) {
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onFirstDown(z);
                }
            }

            public void onChange(int i) {
                if (DoubleAddLayout.this.one.isChecked()) {
                    DoubleAddLayout.this.two.setProgress(DoubleAddLayout.this.one.getTx(), false);
                }
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onFirst(i);
                }
            }

            public void onUp(boolean z, int i) {
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onEndFirst(z, i);
                }
            }

            public void onChecked(boolean z) {
                if (z) {
                    if (DoubleAddLayout.this.two.isChecked()) {
                        DoubleAddLayout.this.one.setProgress(DoubleAddLayout.this.two.getTx(), true);
                    }
                    DoubleAddLayout.this.two.setProgress(DoubleAddLayout.this.one.getTx(), false);
                } else {
                    DoubleAddLayout.this.two.setProgress(0.0f, false);
                }
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onFirstChecked(z);
                }
            }
        });
    }

    private void initTow() {
        this.two.setListener(new IEffectBar.Listener() {
            public void onDown(boolean z) {
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onLastDown(z);
                }
            }

            public void onChange(int i) {
                if (DoubleAddLayout.this.two.isChecked()) {
                    DoubleAddLayout.this.one.setProgress(DoubleAddLayout.this.two.getTx(), true);
                }
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onLast(i);
                }
            }

            public void onUp(boolean z, int i) {
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onEndLast(z, i);
                }
            }

            public void onChecked(boolean z) {
                if (z) {
                    if (DoubleAddLayout.this.one.isChecked()) {
                        DoubleAddLayout.this.two.setProgress(DoubleAddLayout.this.one.getTx(), false);
                    }
                    DoubleAddLayout.this.one.setProgress(DoubleAddLayout.this.two.getTx(), true);
                } else {
                    DoubleAddLayout.this.one.setProgress(-1.0f, true);
                }
                if (DoubleAddLayout.this.listener != null) {
                    DoubleAddLayout.this.listener.onLastChecked(z);
                }
            }
        });
    }

    public void setFillWhenEqual(boolean z) {
        this.fillWhenEqual = z;
        IEffectBar iEffectBar = this.one;
        if (iEffectBar != null) {
            iEffectBar.setFillWhenEqual(z);
        }
        IEffectBar iEffectBar2 = this.two;
        if (iEffectBar2 != null) {
            iEffectBar2.setFillWhenEqual(z);
        }
    }

    public void setListener(DoubleCloseAddLayoutListener doubleCloseAddLayoutListener) {
        this.listener = doubleCloseAddLayoutListener;
    }

    public void setFactory(final IEffectBar.Factory factory) {
        post(new Runnable() {
            public void run() {
                DoubleAddLayout.this.one.setFactory(factory);
                DoubleAddLayout.this.two.setFactory(factory);
            }
        });
    }
}
