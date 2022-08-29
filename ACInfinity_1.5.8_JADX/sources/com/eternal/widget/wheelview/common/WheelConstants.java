package com.eternal.widget.wheelview.common;

import android.graphics.Color;

public class WheelConstants {
    public static final int DIALOG_WHEEL_COLOR;
    public static final String TAG = "com.wx.wheelview";
    public static final int WHEEL_BG = -1;
    public static final int WHEEL_ITEM_HEIGHT = 45;
    public static final int WHEEL_ITEM_IMAGE_TAG = 100;
    public static final int WHEEL_ITEM_MARGIN = 20;
    public static final int WHEEL_ITEM_PADDING = 20;
    public static final int WHEEL_ITEM_TEXT_TAG = 101;
    public static final int WHEEL_SCROLLING_WHAT = 257;
    public static final int WHEEL_SCROLL_DELAY_DURATION = 300;
    public static final int WHEEL_SCROLL_HANDLER_WHAT = 256;
    public static final int WHEEL_SCROLL_START_WHAT = 258;
    public static final int WHEEL_SKIN_COMMON_BG = Color.parseColor("#dddddd");
    public static final int WHEEL_SKIN_COMMON_BORDER_COLOR = Color.parseColor("#666666");
    public static final int WHEEL_SKIN_COMMON_COLOR = Color.parseColor("#f0cfcfcf");
    public static final int WHEEL_SKIN_COMMON_DIVIDER_COLOR = Color.parseColor("#b5b5b5");
    public static final int WHEEL_SKIN_HOLO_BG = -1;
    public static final int WHEEL_SKIN_HOLO_BORDER_COLOR;
    public static final int WHEEL_SMOOTH_SCROLL_DURATION = 50;
    public static final float WHEEL_TEXT_ALPHA = 0.7f;
    public static final int WHEEL_TEXT_COLOR = -16777216;
    public static final int WHEEL_TEXT_SIZE = 16;

    static {
        int parseColor = Color.parseColor("#83cde6");
        WHEEL_SKIN_HOLO_BORDER_COLOR = parseColor;
        DIALOG_WHEEL_COLOR = parseColor;
    }
}
