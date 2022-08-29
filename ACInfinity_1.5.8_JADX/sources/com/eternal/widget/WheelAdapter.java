package com.eternal.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.eternal.widget.wheelview.adapter.BaseWheelAdapter;

public class WheelAdapter extends BaseWheelAdapter<String> {
    private Context mContext;

    public WheelAdapter(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public View bindView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(C2779R.layout.item_wheel, (ViewGroup) null);
            viewHolder.textView = (TextView) view2.findViewById(C2779R.C2782id.item_name);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText((CharSequence) this.mList.get(i));
        return view2;
    }

    static class ViewHolder {
        TextView textView;

        ViewHolder() {
        }
    }
}
