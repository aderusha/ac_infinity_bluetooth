package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.zzi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzas {
    private static final Map zza = new HashMap();
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final zzag zzc;
    private final String zzd;
    /* access modifiers changed from: private */
    public final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    private final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh;
    private final Intent zzi;
    /* access modifiers changed from: private */
    public final zzan zzj;
    private final WeakReference zzk;
    private final IBinder.DeathRecipient zzl = new zzai(this);
    private final AtomicInteger zzm = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public ServiceConnection zzn;
    /* access modifiers changed from: private */
    public IInterface zzo;

    public zzas(Context context, zzag zzag, String str, Intent intent, zzan zzan, zzam zzam) {
        this.zzb = context;
        this.zzc = zzag;
        this.zzd = str;
        this.zzi = intent;
        this.zzj = zzan;
        this.zzk = new WeakReference((Object) null);
    }

    public static /* synthetic */ void zzi(zzas zzas) {
        zzas.zzc.zzd("reportBinderDeath", new Object[0]);
        zzam zzam = (zzam) zzas.zzk.get();
        if (zzam != null) {
            zzas.zzc.zzd("calling onBinderDied", new Object[0]);
            zzam.zza();
        } else {
            zzas.zzc.zzd("%s : Binder has died.", zzas.zzd);
            for (zzah zzc2 : zzas.zze) {
                zzc2.zzc(zzas.zzt());
            }
            zzas.zze.clear();
        }
        zzas.zzu();
    }

    static /* bridge */ /* synthetic */ void zzo(zzas zzas) {
        zzas.zzc.zzd("linkToDeath", new Object[0]);
        try {
            zzas.zzo.asBinder().linkToDeath(zzas.zzl, 0);
        } catch (RemoteException e) {
            zzas.zzc.zzc(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void zzp(zzas zzas) {
        zzas.zzc.zzd("unlinkToDeath", new Object[0]);
        zzas.zzo.asBinder().unlinkToDeath(zzas.zzl, 0);
    }

    private final RemoteException zzt() {
        if (Build.VERSION.SDK_INT < 15) {
            return new RemoteException();
        }
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    /* access modifiers changed from: private */
    public final void zzu() {
        synchronized (this.zzg) {
            for (zzi zzd2 : this.zzf) {
                zzd2.zzd(zzt());
            }
            this.zzf.clear();
        }
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            if (!map.containsKey(this.zzd)) {
                HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                handlerThread.start();
                map.put(this.zzd, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.zzd);
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzo;
    }

    public final void zzq(zzah zzah, zzi zzi2) {
        synchronized (this.zzg) {
            this.zzf.add(zzi2);
            zzi2.zza().addOnCompleteListener(new zzaj(this, zzi2));
        }
        synchronized (this.zzg) {
            if (this.zzm.getAndIncrement() > 0) {
                this.zzc.zza("Already connected to the service.", new Object[0]);
            }
        }
        zzc().post(new zzak(this, zzah.zzb(), zzah));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzr(zzi zzi2, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(zzi2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        zzc().post(new com.google.android.play.core.internal.zzal(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzs(com.google.android.play.core.tasks.zzi r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzg
            monitor-enter(r0)
            java.util.Set r1 = r3.zzf     // Catch:{ all -> 0x0039 }
            r1.remove(r4)     // Catch:{ all -> 0x0039 }
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            java.lang.Object r4 = r3.zzg
            monitor-enter(r4)
            java.util.concurrent.atomic.AtomicInteger r0 = r3.zzm     // Catch:{ all -> 0x0036 }
            int r0 = r0.get()     // Catch:{ all -> 0x0036 }
            if (r0 <= 0) goto L_0x0028
            java.util.concurrent.atomic.AtomicInteger r0 = r3.zzm     // Catch:{ all -> 0x0036 }
            int r0 = r0.decrementAndGet()     // Catch:{ all -> 0x0036 }
            if (r0 <= 0) goto L_0x0028
            com.google.android.play.core.internal.zzag r0 = r3.zzc     // Catch:{ all -> 0x0036 }
            java.lang.String r1 = "Leaving the connection open for other ongoing calls."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0036 }
            r0.zzd(r1, r2)     // Catch:{ all -> 0x0036 }
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            return
        L_0x0028:
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            com.google.android.play.core.internal.zzal r4 = new com.google.android.play.core.internal.zzal
            r4.<init>(r3)
            android.os.Handler r0 = r3.zzc()
            r0.post(r4)
            return
        L_0x0036:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            throw r0
        L_0x0039:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.zzas.zzs(com.google.android.play.core.tasks.zzi):void");
    }

    static /* bridge */ /* synthetic */ void zzn(zzas zzas, zzah zzah) {
        if (zzas.zzo == null && !zzas.zzh) {
            zzas.zzc.zzd("Initiate binding to the service.", new Object[0]);
            zzas.zze.add(zzah);
            zzar zzar = new zzar(zzas, (zzaq) null);
            zzas.zzn = zzar;
            zzas.zzh = true;
            if (!zzas.zzb.bindService(zzas.zzi, zzar, 1)) {
                zzas.zzc.zzd("Failed to bind to the service.", new Object[0]);
                zzas.zzh = false;
                for (zzah zzc2 : zzas.zze) {
                    zzc2.zzc(new zzat());
                }
                zzas.zze.clear();
            }
        } else if (zzas.zzh) {
            zzas.zzc.zzd("Waiting to bind to the service.", new Object[0]);
            zzas.zze.add(zzah);
        } else {
            zzah.run();
        }
    }
}
