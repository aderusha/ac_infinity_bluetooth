package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzc extends ResultReceiver {
    final /* synthetic */ zzi zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzc(zzd zzd, Handler handler, zzi zzi) {
        super(handler);
        this.zza = zzi;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        this.zza.zze((Object) null);
    }
}
