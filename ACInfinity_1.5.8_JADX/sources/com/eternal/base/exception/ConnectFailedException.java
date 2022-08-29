package com.eternal.base.exception;

public class ConnectFailedException extends RuntimeException {
    public String device_id;

    public ConnectFailedException(String str, String str2) {
        super(str);
        this.device_id = str2;
    }
}
