package com.google.android.play.core.assetpacks;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.bumptech.glide.load.Key;
import com.google.android.play.core.internal.zzci;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.ZipException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbt {
    static AssetLocation zza(String str, String str2) throws IOException {
        Long l;
        String str3 = str;
        String str4 = str2;
        zzci.zzb(str3 != null, "Attempted to get file location from a null apk path.");
        zzci.zzb(str4 != null, String.format("Attempted to get file location in apk %s with a null file path.", new Object[]{str3}));
        RandomAccessFile randomAccessFile = new RandomAccessFile(str3, "r");
        byte[] bArr = new byte[22];
        randomAccessFile.seek(randomAccessFile.length() - 22);
        randomAccessFile.readFully(bArr);
        zzbs zzb = zzbr.zzb(bArr, 0) == 1347093766 ? zzb(bArr) : null;
        byte b = 5;
        if (zzb == null) {
            long length = randomAccessFile.length() - 22;
            long j = -65536 + length;
            if (j < 0) {
                j = 0;
            }
            int min = (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, randomAccessFile.length());
            byte[] bArr2 = new byte[min];
            byte[] bArr3 = new byte[22];
            loop0:
            while (true) {
                long max = Math.max(3 + (length - ((long) min)), j);
                randomAccessFile.seek(max);
                randomAccessFile.readFully(bArr2);
                for (int i = min - 4; i >= 0; i -= 4) {
                    byte b2 = bArr2[i];
                    int i2 = b2 != b ? b2 != 6 ? b2 != 75 ? b2 != 80 ? -1 : 0 : 1 : 3 : 2;
                    if (i2 >= 0 && i >= i2 && zzbr.zzb(bArr2, i - i2) == 1347093766) {
                        randomAccessFile.seek((max + ((long) i)) - ((long) i2));
                        randomAccessFile.readFully(bArr3);
                        zzb = zzb(bArr3);
                        break loop0;
                    }
                    b = 5;
                }
                if (max != j) {
                    length = max;
                } else {
                    throw new ZipException(String.format("End Of Central Directory signature not found in APK %s", new Object[]{str3}));
                }
            }
        }
        long j2 = zzb.zza;
        byte[] bytes = str4.getBytes(Key.STRING_CHARSET_NAME);
        byte[] bArr4 = new byte[46];
        byte[] bArr5 = new byte[str2.length()];
        int i3 = 0;
        while (true) {
            if (i3 >= zzb.zzb) {
                l = null;
                break;
            }
            randomAccessFile.seek(j2);
            randomAccessFile.readFully(bArr4);
            int zzb2 = zzbr.zzb(bArr4, 0);
            if (zzb2 == 1347092738) {
                randomAccessFile.seek(j2 + 28);
                int zza = zzbr.zza(bArr4, 28);
                if (zza == str2.length()) {
                    randomAccessFile.seek(46 + j2);
                    randomAccessFile.read(bArr5);
                    if (Arrays.equals(bArr5, bytes)) {
                        l = Long.valueOf(zzbr.zzc(bArr4, 42));
                        break;
                    }
                }
                j2 += (long) (zza + 46 + zzbr.zza(bArr4, 30) + zzbr.zza(bArr4, 32));
                i3++;
            } else {
                throw new ZipException(String.format("Missing central directory file header signature when looking for file %s in APK %s. Read %d entries out of %d. Found %d instead of the header signature %d.", new Object[]{str4, str3, Integer.valueOf(i3), Integer.valueOf(zzb.zzb), Integer.valueOf(zzb2), 1347092738}));
            }
        }
        if (l == null) {
            return null;
        }
        long longValue = l.longValue();
        byte[] bArr6 = new byte[8];
        randomAccessFile.seek(22 + longValue);
        randomAccessFile.readFully(bArr6);
        return new zzbl(str, longValue + 30 + ((long) zzbr.zza(bArr6, 4)) + ((long) zzbr.zza(bArr6, 6)), zzbr.zzc(bArr6, 0));
    }

    private static zzbs zzb(byte[] bArr) {
        int zza = zzbr.zza(bArr, 10);
        return new zzbs(zzbr.zzc(bArr, 16), zzbr.zzc(bArr, 12), zza);
    }
}
