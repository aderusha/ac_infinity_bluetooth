package com.eternal.framework.binding.viewadapter.button;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public final class ViewAdapter {
    public static void drawableTop(Button button, Drawable drawable) {
        if (drawable != null) {
            button.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        }
    }
}
