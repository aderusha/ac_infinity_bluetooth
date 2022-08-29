package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    /* access modifiers changed from: protected */
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        if (r4 != null) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0065, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        if ((r14 & 1) == 0) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006c, code lost:
        r12 = 700;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006f, code lost:
        r12 = 400;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0073, code lost:
        if ((r14 & 2) == 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0075, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008a, code lost:
        return new android.graphics.Typeface.CustomFallbackBuilder(r4.build()).setStyle(new android.graphics.fonts.FontStyle(r12, r2)).build();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r11, android.os.CancellationSignal r12, androidx.core.provider.FontsContractCompat.FontInfo[] r13, int r14) {
        /*
            r10 = this;
            android.content.ContentResolver r11 = r11.getContentResolver()
            r0 = 0
            int r1 = r13.length     // Catch:{ Exception -> 0x008b }
            r2 = 0
            r4 = r0
            r3 = 0
        L_0x0009:
            r5 = 1
            if (r3 >= r1) goto L_0x0063
            r6 = r13[r3]     // Catch:{ Exception -> 0x008b }
            android.net.Uri r7 = r6.getUri()     // Catch:{ IOException -> 0x0060 }
            java.lang.String r8 = "r"
            android.os.ParcelFileDescriptor r7 = r11.openFileDescriptor(r7, r8, r12)     // Catch:{ IOException -> 0x0060 }
            if (r7 != 0) goto L_0x0020
            if (r7 == 0) goto L_0x0060
        L_0x001c:
            r7.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0060
        L_0x0020:
            android.graphics.fonts.Font$Builder r8 = new android.graphics.fonts.Font$Builder     // Catch:{ all -> 0x0054 }
            r8.<init>(r7)     // Catch:{ all -> 0x0054 }
            int r9 = r6.getWeight()     // Catch:{ all -> 0x0054 }
            android.graphics.fonts.Font$Builder r8 = r8.setWeight(r9)     // Catch:{ all -> 0x0054 }
            boolean r9 = r6.isItalic()     // Catch:{ all -> 0x0054 }
            if (r9 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r5 = 0
        L_0x0035:
            android.graphics.fonts.Font$Builder r5 = r8.setSlant(r5)     // Catch:{ all -> 0x0054 }
            int r6 = r6.getTtcIndex()     // Catch:{ all -> 0x0054 }
            android.graphics.fonts.Font$Builder r5 = r5.setTtcIndex(r6)     // Catch:{ all -> 0x0054 }
            android.graphics.fonts.Font r5 = r5.build()     // Catch:{ all -> 0x0054 }
            if (r4 != 0) goto L_0x004e
            android.graphics.fonts.FontFamily$Builder r6 = new android.graphics.fonts.FontFamily$Builder     // Catch:{ all -> 0x0054 }
            r6.<init>(r5)     // Catch:{ all -> 0x0054 }
            r4 = r6
            goto L_0x0051
        L_0x004e:
            r4.addFont(r5)     // Catch:{ all -> 0x0054 }
        L_0x0051:
            if (r7 == 0) goto L_0x0060
            goto L_0x001c
        L_0x0054:
            r5 = move-exception
            if (r7 == 0) goto L_0x005f
            r7.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ IOException -> 0x0060 }
        L_0x005f:
            throw r5     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            int r3 = r3 + 1
            goto L_0x0009
        L_0x0063:
            if (r4 != 0) goto L_0x0066
            return r0
        L_0x0066:
            android.graphics.fonts.FontStyle r11 = new android.graphics.fonts.FontStyle     // Catch:{ Exception -> 0x008b }
            r12 = r14 & 1
            if (r12 == 0) goto L_0x006f
            r12 = 700(0x2bc, float:9.81E-43)
            goto L_0x0071
        L_0x006f:
            r12 = 400(0x190, float:5.6E-43)
        L_0x0071:
            r13 = r14 & 2
            if (r13 == 0) goto L_0x0076
            r2 = 1
        L_0x0076:
            r11.<init>(r12, r2)     // Catch:{ Exception -> 0x008b }
            android.graphics.Typeface$CustomFallbackBuilder r12 = new android.graphics.Typeface$CustomFallbackBuilder     // Catch:{ Exception -> 0x008b }
            android.graphics.fonts.FontFamily r13 = r4.build()     // Catch:{ Exception -> 0x008b }
            r12.<init>(r13)     // Catch:{ Exception -> 0x008b }
            android.graphics.Typeface$CustomFallbackBuilder r11 = r12.setStyle(r11)     // Catch:{ Exception -> 0x008b }
            android.graphics.Typeface r11 = r11.build()     // Catch:{ Exception -> 0x008b }
            return r11
        L_0x008b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        try {
            FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
            int length = entries.length;
            int i2 = 0;
            FontFamily.Builder builder = null;
            int i3 = 0;
            while (true) {
                int i4 = 1;
                if (i3 >= length) {
                    break;
                }
                FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = entries[i3];
                try {
                    Font.Builder weight = new Font.Builder(resources, fontFileResourceEntry.getResourceId()).setWeight(fontFileResourceEntry.getWeight());
                    if (!fontFileResourceEntry.isItalic()) {
                        i4 = 0;
                    }
                    Font build = weight.setSlant(i4).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
                i3++;
            }
            if (builder == null) {
                return null;
            }
            int i5 = (i & 1) != 0 ? 700 : 400;
            if ((i & 2) != 0) {
                i2 = 1;
            }
            return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font build = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
