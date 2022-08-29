package com.afollestad.materialdialogs.color;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.commons.C0817R;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.util.DialogUtils;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class ColorChooserDialog extends DialogFragment implements View.OnClickListener, View.OnLongClickListener {
    public static final String TAG_ACCENT = "[MD_COLOR_CHOOSER]";
    public static final String TAG_CUSTOM = "[MD_COLOR_CHOOSER]";
    public static final String TAG_PRIMARY = "[MD_COLOR_CHOOSER]";
    /* access modifiers changed from: private */
    public ColorCallback callback;
    /* access modifiers changed from: private */
    public int circleSize;
    private View colorChooserCustomFrame;
    /* access modifiers changed from: private */
    public int[][] colorsSub;
    /* access modifiers changed from: private */
    public int[] colorsTop;
    /* access modifiers changed from: private */
    public EditText customColorHex;
    /* access modifiers changed from: private */
    public View customColorIndicator;
    private SeekBar.OnSeekBarChangeListener customColorRgbListener;
    private TextWatcher customColorTextWatcher;
    /* access modifiers changed from: private */
    public SeekBar customSeekA;
    /* access modifiers changed from: private */
    public TextView customSeekAValue;
    /* access modifiers changed from: private */
    public SeekBar customSeekB;
    /* access modifiers changed from: private */
    public TextView customSeekBValue;
    /* access modifiers changed from: private */
    public SeekBar customSeekG;
    /* access modifiers changed from: private */
    public TextView customSeekGValue;
    /* access modifiers changed from: private */
    public SeekBar customSeekR;
    /* access modifiers changed from: private */
    public TextView customSeekRValue;
    private GridView grid;
    /* access modifiers changed from: private */
    public int selectedCustomColor;

    public interface ColorCallback {
        void onColorChooserDismissed(ColorChooserDialog colorChooserDialog);

        void onColorSelection(ColorChooserDialog colorChooserDialog, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorChooserTag {
    }

    public static ColorChooserDialog findVisible(AppCompatActivity appCompatActivity, String str) {
        Fragment findFragmentByTag = appCompatActivity.getSupportFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag == null || !(findFragmentByTag instanceof ColorChooserDialog)) {
            return null;
        }
        return (ColorChooserDialog) findFragmentByTag;
    }

    private void generateColors() {
        Builder builder = getBuilder();
        if (builder.colorsTop != null) {
            this.colorsTop = builder.colorsTop;
            this.colorsSub = builder.colorsSub;
        } else if (builder.accentMode) {
            this.colorsTop = ColorPalette.ACCENT_COLORS;
            this.colorsSub = ColorPalette.ACCENT_COLORS_SUB;
        } else {
            this.colorsTop = ColorPalette.PRIMARY_COLORS;
            this.colorsSub = ColorPalette.PRIMARY_COLORS_SUB;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("top_index", topIndex());
        bundle.putBoolean("in_sub", isInSub());
        bundle.putInt("sub_index", subIndex());
        View view = this.colorChooserCustomFrame;
        bundle.putBoolean("in_custom", view != null && view.getVisibility() == 0);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ColorCallback) {
            this.callback = (ColorCallback) activity;
            return;
        }
        throw new IllegalStateException("ColorChooserDialog needs to be shown from an Activity implementing ColorCallback.");
    }

    /* access modifiers changed from: private */
    public boolean isInSub() {
        return getArguments().getBoolean("in_sub", false);
    }

    /* access modifiers changed from: private */
    public void isInSub(boolean z) {
        getArguments().putBoolean("in_sub", z);
    }

    /* access modifiers changed from: private */
    public int topIndex() {
        return getArguments().getInt("top_index", -1);
    }

    /* access modifiers changed from: private */
    public void topIndex(int i) {
        if (i > -1) {
            findSubIndexForColor(i, this.colorsTop[i]);
        }
        getArguments().putInt("top_index", i);
    }

    /* access modifiers changed from: private */
    public int subIndex() {
        if (this.colorsSub == null) {
            return -1;
        }
        return getArguments().getInt("sub_index", -1);
    }

    /* access modifiers changed from: private */
    public void subIndex(int i) {
        if (this.colorsSub != null) {
            getArguments().putInt("sub_index", i);
        }
    }

    public int getTitle() {
        int i;
        Builder builder = getBuilder();
        if (isInSub()) {
            i = builder.titleSub;
        } else {
            i = builder.title;
        }
        return i == 0 ? builder.title : i;
    }

    public String tag() {
        Builder builder = getBuilder();
        if (builder.tag != null) {
            return builder.tag;
        }
        return super.getTag();
    }

    public boolean isAccentMode() {
        return getBuilder().accentMode;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            int parseInt = Integer.parseInt(((String) view.getTag()).split(":")[0]);
            MaterialDialog materialDialog = (MaterialDialog) getDialog();
            Builder builder = getBuilder();
            if (isInSub()) {
                subIndex(parseInt);
            } else {
                topIndex(parseInt);
                int[][] iArr = this.colorsSub;
                if (iArr != null && parseInt < iArr.length) {
                    materialDialog.setActionButton(DialogAction.NEGATIVE, builder.backBtn);
                    isInSub(true);
                }
            }
            if (builder.allowUserCustom) {
                this.selectedCustomColor = getSelectedColor();
            }
            invalidateDynamicButtonColors();
            invalidate();
        }
    }

    public boolean onLongClick(View view) {
        if (view.getTag() == null) {
            return false;
        }
        ((CircleView) view).showHint(Integer.parseInt(((String) view.getTag()).split(":")[1]));
        return true;
    }

    /* access modifiers changed from: private */
    public void invalidateDynamicButtonColors() {
        MaterialDialog materialDialog = (MaterialDialog) getDialog();
        if (materialDialog != null && getBuilder().dynamicButtonColor) {
            int selectedColor = getSelectedColor();
            if (Color.alpha(selectedColor) < 64 || (Color.red(selectedColor) > 247 && Color.green(selectedColor) > 247 && Color.blue(selectedColor) > 247)) {
                selectedColor = Color.parseColor("#DEDEDE");
            }
            if (getBuilder().dynamicButtonColor) {
                materialDialog.getActionButton(DialogAction.POSITIVE).setTextColor(selectedColor);
                materialDialog.getActionButton(DialogAction.NEGATIVE).setTextColor(selectedColor);
                materialDialog.getActionButton(DialogAction.NEUTRAL).setTextColor(selectedColor);
            }
            if (this.customSeekR != null) {
                if (this.customSeekA.getVisibility() == 0) {
                    MDTintHelper.setTint(this.customSeekA, selectedColor);
                }
                MDTintHelper.setTint(this.customSeekR, selectedColor);
                MDTintHelper.setTint(this.customSeekG, selectedColor);
                MDTintHelper.setTint(this.customSeekB, selectedColor);
            }
        }
    }

    /* access modifiers changed from: private */
    public int getSelectedColor() {
        int i;
        View view = this.colorChooserCustomFrame;
        if (view != null && view.getVisibility() == 0) {
            return this.selectedCustomColor;
        }
        int i2 = 0;
        if (subIndex() > -1) {
            i = this.colorsSub[topIndex()][subIndex()];
        } else {
            i = topIndex() > -1 ? this.colorsTop[topIndex()] : 0;
        }
        if (i != 0) {
            return i;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            i2 = DialogUtils.resolveColor(getActivity(), 16843829);
        }
        return DialogUtils.resolveColor(getActivity(), C0817R.attr.colorAccent, i2);
    }

    private void findSubIndexForColor(int i, int i2) {
        int[][] iArr = this.colorsSub;
        if (iArr != null && iArr.length - 1 >= i) {
            int[] iArr2 = iArr[i];
            for (int i3 = 0; i3 < iArr2.length; i3++) {
                if (iArr2[i3] == i2) {
                    subIndex(i3);
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        r8 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.app.Dialog onCreateDialog(android.os.Bundle r8) {
        /*
            r7 = this;
            android.os.Bundle r0 = r7.getArguments()
            if (r0 == 0) goto L_0x01cd
            android.os.Bundle r0 = r7.getArguments()
            java.lang.String r1 = "builder"
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x01cd
            r7.generateColors()
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L_0x0025
            java.lang.String r2 = "in_custom"
            boolean r8 = r8.getBoolean(r2, r1)
            r8 = r8 ^ r0
            int r2 = r7.getSelectedColor()
            goto L_0x0085
        L_0x0025:
            com.afollestad.materialdialogs.color.ColorChooserDialog$Builder r8 = r7.getBuilder()
            boolean r8 = r8.setPreselectionColor
            if (r8 == 0) goto L_0x0082
            com.afollestad.materialdialogs.color.ColorChooserDialog$Builder r8 = r7.getBuilder()
            int r2 = r8.preselectColor
            r8 = 0
            if (r2 == 0) goto L_0x0085
            r3 = 0
        L_0x0037:
            int[] r4 = r7.colorsTop
            int r5 = r4.length
            if (r8 >= r5) goto L_0x0080
            r4 = r4[r8]
            if (r4 != r2) goto L_0x005d
            r7.topIndex(r8)
            com.afollestad.materialdialogs.color.ColorChooserDialog$Builder r3 = r7.getBuilder()
            boolean r3 = r3.accentMode
            if (r3 == 0) goto L_0x0050
            r8 = 2
            r7.subIndex(r8)
            goto L_0x0084
        L_0x0050:
            int[][] r3 = r7.colorsSub
            if (r3 == 0) goto L_0x0058
            r7.findSubIndexForColor(r8, r2)
            goto L_0x0084
        L_0x0058:
            r8 = 5
            r7.subIndex(r8)
            goto L_0x0084
        L_0x005d:
            int[][] r4 = r7.colorsSub
            if (r4 == 0) goto L_0x007d
            r4 = 0
        L_0x0062:
            int[][] r5 = r7.colorsSub
            r6 = r5[r8]
            int r6 = r6.length
            if (r4 >= r6) goto L_0x007a
            r5 = r5[r8]
            r5 = r5[r4]
            if (r5 != r2) goto L_0x0077
            r7.topIndex(r8)
            r7.subIndex(r4)
            r3 = 1
            goto L_0x007a
        L_0x0077:
            int r4 = r4 + 1
            goto L_0x0062
        L_0x007a:
            if (r3 == 0) goto L_0x007d
            goto L_0x0080
        L_0x007d:
            int r8 = r8 + 1
            goto L_0x0037
        L_0x0080:
            r8 = r3
            goto L_0x0085
        L_0x0082:
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0084:
            r8 = 1
        L_0x0085:
            android.content.res.Resources r3 = r7.getResources()
            int r4 = com.afollestad.materialdialogs.commons.C0817R.dimen.md_colorchooser_circlesize
            int r3 = r3.getDimensionPixelSize(r4)
            r7.circleSize = r3
            com.afollestad.materialdialogs.color.ColorChooserDialog$Builder r3 = r7.getBuilder()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = new com.afollestad.materialdialogs.MaterialDialog$Builder
            androidx.fragment.app.FragmentActivity r5 = r7.getActivity()
            r4.<init>(r5)
            int r5 = r7.getTitle()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.title((int) r5)
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.autoDismiss(r1)
            int r5 = com.afollestad.materialdialogs.commons.C0817R.layout.md_dialog_colorchooser
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.customView((int) r5, (boolean) r1)
            int r5 = r3.cancelBtn
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.negativeText((int) r5)
            int r5 = r3.doneBtn
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.positiveText((int) r5)
            boolean r5 = r3.allowUserCustom
            if (r5 == 0) goto L_0x00c3
            int r5 = r3.customBtn
            goto L_0x00c4
        L_0x00c3:
            r5 = 0
        L_0x00c4:
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.neutralText((int) r5)
            java.lang.String r5 = r3.mediumFont
            java.lang.String r6 = r3.regularFont
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.typeface((java.lang.String) r5, (java.lang.String) r6)
            com.afollestad.materialdialogs.color.ColorChooserDialog$4 r5 = new com.afollestad.materialdialogs.color.ColorChooserDialog$4
            r5.<init>()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.onPositive(r5)
            com.afollestad.materialdialogs.color.ColorChooserDialog$3 r5 = new com.afollestad.materialdialogs.color.ColorChooserDialog$3
            r5.<init>()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.onNegative(r5)
            com.afollestad.materialdialogs.color.ColorChooserDialog$2 r5 = new com.afollestad.materialdialogs.color.ColorChooserDialog$2
            r5.<init>()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.onNeutral(r5)
            com.afollestad.materialdialogs.color.ColorChooserDialog$1 r5 = new com.afollestad.materialdialogs.color.ColorChooserDialog$1
            r5.<init>()
            com.afollestad.materialdialogs.MaterialDialog$Builder r4 = r4.showListener(r5)
            com.afollestad.materialdialogs.Theme r5 = r3.theme
            if (r5 == 0) goto L_0x00fd
            com.afollestad.materialdialogs.Theme r5 = r3.theme
            r4.theme(r5)
        L_0x00fd:
            com.afollestad.materialdialogs.MaterialDialog r4 = r4.build()
            android.view.View r5 = r4.getCustomView()
            int r6 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_grid
            android.view.View r6 = r5.findViewById(r6)
            android.widget.GridView r6 = (android.widget.GridView) r6
            r7.grid = r6
            boolean r6 = r3.allowUserCustom
            if (r6 == 0) goto L_0x01c9
            r7.selectedCustomColor = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorChooserCustomFrame
            android.view.View r2 = r5.findViewById(r2)
            r7.colorChooserCustomFrame = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_hexInput
            android.view.View r2 = r5.findViewById(r2)
            android.widget.EditText r2 = (android.widget.EditText) r2
            r7.customColorHex = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorIndicator
            android.view.View r2 = r5.findViewById(r2)
            r7.customColorIndicator = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorA
            android.view.View r2 = r5.findViewById(r2)
            android.widget.SeekBar r2 = (android.widget.SeekBar) r2
            r7.customSeekA = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorAValue
            android.view.View r2 = r5.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r7.customSeekAValue = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorR
            android.view.View r2 = r5.findViewById(r2)
            android.widget.SeekBar r2 = (android.widget.SeekBar) r2
            r7.customSeekR = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorRValue
            android.view.View r2 = r5.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r7.customSeekRValue = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorG
            android.view.View r2 = r5.findViewById(r2)
            android.widget.SeekBar r2 = (android.widget.SeekBar) r2
            r7.customSeekG = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorGValue
            android.view.View r2 = r5.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r7.customSeekGValue = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorB
            android.view.View r2 = r5.findViewById(r2)
            android.widget.SeekBar r2 = (android.widget.SeekBar) r2
            r7.customSeekB = r2
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorBValue
            android.view.View r2 = r5.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r7.customSeekBValue = r2
            boolean r2 = r3.allowUserCustomAlpha
            r3 = 8
            if (r2 != 0) goto L_0x01af
            int r2 = com.afollestad.materialdialogs.commons.C0817R.C0820id.md_colorALabel
            android.view.View r2 = r5.findViewById(r2)
            r2.setVisibility(r3)
            android.widget.SeekBar r2 = r7.customSeekA
            r2.setVisibility(r3)
            android.widget.TextView r2 = r7.customSeekAValue
            r2.setVisibility(r3)
            android.widget.EditText r2 = r7.customColorHex
            java.lang.String r3 = "2196F3"
            r2.setHint(r3)
            android.widget.EditText r2 = r7.customColorHex
            android.text.InputFilter[] r0 = new android.text.InputFilter[r0]
            android.text.InputFilter$LengthFilter r3 = new android.text.InputFilter$LengthFilter
            r5 = 6
            r3.<init>(r5)
            r0[r1] = r3
            r2.setFilters(r0)
            goto L_0x01c4
        L_0x01af:
            android.widget.EditText r2 = r7.customColorHex
            java.lang.String r5 = "FF2196F3"
            r2.setHint(r5)
            android.widget.EditText r2 = r7.customColorHex
            android.text.InputFilter[] r0 = new android.text.InputFilter[r0]
            android.text.InputFilter$LengthFilter r5 = new android.text.InputFilter$LengthFilter
            r5.<init>(r3)
            r0[r1] = r5
            r2.setFilters(r0)
        L_0x01c4:
            if (r8 != 0) goto L_0x01c9
            r7.toggleCustom(r4)
        L_0x01c9:
            r7.invalidate()
            return r4
        L_0x01cd:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "ColorChooserDialog should be created using its Builder interface."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.color.ColorChooserDialog.onCreateDialog(android.os.Bundle):android.app.Dialog");
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ColorCallback colorCallback = this.callback;
        if (colorCallback != null) {
            colorCallback.onColorChooserDismissed(this);
        }
    }

    /* access modifiers changed from: private */
    public void toggleCustom(MaterialDialog materialDialog) {
        if (materialDialog == null) {
            materialDialog = (MaterialDialog) getDialog();
        }
        if (this.grid.getVisibility() == 0) {
            materialDialog.setTitle(getBuilder().customBtn);
            materialDialog.setActionButton(DialogAction.NEUTRAL, getBuilder().presetsBtn);
            materialDialog.setActionButton(DialogAction.NEGATIVE, getBuilder().cancelBtn);
            this.grid.setVisibility(4);
            this.colorChooserCustomFrame.setVisibility(0);
            C08155 r5 = new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    try {
                        ColorChooserDialog colorChooserDialog = ColorChooserDialog.this;
                        int unused = colorChooserDialog.selectedCustomColor = Color.parseColor("#" + charSequence.toString());
                    } catch (IllegalArgumentException unused2) {
                        int unused3 = ColorChooserDialog.this.selectedCustomColor = -16777216;
                    }
                    ColorChooserDialog.this.customColorIndicator.setBackgroundColor(ColorChooserDialog.this.selectedCustomColor);
                    if (ColorChooserDialog.this.customSeekA.getVisibility() == 0) {
                        int alpha = Color.alpha(ColorChooserDialog.this.selectedCustomColor);
                        ColorChooserDialog.this.customSeekA.setProgress(alpha);
                        ColorChooserDialog.this.customSeekAValue.setText(String.format(Locale.US, "%d", new Object[]{Integer.valueOf(alpha)}));
                    }
                    if (ColorChooserDialog.this.customSeekA.getVisibility() == 0) {
                        ColorChooserDialog.this.customSeekA.setProgress(Color.alpha(ColorChooserDialog.this.selectedCustomColor));
                    }
                    ColorChooserDialog.this.customSeekR.setProgress(Color.red(ColorChooserDialog.this.selectedCustomColor));
                    ColorChooserDialog.this.customSeekG.setProgress(Color.green(ColorChooserDialog.this.selectedCustomColor));
                    ColorChooserDialog.this.customSeekB.setProgress(Color.blue(ColorChooserDialog.this.selectedCustomColor));
                    ColorChooserDialog.this.isInSub(false);
                    ColorChooserDialog.this.topIndex(-1);
                    ColorChooserDialog.this.subIndex(-1);
                    ColorChooserDialog.this.invalidateDynamicButtonColors();
                }
            };
            this.customColorTextWatcher = r5;
            this.customColorHex.addTextChangedListener(r5);
            C08166 r52 = new SeekBar.OnSeekBarChangeListener() {
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (z) {
                        if (ColorChooserDialog.this.getBuilder().allowUserCustomAlpha) {
                            int argb = Color.argb(ColorChooserDialog.this.customSeekA.getProgress(), ColorChooserDialog.this.customSeekR.getProgress(), ColorChooserDialog.this.customSeekG.getProgress(), ColorChooserDialog.this.customSeekB.getProgress());
                            ColorChooserDialog.this.customColorHex.setText(String.format("%08X", new Object[]{Integer.valueOf(argb)}));
                        } else {
                            int rgb = Color.rgb(ColorChooserDialog.this.customSeekR.getProgress(), ColorChooserDialog.this.customSeekG.getProgress(), ColorChooserDialog.this.customSeekB.getProgress());
                            ColorChooserDialog.this.customColorHex.setText(String.format("%06X", new Object[]{Integer.valueOf(rgb & ViewCompat.MEASURED_SIZE_MASK)}));
                        }
                    }
                    ColorChooserDialog.this.customSeekAValue.setText(String.format("%d", new Object[]{Integer.valueOf(ColorChooserDialog.this.customSeekA.getProgress())}));
                    ColorChooserDialog.this.customSeekRValue.setText(String.format("%d", new Object[]{Integer.valueOf(ColorChooserDialog.this.customSeekR.getProgress())}));
                    ColorChooserDialog.this.customSeekGValue.setText(String.format("%d", new Object[]{Integer.valueOf(ColorChooserDialog.this.customSeekG.getProgress())}));
                    ColorChooserDialog.this.customSeekBValue.setText(String.format("%d", new Object[]{Integer.valueOf(ColorChooserDialog.this.customSeekB.getProgress())}));
                }
            };
            this.customColorRgbListener = r52;
            this.customSeekR.setOnSeekBarChangeListener(r52);
            this.customSeekG.setOnSeekBarChangeListener(this.customColorRgbListener);
            this.customSeekB.setOnSeekBarChangeListener(this.customColorRgbListener);
            if (this.customSeekA.getVisibility() == 0) {
                this.customSeekA.setOnSeekBarChangeListener(this.customColorRgbListener);
                this.customColorHex.setText(String.format("%08X", new Object[]{Integer.valueOf(this.selectedCustomColor)}));
                return;
            }
            this.customColorHex.setText(String.format("%06X", new Object[]{Integer.valueOf(16777215 & this.selectedCustomColor)}));
            return;
        }
        materialDialog.setTitle(getBuilder().title);
        materialDialog.setActionButton(DialogAction.NEUTRAL, getBuilder().customBtn);
        if (isInSub()) {
            materialDialog.setActionButton(DialogAction.NEGATIVE, getBuilder().backBtn);
        } else {
            materialDialog.setActionButton(DialogAction.NEGATIVE, getBuilder().cancelBtn);
        }
        this.grid.setVisibility(0);
        this.colorChooserCustomFrame.setVisibility(8);
        this.customColorHex.removeTextChangedListener(this.customColorTextWatcher);
        this.customColorTextWatcher = null;
        this.customSeekR.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        this.customSeekG.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        this.customSeekB.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        this.customColorRgbListener = null;
    }

    /* access modifiers changed from: private */
    public void invalidate() {
        if (this.grid.getAdapter() == null) {
            this.grid.setAdapter(new ColorGridAdapter());
            this.grid.setSelector(ResourcesCompat.getDrawable(getResources(), C0817R.C0819drawable.md_transparent, (Resources.Theme) null));
        } else {
            ((BaseAdapter) this.grid.getAdapter()).notifyDataSetChanged();
        }
        if (getDialog() != null) {
            getDialog().setTitle(getTitle());
        }
    }

    /* access modifiers changed from: private */
    public Builder getBuilder() {
        if (getArguments() == null || !getArguments().containsKey("builder")) {
            return null;
        }
        return (Builder) getArguments().getSerializable("builder");
    }

    private void dismissIfNecessary(AppCompatActivity appCompatActivity, String str) {
        Fragment findFragmentByTag = appCompatActivity.getSupportFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag != null) {
            ((DialogFragment) findFragmentByTag).dismiss();
            appCompatActivity.getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commit();
        }
    }

    public ColorChooserDialog show(AppCompatActivity appCompatActivity) {
        Builder builder = getBuilder();
        if (builder.colorsTop == null) {
            boolean z = builder.accentMode;
        }
        dismissIfNecessary(appCompatActivity, "[MD_COLOR_CHOOSER]");
        show(appCompatActivity.getSupportFragmentManager(), "[MD_COLOR_CHOOSER]");
        return this;
    }

    public static class Builder implements Serializable {
        boolean accentMode = false;
        boolean allowUserCustom = true;
        boolean allowUserCustomAlpha = true;
        int backBtn = C0817R.string.md_back_label;
        int cancelBtn = C0817R.string.md_cancel_label;
        int[][] colorsSub;
        int[] colorsTop;
        final transient AppCompatActivity context;
        int customBtn = C0817R.string.md_custom_label;
        int doneBtn = C0817R.string.md_done_label;
        boolean dynamicButtonColor = true;
        String mediumFont;
        int preselectColor;
        int presetsBtn = C0817R.string.md_presets_label;
        String regularFont;
        boolean setPreselectionColor = false;
        String tag;
        Theme theme;
        final int title;
        int titleSub;

        public <ActivityType extends AppCompatActivity & ColorCallback> Builder(ActivityType activitytype, int i) {
            this.context = activitytype;
            this.title = i;
        }

        public Builder typeface(String str, String str2) {
            this.mediumFont = str;
            this.regularFont = str2;
            return this;
        }

        public Builder titleSub(int i) {
            this.titleSub = i;
            return this;
        }

        public Builder tag(String str) {
            this.tag = str;
            return this;
        }

        public Builder theme(Theme theme2) {
            this.theme = theme2;
            return this;
        }

        public Builder preselect(int i) {
            this.preselectColor = i;
            this.setPreselectionColor = true;
            return this;
        }

        public Builder accentMode(boolean z) {
            this.accentMode = z;
            return this;
        }

        public Builder doneButton(int i) {
            this.doneBtn = i;
            return this;
        }

        public Builder backButton(int i) {
            this.backBtn = i;
            return this;
        }

        public Builder cancelButton(int i) {
            this.cancelBtn = i;
            return this;
        }

        public Builder customButton(int i) {
            this.customBtn = i;
            return this;
        }

        public Builder presetsButton(int i) {
            this.presetsBtn = i;
            return this;
        }

        public Builder dynamicButtonColor(boolean z) {
            this.dynamicButtonColor = z;
            return this;
        }

        public Builder customColors(int[] iArr, int[][] iArr2) {
            this.colorsTop = iArr;
            this.colorsSub = iArr2;
            return this;
        }

        public Builder customColors(int i, int[][] iArr) {
            this.colorsTop = DialogUtils.getColorArray(this.context, i);
            this.colorsSub = iArr;
            return this;
        }

        public Builder allowUserColorInput(boolean z) {
            this.allowUserCustom = z;
            return this;
        }

        public Builder allowUserColorInputAlpha(boolean z) {
            this.allowUserCustomAlpha = z;
            return this;
        }

        public ColorChooserDialog build() {
            ColorChooserDialog colorChooserDialog = new ColorChooserDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("builder", this);
            colorChooserDialog.setArguments(bundle);
            return colorChooserDialog;
        }

        public ColorChooserDialog show() {
            ColorChooserDialog build = build();
            build.show(this.context);
            return build;
        }
    }

    private class ColorGridAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        ColorGridAdapter() {
        }

        public int getCount() {
            if (ColorChooserDialog.this.isInSub()) {
                return ColorChooserDialog.this.colorsSub[ColorChooserDialog.this.topIndex()].length;
            }
            return ColorChooserDialog.this.colorsTop.length;
        }

        public Object getItem(int i) {
            if (ColorChooserDialog.this.isInSub()) {
                return Integer.valueOf(ColorChooserDialog.this.colorsSub[ColorChooserDialog.this.topIndex()][i]);
            }
            return Integer.valueOf(ColorChooserDialog.this.colorsTop[i]);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new CircleView(ColorChooserDialog.this.getContext());
                view.setLayoutParams(new AbsListView.LayoutParams(ColorChooserDialog.this.circleSize, ColorChooserDialog.this.circleSize));
            }
            CircleView circleView = (CircleView) view;
            int i2 = ColorChooserDialog.this.isInSub() ? ColorChooserDialog.this.colorsSub[ColorChooserDialog.this.topIndex()][i] : ColorChooserDialog.this.colorsTop[i];
            circleView.setBackgroundColor(i2);
            if (ColorChooserDialog.this.isInSub()) {
                circleView.setSelected(ColorChooserDialog.this.subIndex() == i);
            } else {
                circleView.setSelected(ColorChooserDialog.this.topIndex() == i);
            }
            circleView.setTag(String.format("%d:%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            circleView.setOnClickListener(ColorChooserDialog.this);
            circleView.setOnLongClickListener(ColorChooserDialog.this);
            return view;
        }
    }
}
