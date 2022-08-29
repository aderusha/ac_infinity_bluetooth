package com.google.android.play.core.internal;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzg extends zzh {
    private final byte[] zza;

    public zzg(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.zza = bArr;
    }

    public final byte[] getEncoded() throws CertificateEncodingException {
        return this.zza;
    }
}
