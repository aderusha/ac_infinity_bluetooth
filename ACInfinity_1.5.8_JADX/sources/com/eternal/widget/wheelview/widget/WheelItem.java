package com.eternal.widget.wheelview.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.eternal.widget.wheelview.util.WheelUtils;

public class WheelItem extends FrameLayout {
    private ImageView mImage;
    private TextView mText;

    public WheelItem(Context context) {
        super(context);
        init();
    }

    public WheelItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WheelItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, WheelUtils.dip2px(getContext(), 45.0f));
        linearLayout.setOrientation(0);
        linearLayout.setPadding(20, 20, 20, 20);
        linearLayout.setGravity(17);
        addView(linearLayout, layoutParams);
        ImageView imageView = new ImageView(getContext());
        this.mImage = imageView;
        imageView.setTag(100);
        this.mImage.setVisibility(8);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.rightMargin = 20;
        linearLayout.addView(this.mImage, layoutParams2);
        TextView textView = new TextView(getContext());
        this.mText = textView;
        textView.setTag(101);
        this.mText.setEllipsize(TextUtils.TruncateAt.END);
        this.mText.setSingleLine();
        this.mText.setIncludeFontPadding(false);
        this.mText.setGravity(17);
        this.mText.setTextColor(-16777216);
        linearLayout.addView(this.mText, new FrameLayout.LayoutParams(-1, -1));
    }

    public void setText(CharSequence charSequence) {
        this.mText.setText(charSequence);
    }

    public void setImage(int i) {
        this.mImage.setVisibility(0);
        this.mImage.setImageResource(i);
    }
}
