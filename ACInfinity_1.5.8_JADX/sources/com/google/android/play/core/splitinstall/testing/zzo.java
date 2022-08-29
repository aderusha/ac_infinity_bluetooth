package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.zzf;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzo implements zzf {
    final /* synthetic */ List zza;
    final /* synthetic */ List zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ List zze;
    final /* synthetic */ FakeSplitInstallManager zzf;

    zzo(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, long j, boolean z, List list3) {
        this.zzf = fakeSplitInstallManager;
        this.zza = list;
        this.zzb = list2;
        this.zzc = j;
        this.zzd = z;
        this.zze = list3;
    }

    public final void zza() {
        this.zzf.zzr(this.zza, this.zzb, this.zzc);
    }

    public final void zzb(int i) {
        boolean unused = this.zzf.zzs(6, i, (Long) null, (Long) null, (List) null, (Integer) null, (List) null);
    }

    public final void zzc() {
        if (!this.zzd) {
            this.zzf.zzp(this.zze, this.zza, this.zzb, this.zzc, true);
        }
    }
}
