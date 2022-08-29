package com.trello.rxlifecycle2;

import javax.annotation.Nullable;

public class OutsideLifecycleException extends IllegalStateException {
    public OutsideLifecycleException(@Nullable String str) {
        super(str);
    }
}
