package com.eternal.widget.pinview;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

class DefaultMovementMethod implements MovementMethod {
    private static DefaultMovementMethod sInstance;

    public boolean canSelectArbitrarily() {
        return false;
    }

    public boolean onGenericMotionEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        return false;
    }

    public boolean onKeyDown(TextView textView, Spannable spannable, int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyOther(TextView textView, Spannable spannable, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(TextView textView, Spannable spannable, int i, KeyEvent keyEvent) {
        return false;
    }

    public void onTakeFocus(TextView textView, Spannable spannable, int i) {
    }

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        return false;
    }

    public boolean onTrackballEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        return false;
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new DefaultMovementMethod();
        }
        return sInstance;
    }

    private DefaultMovementMethod() {
    }

    public void initialize(TextView textView, Spannable spannable) {
        Selection.setSelection(spannable, 0);
    }
}
