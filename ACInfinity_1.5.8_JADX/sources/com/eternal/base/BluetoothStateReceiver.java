package com.eternal.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.KLog;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;

public class BluetoothStateReceiver extends BroadcastReceiver {
    public static String GPS_ACTION = "android.location.PROVIDERS_CHANGED";

    public void onReceive(Context context, Intent intent) {
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            if (intExtra == 10) {
                KLog.m74w("BLE----蓝牙已关闭");
                postEvent((byte) 3);
                RepositoryInjection.providerDeviceRepository().disableBle();
            } else if (intExtra == 12) {
                KLog.m74w("BLE----蓝牙已开启");
                postEvent((byte) 4);
            }
        } else if (GPS_ACTION.equals(intent.getAction())) {
            postEvent((byte) 9);
        }
    }

    private void postEvent(final byte b) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                KLog.m74w("BLE----发送通知");
                RxBus.getDefault().post(new BluetoothEvent(b, 0));
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
