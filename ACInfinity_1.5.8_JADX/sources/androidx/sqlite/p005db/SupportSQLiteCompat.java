package androidx.sqlite.p005db;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import java.io.File;
import java.util.List;

/* renamed from: androidx.sqlite.db.SupportSQLiteCompat */
public final class SupportSQLiteCompat {
    private SupportSQLiteCompat() {
    }

    /* renamed from: androidx.sqlite.db.SupportSQLiteCompat$Api16Impl */
    public static final class Api16Impl {
        public static void cancel(CancellationSignal cancellationSignal) {
            cancellationSignal.cancel();
        }

        public static CancellationSignal createCancellationSignal() {
            return new CancellationSignal();
        }

        public static boolean deleteDatabase(File file) {
            return SQLiteDatabase.deleteDatabase(file);
        }

        public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, CancellationSignal cancellationSignal, SQLiteDatabase.CursorFactory cursorFactory) {
            return sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2, cancellationSignal);
        }

        public static void setForeignKeyConstraintsEnabled(SQLiteDatabase sQLiteDatabase, boolean z) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(z);
        }

        public static void disableWriteAheadLogging(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.disableWriteAheadLogging();
        }

        public static boolean isWriteAheadLoggingEnabled(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.isWriteAheadLoggingEnabled();
        }

        public static void setWriteAheadLoggingEnabled(SQLiteOpenHelper sQLiteOpenHelper, boolean z) {
            sQLiteOpenHelper.setWriteAheadLoggingEnabled(z);
        }

        private Api16Impl() {
        }
    }

    /* renamed from: androidx.sqlite.db.SupportSQLiteCompat$Api19Impl */
    public static final class Api19Impl {
        public static Uri getNotificationUri(Cursor cursor) {
            return cursor.getNotificationUri();
        }

        public static boolean isLowRamDevice(ActivityManager activityManager) {
            return activityManager.isLowRamDevice();
        }

        private Api19Impl() {
        }
    }

    /* renamed from: androidx.sqlite.db.SupportSQLiteCompat$Api21Impl */
    public static final class Api21Impl {
        public static File getNoBackupFilesDir(Context context) {
            return context.getNoBackupFilesDir();
        }

        private Api21Impl() {
        }
    }

    /* renamed from: androidx.sqlite.db.SupportSQLiteCompat$Api23Impl */
    public static final class Api23Impl {
        public static void setExtras(Cursor cursor, Bundle bundle) {
            cursor.setExtras(bundle);
        }

        private Api23Impl() {
        }
    }

    /* renamed from: androidx.sqlite.db.SupportSQLiteCompat$Api29Impl */
    public static final class Api29Impl {
        public static void setNotificationUris(Cursor cursor, ContentResolver contentResolver, List<Uri> list) {
            cursor.setNotificationUris(contentResolver, list);
        }

        public static List<Uri> getNotificationUris(Cursor cursor) {
            return cursor.getNotificationUris();
        }

        private Api29Impl() {
        }
    }
}
