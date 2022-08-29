package com.tencent.bugly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C3749y;

/* renamed from: com.tencent.bugly.a */
/* compiled from: BUGLY */
public abstract class C3611a {

    /* renamed from: id */
    public int f389id;
    public String moduleName;
    public String version;
    public String versionKey;

    public abstract String[] getTables();

    public abstract void init(Context context, boolean z, BuglyStrategy buglyStrategy);

    public void onDbCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
    }

    public void onDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() != null) {
                for (String str : getTables()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
                onDbCreate(sQLiteDatabase);
            }
        } catch (Throwable th) {
            if (!C3749y.m938b(th)) {
                th.printStackTrace();
            }
        }
    }

    public void onDbDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() != null) {
                for (String str : getTables()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
                onDbCreate(sQLiteDatabase);
            }
        } catch (Throwable th) {
            if (!C3749y.m938b(th)) {
                th.printStackTrace();
            }
        }
    }
}
