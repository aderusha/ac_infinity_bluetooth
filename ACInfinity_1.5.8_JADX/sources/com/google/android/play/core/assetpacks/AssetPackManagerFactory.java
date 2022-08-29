package com.google.android.play.core.assetpacks;

import android.content.Context;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class AssetPackManagerFactory {
    private AssetPackManagerFactory() {
    }

    public static synchronized AssetPackManager getInstance(Context context) {
        AssetPackManager zza;
        synchronized (AssetPackManagerFactory.class) {
            zza = zzd.zza(context).zza();
        }
        return zza;
    }
}
