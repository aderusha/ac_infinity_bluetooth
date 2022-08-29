package com.eternal.framework.utils.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class MemoryConstants {
    public static final int BYTE = 1;

    /* renamed from: GB */
    public static final int f192GB = 1073741824;

    /* renamed from: KB */
    public static final int f193KB = 1024;

    /* renamed from: MB */
    public static final int f194MB = 1048576;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
