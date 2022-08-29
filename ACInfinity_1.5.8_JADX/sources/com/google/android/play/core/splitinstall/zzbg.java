package com.google.android.play.core.splitinstall;

import android.util.Log;
import com.eternal.framework.http.cookie.SerializableCookie;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbg {
    static final zzk zza(XmlPullParser xmlPullParser, zzi zzi) {
        while (xmlPullParser.next() != 1) {
            try {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("splits")) {
                        while (xmlPullParser.next() != 3) {
                            if (xmlPullParser.getEventType() == 2) {
                                if (xmlPullParser.getName().equals("module")) {
                                    String zzb = zzb(SerializableCookie.NAME, xmlPullParser, zzi);
                                    if (zzb != null) {
                                        while (xmlPullParser.next() != 3) {
                                            if (xmlPullParser.getEventType() == 2) {
                                                if (xmlPullParser.getName().equals("language")) {
                                                    while (xmlPullParser.next() != 3) {
                                                        if (xmlPullParser.getEventType() == 2) {
                                                            if (xmlPullParser.getName().equals("entry")) {
                                                                String zzb2 = zzb("key", xmlPullParser, zzi);
                                                                String zzb3 = zzb("split", xmlPullParser, zzi);
                                                                zzc(xmlPullParser, zzi);
                                                                if (!(zzb2 == null || zzb3 == null)) {
                                                                    zzi.zza(zzb, zzb2, zzb3);
                                                                }
                                                            } else {
                                                                zzc(xmlPullParser, zzi);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    zzc(xmlPullParser, zzi);
                                                }
                                            }
                                        }
                                    } else {
                                        zzc(xmlPullParser, zzi);
                                    }
                                } else {
                                    zzc(xmlPullParser, zzi);
                                }
                            }
                        }
                    } else {
                        zzc(xmlPullParser, zzi);
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e);
                return null;
            }
        }
        return zzi.zzb();
    }

    private static final String zzb(String str, XmlPullParser xmlPullParser, zzi zzi) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    private static final void zzc(XmlPullParser xmlPullParser, zzi zzi) throws IOException, XmlPullParserException {
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
