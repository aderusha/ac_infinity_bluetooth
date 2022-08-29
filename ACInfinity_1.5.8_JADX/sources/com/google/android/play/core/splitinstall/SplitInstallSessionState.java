package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class SplitInstallSessionState {
    public static SplitInstallSessionState create(int i, int i2, int i3, long j, long j2, List<String> list, List<String> list2) {
        if (i2 != 8) {
            return new zza(i, i2, i3, j, j2, list, list2, (PendingIntent) null, (List) null);
        }
        throw new IllegalArgumentException("REQUIRES_USER_CONFIRMATION state not supported.");
    }

    public static SplitInstallSessionState zzd(Bundle bundle) {
        return new zza(bundle.getInt("session_id"), bundle.getInt(NotificationCompat.CATEGORY_STATUS), bundle.getInt("error_code"), bundle.getLong("bytes_downloaded"), bundle.getLong("total_bytes_to_download"), bundle.getStringArrayList("module_names"), bundle.getStringArrayList("languages"), (PendingIntent) bundle.getParcelable("user_confirmation_intent"), bundle.getParcelableArrayList("split_file_intents"));
    }

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public boolean hasTerminalStatus() {
        int status = status();
        return status == 0 || status == 5 || status == 6 || status == 7;
    }

    public List<String> languages() {
        return zza() != null ? new ArrayList(zza()) : new ArrayList();
    }

    public List<String> moduleNames() {
        if (zzb() != null) {
            return new ArrayList(zzb());
        }
        return new ArrayList();
    }

    @Deprecated
    public abstract PendingIntent resolutionIntent();

    public abstract int sessionId();

    public abstract int status();

    public abstract long totalBytesToDownload();

    /* access modifiers changed from: package-private */
    public abstract List zza();

    /* access modifiers changed from: package-private */
    public abstract List zzb();

    /* access modifiers changed from: package-private */
    public abstract List zzc();
}
