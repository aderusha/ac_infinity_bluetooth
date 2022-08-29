package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.LiveData;
import androidx.sqlite.p005db.SimpleSQLiteQuery;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class InvalidationTracker {
    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ";
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    AutoCloser mAutoCloser;
    volatile SupportSQLiteStatement mCleanupStatement;
    final RoomDatabase mDatabase;
    private volatile boolean mInitialized;
    private final InvalidationLiveDataContainer mInvalidationLiveDataContainer;
    private MultiInstanceInvalidationClient mMultiInstanceInvalidationClient;
    private final ObservedTableTracker mObservedTableTracker;
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap;
    AtomicBoolean mPendingRefresh;
    Runnable mRefreshRunnable;
    private final Object mSyncTriggersLock;
    final HashMap<String, Integer> mTableIdLookup;
    final String[] mTableNames;
    private Map<String, Set<String>> mViewTables;

    public InvalidationTracker(RoomDatabase roomDatabase, String... strArr) {
        this(roomDatabase, new HashMap(), Collections.emptyMap(), strArr);
    }

    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.mAutoCloser = null;
        this.mPendingRefresh = new AtomicBoolean(false);
        this.mInitialized = false;
        this.mObserverMap = new SafeIterableMap<>();
        this.mSyncTriggersLock = new Object();
        this.mRefreshRunnable = new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x007d, code lost:
                if (r5.this$0.mAutoCloser != null) goto L_0x007f;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
                r5.this$0.mAutoCloser.decrementCountAndScheduleClose();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
                if (r5.this$0.mAutoCloser == null) goto L_0x00a2;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a2, code lost:
                if (r1 == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a8, code lost:
                if (r1.isEmpty() != false) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:42:0x00aa, code lost:
                r0 = r5.this$0.mObserverMap;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ae, code lost:
                monitor-enter(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
                r2 = r5.this$0.mObserverMap.iterator();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bb, code lost:
                if (r2.hasNext() == false) goto L_0x00cd;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x00bd, code lost:
                ((androidx.room.InvalidationTracker.ObserverWrapper) r2.next().getValue()).notifyByTableInvalidStatus(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:49:0x00cd, code lost:
                monitor-exit(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.RoomDatabase r0 = r0.mDatabase
                    java.util.concurrent.locks.Lock r0 = r0.getCloseLock()
                    r0.lock()
                    r1 = 0
                    androidx.room.InvalidationTracker r2 = androidx.room.InvalidationTracker.this     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    boolean r2 = r2.ensureInitialization()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    if (r2 != 0) goto L_0x0025
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x0024
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    r0.decrementCountAndScheduleClose()
                L_0x0024:
                    return
                L_0x0025:
                    androidx.room.InvalidationTracker r2 = androidx.room.InvalidationTracker.this     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mPendingRefresh     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    r3 = 1
                    r4 = 0
                    boolean r2 = r2.compareAndSet(r3, r4)     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    if (r2 != 0) goto L_0x0042
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x0041
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    r0.decrementCountAndScheduleClose()
                L_0x0041:
                    return
                L_0x0042:
                    androidx.room.InvalidationTracker r2 = androidx.room.InvalidationTracker.this     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    androidx.room.RoomDatabase r2 = r2.mDatabase     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    boolean r2 = r2.inTransaction()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    if (r2 == 0) goto L_0x005d
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x005c
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    r0.decrementCountAndScheduleClose()
                L_0x005c:
                    return
                L_0x005d:
                    androidx.room.InvalidationTracker r2 = androidx.room.InvalidationTracker.this     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    androidx.room.RoomDatabase r2 = r2.mDatabase     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    androidx.sqlite.db.SupportSQLiteOpenHelper r2 = r2.getOpenHelper()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    androidx.sqlite.db.SupportSQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    r2.beginTransactionNonExclusive()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    java.util.Set r1 = r5.checkUpdatedTable()     // Catch:{ all -> 0x0087 }
                    r2.setTransactionSuccessful()     // Catch:{ all -> 0x0087 }
                    r2.endTransaction()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x00a2
                L_0x007f:
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    r0.decrementCountAndScheduleClose()
                    goto L_0x00a2
                L_0x0087:
                    r3 = move-exception
                    r2.endTransaction()     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                    throw r3     // Catch:{ IllegalStateException -> 0x0090, SQLiteException -> 0x008e }
                L_0x008c:
                    r1 = move-exception
                    goto L_0x00d3
                L_0x008e:
                    r2 = move-exception
                    goto L_0x0091
                L_0x0090:
                    r2 = move-exception
                L_0x0091:
                    java.lang.String r3 = "ROOM"
                    java.lang.String r4 = "Cannot run invalidation tracker. Is the db closed?"
                    android.util.Log.e(r3, r4, r2)     // Catch:{ all -> 0x008c }
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x00a2
                    goto L_0x007f
                L_0x00a2:
                    if (r1 == 0) goto L_0x00d2
                    boolean r0 = r1.isEmpty()
                    if (r0 != 0) goto L_0x00d2
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.arch.core.internal.SafeIterableMap<androidx.room.InvalidationTracker$Observer, androidx.room.InvalidationTracker$ObserverWrapper> r0 = r0.mObserverMap
                    monitor-enter(r0)
                    androidx.room.InvalidationTracker r2 = androidx.room.InvalidationTracker.this     // Catch:{ all -> 0x00cf }
                    androidx.arch.core.internal.SafeIterableMap<androidx.room.InvalidationTracker$Observer, androidx.room.InvalidationTracker$ObserverWrapper> r2 = r2.mObserverMap     // Catch:{ all -> 0x00cf }
                    java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00cf }
                L_0x00b7:
                    boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00cf }
                    if (r3 == 0) goto L_0x00cd
                    java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00cf }
                    java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00cf }
                    java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00cf }
                    androidx.room.InvalidationTracker$ObserverWrapper r3 = (androidx.room.InvalidationTracker.ObserverWrapper) r3     // Catch:{ all -> 0x00cf }
                    r3.notifyByTableInvalidStatus(r1)     // Catch:{ all -> 0x00cf }
                    goto L_0x00b7
                L_0x00cd:
                    monitor-exit(r0)     // Catch:{ all -> 0x00cf }
                    goto L_0x00d2
                L_0x00cf:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00cf }
                    throw r1
                L_0x00d2:
                    return
                L_0x00d3:
                    r0.unlock()
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    if (r0 == 0) goto L_0x00e3
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.AutoCloser r0 = r0.mAutoCloser
                    r0.decrementCountAndScheduleClose()
                L_0x00e3:
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker.C06741.run():void");
            }

            /* JADX INFO: finally extract failed */
            private Set<Integer> checkUpdatedTable() {
                HashSet hashSet = new HashSet();
                Cursor query = InvalidationTracker.this.mDatabase.query(new SimpleSQLiteQuery(InvalidationTracker.SELECT_UPDATED_TABLES_SQL));
                while (query.moveToNext()) {
                    try {
                        hashSet.add(Integer.valueOf(query.getInt(0)));
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                query.close();
                if (!hashSet.isEmpty()) {
                    InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                }
                return hashSet;
            }
        };
        this.mDatabase = roomDatabase;
        this.mObservedTableTracker = new ObservedTableTracker(strArr.length);
        this.mTableIdLookup = new HashMap<>();
        this.mViewTables = map2;
        this.mInvalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        int length = strArr.length;
        this.mTableNames = new String[length];
        for (int i = 0; i < length; i++) {
            String lowerCase = strArr[i].toLowerCase(Locale.US);
            this.mTableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str = map.get(strArr[i]);
            if (str != null) {
                this.mTableNames[i] = str.toLowerCase(Locale.US);
            } else {
                this.mTableNames[i] = lowerCase;
            }
        }
        for (Map.Entry next : map.entrySet()) {
            String lowerCase2 = ((String) next.getValue()).toLowerCase(Locale.US);
            if (this.mTableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = ((String) next.getKey()).toLowerCase(Locale.US);
                HashMap<String, Integer> hashMap = this.mTableIdLookup;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAutoCloser(AutoCloser autoCloser) {
        this.mAutoCloser = autoCloser;
        autoCloser.setAutoCloseCallback(new InvalidationTracker$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    public void internalInit(SupportSQLiteDatabase supportSQLiteDatabase) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
            supportSQLiteDatabase.execSQL(CREATE_TRACKING_TABLE_SQL);
            syncTriggers(supportSQLiteDatabase);
            this.mCleanupStatement = supportSQLiteDatabase.compileStatement(RESET_UPDATED_TABLES_SQL);
            this.mInitialized = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void onAutoCloseCallback() {
        synchronized (this) {
            this.mInitialized = false;
            this.mObservedTableTracker.resetTriggerState();
        }
    }

    /* access modifiers changed from: package-private */
    public void startMultiInstanceInvalidation(Context context, String str, Intent intent) {
        this.mMultiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, intent, this, this.mDatabase.getQueryExecutor());
    }

    /* access modifiers changed from: package-private */
    public void stopMultiInstanceInvalidation() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.mMultiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null) {
            multiInstanceInvalidationClient.stop();
            this.mMultiInstanceInvalidationClient = null;
        }
    }

    private static void appendTriggerName(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    private void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String appendTriggerName : TRIGGERS) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(sb, str, appendTriggerName);
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    private void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append(UPDATE_TABLE_NAME);
            sb.append(" SET ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append(TABLE_ID_COLUMN_NAME);
            sb.append(" = ");
            sb.append(i);
            sb.append(" AND ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 0");
            sb.append("; END");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public void addObserver(Observer observer) {
        ObserverWrapper putIfAbsent;
        String[] resolveViews = resolveViews(observer.mTables);
        int[] iArr = new int[resolveViews.length];
        int length = resolveViews.length;
        int i = 0;
        while (i < length) {
            Integer num = this.mTableIdLookup.get(resolveViews[i].toLowerCase(Locale.US));
            if (num != null) {
                iArr[i] = num.intValue();
                i++;
            } else {
                throw new IllegalArgumentException("There is no table with name " + resolveViews[i]);
            }
        }
        ObserverWrapper observerWrapper = new ObserverWrapper(observer, iArr, resolveViews);
        synchronized (this.mObserverMap) {
            putIfAbsent = this.mObserverMap.putIfAbsent(observer, observerWrapper);
        }
        if (putIfAbsent == null && this.mObservedTableTracker.onAdded(iArr)) {
            syncTriggers();
        }
    }

    private String[] validateAndResolveTableNames(String[] strArr) {
        String[] resolveViews = resolveViews(strArr);
        int length = resolveViews.length;
        int i = 0;
        while (i < length) {
            String str = resolveViews[i];
            if (this.mTableIdLookup.containsKey(str.toLowerCase(Locale.US))) {
                i++;
            } else {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
        }
        return resolveViews;
    }

    private String[] resolveViews(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.mViewTables.containsKey(lowerCase)) {
                hashSet.addAll(this.mViewTables.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private static void beginTransactionInternal(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (Build.VERSION.SDK_INT < 16 || !supportSQLiteDatabase.isWriteAheadLoggingEnabled()) {
            supportSQLiteDatabase.beginTransaction();
        } else {
            supportSQLiteDatabase.beginTransactionNonExclusive();
        }
    }

    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    public void removeObserver(Observer observer) {
        ObserverWrapper remove;
        synchronized (this.mObserverMap) {
            remove = this.mObserverMap.remove(observer);
        }
        if (remove != null && this.mObservedTableTracker.onRemoved(remove.mTableIds)) {
            syncTriggers();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean ensureInitialization() {
        if (!this.mDatabase.isOpen()) {
            return false;
        }
        if (!this.mInitialized) {
            this.mDatabase.getOpenHelper().getWritableDatabase();
        }
        if (this.mInitialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser = this.mAutoCloser;
            if (autoCloser != null) {
                autoCloser.incrementCountAndEnsureDbIsOpen();
            }
            this.mDatabase.getQueryExecutor().execute(this.mRefreshRunnable);
        }
    }

    public void refreshVersionsSync() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser != null) {
            autoCloser.incrementCountAndEnsureDbIsOpen();
        }
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    public void notifyObserversByTableNames(String... strArr) {
        synchronized (this.mObserverMap) {
            Iterator<Map.Entry<Observer, ObserverWrapper>> it = this.mObserverMap.iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                if (!((Observer) next.getKey()).isRemote()) {
                    ((ObserverWrapper) next.getValue()).notifyByTableNames(strArr);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void syncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (!supportSQLiteDatabase.inTransaction()) {
            try {
                Lock closeLock = this.mDatabase.getCloseLock();
                closeLock.lock();
                try {
                    synchronized (this.mSyncTriggersLock) {
                        int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                        if (tablesToSync != null) {
                            int length = tablesToSync.length;
                            beginTransactionInternal(supportSQLiteDatabase);
                            int i = 0;
                            while (i < length) {
                                try {
                                    int i2 = tablesToSync[i];
                                    if (i2 == 1) {
                                        startTrackingTable(supportSQLiteDatabase, i);
                                    } else if (i2 == 2) {
                                        stopTrackingTable(supportSQLiteDatabase, i);
                                    }
                                    i++;
                                } catch (Throwable th) {
                                    supportSQLiteDatabase.endTransaction();
                                    throw th;
                                }
                            }
                            supportSQLiteDatabase.setTransactionSuccessful();
                            supportSQLiteDatabase.endTransaction();
                            closeLock.unlock();
                        }
                    }
                } finally {
                    closeLock.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void syncTriggers() {
        if (this.mDatabase.isOpen()) {
            syncTriggers(this.mDatabase.getOpenHelper().getWritableDatabase());
        }
    }

    @Deprecated
    public <T> LiveData<T> createLiveData(String[] strArr, Callable<T> callable) {
        return createLiveData(strArr, false, callable);
    }

    public <T> LiveData<T> createLiveData(String[] strArr, boolean z, Callable<T> callable) {
        return this.mInvalidationLiveDataContainer.create(validateAndResolveTableNames(strArr), z, callable);
    }

    static class ObserverWrapper {
        final Observer mObserver;
        private final Set<String> mSingleTableSet;
        final int[] mTableIds;
        private final String[] mTableNames;

        ObserverWrapper(Observer observer, int[] iArr, String[] strArr) {
            this.mObserver = observer;
            this.mTableIds = iArr;
            this.mTableNames = strArr;
            if (iArr.length == 1) {
                HashSet hashSet = new HashSet();
                hashSet.add(strArr[0]);
                this.mSingleTableSet = Collections.unmodifiableSet(hashSet);
                return;
            }
            this.mSingleTableSet = null;
        }

        /* access modifiers changed from: package-private */
        public void notifyByTableInvalidStatus(Set<Integer> set) {
            int length = this.mTableIds.length;
            Set set2 = null;
            for (int i = 0; i < length; i++) {
                if (set.contains(Integer.valueOf(this.mTableIds[i]))) {
                    if (length == 1) {
                        set2 = this.mSingleTableSet;
                    } else {
                        if (set2 == null) {
                            set2 = new HashSet(length);
                        }
                        set2.add(this.mTableNames[i]);
                    }
                }
            }
            if (set2 != null) {
                this.mObserver.onInvalidated(set2);
            }
        }

        /* access modifiers changed from: package-private */
        public void notifyByTableNames(String[] strArr) {
            Set<String> set = null;
            if (this.mTableNames.length == 1) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (strArr[i].equalsIgnoreCase(this.mTableNames[0])) {
                        set = this.mSingleTableSet;
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.mTableNames;
                    int length2 = strArr2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        String str2 = strArr2[i2];
                        if (str2.equalsIgnoreCase(str)) {
                            hashSet.add(str2);
                            break;
                        }
                        i2++;
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.mObserver.onInvalidated(set);
            }
        }
    }

    public static abstract class Observer {
        final String[] mTables;

        /* access modifiers changed from: package-private */
        public boolean isRemote() {
            return false;
        }

        public abstract void onInvalidated(Set<String> set);

        protected Observer(String str, String... strArr) {
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
            this.mTables = strArr2;
            strArr2[strArr.length] = str;
        }

        public Observer(String[] strArr) {
            this.mTables = (String[]) Arrays.copyOf(strArr, strArr.length);
        }
    }

    static class ObservedTableTracker {
        static final int ADD = 1;
        static final int NO_OP = 0;
        static final int REMOVE = 2;
        boolean mNeedsSync;
        final long[] mTableObservers;
        final int[] mTriggerStateChanges;
        final boolean[] mTriggerStates;

        ObservedTableTracker(int i) {
            long[] jArr = new long[i];
            this.mTableObservers = jArr;
            boolean[] zArr = new boolean[i];
            this.mTriggerStates = zArr;
            this.mTriggerStateChanges = new int[i];
            Arrays.fill(jArr, 0);
            Arrays.fill(zArr, false);
        }

        /* access modifiers changed from: package-private */
        public boolean onAdded(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.mTableObservers;
                    long j = jArr[i];
                    jArr[i] = 1 + j;
                    if (j == 0) {
                        this.mNeedsSync = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean onRemoved(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.mTableObservers;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        this.mNeedsSync = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public void resetTriggerState() {
            synchronized (this) {
                Arrays.fill(this.mTriggerStates, false);
                this.mNeedsSync = true;
            }
        }

        /* access modifiers changed from: package-private */
        public int[] getTablesToSync() {
            synchronized (this) {
                if (!this.mNeedsSync) {
                    return null;
                }
                int length = this.mTableObservers.length;
                for (int i = 0; i < length; i++) {
                    int i2 = 1;
                    boolean z = this.mTableObservers[i] > 0;
                    boolean[] zArr = this.mTriggerStates;
                    if (z != zArr[i]) {
                        int[] iArr = this.mTriggerStateChanges;
                        if (!z) {
                            i2 = 2;
                        }
                        iArr[i] = i2;
                    } else {
                        this.mTriggerStateChanges[i] = 0;
                    }
                    zArr[i] = z;
                }
                this.mNeedsSync = false;
                int[] iArr2 = (int[]) this.mTriggerStateChanges.clone();
                return iArr2;
            }
        }
    }

    static class WeakObserver extends Observer {
        final WeakReference<Observer> mDelegateRef;
        final InvalidationTracker mTracker;

        WeakObserver(InvalidationTracker invalidationTracker, Observer observer) {
            super(observer.mTables);
            this.mTracker = invalidationTracker;
            this.mDelegateRef = new WeakReference<>(observer);
        }

        public void onInvalidated(Set<String> set) {
            Observer observer = (Observer) this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(set);
            }
        }
    }
}
