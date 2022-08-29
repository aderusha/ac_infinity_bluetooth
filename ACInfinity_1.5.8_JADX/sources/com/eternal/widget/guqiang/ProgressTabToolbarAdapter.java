package com.eternal.widget.guqiang;

import android.view.View;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;

public class ProgressTabToolbarAdapter {
    public static void setTitle(ProgressTabToolbar progressTabToolbar, String str) {
        progressTabToolbar.setTitle(str);
    }

    public static void setSubtitle(ProgressTabToolbar progressTabToolbar, String str) {
        progressTabToolbar.setSubtitle(str);
    }

    public static void onBack(ProgressTabToolbar progressTabToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressTabToolbar.getBack(), bindingCommand, false);
    }

    public static void onNext(ProgressTabToolbar progressTabToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressTabToolbar.getNext(), bindingCommand, false);
    }

    public static void onSecondNext(ProgressTabToolbar progressTabToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressTabToolbar.getNext(), bindingCommand, false);
    }

    public static void setProgress(ProgressTabToolbar progressTabToolbar, int i) {
        progressTabToolbar.setProgress((float) i);
    }

    public static void setNextVisible(ProgressTabToolbar progressTabToolbar, boolean z) {
        View next = progressTabToolbar.getNext();
        if (next != null) {
            next.setVisibility(z ? 0 : 8);
        }
    }
}
