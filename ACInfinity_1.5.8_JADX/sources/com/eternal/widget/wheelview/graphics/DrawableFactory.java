package com.eternal.widget.wheelview.graphics;

import android.graphics.drawable.Drawable;
import com.eternal.widget.wheelview.widget.WheelView;

public class DrawableFactory {
    public static Drawable createDrawable(WheelView.Skin skin, int i, int i2, WheelView.WheelViewStyle wheelViewStyle, int i3, int i4) {
        if (skin.equals(WheelView.Skin.Common)) {
            return new CommonDrawable(i, i2, wheelViewStyle, i3, i4);
        }
        if (skin.equals(WheelView.Skin.Holo)) {
            return new HoloDrawable(i, i2, wheelViewStyle, i3, i4);
        }
        return new WheelDrawable(i, i2, wheelViewStyle);
    }
}
