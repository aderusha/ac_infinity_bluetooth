package com.google.android.play.core.splitinstall.testing;

import android.content.Context;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.zzs;
import com.google.android.play.core.splitinstall.zzu;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class FakeSplitInstallManagerFactory {
    private static FakeSplitInstallManager zza;

    private FakeSplitInstallManagerFactory() {
    }

    public static FakeSplitInstallManager create(Context context) {
        try {
            File zzb = zzu.zza(context).zzb();
            if (zzb == null) {
                throw new LocalTestingException("Failed to retrieve local testing directory path");
            } else if (zzb.exists()) {
                return create(context, zzb);
            } else {
                throw new LocalTestingException(String.format("Local testing directory not found: %s", new Object[]{zzb}));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static FakeSplitInstallManager createNewInstance(Context context, File file) {
        SplitCompat.install(context);
        return new FakeSplitInstallManager(context, file, new zzs(context, context.getPackageName()), new zzq(file));
    }

    public static synchronized FakeSplitInstallManager create(Context context, File file) {
        FakeSplitInstallManager fakeSplitInstallManager;
        synchronized (FakeSplitInstallManagerFactory.class) {
            FakeSplitInstallManager fakeSplitInstallManager2 = zza;
            if (fakeSplitInstallManager2 == null) {
                zza = createNewInstance(context, file);
            } else if (!fakeSplitInstallManager2.zzc().getAbsolutePath().equals(file.getAbsolutePath())) {
                throw new RuntimeException(String.format("Different module directories used to initialize FakeSplitInstallManager: '%s' and '%s'", new Object[]{zza.zzc().getAbsolutePath(), file.getAbsolutePath()}));
            }
            fakeSplitInstallManager = zza;
        }
        return fakeSplitInstallManager;
    }
}
