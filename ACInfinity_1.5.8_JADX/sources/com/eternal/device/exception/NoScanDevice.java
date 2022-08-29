package com.eternal.device.exception;

public class NoScanDevice extends RuntimeException {
    public NoScanDevice() {
        super("no scanned device!");
    }
}
