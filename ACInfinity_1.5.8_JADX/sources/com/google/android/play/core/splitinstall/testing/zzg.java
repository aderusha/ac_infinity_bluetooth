package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallSessionState;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzg implements zzp {
    public final /* synthetic */ int zza;

    public /* synthetic */ zzg(int i) {
        this.zza = i;
    }

    public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
        int i = this.zza;
        int i2 = FakeSplitInstallManager.zza;
        if (splitInstallSessionState == null) {
            return null;
        }
        return SplitInstallSessionState.create(splitInstallSessionState.sessionId(), 6, i, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
    }
}
