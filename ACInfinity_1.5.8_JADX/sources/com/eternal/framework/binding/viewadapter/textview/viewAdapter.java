package com.eternal.framework.binding.viewadapter.textview;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.alibaba.android.arouter.utils.Consts;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.utils.Utils;

public class viewAdapter {
    public static void setTextColor(TextView textView, int i) {
        textView.setTextColor(i);
    }

    public static void text(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
    }

    public static void textSmallDecimal(TextView textView, String str) {
        SpannableString spannableString = new SpannableString(str);
        if (str.contains(Consts.DOT)) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), str.indexOf(Consts.DOT), str.length(), 33);
        }
        textView.setText(spannableString);
    }

    public static void textDecoration(TextView textView, String str, int i, int i2) {
        SpannableString spannableString;
        if (str != null) {
            String str2 = null;
            if (str.contains("kPa")) {
                str2 = "kPa";
            } else if (str.contains("%")) {
                str2 = "%";
            } else if (str.contains(ProtocolTransformer.FAH)) {
                str2 = ProtocolTransformer.FAH;
            } else if (str.contains(ProtocolTransformer.DEGREE)) {
                str2 = ProtocolTransformer.DEGREE;
            }
            if (TextUtils.isEmpty(str2) || !str.contains(str2)) {
                spannableString = new SpannableString(str);
            } else {
                String replace = str.replace(str2, " " + str2);
                String str3 = " " + str2;
                spannableString = new SpannableString(replace);
                spannableString.setSpan(ResourcesCompat.getFont(Utils.getContext(), i2), replace.indexOf(str3), replace.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(i), replace.indexOf(str3), replace.length(), 33);
                spannableString.setSpan(new RelativeSizeSpan(0.71f), replace.indexOf(str3), replace.length(), 33);
            }
            textView.setText(spannableString);
        }
    }

    public static void drawableStart(TextView textView, Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }
}
