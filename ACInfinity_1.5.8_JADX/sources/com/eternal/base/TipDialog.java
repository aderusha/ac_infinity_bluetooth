package com.eternal.base;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.res.C2663R;

public class TipDialog {
    public static MaterialDialog showTipDialog(Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return showTipDialog(context, str, str2, str3, str4, z, z2, onClickListener, onClickListener2, (DialogInterface.OnDismissListener) null);
    }

    public static MaterialDialog showTipDialog(Context context, String str, String str2, String str3, String str4, final boolean z, boolean z2, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2, final DialogInterface.OnDismissListener onDismissListener) {
        int i = 0;
        View inflate = LayoutInflater.from(context).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
        MaterialDialog.Builder customView = new MaterialDialog.Builder(context).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false);
        if (!z) {
            customView.canceledOnTouchOutside(z).autoDismiss(z);
        }
        final MaterialDialog build = customView.build();
        if (!z) {
            build.setCancelable(z);
        }
        ((TextView) inflate.findViewById(C1323R.C1326id.tv_title)).setText(str);
        ((TextView) inflate.findViewById(C1323R.C1326id.tv_content)).setText(str2);
        TextView textView = (TextView) inflate.findViewById(C2663R.C2666id.tv_cancel);
        textView.setText(str3);
        textView.setVisibility(z2 ? 0 : 8);
        View findViewById = inflate.findViewById(C2663R.C2666id.v_line);
        if (!z2) {
            i = 8;
        }
        findViewById.setVisibility(i);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View.OnClickListener onClickListener = onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (z) {
                    build.dismiss();
                }
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(C2663R.C2666id.tv_confirm);
        textView2.setText(str4);
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View.OnClickListener onClickListener = onClickListener2;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (z) {
                    build.dismiss();
                }
            }
        });
        if (onDismissListener != null) {
            build.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    onDismissListener.onDismiss(dialogInterface);
                }
            });
        }
        build.show();
        return build;
    }

    public static MaterialDialog showTipDialog2(Context context, String str, String str2, String str3, String str4, String str5, final boolean z, boolean z2, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2, final View.OnClickListener onClickListener3, final DialogInterface.OnDismissListener onDismissListener) {
        int i = 0;
        View inflate = LayoutInflater.from(context).inflate(C2663R.layout.dialog_tip2, (ViewGroup) null, false);
        MaterialDialog.Builder customView = new MaterialDialog.Builder(context).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false);
        if (!z) {
            customView.canceledOnTouchOutside(z).autoDismiss(z);
        }
        final MaterialDialog build = customView.build();
        if (!z) {
            build.setCancelable(z);
        }
        ((TextView) inflate.findViewById(C1323R.C1326id.tv_title)).setText(str);
        ((TextView) inflate.findViewById(C1323R.C1326id.tv_content)).setText(str2);
        TextView textView = (TextView) inflate.findViewById(C2663R.C2666id.tv_cancel);
        textView.setText(str3);
        if (!z2) {
            i = 8;
        }
        textView.setVisibility(i);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View.OnClickListener onClickListener = onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (z) {
                    build.dismiss();
                }
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(C2663R.C2666id.tv_confirm);
        textView2.setText(str4);
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View.OnClickListener onClickListener = onClickListener2;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (z) {
                    build.dismiss();
                }
            }
        });
        TextView textView3 = (TextView) inflate.findViewById(C2663R.C2666id.tv_confirm_second);
        textView3.setText(str5);
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View.OnClickListener onClickListener = onClickListener3;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (z) {
                    build.dismiss();
                }
            }
        });
        if (onDismissListener != null) {
            build.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    onDismissListener.onDismiss(dialogInterface);
                }
            });
        }
        build.show();
        return build;
    }
}
