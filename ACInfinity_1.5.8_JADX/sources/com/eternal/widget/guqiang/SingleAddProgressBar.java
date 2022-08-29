package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.IEffectBar;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class SingleAddProgressBar extends ConstraintLayout implements IEffectBar {
    private Disposable addSubs;
    /* access modifiers changed from: private */
    public int cWidth;
    private ImageView circle;
    /* access modifiers changed from: private */
    public float dWidth;
    private int distance;
    private IEffectBar.Factory factory;
    private boolean fillWhenEqual;
    private int interval;
    private boolean isMax;
    /* access modifiers changed from: private */
    public IEffectBar.Listener listener;
    private TextView maxTitle;
    /* access modifiers changed from: private */
    public int min;
    private TextView minTitle;
    private Disposable minusSubs;
    /* access modifiers changed from: private */
    public int now;
    private View progress;
    private View progressBack;

    /* renamed from: px */
    private float f248px;
    private TextView show;
    private TextView title;
    private int width;

    public boolean isChecked() {
        return true;
    }

    public SingleAddProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SingleAddProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleAddProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_single_add_progress_bar, this);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.minTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_min);
        this.maxTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_max);
        this.show = (TextView) inflate.findViewById(C2779R.C2782id.txt_show);
        this.circle = (ImageView) inflate.findViewById(C2779R.C2782id.img_circle);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
        this.progressBack = inflate.findViewById(C2779R.C2782id.v_progress_background);
        this.addSubs = Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                SingleAddProgressBar.this.findViewById(C2779R.C2782id.img_add).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (SingleAddProgressBar.this.isChecked()) {
                            SingleAddProgressBar.this.moveView((((float) ((SingleAddProgressBar.this.now - SingleAddProgressBar.this.min) + 1)) * SingleAddProgressBar.this.dWidth) + (((float) SingleAddProgressBar.this.cWidth) / 2.0f));
                            if (SingleAddProgressBar.this.listener != null) {
                                SingleAddProgressBar.this.listener.onDown(false);
                                SingleAddProgressBar.this.listener.onChange(SingleAddProgressBar.this.now);
                            }
                            observableEmitter.onNext(view);
                        }
                    }
                });
            }
        }).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                if (SingleAddProgressBar.this.listener != null) {
                    SingleAddProgressBar.this.listener.onUp(false, SingleAddProgressBar.this.now);
                }
            }
        });
        this.minusSubs = Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                SingleAddProgressBar.this.findViewById(C2779R.C2782id.img_minus).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (SingleAddProgressBar.this.isChecked()) {
                            observableEmitter.onNext(this);
                            SingleAddProgressBar.this.moveView((((float) ((SingleAddProgressBar.this.now - SingleAddProgressBar.this.min) - 1)) * SingleAddProgressBar.this.dWidth) + (((float) SingleAddProgressBar.this.cWidth) / 2.0f));
                            if (SingleAddProgressBar.this.listener != null) {
                                SingleAddProgressBar.this.listener.onDown(false);
                                SingleAddProgressBar.this.listener.onChange(SingleAddProgressBar.this.now);
                            }
                        }
                    }
                });
            }
        }).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                SingleAddProgressBar.this.listener.onUp(false, SingleAddProgressBar.this.now);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.SingleAddProgressBar);
        this.title.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleAddProgressBar_title));
        this.minTitle.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleAddProgressBar_minTitle));
        this.maxTitle.setText(obtainStyledAttributes.getString(C2779R.styleable.SingleAddProgressBar_maxTitle));
        this.distance = obtainStyledAttributes.getInt(C2779R.styleable.SingleAddProgressBar_distance, 0);
        this.min = obtainStyledAttributes.getInt(C2779R.styleable.SingleAddProgressBar_minNum, 0);
        this.interval = obtainStyledAttributes.getInt(C2779R.styleable.SingleAddProgressBar_interval, 1);
        this.isMax = obtainStyledAttributes.getBoolean(C2779R.styleable.SingleAddProgressBar_isMax, false);
        if (obtainStyledAttributes.getBoolean(C2779R.styleable.SingleAddProgressBar_noAdd, false)) {
            findViewById(C2779R.C2782id.layout_btn).setVisibility(8);
        }
        obtainStyledAttributes.recycle();
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setFillWhenEqual(boolean z) {
        this.fillWhenEqual = z;
    }

    public void setListener(IEffectBar.Listener listener2) {
        this.listener = listener2;
    }

    private boolean inRangeOfView(MotionEvent motionEvent) {
        this.circle.getLocationOnScreen(new int[2]);
        float x = this.circle.getX();
        return motionEvent.getY() >= 0.0f && motionEvent.getY() <= this.circle.getY() + ((float) this.circle.getMeasuredHeight()) && motionEvent.getX() >= x - ((float) ConvertUtils.dp2px(30.0f)) && motionEvent.getX() <= (((float) this.circle.getMeasuredWidth()) + x) + ((float) ConvertUtils.dp2px(30.0f));
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
        this.dWidth = ((float) (this.width - this.cWidth)) / ((float) i2);
    }

    public void setFactory(IEffectBar.Factory factory2) {
        this.factory = factory2;
        this.show.setText(factory2.getText((int) (((this.circle.getTranslationX() * ((float) this.distance)) / ((float) (this.width - this.cWidth))) + ((float) this.min))));
    }

    public void setProgress(float f, boolean z) {
        if (f == -1.0f) {
            this.f248px = (float) (this.width - this.cWidth);
        } else {
            this.f248px = f;
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
        if (!this.fillWhenEqual && Math.round((this.f248px / this.dWidth) + ((float) this.min)) == this.now) {
            this.progress.setTranslationX(this.circle.getTranslationX());
            ViewGroup.LayoutParams layoutParams = this.progress.getLayoutParams();
            layoutParams.width = 1;
            this.progress.setLayoutParams(layoutParams);
        } else if (this.isMax) {
            float f3 = this.f248px;
            if (f > f3 || (this.fillWhenEqual && f == f3)) {
                f2 = ((float) this.width) - f;
            } else {
                f2 = (f3 - f) + ((float) this.cWidth);
            }
            float f4 = (float) ((int) f2);
            if (f4 == 0.0f) {
                f4 = ((float) this.width) - this.circle.getTranslationX();
            }
            this.progress.setTranslationX(this.circle.getTranslationX());
            ViewGroup.LayoutParams layoutParams2 = this.progress.getLayoutParams();
            layoutParams2.width = (int) f4;
            this.progress.setLayoutParams(layoutParams2);
        } else {
            float f5 = this.f248px;
            if (f <= f5 || (this.fillWhenEqual && ((double) f) <= Math.ceil((double) f5))) {
                this.progress.setTranslationX(0.0f);
            } else {
                float f6 = this.f248px;
                f -= f6;
                this.progress.setTranslationX(f6);
            }
            float f7 = (float) ((int) f);
            if (f7 == 0.0f) {
                f7 = 1.0f;
            }
            ViewGroup.LayoutParams layoutParams3 = this.progress.getLayoutParams();
            layoutParams3.width = (int) f7;
            this.progress.setLayoutParams(layoutParams3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r0 != 3) goto L_0x006e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0059
            if (r0 == r2) goto L_0x0048
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r5 = 3
            if (r0 == r5) goto L_0x0051
            goto L_0x006e
        L_0x0011:
            float r5 = r5.getX()
            int r0 = r4.distance
            r1 = 1440(0x5a0, float:2.018E-42)
            if (r0 != r1) goto L_0x003b
            float r0 = r4.dWidth
            float r5 = r5 / r0
            int r0 = r4.min
            float r0 = (float) r0
            float r5 = r5 + r0
            int r5 = java.lang.Math.round(r5)
            int r5 = r5 / 15
            int r5 = r5 * 15
            r4.now = r5
            int r0 = r4.min
            int r5 = r5 - r0
            float r5 = (float) r5
            float r0 = r4.dWidth
            float r5 = r5 * r0
            int r0 = r4.cWidth
            float r0 = (float) r0
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            float r5 = r5 + r0
        L_0x003b:
            r4.moveView(r5)
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x006e
            int r0 = r4.now
            r5.onChange(r0)
            goto L_0x006e
        L_0x0048:
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x0051
            int r0 = r4.now
            r5.onUp(r2, r0)
        L_0x0051:
            android.view.ViewParent r5 = r4.getParent()
            r5.requestDisallowInterceptTouchEvent(r1)
            goto L_0x006e
        L_0x0059:
            android.view.ViewParent r0 = r4.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            boolean r5 = r4.inRangeOfView(r5)
            if (r5 != 0) goto L_0x0067
            return r1
        L_0x0067:
            com.eternal.widget.guqiang.IEffectBar$Listener r5 = r4.listener
            if (r5 == 0) goto L_0x006e
            r5.onDown(r2)
        L_0x006e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.SingleAddProgressBar.onTouchEvent(android.view.MotionEvent):boolean");
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
                SingleAddProgressBar singleAddProgressBar = SingleAddProgressBar.this;
                singleAddProgressBar.moveView((((float) (singleAddProgressBar.now - SingleAddProgressBar.this.min)) * SingleAddProgressBar.this.dWidth) + (((float) SingleAddProgressBar.this.cWidth) / 2.0f));
                if (SingleAddProgressBar.this.listener != null) {
                    SingleAddProgressBar.this.listener.onChange(SingleAddProgressBar.this.now);
                }
            }
        });
    }
}
