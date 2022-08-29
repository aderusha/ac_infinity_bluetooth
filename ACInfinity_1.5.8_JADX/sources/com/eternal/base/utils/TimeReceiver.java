package com.eternal.base.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.Utils;

public class TimeReceiver extends BroadcastReceiver {
    private static TimeReceiver receiver;

    private TimeReceiver() {
    }

    public static void register() {
        if (receiver == null) {
            receiver = new TimeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        Utils.getContext().registerReceiver(receiver, intentFilter);
    }

    public static void unregister() {
        if (receiver != null) {
            try {
                Utils.getContext().unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            receiver = null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
            RxBus.getDefault().post(Long.valueOf(System.currentTimeMillis()));
        }
    }
}
