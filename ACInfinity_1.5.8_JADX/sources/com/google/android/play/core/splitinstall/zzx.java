package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.listener.zzc;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzx extends zzc {
    private static zzx zzc;
    private final Handler zzd = new Handler(Looper.getMainLooper());
    private final zzg zze;
    private final Set zzf = new LinkedHashSet();

    public zzx(Context context, zzg zzg) {
        super(new zzag("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.zze = zzg;
    }

    public static synchronized zzx zzc(Context context) {
        zzx zzx;
        synchronized (zzx.class) {
            if (zzc == null) {
                zzc = new zzx(context, zzo.INSTANCE);
            }
            zzx = zzc;
        }
        return zzx;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra != null) {
            SplitInstallSessionState zzd2 = SplitInstallSessionState.zzd(bundleExtra);
            this.zza.zza("ListenerRegistryBroadcastReceiver.onReceive: %s", zzd2);
            zzh zza = this.zze.zza();
            if (zzd2.status() != 3 || zza == null) {
                zzm(zzd2);
            } else {
                zza.zzd(zzd2.zzc(), new zzv(this, zzd2, intent, context));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzk(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzf.add(splitInstallStateUpdatedListener);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzl(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzf.remove(splitInstallStateUpdatedListener);
    }

    public final synchronized void zzm(SplitInstallSessionState splitInstallSessionState) {
        Iterator it = new LinkedHashSet(this.zzf).iterator();
        while (it.hasNext()) {
            ((SplitInstallStateUpdatedListener) it.next()).onStateUpdate(splitInstallSessionState);
        }
        super.zzi(splitInstallSessionState);
    }
}
