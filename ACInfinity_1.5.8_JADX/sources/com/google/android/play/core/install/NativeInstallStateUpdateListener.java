package com.google.android.play.core.install;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class NativeInstallStateUpdateListener implements InstallStateUpdatedListener {
    NativeInstallStateUpdateListener() {
    }

    public native void onStateUpdate(InstallState installState);
}
