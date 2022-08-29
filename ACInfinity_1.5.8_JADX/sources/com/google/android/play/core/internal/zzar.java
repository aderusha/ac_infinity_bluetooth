package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzar implements ServiceConnection {
    final /* synthetic */ zzas zza;

    /* synthetic */ zzar(zzas zzas, zzaq zzaq) {
        this.zza = zzas;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        zzas zzas = this.zza;
        zzas.zzc().post(new zzao(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        zzas zzas = this.zza;
        zzas.zzc().post(new zzap(this));
    }
}
