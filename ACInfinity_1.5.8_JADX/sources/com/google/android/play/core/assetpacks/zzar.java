package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzar extends zzal {
    final int zzc;
    final String zzd;
    final int zze;
    final /* synthetic */ zzaw zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzar(zzaw zzaw, zzi zzi, int i, String str, int i2) {
        super(zzaw, zzi);
        this.zzf = zzaw;
        this.zzc = i;
        this.zzd = str;
        this.zze = i2;
    }

    public final void zzd(Bundle bundle) {
        this.zzf.zzf.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzaw.zza.zzb("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(i));
        int i2 = this.zze;
        if (i2 > 0) {
            this.zzf.zzD(this.zzc, this.zzd, i2 - 1);
        }
    }
}
