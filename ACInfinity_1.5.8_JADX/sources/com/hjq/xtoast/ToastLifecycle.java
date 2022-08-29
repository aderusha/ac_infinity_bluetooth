package com.hjq.xtoast;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class ToastLifecycle implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private XToast mToast;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    ToastLifecycle(XToast xToast, Activity activity) {
        this.mActivity = activity;
        this.mToast = xToast;
    }

    /* access modifiers changed from: package-private */
    public void register() {
        this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    /* access modifiers changed from: package-private */
    public void unregister() {
        this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }

    public void onActivityPaused(Activity activity) {
        XToast xToast;
        Activity activity2 = this.mActivity;
        if (activity2 != null && (xToast = this.mToast) != null && activity2 == activity && xToast.isShow() && this.mActivity.isFinishing()) {
            this.mToast.cancel();
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mActivity == activity) {
            this.mActivity = null;
            XToast xToast = this.mToast;
            if (xToast != null) {
                xToast.recycle();
                this.mToast = null;
            }
        }
    }
}
