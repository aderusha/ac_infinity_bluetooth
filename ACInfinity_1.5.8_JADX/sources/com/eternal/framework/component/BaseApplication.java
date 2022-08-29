package com.eternal.framework.component;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.eternal.framework.utils.Utils;
import java.util.Objects;

public class BaseApplication extends Application {
    private static Application sInstance;

    public interface AppStateChangeListener {
        void appTurnIntoBackGround();

        void appTurnIntoForeground();
    }

    public void onCreate() {
        super.onCreate();
    }

    public static synchronized void setApplication(Application application, final AppStateChangeListener appStateChangeListener) {
        synchronized (BaseApplication.class) {
            sInstance = application;
            Utils.init(application);
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                private int resumeActivityCount = 0;

                public void onActivityPaused(Activity activity) {
                }

                public void onActivityResumed(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    AppManager.getAppManager().addActivity(activity);
                }

                public void onActivityStarted(Activity activity) {
                    AppStateChangeListener appStateChangeListener;
                    AppManager.getAppManager().addStart();
                    int i = this.resumeActivityCount;
                    this.resumeActivityCount = i + 1;
                    if (i == 0 && (appStateChangeListener = AppStateChangeListener.this) != null) {
                        appStateChangeListener.appTurnIntoForeground();
                    }
                }

                public void onActivityStopped(Activity activity) {
                    AppStateChangeListener appStateChangeListener;
                    AppManager.getAppManager().subStart();
                    int i = this.resumeActivityCount - 1;
                    this.resumeActivityCount = i;
                    if (i == 0 && (appStateChangeListener = AppStateChangeListener.this) != null) {
                        appStateChangeListener.appTurnIntoBackGround();
                    }
                }

                public void onActivityDestroyed(Activity activity) {
                    AppManager.getAppManager().removeActivity(activity);
                }
            });
        }
    }

    public static Application getInstance() {
        Application application = sInstance;
        Objects.requireNonNull(application, "please inherit BaseApplication or call setApplication.");
        return application;
    }
}
