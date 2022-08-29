package com.eternal.widget.wheelview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.eternal.widget.wheelview.widget.WheelItem;

public class ArrayWheelAdapter<T> extends BaseWheelAdapter<T> {
    private Context mContext;

    public ArrayWheelAdapter(Context context) {
        this.mContext = context;
    }

    public View bindView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new WheelItem(this.mContext);
        }
        WheelItem wheelItem = (WheelItem) view;
        Object item = getItem(i);
        if (item != null) {
            if (wheelItem instanceof CharSequence) {
                wheelItem.setText((CharSequence) item);
            } else {
                wheelItem.setText(item.toString());
            }
        }
        return view;
    }
}
