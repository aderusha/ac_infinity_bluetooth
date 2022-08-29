package com.clj.fastble.callback;

import com.clj.fastble.exception.BleException;

public abstract class BleMtuChangedCallback extends BleBaseCallback {
    public abstract void onMtuChanged(int i);

    public abstract void onSetMTUFailure(BleException bleException);
}
