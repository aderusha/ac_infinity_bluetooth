package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzcm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbk extends zzcm {
    private final File zza;
    private final File zzb;
    private final NavigableMap zzc = new TreeMap();

    zzbk(File file, File file2) throws IOException {
        this.zza = file;
        this.zzb = file2;
        List<File> zza2 = zzep.zza(file, file2);
        if (!zza2.isEmpty()) {
            long j = 0;
            for (File file3 : zza2) {
                this.zzc.put(Long.valueOf(j), file3);
                j += file3.length();
            }
            return;
        }
        throw new zzck(String.format("Virtualized slice archive empty for %s, %s", new Object[]{file, file2}));
    }

    private final InputStream zzd(long j, Long l) throws IOException {
        FileInputStream fileInputStream = new FileInputStream((File) this.zzc.get(l));
        if (fileInputStream.skip(j - l.longValue()) == j - l.longValue()) {
            return fileInputStream;
        }
        throw new zzck(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", new Object[]{l}));
    }

    public final void close() {
    }

    public final long zza() {
        Map.Entry lastEntry = this.zzc.lastEntry();
        return ((Long) lastEntry.getKey()).longValue() + ((File) lastEntry.getValue()).length();
    }

    /* access modifiers changed from: protected */
    public final InputStream zzb(long j, long j2) throws IOException {
        if (j < 0 || j2 < 0) {
            throw new zzck(String.format("Invalid input parameters %s, %s", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
        }
        long j3 = j + j2;
        if (j3 <= zza()) {
            Long l = (Long) this.zzc.floorKey(Long.valueOf(j));
            Long l2 = (Long) this.zzc.floorKey(Long.valueOf(j3));
            if (l.equals(l2)) {
                return new zzbj(zzd(j, l), j2);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzd(j, l));
            Collection values = this.zzc.subMap(l, false, l2, false).values();
            if (!values.isEmpty()) {
                arrayList.add(new zzdr(Collections.enumeration(values)));
            }
            arrayList.add(new zzbj(new FileInputStream((File) this.zzc.get(l2)), j2 - (l2.longValue() - j)));
            return new SequenceInputStream(Collections.enumeration(arrayList));
        }
        throw new zzck(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", new Object[]{Long.valueOf(zza()), Long.valueOf(j3)}));
    }
}
