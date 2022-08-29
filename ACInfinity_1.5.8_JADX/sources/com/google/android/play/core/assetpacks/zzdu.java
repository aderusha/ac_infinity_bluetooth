package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.IOException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzdu {
    private static final zzag zza = new zzag("MergeSliceTaskHandler");
    private final zzbh zzb;

    zzdu(zzbh zzbh) {
        this.zzb = zzbh;
    }

    private static void zzb(File file, File file2) {
        if (file.isDirectory()) {
            file2.mkdirs();
            for (File file3 : file.listFiles()) {
                zzb(file3, new File(file2, file3.getName()));
            }
            if (!file.delete()) {
                String valueOf = String.valueOf(file);
                String.valueOf(valueOf).length();
                throw new zzck("Unable to delete directory: ".concat(String.valueOf(valueOf)));
            }
        } else if (file2.exists()) {
            throw new zzck("File clashing with existing file from other slice: ".concat(file2.toString()));
        } else if (!file.renameTo(file2)) {
            String valueOf2 = String.valueOf(file);
            String.valueOf(valueOf2).length();
            throw new zzck("Unable to move file: ".concat(String.valueOf(valueOf2)));
        }
    }

    public final void zza(zzdt zzdt) {
        File zzq = this.zzb.zzq(zzdt.zzl, zzdt.zza, zzdt.zzb, zzdt.zzc);
        if (zzq.exists()) {
            File zzj = this.zzb.zzj(zzdt.zzl, zzdt.zza, zzdt.zzb);
            if (!zzj.exists()) {
                zzj.mkdirs();
            }
            zzb(zzq, zzj);
            try {
                this.zzb.zzA(zzdt.zzl, zzdt.zza, zzdt.zzb, this.zzb.zzb(zzdt.zzl, zzdt.zza, zzdt.zzb) + 1);
            } catch (IOException e) {
                zza.zzb("Writing merge checkpoint failed with %s.", e.getMessage());
                throw new zzck("Writing merge checkpoint failed.", e, zzdt.zzk);
            }
        } else {
            throw new zzck(String.format("Cannot find verified files for slice %s.", new Object[]{zzdt.zzc}), zzdt.zzk);
        }
    }
}
