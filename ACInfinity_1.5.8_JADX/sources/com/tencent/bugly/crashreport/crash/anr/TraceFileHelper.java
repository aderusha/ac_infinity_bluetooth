package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.C3749y;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public class TraceFileHelper {

    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    /* compiled from: BUGLY */
    public static class C3651a {

        /* renamed from: a */
        public long f624a;

        /* renamed from: b */
        public String f625b;

        /* renamed from: c */
        public long f626c;

        /* renamed from: d */
        public Map<String, String[]> f627d;
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    /* compiled from: BUGLY */
    public interface C3652b {
        /* renamed from: a */
        boolean mo24116a(long j);

        /* renamed from: a */
        boolean mo24117a(long j, long j2, String str);

        /* renamed from: a */
        boolean mo24118a(String str, int i, String str2, String str3);
    }

    public static C3651a readTargetDumpInfo(final String str, String str2, final boolean z) {
        if (!(str == null || str2 == null)) {
            final C3651a aVar = new C3651a();
            readTraceFile(str2, new C3652b() {
                /* renamed from: a */
                public final boolean mo24118a(String str, int i, String str2, String str3) {
                    C3749y.m939c("new thread %s", str);
                    if (aVar.f624a > 0 && aVar.f626c > 0 && aVar.f625b != null) {
                        if (aVar.f627d == null) {
                            aVar.f627d = new HashMap();
                        }
                        Map<String, String[]> map = aVar.f627d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        map.put(str, new String[]{str2, str3, sb.toString()});
                    }
                    return true;
                }

                /* renamed from: a */
                public final boolean mo24117a(long j, long j2, String str) {
                    C3749y.m939c("new process %s", str);
                    if (!str.equals(str)) {
                        return true;
                    }
                    aVar.f624a = j;
                    aVar.f625b = str;
                    aVar.f626c = j2;
                    return z;
                }

                /* renamed from: a */
                public final boolean mo24116a(long j) {
                    C3749y.m939c("process end %d", Long.valueOf(j));
                    return aVar.f624a <= 0 || aVar.f626c <= 0 || aVar.f625b == null;
                }
            });
            if (aVar.f624a <= 0 || aVar.f626c <= 0 || aVar.f625b == null) {
                return null;
            }
            return aVar;
        }
        return null;
    }

    public static C3651a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            C3749y.m941e("path:%s", str);
            return null;
        }
        final C3651a aVar = new C3651a();
        readTraceFile(str, new C3652b() {
            /* renamed from: a */
            public final boolean mo24118a(String str, int i, String str2, String str3) {
                C3749y.m939c("new thread %s", str);
                if (aVar.f627d == null) {
                    aVar.f627d = new HashMap();
                }
                Map<String, String[]> map = aVar.f627d;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                map.put(str, new String[]{str2, str3, sb.toString()});
                return true;
            }

            /* renamed from: a */
            public final boolean mo24117a(long j, long j2, String str) {
                C3749y.m939c("new process %s", str);
                aVar.f624a = j;
                aVar.f625b = str;
                aVar.f626c = j2;
                return z;
            }

            /* renamed from: a */
            public final boolean mo24116a(long j) {
                C3749y.m939c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.f624a > 0 && aVar.f626c > 0 && aVar.f625b != null) {
            return aVar;
        }
        C3749y.m941e("first dump error %s", aVar.f624a + " " + aVar.f626c + " " + aVar.f625b);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x018b A[Catch:{ all -> 0x0181 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01b4 A[SYNTHETIC, Splitter:B:70:0x01b4] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01c6 A[SYNTHETIC, Splitter:B:78:0x01c6] */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void readTraceFile(java.lang.String r18, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.C3652b r19) {
        /*
            r0 = r18
            r6 = r19
            java.lang.String r7 = "\\s"
            if (r0 == 0) goto L_0x01d6
            if (r6 != 0) goto L_0x000c
            goto L_0x01d6
        L_0x000c:
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 != 0) goto L_0x0018
            return
        L_0x0018:
            r1.lastModified()
            r1.length()
            r2 = 0
            r8 = 2
            r9 = 0
            r10 = 1
            java.io.BufferedReader r11 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0184 }
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Exception -> 0x0184 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0184 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x0184 }
            java.lang.String r0 = "-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}"
            java.util.regex.Pattern r12 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = "-{5}\\send\\s\\d+\\s-{5}"
            java.util.regex.Pattern r13 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = "Cmd\\sline:\\s(\\S+)"
            java.util.regex.Pattern r14 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = "\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*"
            java.util.regex.Pattern r15 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r1 = java.util.Locale.US     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r5.<init>(r0, r1)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
        L_0x004d:
            java.util.regex.Pattern[] r0 = new java.util.regex.Pattern[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0[r9] = r12     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.Object[] r0 = m442a(r11, r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r0 == 0) goto L_0x016a
            java.util.regex.Pattern[] r1 = new java.util.regex.Pattern[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r1[r9] = r14     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.Object[] r1 = m442a(r11, r1)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r1 != 0) goto L_0x0078
            java.lang.String r0 = "Failed to find process name."
            java.lang.Object[] r1 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r11.close()     // Catch:{ IOException -> 0x006c }
            return
        L_0x006c:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r0 != 0) goto L_0x0077
            r1.printStackTrace()
        L_0x0077:
            return
        L_0x0078:
            r0 = r0[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r2 = r0[r8]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            long r2 = java.lang.Long.parseLong(r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r4.<init>()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r16 = 4
            r9 = r0[r16]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r4.append(r9)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r9 = " "
            r4.append(r9)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r9 = 5
            r0 = r0[r9]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r4.append(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.util.Date r0 = r5.parse(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            long r16 = r0.getTime()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0 = r1[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.util.regex.Matcher r0 = r14.matcher(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0.find()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0.group(r10)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r9 = r0.group(r10)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0 = r19
            r1 = r2
            r3 = r16
            r16 = r5
            r5 = r9
            boolean r0 = r0.mo24117a(r1, r3, r5)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r0 != 0) goto L_0x00dd
            r11.close()     // Catch:{ IOException -> 0x00d1 }
            return
        L_0x00d1:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r0 != 0) goto L_0x00dc
            r1.printStackTrace()
        L_0x00dc:
            return
        L_0x00dd:
            java.util.regex.Pattern[] r0 = new java.util.regex.Pattern[r8]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r1 = 0
            r0[r1] = r15     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0[r10] = r13     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.Object[] r0 = m442a(r11, r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r0 == 0) goto L_0x0165
            r2 = r0[r1]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r2 != r15) goto L_0x013f
            r0 = r0[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r1 = "\".+\""
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.util.regex.Matcher r1 = r1.matcher(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r1.find()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r1 = r1.group()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            int r2 = r1.length()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            int r2 = r2 - r10
            java.lang.String r1 = r1.substring(r10, r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r2 = "NATIVE"
            r0.contains(r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r2 = "tid=\\d+"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.util.regex.Matcher r0 = r2.matcher(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0.find()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r0.group()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r2 = "="
            int r2 = r0.indexOf(r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            int r2 = r2 + r10
            java.lang.String r0 = r0.substring(r2)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r2 = m441a(r11)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r3 = m443b(r11)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r6.mo24118a(r1, r0, r2, r3)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            goto L_0x00dd
        L_0x013f:
            r0 = r0[r10]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            r0 = r0[r8]     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            boolean r0 = r6.mo24116a(r0)     // Catch:{ Exception -> 0x017e, all -> 0x017a }
            if (r0 != 0) goto L_0x0165
            r11.close()     // Catch:{ IOException -> 0x0159 }
            return
        L_0x0159:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r0 != 0) goto L_0x0164
            r1.printStackTrace()
        L_0x0164:
            return
        L_0x0165:
            r5 = r16
            r9 = 0
            goto L_0x004d
        L_0x016a:
            r11.close()     // Catch:{ IOException -> 0x016e }
            return
        L_0x016e:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r0 != 0) goto L_0x0179
            r1.printStackTrace()
        L_0x0179:
            return
        L_0x017a:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x01c4
        L_0x017e:
            r0 = move-exception
            r2 = r11
            goto L_0x0185
        L_0x0181:
            r0 = move-exception
            r1 = r0
            goto L_0x01c4
        L_0x0184:
            r0 = move-exception
        L_0x0185:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)     // Catch:{ all -> 0x0181 }
            if (r1 != 0) goto L_0x018e
            r0.printStackTrace()     // Catch:{ all -> 0x0181 }
        L_0x018e:
            java.lang.String r1 = "trace open fail:%s : %s"
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ all -> 0x0181 }
            java.lang.Class r4 = r0.getClass()     // Catch:{ all -> 0x0181 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0181 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0181 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0181 }
            r4.<init>()     // Catch:{ all -> 0x0181 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0181 }
            r4.append(r0)     // Catch:{ all -> 0x0181 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0181 }
            r3[r10] = r0     // Catch:{ all -> 0x0181 }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r3)     // Catch:{ all -> 0x0181 }
            if (r2 == 0) goto L_0x01c3
            r2.close()     // Catch:{ IOException -> 0x01b8 }
            return
        L_0x01b8:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r0 != 0) goto L_0x01c3
            r1.printStackTrace()
        L_0x01c3:
            return
        L_0x01c4:
            if (r2 == 0) goto L_0x01d5
            r2.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01d5
        L_0x01ca:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r2)
            if (r0 != 0) goto L_0x01d5
            r2.printStackTrace()
        L_0x01d5:
            throw r1
        L_0x01d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTraceFile(java.lang.String, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        return new java.lang.Object[]{r5, r1};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        if (r8 != null) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r1 = r7.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r1 == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        r2 = r8.length;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r4 >= r2) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        r5 = r8[r4];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r5.matcher(r1).matches() == false) goto L_0x0026;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] m442a(java.io.BufferedReader r7, java.util.regex.Pattern... r8) throws java.io.IOException {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0029
            if (r8 != 0) goto L_0x0006
            goto L_0x0029
        L_0x0006:
            java.lang.String r1 = r7.readLine()
            if (r1 == 0) goto L_0x0029
            int r2 = r8.length
            r3 = 0
            r4 = 0
        L_0x000f:
            if (r4 >= r2) goto L_0x0006
            r5 = r8[r4]
            java.util.regex.Matcher r6 = r5.matcher(r1)
            boolean r6 = r6.matches()
            if (r6 == 0) goto L_0x0026
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r7[r3] = r5
            r8 = 1
            r7[r8] = r1
            return r7
        L_0x0026:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.m442a(java.io.BufferedReader, java.util.regex.Pattern[]):java.lang.Object[]");
    }

    /* renamed from: a */
    private static String m441a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m443b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + "\n");
            }
        }
        return stringBuffer.toString();
    }
}
