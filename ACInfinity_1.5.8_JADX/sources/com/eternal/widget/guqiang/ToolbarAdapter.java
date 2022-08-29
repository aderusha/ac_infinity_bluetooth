package com.eternal.widget.guqiang;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;

public class ToolbarAdapter {
    public static void setTitle(Toolbar toolbar, String str) {
        toolbar.setTitle(str);
    }

    public static void onBack(Toolbar toolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(toolbar.getBack(), bindingCommand, false);
    }

    public static void onNext(Toolbar toolbar, BindingCommand<Void> bindingCommand) {
        ViewAdapter.onClickCommand(toolbar.getNext(), bindingCommand, false);
    }

    public static void backRes(Toolbar toolbar, Drawable drawable) {
        toolbar.setBackRes(drawable);
    }

    public static void setBackVisible(Toolbar toolbar, boolean z) {
        ImageView back = toolbar.getBack();
        if (back != null) {
            back.setVisibility(z ? 0 : 8);
        }
    }

    public static void nextTitle(Toolbar toolbar, CharSequence charSequence) {
        toolbar.setNextTitleText(charSequence);
    }

    public static void setNextVisible(Toolbar toolbar, boolean z) {
        View next = toolbar.getNext();
        if (next != null) {
            next.setVisibility(z ? 0 : 8);
        }
    }
}
