package com.eternal.base;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.alibaba.android.arouter.launcher.ARouter;
import com.clj.fastble.BleManager;
import com.eternal.base.global.ActivityEvent;
import com.eternal.framework.utils.KLog;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.plugins.RxJavaPlugins;

public class BaseModuleInit implements IModuleInit {
    public boolean initLow(Application application) {
        return false;
    }

    public boolean initAhead(Application application) {
        KLog.init(false);
        ARouter.init(application);
        BleManager.getInstance().init(application);
        BleManager.getInstance().setReConnectCount(0);
        initChannel(application);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                KLog.m65e(th);
            }
        });
        KLog.m68i("base library init");
        return false;
    }

    private void initChannel(Application application) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(ActivityEvent.NOTIFICATION, ActivityEvent.NOTIFICATION, 4);
            notificationChannel.setShowBadge(true);
            ((NotificationManager) application.getSystemService(ActivityEvent.NOTIFICATION)).createNotificationChannel(notificationChannel);
        }
    }
}
