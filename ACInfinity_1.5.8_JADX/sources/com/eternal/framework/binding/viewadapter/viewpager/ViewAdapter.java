package com.eternal.framework.binding.viewadapter.viewpager;

import androidx.viewpager.widget.ViewPager;
import com.eternal.framework.binding.command.BindingCommand;

public class ViewAdapter {
    public static void onScrollChangeCommand(ViewPager viewPager, final BindingCommand<ViewPagerDataWrapper> bindingCommand, final BindingCommand<Integer> bindingCommand2, final BindingCommand<Integer> bindingCommand3) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int state;

            public void onPageScrolled(int i, float f, int i2) {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(new ViewPagerDataWrapper((float) i, f, i2, this.state));
                }
            }

            public void onPageSelected(int i) {
                BindingCommand bindingCommand = bindingCommand2;
                if (bindingCommand != null) {
                    bindingCommand.execute(Integer.valueOf(i));
                }
            }

            public void onPageScrollStateChanged(int i) {
                this.state = i;
                BindingCommand bindingCommand = bindingCommand3;
                if (bindingCommand != null) {
                    bindingCommand.execute(Integer.valueOf(i));
                }
            }
        });
    }

    public static class ViewPagerDataWrapper {
        public float position;
        public float positionOffset;
        public int positionOffsetPixels;
        public int state;

        public ViewPagerDataWrapper(float f, float f2, int i, int i2) {
            this.positionOffset = f2;
            this.position = f;
            this.positionOffsetPixels = i;
            this.state = i2;
        }
    }
}
