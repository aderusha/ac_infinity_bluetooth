package com.eternal.framework.utils.compression;

import java.util.Objects;

final class Preconditions {
    Preconditions() {
    }

    static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
