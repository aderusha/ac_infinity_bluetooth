package com.eternal.data.p007ui;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* renamed from: com.eternal.data.ui.TimeValue */
public class TimeValue {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
    final long time;
    final int value;

    public TimeValue(long j, int i) {
        this.time = j;
        this.value = i;
    }

    public String toString() {
        return "TimeValue{time=" + format.format(Long.valueOf(this.time * 1000)) + ", value=" + this.value + '}';
    }
}
