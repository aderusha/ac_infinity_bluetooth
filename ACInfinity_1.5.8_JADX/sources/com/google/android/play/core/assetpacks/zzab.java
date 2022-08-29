package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzab extends zzah {
    final /* synthetic */ String zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzaw zzaw, zzi zzi, String str, zzi zzi2) {
        super(zzi);
        this.zzc = zzaw;
        this.zza = str;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzc.zzf.zze()).zzj(this.zzc.zzc, zzaw.zzz(0, this.zza), zzaw.zzA(), new zzat(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "removePack(%s)", this.zza);
        }
    }
}
