package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzam;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzq {
    /* access modifiers changed from: private */
    public static final zzag zzb = new zzag("AppUpdateService");
    private static final Intent zzc = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");
    zzas zza;
    /* access modifiers changed from: private */
    public final String zzd;
    private final Context zze;
    private final zzs zzf;

    zzq(Context context, zzs zzs) {
        this.zzd = context.getPackageName();
        this.zze = context;
        this.zzf = zzs;
        if (zzch.zzb(context)) {
            this.zza = new zzas(zzce.zza(context), zzb, "AppUpdateService", zzc, zzk.zza, (zzam) null);
        }
    }

    static /* bridge */ /* synthetic */ Bundle zzb(zzq zzq, String str) {
        Integer num;
        Bundle bundle = new Bundle();
        bundle.putAll(zzi());
        bundle.putString("package.name", str);
        try {
            num = Integer.valueOf(zzq.zze.getPackageManager().getPackageInfo(zzq.zze.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            zzb.zzb("The current version of the app could not be retrieved", new Object[0]);
            num = null;
        }
        if (num != null) {
            bundle.putInt("app.version.code", num.intValue());
        }
        return bundle;
    }

    static /* bridge */ /* synthetic */ AppUpdateInfo zzd(zzq zzq, Bundle bundle, String str) {
        Integer num;
        Bundle bundle2 = bundle;
        int i = bundle2.getInt("version.code", -1);
        int i2 = bundle2.getInt("update.availability");
        int i3 = bundle2.getInt("install.status", 0);
        if (bundle2.getInt("client.version.staleness", -1) == -1) {
            num = null;
        } else {
            num = Integer.valueOf(bundle2.getInt("client.version.staleness"));
        }
        return AppUpdateInfo.zzb(str, i, i2, i3, num, bundle2.getInt("in.app.update.priority", 0), bundle2.getLong("bytes.downloaded"), bundle2.getLong("total.bytes.to.download"), bundle2.getLong("additional.size.required"), zzq.zzf.zza(), (PendingIntent) bundle2.getParcelable("blocking.intent"), (PendingIntent) bundle2.getParcelable("nonblocking.intent"), (PendingIntent) bundle2.getParcelable("blocking.destructive.intent"), (PendingIntent) bundle2.getParcelable("nonblocking.destructive.intent"));
    }

    /* access modifiers changed from: private */
    public static Bundle zzi() {
        Bundle bundle = new Bundle();
        bundle.putAll(PlayCoreVersion.zza("app_update"));
        bundle.putInt("playcore.version.code", 11003);
        return bundle;
    }

    private static Task zzj() {
        zzb.zzb("onError(%d)", -9);
        return Tasks.zza(new InstallException(-9));
    }

    public final Task zzf(String str) {
        if (this.zza == null) {
            return zzj();
        }
        zzb.zzd("completeUpdate(%s)", str);
        zzi zzi = new zzi();
        this.zza.zzq(new zzm(this, zzi, zzi, str), zzi);
        return zzi.zza();
    }

    public final Task zzg(String str) {
        if (this.zza == null) {
            return zzj();
        }
        zzb.zzd("requestUpdateInfo(%s)", str);
        zzi zzi = new zzi();
        this.zza.zzq(new zzl(this, zzi, str, zzi), zzi);
        return zzi.zza();
    }
}
