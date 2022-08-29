package com.eternal.account;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import p019no.nordicsemi.android.dfu.DfuBaseService;

public class DfuService extends DfuBaseService {
    /* access modifiers changed from: protected */
    public boolean isDebug() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateForegroundNotification(NotificationCompat.Builder builder) {
    }

    /* access modifiers changed from: protected */
    public Class<? extends Activity> getNotificationTarget() {
        return NotificationActivity.class;
    }
}
