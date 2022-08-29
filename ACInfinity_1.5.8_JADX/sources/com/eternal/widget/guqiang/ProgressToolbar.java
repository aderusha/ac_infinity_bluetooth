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

public class ProgressToolbar extends ConstraintLayout {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public float f246a;
    private ValueAnimator animator;
    private ImageView back;
    /* access modifiers changed from: private */
    public long last;
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

    public ProgressToolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public ProgressToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.progress_toolbar_layout, this);
        this.back = (ImageView) inflate.findViewById(C2779R.C2782id.img_back);
        this.next = (ImageView) inflate.findViewById(C2779R.C2782id.img_next);
        this.secondNext = (ImageView) inflate.findViewById(C2779R.C2782id.img_second_next);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.llTitle = (LinearLayout) inflate.findViewById(C2779R.C2782id.ll_title);
        this.subtitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_subtitle);
        this.nextTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_next);
        this.progress = inflate.findViewById(C2779R.C2782id.v_progress);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0});
        this.animator = ofInt;
        ofInt.setDuration(1000);
        this.animator.setRepeatCount(-1);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis <= ProgressToolbar.this.nextTime) {
                    ProgressToolbar progressToolbar = ProgressToolbar.this;
                    progressToolbar.setProgress(((progressToolbar.f246a * ((float) (currentTimeMillis - ProgressToolbar.this.last))) / ((float) (ProgressToolbar.this.nextTime - ProgressToolbar.this.last))) + (ProgressToolbar.this.target - ProgressToolbar.this.f246a));
                } else if (ProgressToolbar.this.percent != ProgressToolbar.this.target) {
                    ProgressToolbar progressToolbar2 = ProgressToolbar.this;
                    progressToolbar2.setProgress(progressToolbar2.target);
                }
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.ProgressToolbar);
            this.back.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressToolbar_backRes));
            this.next.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressToolbar_nextRes));
            this.secondNext.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.ProgressToolbar_secondNextRes));
            this.nextTitle.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressToolbar_nextTitle));
            this.title.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressToolbar_title));
            this.subtitle.setText(obtainStyledAttributes.getText(C2779R.styleable.ProgressToolbar_subtitle));
            obtainStyledAttributes.recycle();
        }
    }

    public void setTitle(String str) {
        this.title.setText(str);
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
            this.f246a = f3;
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
            this.f246a = f;
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
                        ProgressToolbar.this.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        ProgressToolbar.this.setProgress(0.0f);
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
