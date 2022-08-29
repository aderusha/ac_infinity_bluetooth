package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.tasks.Tasks;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbu {
    private final zzco zza;

    zzbu(zzco zzco) {
        this.zza = zzco;
    }

    /* access modifiers changed from: package-private */
    public final InputStream zza(int i, String str, String str2, int i2) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Tasks.await(((zzy) this.zza.zza()).zza(i, str, str2, i2));
            if (parcelFileDescriptor != null && parcelFileDescriptor.getFileDescriptor() != null) {
                return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
            }
            throw new zzck(String.format("Corrupted ParcelFileDescriptor, session %s packName %s sliceId %s, chunkNumber %s", new Object[]{Integer.valueOf(i), str, str2, Integer.valueOf(i2)}), i);
        } catch (ExecutionException e) {
            throw new zzck(String.format("Error opening chunk file, session %s packName %s sliceId %s, chunkNumber %s", new Object[]{Integer.valueOf(i), str, str2, Integer.valueOf(i2)}), e, i);
        } catch (InterruptedException e2) {
            throw new zzck("Extractor was interrupted while waiting for chunk file.", e2, i);
        }
    }
}
