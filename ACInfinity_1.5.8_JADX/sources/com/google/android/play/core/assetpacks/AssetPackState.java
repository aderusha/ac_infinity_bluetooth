package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.zzb;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class AssetPackState {
    public static AssetPackState zzb(String str, int i, int i2, long j, long j2, double d, int i3, String str2, String str3) {
        return new zzbn(str, i, i2, j, j2, (int) Math.rint(100.0d * d), i3, str2, str3);
    }

    static AssetPackState zzc(Bundle bundle, String str, zzco zzco, zzeb zzeb, zzbe zzbe) {
        Bundle bundle2 = bundle;
        String str2 = str;
        int zza = zzbe.zza(bundle2.getInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, str2)), str2);
        int i = bundle2.getInt(zzb.zza("error_code", str2));
        long j = bundle2.getLong(zzb.zza("bytes_downloaded", str2));
        long j2 = bundle2.getLong(zzb.zza("total_bytes_to_download", str2));
        double zza2 = zzco.zza(str2);
        long j3 = bundle2.getLong(zzb.zza("pack_version", str2));
        long j4 = bundle2.getLong(zzb.zza("pack_base_version", str2));
        int i2 = 1;
        int i3 = 4;
        if (zza != 4) {
            i3 = zza;
        } else if (!(j4 == 0 || j4 == j3)) {
            i2 = 2;
        }
        return zzb(str, i3, i, j, j2, zza2, i2, bundle2.getString(zzb.zza("pack_version_tag", str2), String.valueOf(bundle2.getInt("app_version_code"))), zzeb.zza(str2));
    }

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();

    public abstract int zza();

    public abstract String zzd();

    public abstract String zze();
}
