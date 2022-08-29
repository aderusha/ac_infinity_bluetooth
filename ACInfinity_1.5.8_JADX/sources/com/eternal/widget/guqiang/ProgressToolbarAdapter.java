package com.eternal.widget.guqiang;

import android.view.View;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;

public class ProgressToolbarAdapter {
    public static void setTitle(ProgressToolbar progressToolbar, String str) {
        progressToolbar.setTitle(str);
    }

    public static void setSubtitle(ProgressToolbar progressToolbar, String str) {
        progressToolbar.setSubtitle(str);
    }

    public static void onBack(ProgressToolbar progressToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressToolbar.getBack(), bindingCommand, false);
    }

    public static void onNext(ProgressToolbar progressToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressToolbar.getNext(), bindingCommand, false);
    }

    public static void onSecondNext(ProgressToolbar progressToolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(progressToolbar.getNext(), bindingCommand, false);
    }

    public static void setProgress(ProgressToolbar progressToolbar, int i) {
        progressToolbar.setProgress((float) i);
    }

    public static void setNextVisible(ProgressToolbar progressToolbar, boolean z) {
        View next = progressToolbar.getNext();
        if (next != null) {
            next.setVisibility(z ? 0 : 8);
        }
    }
}
