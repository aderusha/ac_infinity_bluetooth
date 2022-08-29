package com.zhihu.matisse.internal.entity;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.zhihu.matisse.internal.p012ui.widget.IncapableDialog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class IncapableCause {
    public static final int DIALOG = 1;
    public static final int NONE = 2;
    public static final int TOAST = 0;
    private int mForm = 0;
    private String mMessage;
    private String mTitle;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Form {
    }

    public IncapableCause(String str) {
        this.mMessage = str;
    }

    public IncapableCause(String str, String str2) {
        this.mTitle = str;
        this.mMessage = str2;
    }

    public IncapableCause(int i, String str) {
        this.mForm = i;
        this.mMessage = str;
    }

    public IncapableCause(int i, String str, String str2) {
        this.mForm = i;
        this.mTitle = str;
        this.mMessage = str2;
    }

    public static void handleCause(Context context, IncapableCause incapableCause) {
        if (incapableCause != null) {
            int i = incapableCause.mForm;
            if (i == 1) {
                IncapableDialog.newInstance(incapableCause.mTitle, incapableCause.mMessage).show(((FragmentActivity) context).getSupportFragmentManager(), IncapableDialog.class.getName());
            } else if (i != 2) {
                Toast.makeText(context, incapableCause.mMessage, 0).show();
            }
        }
    }
}
