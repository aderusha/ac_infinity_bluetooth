package com.google.android.play.core.install;

import android.content.Intent;
import com.google.android.play.core.internal.zzag;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class InstallState {
    public static InstallState zza(int i, long j, long j2, int i2, String str) {
        return new zza(i, j, j2, i2, str);
    }

    public static InstallState zzb(Intent intent, zzag zzag) {
        Intent intent2 = intent;
        zzag zzag2 = zzag;
        zzag2.zza("List of extras in received intent needed by fromUpdateIntent:", new Object[0]);
        zzag2.zza("Key: %s; value: %s", "install.status", Integer.valueOf(intent2.getIntExtra("install.status", 0)));
        zzag2.zza("Key: %s; value: %s", "error.code", Integer.valueOf(intent2.getIntExtra("error.code", 0)));
        return new zza(intent2.getIntExtra("install.status", 0), intent2.getLongExtra("bytes.downloaded", 0), intent2.getLongExtra("total.bytes.to.download", 0), intent2.getIntExtra("error.code", 0), intent2.getStringExtra("package.name"));
    }

    public abstract long bytesDownloaded();

    public abstract int installErrorCode();

    public abstract int installStatus();

    public abstract String packageName();

    public abstract long totalBytesToDownload();
}
