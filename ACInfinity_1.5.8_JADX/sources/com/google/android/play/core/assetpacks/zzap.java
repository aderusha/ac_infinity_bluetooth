package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzap extends zzal {
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzap(zzaw zzaw, zzi zzi) {
        super(zzaw, zzi);
        this.zzc = zzaw;
    }

    public final void zzd(Bundle bundle) {
        this.zzc.zzg.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzaw.zza.zzb("onError(%d)", Integer.valueOf(i));
        this.zza.zzd(new AssetPackException(i));
    }

    public final void zzh(Bundle bundle, Bundle bundle2) {
        super.zzh(bundle, bundle2);
        if (!this.zzc.zzh.compareAndSet(true, false)) {
            zzaw.zza.zze("Expected keepingAlive to be true, but was false.", new Object[0]);
        }
        if (bundle.getBoolean("keep_alive")) {
            this.zzc.zzf();
        }
    }
}
