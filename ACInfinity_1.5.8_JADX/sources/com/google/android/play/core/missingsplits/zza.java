package com.google.android.play.core.missingsplits;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.play.core.internal.zzag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zza {
    private static final zzag zza = new zzag("MissingSplitsAppComponentsHelper");
    private final Context zzb;
    private final PackageManager zzc;

    zza(Context context, PackageManager packageManager) {
        this.zzb = context;
        this.zzc = packageManager;
    }

    private final List zzd() {
        try {
            ArrayList arrayList = new ArrayList();
            PackageInfo packageInfo = this.zzc.getPackageInfo(this.zzb.getPackageName(), 526);
            if (packageInfo.providers != null) {
                Collections.addAll(arrayList, packageInfo.providers);
            }
            if (packageInfo.receivers != null) {
                Collections.addAll(arrayList, packageInfo.receivers);
            }
            if (packageInfo.services != null) {
                Collections.addAll(arrayList, packageInfo.services);
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            zza.zze("Failed to resolve own package : %s", e);
            return Collections.emptyList();
        }
    }

    private final void zze(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ComponentInfo componentInfo = (ComponentInfo) it.next();
            this.zzc.setComponentEnabledSetting(new ComponentName(componentInfo.packageName, componentInfo.name), i, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        zza.zzd("Disabling all non-activity components", new Object[0]);
        zze(zzd(), 2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        zza.zzd("Resetting enabled state of all non-activity components", new Object[0]);
        zze(zzd(), 0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        for (ComponentInfo componentInfo : zzd()) {
            if (this.zzc.getComponentEnabledSetting(new ComponentName(componentInfo.packageName, componentInfo.name)) != 2) {
                zza.zza("Not all non-activity components are disabled", new Object[0]);
                return false;
            }
        }
        zza.zza("All non-activity components are disabled", new Object[0]);
        return true;
    }
}
