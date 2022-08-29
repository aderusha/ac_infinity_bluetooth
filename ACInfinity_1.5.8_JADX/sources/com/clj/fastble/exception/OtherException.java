package com.clj.fastble.exception;

public class OtherException extends BleException {
    public OtherException(String str) {
        super(102, str);
    }
}
