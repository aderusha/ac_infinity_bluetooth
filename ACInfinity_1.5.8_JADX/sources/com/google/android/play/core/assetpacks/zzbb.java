package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.listener.zzc;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbb extends zzc {
    private final zzde zzc;
    private final zzcl zzd;
    private final zzco zze;
    private final zzbx zzf;
    private final zzco zzg;
    private final zzco zzh;
    private final zzco zzi;
    private final zzeb zzj;
    private final Handler zzk = new Handler(Looper.getMainLooper());

    zzbb(Context context, zzde zzde, zzcl zzcl, zzco zzco, zzco zzco2, zzbx zzbx, zzco zzco3, zzco zzco4, zzeb zzeb) {
        super(new zzag("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.zzc = zzde;
        this.zzd = zzcl;
        this.zze = zzco;
        this.zzg = zzco2;
        this.zzf = zzbx;
        this.zzh = zzco3;
        this.zzi = zzco4;
        this.zzj = zzeb;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra != null) {
            ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
            if (stringArrayList == null || stringArrayList.size() != 1) {
                this.zza.zzb("Corrupt bundle received from broadcast.", new Object[0]);
                return;
            }
            AssetPackState zzc2 = AssetPackState.zzc(bundleExtra, stringArrayList.get(0), this.zzg, this.zzj, zzbd.zza);
            this.zza.zza("ListenerRegistryBroadcastReceiver.onReceive: %s", zzc2);
            PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
            if (pendingIntent != null) {
                this.zzf.zzb(pendingIntent);
            }
            ((Executor) this.zzi.zza()).execute(new zzaz(this, bundleExtra, zzc2));
            ((Executor) this.zzh.zza()).execute(new zzay(this, bundleExtra));
            return;
        }
        this.zza.zzb("Empty bundle received from broadcast.", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Bundle bundle) {
        if (this.zzc.zzp(bundle)) {
            this.zzd.zza();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Bundle bundle, AssetPackState assetPackState) {
        if (this.zzc.zzo(bundle)) {
            zzd(assetPackState);
            ((zzy) this.zze.zza()).zzf();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(AssetPackState assetPackState) {
        this.zzk.post(new zzba(this, assetPackState));
    }
}
