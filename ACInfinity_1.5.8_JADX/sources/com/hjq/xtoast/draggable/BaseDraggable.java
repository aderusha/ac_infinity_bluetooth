package com.hjq.xtoast.draggable;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import com.hjq.xtoast.XToast;

public abstract class BaseDraggable implements View.OnTouchListener {
    private View mRootView;
    private XToast mToast;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    public void start(XToast xToast) {
        this.mToast = xToast;
        this.mRootView = xToast.getView();
        this.mWindowManager = xToast.getWindowManager();
        this.mWindowParams = xToast.getWindowParams();
        this.mRootView.setOnTouchListener(this);
    }

    /* access modifiers changed from: protected */
    public XToast getXToast() {
        return this.mToast;
    }

    /* access modifiers changed from: protected */
    public WindowManager getWindowManager() {
        return this.mWindowManager;
    }

    /* access modifiers changed from: protected */
    public WindowManager.LayoutParams getWindowParams() {
        return this.mWindowParams;
    }

    /* access modifiers changed from: protected */
    public View getRootView() {
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public int getStatusBarHeight() {
        Rect rect = new Rect();
        getRootView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /* access modifiers changed from: protected */
    public void updateLocation(float f, float f2) {
        updateLocation((int) f, (int) f2);
    }

    /* access modifiers changed from: protected */
    public void updateLocation(int i, int i2) {
        if (this.mWindowParams.x != i || this.mWindowParams.y != i2) {
            this.mWindowParams.x = i;
            this.mWindowParams.y = i2;
            this.mWindowParams.gravity = 8388659;
            try {
                this.mWindowManager.updateViewLayout(this.mRootView, this.mWindowParams);
            } catch (IllegalArgumentException unused) {
            }
        }
    }
}
