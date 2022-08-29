package com.eternal.notification.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.notification.C2420R;

public class TitleSelectView extends ConstraintLayout {
    private TextView info;
    /* access modifiers changed from: private */
    public OnSelectListener listener;
    /* access modifiers changed from: private */
    public boolean noDouble;
    private ImageView select;
    private TextView title;
    private boolean unSelectAble;

    public interface OnSelectListener {
        void onchange(boolean z);
    }

    public TitleSelectView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TitleSelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2420R.styleable.TitleSelectView);
        this.title.setText(obtainStyledAttributes.getString(C2420R.styleable.TitleSelectView_title));
        this.info.setText(obtainStyledAttributes.getString(C2420R.styleable.TitleSelectView_info));
        this.select.setSelected(obtainStyledAttributes.getBoolean(C2420R.styleable.TitleSelectView_select, false));
        this.noDouble = obtainStyledAttributes.getBoolean(C2420R.styleable.TitleSelectView_noDouble, false);
        this.unSelectAble = obtainStyledAttributes.getBoolean(C2420R.styleable.TitleSelectView_unSelectAble, true);
        obtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, C2420R.layout.layout_title_select, this);
        this.title = (TextView) inflate.findViewById(C2420R.C2423id.tv_mode_type);
        this.info = (TextView) inflate.findViewById(C2420R.C2423id.tv_mode_type_des);
        ImageView imageView = (ImageView) inflate.findViewById(C2420R.C2423id.iv_select);
        this.select = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TitleSelectView.this.noDouble) {
                    boolean z = !TitleSelectView.this.getSelect();
                    TitleSelectView.this.setSelect(z);
                    if (TitleSelectView.this.listener != null) {
                        TitleSelectView.this.listener.onchange(z);
                    }
                } else if (!TitleSelectView.this.getSelect()) {
                    TitleSelectView.this.setSelect(true);
                    if (TitleSelectView.this.listener != null) {
                        TitleSelectView.this.listener.onchange(true);
                    }
                }
            }
        });
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public void setInfo(String str) {
        this.info.setText(str);
    }

    public void setSelect(boolean z) {
        this.select.setSelected(z);
    }

    public boolean getSelect() {
        return this.select.isSelected();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.listener != null) {
            boolean z = !getSelect();
            if (this.unSelectAble || z) {
                setSelect(z);
                OnSelectListener onSelectListener = this.listener;
                if (onSelectListener != null) {
                    onSelectListener.onchange(z);
                }
            }
        }
        return true;
    }

    public void setSelectListener(OnSelectListener onSelectListener) {
        this.listener = onSelectListener;
    }
}
