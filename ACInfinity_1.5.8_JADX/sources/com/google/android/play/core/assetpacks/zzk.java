package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzk extends ResultReceiver {
    final /* synthetic */ zzi zza;
    final /* synthetic */ zzl zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(zzl zzl, Handler handler, zzi zzi) {
        super(handler);
        this.zzb = zzl;
        this.zza = zzi;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.zza.zze(-1);
            this.zzb.zzh.zzb((PendingIntent) null);
        } else if (i != 2) {
            this.zza.zzd(new AssetPackException(-100));
        } else {
            this.zza.zze(0);
        }
    }
}
