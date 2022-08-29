package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzad extends zzah {
    final /* synthetic */ List zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(zzaw zzaw, zzi zzi, List list, zzi zzi2) {
        super(zzi);
        this.zzc = zzaw;
        this.zza = list;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzc.zzf.zze()).zzc(this.zzc.zzc, zzaw.zzv(this.zza), zzaw.zzA(), new zzam(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "cancelDownloads(%s)", this.zza);
        }
    }
}
