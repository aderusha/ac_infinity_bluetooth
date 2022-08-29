package com.eternal.framework.http.download;

public class DownloadManager {
    /* JADX WARNING: Can't wrap try/catch for region: R(14:4|5|(1:7)(1:8)|9|(1:11)|12|(3:13|14|(3:16|(2:18|48)(2:19|47)|20)(1:46))|21|22|23|(1:25)|26|27|28) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0085 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0096 A[Catch:{ IOException -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009d A[SYNTHETIC, Splitter:B:42:0x009d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File saveFile(okhttp3.ResponseBody r19, java.lang.String r20, java.lang.String r21, com.eternal.framework.http.download.ProgressListener r22) throws java.io.IOException {
        /*
            r18 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            android.content.Context r1 = com.eternal.framework.http.RxHttpUtils.getContext()
            r2 = 0
            java.io.File r1 = r1.getExternalFilesDir(r2)
            r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            long r11 = r19.contentLength()
            r1 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r1]
            java.io.InputStream r13 = r19.byteStream()     // Catch:{ all -> 0x008f }
            r3 = 0
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x008b }
            if (r21 != 0) goto L_0x002d
            goto L_0x002f
        L_0x002d:
            r0 = r21
        L_0x002f:
            r5.<init>(r0)     // Catch:{ all -> 0x008b }
            boolean r0 = r5.exists()     // Catch:{ all -> 0x008b }
            if (r0 != 0) goto L_0x003b
            r5.mkdirs()     // Catch:{ all -> 0x008b }
        L_0x003b:
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x008b }
            r6 = r20
            r0.<init>(r5, r6)     // Catch:{ all -> 0x008b }
            java.io.FileOutputStream r14 = new java.io.FileOutputStream     // Catch:{ all -> 0x008b }
            r14.<init>(r0)     // Catch:{ all -> 0x008b }
        L_0x0047:
            int r2 = r13.read(r1)     // Catch:{ all -> 0x0089 }
            r5 = -1
            if (r2 == r5) goto L_0x007a
            long r5 = (long) r2     // Catch:{ all -> 0x0089 }
            long r9 = r3 + r5
            r3 = 0
            r14.write(r1, r3, r2)     // Catch:{ all -> 0x0089 }
            float r2 = (float) r9     // Catch:{ all -> 0x0089 }
            r4 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 * r4
            float r4 = (float) r11     // Catch:{ all -> 0x0089 }
            float r2 = r2 / r4
            r4 = 1120403456(0x42c80000, float:100.0)
            float r2 = r2 * r4
            int r8 = (int) r2     // Catch:{ all -> 0x0089 }
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x0067
            r2 = 1
            goto L_0x0068
        L_0x0067:
            r2 = 0
        L_0x0068:
            java.lang.String r15 = r0.getAbsolutePath()     // Catch:{ all -> 0x0089 }
            r3 = r22
            r4 = r9
            r6 = r11
            r16 = r9
            r9 = r2
            r10 = r15
            r3.onResponseProgress(r4, r6, r8, r9, r10)     // Catch:{ all -> 0x0089 }
            r3 = r16
            goto L_0x0047
        L_0x007a:
            r14.flush()     // Catch:{ all -> 0x0089 }
            r19.close()     // Catch:{ IOException -> 0x0085 }
            if (r13 == 0) goto L_0x0085
            r13.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0085:
            r14.close()     // Catch:{ IOException -> 0x0088 }
        L_0x0088:
            return r0
        L_0x0089:
            r0 = move-exception
            goto L_0x008d
        L_0x008b:
            r0 = move-exception
            r14 = r2
        L_0x008d:
            r2 = r13
            goto L_0x0091
        L_0x008f:
            r0 = move-exception
            r14 = r2
        L_0x0091:
            r19.close()     // Catch:{ IOException -> 0x009a }
            if (r2 == 0) goto L_0x009b
            r2.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x009b
        L_0x009a:
        L_0x009b:
            if (r14 == 0) goto L_0x00a0
            r14.close()     // Catch:{ IOException -> 0x00a0 }
        L_0x00a0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.download.DownloadManager.saveFile(okhttp3.ResponseBody, java.lang.String, java.lang.String, com.eternal.framework.http.download.ProgressListener):java.io.File");
    }
}
