package com.eternal.widget.guqiang;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.widget.C2779R;

public class RangeSeekBar extends ConstraintLayout {
    private int cWidth;
    private float dWidth;
    private int distance;
    int downType;
    private Factory factory;
    private int high;
    private ImageView highCircle;
    private View highProgress;
    private TextView highShow;
    private boolean highSwitch;
    private int interval;
    private Listener listener;
    private int low;
    private ImageView lowCircle;
    private View lowProgress;
    private TextView lowShow;
    private boolean lowSwitch;
    private int min;
    private View progressBack;

    /* renamed from: px */
    private float f247px;
    private String unit;
    private int width;

    public interface Factory {
        String getText(int i);
    }

    public interface Listener {
        void onChange(boolean z, boolean z2, int i, int i2);

        void onSwitchChange(boolean z, boolean z2);
    }

    public void setTitle(String str) {
    }

    public RangeSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public RangeSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RangeSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_range_seek_bar, this);
        this.lowShow = (TextView) inflate.findViewById(C2779R.C2782id.txt_low_show);
        this.lowCircle = (ImageView) inflate.findViewById(C2779R.C2782id.img_low_circle);
        this.lowProgress = inflate.findViewById(C2779R.C2782id.v_low_progress);
        this.highShow = (TextView) inflate.findViewById(C2779R.C2782id.txt_high_show);
        this.highCircle = (ImageView) inflate.findViewById(C2779R.C2782id.img_high_circle);
        this.highProgress = inflate.findViewById(C2779R.C2782id.v_high_progress);
        this.progressBack = inflate.findViewById(C2779R.C2782id.v_progress_background);
    }

    public void setFactory(Factory factory2) {
        this.factory = factory2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = false;
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            int inRangeOfView = inRangeOfView(motionEvent, this.lowCircle, this.highCircle);
            this.downType = inRangeOfView;
            if (inRangeOfView == 0) {
                return false;
            }
        } else if (action != 1) {
            if (action == 2) {
                float x = motionEvent.getX();
                int i = this.width;
                int i2 = this.cWidth;
                int i3 = i - i2;
                float f = x - (((float) i2) / 2.0f);
                if (f < 0.0f) {
                    f = 0.0f;
                } else {
                    float f2 = (float) i3;
                    if (f > f2) {
                        f = f2;
                    }
                }
                int round = Math.round((f / this.dWidth) + ((float) this.min));
                if (this.downType == 1) {
                    this.low = round;
                    if (!this.lowSwitch) {
                        setLowSwitch(true);
                        Listener listener2 = this.listener;
                        if (listener2 != null) {
                            listener2.onSwitchChange(this.lowSwitch, this.highSwitch);
                        }
                    } else {
                        moveLowView();
                    }
                } else {
                    this.high = round;
                    if (!this.highSwitch) {
                        setHighSwitch(true);
                        Listener listener3 = this.listener;
                        if (listener3 != null) {
                            listener3.onSwitchChange(this.lowSwitch, this.highSwitch);
                        }
                    } else {
                        moveHighView();
                    }
                }
                Listener listener4 = this.listener;
                if (listener4 != null) {
                    if (this.downType == 1) {
                        z = true;
                    }
                    listener4.onChange(true, z, this.low, this.high);
                }
            } else if (action != 3) {
                return super.onTouchEvent(motionEvent);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return true;
    }

    public void setStyle(String str, String str2, String str3, int i, int i2) {
        setTitle(str);
        this.min = i;
        this.distance = i2;
    }

    public void setLow(int i) {
        int i2 = this.min;
        if (i < i2) {
            i = i2;
        }
        this.low = i;
        moveLowView();
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(false, false, this.low, this.high);
        }
    }

    public void setHigh(int i) {
        int i2 = this.min;
        if (i < i2) {
            i = i2;
        }
        this.high = i;
        moveHighView();
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(false, false, this.low, this.high);
        }
    }

    public void setProgress(int i, int i2) {
        setHigh(i);
        setLow(i2);
    }

    public void setHighSwitch(boolean z) {
        this.highSwitch = z;
        moveHighView();
        if (z) {
            this.highShow.setVisibility(0);
        } else {
            this.highShow.setVisibility(4);
        }
    }

    public void setLowSwitch(boolean z) {
        this.lowSwitch = z;
        moveLowView();
        if (z) {
            this.lowShow.setVisibility(0);
        } else {
            this.lowShow.setVisibility(4);
        }
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.min + this.distance;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public void moveLowView() {
        int i = this.lowSwitch ? this.low : this.min;
        Factory factory2 = this.factory;
        if (factory2 != null) {
            this.lowShow.setText(factory2.getText(i));
        } else {
            this.lowShow.setText(String.format(this.unit, new Object[]{Integer.valueOf(i)}));
        }
        this.lowCircle.setTranslationX(getTx(i));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.lowShow.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.lowShow.getMeasuredWidth();
        float f = (float) measuredWidth;
        float x = (this.lowCircle.getX() + (((float) this.cWidth) / 2.0f)) - (f / 2.0f);
        TextView textView = this.lowShow;
        if (x < 0.0f) {
            x = 0.0f;
        } else {
            int i2 = this.width;
            if (f + x > ((float) i2)) {
                x = (float) (i2 - measuredWidth);
            }
        }
        textView.setTranslationX(x);
        moveProgress();
        adjustShowPosition();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void moveProgress() {
        /*
            r7 = this;
            boolean r0 = r7.highSwitch
            r1 = 0
            r2 = 0
            r3 = 1073741824(0x40000000, float:2.0)
            if (r0 == 0) goto L_0x0059
            boolean r4 = r7.lowSwitch
            if (r4 == 0) goto L_0x0059
            int r0 = r7.high
            int r4 = r7.low
            if (r0 > r4) goto L_0x0033
            android.widget.ImageView r0 = r7.highCircle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r4 = r7.highCircle
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r4 = r4 / r3
            float r0 = r0 + r4
            android.widget.ImageView r4 = r7.lowCircle
            float r4 = r4.getTranslationX()
            android.widget.ImageView r5 = r7.lowCircle
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r5 = r5 / r3
            float r4 = r4 + r5
            float r4 = r4 - r0
            int r3 = (int) r4
            goto L_0x006f
        L_0x0033:
            android.widget.ImageView r0 = r7.highCircle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r4 = r7.highCircle
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r4 = r4 / r3
            float r0 = r0 + r4
            int r4 = r7.width
            float r4 = (float) r4
            float r4 = r4 - r0
            int r4 = (int) r4
            android.widget.ImageView r5 = r7.lowCircle
            float r5 = r5.getTranslationX()
            android.widget.ImageView r6 = r7.lowCircle
            int r6 = r6.getWidth()
            float r6 = (float) r6
            float r6 = r6 / r3
            float r5 = r5 + r6
            float r5 = r5 - r1
            int r3 = (int) r5
            goto L_0x008c
        L_0x0059:
            if (r0 == 0) goto L_0x0072
            android.widget.ImageView r0 = r7.highCircle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r4 = r7.highCircle
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r4 = r4 / r3
            float r0 = r0 + r4
            int r3 = r7.width
            float r3 = (float) r3
            float r3 = r3 - r0
            int r3 = (int) r3
        L_0x006f:
            r4 = r3
            r3 = 0
            goto L_0x008c
        L_0x0072:
            boolean r0 = r7.lowSwitch
            if (r0 == 0) goto L_0x0089
            android.widget.ImageView r0 = r7.lowCircle
            float r0 = r0.getTranslationX()
            android.widget.ImageView r4 = r7.lowCircle
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r4 = r4 / r3
            float r0 = r0 + r4
            float r0 = r0 - r1
            int r3 = (int) r0
            r0 = 0
            goto L_0x008b
        L_0x0089:
            r0 = 0
            r3 = 0
        L_0x008b:
            r4 = 0
        L_0x008c:
            android.view.View r5 = r7.lowProgress
            r5.setTranslationX(r1)
            android.view.View r1 = r7.lowProgress
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            r1.width = r3
            android.view.View r5 = r7.lowProgress
            r5.setLayoutParams(r1)
            android.view.View r1 = r7.lowProgress
            r5 = 4
            if (r3 > 0) goto L_0x00a5
            r3 = 4
            goto L_0x00a6
        L_0x00a5:
            r3 = 0
        L_0x00a6:
            r1.setVisibility(r3)
            android.view.View r1 = r7.highProgress
            r1.setTranslationX(r0)
            android.view.View r0 = r7.highProgress
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            r0.width = r4
            android.view.View r1 = r7.highProgress
            r1.setLayoutParams(r0)
            android.view.View r0 = r7.highProgress
            if (r4 > 0) goto L_0x00c0
            r2 = 4
        L_0x00c0:
            r0.setVisibility(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.RangeSeekBar.moveProgress():void");
    }

    public void moveHighView() {
        int i = this.highSwitch ? this.high : this.min + this.distance;
        Factory factory2 = this.factory;
        if (factory2 != null) {
            this.highShow.setText(factory2.getText(i));
        } else {
            this.highShow.setText(String.format(this.unit, new Object[]{Integer.valueOf(i)}));
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.highShow.measure(makeMeasureSpec, makeMeasureSpec);
        this.highCircle.setTranslationX(getTx(i));
        int measuredWidth = this.highShow.getMeasuredWidth();
        float f = (float) measuredWidth;
        float x = (this.highCircle.getX() + (((float) this.cWidth) / 2.0f)) - (f / 2.0f);
        TextView textView = this.highShow;
        if (x < 0.0f) {
            x = 0.0f;
        } else {
            int i2 = this.width;
            if (f + x > ((float) i2)) {
                x = (float) (i2 - measuredWidth);
            }
        }
        textView.setTranslationX(x);
        moveProgress();
        adjustShowPosition();
    }

    private void adjustShowPosition() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.lowShow.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.lowShow.getMeasuredWidth();
        float x = this.lowCircle.getX();
        float f = (float) measuredWidth;
        float f2 = ((((float) this.cWidth) / 2.0f) + x) - (f / 2.0f);
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
        this.highShow.measure(makeMeasureSpec2, makeMeasureSpec2);
        int measuredWidth2 = this.highShow.getMeasuredWidth();
        float x2 = this.highCircle.getX();
        float f4 = (float) measuredWidth2;
        float f5 = ((((float) this.cWidth) / 2.0f) + x2) - (f4 / 2.0f);
        if (f5 < 0.0f) {
            f5 = 0.0f;
        } else {
            int i2 = this.width;
            if (f5 + f4 > ((float) i2)) {
                f5 = (float) (i2 - measuredWidth2);
            }
        }
        if (this.lowSwitch && this.highSwitch) {
            float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C2779R.dimen.dp_3);
            float f6 = ((float) (measuredWidth + measuredWidth2)) + dimensionPixelSize;
            if (x > x2) {
                if (f5 + f4 + dimensionPixelSize > f2) {
                    float width2 = (((x2 + ((float) this.highCircle.getWidth())) + x) - f6) / 2.0f;
                    if (width2 >= 0.0f) {
                        int i3 = this.width;
                        f3 = width2 + f6 > ((float) i3) ? ((float) i3) - f6 : width2;
                    }
                    f2 = f4 + f3 + dimensionPixelSize;
                    f5 = f3;
                }
            } else if (f2 + f + dimensionPixelSize > f5) {
                float width3 = (((x + ((float) this.highCircle.getWidth())) + x2) - f6) / 2.0f;
                if (width3 >= 0.0f) {
                    int i4 = this.width;
                    f3 = width3 + f6 > ((float) i4) ? ((float) i4) - f6 : width3;
                }
                f5 = f + f3 + dimensionPixelSize;
                f2 = f3;
            }
        }
        this.highShow.setTranslationX(f5);
        this.lowShow.setTranslationX(f2);
    }

    public float getTx(int i) {
        return ((float) (i - this.min)) * this.dWidth;
    }

    private int inRangeOfView(MotionEvent motionEvent, View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        float x = view.getX();
        boolean z = motionEvent.getY() >= 0.0f && motionEvent.getY() <= view.getY() + ((float) view.getMeasuredHeight()) && motionEvent.getX() >= x - ((float) ConvertUtils.dp2px(30.0f)) && motionEvent.getX() <= (((float) view.getMeasuredWidth()) + x) + ((float) ConvertUtils.dp2px(30.0f));
        view2.getLocationOnScreen(iArr);
        float x2 = view2.getX();
        boolean z2 = motionEvent.getY() >= 0.0f && motionEvent.getY() <= view2.getY() + ((float) view2.getMeasuredHeight()) && motionEvent.getX() >= x2 - ((float) ConvertUtils.dp2px(30.0f)) && motionEvent.getX() <= (((float) view2.getMeasuredWidth()) + x2) + ((float) ConvertUtils.dp2px(30.0f));
        if (z && z2) {
            if (Math.abs(motionEvent.getX() - (x + (((float) view.getMeasuredWidth()) / 2.0f))) < Math.abs(motionEvent.getX() - (x2 + (((float) view2.getMeasuredWidth()) / 2.0f)))) {
                return 1;
            }
            return 2;
        } else if (!z) {
            if (z2) {
                return 2;
            }
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.width = this.progressBack.getMeasuredWidth();
        int measuredWidth = this.lowCircle.getMeasuredWidth();
        this.cWidth = measuredWidth;
        this.dWidth = ((float) (this.width - measuredWidth)) / ((float) this.distance);
        post(new Runnable() {
            public void run() {
                RangeSeekBar.this.moveLowView();
                RangeSeekBar.this.moveHighView();
            }
        });
    }
}
