package com.eternal.device;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemDeviceView extends LinearLayout {
    public ItemDeviceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ItemDeviceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ItemDeviceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C1922R.layout.item_device_view, this);
        ImageView imageView = (ImageView) inflate.findViewById(C1922R.C1925id.iv_blue);
        ImageView imageView2 = (ImageView) inflate.findViewById(C1922R.C1925id.iv_wifi);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1922R.styleable.ItemDeviceView);
        ((TextView) inflate.findViewById(C1922R.C1925id.tv_name)).setText(obtainStyledAttributes.getString(C1922R.styleable.ItemDeviceView_name));
        ((TextView) inflate.findViewById(C1922R.C1925id.tv_mode)).setText(obtainStyledAttributes.getString(C1922R.styleable.ItemDeviceView_mode));
        int i2 = 0;
        ((ImageView) inflate.findViewById(C1922R.C1925id.iv_icon)).setImageResource(obtainStyledAttributes.getResourceId(C1922R.styleable.ItemDeviceView_icon, 0));
        imageView.setVisibility(obtainStyledAttributes.getBoolean(C1922R.styleable.ItemDeviceView_blueVisible, true) ? 0 : 8);
        imageView2.setVisibility(!obtainStyledAttributes.getBoolean(C1922R.styleable.ItemDeviceView_wifiVisible, false) ? 8 : i2);
        obtainStyledAttributes.recycle();
    }
}
