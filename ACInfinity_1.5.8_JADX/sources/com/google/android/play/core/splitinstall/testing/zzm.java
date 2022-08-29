package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallSessionState;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzm implements Runnable {
    public final /* synthetic */ FakeSplitInstallManager zza;
    public final /* synthetic */ SplitInstallSessionState zzb;

    public /* synthetic */ zzm(FakeSplitInstallManager fakeSplitInstallManager, SplitInstallSessionState splitInstallSessionState) {
        this.zza = fakeSplitInstallManager;
        this.zzb = splitInstallSessionState;
    }

    public final void run() {
        this.zza.zzg(this.zzb);
    }
}
