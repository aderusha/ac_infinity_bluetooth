package com.google.android.play.core.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzb extends BroadcastReceiver {
    final /* synthetic */ zzc zza;

    /* synthetic */ zzb(zzc zzc, zza zza2) {
        this.zza = zzc;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zza.zza(context, intent);
    }
}
