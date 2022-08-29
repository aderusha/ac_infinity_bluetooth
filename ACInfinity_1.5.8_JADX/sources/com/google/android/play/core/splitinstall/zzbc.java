package com.google.android.play.core.splitinstall;

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

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbc {
    /* access modifiers changed from: private */
    public static final zzag zzb = new zzag("SplitInstallService");
    private static final Intent zzc = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");
    zzas zza;
    /* access modifiers changed from: private */
    public final String zzd;

    zzbc(Context context, String str) {
        this.zzd = str;
        if (zzch.zzb(context)) {
            this.zza = new zzas(zzce.zza(context), zzb, "SplitInstallService", zzc, zzak.zza, (zzam) null);
        }
    }

    static /* bridge */ /* synthetic */ Bundle zza() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        return bundle;
    }

    static /* bridge */ /* synthetic */ ArrayList zzl(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("language", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* bridge */ /* synthetic */ ArrayList zzm(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    private static Task zzn() {
        zzb.zzb("onError(%d)", -14);
        return Tasks.zza(new SplitInstallException(-14));
    }

    public final Task zzc(int i) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("cancelInstall(%d)", Integer.valueOf(i));
        zzi zzi = new zzi();
        this.zza.zzq(new zzas(this, zzi, i, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzd(List list) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("deferredInstall(%s)", list);
        zzi zzi = new zzi();
        this.zza.zzq(new zzan(this, zzi, list, zzi), zzi);
        return zzi.zza();
    }

    public final Task zze(List list) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("deferredLanguageInstall(%s)", list);
        zzi zzi = new zzi();
        this.zza.zzq(new zzao(this, zzi, list, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzf(List list) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("deferredLanguageUninstall(%s)", list);
        zzi zzi = new zzi();
        this.zza.zzq(new zzap(this, zzi, list, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzg(List list) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("deferredUninstall(%s)", list);
        zzi zzi = new zzi();
        this.zza.zzq(new zzam(this, zzi, list, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzh(int i) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("getSessionState(%d)", Integer.valueOf(i));
        zzi zzi = new zzi();
        this.zza.zzq(new zzaq(this, zzi, i, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzi() {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("getSessionStates", new Object[0]);
        zzi zzi = new zzi();
        this.zza.zzq(new zzar(this, zzi, zzi), zzi);
        return zzi.zza();
    }

    public final Task zzj(Collection collection, Collection collection2) {
        if (this.zza == null) {
            return zzn();
        }
        zzb.zzd("startInstall(%s,%s)", collection, collection2);
        zzi zzi = new zzi();
        this.zza.zzq(new zzal(this, zzi, collection, collection2, zzi), zzi);
        return zzi.zza();
    }
}
