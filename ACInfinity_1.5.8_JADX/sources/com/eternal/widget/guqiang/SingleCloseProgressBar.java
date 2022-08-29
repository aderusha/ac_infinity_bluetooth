package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.IEffectBar;

public class SingleCloseProgressBar extends ConstraintLayout implements IEffectBar {
    /* access modifiers changed from: private */
    public int cWidth;
    private ImageView circle;
    /* access modifiers changed from: private */
    public float dWidth;
    private int distance;
    private IEffectBar.Factory factory;
    private int interval;
    private boolean isHigh;
    private boolean isMax;
    /* access modifiers changed from: private */
    public IEffectBar.Listener listener;
    private TextView maxTitle;
    /* access modifiers changed from: private */
    public int min;
    private TextView minTitle;
    /* access modifiers changed from: private */
    public int now;
    /* access modifiers changed from: private */
    public View progress;
    /* access modifiers changed from: private */
    public View progressBack;
    private int progressColor;

    /* renamed from: px */
    private float f252px;

    /* renamed from: s */
    private Switch f253s;
    /* access modifiers changed from: private */
    public TextView show;
    private TextView title;
    private int width;

    public void setFillWhenEqual(boolean z) {
    }

    public SingleCloseProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SingleCloseProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleCloseProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_single_close_progressbar, this);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.show = (TextView) inflate.findViewById(C2779R.C2782id.txt_show);
        this.circle = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
        this.progressBack = inflate.findViewById(C2779R.C2782id.v_progress_background);
        this.minTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_min);
        this.maxTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_max);
        Switch switchR = (Switch) inflate.findViewById(C2779R.C2782id.sw_close);
        this.f253s = switchR;
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SingleCloseProgressBar.this.show.setTextColor(ContextCompat.getColor(Utils.getContext(), C2779R.C2780color.color_15BAFF));
                    SingleCloseProgressBar.this.progressBack.setBackgroundResource(C2779R.C2781drawable.rectang_10_ffffff_shape);
                    SingleCloseProgressBar.this.progress.setVisibility(0);
                } else {
                    SingleCloseProgressBar.this.show.setTextColor(ContextCompat.getColor(Utils.getContext(), C2779R.C2780color.color_5D5D5D));
                    SingleCloseProgressBar.this.progressBack.setBackgroundResource(C2779R.C2781drawable.rectang_10_eeeeee_shape);
                    SingleCloseProgressBar.this.progress.setVisibility(4);
                }
                if (SingleCloseProgressBar.this.listener != null) {
                    SingleCloseProgressBar.this.listener.onChecked(z);
                }
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.SingleCloseProgressBar);
        this.title.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleCloseProgressBar_title));
        this.distance = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseProgressBar_distance, 0);
        this.min = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseProgressBar_minNum, 0);
        this.minTitle.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleCloseProgressBar_minTitle));
        this.maxTitle.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleCloseProgressBar_maxTitle));
        this.interval = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseProgressBar_interval, 1);
        this.isMax = obtainStyledAttributes.getBoolean(C2779R.styleable.SingleCloseProgressBar_isMax, false);
        this.isHigh = obtainStyledAttributes.getBoolean(C2779R.styleable.SingleCloseProgressBar_isHigh, false);
        int color = obtainStyledAttributes.getColor(C2779R.styleable.SingleCloseProgressBar_progressColor, -15353089);
        this.progressColor = color;
        this.progress.setBackgroundColor(color);
        obtainStyledAttributes.recycle();
    }

    public void setListener(IEffectBar.Listener listener2) {
        this.listener = listener2;
    }

    private boolean inRangeOfView(MotionEvent motionEvent) {
        this.circle.getLocationOnScreen(new int[2]);
        float x = this.circle.getX();
        float y = this.circle.getY();
        return new Rect((int) (x - ((float) ConvertUtils.dp2px(30.0f))), (int) y, (int) (x + ((float) this.cWidth) + ((float) ConvertUtils.dp2px(30.0f))), (int) (((float) this.circle.getMeasuredHeight()) + y)).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public void setType(String str, String str2, String str3, int i, int i2) {
        this.title.setText(str);
        setValue(str2, str3, i, i2);
    }

    public void setValue(String str, String str2, int i, int i2) {
        this.minTitle.setText(str);
        this.maxTitle.setText(str2);
        this.min = i;
        this.distance = i2;
    }

    public void setFactory(IEffectBar.Factory factory2) {
        this.factory = factory2;
        this.show.setText(factory2.getText((int) (((this.circle.getTranslationX() * ((float) this.distance)) / ((float) (this.width - this.cWidth))) + ((float) this.min))));
    }

    public void setProgress(float f, boolean z) {
        if (f == -1.0f) {
            this.f252px = (float) (this.width - this.cWidth);
        } else {
            this.f252px = f;
        }
        this.isMax = z;
        moveProgress(this.circle.getTranslationX());
    }

    public float getTx() {
        return this.circle.getTranslationX();
    }

    public void setProgress(int i) {
        moveView((((float) (i - this.min)) * this.dWidth) + (((float) this.cWidth) / 2.0f));
        this.now = i;
        IEffectBar.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(i);
        }
    }

    public int getProgress() {
        return this.now;
    }

    /* access modifiers changed from: private */
    public void moveView(float f) {
        int i = this.width;
        int i2 = this.cWidth;
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
        int round = Math.round((f2 / this.dWidth) + ((float) this.min));
        this.now = round;
        IEffectBar.Factory factory2 = this.factory;
        if (factory2 != null) {
            this.show.setText(factory2.getText(round));
        } else {
            this.show.setText(String.valueOf(round));
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.show.measure(makeMeasureSpec, makeMeasureSpec);
        this.circle.setTranslationX(f2);
        int measuredWidth = this.show.getMeasuredWidth();
        float x = this.circle.getX();
        if (x == 0.0f) {
            this.show.setTranslationX(0.0f);
        } else {
            float f5 = (float) measuredWidth;
            float f6 = (x + (((float) this.cWidth) / 2.0f)) - (f5 / 2.0f);
            TextView textView = this.show;
            if (f6 >= 0.0f) {
                int i4 = this.width;
                f3 = f5 + f6 > ((float) i4) ? (float) (i4 - measuredWidth) : f6;
            }
            textView.setTranslationX(f3);
        }
        moveProgress(f2);
    }

    private void moveProgress(float f) {
        float f2;
        if (this.isMax) {
            float f3 = this.f252px;
            if (f <= f3 || this.now == Math.round((f3 / this.dWidth) + ((float) this.min))) {
                f2 = (this.f252px - f) + ((float) this.cWidth);
            } else {
                f2 = ((float) this.width) - f;
            }
            float f4 = (float) ((int) f2);
            if (f4 == 0.0f) {
                f4 = ((float) this.width) - this.circle.getTranslationX();
            }
            this.progress.setTranslationX(this.circle.getTranslationX());
            ViewGroup.LayoutParams layoutParams = this.progress.getLayoutParams();
            layoutParams.width = (int) f4;
            this.progress.setLayoutParams(layoutParams);
            return;
        }
        float f5 = this.f252px;
        if (f > f5 || this.now == Math.round((f5 / this.dWidth) + ((float) this.min))) {
            float f6 = this.f252px;
            f -= f6;
            this.progress.setTranslationX(f6);
        } else {
            this.progress.setTranslationX(0.0f);
        }
        float f7 = (float) ((int) f);
        if (f7 <= 0.0f) {
            f7 = 1.0f;
        }
        ViewGroup.LayoutParams layoutParams2 = this.progress.getLayoutParams();
        layoutParams2.width = (int) f7;
        this.progress.setLayoutParams(layoutParams2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r0 != 3) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0033
            if (r0 == r2) goto L_0x0022
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r5 = 3
            if (r0 == r5) goto L_0x002b
            goto L_0x005c
        L_0x0011:
            float r5 = r5.getX()
            r4.moveView(r5)
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x005c
            int r0 = r4.now
            r5.onChange(r0)
            goto L_0x005c
        L_0x0022:
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x002b
            int r0 = r4.now
            r5.onUp(r2, r0)
        L_0x002b:
            android.view.ViewParent r5 = r4.getParent()
            r5.requestDisallowInterceptTouchEvent(r1)
            goto L_0x005c
        L_0x0033:
            android.view.ViewParent r0 = r4.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            boolean r5 = r4.inRangeOfView(r5)
            if (r5 != 0) goto L_0x0041
            return r1
        L_0x0041:
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x0048
            r5.onDown(r2)
        L_0x0048:
            android.widget.Switch r5 = r4.f253s
            boolean r5 = r5.isChecked()
            if (r5 != 0) goto L_0x005c
            android.widget.Switch r5 = r4.f253s
            r5.setChecked(r2)
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x005c
            r5.onChecked(r2)
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.SingleCloseProgressBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setChecked(boolean z) {
        this.f253s.setChecked(z);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.width = this.progressBack.getMeasuredWidth();
        int measuredWidth = this.circle.getMeasuredWidth();
        this.cWidth = measuredWidth;
        this.dWidth = ((float) (this.width - measuredWidth)) / ((float) this.distance);
        boolean z = this.isMax;
        if (z) {
            setProgress(-1.0f, z);
        } else {
            setProgress(0.0f, z);
        }
        post(new Runnable() {
            public void run() {
                SingleCloseProgressBar.this.moveView((((float) (SingleCloseProgressBar.this.now - SingleCloseProgressBar.this.min)) * SingleCloseProgressBar.this.dWidth) + (((float) SingleCloseProgressBar.this.cWidth) / 2.0f));
                if (SingleCloseProgressBar.this.listener != null) {
                    SingleCloseProgressBar.this.listener.onChange(SingleCloseProgressBar.this.now);
                }
            }
        });
    }

    public boolean isChecked() {
        return this.f253s.isChecked();
    }
}
