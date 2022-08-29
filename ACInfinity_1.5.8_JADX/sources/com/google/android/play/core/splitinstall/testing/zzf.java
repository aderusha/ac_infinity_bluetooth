package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzf implements zzp {
    public final /* synthetic */ int zza;

    public /* synthetic */ zzf(int i) {
        this.zza = i;
    }

    public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
        int status;
        int i = this.zza;
        int i2 = FakeSplitInstallManager.zza;
        if (splitInstallSessionState != null && i == splitInstallSessionState.sessionId() && ((status = splitInstallSessionState.status()) == 1 || status == 2 || status == 8 || status == 9 || status == 7)) {
            return SplitInstallSessionState.create(i, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
        }
        throw new SplitInstallException(-3);
    }
}
