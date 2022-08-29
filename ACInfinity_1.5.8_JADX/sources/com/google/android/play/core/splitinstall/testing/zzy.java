package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.splitinstall.model.zza;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzy {
    private static final zzag zza = new zzag("LocalTestingConfigParser");
    private final XmlPullParser zzb;
    private final zzs zzc = zzt.zzc();

    zzy(XmlPullParser xmlPullParser) {
        this.zzb = xmlPullParser;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.play.core.splitinstall.testing.zzt zza(java.io.File r4) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "local_testing_config.xml"
            r0.<init>(r4, r1)
            boolean r4 = r0.exists()
            if (r4 != 0) goto L_0x0010
            com.google.android.play.core.splitinstall.testing.zzt r4 = com.google.android.play.core.splitinstall.testing.zzt.zza
            return r4
        L_0x0010:
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ IOException -> 0x0042, XmlPullParserException -> 0x0040, RuntimeException -> 0x003e }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0042, XmlPullParserException -> 0x0040, RuntimeException -> 0x003e }
            org.xmlpull.v1.XmlPullParserFactory r0 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ all -> 0x0039 }
            org.xmlpull.v1.XmlPullParser r0 = r0.newPullParser()     // Catch:{ all -> 0x0039 }
            r0.setInput(r4)     // Catch:{ all -> 0x0039 }
            com.google.android.play.core.splitinstall.testing.zzy r2 = new com.google.android.play.core.splitinstall.testing.zzy     // Catch:{ all -> 0x0039 }
            r2.<init>(r0)     // Catch:{ all -> 0x0039 }
            com.google.android.play.core.splitinstall.testing.zzu r0 = new com.google.android.play.core.splitinstall.testing.zzu     // Catch:{ all -> 0x0039 }
            r0.<init>(r2)     // Catch:{ all -> 0x0039 }
            java.lang.String r3 = "local-testing-config"
            r2.zze(r3, r0)     // Catch:{ all -> 0x0039 }
            com.google.android.play.core.splitinstall.testing.zzs r0 = r2.zzc     // Catch:{ all -> 0x0039 }
            com.google.android.play.core.splitinstall.testing.zzt r0 = r0.zze()     // Catch:{ all -> 0x0039 }
            r4.close()     // Catch:{ IOException -> 0x0042, XmlPullParserException -> 0x0040, RuntimeException -> 0x003e }
            return r0
        L_0x0039:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x003d }
        L_0x003d:
            throw r0     // Catch:{ IOException -> 0x0042, XmlPullParserException -> 0x0040, RuntimeException -> 0x003e }
        L_0x003e:
            r4 = move-exception
            goto L_0x0043
        L_0x0040:
            r4 = move-exception
            goto L_0x0043
        L_0x0042:
            r4 = move-exception
        L_0x0043:
            com.google.android.play.core.internal.zzag r0 = zza
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r1
            r1 = 1
            java.lang.String r4 = r4.getMessage()
            r2[r1] = r4
            java.lang.String r4 = "%s can not be parsed, using default. Error: %s"
            r0.zze(r4, r2)
            com.google.android.play.core.splitinstall.testing.zzt r4 = com.google.android.play.core.splitinstall.testing.zzt.zza
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.zzy.zza(java.io.File):com.google.android.play.core.splitinstall.testing.zzt");
    }

    public static /* synthetic */ void zzb(zzy zzy) {
        for (int i = 0; i < zzy.zzb.getAttributeCount(); i++) {
            if ("defaultErrorCode".equals(zzy.zzb.getAttributeName(i))) {
                zzy.zzc.zza(zza.zza(zzy.zzb.getAttributeValue(i)));
            }
        }
        zzy.zze("split-install-error", new zzw(zzy));
    }

    public static /* synthetic */ void zzc(zzy zzy) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < zzy.zzb.getAttributeCount(); i++) {
            if ("module".equals(zzy.zzb.getAttributeName(i))) {
                str = zzy.zzb.getAttributeValue(i);
            }
            if ("errorCode".equals(zzy.zzb.getAttributeName(i))) {
                str2 = zzy.zzb.getAttributeValue(i);
            }
        }
        if (str == null || str2 == null) {
            throw new XmlPullParserException(String.format("'%s' element does not contain 'module'/'errorCode' attributes.", new Object[]{"split-install-error"}), zzy.zzb, (Throwable) null);
        }
        zzy.zzc.zzd().put(str, Integer.valueOf(zza.zza(str2)));
        do {
        } while (zzy.zzb.next() != 3);
    }

    private final void zze(String str, zzx zzx) throws IOException, XmlPullParserException {
        while (true) {
            int next = this.zzb.next();
            if (next != 3 && next != 1) {
                if (this.zzb.getEventType() == 2) {
                    if (this.zzb.getName().equals(str)) {
                        zzx.zza();
                    } else {
                        throw new XmlPullParserException(String.format("Expected '%s' tag but found '%s'.", new Object[]{str, this.zzb.getName()}), this.zzb, (Throwable) null);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() throws IOException, XmlPullParserException {
        zze("split-install-errors", new zzv(this));
    }
}
