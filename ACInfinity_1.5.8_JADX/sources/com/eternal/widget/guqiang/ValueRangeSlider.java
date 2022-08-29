package com.eternal.widget.guqiang;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.widget.C2779R;

public class ValueRangeSlider extends ConstraintLayout {
    private int cWidth;
    /* access modifiers changed from: private */
    public int chWidth;
    private ImageView circle;
    private ImageView circleHigh;
    private ImageView circleLow;
    /* access modifiers changed from: private */
    public float dWidth;
    private int distance;
    /* access modifiers changed from: private */
    public int high;
    boolean highClose;
    /* access modifiers changed from: private */
    public int low;
    boolean lowClose;
    /* access modifiers changed from: private */
    public int min;
    /* access modifiers changed from: private */
    public int now;
    boolean nowClose;
    private View progress;
    private View progressBack;

    /* renamed from: px */
    private float f255px;
    private TextView show;
    private TextView showHigh;
    private TextView showLow;
    private String unit;
    private int width;

    public ValueRangeSlider(Context context) {
        this(context, (AttributeSet) null);
    }

    public ValueRangeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ValueRangeSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.value_range_slider, this);
        this.show = (TextView) inflate.findViewById(C2779R.C2782id.txt_show);
        this.showHigh = (TextView) inflate.findViewById(C2779R.C2782id.txt_show_high);
        this.showLow = (TextView) inflate.findViewById(C2779R.C2782id.txt_show_low);
        this.circle = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle);
        this.circleHigh = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle_high);
        this.circleLow = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle_low);
        this.progressBack = inflate.findViewById(C2779R.C2782id.v_progress_background);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
    }

    public void setMin(int i) {
        this.min = i;
        update();
    }

    public void setDistance(int i) {
        this.distance = i;
        update();
    }

    public void setUnit(String str) {
        this.unit = str;
        update();
    }

    public void moveView(float f) {
        int i = this.width;
        int i2 = this.chWidth;
        int i3 = i - i2;
        float f2 = f - (((float) i2) / 2.0f);
        float f3 = 0.0f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            float f4 = (float) i3;
            if (f2 > f4) {
                f2 = f4;
            }
        }
        this.now = Math.round((f2 / this.dWidth) + ((float) this.min));
        TextView textView = this.show;
        textView.setText(this.now + this.unit);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.show.measure(makeMeasureSpec, makeMeasureSpec);
        this.circle.setTranslationX((((float) (this.now - this.min)) * this.dWidth) - (((float) (this.cWidth - this.chWidth)) / 2.0f));
        int measuredWidth = this.show.getMeasuredWidth();
        float f5 = (float) measuredWidth;
        float x = (this.circle.getX() + (((float) this.chWidth) / 2.0f)) - (f5 / 2.0f);
        TextView textView2 = this.show;
        if (x >= 0.0f) {
            int i4 = this.width;
            f3 = f5 + x > ((float) i4) ? (float) (i4 - measuredWidth) : x;
        }
        textView2.setTranslationX(f3);
        moveProgress();
    }

    public void moveHighView(float f) {
        int i = this.width;
        int i2 = this.chWidth;
        int i3 = i - i2;
        float f2 = f - (((float) i2) / 2.0f);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            float f3 = (float) i3;
            if (f2 > f3) {
                f2 = f3;
            }
        }
        this.high = Math.round((f2 / this.dWidth) + ((float) this.min));
        TextView textView = this.showHigh;
        textView.setText(this.high + this.unit);
        this.circleHigh.setTranslationX(((float) (this.high - this.min)) * this.dWidth);
        moveProgress();
        adjustShowPosition();
    }

    public void moveLowView(float f) {
        int i = this.width;
        int i2 = this.chWidth;
        int i3 = i - i2;
        float f2 = f - (((float) i2) / 2.0f);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            float f3 = (float) i3;
            if (f2 > f3) {
                f2 = f3;
            }
        }
        this.low = Math.round((f2 / this.dWidth) + ((float) this.min));
        TextView textView = this.showLow;
        textView.setText(this.low + this.unit);
        this.circleLow.setTranslationX(((float) (this.low - this.min)) * this.dWidth);
        moveProgress();
        adjustShowPosition();
    }

    private void adjustShowPosition() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.showLow.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.showLow.getMeasuredWidth();
        float x = this.circleLow.getX();
        float f = (float) measuredWidth;
        float f2 = ((((float) this.chWidth) / 2.0f) + x) - (f / 2.0f);
        float f3 = 0.0f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            int i = this.width;
            if (f2 + f > ((float) i)) {
                f2 = (float) (i - measuredWidth);
            }
        }
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.showHigh.measure(makeMeasureSpec2, makeMeasureSpec2);
        int measuredWidth2 = this.showHigh.getMeasuredWidth();
        float x2 = this.circleHigh.getX();
        float f4 = (float) measuredWidth2;
        float f5 = ((((float) this.chWidth) / 2.0f) + x2) - (f4 / 2.0f);
        if (f5 < 0.0f) {
            f5 = 0.0f;
        } else {
            int i2 = this.width;
            if (f5 + f4 > ((float) i2)) {
                f5 = (float) (i2 - measuredWidth2);
            }
        }
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C2779R.dimen.dp_3);
        float f6 = ((float) (measuredWidth + measuredWidth2)) + dimensionPixelSize;
        if (!this.lowClose && !this.highClose) {
            if (x > x2) {
                if (f5 + f4 + dimensionPixelSize > f2) {
                    float width2 = (((x2 + ((float) this.circleHigh.getWidth())) + x) - f6) / 2.0f;
                    if (width2 >= 0.0f) {
                        int i3 = this.width;
                        f3 = width2 + f6 > ((float) i3) ? ((float) i3) - f6 : width2;
                    }
                    f2 = f4 + f3 + dimensionPixelSize;
                    f5 = f3;
                }
            } else if (f2 + f + dimensionPixelSize > f5) {
                float width3 = (((x + ((float) this.circleHigh.getWidth())) + x2) - f6) / 2.0f;
                if (width3 >= 0.0f) {
                    int i4 = this.width;
                    f3 = width3 + f6 > ((float) i4) ? ((float) i4) - f6 : width3;
                }
                f5 = f + f3 + dimensionPixelSize;
                f2 = f3;
            }
        }
        this.showHigh.setTranslationX(f5);
        this.showLow.setTranslationX(f2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void moveProgress() {
        /*
            r5 = this;
            boolean r0 = r5.nowClose
            r1 = 0
            if (r0 != 0) goto L_0x00c6
            boolean r0 = r5.highClose
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != 0) goto L_0x0077
            boolean r3 = r5.lowClose
            if (r3 != 0) goto L_0x0077
            int r0 = r5.high
            int r3 = r5.low
            if (r0 >= r3) goto L_0x0037
            int r4 = r5.now
            if (r4 <= r0) goto L_0x00c6
            if (r4 >= r3) goto L_0x00c6
            android.widget.ImageView r0 = r5.circleHigh
            float r0 = r0.getTranslationX()
            android.widget.ImageView r3 = r5.circleHigh
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r2
            float r0 = r0 + r3
            android.widget.ImageView r3 = r5.circle
            float r3 = r3.getTranslationX()
            android.widget.ImageView r4 = r5.circle
            int r4 = r4.getWidth()
            goto L_0x009a
        L_0x0037:
            if (r0 <= r3) goto L_0x00c6
            int r4 = r5.now
            if (r4 <= r0) goto L_0x0059
            android.widget.ImageView r0 = r5.circleHigh
            float r0 = r0.getTranslationX()
            android.widget.ImageView r3 = r5.circleHigh
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r2
            float r0 = r0 + r3
            android.widget.ImageView r3 = r5.circle
            float r3 = r3.getTranslationX()
            android.widget.ImageView r4 = r5.circle
            int r4 = r4.getWidth()
            goto L_0x009a
        L_0x0059:
            if (r4 >= r3) goto L_0x00c6
            android.widget.ImageView r0 = r5.circle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r3 = r5.circle
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r2
            float r0 = r0 + r3
            android.widget.ImageView r3 = r5.circleLow
            float r3 = r3.getTranslationX()
            android.widget.ImageView r4 = r5.circleLow
            int r4 = r4.getWidth()
            goto L_0x009a
        L_0x0077:
            if (r0 != 0) goto L_0x00a0
            int r0 = r5.now
            int r3 = r5.high
            if (r0 <= r3) goto L_0x00c6
            android.widget.ImageView r0 = r5.circleHigh
            float r0 = r0.getTranslationX()
            android.widget.ImageView r3 = r5.circleHigh
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r2
            float r0 = r0 + r3
            android.widget.ImageView r3 = r5.circle
            float r3 = r3.getTranslationX()
            android.widget.ImageView r4 = r5.circle
            int r4 = r4.getWidth()
        L_0x009a:
            float r4 = (float) r4
            float r4 = r4 / r2
            float r3 = r3 + r4
            float r3 = r3 - r0
            int r2 = (int) r3
            goto L_0x00c8
        L_0x00a0:
            boolean r0 = r5.lowClose
            if (r0 != 0) goto L_0x00c6
            int r0 = r5.now
            int r3 = r5.low
            if (r0 >= r3) goto L_0x00c6
            android.widget.ImageView r0 = r5.circle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r3 = r5.circle
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r2
            float r0 = r0 + r3
            android.widget.ImageView r3 = r5.circleLow
            float r3 = r3.getTranslationX()
            android.widget.ImageView r4 = r5.circleLow
            int r4 = r4.getWidth()
            goto L_0x009a
        L_0x00c6:
            r0 = 0
            r2 = 0
        L_0x00c8:
            android.view.View r3 = r5.progress
            r3.setTranslationX(r0)
            if (r2 != 0) goto L_0x00d6
            android.view.View r0 = r5.progress
            r1 = 4
            r0.setVisibility(r1)
            goto L_0x00e8
        L_0x00d6:
            android.view.View r0 = r5.progress
            r0.setVisibility(r1)
            android.view.View r0 = r5.progress
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            r0.width = r2
            android.view.View r1 = r5.progress
            r1.setLayoutParams(r0)
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.ValueRangeSlider.moveProgress():void");
    }

    public void setProgress(int i) {
        moveView((((float) (i - this.min)) * this.dWidth) + (((float) this.chWidth) / 2.0f));
        this.now = i;
    }

    public void setLow(int i) {
        moveLowView((((float) (i - this.min)) * this.dWidth) + (((float) this.chWidth) / 2.0f));
        this.low = i;
    }

    public void setHigh(int i) {
        moveHighView((((float) (i - this.min)) * this.dWidth) + (((float) this.chWidth) / 2.0f));
        this.high = i;
    }

    public void setHighClose(boolean z) {
        this.highClose = z;
        if (z) {
            this.circleHigh.setVisibility(4);
            this.showHigh.setVisibility(4);
        } else {
            this.circleHigh.setVisibility(0);
            this.showHigh.setVisibility(0);
        }
        moveProgress();
        adjustShowPosition();
    }

    public void setLowClose(boolean z) {
        this.lowClose = z;
        if (z) {
            this.circleLow.setVisibility(4);
            this.showLow.setVisibility(4);
        } else {
            this.circleLow.setVisibility(0);
            this.showLow.setVisibility(0);
        }
        moveProgress();
        adjustShowPosition();
    }

    public void setNowClose(boolean z) {
        this.nowClose = z;
        if (z) {
            this.circle.setVisibility(4);
        } else {
            this.circle.setVisibility(0);
        }
        moveProgress();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.width = this.progressBack.getMeasuredWidth();
        this.chWidth = this.circleHigh.getMeasuredWidth();
        this.cWidth = this.circle.getMeasuredWidth();
        update();
    }

    private void update() {
        int i = this.distance;
        if (i != 0) {
            this.dWidth = ((float) (this.width - this.chWidth)) / ((float) i);
            post(new Runnable() {
                public void run() {
                    ValueRangeSlider valueRangeSlider = ValueRangeSlider.this;
                    valueRangeSlider.moveView((((float) (valueRangeSlider.now - ValueRangeSlider.this.min)) * ValueRangeSlider.this.dWidth) + (((float) ValueRangeSlider.this.chWidth) / 2.0f));
                    ValueRangeSlider valueRangeSlider2 = ValueRangeSlider.this;
                    valueRangeSlider2.moveLowView((((float) (valueRangeSlider2.low - ValueRangeSlider.this.min)) * ValueRangeSlider.this.dWidth) + (((float) ValueRangeSlider.this.chWidth) / 2.0f));
                    ValueRangeSlider valueRangeSlider3 = ValueRangeSlider.this;
                    valueRangeSlider3.moveHighView((((float) (valueRangeSlider3.high - ValueRangeSlider.this.min)) * ValueRangeSlider.this.dWidth) + (((float) ValueRangeSlider.this.chWidth) / 2.0f));
                }
            });
        }
    }
}
