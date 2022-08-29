package com.eternal.widget.guqiang;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.widget.C2779R;
import p014io.reactivex.disposables.Disposable;

public class FanProgressBar extends ConstraintLayout {
    private Disposable addSubs;
    /* access modifiers changed from: private */
    public int cWidth;
    private ImageView circle;
    private ImageView circleBg;
    /* access modifiers changed from: private */
    public float dWidth;
    private int distance;
    private int interval;
    private Listener listener;
    private int marginStart;
    /* access modifiers changed from: private */
    public int min;
    private Disposable minusSubs;
    /* access modifiers changed from: private */
    public int now;
    private View progress;
    private View progressBack;

    /* renamed from: px */
    private float f244px;
    private TextView show;
    private TextView title;
    private int width;

    public interface Listener {
        void onChange(boolean z, int i);

        void onDown(boolean z);

        void onUp(boolean z, int i);
    }

    public void setTitle(String str) {
    }

    public FanProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public FanProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FanProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_fan_progress_bar, this);
        this.marginStart = getResources().getDimensionPixelSize(C2779R.dimen.dp_6);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.show = (TextView) inflate.findViewById(C2779R.C2782id.txt_show);
        this.circle = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle);
        this.circleBg = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle_bg);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
        this.progressBack = inflate.findViewById(C2779R.C2782id.v_progress_background);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            if (!inRangeOfView(motionEvent)) {
                return false;
            }
            this.circleBg.setVisibility(0);
            Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onDown(true);
            }
        } else if (action == 1) {
            this.circleBg.setVisibility(4);
            Listener listener3 = this.listener;
            if (listener3 != null) {
                listener3.onUp(true, this.now);
            }
        } else if (action == 2) {
            moveView(motionEvent.getX() - ((float) this.marginStart));
            Listener listener4 = this.listener;
            if (listener4 != null) {
                listener4.onChange(true, this.now);
            }
        } else if (action != 3) {
            return super.onTouchEvent(motionEvent);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return true;
    }

    public void setStyle(String str, String str2, String str3, int i, int i2) {
        setTitle(str);
        this.min = i;
        this.distance = i2;
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

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
        this.show.setText(String.valueOf(round));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.show.measure(makeMeasureSpec, makeMeasureSpec);
        this.circle.setTranslationX(getTx());
        this.circleBg.setTranslationX(this.circle.getTranslationX());
        int measuredWidth = this.show.getMeasuredWidth();
        float f5 = (float) measuredWidth;
        float x = (this.circle.getX() + (((float) this.cWidth) / 2.0f)) - (f5 / 2.0f);
        TextView textView = this.show;
        if (x >= 0.0f) {
            int i4 = this.width;
            f3 = f5 + x > ((float) i4) ? (float) (i4 - measuredWidth) : x;
        }
        textView.setTranslationX(f3);
        moveProgress(this.circle.getTranslationX());
    }

    private void moveProgress(float f) {
        this.progress.setTranslationX((float) this.marginStart);
        int i = (int) (f - ((float) this.marginStart));
        if (i <= 0) {
            i = 1;
        }
        ViewGroup.LayoutParams layoutParams = this.progress.getLayoutParams();
        layoutParams.width = i;
        this.progress.setLayoutParams(layoutParams);
    }

    public float getTx() {
        return (((float) (this.now - this.min)) * this.dWidth) + ((float) this.marginStart);
    }

    public void setProgress(int i) {
        moveView((((float) (i - this.min)) * this.dWidth) + (((float) this.cWidth) / 2.0f));
        this.now = i;
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(false, i);
        }
    }

    private boolean inRangeOfView(MotionEvent motionEvent) {
        this.circle.getLocationOnScreen(new int[2]);
        float x = this.circle.getX();
        return motionEvent.getY() >= 0.0f && motionEvent.getY() <= this.circle.getY() + ((float) this.circle.getMeasuredHeight()) && motionEvent.getX() >= x - ((float) ConvertUtils.dp2px(30.0f)) && motionEvent.getX() <= (((float) this.circle.getMeasuredWidth()) + x) + ((float) ConvertUtils.dp2px(30.0f));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int measuredWidth = this.progressBack.getMeasuredWidth();
        if (measuredWidth != 0) {
            this.width = measuredWidth;
        }
        int measuredWidth2 = this.circle.getMeasuredWidth();
        if (measuredWidth2 != 0) {
            this.cWidth = measuredWidth2;
        }
        this.dWidth = ((float) (this.width - this.cWidth)) / ((float) this.distance);
        post(new Runnable() {
            public void run() {
                FanProgressBar fanProgressBar = FanProgressBar.this;
                fanProgressBar.moveView((((float) (fanProgressBar.now - FanProgressBar.this.min)) * FanProgressBar.this.dWidth) + (((float) FanProgressBar.this.cWidth) / 2.0f));
            }
        });
    }
}
