package com.google.android.play.core.assetpacks;

import java.util.Arrays;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbq extends zzet {
    private final String zza;
    private final long zzb;
    private final int zzc;
    private final boolean zzd;
    private final boolean zze;
    private final byte[] zzf;

    zzbq(String str, long j, int i, boolean z, boolean z2, byte[] bArr) {
        this.zza = str;
        this.zzb = j;
        this.zzc = i;
        this.zzd = z;
        this.zze = z2;
        this.zzf = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzet = (zzet) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzet.zzc()) : zzet.zzc() == null) {
                if (this.zzb == zzet.zzb() && this.zzc == zzet.zza() && this.zzd == zzet.zze() && this.zze == zzet.zzd()) {
                    if (Arrays.equals(this.zzf, zzet instanceof zzbq ? ((zzbq) zzet).zzf : zzet.zzf())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzb;
        int i = this.zzc;
        boolean z = this.zzd;
        boolean z2 = this.zze;
        String arrays = Arrays.toString(this.zzf);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(arrays).length());
        sb.append("ZipEntry{name=");
        sb.append(str);
        sb.append(", size=");
        sb.append(j);
        sb.append(", compressionMethod=");
        sb.append(i);
        sb.append(", isPartial=");
        sb.append(z);
        sb.append(", isEndOfArchive=");
        sb.append(z2);
        sb.append(", headerBytes=");
        sb.append(arrays);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final long zzb() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final String zzc() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzf() {
        return this.zzf;
    }

    public final int hashCode() {
        int i;
        String str = this.zza;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        long j = this.zzb;
        int i2 = 1237;
        int i3 = (((((((i ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zzc) * 1000003) ^ (true != this.zzd ? 1237 : 1231)) * 1000003;
        if (true == this.zze) {
            i2 = 1231;
        }
        return ((i3 ^ i2) * 1000003) ^ Arrays.hashCode(this.zzf);
    }
}
