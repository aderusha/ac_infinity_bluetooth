package com.eternal.framework.binding.viewadapter.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.eternal.framework.binding.command.BindingCommand;
import com.jakewharton.rxbinding2.view.RxView;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.functions.Consumer;

public class ViewAdapter {
    public static final int CLICK_INTERVAL = 1;

    public static void onClickCommand(View view, final BindingCommand bindingCommand, boolean z) {
        if (z) {
            RxView.clicks(view).subscribe(new Consumer<Object>() {
                public void accept(Object obj) throws Exception {
                    BindingCommand bindingCommand = BindingCommand.this;
                    if (bindingCommand != null) {
                        bindingCommand.execute();
                    }
                }
            });
        } else {
            RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
                public void accept(Object obj) throws Exception {
                    BindingCommand bindingCommand = BindingCommand.this;
                    if (bindingCommand != null) {
                        bindingCommand.execute();
                    }
                }
            });
        }
    }

    public static void onLongClickCommand(View view, final BindingCommand bindingCommand) {
        RxView.longClicks(view).subscribe(new Consumer<Object>() {
            public void accept(Object obj) throws Exception {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute();
                }
            }
        });
    }

    public static void replyCurrentView(View view, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(view);
        }
    }

    public static void requestFocusCommand(View view, Boolean bool) {
        if (bool.booleanValue()) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            return;
        }
        view.clearFocus();
    }

    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> bindingCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(Boolean.valueOf(z));
                }
            }
        });
    }

    public static void onFocusChangeCommand(View view, View.OnFocusChangeListener onFocusChangeListener) {
        view.setOnFocusChangeListener(onFocusChangeListener);
    }

    public static void isVisible(View view, Boolean bool) {
        if (bool.booleanValue()) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public static void backgroundDrawable(View view, Drawable drawable) {
        if (drawable != null) {
            view.setBackground(drawable);
        }
    }

    public static void backgroundColor(View view, int i) {
        view.setBackgroundColor(i);
    }

    public static void onSelected(View view, boolean z) {
        view.setSelected(z);
    }
}
