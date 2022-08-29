package com.google.android.play.core.assetpacks;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbr {
    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    static int zzb(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }

    static long zzc(byte[] bArr, int i) {
        return ((long) ((zza(bArr, i + 2) << 16) | zza(bArr, i))) & 4294967295L;
    }
}
