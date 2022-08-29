package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzen {
    private static final zzag zza = new zzag("SliceMetadataManager");
    private final byte[] zzb = new byte[8192];
    private final zzbh zzc;
    private final String zzd;
    private final int zze;
    private final long zzf;
    private final String zzg;
    private int zzh;

    zzen(zzbh zzbh, String str, int i, long j, String str2) {
        this.zzc = zzbh;
        this.zzd = str;
        this.zze = i;
        this.zzf = j;
        this.zzg = str2;
        this.zzh = -1;
    }

    private final File zzn() {
        File zzo = this.zzc.zzo(this.zzd, this.zze, this.zzf, this.zzg);
        if (!zzo.exists()) {
            zzo.mkdirs();
        }
        return zzo;
    }

    private final File zzo() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        zzn.getParentFile().mkdirs();
        zzn.createNewFile();
        return zzn;
    }

    /* access modifiers changed from: package-private */
    public final int zza() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        if (!zzn.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(zzn);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            if (Integer.parseInt(properties.getProperty("fileStatus", "-1")) == 4) {
                return -1;
            }
            if (properties.getProperty("previousChunk") != null) {
                return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
            }
            throw new zzck("Slice checkpoint file corrupt.");
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final zzem zzb() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        if (zzn.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(zzn);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                    throw new zzck("Slice checkpoint file corrupt.");
                }
                try {
                    int parseInt = Integer.parseInt(properties.getProperty("fileStatus"));
                    String property = properties.getProperty("fileName");
                    long parseLong = Long.parseLong(properties.getProperty("fileOffset", "-1"));
                    long parseLong2 = Long.parseLong(properties.getProperty("remainingBytes", "-1"));
                    int parseInt2 = Integer.parseInt(properties.getProperty("previousChunk"));
                    this.zzh = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                    return new zzbp(parseInt, property, parseLong, parseLong2, parseInt2);
                } catch (NumberFormatException e) {
                    throw new zzck("Slice checkpoint file corrupt.", (Exception) e);
                }
            } catch (Throwable unused) {
            }
        } else {
            throw new zzck("Slice checkpoint file does not exist.");
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final File zzc() {
        return new File(zzn(), String.format("%s-NAM.dat", new Object[]{Integer.valueOf(this.zzh)}));
    }

    /* access modifiers changed from: package-private */
    public final void zzd(InputStream inputStream, long j) throws IOException {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(zzc(), "rw");
        try {
            randomAccessFile.seek(j);
            do {
                read = inputStream.read(this.zzb);
                if (read > 0) {
                    randomAccessFile.write(this.zzb, 0, read);
                }
            } while (read == 8192);
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final void zze(long j, byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(zzc(), "rw");
        try {
            randomAccessFile.seek(j);
            randomAccessFile.write(bArr, i, i2);
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final void zzf(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "3");
        properties.put("fileOffset", String.valueOf(zzc().length()));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final void zzg(String str, long j, long j2, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j));
        properties.put("remainingBytes", String.valueOf(j2));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final void zzh(byte[] bArr, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File zzm = this.zzc.zzm(this.zzd, this.zze, this.zzf, this.zzg);
            if (zzm.exists()) {
                zzm.delete();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(zzm);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.close();
                return;
            } catch (Throwable unused) {
            }
            throw th;
            throw th;
        } catch (Throwable unused2) {
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0030 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzj(byte[] r6) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            r1 = 1
            int r0 = r0 + r1
            r5.zzh = r0
            java.io.File r0 = new java.io.File
            java.io.File r2 = r5.zzn()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            int r3 = r5.zzh
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = 0
            r1[r4] = r3
            java.lang.String r3 = "%s-LFH.dat"
            java.lang.String r1 = java.lang.String.format(r3, r1)
            r0.<init>(r2, r1)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0031 }
            r1.write(r6)     // Catch:{ all -> 0x002c }
            r1.close()     // Catch:{ IOException -> 0x0031 }
            return
        L_0x002c:
            r6 = move-exception
            r1.close()     // Catch:{ all -> 0x0030 }
        L_0x0030:
            throw r6     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            r6 = move-exception
            com.google.android.play.core.assetpacks.zzck r0 = new com.google.android.play.core.assetpacks.zzck
            java.lang.String r1 = "Could not write metadata file."
            r0.<init>((java.lang.String) r1, (java.lang.Exception) r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzen.zzj(byte[]):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzk(byte[] bArr, InputStream inputStream) throws IOException {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(zzc());
        try {
            fileOutputStream.write(bArr);
            int read = inputStream.read(this.zzb);
            while (read > 0) {
                fileOutputStream.write(this.zzb, 0, read);
                read = inputStream.read(this.zzb);
            }
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final void zzl(byte[] bArr, int i, int i2) throws IOException {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(zzc());
        try {
            fileOutputStream.write(bArr, 0, i2);
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzm() {
        /*
            r6 = this;
            com.google.android.play.core.assetpacks.zzbh r0 = r6.zzc
            java.lang.String r1 = r6.zzd
            int r2 = r6.zze
            long r3 = r6.zzf
            java.lang.String r5 = r6.zzg
            java.io.File r0 = r0.zzn(r1, r2, r3, r5)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto L_0x0016
            return r2
        L_0x0016:
            r1 = 1
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004b }
            r3.<init>(r0)     // Catch:{ IOException -> 0x004b }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x0046 }
            r0.<init>()     // Catch:{ all -> 0x0046 }
            r0.load(r3)     // Catch:{ all -> 0x0046 }
            r3.close()     // Catch:{ IOException -> 0x004b }
            java.lang.String r3 = "fileStatus"
            java.lang.String r4 = r0.getProperty(r3)
            if (r4 != 0) goto L_0x0039
            com.google.android.play.core.internal.zzag r0 = zza
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r3 = "Slice checkpoint file corrupt while checking if extraction finished."
            r0.zzb(r3, r1)
            return r2
        L_0x0039:
            java.lang.String r0 = r0.getProperty(r3)
            int r0 = java.lang.Integer.parseInt(r0)
            r3 = 4
            if (r0 != r3) goto L_0x0045
            return r1
        L_0x0045:
            return r2
        L_0x0046:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x004a }
        L_0x004a:
            throw r0     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            r0 = move-exception
            com.google.android.play.core.internal.zzag r3 = zza
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r2] = r0
            java.lang.String r0 = "Could not read checkpoint while checking if extraction finished. %s"
            r3.zzb(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzen.zzm():boolean");
    }
}
