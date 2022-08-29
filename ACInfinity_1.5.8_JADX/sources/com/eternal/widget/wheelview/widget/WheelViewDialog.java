package com.eternal.widget.wheelview.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.eternal.widget.wheelview.adapter.ArrayWheelAdapter;
import com.eternal.widget.wheelview.common.WheelConstants;
import com.eternal.widget.wheelview.util.WheelUtils;
import com.eternal.widget.wheelview.widget.WheelView;
import java.util.Arrays;
import java.util.List;

public class WheelViewDialog<T> implements View.OnClickListener {
    private TextView mButton;
    private Context mContext;
    private AlertDialog mDialog;
    private View mLine1;
    private View mLine2;
    private OnDialogItemClickListener mOnDialogItemClickListener;
    /* access modifiers changed from: private */
    public int mSelectedPos;
    /* access modifiers changed from: private */
    public T mSelectedText;
    private WheelView.WheelViewStyle mStyle;
    private TextView mTitle;
    private WheelView<T> mWheelView;

    public interface OnDialogItemClickListener<T> {
        void onItemClick(int i, T t);
    }

    public WheelViewDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(WheelUtils.dip2px(this.mContext, 20.0f), 0, WheelUtils.dip2px(this.mContext, 20.0f), 0);
        TextView textView = new TextView(this.mContext);
        this.mTitle = textView;
        textView.setTextColor(WheelConstants.DIALOG_WHEEL_COLOR);
        this.mTitle.setTextSize(2, 16.0f);
        this.mTitle.setGravity(17);
        linearLayout.addView(this.mTitle, new LinearLayout.LayoutParams(-1, WheelUtils.dip2px(this.mContext, 50.0f)));
        View view = new View(this.mContext);
        this.mLine1 = view;
        view.setBackgroundColor(WheelConstants.DIALOG_WHEEL_COLOR);
        linearLayout.addView(this.mLine1, new LinearLayout.LayoutParams(-1, WheelUtils.dip2px(this.mContext, 2.0f)));
        WheelView<T> wheelView = new WheelView<>(this.mContext);
        this.mWheelView = wheelView;
        wheelView.setSkin(WheelView.Skin.Holo);
        this.mWheelView.setWheelAdapter(new ArrayWheelAdapter(this.mContext));
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        this.mStyle = wheelViewStyle;
        wheelViewStyle.textColor = -7829368;
        this.mStyle.selectedTextZoom = 1.2f;
        this.mWheelView.setStyle(this.mStyle);
        this.mWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<T>() {
            public void onStartScroll(boolean z) {
            }

            public void onItemSelected(int i, T t, boolean z) {
                int unused = WheelViewDialog.this.mSelectedPos = i;
                Object unused2 = WheelViewDialog.this.mSelectedText = t;
            }

            public void onScrolling(int i, T t, boolean z) {
                int unused = WheelViewDialog.this.mSelectedPos = i;
                Object unused2 = WheelViewDialog.this.mSelectedText = t;
            }
        });
        linearLayout.addView(this.mWheelView, new ViewGroup.MarginLayoutParams(-1, -2));
        View view2 = new View(this.mContext);
        this.mLine2 = view2;
        view2.setBackgroundColor(WheelConstants.DIALOG_WHEEL_COLOR);
        linearLayout.addView(this.mLine2, new LinearLayout.LayoutParams(-1, WheelUtils.dip2px(this.mContext, 1.0f)));
        TextView textView2 = new TextView(this.mContext);
        this.mButton = textView2;
        textView2.setTextColor(WheelConstants.DIALOG_WHEEL_COLOR);
        this.mButton.setTextSize(2, 12.0f);
        this.mButton.setGravity(17);
        this.mButton.setClickable(true);
        this.mButton.setOnClickListener(this);
        this.mButton.setText("OK");
        linearLayout.addView(this.mButton, new LinearLayout.LayoutParams(-1, WheelUtils.dip2px(this.mContext, 45.0f)));
        AlertDialog create = new AlertDialog.Builder(this.mContext).create();
        this.mDialog = create;
        create.setView(linearLayout);
        this.mDialog.setCanceledOnTouchOutside(false);
    }

    public WheelViewDialog setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener) {
        this.mOnDialogItemClickListener = onDialogItemClickListener;
        return this;
    }

    public WheelViewDialog setDialogStyle(int i) {
        this.mTitle.setTextColor(i);
        this.mLine1.setBackgroundColor(i);
        this.mLine2.setBackgroundColor(i);
        this.mButton.setTextColor(i);
        this.mStyle.selectedTextColor = i;
        this.mStyle.holoBorderColor = i;
        return this;
    }

    public WheelViewDialog setTitle(String str) {
        this.mTitle.setText(str);
        return this;
    }

    public WheelViewDialog setTextColor(int i) {
        this.mTitle.setTextColor(i);
        return this;
    }

    public WheelViewDialog setTextSize(int i) {
        this.mTitle.setTextSize((float) i);
        return this;
    }

    public WheelViewDialog setButtonText(String str) {
        this.mButton.setText(str);
        return this;
    }

    public WheelViewDialog setButtonColor(int i) {
        this.mButton.setTextColor(i);
        return this;
    }

    public WheelViewDialog setButtonSize(int i) {
        this.mButton.setTextSize((float) i);
        return this;
    }

    public WheelViewDialog setCount(int i) {
        this.mWheelView.setWheelSize(i);
        return this;
    }

    public WheelViewDialog setLoop(boolean z) {
        this.mWheelView.setLoop(z);
        return this;
    }

    public WheelViewDialog setSelection(int i) {
        this.mWheelView.setSelection(i);
        return this;
    }

    public WheelViewDialog setItems(T[] tArr) {
        return setItems(Arrays.asList(tArr));
    }

    public WheelViewDialog setItems(List<T> list) {
        this.mWheelView.setWheelData(list);
        return this;
    }

    public WheelViewDialog show() {
        if (!this.mDialog.isShowing()) {
            this.mDialog.show();
        }
        return this;
    }

    public WheelViewDialog dismiss() {
        if (this.mDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        return this;
    }

    public void onClick(View view) {
        dismiss();
        OnDialogItemClickListener onDialogItemClickListener = this.mOnDialogItemClickListener;
        if (onDialogItemClickListener != null) {
            onDialogItemClickListener.onItemClick(this.mSelectedPos, this.mSelectedText);
        }
    }
}
