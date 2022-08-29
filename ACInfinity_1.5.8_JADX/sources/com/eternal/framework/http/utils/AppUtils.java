package com.eternal.framework.http.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.eternal.framework.http.RxHttpUtils;
import java.util.UUID;

public class AppUtils {
    public static String getAppVersion() {
        try {
            return RxHttpUtils.getContext().getPackageManager().getPackageInfo(RxHttpUtils.getContext().getPackageName(), 16384).versionName;
        } catch (Exception unused) {
            return "0";
        }
    }

    public static String getUUID() {
        Context context = RxHttpUtils.getContext();
        String str = (String) SPUtils.get(context, "PHONE_UUID", "");
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            str = new UUID((long) Settings.Secure.getString(context.getContentResolver(), "android_id").hashCode(), (((long) telephonyManager.getDeviceId().hashCode()) << 32) | ((long) telephonyManager.getSimSerialNumber().hashCode())).toString();
            SPUtils.put(context, "PHONE_UUID", str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
