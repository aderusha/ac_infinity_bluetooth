package com.eternal.base.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.eternal.base.C1323R;
import com.google.android.material.snackbar.Snackbar;

public class CustomToastUtils {
    private static Toast receToast;

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, (CharSequence) str, -1).show();
    }

    public static void showCenterRoundRectToast(Context context, String str) {
        Toast toast = receToast;
        if (toast == null) {
            Toast toast2 = new Toast(context);
            receToast = toast2;
            toast2.setGravity(17, 0, 0);
            View inflate = LayoutInflater.from(context).inflate(C1323R.layout.layout_toast_round_rectang, (ViewGroup) null);
            ((TextView) inflate.findViewById(C1323R.C1326id.tv_toast_content)).setText(str);
            receToast.setView(inflate);
        } else {
            TextView textView = (TextView) toast.getView().findViewById(C1323R.C1326id.tv_toast_content);
            if (textView != null) {
                textView.setText(str);
            }
        }
        receToast.setDuration(0);
        receToast.show();
    }
}
