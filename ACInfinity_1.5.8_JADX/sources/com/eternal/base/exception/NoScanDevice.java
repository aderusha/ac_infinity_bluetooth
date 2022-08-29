package com.eternal.base.exception;

public class NoScanDevice extends RuntimeException {
    public NoScanDevice() {
        super("no scanned device!");
    }
}
