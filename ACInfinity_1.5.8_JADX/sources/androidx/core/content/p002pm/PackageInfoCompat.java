package androidx.core.content.p002pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: androidx.core.content.pm.PackageInfoCompat */
public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return (long) packageInfo.versionCode;
    }

    public static List<Signature> getSignatures(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        Signature[] signatureArr;
        if (Build.VERSION.SDK_INT >= 28) {
            SigningInfo signingInfo = packageManager.getPackageInfo(str, 134217728).signingInfo;
            if (Api28Impl.hasMultipleSigners(signingInfo)) {
                signatureArr = Api28Impl.getApkContentsSigners(signingInfo);
            } else {
                signatureArr = Api28Impl.getSigningCertificateHistory(signingInfo);
            }
        } else {
            signatureArr = packageManager.getPackageInfo(str, 64).signatures;
        }
        if (signatureArr == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(signatureArr);
    }

    public static boolean hasSignatures(PackageManager packageManager, String str, Map<byte[], Integer> map, boolean z) throws PackageManager.NameNotFoundException {
        if (map.isEmpty()) {
            return false;
        }
        Set<byte[]> keySet = map.keySet();
        for (byte[] next : keySet) {
            if (next != null) {
                Integer num = map.get(next);
                if (num != null) {
                    int intValue = num.intValue();
                    if (intValue != 0 && intValue != 1) {
                        throw new IllegalArgumentException("Unsupported certificate type " + num + " when verifying " + str);
                    }
                } else {
                    throw new IllegalArgumentException("Type must be specified for cert when verifying " + str);
                }
            } else {
                throw new IllegalArgumentException("Cert byte array cannot be null when verifying " + str);
            }
        }
        List<Signature> signatures = getSignatures(packageManager, str);
        if (z || Build.VERSION.SDK_INT < 28) {
            if (signatures.size() != 0 && map.size() <= signatures.size() && (!z || map.size() == signatures.size())) {
                byte[][] bArr = null;
                if (map.containsValue(1)) {
                    bArr = new byte[signatures.size()][];
                    for (int i = 0; i < signatures.size(); i++) {
                        bArr[i] = computeSHA256Digest(signatures.get(i).toByteArray());
                    }
                }
                Iterator<byte[]> it = keySet.iterator();
                if (it.hasNext()) {
                    byte[] next2 = it.next();
                    Integer num2 = map.get(next2);
                    int intValue2 = num2.intValue();
                    if (intValue2 != 0) {
                        if (intValue2 != 1) {
                            throw new IllegalArgumentException("Unsupported certificate type " + num2);
                        } else if (!byteArrayContains(bArr, next2)) {
                            return false;
                        }
                    } else if (!signatures.contains(new Signature(next2))) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
        for (byte[] next3 : keySet) {
            if (!Api28Impl.hasSigningCertificate(packageManager, str, next3, map.get(next3).intValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean byteArrayContains(byte[][] bArr, byte[] bArr2) {
        for (byte[] equals : bArr) {
            if (Arrays.equals(bArr2, equals)) {
                return true;
            }
        }
        return false;
    }

    private static byte[] computeSHA256Digest(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA256").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Device doesn't support SHA256 cert checking", e);
        }
    }

    private PackageInfoCompat() {
    }

    /* renamed from: androidx.core.content.pm.PackageInfoCompat$Api28Impl */
    private static class Api28Impl {
        private Api28Impl() {
        }

        static boolean hasSigningCertificate(PackageManager packageManager, String str, byte[] bArr, int i) {
            return packageManager.hasSigningCertificate(str, bArr, i);
        }

        static boolean hasMultipleSigners(SigningInfo signingInfo) {
            return signingInfo.hasMultipleSigners();
        }

        static Signature[] getApkContentsSigners(SigningInfo signingInfo) {
            return signingInfo.getApkContentsSigners();
        }

        static Signature[] getSigningCertificateHistory(SigningInfo signingInfo) {
            return signingInfo.getSigningCertificateHistory();
        }
    }
}
