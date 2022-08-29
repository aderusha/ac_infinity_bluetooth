package com.trello.rxlifecycle2.internal;

import java.util.Objects;

public final class Preconditions {
    public static <T> T checkNotNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
