package androidx.room;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

final class AutoCloser {
    final long mAutoCloseTimeoutInMs;
    final Runnable mAutoCloser = new Runnable() {
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                androidx.room.AutoCloser r0 = androidx.room.AutoCloser.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                long r1 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x0058 }
                androidx.room.AutoCloser r3 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                long r3 = r3.mLastDecrementRefCountTimeStamp     // Catch:{ all -> 0x0058 }
                long r1 = r1 - r3
                androidx.room.AutoCloser r3 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                long r3 = r3.mAutoCloseTimeoutInMs     // Catch:{ all -> 0x0058 }
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r5 >= 0) goto L_0x0018
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                return
            L_0x0018:
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                int r1 = r1.mRefCount     // Catch:{ all -> 0x0058 }
                if (r1 == 0) goto L_0x0020
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                return
            L_0x0020:
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                java.lang.Runnable r1 = r1.mOnAutoCloseCallback     // Catch:{ all -> 0x0058 }
                if (r1 == 0) goto L_0x0050
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                java.lang.Runnable r1 = r1.mOnAutoCloseCallback     // Catch:{ all -> 0x0058 }
                r1.run()     // Catch:{ all -> 0x0058 }
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.mDelegateDatabase     // Catch:{ all -> 0x0058 }
                if (r1 == 0) goto L_0x004e
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.mDelegateDatabase     // Catch:{ all -> 0x0058 }
                boolean r1 = r1.isOpen()     // Catch:{ all -> 0x0058 }
                if (r1 == 0) goto L_0x004e
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ IOException -> 0x0045 }
                androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.mDelegateDatabase     // Catch:{ IOException -> 0x0045 }
                r1.close()     // Catch:{ IOException -> 0x0045 }
                goto L_0x0049
            L_0x0045:
                r1 = move-exception
                androidx.room.util.SneakyThrow.reThrow(r1)     // Catch:{ all -> 0x0058 }
            L_0x0049:
                androidx.room.AutoCloser r1 = androidx.room.AutoCloser.this     // Catch:{ all -> 0x0058 }
                r2 = 0
                r1.mDelegateDatabase = r2     // Catch:{ all -> 0x0058 }
            L_0x004e:
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                return
            L_0x0050:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0058 }
                java.lang.String r2 = "mOnAutoCloseCallback is null but it should have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568"
                r1.<init>(r2)     // Catch:{ all -> 0x0058 }
                throw r1     // Catch:{ all -> 0x0058 }
            L_0x0058:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.AutoCloser.C06432.run():void");
        }
    };
    SupportSQLiteDatabase mDelegateDatabase;
    private SupportSQLiteOpenHelper mDelegateOpenHelper = null;
    private final Runnable mExecuteAutoCloser = new Runnable() {
        public void run() {
            AutoCloser.this.mExecutor.execute(AutoCloser.this.mAutoCloser);
        }
    };
    final Executor mExecutor;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    long mLastDecrementRefCountTimeStamp = SystemClock.uptimeMillis();
    final Object mLock = new Object();
    private boolean mManuallyClosed = false;
    Runnable mOnAutoCloseCallback = null;
    int mRefCount = 0;

    AutoCloser(long j, TimeUnit timeUnit, Executor executor) {
        this.mAutoCloseTimeoutInMs = timeUnit.toMillis(j);
        this.mExecutor = executor;
    }

    public void init(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (this.mDelegateOpenHelper != null) {
            Log.e("ROOM", "AutoCloser initialized multiple times. Please file a bug against room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");
        } else {
            this.mDelegateOpenHelper = supportSQLiteOpenHelper;
        }
    }

    public <V> V executeRefCountingFunction(Function<SupportSQLiteDatabase, V> function) {
        try {
            return function.apply(incrementCountAndEnsureDbIsOpen());
        } finally {
            decrementCountAndScheduleClose();
        }
    }

    public SupportSQLiteDatabase incrementCountAndEnsureDbIsOpen() {
        synchronized (this.mLock) {
            this.mHandler.removeCallbacks(this.mExecuteAutoCloser);
            this.mRefCount++;
            if (!this.mManuallyClosed) {
                SupportSQLiteDatabase supportSQLiteDatabase = this.mDelegateDatabase;
                if (supportSQLiteDatabase == null || !supportSQLiteDatabase.isOpen()) {
                    SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.mDelegateOpenHelper;
                    if (supportSQLiteOpenHelper != null) {
                        SupportSQLiteDatabase writableDatabase = supportSQLiteOpenHelper.getWritableDatabase();
                        this.mDelegateDatabase = writableDatabase;
                        return writableDatabase;
                    }
                    throw new IllegalStateException("AutoCloser has not been initialized. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");
                }
                SupportSQLiteDatabase supportSQLiteDatabase2 = this.mDelegateDatabase;
                return supportSQLiteDatabase2;
            }
            throw new IllegalStateException("Attempting to open already closed database.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decrementCountAndScheduleClose() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            int r1 = r5.mRefCount     // Catch:{ all -> 0x0026 }
            if (r1 <= 0) goto L_0x001e
            int r1 = r1 + -1
            r5.mRefCount = r1     // Catch:{ all -> 0x0026 }
            if (r1 != 0) goto L_0x001c
            androidx.sqlite.db.SupportSQLiteDatabase r1 = r5.mDelegateDatabase     // Catch:{ all -> 0x0026 }
            if (r1 != 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return
        L_0x0013:
            android.os.Handler r1 = r5.mHandler     // Catch:{ all -> 0x0026 }
            java.lang.Runnable r2 = r5.mExecuteAutoCloser     // Catch:{ all -> 0x0026 }
            long r3 = r5.mAutoCloseTimeoutInMs     // Catch:{ all -> 0x0026 }
            r1.postDelayed(r2, r3)     // Catch:{ all -> 0x0026 }
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return
        L_0x001e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = "ref count is 0 or lower but we're supposed to decrement"
            r1.<init>(r2)     // Catch:{ all -> 0x0026 }
            throw r1     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.AutoCloser.decrementCountAndScheduleClose():void");
    }

    public SupportSQLiteDatabase getDelegateDatabase() {
        SupportSQLiteDatabase supportSQLiteDatabase;
        synchronized (this.mLock) {
            supportSQLiteDatabase = this.mDelegateDatabase;
        }
        return supportSQLiteDatabase;
    }

    public void closeDatabaseIfOpen() throws IOException {
        synchronized (this.mLock) {
            this.mManuallyClosed = true;
            SupportSQLiteDatabase supportSQLiteDatabase = this.mDelegateDatabase;
            if (supportSQLiteDatabase != null) {
                supportSQLiteDatabase.close();
            }
            this.mDelegateDatabase = null;
        }
    }

    public boolean isActive() {
        return !this.mManuallyClosed;
    }

    public int getRefCountForTest() {
        int i;
        synchronized (this.mLock) {
            i = this.mRefCount;
        }
        return i;
    }

    public void setAutoCloseCallback(Runnable runnable) {
        this.mOnAutoCloseCallback = runnable;
    }
}
