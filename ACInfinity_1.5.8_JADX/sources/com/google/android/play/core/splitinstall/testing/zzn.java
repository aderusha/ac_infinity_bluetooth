package com.google.android.play.core.splitinstall.testing;

import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ FakeSplitInstallManager zza;
    public final /* synthetic */ List zzb;
    public final /* synthetic */ List zzc;

    public /* synthetic */ zzn(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2) {
        this.zza = fakeSplitInstallManager;
        this.zzb = list;
        this.zzc = list2;
    }

    public final void run() {
        this.zza.zzi(this.zzb, this.zzc);
    }
}
