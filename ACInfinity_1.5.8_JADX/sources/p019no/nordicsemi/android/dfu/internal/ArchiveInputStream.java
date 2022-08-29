package p019no.nordicsemi.android.dfu.internal;

import android.os.Build;
import android.util.Log;
import com.alibaba.android.arouter.utils.Consts;
import com.bumptech.glide.load.Key;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import p019no.nordicsemi.android.dfu.internal.manifest.Manifest;
import p019no.nordicsemi.android.dfu.internal.manifest.ManifestFile;

/* renamed from: no.nordicsemi.android.dfu.internal.ArchiveInputStream */
public class ArchiveInputStream extends InputStream {
    private static final String APPLICATION_BIN = "application.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_INIT = "application.dat";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String TAG = "DfuArchiveInputStream";
    private byte[] applicationBytes;
    private byte[] applicationInitBytes;
    private int applicationSize;
    private byte[] bootloaderBytes;
    private int bootloaderSize;
    private int bytesRead;
    private int bytesReadFromCurrentSource;
    private int bytesReadFromMarkedSource;
    private final CRC32 crc32 = new CRC32();
    private byte[] currentSource;
    private final Map<String, byte[]> entries;
    private Manifest manifest;
    private byte[] markedSource;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] softDeviceBytes;
    private int softDeviceSize;
    private byte[] systemInitBytes;
    private int type;
    private final ZipInputStream zipInputStream;

    public boolean markSupported() {
        return true;
    }

    public long skip(long j) {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:81:0x01f5 A[Catch:{ all -> 0x025a }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0203 A[Catch:{ all -> 0x025a }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0224 A[Catch:{ all -> 0x025a }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0232 A[Catch:{ all -> 0x025a }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0252 A[SYNTHETIC, Splitter:B:99:0x0252] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ArchiveInputStream(java.io.InputStream r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5.<init>()
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            r0.<init>(r6)
            r5.zipInputStream = r0
            java.util.zip.CRC32 r6 = new java.util.zip.CRC32
            r6.<init>()
            r5.crc32 = r6
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r5.entries = r6
            r1 = 0
            r5.bytesRead = r1
            r5.bytesReadFromCurrentSource = r1
            r5.parseZip(r7)     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest     // Catch:{ all -> 0x025a }
            r2 = 1
            if (r7 == 0) goto L_0x01ad
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r7.getApplicationInfo()     // Catch:{ all -> 0x025a }
            java.lang.String r3 = " not found."
            if (r7 == 0) goto L_0x007a
            if (r8 == 0) goto L_0x0033
            r7 = r8 & 4
            if (r7 <= 0) goto L_0x007a
        L_0x0033:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r7.getApplicationInfo()     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.applicationBytes = r4     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getDatFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.applicationInitBytes = r4     // Catch:{ all -> 0x025a }
            byte[] r4 = r5.applicationBytes     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x005c
            int r7 = r4.length     // Catch:{ all -> 0x025a }
            r5.applicationSize = r7     // Catch:{ all -> 0x025a }
            r5.currentSource = r4     // Catch:{ all -> 0x025a }
            r7 = 1
            goto L_0x007b
        L_0x005c:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r8.<init>()     // Catch:{ all -> 0x025a }
            java.lang.String r0 = "Application file "
            r8.append(r0)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            r8.append(r7)     // Catch:{ all -> 0x025a }
            r8.append(r3)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x025a }
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x007a:
            r7 = 0
        L_0x007b:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r4 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r4 = r4.getBootloaderInfo()     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x00dc
            if (r8 == 0) goto L_0x0089
            r4 = r8 & 2
            if (r4 <= 0) goto L_0x00dc
        L_0x0089:
            byte[] r7 = r5.systemInitBytes     // Catch:{ all -> 0x025a }
            if (r7 != 0) goto L_0x00d4
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r7.getBootloaderInfo()     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.bootloaderBytes = r4     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getDatFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.systemInitBytes = r4     // Catch:{ all -> 0x025a }
            byte[] r4 = r5.bootloaderBytes     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x00b6
            int r7 = r4.length     // Catch:{ all -> 0x025a }
            r5.bootloaderSize = r7     // Catch:{ all -> 0x025a }
            r5.currentSource = r4     // Catch:{ all -> 0x025a }
            r7 = 1
            goto L_0x00dc
        L_0x00b6:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r8.<init>()     // Catch:{ all -> 0x025a }
            java.lang.String r0 = "Bootloader file "
            r8.append(r0)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            r8.append(r7)     // Catch:{ all -> 0x025a }
            r8.append(r3)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x025a }
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x00d4:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.String r7 = "Manifest: softdevice and bootloader specified. Use softdevice_bootloader instead."
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x00dc:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r4 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r4 = r4.getSoftdeviceInfo()     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x0131
            if (r8 == 0) goto L_0x00ea
            r4 = r8 & 1
            if (r4 <= 0) goto L_0x0131
        L_0x00ea:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r7.getSoftdeviceInfo()     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.softDeviceBytes = r4     // Catch:{ all -> 0x025a }
            java.lang.String r4 = r7.getDatFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.systemInitBytes = r4     // Catch:{ all -> 0x025a }
            byte[] r4 = r5.softDeviceBytes     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x0113
            int r7 = r4.length     // Catch:{ all -> 0x025a }
            r5.softDeviceSize = r7     // Catch:{ all -> 0x025a }
            r5.currentSource = r4     // Catch:{ all -> 0x025a }
            r7 = 1
            goto L_0x0131
        L_0x0113:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r8.<init>()     // Catch:{ all -> 0x025a }
            java.lang.String r0 = "SoftDevice file "
            r8.append(r0)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            r8.append(r7)     // Catch:{ all -> 0x025a }
            r8.append(r3)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x025a }
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x0131:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r4 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo r4 = r4.getSoftdeviceBootloaderInfo()     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x01a0
            if (r8 == 0) goto L_0x0143
            r4 = r8 & 1
            if (r4 <= 0) goto L_0x01a0
            r8 = r8 & 2
            if (r8 <= 0) goto L_0x01a0
        L_0x0143:
            byte[] r7 = r5.systemInitBytes     // Catch:{ all -> 0x025a }
            if (r7 != 0) goto L_0x0198
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest     // Catch:{ all -> 0x025a }
            no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo r7 = r7.getSoftdeviceBootloaderInfo()     // Catch:{ all -> 0x025a }
            java.lang.String r8 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r8 = r6.get(r8)     // Catch:{ all -> 0x025a }
            byte[] r8 = (byte[]) r8     // Catch:{ all -> 0x025a }
            r5.softDeviceAndBootloaderBytes = r8     // Catch:{ all -> 0x025a }
            java.lang.String r8 = r7.getDatFileName()     // Catch:{ all -> 0x025a }
            java.lang.Object r6 = r6.get(r8)     // Catch:{ all -> 0x025a }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x025a }
            r5.systemInitBytes = r6     // Catch:{ all -> 0x025a }
            byte[] r6 = r5.softDeviceAndBootloaderBytes     // Catch:{ all -> 0x025a }
            if (r6 == 0) goto L_0x017a
            int r6 = r7.getSoftdeviceSize()     // Catch:{ all -> 0x025a }
            r5.softDeviceSize = r6     // Catch:{ all -> 0x025a }
            int r6 = r7.getBootloaderSize()     // Catch:{ all -> 0x025a }
            r5.bootloaderSize = r6     // Catch:{ all -> 0x025a }
            byte[] r6 = r5.softDeviceAndBootloaderBytes     // Catch:{ all -> 0x025a }
            r5.currentSource = r6     // Catch:{ all -> 0x025a }
            goto L_0x01a1
        L_0x017a:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x025a }
            r8.<init>()     // Catch:{ all -> 0x025a }
            java.lang.String r0 = "File "
            r8.append(r0)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r7.getBinFileName()     // Catch:{ all -> 0x025a }
            r8.append(r7)     // Catch:{ all -> 0x025a }
            r8.append(r3)     // Catch:{ all -> 0x025a }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x025a }
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x0198:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.String r7 = "Manifest: The softdevice_bootloader may not be used together with softdevice or bootloader."
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x01a0:
            r2 = r7
        L_0x01a1:
            if (r2 == 0) goto L_0x01a5
            goto L_0x0245
        L_0x01a5:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.String r7 = "Manifest file must specify at least one file."
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x01ad:
            if (r8 == 0) goto L_0x01b3
            r7 = r8 & 4
            if (r7 <= 0) goto L_0x01e0
        L_0x01b3:
            java.lang.String r7 = "application.hex"
            java.lang.Object r7 = r6.get(r7)     // Catch:{ all -> 0x025a }
            byte[] r7 = (byte[]) r7     // Catch:{ all -> 0x025a }
            r5.applicationBytes = r7     // Catch:{ all -> 0x025a }
            if (r7 != 0) goto L_0x01c9
            java.lang.String r7 = "application.bin"
            java.lang.Object r7 = r6.get(r7)     // Catch:{ all -> 0x025a }
            byte[] r7 = (byte[]) r7     // Catch:{ all -> 0x025a }
            r5.applicationBytes = r7     // Catch:{ all -> 0x025a }
        L_0x01c9:
            byte[] r7 = r5.applicationBytes     // Catch:{ all -> 0x025a }
            if (r7 == 0) goto L_0x01e0
            int r7 = r7.length     // Catch:{ all -> 0x025a }
            r5.applicationSize = r7     // Catch:{ all -> 0x025a }
            java.lang.String r7 = "application.dat"
            java.lang.Object r7 = r6.get(r7)     // Catch:{ all -> 0x025a }
            byte[] r7 = (byte[]) r7     // Catch:{ all -> 0x025a }
            r5.applicationInitBytes = r7     // Catch:{ all -> 0x025a }
            byte[] r7 = r5.applicationBytes     // Catch:{ all -> 0x025a }
            r5.currentSource = r7     // Catch:{ all -> 0x025a }
            r7 = 1
            goto L_0x01e1
        L_0x01e0:
            r7 = 0
        L_0x01e1:
            java.lang.String r3 = "system.dat"
            if (r8 == 0) goto L_0x01e9
            r4 = r8 & 2
            if (r4 <= 0) goto L_0x0213
        L_0x01e9:
            java.lang.String r4 = "bootloader.hex"
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.bootloaderBytes = r4     // Catch:{ all -> 0x025a }
            if (r4 != 0) goto L_0x01ff
            java.lang.String r4 = "bootloader.bin"
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x025a }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x025a }
            r5.bootloaderBytes = r4     // Catch:{ all -> 0x025a }
        L_0x01ff:
            byte[] r4 = r5.bootloaderBytes     // Catch:{ all -> 0x025a }
            if (r4 == 0) goto L_0x0213
            int r7 = r4.length     // Catch:{ all -> 0x025a }
            r5.bootloaderSize = r7     // Catch:{ all -> 0x025a }
            java.lang.Object r7 = r6.get(r3)     // Catch:{ all -> 0x025a }
            byte[] r7 = (byte[]) r7     // Catch:{ all -> 0x025a }
            r5.systemInitBytes = r7     // Catch:{ all -> 0x025a }
            byte[] r7 = r5.bootloaderBytes     // Catch:{ all -> 0x025a }
            r5.currentSource = r7     // Catch:{ all -> 0x025a }
            r7 = 1
        L_0x0213:
            if (r8 == 0) goto L_0x0218
            r8 = r8 & r2
            if (r8 <= 0) goto L_0x0242
        L_0x0218:
            java.lang.String r8 = "softdevice.hex"
            java.lang.Object r8 = r6.get(r8)     // Catch:{ all -> 0x025a }
            byte[] r8 = (byte[]) r8     // Catch:{ all -> 0x025a }
            r5.softDeviceBytes = r8     // Catch:{ all -> 0x025a }
            if (r8 != 0) goto L_0x022e
            java.lang.String r8 = "softdevice.bin"
            java.lang.Object r8 = r6.get(r8)     // Catch:{ all -> 0x025a }
            byte[] r8 = (byte[]) r8     // Catch:{ all -> 0x025a }
            r5.softDeviceBytes = r8     // Catch:{ all -> 0x025a }
        L_0x022e:
            byte[] r8 = r5.softDeviceBytes     // Catch:{ all -> 0x025a }
            if (r8 == 0) goto L_0x0242
            int r7 = r8.length     // Catch:{ all -> 0x025a }
            r5.softDeviceSize = r7     // Catch:{ all -> 0x025a }
            java.lang.Object r6 = r6.get(r3)     // Catch:{ all -> 0x025a }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x025a }
            r5.systemInitBytes = r6     // Catch:{ all -> 0x025a }
            byte[] r6 = r5.softDeviceBytes     // Catch:{ all -> 0x025a }
            r5.currentSource = r6     // Catch:{ all -> 0x025a }
            goto L_0x0243
        L_0x0242:
            r2 = r7
        L_0x0243:
            if (r2 == 0) goto L_0x0252
        L_0x0245:
            r5.mark(r1)     // Catch:{ all -> 0x025a }
            int r6 = r5.getContentType()
            r5.type = r6
            r0.close()
            return
        L_0x0252:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x025a }
            java.lang.String r7 = "The ZIP file must contain an Application, a Soft Device and/or a Bootloader."
            r6.<init>(r7)     // Catch:{ all -> 0x025a }
            throw r6     // Catch:{ all -> 0x025a }
        L_0x025a:
            r6 = move-exception
            int r7 = r5.getContentType()
            r5.type = r7
            java.util.zip.ZipInputStream r7 = r5.zipInputStream
            r7.close()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.internal.ArchiveInputStream.<init>(java.io.InputStream, int, int):void");
    }

    private String validateFilename(String str, String str2) throws IOException {
        String canonicalPath = new File(str).getCanonicalPath();
        if (canonicalPath.startsWith(new File(str2).getCanonicalPath())) {
            return canonicalPath.substring(1);
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    private void parseZip(int i) throws IOException {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = this.zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String validateFilename = validateFilename(nextEntry.getName(), Consts.DOT);
            if (nextEntry.isDirectory()) {
                Log.w(TAG, "A directory found in the ZIP: " + validateFilename + "!");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = this.zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (validateFilename.toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                    byteArray = new byte[hexInputStream.available()];
                    hexInputStream.read(byteArray);
                    hexInputStream.close();
                }
                if (!MANIFEST.equals(validateFilename)) {
                    this.entries.put(validateFilename, byteArray);
                } else if (Build.VERSION.SDK_INT >= 19) {
                    str = new String(byteArray, StandardCharsets.UTF_8);
                } else {
                    str = new String(byteArray, Key.STRING_CHARSET_NAME);
                }
            }
        }
        if (this.entries.isEmpty()) {
            throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
        } else if (str != null) {
            Manifest manifest2 = ((ManifestFile) new Gson().fromJson(str, ManifestFile.class)).getManifest();
            this.manifest = manifest2;
            if (manifest2 == null) {
                Log.w(TAG, "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
            }
        } else {
            Log.w(TAG, "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
        }
    }

    public void close() throws IOException {
        this.softDeviceBytes = null;
        this.bootloaderBytes = null;
        this.applicationBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        this.zipInputStream.close();
    }

    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        int rawRead = rawRead(bArr, i, i2);
        return (i2 <= rawRead || startNextFile() == null) ? rawRead : rawRead + rawRead(bArr, i + rawRead, i2 - rawRead);
    }

    private int rawRead(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.currentSource.length - this.bytesReadFromCurrentSource);
        System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, bArr, i, min);
        this.bytesReadFromCurrentSource += min;
        this.bytesRead += min;
        this.crc32.update(bArr, i, min);
        return min;
    }

    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    public void reset() {
        byte[] bArr;
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && (bArr = this.softDeviceBytes) != null) {
            this.crc32.update(bArr);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public void fullReset() {
        byte[] bArr;
        byte[] bArr2 = this.softDeviceBytes;
        if (!(bArr2 == null || (bArr = this.bootloaderBytes) == null || this.currentSource != bArr)) {
            this.currentSource = bArr2;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public int getContentType() {
        this.type = 0;
        if (this.softDeviceAndBootloaderBytes != null) {
            this.type = 0 | 3;
        }
        if (this.softDeviceSize > 0) {
            this.type |= 1;
        }
        if (this.bootloaderSize > 0) {
            this.type |= 2;
        }
        if (this.applicationSize > 0) {
            this.type |= 4;
        }
        return this.type;
    }

    public int setContentType(int i) {
        byte[] bArr;
        this.type = i;
        int i2 = i & 4;
        if (i2 > 0 && this.applicationBytes == null) {
            this.type = i & -5;
        }
        int i3 = i & 3;
        if (i3 == 3) {
            if (this.softDeviceBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
            if (this.bootloaderBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
        } else if (this.softDeviceAndBootloaderBytes != null) {
            this.type &= -4;
        }
        if (i3 > 0 && (bArr = this.softDeviceAndBootloaderBytes) != null) {
            this.currentSource = bArr;
        } else if ((i & 1) > 0) {
            this.currentSource = this.softDeviceBytes;
        } else if ((i & 2) > 0) {
            this.currentSource = this.bootloaderBytes;
        } else if (i2 > 0) {
            this.currentSource = this.applicationBytes;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
        return this.type;
    }

    private byte[] startNextFile() {
        byte[] bArr;
        byte[] bArr2 = this.currentSource;
        if (bArr2 != this.softDeviceBytes || (bArr = this.bootloaderBytes) == null || (this.type & 2) <= 0) {
            bArr = this.applicationBytes;
            if (bArr2 == bArr || bArr == null || (this.type & 4) <= 0) {
                bArr = null;
                this.currentSource = null;
            } else {
                this.currentSource = bArr;
            }
        } else {
            this.currentSource = bArr;
        }
        this.bytesReadFromCurrentSource = 0;
        return bArr;
    }

    public int available() {
        int softDeviceImageSize;
        int i;
        byte[] bArr = this.softDeviceAndBootloaderBytes;
        if (bArr == null || this.softDeviceSize != 0 || this.bootloaderSize != 0 || (this.type & 3) <= 0) {
            softDeviceImageSize = softDeviceImageSize() + bootloaderImageSize() + applicationImageSize();
            i = this.bytesRead;
        } else {
            softDeviceImageSize = bArr.length + applicationImageSize();
            i = this.bytesRead;
        }
        return softDeviceImageSize - i;
    }

    public int softDeviceImageSize() {
        if ((this.type & 1) > 0) {
            return this.softDeviceSize;
        }
        return 0;
    }

    public int bootloaderImageSize() {
        if ((this.type & 2) > 0) {
            return this.bootloaderSize;
        }
        return 0;
    }

    public int applicationImageSize() {
        if ((this.type & 4) > 0) {
            return this.applicationSize;
        }
        return 0;
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }

    public boolean isSecureDfuRequired() {
        Manifest manifest2 = this.manifest;
        return manifest2 != null && manifest2.isSecureDfuRequired();
    }
}
