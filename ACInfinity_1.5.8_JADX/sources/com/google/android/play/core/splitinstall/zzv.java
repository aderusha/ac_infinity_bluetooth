package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzv implements zzf {
    final /* synthetic */ SplitInstallSessionState zza;
    final /* synthetic */ Intent zzb;
    final /* synthetic */ Context zzc;
    final /* synthetic */ zzx zzd;

    zzv(zzx zzx, SplitInstallSessionState splitInstallSessionState, Intent intent, Context context) {
        this.zzd = zzx;
        this.zza = splitInstallSessionState;
        this.zzb = intent;
        this.zzc = context;
    }

    public final void zza() {
        this.zzd.zzd.post(new zzw(this.zzd, this.zza, 5, 0));
    }

    public final void zzb(int i) {
        this.zzd.zzd.post(new zzw(this.zzd, this.zza, 6, i));
    }

    public final void zzc() {
        if (!this.zzb.getBooleanExtra("triggered_from_app_after_verification", false)) {
            this.zzb.putExtra("triggered_from_app_after_verification", true);
            this.zzc.sendBroadcast(this.zzb);
            return;
        }
        this.zzd.zza.zzb("Splits copied and verified more than once.", new Object[0]);
    }
}
