package com.eternal.base.exception;

import com.clj.fastble.data.BleDevice;

public class BleDisException extends RuntimeException {
    public BleDisException(BleDevice bleDevice) {
        super("disconnect " + bleDevice.getMac());
    }
}
