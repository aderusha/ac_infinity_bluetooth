package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbm extends AssetPackLocation {
    private final int zza;
    private final String zzb;
    private final String zzc;

    zzbm(int i, String str, String str2) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
    }

    public final String assetsPath() {
        return this.zzc;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r1 = r4.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.play.core.assetpacks.AssetPackLocation
            r2 = 0
            if (r1 == 0) goto L_0x003f
            com.google.android.play.core.assetpacks.AssetPackLocation r5 = (com.google.android.play.core.assetpacks.AssetPackLocation) r5
            int r1 = r4.zza
            int r3 = r5.packStorageMethod()
            if (r1 != r3) goto L_0x003f
            java.lang.String r1 = r4.zzb
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = r5.path()
            if (r1 != 0) goto L_0x003f
            goto L_0x0028
        L_0x001e:
            java.lang.String r3 = r5.path()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x003f
        L_0x0028:
            java.lang.String r1 = r4.zzc
            if (r1 != 0) goto L_0x0033
            java.lang.String r5 = r5.assetsPath()
            if (r5 != 0) goto L_0x003f
            goto L_0x003e
        L_0x0033:
            java.lang.String r5 = r5.assetsPath()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            return r0
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzbm.equals(java.lang.Object):boolean");
    }

    public final int packStorageMethod() {
        return this.zza;
    }

    public final String path() {
        return this.zzb;
    }

    public final String toString() {
        int i = this.zza;
        String str = this.zzb;
        String str2 = this.zzc;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str2).length());
        sb.append("AssetPackLocation{packStorageMethod=");
        sb.append(i);
        sb.append(", path=");
        sb.append(str);
        sb.append(", assetsPath=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    public final int hashCode() {
        int i;
        int i2 = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        int i3 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i4 = (i2 ^ i) * 1000003;
        String str2 = this.zzc;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return i4 ^ i3;
    }
}
