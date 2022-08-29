package androidx.room.migration;

import androidx.sqlite.p005db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {

    /* renamed from: androidx.room.migration.AutoMigrationSpec$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onPostMigrate(AutoMigrationSpec autoMigrationSpec, SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
