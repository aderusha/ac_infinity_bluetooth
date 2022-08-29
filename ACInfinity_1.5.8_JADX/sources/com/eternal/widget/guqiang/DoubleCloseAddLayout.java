package com.eternal.widget.guqiang;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.eternal.widget.guqiang.IEffectBar;

public class DoubleCloseAddLayout extends LinearLayout {
    private boolean fillWhenEqual;
    /* access modifiers changed from: private */
    public DoubleCloseAddLayoutListener listener;
    /* access modifiers changed from: private */
    public IEffectBar one;
    /* access modifiers changed from: private */
    public IEffectBar two;

    public DoubleCloseAddLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DoubleCloseAddLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DoubleCloseAddLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DoubleCloseAddLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        post(new Runnable() {
            public void run() {
                DoubleCloseAddLayout.this.init();
            }
        });
    }

    /* access modifiers changed from: private */
    public void init() {
        this.one = (IEffectBar) getChildAt(1);
        this.two = (IEffectBar) getChildAt(4);
        this.one.setFillWhenEqual(this.fillWhenEqual);
        this.two.setFillWhenEqual(this.fillWhenEqual);
        initOne();
        initTow();
    }

    private void initOne() {
        this.one.setListener(new IEffectBar.Listener() {
            public void onDown(boolean z) {
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onFirstDown(z);
                }
            }

            public void onChange(int i) {
                if (DoubleCloseAddLayout.this.one.isChecked()) {
                    DoubleCloseAddLayout.this.two.setProgress(DoubleCloseAddLayout.this.one.getTx(), false);
                }
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onFirst(i);
                }
            }

            public void onUp(boolean z, int i) {
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onEndFirst(z, i);
                }
            }

            public void onChecked(boolean z) {
                if (z) {
                    if (DoubleCloseAddLayout.this.two.isChecked()) {
                        DoubleCloseAddLayout.this.one.setProgress(DoubleCloseAddLayout.this.two.getTx(), true);
                    }
                    DoubleCloseAddLayout.this.two.setProgress(DoubleCloseAddLayout.this.one.getTx(), false);
                } else {
                    DoubleCloseAddLayout.this.two.setProgress(0.0f, false);
                }
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onFirstChecked(z);
                }
            }
        });
    }

    private void initTow() {
        this.two.setListener(new IEffectBar.Listener() {
            public void onDown(boolean z) {
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onLastDown(z);
                }
            }

            public void onChange(int i) {
                if (DoubleCloseAddLayout.this.two.isChecked()) {
                    DoubleCloseAddLayout.this.one.setProgress(DoubleCloseAddLayout.this.two.getTx(), true);
                }
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onLast(i);
                }
            }

            public void onUp(boolean z, int i) {
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onEndLast(z, i);
                }
            }

            public void onChecked(boolean z) {
                if (z) {
                    if (DoubleCloseAddLayout.this.one.isChecked()) {
                        DoubleCloseAddLayout.this.two.setProgress(DoubleCloseAddLayout.this.one.getTx(), false);
                    }
                    DoubleCloseAddLayout.this.one.setProgress(DoubleCloseAddLayout.this.two.getTx(), true);
                } else {
                    DoubleCloseAddLayout.this.one.setProgress(-1.0f, true);
                }
                if (DoubleCloseAddLayout.this.listener != null) {
                    DoubleCloseAddLayout.this.listener.onLastChecked(z);
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
                DoubleCloseAddLayout.this.one.setFactory(factory);
                DoubleCloseAddLayout.this.two.setFactory(factory);
            }
        });
    }
}
