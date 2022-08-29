package com.google.android.play.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzci;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzc {
    protected final zzag zza;
    protected final Set zzb = new HashSet();
    private final IntentFilter zzc;
    private final Context zzd;
    private zzb zze = null;
    private volatile boolean zzf = false;

    protected zzc(zzag zzag, IntentFilter intentFilter, Context context) {
        this.zza = zzag;
        this.zzc = intentFilter;
        this.zzd = zzce.zza(context);
    }

    private final void zzb() {
        zzb zzb2;
        if ((this.zzf || !this.zzb.isEmpty()) && this.zze == null) {
            zzb zzb3 = new zzb(this, (zza) null);
            this.zze = zzb3;
            this.zzd.registerReceiver(zzb3, this.zzc);
        }
        if (!this.zzf && this.zzb.isEmpty() && (zzb2 = this.zze) != null) {
            this.zzd.unregisterReceiver(zzb2);
            this.zze = null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, Intent intent);

    public final synchronized void zze() {
        this.zza.zzd("clearListeners", new Object[0]);
        this.zzb.clear();
        zzb();
    }

    public final synchronized void zzf(StateUpdatedListener stateUpdatedListener) {
        this.zza.zzd("registerListener", new Object[0]);
        zzci.zza(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.zzb.add(stateUpdatedListener);
        zzb();
    }

    public final synchronized void zzg(boolean z) {
        this.zzf = z;
        zzb();
    }

    public final synchronized void zzh(StateUpdatedListener stateUpdatedListener) {
        this.zza.zzd("unregisterListener", new Object[0]);
        zzci.zza(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.zzb.remove(stateUpdatedListener);
        zzb();
    }

    public final synchronized void zzi(Object obj) {
        Iterator it = new HashSet(this.zzb).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(obj);
        }
    }

    public final synchronized boolean zzj() {
        return this.zze != null;
    }
}
