package com.afollestad.materialdialogs;

import androidx.core.view.GravityCompat;

public enum GravityEnum {
    START,
    CENTER,
    END;
    
    private static final boolean HAS_RTL = false;

    /* renamed from: com.afollestad.materialdialogs.GravityEnum$1 */
    static /* synthetic */ class C08011 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$GravityEnum = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.afollestad.materialdialogs.GravityEnum[] r0 = com.afollestad.materialdialogs.GravityEnum.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$GravityEnum = r0
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x001d }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.CENTER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.END     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.GravityEnum.C08011.<clinit>():void");
        }
    }

    public int getGravityInt() {
        int i = C08011.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            if (i != 3) {
                throw new IllegalStateException("Invalid gravity constant");
            } else if (HAS_RTL) {
                return GravityCompat.END;
            } else {
                return 5;
            }
        } else if (HAS_RTL) {
            return GravityCompat.START;
        } else {
            return 3;
        }
    }

    public int getTextAlignment() {
        int i = C08011.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
        if (i != 2) {
            return i != 3 ? 5 : 6;
        }
        return 4;
    }
}
