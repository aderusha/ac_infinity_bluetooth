package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zza extends SplitInstallSessionState {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private final List zzf;
    private final List zzg;
    private final PendingIntent zzh;
    private final List zzi;

    zza(int i, int i2, int i3, long j, long j2, List list, List list2, PendingIntent pendingIntent, List list3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = j;
        this.zze = j2;
        this.zzf = list;
        this.zzg = list2;
        this.zzh = pendingIntent;
        this.zzi = list3;
    }

    public final long bytesDownloaded() {
        return this.zzd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1 = r7.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        r1 = r7.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0061, code lost:
        r1 = r7.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0076, code lost:
        r1 = r7.zzi;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.android.play.core.splitinstall.SplitInstallSessionState
            r2 = 0
            if (r1 == 0) goto L_0x008d
            com.google.android.play.core.splitinstall.SplitInstallSessionState r8 = (com.google.android.play.core.splitinstall.SplitInstallSessionState) r8
            int r1 = r7.zza
            int r3 = r8.sessionId()
            if (r1 != r3) goto L_0x008d
            int r1 = r7.zzb
            int r3 = r8.status()
            if (r1 != r3) goto L_0x008d
            int r1 = r7.zzc
            int r3 = r8.errorCode()
            if (r1 != r3) goto L_0x008d
            long r3 = r7.zzd
            long r5 = r8.bytesDownloaded()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x008d
            long r3 = r7.zze
            long r5 = r8.totalBytesToDownload()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x008d
            java.util.List r1 = r7.zzf
            if (r1 != 0) goto L_0x0042
            java.util.List r1 = r8.zzb()
            if (r1 != 0) goto L_0x008d
            goto L_0x004c
        L_0x0042:
            java.util.List r3 = r8.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x008d
        L_0x004c:
            java.util.List r1 = r7.zzg
            if (r1 != 0) goto L_0x0057
            java.util.List r1 = r8.zza()
            if (r1 != 0) goto L_0x008d
            goto L_0x0061
        L_0x0057:
            java.util.List r3 = r8.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x008d
        L_0x0061:
            android.app.PendingIntent r1 = r7.zzh
            if (r1 != 0) goto L_0x006c
            android.app.PendingIntent r1 = r8.resolutionIntent()
            if (r1 != 0) goto L_0x008d
            goto L_0x0076
        L_0x006c:
            android.app.PendingIntent r3 = r8.resolutionIntent()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x008d
        L_0x0076:
            java.util.List r1 = r7.zzi
            if (r1 != 0) goto L_0x0081
            java.util.List r8 = r8.zzc()
            if (r8 != 0) goto L_0x008d
            goto L_0x008c
        L_0x0081:
            java.util.List r8 = r8.zzc()
            boolean r8 = r1.equals(r8)
            if (r8 != 0) goto L_0x008c
            goto L_0x008d
        L_0x008c:
            return r0
        L_0x008d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zza.equals(java.lang.Object):boolean");
    }

    public final int errorCode() {
        return this.zzc;
    }

    @Deprecated
    public final PendingIntent resolutionIntent() {
        return this.zzh;
    }

    public final int sessionId() {
        return this.zza;
    }

    public final int status() {
        return this.zzb;
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        long j = this.zzd;
        long j2 = this.zze;
        String valueOf = String.valueOf(this.zzf);
        String valueOf2 = String.valueOf(this.zzg);
        String valueOf3 = String.valueOf(this.zzh);
        String valueOf4 = String.valueOf(this.zzi);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 251 + length2 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("SplitInstallSessionState{sessionId=");
        sb.append(i);
        sb.append(", status=");
        sb.append(i2);
        sb.append(", errorCode=");
        sb.append(i3);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", moduleNamesNullable=");
        sb.append(valueOf);
        sb.append(", languagesNullable=");
        sb.append(valueOf2);
        sb.append(", resolutionIntent=");
        sb.append(valueOf3);
        sb.append(", splitFileIntents=");
        sb.append(valueOf4);
        sb.append("}");
        return sb.toString();
    }

    public final long totalBytesToDownload() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final List zza() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final List zzb() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final List zzc() {
        return this.zzi;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = this.zza;
        int i5 = this.zzb;
        int i6 = this.zzc;
        long j = this.zzd;
        long j2 = this.zze;
        int i7 = (((((((((i4 ^ 1000003) * 1000003) ^ i5) * 1000003) ^ i6) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        List list = this.zzf;
        int i8 = 0;
        if (list == null) {
            i = 0;
        } else {
            i = list.hashCode();
        }
        int i9 = (i7 ^ i) * 1000003;
        List list2 = this.zzg;
        if (list2 == null) {
            i2 = 0;
        } else {
            i2 = list2.hashCode();
        }
        int i10 = (i9 ^ i2) * 1000003;
        PendingIntent pendingIntent = this.zzh;
        if (pendingIntent == null) {
            i3 = 0;
        } else {
            i3 = pendingIntent.hashCode();
        }
        int i11 = (i10 ^ i3) * 1000003;
        List list3 = this.zzi;
        if (list3 != null) {
            i8 = list3.hashCode();
        }
        return i11 ^ i8;
    }
}
