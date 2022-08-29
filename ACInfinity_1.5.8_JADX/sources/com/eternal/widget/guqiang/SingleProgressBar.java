package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;

public class SingleProgressBar extends ConstraintLayout {
    private int distance;
    private ImageView ivBar;
    private OnChangeListener listener;
    private byte mCurrentType;
    /* access modifiers changed from: private */
    public int min;
    /* access modifiers changed from: private */
    public int minDisplace;
    /* access modifiers changed from: private */
    public int now;
    /* access modifiers changed from: private */
    public float tempMinWidth;
    private TextView tvLeftMin;
    private TextView tvRightMax;
    private TextView tvShow;

    public interface OnChangeListener {
        void onChange(View view, int i);

        void onDown();

        void onEnd();
    }

    public static class Type {
        public static final byte CALIBRATION_HUMIDITY = 5;
        public static final byte CALIBRATION_TEMPERATURE_C = 4;
        public static final byte CALIBRATION_TEMPERATURE_F = 3;
        public static final byte CYCLE_OFF = 7;
        public static final byte CYCLE_ON = 6;
        public static final byte LEAF_TEMPERATURE_C = 11;
        public static final byte LEAF_TEMPERATURE_F = 10;
        public static final byte OFF = 9;

        /* renamed from: ON */
        public static final byte f254ON = 8;
        public static final byte TRANSITION_HUMIDITY = 2;
        public static final byte TRANSITION_TEMPERATURE_C = 1;
        public static final byte TRANSITION_TEMPERATURE_F = 0;
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.listener = onChangeListener;
    }

