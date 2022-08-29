package com.eternal.notification.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.notification.C2420R;

public class TitleItemView extends ConstraintLayout {
    private TextView info;
    private ImageView ivNext;
    private TextView title;

    public TitleItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TitleItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2420R.styleable.TitleItemView);
        this.title.setText(obtainStyledAttributes.getString(C2420R.styleable.TitleItemView_title));
        this.info.setText(obtainStyledAttributes.getString(C2420R.styleable.TitleItemView_info));
        int i2 = 0;
        this.ivNext.setVisibility(!obtainStyledAttributes.getBoolean(C2420R.styleable.TitleItemView_arrowVisible, false) ? 4 : i2);
        obtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, C2420R.layout.layout_title_item, this);
        this.title = (TextView) inflate.findViewById(C2420R.C2423id.tv_mode_type);
        this.info = (TextView) inflate.findViewById(C2420R.C2423id.tv_mode_type_des);
        this.ivNext = (ImageView) inflate.findViewById(C2420R.C2423id.iv_next);
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public void setInfo(String str) {
        this.info.setText(str);
    }

    public void setArrowVisible(boolean z) {
        this.ivNext.setVisibility(z ? 0 : 4);
    }
}
