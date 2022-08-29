package com.hjq.xtoast.draggable;

import android.animation.ValueAnimator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class SpringDraggable extends BaseDraggable {
    private float mViewDownX;
    private float mViewDownY;

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mViewDownX = (float) ((int) motionEvent.getX());
            this.mViewDownY = (float) ((int) motionEvent.getY());
        } else if (action == 1) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) (motionEvent.getRawY() - ((float) getStatusBarHeight()));
            int screenWidth = getScreenWidth();
            float f = rawX < screenWidth / 2 ? 0.0f : (float) screenWidth;
            float f2 = this.mViewDownX;
            startAnimation(((float) rawX) - f2, f - f2, ((float) rawY) - this.mViewDownY);
            if (this.mViewDownX == ((float) ((int) motionEvent.getX())) && this.mViewDownY == ((float) ((int) motionEvent.getY()))) {
                return false;
            }
            return true;
        } else if (action == 2) {
            updateLocation(((float) ((int) motionEvent.getRawX())) - this.mViewDownX, ((float) ((int) (motionEvent.getRawY() - ((float) getStatusBarHeight())))) - this.mViewDownY);
        }
        return false;
    }

    private int getScreenWidth() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void startAnimation(float f, float f2, final float f3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        ofFloat.setDuration(500);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SpringDraggable.this.updateLocation(((Float) valueAnimator.getAnimatedValue()).floatValue(), f3);
            }
        });
        ofFloat.start();
    }
}
