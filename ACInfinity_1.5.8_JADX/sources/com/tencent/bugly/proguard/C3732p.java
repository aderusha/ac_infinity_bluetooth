package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.C3611a;
import com.tencent.bugly.crashreport.common.info.C3626a;
import java.util.List;

/* renamed from: com.tencent.bugly.proguard.p */
/* compiled from: BUGLY */
public final class C3732p extends SQLiteOpenHelper {

    /* renamed from: a */
    public static String f994a = "bugly_db";

    /* renamed from: b */
    private static int f995b = 16;

    /* renamed from: c */
    private Context f996c;

    /* renamed from: d */
    private List<C3611a> f997d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3732p(Context context, List<C3611a> list) {
        super(context, f994a + "_", (SQLiteDatabase.CursorFactory) null, f995b);
        C3626a.m337a(context).getClass();
        this.f996c = context;
        this.f997d = list;
    }

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_ui");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _ut");
            sb.append(" int");
            sb.append(" , _tp");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" , _pc");
            sb.append(" text");
            sb.append(" ) ");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_lr");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tp");
            sb.append(" int");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _pc");
            sb.append(" text");
            sb.append(" , _th");
            sb.append(" text");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" ) ");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_pf");
            sb.append(" ( _id");
            sb.append(" integer");
            sb.append(" , _tp");
            sb.append(" text");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(",primary key(_id");
            sb.append(",_tp");
            sb.append(" )) ");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_cr");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _s1");
            sb.append(" text");
            sb.append(" , _up");
            sb.append(" int");
            sb.append(" , _me");
            sb.append(" int");
            sb.append(" , _uc");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" ) ");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS dl_1002");
            sb.append(" (_id");
            sb.append(" integer primary key autoincrement, _dUrl");
            sb.append(" varchar(100), _sFile");
            sb.append(" varchar(100), _sLen");
            sb.append(" INTEGER, _tLen");
            sb.append(" INTEGER, _MD5");
            sb.append(" varchar(100), _DLTIME");
            sb.append(" INTEGER)");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append("CREATE TABLE IF NOT EXISTS ge_1002");
            sb.append(" (_id");
            sb.append(" integer primary key autoincrement, _time");
            sb.append(" INTEGER, _datas");
            sb.append(" blob)");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS st_1002");
            sb.append(" ( _id");
            sb.append(" integer");
            sb.append(" , _tp");
            sb.append(" text");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(",primary key(_id");
            sb.append(",_tp");
            sb.append(" )) ");
            C3749y.m939c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_sla");
            sb.append(" ( _id");
            sb.append(" TEXT NOT NULL , _tm");
            sb.append(" INTEGER NOT NULL , _dt");
            sb.append(" TEXT NOT NULL , PRIMARY KEY(");
            sb.append("_id)");
            sb.append(" ) ");
            String sb2 = sb.toString();
            C3749y.m939c(sb2, new Object[0]);
            sQLiteDatabase.execSQL(sb2, new String[0]);
        } catch (Throwable th) {
            if (!C3749y.m938b(th)) {
                th.printStackTrace();
            }
        }
        List<C3611a> list = this.f997d;
        if (list != null) {
            for (C3611a onDbCreate : list) {
                try {
                    onDbCreate.onDbCreate(sQLiteDatabase);
                } catch (Throwable th2) {
                    if (!C3749y.m938b(th2)) {
                        th2.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m865a(SQLiteDatabase sQLiteDatabase) {
        try {
            String[] strArr = {"t_lr", "t_ui", "t_pf"};
            for (int i = 0; i < 3; i++) {
                String str = strArr[i];
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str, new String[0]);
            }
        } catch (Throwable th) {
            if (!C3749y.m938b(th)) {
                th.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onUpgrade(android.database.sqlite.SQLiteDatabase r6, int r7, int r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "[Database] Upgrade %d to %d , drop tables!"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0061 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0061 }
            r3 = 0
            r1[r3] = r2     // Catch:{ all -> 0x0061 }
            r2 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0061 }
            r1[r2] = r4     // Catch:{ all -> 0x0061 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x0061 }
            java.util.List<com.tencent.bugly.a> r0 = r5.f997d     // Catch:{ all -> 0x0061 }
            if (r0 == 0) goto L_0x003a
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0061 }
        L_0x001f:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0061 }
            if (r1 == 0) goto L_0x003a
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0061 }
            com.tencent.bugly.a r1 = (com.tencent.bugly.C3611a) r1     // Catch:{ all -> 0x0061 }
            r1.onDbUpgrade(r6, r7, r8)     // Catch:{ all -> 0x002f }
            goto L_0x001f
        L_0x002f:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m938b(r1)     // Catch:{ all -> 0x0061 }
            if (r2 != 0) goto L_0x001f
            r1.printStackTrace()     // Catch:{ all -> 0x0061 }
            goto L_0x001f
        L_0x003a:
            boolean r7 = r5.m865a(r6)     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0045
            r5.onCreate(r6)     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return
        L_0x0045:
            java.lang.String r6 = "[Database] Failed to drop, delete db."
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x0061 }
            com.tencent.bugly.proguard.C3749y.m940d(r6, r7)     // Catch:{ all -> 0x0061 }
            android.content.Context r6 = r5.f996c     // Catch:{ all -> 0x0061 }
            java.lang.String r7 = f994a     // Catch:{ all -> 0x0061 }
            java.io.File r6 = r6.getDatabasePath(r7)     // Catch:{ all -> 0x0061 }
            if (r6 == 0) goto L_0x005f
            boolean r7 = r6.canWrite()     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x005f
            r6.delete()     // Catch:{ all -> 0x0061 }
        L_0x005f:
            monitor-exit(r5)
            return
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3732p.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onDowngrade(android.database.sqlite.SQLiteDatabase r6, int r7, int r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = com.tencent.bugly.crashreport.common.info.C3627b.m394c()     // Catch:{ all -> 0x0069 }
            r1 = 11
            if (r0 < r1) goto L_0x0067
            java.lang.String r0 = "[Database] Downgrade %d to %d drop tables."
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0069 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0069 }
            r3 = 0
            r1[r3] = r2     // Catch:{ all -> 0x0069 }
            r2 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0069 }
            r1[r2] = r4     // Catch:{ all -> 0x0069 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x0069 }
            java.util.List<com.tencent.bugly.a> r0 = r5.f997d     // Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0042
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0069 }
        L_0x0027:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x0042
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0069 }
            com.tencent.bugly.a r1 = (com.tencent.bugly.C3611a) r1     // Catch:{ all -> 0x0069 }
            r1.onDbDowngrade(r6, r7, r8)     // Catch:{ all -> 0x0037 }
            goto L_0x0027
        L_0x0037:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m938b(r1)     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0027
            r1.printStackTrace()     // Catch:{ all -> 0x0069 }
            goto L_0x0027
        L_0x0042:
            boolean r7 = r5.m865a(r6)     // Catch:{ all -> 0x0069 }
            if (r7 == 0) goto L_0x004d
            r5.onCreate(r6)     // Catch:{ all -> 0x0069 }
            monitor-exit(r5)
            return
        L_0x004d:
            java.lang.String r6 = "[Database] Failed to drop, delete db."
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x0069 }
            com.tencent.bugly.proguard.C3749y.m940d(r6, r7)     // Catch:{ all -> 0x0069 }
            android.content.Context r6 = r5.f996c     // Catch:{ all -> 0x0069 }
            java.lang.String r7 = f994a     // Catch:{ all -> 0x0069 }
            java.io.File r6 = r6.getDatabasePath(r7)     // Catch:{ all -> 0x0069 }
            if (r6 == 0) goto L_0x0067
            boolean r7 = r6.canWrite()     // Catch:{ all -> 0x0069 }
            if (r7 == 0) goto L_0x0067
            r6.delete()     // Catch:{ all -> 0x0069 }
        L_0x0067:
            monitor-exit(r5)
            return
        L_0x0069:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3732p.onDowngrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getReadableDatabase();
            } catch (Throwable unused) {
                C3749y.m940d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                if (i == 5) {
                    C3749y.m941e("[Database] Failed to get db.", new Object[0]);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sQLiteDatabase;
    }

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (Throwable unused) {
                C3749y.m940d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                if (i == 5) {
                    C3749y.m941e("[Database] Failed to get db.", new Object[0]);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (sQLiteDatabase == null) {
            C3749y.m940d("[Database] db error delay error record 1min.", new Object[0]);
        }
        return sQLiteDatabase;
    }
}
