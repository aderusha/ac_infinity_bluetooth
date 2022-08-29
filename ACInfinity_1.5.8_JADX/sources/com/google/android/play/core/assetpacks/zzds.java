package com.google.android.play.core.assetpacks;

import java.util.Arrays;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzds {
    private byte[] zza = new byte[4096];
    private int zzb;
    private long zzc;
    private long zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private String zzi;

    public zzds() {
        zzd();
    }

    private final int zze(int i, byte[] bArr, int i2, int i3) {
        int i4 = this.zzb;
        if (i4 >= i) {
            return 0;
        }
        int min = Math.min(i3, i - i4);
        System.arraycopy(bArr, i2, this.zza, this.zzb, min);
        int i5 = this.zzb + min;
        this.zzb = i5;
        if (i5 < i) {
            return -1;
        }
        return min;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb(byte[] bArr, int i, int i2) {
        int zze2 = zze(30, bArr, i, i2);
        if (zze2 == -1) {
            return -1;
        }
        if (this.zzc == -1) {
            long zzc2 = zzbr.zzc(this.zza, 0);
            this.zzc = zzc2;
            if (zzc2 == 67324752) {
                this.zzh = false;
                this.zzd = zzbr.zzc(this.zza, 18);
                this.zzg = zzbr.zza(this.zza, 8);
                this.zze = zzbr.zza(this.zza, 26);
                int zza2 = this.zze + 30 + zzbr.zza(this.zza, 28);
                this.zzf = zza2;
                int length = this.zza.length;
                if (length < zza2) {
                    do {
                        length += length;
                    } while (length < zza2);
                    this.zza = Arrays.copyOf(this.zza, length);
                }
            } else {
                this.zzh = true;
            }
        }
        int zze3 = zze(this.zzf, bArr, i + zze2, i2 - zze2);
        if (zze3 == -1) {
            return -1;
        }
        int i3 = zze2 + zze3;
        if (!this.zzh && this.zzi == null) {
            this.zzi = new String(this.zza, 30, this.zze);
        }
        return i3;
    }

    public final zzet zzc() {
        int i = this.zzb;
        int i2 = this.zzf;
        if (i < i2) {
            return new zzbq(this.zzi, this.zzd, this.zzg, true, this.zzh, Arrays.copyOf(this.zza, i));
        }
        zzbq zzbq = new zzbq(this.zzi, this.zzd, this.zzg, false, this.zzh, Arrays.copyOf(this.zza, i2));
        zzd();
        return zzbq;
    }

    public final void zzd() {
        this.zzb = 0;
        this.zze = -1;
        this.zzc = -1;
        this.zzh = false;
        this.zzf = 30;
        this.zzd = -1;
        this.zzg = -1;
        this.zzi = null;
    }
}
