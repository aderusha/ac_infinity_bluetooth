package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzam;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzaw implements zzy {
    /* access modifiers changed from: private */
    public static final zzag zza = new zzag("AssetPackServiceImpl");
    private static final Intent zzb = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    /* access modifiers changed from: private */
    public final String zzc;
    /* access modifiers changed from: private */
    public final zzco zzd;
    /* access modifiers changed from: private */
    public final zzeb zze;
    /* access modifiers changed from: private */
    public zzas zzf;
    /* access modifiers changed from: private */
    public zzas zzg;
    /* access modifiers changed from: private */
    public final AtomicBoolean zzh = new AtomicBoolean();

    zzaw(Context context, zzco zzco, zzeb zzeb) {
        this.zzc = context.getPackageName();
        this.zzd = zzco;
        this.zze = zzeb;
        if (zzch.zzb(context)) {
            Context zza2 = zzce.zza(context);
            zzag zzag = zza;
            Intent intent = zzb;
            this.zzf = new zzas(zza2, zzag, "AssetPackService", intent, zzz.zza, (zzam) null);
            this.zzg = new zzas(zzce.zza(context), zzag, "AssetPackService-keepAlive", intent, zzz.zza, (zzam) null);
        }
        zza.zza("AssetPackService initiated.", new Object[0]);
    }

    /* access modifiers changed from: private */
    public static Bundle zzA() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(1);
        arrayList2.add(2);
        bundle.putIntegerArrayList("supported_patch_formats", arrayList2);
        return bundle;
    }

    /* access modifiers changed from: private */
    public static Bundle zzB(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", i);
        return bundle;
    }

    private static Task zzC() {
        zza.zzb("onError(%d)", -11);
        return Tasks.zza(new AssetPackException(-11));
    }

    /* access modifiers changed from: private */
    public final void zzD(int i, String str, int i2) {
        if (this.zzf != null) {
            zza.zzd("notifyModuleCompleted", new Object[0]);
            zzi zzi = new zzi();
            this.zzf.zzq(new zzah(this, zzi, i, str, zzi, i2), zzi);
            return;
        }
        throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
    }

    static /* bridge */ /* synthetic */ Bundle zzk(int i, String str, String str2, int i2) {
        Bundle zzz = zzz(i, str);
        zzz.putString("slice_id", str2);
        zzz.putInt("chunk_number", i2);
        return zzz;
    }

    static /* bridge */ /* synthetic */ Bundle zzn(Map map) {
        Bundle zzA = zzA();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        zzA.putParcelableArrayList("installed_asset_module", arrayList);
        return zzA;
    }

    static /* bridge */ /* synthetic */ ArrayList zzv(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* bridge */ /* synthetic */ List zzw(zzaw zzaw, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssetPackState next = AssetPackStates.zza((Bundle) it.next(), zzaw.zzd, zzaw.zze).packStates().values().iterator().next();
            if (next == null) {
                zza.zzb("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (zzbg.zza(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static Bundle zzz(int i, String str) {
        Bundle zzB = zzB(i);
        zzB.putString("module_name", str);
        return zzB;
    }

    public final Task zza(int i, String str, String str2, int i2) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i2), Integer.valueOf(i));
        zzi zzi = new zzi();
        this.zzf.zzq(new zzaj(this, zzi, i, str, str2, i2, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzb(List list, zzbe zzbe, Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("getPackStates(%s)", list);
        zzi zzi = new zzi();
        this.zzf.zzq(new zzaf(this, zzi, list, map, zzi, zzbe), zzi);
        return zzi.zza();
    }

    public final Task zzc(List list, List list2, Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("startDownload(%s)", list2);
        zzi zzi = new zzi();
        this.zzf.zzq(new zzac(this, zzi, list2, map, zzi, list), zzi);
        zzi.zza().addOnSuccessListener(new zzaa(this));
        return zzi.zza();
    }

    public final Task zzd(Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("syncPacks", new Object[0]);
        zzi zzi = new zzi();
        this.zzf.zzq(new zzae(this, zzi, map, zzi), zzi);
        return zzi.zza();
    }

    public final void zze(List list) {
        if (this.zzf != null) {
            zza.zzd("cancelDownloads(%s)", list);
            zzi zzi = new zzi();
            this.zzf.zzq(new zzad(this, zzi, list, zzi), zzi);
        }
    }

    public final synchronized void zzf() {
        if (this.zzg == null) {
            zza.zze("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        zzag zzag = zza;
        zzag.zzd("keepAlive", new Object[0]);
        if (!this.zzh.compareAndSet(false, true)) {
            zzag.zzd("Service is already kept alive.", new Object[0]);
            return;
        }
        zzi zzi = new zzi();
        this.zzg.zzq(new zzak(this, zzi, zzi), zzi);
    }

    public final void zzg(int i, String str, String str2, int i2) {
        if (this.zzf != null) {
            zza.zzd("notifyChunkTransferred", new Object[0]);
            zzi zzi = new zzi();
            this.zzf.zzq(new zzag(this, zzi, i, str, str2, i2, zzi), zzi);
            return;
        }
        throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
    }

    public final void zzh(int i, String str) {
        zzD(i, str, 10);
    }

    public final void zzi(int i) {
        if (this.zzf != null) {
            zza.zzd("notifySessionFailed", new Object[0]);
            zzi zzi = new zzi();
            this.zzf.zzq(new zzai(this, zzi, i, zzi), zzi);
            return;
        }
        throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
    }

    public final void zzj(String str) {
        if (this.zzf != null) {
            zza.zzd("removePack(%s)", str);
            zzi zzi = new zzi();
            this.zzf.zzq(new zzab(this, zzi, str, zzi), zzi);
        }
    }
}
