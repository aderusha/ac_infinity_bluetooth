package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzi implements zzp {
    public final /* synthetic */ Integer zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ int zzc;
    public final /* synthetic */ Long zzd;
    public final /* synthetic */ Long zze;
    public final /* synthetic */ List zzf;
    public final /* synthetic */ List zzg;

    public /* synthetic */ zzi(Integer num, int i, int i2, Long l, Long l2, List list, List list2) {
        this.zza = num;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = l;
        this.zze = l2;
        this.zzf = list;
        this.zzg = list2;
    }

    public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
        long j;
        Integer num = this.zza;
        int i = this.zzb;
        int i2 = this.zzc;
        Long l = this.zzd;
        Long l2 = this.zze;
        List<String> list = this.zzf;
        List<String> list2 = this.zzg;
        int i3 = FakeSplitInstallManager.zza;
        SplitInstallSessionState create = splitInstallSessionState == null ? SplitInstallSessionState.create(0, 0, 0, 0, 0, new ArrayList(), new ArrayList()) : splitInstallSessionState;
        int sessionId = num == null ? create.sessionId() : num.intValue();
        long bytesDownloaded = l == null ? create.bytesDownloaded() : l.longValue();
        if (l2 == null) {
            j = create.totalBytesToDownload();
        } else {
            j = l2.longValue();
        }
        return SplitInstallSessionState.create(sessionId, i, i2, bytesDownloaded, j, list == null ? create.moduleNames() : list, list2 == null ? create.languages() : list2);
    }
}
