package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzp {
    private final Context zza;

    public zzp(Context context) {
        this.zza = context;
    }

    static String zzb(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            return bundle.getString("local_testing_dir");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final Context zza() {
        return this.zza;
    }
}
