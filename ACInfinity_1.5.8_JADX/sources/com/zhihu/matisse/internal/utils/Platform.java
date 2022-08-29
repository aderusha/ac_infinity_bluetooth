package com.zhihu.matisse.internal.utils;

import android.os.Build;

public class Platform {
    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= 19;
    }
}
