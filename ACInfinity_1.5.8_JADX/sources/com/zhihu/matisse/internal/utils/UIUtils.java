package com.zhihu.matisse.internal.utils;

import android.content.Context;

public class UIUtils {
    public static int spanCount(Context context, int i) {
        int round = Math.round(((float) context.getResources().getDisplayMetrics().widthPixels) / ((float) i));
        if (round == 0) {
            return 1;
        }
        return round;
    }
}
