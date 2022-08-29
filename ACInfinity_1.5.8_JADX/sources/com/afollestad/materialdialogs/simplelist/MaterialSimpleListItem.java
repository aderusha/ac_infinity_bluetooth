package com.afollestad.materialdialogs.simplelist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import com.afollestad.materialdialogs.util.DialogUtils;

public class MaterialSimpleListItem {
    private final Builder builder;

    private MaterialSimpleListItem(Builder builder2) {
        this.builder = builder2;
    }

    public Drawable getIcon() {
        return this.builder.icon;
    }

    public CharSequence getContent() {
        return this.builder.content;
    }

    public int getIconPadding() {
        return this.builder.iconPadding;
    }

    public int getBackgroundColor() {
        return this.builder.backgroundColor;
    }

    public long getId() {
        return this.builder.f75id;
    }

    public Object getTag() {
        return this.builder.tag;
    }

    public String toString() {
        return getContent() != null ? getContent().toString() : "(no content)";
    }

    public static class Builder {
        int backgroundColor = Color.parseColor("#BCBCBC");
        protected CharSequence content;
        private final Context context;
        protected Drawable icon;
        int iconPadding;

        /* renamed from: id */
        protected long f75id;
        Object tag;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder icon(Drawable drawable) {
            this.icon = drawable;
            return this;
        }

        public Builder icon(int i) {
            return icon(ContextCompat.getDrawable(this.context, i));
        }

        public Builder iconPadding(int i) {
            this.iconPadding = i;
            return this;
        }

        public Builder iconPaddingDp(int i) {
            this.iconPadding = (int) TypedValue.applyDimension(1, (float) i, this.context.getResources().getDisplayMetrics());
            return this;
        }

        public Builder iconPaddingRes(int i) {
            return iconPadding(this.context.getResources().getDimensionPixelSize(i));
        }

        public Builder content(CharSequence charSequence) {
            this.content = charSequence;
            return this;
        }

        public Builder content(int i) {
            return content((CharSequence) this.context.getString(i));
        }

        public Builder backgroundColor(int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder backgroundColorRes(int i) {
            return backgroundColor(DialogUtils.getColor(this.context, i));
        }

        public Builder backgroundColorAttr(int i) {
            return backgroundColor(DialogUtils.resolveColor(this.context, i));
        }

        /* renamed from: id */
        public Builder mo11411id(long j) {
            this.f75id = j;
            return this;
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public MaterialSimpleListItem build() {
            return new MaterialSimpleListItem(this);
        }
    }
}
