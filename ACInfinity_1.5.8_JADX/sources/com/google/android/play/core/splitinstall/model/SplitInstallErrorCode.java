package com.google.android.play.core.splitinstall.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
/* compiled from: com.google.android.play:core@@1.10.3 */
public @interface SplitInstallErrorCode {
    public static final int ACCESS_DENIED = -7;
    public static final int ACTIVE_SESSIONS_LIMIT_EXCEEDED = -1;
    public static final int API_NOT_AVAILABLE = -5;
    public static final int APP_NOT_OWNED = -15;
    public static final int INCOMPATIBLE_WITH_EXISTING_SESSION = -8;
    public static final int INSUFFICIENT_STORAGE = -10;
    public static final int INTERNAL_ERROR = -100;
    public static final int INVALID_REQUEST = -3;
    public static final int MODULE_UNAVAILABLE = -2;
    public static final int NETWORK_ERROR = -6;
    public static final int NO_ERROR = 0;
    public static final int PLAY_STORE_NOT_FOUND = -14;
    @Deprecated
    public static final int SERVICE_DIED = -9;
    public static final int SESSION_NOT_FOUND = -4;
    public static final int SPLITCOMPAT_COPY_ERROR = -13;
    public static final int SPLITCOMPAT_EMULATION_ERROR = -12;
    public static final int SPLITCOMPAT_VERIFICATION_ERROR = -11;
}
