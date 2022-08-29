package com.eternal.base.exception;

public class NotCloseScanException extends RuntimeException {
    public NotCloseScanException() {
        super("not close scan!");
    }
}
