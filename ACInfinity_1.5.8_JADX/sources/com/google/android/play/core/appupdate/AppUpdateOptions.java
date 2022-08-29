package com.google.android.play.core.appupdate;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class AppUpdateOptions {

    /* compiled from: com.google.android.play:core@@1.10.3 */
    public static abstract class Builder {
        public abstract AppUpdateOptions build();

        public abstract Builder setAllowAssetPackDeletion(boolean z);

        public abstract Builder setAppUpdateType(int i);
    }

    public static AppUpdateOptions defaultOptions(int i) {
        return newBuilder(i).build();
    }

    public static Builder newBuilder(int i) {
        zzu zzu = new zzu();
        zzu.setAppUpdateType(i);
        zzu.setAllowAssetPackDeletion(false);
        return zzu;
    }

    public abstract boolean allowAssetPackDeletion();

    public abstract int appUpdateType();
}
