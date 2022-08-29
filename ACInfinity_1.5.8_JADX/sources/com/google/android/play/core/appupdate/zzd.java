package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzd extends ResultReceiver {
    final /* synthetic */ zzi zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzd(zzf zzf, Handler handler, zzi zzi) {
        super(handler);
        this.zza = zzi;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.zza.zze(-1);
        } else if (i != 2) {
            this.zza.zze(1);
        } else {
            this.zza.zze(0);
        }
    }
}
