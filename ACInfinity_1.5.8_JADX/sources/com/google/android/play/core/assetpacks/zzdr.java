package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzdr extends InputStream {
    private final Enumeration zza;
    private InputStream zzb;

    public zzdr(Enumeration enumeration) throws IOException {
        this.zza = enumeration;
        zza();
    }

    public final void close() throws IOException {
        super.close();
        InputStream inputStream = this.zzb;
        if (inputStream != null) {
            inputStream.close();
            this.zzb = null;
        }
    }

    public final int read() throws IOException {
        while (true) {
            InputStream inputStream = this.zzb;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            zza();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws IOException {
        InputStream inputStream = this.zzb;
        if (inputStream != null) {
            inputStream.close();
        }
        this.zzb = this.zza.hasMoreElements() ? new FileInputStream((File) this.zza.nextElement()) : null;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzb == null) {
            return -1;
        }
        Objects.requireNonNull(bArr);
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            do {
                int read = this.zzb.read(bArr, i, i2);
                if (read > 0) {
                    return read;
                }
                zza();
            } while (this.zzb != null);
            return -1;
        }
    }
}
