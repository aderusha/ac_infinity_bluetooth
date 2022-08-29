package com.eternal.widget;

import com.eternal.widget.WheelView;

public class WheelViewAdapter {
    public static void setOnSelect(WheelView wheelView, WheelView.OnSelectListener onSelectListener) {
        wheelView.setOnSelectListener(onSelectListener);
    }
}
