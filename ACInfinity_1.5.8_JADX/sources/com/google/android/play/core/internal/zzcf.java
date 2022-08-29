package com.google.android.play.core.internal;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzcf {
    private final Context zza;

    public zzcf(Context context) {
        this.zza = context;
    }

    private static String zzb(Locale locale) {
        String str;
        String valueOf = String.valueOf(locale.getLanguage());
        if (locale.getCountry().isEmpty()) {
            str = "";
        } else {
            String valueOf2 = String.valueOf(locale.getCountry());
            str = valueOf2.length() != 0 ? "_".concat(valueOf2) : new String("_");
        }
        String valueOf3 = String.valueOf(str);
        return valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf);
    }

    public final List zza() {
        Configuration configuration = this.zza.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT < 24) {
            return Collections.singletonList(zzb(configuration.locale));
        }
        LocaleList locales = configuration.getLocales();
        ArrayList arrayList = new ArrayList(locales.size());
        for (int i = 0; i < locales.size(); i++) {
            arrayList.add(zzb(locales.get(i)));
        }
        return arrayList;
    }
}
