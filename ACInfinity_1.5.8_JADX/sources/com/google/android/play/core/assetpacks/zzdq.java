package com.google.android.play.core.assetpacks;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzdq {
    static String zza(List list) throws NoSuchAlgorithmException, IOException {
        int read;
        MessageDigest instance = MessageDigest.getInstance("SHA256");
        byte[] bArr = new byte[8192];
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FileInputStream fileInputStream = new FileInputStream((File) it.next());
            do {
                try {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    }
                } catch (Throwable unused) {
                }
            } while (read != -1);
            fileInputStream.close();
        }
        return Base64.encodeToString(instance.digest(), 11);
        throw th;
    }
}
