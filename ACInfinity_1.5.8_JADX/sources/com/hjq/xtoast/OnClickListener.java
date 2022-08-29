package com.hjq.xtoast;

import android.view.View;

public interface OnClickListener<V extends View> {
    void onClick(XToast xToast, V v);
}