    public SingleProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SingleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_single_bar_progressbar, this);
        this.tvLeftMin = (TextView) inflate.findViewById(C2779R.C2782id.txt_min);
        this.tvRightMax = (TextView) inflate.findViewById(C2779R.C2782id.txt_max);
        this.tvShow = (TextView) inflate.findViewById(C2779R.C2782id.tv_show);
        this.ivBar = (ImageView) inflate.findViewById(C2779R.C2782id.iv_progress_bar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.SingleProgressBar);
        setModeType((byte) obtainStyledAttributes.getInt(C2779R.styleable.SingleProgressBar_modeType, 0), (byte) 0);
        obtainStyledAttributes.recycle();
    }

    private void setMinAndMax(String str, String str2, int i, int i2, int i3) {
        this.min = i;
        this.minDisplace = i3;
        this.distance = i2;
        this.tvLeftMin.setText(str);
        this.tvRightMax.setText(str2);
        this.tempMinWidth = ((((float) getWidth()) - ((float) this.ivBar.getWidth())) * ((float) i3)) / ((float) i2);
        moveView(this.ivBar.getTranslationX());
    }

    public void setModeType(byte b, byte b2) {
        this.mCurrentType = b;
        if (b == 0) {
            setMinAndMax("0℉", "+8℉", 0, 8, 2);
        } else if (b == 1) {
            setMinAndMax("0℃", "+4℃", 0, 4, 1);
        } else if (b == 2) {
            setMinAndMax("0%", "+8%", 0, 8, 1);
        } else if (b != 3) {
            if (b != 4) {
                if (b != 5) {
                    if (b == 10) {
                        setMinAndMax("-20℉", "+20℉", -20, 40, 2);
                    } else if (b != 11) {
                        setMinAndMax("0", "10", 0, 10, 1);
                    } else {
                        setMinAndMax("-10℃", "+10℃", -10, 20, 1);
                    }
                } else if (b2 != 0) {
                    setMinAndMax("-10%", "+10%", -10, 20, 1);
                } else {
                    setMinAndMax("-8%", "+8%", -8, 16, 1);
                }
            } else if (b2 != 0) {
                setMinAndMax("-10℃", "+10℃", -10, 20, 1);
            } else {
                setMinAndMax("-4℃", "+4℃", -4, 8, 1);
            }
        } else if (b2 != 0) {
            setMinAndMax("-20℉", "+20℉", -20, 40, 2);
        } else {
            setMinAndMax("-8℉", "+8℉", -8, 16, 2);
        }
    }

    public void setShowText(int i) {
        int i2 = this.min;
        int i3 = this.distance;
        if (i > i2 + i3) {
            i = i2 + i3;
        }
        if (i < i2) {
            i = i2;
        }
        if (this.now != i) {
            moveView((((float) (i - i2)) * this.tempMinWidth) / ((float) this.minDisplace));
            this.now = i;
            OnChangeListener onChangeListener = this.listener;
            if (onChangeListener != null) {
                onChangeListener.onChange(this, i);
            }
        }
    }

    public int getProgress() {
        return (((int) (this.ivBar.getTranslationX() / this.tempMinWidth)) * this.minDisplace) + this.min;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r0 != 3) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0031
            if (r0 == r2) goto L_0x0022
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r5 = 3
            if (r0 == r5) goto L_0x0029
            goto L_0x0046
        L_0x0011:
            float r5 = r5.getX()
            r4.moveView(r5)
            com.eternal.widget.guqiang.SingleProgressBar$OnChangeListener r5 = r4.listener
            if (r5 == 0) goto L_0x0046
            int r0 = r4.now
            r5.onChange(r4, r0)
            goto L_0x0046
        L_0x0022:
            com.eternal.widget.guqiang.SingleProgressBar$OnChangeListener r5 = r4.listener
            if (r5 == 0) goto L_0x0029
            r5.onEnd()
        L_0x0029:
            android.view.ViewParent r5 = r4.getParent()
            r5.requestDisallowInterceptTouchEvent(r1)
            goto L_0x0046
        L_0x0031:
            android.view.ViewParent r0 = r4.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            boolean r5 = r4.inRangeOfView(r5)
            if (r5 != 0) goto L_0x003f
            return r1
        L_0x003f:
            com.eternal.widget.guqiang.SingleProgressBar$OnChangeListener r5 = r4.listener
            if (r5 == 0) goto L_0x0046
            r5.onDown()
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.SingleProgressBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean inRangeOfView(MotionEvent motionEvent) {
        this.ivBar.getLocationOnScreen(new int[2]);
        float x = this.ivBar.getX();
        float y = this.ivBar.getY();
        return new Rect((int) (x - ((float) ConvertUtils.dp2px(30.0f))), (int) y, (int) (x + ((float) this.ivBar.getMaxWidth()) + ((float) ConvertUtils.dp2px(30.0f))), (int) (((float) this.ivBar.getMeasuredHeight()) + y)).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    /* access modifiers changed from: private */
    public void moveView(float f) {
        int width;
        int width2 = getWidth() - this.ivBar.getWidth();
        float f2 = 0.0f;
        if (f < 0.0f) {
            f = 0.0f;
        } else {
            float f3 = (float) width2;
            if (f > f3) {
                f = f3;
            }
        }
        int i = (((int) (f / this.tempMinWidth)) * this.minDisplace) + this.min;
        this.now = i;
        byte b = this.mCurrentType;
        if (b == 0) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
        } else if (b == 1) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
        } else if (b == 2) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_percent_num, getPlusNum(this.now)));
        } else if (b == 3) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
        } else if (b == 4) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
        } else if (b == 5) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_percent_num, getPlusNum(this.now)));
        } else if (b == 10) {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
        } else if (b != 11) {
            this.tvShow.setText(String.valueOf(i));
        } else {
            this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.tvShow.measure(makeMeasureSpec, makeMeasureSpec);
        float f4 = (((float) (this.now - this.min)) * this.tempMinWidth) / ((float) this.minDisplace);
        ImageView imageView = this.ivBar;
        float f5 = (float) width2;
        if (f4 > f5) {
            f4 = f5;
        }
        imageView.setTranslationX(f4);
        int measuredWidth = this.tvShow.getMeasuredWidth();
        float x = this.ivBar.getX();
        float width3 = (((float) (this.ivBar.getWidth() / 2)) + x) - ((float) (measuredWidth / 2));
        if (width3 >= 0.0f) {
            if (((float) measuredWidth) + width3 > ((float) getWidth())) {
                width = getWidth();
            } else if (x != 0.0f) {
                if (x == f5) {
                    width = getWidth();
                } else {
                    f2 = width3;
                }
            }
            f2 = (float) (width - measuredWidth);
        }
        this.tvShow.setTranslationX(f2);
    }

    private String getPlusNum(int i) {
        if (i <= 0) {
            return String.valueOf(i);
        }
        return "+" + i;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() {
            public void run() {
                SingleProgressBar singleProgressBar = SingleProgressBar.this;
                singleProgressBar.moveView((((float) (singleProgressBar.now - SingleProgressBar.this.min)) * SingleProgressBar.this.tempMinWidth) / ((float) SingleProgressBar.this.minDisplace));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.tempMinWidth = ((((float) View.MeasureSpec.getSize(i)) - ((float) this.ivBar.getWidth())) * ((float) this.minDisplace)) / ((float) this.distance);
    }
}
