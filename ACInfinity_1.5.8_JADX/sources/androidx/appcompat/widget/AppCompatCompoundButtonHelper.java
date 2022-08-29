package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;

class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    interface DirectSetButtonDrawableInterface {
        void setButtonDrawable(Drawable drawable);
    }

    AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f A[SYNTHETIC, Splitter:B:12:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0067 A[Catch:{ all -> 0x007c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r4, int r5) {
        /*
            r3 = this;
            android.widget.CompoundButton r0 = r3.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.C0041R.styleable.CompoundButton
            r2 = 0
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r1, r5, r2)
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonCompat     // Catch:{ all -> 0x007c }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x002c
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonCompat     // Catch:{ all -> 0x007c }
            int r5 = r4.getResourceId(r5, r2)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x002c
            android.widget.CompoundButton r0 = r3.mView     // Catch:{ NotFoundException -> 0x002c }
            android.content.Context r1 = r0.getContext()     // Catch:{ NotFoundException -> 0x002c }
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r5)     // Catch:{ NotFoundException -> 0x002c }
            r0.setButtonDrawable(r5)     // Catch:{ NotFoundException -> 0x002c }
            r5 = 1
            goto L_0x002d
        L_0x002c:
            r5 = 0
        L_0x002d:
            if (r5 != 0) goto L_0x004c
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_android_button     // Catch:{ all -> 0x007c }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x004c
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_android_button     // Catch:{ all -> 0x007c }
            int r5 = r4.getResourceId(r5, r2)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x004c
            android.widget.CompoundButton r0 = r3.mView     // Catch:{ all -> 0x007c }
            android.content.Context r1 = r0.getContext()     // Catch:{ all -> 0x007c }
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r5)     // Catch:{ all -> 0x007c }
            r0.setButtonDrawable(r5)     // Catch:{ all -> 0x007c }
        L_0x004c:
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonTint     // Catch:{ all -> 0x007c }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x005f
            android.widget.CompoundButton r5 = r3.mView     // Catch:{ all -> 0x007c }
            int r0 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonTint     // Catch:{ all -> 0x007c }
            android.content.res.ColorStateList r0 = r4.getColorStateList(r0)     // Catch:{ all -> 0x007c }
            androidx.core.widget.CompoundButtonCompat.setButtonTintList(r5, r0)     // Catch:{ all -> 0x007c }
        L_0x005f:
            int r5 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonTintMode     // Catch:{ all -> 0x007c }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x0078
            android.widget.CompoundButton r5 = r3.mView     // Catch:{ all -> 0x007c }
            int r0 = androidx.appcompat.C0041R.styleable.CompoundButton_buttonTintMode     // Catch:{ all -> 0x007c }
            r1 = -1
            int r0 = r4.getInt(r0, r1)     // Catch:{ all -> 0x007c }
            r1 = 0
            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r0, r1)     // Catch:{ all -> 0x007c }
            androidx.core.widget.CompoundButtonCompat.setButtonTintMode(r5, r0)     // Catch:{ all -> 0x007c }
        L_0x0078:
            r4.recycle()
            return
        L_0x007c:
            r5 = move-exception
            r4.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* access modifiers changed from: package-private */
    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            Drawable mutate = DrawableCompat.wrap(buttonDrawable).mutate();
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList(mutate, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(mutate, this.mButtonTintMode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable(mutate);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = androidx.core.widget.CompoundButtonCompat.getButtonDrawable(r2.mView);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCompoundPaddingLeft(int r3) {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 >= r1) goto L_0x0013
            android.widget.CompoundButton r0 = r2.mView
            android.graphics.drawable.Drawable r0 = androidx.core.widget.CompoundButtonCompat.getButtonDrawable(r0)
            if (r0 == 0) goto L_0x0013
            int r0 = r0.getIntrinsicWidth()
            int r3 = r3 + r0
        L_0x0013:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.getCompoundPaddingLeft(int):int");
    }
}
