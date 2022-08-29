package com.eternal.framework.http.utils;

import android.widget.Toast;
import com.eternal.framework.http.RxHttpUtils;

public class ToastUtils {
    private static Toast mToast;

    public static void showToast(String str) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(RxHttpUtils.getContext(), str, 0);
        } else {
            toast.setText(str);
        }
        mToast.show();
    }
}
