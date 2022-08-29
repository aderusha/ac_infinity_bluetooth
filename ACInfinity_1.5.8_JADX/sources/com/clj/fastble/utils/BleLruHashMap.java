package com.clj.fastble.utils;

import com.clj.fastble.bluetooth.BleBluetooth;
import java.util.LinkedHashMap;
import java.util.Map;

public class BleLruHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_SIZE;

    public BleLruHashMap(int i) {
        super(((int) Math.ceil(((double) i) / 0.75d)) + 1, 0.75f, true);
        this.MAX_SIZE = i;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry entry) {
        if (size() > this.MAX_SIZE && (entry.getValue() instanceof BleBluetooth)) {
            ((BleBluetooth) entry.getValue()).disconnect();
        }
        return size() > this.MAX_SIZE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : entrySet()) {
            sb.append(String.format("%s:%s ", new Object[]{entry.getKey(), entry.getValue()}));
        }
        return sb.toString();
    }
}
