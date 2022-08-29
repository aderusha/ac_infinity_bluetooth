package com.google.android.play.core.internal;

import com.google.android.play.core.splitinstall.zzn;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbk implements zzaz {
    zzbk() {
    }

    public static void zzc(ClassLoader classLoader, Set set, zzbj zzbj) {
        if (!set.isEmpty()) {
            HashSet hashSet = new HashSet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(((File) it.next()).getParentFile());
            }
            Object zzc = zzbf.zzc(classLoader);
            zzbv zzb = zzbw.zzb(zzc, "nativeLibraryDirectories", List.class);
            synchronized (zzn.class) {
                ArrayList arrayList = new ArrayList((Collection) zzb.zzc());
                hashSet.removeAll(arrayList);
                arrayList.addAll(hashSet);
                zzb.zze(arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            Object[] zza = zzbj.zza(zzc, new ArrayList(hashSet), (File) null, arrayList2);
            if (!arrayList2.isEmpty()) {
                zzbt zzbt = new zzbt("Error in makePathElements");
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    IOException iOException = (IOException) arrayList2.get(i);
                }
                throw zzbt;
            }
            synchronized (zzn.class) {
                zzbw.zza(zzc, "nativeLibraryPathElements", Object.class).zzb(Arrays.asList(zza));
            }
        }
    }

    public static boolean zzd(ClassLoader classLoader, File file, File file2, boolean z, String str) {
        return zzbf.zze(classLoader, file, file2, z, new zzbh(), "zip", new zzbc());
    }

    public final void zza(ClassLoader classLoader, Set set) {
        zzc(classLoader, set, new zzbi());
    }

    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzd(classLoader, file, file2, z, "zip");
    }
}
