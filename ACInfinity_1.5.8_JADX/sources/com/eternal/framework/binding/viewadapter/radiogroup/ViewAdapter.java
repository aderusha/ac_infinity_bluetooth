package com.eternal.framework.binding.viewadapter.radiogroup;

import android.widget.RadioGroup;

public class ViewAdapter {
    public static void onCheckedChanged(RadioGroup radioGroup, RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
