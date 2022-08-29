package com.zhihu.matisse.internal.p012ui.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.zhihu.matisse.C3757R;

/* renamed from: com.zhihu.matisse.internal.ui.widget.IncapableDialog */
public class IncapableDialog extends DialogFragment {
    public static final String EXTRA_MESSAGE = "extra_message";
    public static final String EXTRA_TITLE = "extra_title";

    public static IncapableDialog newInstance(String str, String str2) {
        IncapableDialog incapableDialog = new IncapableDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, str);
        bundle.putString(EXTRA_MESSAGE, str2);
        incapableDialog.setArguments(bundle);
        return incapableDialog;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String string = getArguments().getString(EXTRA_TITLE);
        String string2 = getArguments().getString(EXTRA_MESSAGE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle((CharSequence) string);
        }
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage((CharSequence) string2);
        }
        builder.setPositiveButton(C3757R.string.button_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}
