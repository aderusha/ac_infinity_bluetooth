package com.eternal.base.global;

import com.eternal.widget.guqiang.IProgressCallback;

public class ProgressEvent {
    public static final byte END = 1;
    public static final byte INIT = 3;
    public static final byte SET = 2;
    public static final byte START = 0;
    public final byte Statue;
    public final IProgressCallback callback;
    public final float percent;

    public ProgressEvent(byte b, float f) {
        this.Statue = b;
        this.percent = f;
        this.callback = null;
    }

    public ProgressEvent(byte b) {
        this(b, 0.0f);
    }

    public ProgressEvent(IProgressCallback iProgressCallback) {
        this.Statue = 1;
        this.percent = 100.0f;
        this.callback = iProgressCallback;
    }
}
