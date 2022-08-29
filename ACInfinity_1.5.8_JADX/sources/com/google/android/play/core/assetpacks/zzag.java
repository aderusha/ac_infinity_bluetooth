package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzag extends zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ int zzd;
    final /* synthetic */ zzi zze;
    final /* synthetic */ zzaw zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzag(zzaw zzaw, zzi zzi, int i, String str, String str2, int i2, zzi zzi2) {
        super(zzi);
        this.zzf = zzaw;
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = i2;
        this.zze = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzf.zzf.zze()).zzg(this.zzf.zzc, zzaw.zzk(this.zza, this.zzb, this.zzc, this.zzd), zzaw.zzA(), new zzaq(this.zzf, this.zze));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "notifyChunkTransferred", new Object[0]);
        }
    }
}
