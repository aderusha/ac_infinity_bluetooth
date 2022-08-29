package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzz;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzci implements ServiceConnection {
    private final zzag zza = new zzag("ExtractionForegroundServiceConnection");
    private final List zzb = new ArrayList();
    private final Context zzc;
    private ExtractionForegroundService zzd;
    private Notification zze;

    zzci(Context context) {
        this.zzc = context;
    }

    private final void zzd() {
        ArrayList arrayList;
        synchronized (this.zzb) {
            arrayList = new ArrayList(this.zzb);
            this.zzb.clear();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            try {
                ((zzz) arrayList.get(i)).zze(new Bundle(), new Bundle());
            } catch (RemoteException unused) {
                this.zza.zzb("Could not resolve Play Store service state update callback.", new Object[0]);
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zza("Starting foreground installation service.", new Object[0]);
        ExtractionForegroundService extractionForegroundService = ((zzch) iBinder).zza;
        this.zzd = extractionForegroundService;
        extractionForegroundService.startForeground(-1883842196, this.zze);
        zzd();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }

    /* access modifiers changed from: package-private */
    public final void zza(Notification notification) {
        this.zze = notification;
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        this.zza.zza("Stopping foreground installation service.", new Object[0]);
        this.zzc.unbindService(this);
        ExtractionForegroundService extractionForegroundService = this.zzd;
        if (extractionForegroundService != null) {
            extractionForegroundService.zza();
        }
        zzd();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzz zzz) {
        synchronized (this.zzb) {
            this.zzb.add(zzz);
        }
    }
}
