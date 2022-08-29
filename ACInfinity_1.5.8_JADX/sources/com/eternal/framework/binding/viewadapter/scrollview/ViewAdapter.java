package com.eternal.framework.binding.viewadapter.scrollview;

import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;
import com.eternal.framework.binding.command.BindingCommand;

public final class ViewAdapter {
    public static void onScrollChangeCommand(NestedScrollView nestedScrollView, final BindingCommand<NestScrollDataWrapper> bindingCommand) {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(new NestScrollDataWrapper(i, i2, i3, i4));
                }
            }
        });
    }

    public static void onScrollChangeCommand(final ScrollView scrollView, final BindingCommand<ScrollDataWrapper> bindingCommand) {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            public void onScrollChanged() {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(new ScrollDataWrapper((float) scrollView.getScrollX(), (float) scrollView.getScrollY()));
                }
            }
        });
    }

    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;

        public ScrollDataWrapper(float f, float f2) {
            this.scrollX = f;
            this.scrollY = f2;
        }
    }

    public static class NestScrollDataWrapper {
        public int oldScrollX;
        public int oldScrollY;
        public int scrollX;
        public int scrollY;

        public NestScrollDataWrapper(int i, int i2, int i3, int i4) {
            this.scrollX = i;
            this.scrollY = i2;
            this.oldScrollX = i3;
            this.oldScrollY = i4;
        }
    }
}
