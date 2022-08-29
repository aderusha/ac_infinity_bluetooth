package com.eternal.widget.guqiang;

import android.view.View;
import androidx.databinding.InverseBindingListener;
import com.eternal.widget.guqiang.SingleProgressBar;

public class SingleProgressBarAdapter {
    public static void setProgress(SingleProgressBar singleProgressBar, int i) {
        if (singleProgressBar.getProgress() != i) {
            singleProgressBar.setShowText(i);
        }
    }

    public static int getProgress(SingleProgressBar singleProgressBar) {
        return singleProgressBar.getProgress();
    }

    public static void progressAttrChanged(SingleProgressBar singleProgressBar, final InverseBindingListener inverseBindingListener) {
        singleProgressBar.setOnChangeListener(new SingleProgressBar.OnChangeListener() {
            public void onDown() {
            }

            public void onEnd() {
            }

            public void onChange(View view, int i) {
                InverseBindingListener.this.onChange();
            }
        });
    }
}
