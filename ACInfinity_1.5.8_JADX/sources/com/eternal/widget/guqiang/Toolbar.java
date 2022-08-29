package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.widget.C2779R;

public class Toolbar extends ConstraintLayout {
    private ImageView back;
    private ImageView next;
    private TextView nextTitle;
    private TextView title;

    public Toolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.toolbar_layout, this);
        this.back = (ImageView) inflate.findViewById(C2779R.C2782id.img_back);
        this.next = (ImageView) inflate.findViewById(C2779R.C2782id.img_next);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.nextTitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_next);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.Toolbar);
            this.back.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.Toolbar_backRes));
            this.next.setImageDrawable(obtainStyledAttributes.getDrawable(C2779R.styleable.Toolbar_nextRes));
            this.title.setText(obtainStyledAttributes.getText(C2779R.styleable.Toolbar_title));
            this.nextTitle.setText(obtainStyledAttributes.getText(C2779R.styleable.Toolbar_nextTitle));
            obtainStyledAttributes.recycle();
        }
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public ImageView getBack() {
        return this.back;
    }

    public View getNext() {
        return this.next.getDrawable() == null ? this.nextTitle : this.next;
    }

    public void setBackRes(Drawable drawable) {
        this.back.setImageDrawable(drawable);
    }

    public void setNextTitleText(CharSequence charSequence) {
        this.nextTitle.setText(charSequence);
    }
}
