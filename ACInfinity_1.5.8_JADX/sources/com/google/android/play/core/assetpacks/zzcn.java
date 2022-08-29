package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzcn extends OutputStream {
    private final zzds zza = new zzds();
    private final File zzb;
    private final zzen zzc;
    private long zzd;
    private long zze;
    private FileOutputStream zzf;
    private zzet zzg;

    zzcn(File file, zzen zzen) {
        this.zzb = file;
        this.zzc = zzen;
    }

    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        while (i2 > 0) {
            if (this.zzd == 0 && this.zze == 0) {
                int zzb2 = this.zza.zzb(bArr, i, i2);
                if (zzb2 != -1) {
                    i += zzb2;
                    i2 -= zzb2;
                    zzet zzc2 = this.zza.zzc();
                    this.zzg = zzc2;
                    if (zzc2.zzd()) {
                        this.zzd = 0;
                        this.zzc.zzl(this.zzg.zzf(), 0, this.zzg.zzf().length);
                        this.zze = (long) this.zzg.zzf().length;
                    } else if (!this.zzg.zzh() || this.zzg.zzg()) {
                        byte[] zzf2 = this.zzg.zzf();
                        this.zzc.zzl(zzf2, 0, zzf2.length);
                        this.zzd = this.zzg.zzb();
                    } else {
                        this.zzc.zzj(this.zzg.zzf());
                        File file = new File(this.zzb, this.zzg.zzc());
                        file.getParentFile().mkdirs();
                        this.zzd = this.zzg.zzb();
                        this.zzf = new FileOutputStream(file);
                    }
                } else {
                    return;
                }
            }
            if (!this.zzg.zzg()) {
                if (this.zzg.zzd()) {
                    this.zzc.zze(this.zze, bArr, i, i2);
                    this.zze += (long) i2;
                    i3 = i2;
                } else if (this.zzg.zzh()) {
                    i3 = (int) Math.min((long) i2, this.zzd);
                    this.zzf.write(bArr, i, i3);
                    long j = this.zzd - ((long) i3);
                    this.zzd = j;
                    if (j == 0) {
                        this.zzf.close();
                    }
                } else {
                    i3 = (int) Math.min((long) i2, this.zzd);
                    int length = this.zzg.zzf().length;
                    this.zzc.zze((((long) length) + this.zzg.zzb()) - this.zzd, bArr, i, i3);
                    this.zzd -= (long) i3;
                }
                i += i3;
                i2 -= i3;
            }
        }
    }
}
