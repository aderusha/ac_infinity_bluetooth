package com.eternal.framework.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.eternal.framework.C2171R;
import java.util.Collection;
import java.util.List;

public class MaterialDialogUtils {
    public void showThemed(Context context, String str, String str2) {
        new MaterialDialog.Builder(context).title((CharSequence) str).content((CharSequence) str2).positiveText((CharSequence) "agree").negativeText((CharSequence) "disagree").positiveColorRes(C2171R.C2172color.white).negativeColorRes(C2171R.C2172color.white).titleGravity(GravityEnum.CENTER).titleColorRes(C2171R.C2172color.white).contentColorRes(17170443).backgroundColorRes(C2171R.C2172color.material_blue_grey_800).dividerColorRes(C2171R.C2172color.white).btnSelector(C2171R.C2173drawable.md_selector, DialogAction.POSITIVE).positiveColor(-1).negativeColorAttr(16842810).theme(Theme.DARK).autoDismiss(true).showListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
            }
        }).cancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).dismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        }).show();
    }

    public static MaterialDialog.Builder showIndeterminateProgressDialog(Context context, String str, boolean z) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).progress(true, 0).progressIndeterminateStyle(z).canceledOnTouchOutside(false).backgroundColorRes(C2171R.C2172color.white).keyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                keyEvent.getAction();
                return false;
            }
        });
    }

    public static MaterialDialog.Builder showBasicDialog(Context context, String str) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).positiveText((CharSequence) "确定").negativeText((CharSequence) "取消");
    }

    public static MaterialDialog.Builder showBasicDialogNoTitle(Context context, String str) {
        return new MaterialDialog.Builder(context).content((CharSequence) str).positiveText((CharSequence) "确定").negativeText((CharSequence) "取消");
    }

    public static MaterialDialog.Builder showBasicDialogNoCancel(Context context, String str, String str2) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).content((CharSequence) str2).positiveText((CharSequence) "确定");
    }

    public static MaterialDialog.Builder showBasicDialog(Context context, String str, String str2) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).content((CharSequence) str2).positiveText((CharSequence) "确定").negativeText((CharSequence) "取消");
    }

    public static MaterialDialog.Builder showBasicDialogPositive(Context context, String str, String str2) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).content((CharSequence) str2).positiveText((CharSequence) "复制").negativeText((CharSequence) "取消");
    }

    public static MaterialDialog.Builder getSelectDialog(Context context, String str, String[] strArr) {
        MaterialDialog.Builder negativeText = new MaterialDialog.Builder(context).items((CharSequence[]) strArr).itemsColor(-12226906).negativeText((CharSequence) "取消");
        if (!TextUtils.isEmpty(str)) {
            negativeText.title((CharSequence) str);
        }
        return negativeText;
    }

    public static MaterialDialog.Builder showBasicListDialog(Context context, String str, List list) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).items((Collection) list).itemsCallback(new MaterialDialog.ListCallback() {
            public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
            }
        }).negativeText((CharSequence) "取消");
    }

    public static MaterialDialog.Builder showSingleListDialog(Context context, String str, List list) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).items((Collection) list).itemsCallbackSingleChoice(1, new MaterialDialog.ListCallbackSingleChoice() {
            public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                return true;
            }
        }).positiveText((CharSequence) "选择");
    }

    public static MaterialDialog.Builder showMultiListDialog(Context context, String str, List list) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).items((Collection) list).itemsCallbackMultiChoice(new Integer[]{1, 3}, new MaterialDialog.ListCallbackMultiChoice() {
            public boolean onSelection(MaterialDialog materialDialog, Integer[] numArr, CharSequence[] charSequenceArr) {
                return true;
            }
        }).onNeutral(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                materialDialog.clearSelectedIndices();
            }
        }).alwaysCallMultiChoiceCallback().positiveText(C2171R.string.md_choose_label).autoDismiss(false).neutralText((CharSequence) "clear").itemsDisabledIndices(0, 1);
    }

    public static void showCustomDialog(Context context, String str, int i) {
        new MaterialDialog.Builder(context).title((CharSequence) str).customView(i, true).positiveText((CharSequence) "确定").negativeText(17039360).onPositive(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
            }
        }).build();
    }

    public static MaterialDialog.Builder showInputDialog(Context context, String str, String str2) {
        return new MaterialDialog.Builder(context).title((CharSequence) str).content((CharSequence) str2).inputType(8289).positiveText((CharSequence) "确定").negativeText((CharSequence) "取消").input((CharSequence) "hint", (CharSequence) "prefill", true, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
            public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
            }
        });
    }
}
