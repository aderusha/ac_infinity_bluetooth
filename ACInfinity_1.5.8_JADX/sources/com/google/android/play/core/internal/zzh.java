package com.google.android.play.core.internal;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
class zzh extends X509Certificate {
    private final X509Certificate zza;

    public zzh(X509Certificate x509Certificate) {
        this.zza = x509Certificate;
    }

    public final void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        this.zza.checkValidity();
    }

    public final int getBasicConstraints() {
        return this.zza.getBasicConstraints();
    }

    public final Set getCriticalExtensionOIDs() {
        return this.zza.getCriticalExtensionOIDs();
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        return this.zza.getEncoded();
    }

    public final byte[] getExtensionValue(String str) {
        return this.zza.getExtensionValue(str);
    }

    public final Principal getIssuerDN() {
        return this.zza.getIssuerDN();
    }

    public final boolean[] getIssuerUniqueID() {
        return this.zza.getIssuerUniqueID();
    }

    public final boolean[] getKeyUsage() {
        return this.zza.getKeyUsage();
    }

    public final Set getNonCriticalExtensionOIDs() {
        return this.zza.getNonCriticalExtensionOIDs();
    }

    public final Date getNotAfter() {
        return this.zza.getNotAfter();
    }

    public final Date getNotBefore() {
        return this.zza.getNotBefore();
    }

    public final PublicKey getPublicKey() {
        return this.zza.getPublicKey();
    }

    public final BigInteger getSerialNumber() {
        return this.zza.getSerialNumber();
    }

    public final String getSigAlgName() {
        return this.zza.getSigAlgName();
    }

    public final String getSigAlgOID() {
        return this.zza.getSigAlgOID();
    }

    public final byte[] getSigAlgParams() {
        return this.zza.getSigAlgParams();
    }

    public final byte[] getSignature() {
        return this.zza.getSignature();
    }

    public final Principal getSubjectDN() {
        return this.zza.getSubjectDN();
    }

    public final boolean[] getSubjectUniqueID() {
        return this.zza.getSubjectUniqueID();
    }

    public final byte[] getTBSCertificate() throws CertificateEncodingException {
        return this.zza.getTBSCertificate();
    }

    public final int getVersion() {
        return this.zza.getVersion();
    }

    public final boolean hasUnsupportedCriticalExtension() {
        return this.zza.hasUnsupportedCriticalExtension();
    }

    public final String toString() {
        return this.zza.toString();
    }

    public final void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.zza.verify(publicKey);
    }

    public final void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        this.zza.checkValidity(date);
    }

    public final void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.zza.verify(publicKey, str);
    }
}
