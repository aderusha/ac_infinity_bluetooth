package com.eternal.base.concat;

import android.util.SparseArray;

public class LogExtra {
    public boolean hideAutoTime;
    public boolean isDegree;
    public final SparseArray<String> notifyName;
    public byte portCount;
    public final long time;

    public LogExtra(long j, boolean z, SparseArray<String> sparseArray, byte b) {
        this.time = j;
        this.isDegree = z;
        this.notifyName = sparseArray;
    }
}
