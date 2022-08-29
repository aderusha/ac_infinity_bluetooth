package com.google.android.play.core.assetpacks.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
/* compiled from: com.google.android.play:core@@1.10.3 */
public @interface AssetPackErrorCode {
    public static final int ACCESS_DENIED = -7;
    public static final int API_NOT_AVAILABLE = -5;
    public static final int APP_NOT_OWNED = -13;
    public static final int APP_UNAVAILABLE = -1;
    public static final int DOWNLOAD_NOT_FOUND = -4;
    public static final int INSUFFICIENT_STORAGE = -10;
    public static final int INTERNAL_ERROR = -100;
    public static final int INVALID_REQUEST = -3;
    public static final int NETWORK_ERROR = -6;
    public static final int NO_ERROR = 0;
    public static final int PACK_UNAVAILABLE = -2;
}
