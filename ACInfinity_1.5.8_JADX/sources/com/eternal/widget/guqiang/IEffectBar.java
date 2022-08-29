package com.eternal.widget.guqiang;

public interface IEffectBar {

    public interface Factory {
        String getText(int i);
    }

    public interface Listener {
        void onChange(int i);

        void onChecked(boolean z);

        void onDown(boolean z);

        void onUp(boolean z, int i);
    }

    int getProgress();

    float getTx();

    boolean isChecked();

    void setFactory(Factory factory);

    void setFillWhenEqual(boolean z);

    void setListener(Listener listener);

    void setProgress(float f, boolean z);
}
