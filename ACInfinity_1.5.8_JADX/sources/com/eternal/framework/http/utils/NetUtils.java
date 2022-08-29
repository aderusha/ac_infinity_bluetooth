package com.eternal.framework.http.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.eternal.framework.http.RxHttpUtils;

public class NetUtils {
    public static boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo;
        Context context = RxHttpUtils.getContext();
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }
}
