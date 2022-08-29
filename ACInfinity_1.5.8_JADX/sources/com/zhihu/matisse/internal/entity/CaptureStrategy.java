package com.zhihu.matisse.internal.entity;

public class CaptureStrategy {
    public final String authority;
    public final String directory;
    public final boolean isPublic;

    public CaptureStrategy(boolean z, String str) {
        this(z, str, (String) null);
    }

    public CaptureStrategy(boolean z, String str, String str2) {
        this.isPublic = z;
        this.authority = str;
        this.directory = str2;
    }
}
