package com.eternal.base.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.LinkedList;
import java.util.List;

public class SoftKeyBroadManager implements ViewTreeObserver.OnGlobalLayoutListener {
    private final View activityRootView;
    private boolean isSoftKeyboardOpened;
    private int lastSoftKeyboardHeightInPx;
    private final List<SoftKeyboardStateListener> listeners;

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardClosed();

        void onSoftKeyboardOpened(int i);
    }

    public SoftKeyBroadManager(View view) {
        this(view, false);
    }

    public SoftKeyBroadManager(View view, boolean z) {
        this.listeners = new LinkedList();
        this.activityRootView = view;
        this.isSoftKeyboardOpened = z;
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.activityRootView.getWindowVisibleDisplayFrame(rect);
        int height = this.activityRootView.getRootView().getHeight() - (rect.bottom - rect.top);
        boolean z = this.isSoftKeyboardOpened;
        if (!z && height > 500) {
            this.isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(height);
        } else if (z && height < 500) {
            this.isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    public void setIsSoftKeyboardOpened(boolean z) {
        this.isSoftKeyboardOpened = z;
    }

    public boolean isSoftKeyboardOpened() {
        return this.isSoftKeyboardOpened;
    }

    public int getLastSoftKeyboardHeightInPx() {
        return this.lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener softKeyboardStateListener) {
        this.listeners.add(softKeyboardStateListener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener softKeyboardStateListener) {
        this.listeners.remove(softKeyboardStateListener);
    }

    private void notifyOnSoftKeyboardOpened(int i) {
        this.lastSoftKeyboardHeightInPx = i;
        for (SoftKeyboardStateListener next : this.listeners) {
            if (next != null) {
                next.onSoftKeyboardOpened(i);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener next : this.listeners) {
            if (next != null) {
                next.onSoftKeyboardClosed();
            }
        }
    }

    public static void openKeyboard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 0);
        }
    }

    public static void closeKeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static void toggleKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 0);
        }
    }
}
