package com.clj.fastble.callback;

import com.clj.fastble.exception.BleException;

public abstract class BleReadCallback extends BleBaseCallback {
    public abstract void onReadFailure(BleException bleException);

    public abstract void onReadSuccess(byte[] bArr);
}
