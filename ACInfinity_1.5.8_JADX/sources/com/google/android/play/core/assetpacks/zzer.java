package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzer {
    private static final zzag zza = new zzag("VerifySliceTaskHandler");
    private final zzbh zzb;

    zzer(zzbh zzbh) {
        this.zzb = zzbh;
    }

    private final void zzb(zzeq zzeq, File file) {
        try {
            File zzo = this.zzb.zzo(zzeq.zzl, zzeq.zza, zzeq.zzb, zzeq.zzc);
            if (zzo.exists()) {
                try {
                    if (zzdq.zza(zzep.zza(file, zzo)).equals(zzeq.zzd)) {
                        zza.zzd("Verification of slice %s of pack %s successful.", zzeq.zzc, zzeq.zzl);
                        return;
                    }
                    throw new zzck(String.format("Verification failed for slice %s.", new Object[]{zzeq.zzc}), zzeq.zzk);
                } catch (NoSuchAlgorithmException e) {
                    throw new zzck("SHA256 algorithm not supported.", e, zzeq.zzk);
                } catch (IOException e2) {
                    throw new zzck(String.format("Could not digest file during verification for slice %s.", new Object[]{zzeq.zzc}), e2, zzeq.zzk);
                }
            } else {
                throw new zzck(String.format("Cannot find metadata files for slice %s.", new Object[]{zzeq.zzc}), zzeq.zzk);
            }
        } catch (IOException e3) {
            throw new zzck(String.format("Could not reconstruct slice archive during verification for slice %s.", new Object[]{zzeq.zzc}), e3, zzeq.zzk);
        }
    }

    public final void zza(zzeq zzeq) {
        File zzp = this.zzb.zzp(zzeq.zzl, zzeq.zza, zzeq.zzb, zzeq.zzc);
        if (zzp.exists()) {
            zzb(zzeq, zzp);
            File zzq = this.zzb.zzq(zzeq.zzl, zzeq.zza, zzeq.zzb, zzeq.zzc);
            if (!zzq.exists()) {
                zzq.mkdirs();
            }
            if (!zzp.renameTo(zzq)) {
                throw new zzck(String.format("Failed to move slice %s after verification.", new Object[]{zzeq.zzc}), zzeq.zzk);
            }
            return;
        }
        throw new zzck(String.format("Cannot find unverified files for slice %s.", new Object[]{zzeq.zzc}), zzeq.zzk);
    }
}
