package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbp extends zzem {
    private final int zza;
    private final String zzb;
    private final long zzc;
    private final long zzd;
    private final int zze;

    zzbp(int i, String str, long j, long j2, int i2) {
        this.zza = i;
        this.zzb = str;
        this.zzc = j;
        this.zzd = j2;
        this.zze = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = r7.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.android.play.core.assetpacks.zzem
            r2 = 0
            if (r1 == 0) goto L_0x0046
            com.google.android.play.core.assetpacks.zzem r8 = (com.google.android.play.core.assetpacks.zzem) r8
            int r1 = r7.zza
            int r3 = r8.zza()
            if (r1 != r3) goto L_0x0046
            java.lang.String r1 = r7.zzb
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = r8.zze()
            if (r1 != 0) goto L_0x0046
            goto L_0x0029
        L_0x001e:
            java.lang.String r3 = r8.zze()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0029
            goto L_0x0046
        L_0x0029:
            long r3 = r7.zzc
            long r5 = r8.zzc()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0046
            long r3 = r7.zzd
            long r5 = r8.zzd()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0046
            int r1 = r7.zze
            int r8 = r8.zzb()
            if (r1 != r8) goto L_0x0046
            return r0
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzbp.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.zzc;
        long j2 = this.zzd;
        return ((((((i ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.zze;
    }

    public final String toString() {
        int i = this.zza;
        String str = this.zzb;
        long j = this.zzc;
        long j2 = this.zzd;
        int i2 = this.zze;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 157);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(i);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(j);
        sb.append(", remainingBytes=");
        sb.append(j2);
        sb.append(", previousChunk=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final long zzc() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final long zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final String zze() {
        return this.zzb;
    }
}
