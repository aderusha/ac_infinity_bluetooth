package com.eternal.notification.view;

import androidx.databinding.InverseBindingListener;
import com.eternal.notification.view.TitleSelectView;

public class TitleSelectViewAdapter {
    public static void setSelect(TitleSelectView titleSelectView, boolean z) {
        if (titleSelectView.getSelect() != z) {
            titleSelectView.setSelect(z);
        }
    }

    public static boolean getSelect(TitleSelectView titleSelectView) {
        return titleSelectView.getSelect();
    }

    public static void selectAttrChange(TitleSelectView titleSelectView, final InverseBindingListener inverseBindingListener) {
        titleSelectView.setSelectListener(new TitleSelectView.OnSelectListener() {
            public void onchange(boolean z) {
                InverseBindingListener.this.onChange();
            }
        });
    }

    public static void setTitle(TitleSelectView titleSelectView, String str) {
        titleSelectView.setTitle(str);
    }

    public static void setInfo(TitleSelectView titleSelectView, String str) {
        titleSelectView.setInfo(str);
    }
}
