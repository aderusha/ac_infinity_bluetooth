package com.clj.fastble.exception;

public class TimeoutException extends BleException {
    public TimeoutException() {
        super(100, "Timeout Exception Occurred!");
    }
}
