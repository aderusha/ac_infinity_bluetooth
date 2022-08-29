package com.eternal.widget.guqiang;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.widget.C2779R;
import com.google.android.material.tabs.TabLayout;

public class ProgressTabToolbar extends ConstraintLayout {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public float f245a;
    /* access modifiers changed from: private */
    public ValueAnimator animator;
    private ImageView back;
    /* access modifiers changed from: private */
    public long last;
    private TabLayout layout;
    private LinearLayout llTitle;
    private ImageView next;
    /* access modifiers changed from: private */
    public long nextTime;
    private TextView nextTitle;
    /* access modifiers changed from: private */
    public float percent;
    private View progress;
    private ImageView secondNext;
    private TextView subtitle;
    /* access modifiers changed from: private */
    public float target;
    private TextView title;
    private TextView title2;

    public ProgressTabToolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public ProgressTabToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressTabToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.progress_tab_toolbar_layout, this);
        this.back = (ImageView) inflate.findViewById(C2779R.C2782id.img_back);
        this.next = (ImageView) inflate.findViewById(C2779R.C2782id.img_next);
        this.secondNext = (ImageView) inflate.findViewById(C2779R.C2782id.img_second_next);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.title2 = (TextView) inflate.findViewById(C2779R.C2782id.txt_title2);
        this.llTitle = (LinearLayout) inflate.findViewById(C2779R.C2782id.ll_title);
        this.subtitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_subtitle);
        this.nextTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_next);
        this.layout = (TabLayout) inflate.findViewById(C2779R.C2782id.tb_port);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0});
        this.animator = ofInt;
        ofInt.setDuration(1000);
        this.animator.setRepeatCount(-1);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis <= ProgressTabToolbar.this.nextTime) {
                    ProgressTabToolbar progressTabToolbar = ProgressTabToolbar.this;
                    progressTabToolbar.setProgress(((progressTabToolbar.f245a * ((float) (currentTimeMillis - ProgressTabToolbar.this.last))) / ((float) (ProgressTabToolbar.this.nextTime - ProgressTabToolbar.this.last))) + (ProgressTabToolbar.this.target - ProgressTabToolbar.this.f245a));
                    if (ProgressTabToolbar.this.percent == 100.0f) {
                        ProgressTabToolbar.this.animator.end();
                        ProgressTabToolbar.this.setProgress(0.0f);
                    }
                } else if (ProgressTabToolbar.this.percent != ProgressTabToolbar.this.target) {
                    ProgressTabToolbar progressTabToolbar2 = ProgressTabToolbar.this;
                    progressTabToolbar2.setProgress(progressTabToolbar2.target);
                }
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.ProgressTabToolbar);
            this.back.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressTabToolbar_backRes));
            this.next.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressTabToolbar_nextRes));
            this.secondNext.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressTabToolbar_secondNextRes));
            this.nextTitle.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressTabToolbar_nextTitle));
            this.title.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressTabToolbar_title));
            this.title2.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressTabToolbar_title));
            this.subtitle.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressTabToolbar_subtitle));
            setSingleTitle(obtainStyledAttributes.getBoolean(C2779R.styleable.ProgressTabToolbar_singleTitle, false));
            setShowTab(obtainStyledAttributes.getBoolean(C2779R.styleable.ProgressTabToolbar_showTab, false));
            obtainStyledAttributes.recycle();
        }
    }

    public void setSingleTitle(boolean z) {
        if (z) {
            this.llTitle.setVisibility(8);
            this.title2.setVisibility(0);
            return;
        }
        this.llTitle.setVisibility(0);
        this.title2.setVisibility(8);
    }

    public void setShowTab(boolean z) {
        if (z) {
            this.layout.setVisibility(0);
        } else {
            this.layout.setVisibility(8);
        }
    }

    public void setTitle(String str) {
        this.title.setText(str);
        this.title2.setText(str);
    }

    public void setSubtitle(String str) {
        this.subtitle.setText(str);
    }

    public ImageView getBack() {
        return this.back;
    }

    public View getNext() {
        return this.next.getDrawable() == null ? this.nextTitle : this.next;
    }

    public View getSecondNext() {
        return this.secondNext;
    }

    public void setAnimatorProgress(float f) {
        if (this.percent <= f) {
            float f2 = this.target;
            float f3 = (f - f2) * 1.5f;
            this.f245a = f3;
            this.target = f2 + f3;
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.last;
            this.nextTime = j + ((long) (((float) (currentTimeMillis - j)) * 1.5f));
            this.last = currentTimeMillis;
        }
    }

    public void startProgress() {
        if (!this.animator.isStarted()) {
            this.target = 0.0f;
            this.percent = 0.0f;
            this.animator.start();
            this.last = System.currentTimeMillis();
        }
    }

    public void initProgress(float f, int i) {
        if (this.target == 0.0f) {
            this.f245a = f;
            this.target = f;
            this.nextTime = this.last + ((long) i);
            this.last = System.currentTimeMillis();
        }
    }

    public void endProgress(final IProgressCallback iProgressCallback) {
        if (this.animator.isStarted()) {
            this.animator.end();
            float f = this.percent;
            if (f < 100.0f) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, 100.0f});
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ProgressTabToolbar.this.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        ProgressTabToolbar.this.setProgress(0.0f);
                        IProgressCallback iProgressCallback = iProgressCallback;
                        if (iProgressCallback != null) {
                            iProgressCallback.onEnd();
                        }
                    }
                });
                ofFloat.setDuration(500);
                ofFloat.start();
                return;
            }
            setProgress(0.0f);
            if (iProgressCallback != null) {
                iProgressCallback.onEnd();
            }
        }
    }

    public void setProgress(float f) {
        if (f != this.percent) {
            ViewGroup.LayoutParams layoutParams = this.progress.getLayoutParams();
            layoutParams.width = (int) ((((float) getWidth()) * f) / 100.0f);
            this.progress.setLayoutParams(layoutParams);
            this.percent = f;
        }
    }

    public void cancelProgress() {
        if (this.animator.isStarted()) {
            this.animator.end();
        }
        setProgress(0.0f);
    }
}
