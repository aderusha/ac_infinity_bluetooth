package com.alibaba.android.arouter.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.alibaba.android.arouter.launcher.ARouter;

@Deprecated
public class AutowiredLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        ARouter.getInstance().inject(activity);
    }
}
