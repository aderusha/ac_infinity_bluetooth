package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbw extends FilterInputStream {
    private final zzds zza = new zzds();
    private byte[] zzb = new byte[4096];
    private long zzc;
    private boolean zzd = false;
    private boolean zze = false;

    zzbw(InputStream inputStream) {
        super(inputStream);
    }

    private final int zze(byte[] bArr, int i, int i2) throws IOException {
        return Math.max(0, super.read(bArr, i, i2));
    }

    private final boolean zzf(int i) throws IOException {
        int zze2 = zze(this.zzb, 0, i);
        if (zze2 != i) {
            int i2 = i - zze2;
            if (zze(this.zzb, zze2, i2) != i2) {
                this.zza.zzb(this.zzb, 0, zze2);
                return false;
            }
        }
        this.zza.zzb(this.zzb, 0, i);
        return true;
    }

    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: package-private */
    public final long zza() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzet zzb() throws IOException {
        byte[] bArr;
        if (this.zzc > 0) {
            do {
                bArr = this.zzb;
            } while (read(bArr, 0, bArr.length) != -1);
        }
        if (this.zzd || this.zze) {
            return new zzbq((String) null, -1, -1, false, false, (byte[]) null);
        }
        if (!zzf(30)) {
            this.zzd = true;
            return this.zza.zzc();
        }
        zzet zzc2 = this.zza.zzc();
        if (zzc2.zzd()) {
            this.zze = true;
            return zzc2;
        } else if (zzc2.zzb() != 4294967295L) {
            int zza2 = this.zza.zza() - 30;
            long j = (long) zza2;
            int length = this.zzb.length;
            if (j > ((long) length)) {
                do {
                    length += length;
                } while (((long) length) < j);
                this.zzb = Arrays.copyOf(this.zzb, length);
            }
            if (!zzf(zza2)) {
                this.zzd = true;
                return this.zza.zzc();
            }
            zzet zzc3 = this.zza.zzc();
            this.zzc = zzc3.zzb();
            return zzc3;
        } else {
            throw new zzck("Files bigger than 4GiB are not supported.");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd() {
        return this.zzd;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.zzc;
        if (j <= 0 || this.zzd) {
            return -1;
        }
        int zze2 = zze(bArr, i, (int) Math.min(j, (long) i2));
        this.zzc -= (long) zze2;
        if (zze2 != 0) {
            return zze2;
        }
        this.zzd = true;
        return 0;
    }
}
