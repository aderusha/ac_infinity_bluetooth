package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.zzb;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.common.zza;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.splitinstall.zzs;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzl implements AssetPackManager {
    private static final zzag zza = new zzag("AssetPackManager");
    private final zzbh zzb;
    private final zzco zzc;
    private final zzbb zzd;
    private final zzs zze;
    private final zzde zzf;
    private final zzco zzg;
    /* access modifiers changed from: private */
    public final zzbx zzh;
    private final zzco zzi;
    private final zza zzj;
    private final zzeb zzk;
    private final Handler zzl = new Handler(Looper.getMainLooper());
    private boolean zzm;

    zzl(zzbh zzbh, zzco zzco, zzbb zzbb, zzs zzs, zzde zzde, zzco zzco2, zzbx zzbx, zzco zzco3, zza zza2, zzeb zzeb) {
        this.zzb = zzbh;
        this.zzc = zzco;
        this.zzd = zzbb;
        this.zze = zzs;
        this.zzf = zzde;
        this.zzg = zzco2;
        this.zzh = zzbx;
        this.zzi = zzco3;
        this.zzj = zza2;
        this.zzk = zzeb;
    }

    private final void zzh() {
        ((Executor) this.zzi.zza()).execute(new zzi(this));
    }

    public final AssetPackStates cancel(List<String> list) {
        int i;
        List<String> list2 = list;
        Map zzf2 = this.zzf.zzf(list2);
        HashMap hashMap = new HashMap();
        for (String next : list) {
            Integer num = (Integer) zzf2.get(next);
            if (num == null) {
                i = 0;
            } else {
                i = num.intValue();
            }
            hashMap.put(next, AssetPackState.zzb(next, i, 0, 0, 0, 0.0d, 0, "", ""));
        }
        ((zzy) this.zzc.zza()).zze(list2);
        return new zzbo(0, hashMap);
    }

    public final void clearListeners() {
        this.zzd.zze();
    }

    public final Task<AssetPackStates> fetch(List<String> list) {
        Map zzu = this.zzb.zzu();
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        if (!this.zzj.zza("assetOnlyUpdates")) {
            arrayList.removeAll(zzu.keySet());
            arrayList2.addAll(list);
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList.isEmpty()) {
            return ((zzy) this.zzc.zza()).zzc(arrayList2, arrayList, zzu);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("error_code", 0);
        for (String next : list) {
            bundle.putInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, next), 4);
            bundle.putInt(zzb.zza("error_code", next), 0);
            bundle.putLong(zzb.zza("total_bytes_to_download", next), 0);
            bundle.putLong(zzb.zza("bytes_downloaded", next), 0);
        }
        bundle.putStringArrayList("pack_names", new ArrayList(list));
        bundle.putLong("total_bytes_to_download", 0);
        bundle.putLong("bytes_downloaded", 0);
        return Tasks.zzb(AssetPackStates.zza(bundle, this.zzg, this.zzk));
    }

    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation assetPackLocation;
        if (!this.zzm) {
            ((Executor) this.zzi.zza()).execute(new zzh(this));
            this.zzm = true;
        }
        if (this.zzb.zzG(str)) {
            try {
                assetPackLocation = this.zzb.zzf(str);
            } catch (IOException unused) {
            }
        } else {
            if (this.zze.zzc().contains(str)) {
                assetPackLocation = AssetPackLocation.zza();
            }
            assetPackLocation = null;
        }
        if (assetPackLocation == null) {
            return null;
        }
        if (assetPackLocation.packStorageMethod() == 1) {
            zzbh zzbh = this.zzb;
            return zzbh.zzd(str, str2, zzbh.zzs(str));
        } else if (assetPackLocation.packStorageMethod() == 0) {
            return this.zzb.zze(str, str2, assetPackLocation);
        } else {
            zza.zza("The asset %s is not present in Asset Pack %s", str2, str);
            return null;
        }
    }

    public final AssetPackLocation getPackLocation(String str) {
        if (!this.zzm) {
            ((Executor) this.zzi.zza()).execute(new zzh(this));
            this.zzm = true;
        }
        if (this.zzb.zzG(str)) {
            try {
                return this.zzb.zzf(str);
            } catch (IOException unused) {
                return null;
            }
        } else if (this.zze.zzc().contains(str)) {
            return AssetPackLocation.zza();
        } else {
            return null;
        }
    }

    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> zzv = this.zzb.zzv();
        HashMap hashMap = new HashMap();
        for (String put : this.zze.zzc()) {
            hashMap.put(put, AssetPackLocation.zza());
        }
        zzv.putAll(hashMap);
        return zzv;
    }

    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return ((zzy) this.zzc.zza()).zzb(list, new zze(this), this.zzb.zzu());
    }

    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean zzj2 = this.zzd.zzj();
        this.zzd.zzf(assetPackStateUpdateListener);
        if (!zzj2) {
            zzh();
        }
    }

    public final Task<Void> removePack(String str) {
        zzi zzi2 = new zzi();
        ((Executor) this.zzi.zza()).execute(new zzj(this, str, zzi2));
        return zzi2.zza();
    }

    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (activity == null) {
            return Tasks.zza(new AssetPackException(-3));
        }
        if (this.zzh.zza() == null) {
            return Tasks.zza(new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.zzh.zza());
        zzi zzi2 = new zzi();
        intent.putExtra("result_receiver", new zzk(this, this.zzl, zzi2));
        activity.startActivity(intent);
        return zzi2.zza();
    }

    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.zzd.zzh(assetPackStateUpdateListener);
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i, String str) {
        if (!this.zzb.zzG(str) && i == 4) {
            return 8;
        }
        if (!this.zzb.zzG(str) || i == 4) {
            return i;
        }
        return 4;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc() {
        this.zzb.zzy();
        this.zzb.zzw();
        this.zzb.zzx();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str, zzi zzi2) {
        if (this.zzb.zzD(str)) {
            zzi2.zzc((Object) null);
            ((zzy) this.zzc.zza()).zzj(str);
            return;
        }
        zzi2.zzb(new IOException(String.format("Failed to remove pack %s.", new Object[]{str})));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf() {
        Task zzd2 = ((zzy) this.zzc.zza()).zzd(this.zzb.zzu());
        zzbh zzbh = this.zzb;
        zzbh.getClass();
        zzd2.addOnSuccessListener((Executor) this.zzi.zza(), new zzg(zzbh));
        zzd2.addOnFailureListener((Executor) this.zzi.zza(), zzf.zza);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(boolean z) {
        boolean zzj2 = this.zzd.zzj();
        this.zzd.zzg(z);
        if (z && !zzj2) {
            zzh();
        }
    }
}
