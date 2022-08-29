package com.google.android.play.core.internal;

import android.content.res.AssetManager;
import com.google.android.play.core.splitcompat.zza;
import java.io.File;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzax {
    private final zza zza;
    private XmlPullParser zzb;

    public zzax(zza zza2) {
        this.zza = zza2;
    }

    public final long zza() throws IOException, XmlPullParserException {
        if (this.zzb != null) {
            while (true) {
                int next = this.zzb.next();
                if (next != 2) {
                    if (next == 1) {
                        break;
                    }
                } else if (this.zzb.getName().equals("manifest")) {
                    String attributeValue = this.zzb.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCode");
                    String attributeValue2 = this.zzb.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCodeMajor");
                    if (attributeValue != null) {
                        try {
                            int parseInt = Integer.parseInt(attributeValue);
                            if (attributeValue2 == null) {
                                return (long) parseInt;
                            }
                            try {
                                return (((long) Integer.parseInt(attributeValue2)) << 32) | (((long) parseInt) & 4294967295L);
                            } catch (NumberFormatException e) {
                                throw new XmlPullParserException(String.format("Couldn't parse versionCodeMajor to int: %s", new Object[]{e.getMessage()}));
                            }
                        } catch (NumberFormatException e2) {
                            throw new XmlPullParserException(String.format("Couldn't parse versionCode to int: %s", new Object[]{e2.getMessage()}));
                        }
                    } else {
                        throw new XmlPullParserException("Manifest entry doesn't contain 'versionCode' attribute.");
                    }
                }
            }
            throw new XmlPullParserException("Couldn't find manifest entry at top-level.");
        }
        throw new XmlPullParserException("Manifest file needs to be loaded before parsing.");
    }

    public final void zzb(AssetManager assetManager, File file) throws IOException {
        this.zzb = assetManager.openXmlResourceParser(zza.zzc(assetManager, file), "AndroidManifest.xml");
    }
}
