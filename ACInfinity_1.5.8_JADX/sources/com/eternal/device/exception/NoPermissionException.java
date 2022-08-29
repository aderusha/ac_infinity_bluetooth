package com.eternal.device.exception;

public class NoPermissionException extends RuntimeException {
    public NoPermissionException() {
        super("no permission");
    }
}
