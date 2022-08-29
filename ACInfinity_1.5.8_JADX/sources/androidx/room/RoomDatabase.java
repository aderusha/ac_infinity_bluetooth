package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.SneakyThrow;
import androidx.sqlite.p005db.SimpleSQLiteQuery;
import androidx.sqlite.p005db.SupportSQLiteCompat;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import androidx.sqlite.p005db.SupportSQLiteQuery;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import androidx.sqlite.p005db.framework.FrameworkSQLiteOpenHelperFactory;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    private AutoCloser mAutoCloser;
    protected Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> mAutoMigrationSpecs = new HashMap();
    private final Map<String, Object> mBackingFieldMap = Collections.synchronizedMap(new HashMap());
    @Deprecated
    protected List<Callback> mCallbacks;
    private final ReentrantReadWriteLock mCloseLock = new ReentrantReadWriteLock();
    @Deprecated
    protected volatile SupportSQLiteDatabase mDatabase;
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();
    private SupportSQLiteOpenHelper mOpenHelper;
    private Executor mQueryExecutor;
    private final ThreadLocal<Integer> mSuspendingTransactionId = new ThreadLocal<>();
    private Executor mTransactionExecutor;
    private final Map<Class<?>, Object> mTypeConverters = new HashMap();
    boolean mWriteAheadLoggingEnabled;

    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onDestructiveMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    public static abstract class PrepackagedDatabaseCallback {
        public void onOpenPrepackagedDatabase(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    public interface QueryCallback {
        void onQuery(String str, List<Object> list);
    }

    public abstract void clearAllTables();

    /* access modifiers changed from: protected */
    public abstract InvalidationTracker createInvalidationTracker();

    /* access modifiers changed from: protected */
    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    /* access modifiers changed from: package-private */
    public Lock getCloseLock() {
        return this.mCloseLock.readLock();
    }

    /* access modifiers changed from: package-private */
    public ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.mSuspendingTransactionId;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getBackingFieldMap() {
        return this.mBackingFieldMap;
    }

    public <T> T getTypeConverter(Class<T> cls) {
        return this.mTypeConverters.get(cls);
    }

    public void init(DatabaseConfiguration databaseConfiguration) {
        boolean z;
        this.mOpenHelper = createOpenHelper(databaseConfiguration);
        Set<Class<? extends AutoMigrationSpec>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator<Class<? extends AutoMigrationSpec>> it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            int i = -1;
            if (it.hasNext()) {
                Class next = it.next();
                int size = databaseConfiguration.autoMigrationSpecs.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    } else if (next.isAssignableFrom(databaseConfiguration.autoMigrationSpecs.get(size).getClass())) {
                        bitSet.set(size);
                        i = size;
                        break;
                    } else {
                        size--;
                    }
                }
                if (i >= 0) {
                    this.mAutoMigrationSpecs.put(next, databaseConfiguration.autoMigrationSpecs.get(i));
                } else {
                    throw new IllegalArgumentException("A required auto migration spec (" + next.getCanonicalName() + ") is missing in the database configuration.");
                }
            } else {
                int size2 = databaseConfiguration.autoMigrationSpecs.size() - 1;
                while (size2 >= 0) {
                    if (bitSet.get(size2)) {
                        size2--;
                    } else {
                        throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                    }
                }
                Iterator<Migration> it2 = getAutoMigrations(this.mAutoMigrationSpecs).iterator();
                while (true) {
                    z = false;
                    if (!it2.hasNext()) {
                        break;
                    }
                    Migration next2 = it2.next();
                    if (!databaseConfiguration.migrationContainer.getMigrations().containsKey(Integer.valueOf(next2.startVersion))) {
                        databaseConfiguration.migrationContainer.addMigrations(next2);
                    }
                }
                SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) unwrapOpenHelper(SQLiteCopyOpenHelper.class, this.mOpenHelper);
                if (sQLiteCopyOpenHelper != null) {
                    sQLiteCopyOpenHelper.setDatabaseConfiguration(databaseConfiguration);
                }
                AutoClosingRoomOpenHelper autoClosingRoomOpenHelper = (AutoClosingRoomOpenHelper) unwrapOpenHelper(AutoClosingRoomOpenHelper.class, this.mOpenHelper);
                if (autoClosingRoomOpenHelper != null) {
                    AutoCloser autoCloser = autoClosingRoomOpenHelper.getAutoCloser();
                    this.mAutoCloser = autoCloser;
                    this.mInvalidationTracker.setAutoCloser(autoCloser);
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    if (databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING) {
                        z = true;
                    }
                    this.mOpenHelper.setWriteAheadLoggingEnabled(z);
                }
                this.mCallbacks = databaseConfiguration.callbacks;
                this.mQueryExecutor = databaseConfiguration.queryExecutor;
                this.mTransactionExecutor = new TransactionExecutor(databaseConfiguration.transactionExecutor);
                this.mAllowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
                this.mWriteAheadLoggingEnabled = z;
                if (databaseConfiguration.multiInstanceInvalidationServiceIntent != null) {
                    this.mInvalidationTracker.startMultiInstanceInvalidation(databaseConfiguration.context, databaseConfiguration.name, databaseConfiguration.multiInstanceInvalidationServiceIntent);
                }
                Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                for (Map.Entry next3 : requiredTypeConverters.entrySet()) {
                    Class cls = (Class) next3.getKey();
                    Iterator it3 = ((List) next3.getValue()).iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Class cls2 = (Class) it3.next();
                            int size3 = databaseConfiguration.typeConverters.size() - 1;
                            while (true) {
                                if (size3 < 0) {
                                    size3 = -1;
                                    break;
                                } else if (cls2.isAssignableFrom(databaseConfiguration.typeConverters.get(size3).getClass())) {
                                    bitSet2.set(size3);
                                    break;
                                } else {
                                    size3--;
                                }
                            }
                            if (size3 >= 0) {
                                this.mTypeConverters.put(cls2, databaseConfiguration.typeConverters.get(size3));
                            } else {
                                throw new IllegalArgumentException("A required type converter (" + cls2 + ") for " + cls.getCanonicalName() + " is missing in the database configuration.");
                            }
                        }
                    }
                }
                int size4 = databaseConfiguration.typeConverters.size() - 1;
                while (size4 >= 0) {
                    if (bitSet2.get(size4)) {
                        size4--;
                    } else {
                        throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                    }
                }
                return;
            }
        }
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Collections.emptyList();
    }

    private <T> T unwrapOpenHelper(Class<T> cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        return this.mOpenHelper;
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return Collections.emptyMap();
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return Collections.emptySet();
    }

    public boolean isOpen() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser != null) {
            return autoCloser.isActive();
        }
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        return supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen();
    }

    public void close() {
        if (isOpen()) {
            ReentrantReadWriteLock.WriteLock writeLock = this.mCloseLock.writeLock();
            writeLock.lock();
            try {
                this.mInvalidationTracker.stopMultiInstanceInvalidation();
                this.mOpenHelper.close();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.mSuspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public Cursor query(String str, Object[] objArr) {
        return this.mOpenHelper.getWritableDatabase().query((SupportSQLiteQuery) new SimpleSQLiteQuery(str, objArr));
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        return query(supportSQLiteQuery, (CancellationSignal) null);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal == null || Build.VERSION.SDK_INT < 16) {
            return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery);
        }
        return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery, cancellationSignal);
    }

    public SupportSQLiteStatement compileStatement(String str) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return this.mOpenHelper.getWritableDatabase().compileStatement(str);
    }

    @Deprecated
    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser == null) {
            internalBeginTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new RoomDatabase$$ExternalSyntheticLambda0(this));
        }
    }

    public /* synthetic */ Object lambda$beginTransaction$0$RoomDatabase(SupportSQLiteDatabase supportSQLiteDatabase) {
        internalBeginTransaction();
        return null;
    }

    private void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(writableDatabase);
        if (Build.VERSION.SDK_INT < 16 || !writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransaction();
        } else {
            writableDatabase.beginTransactionNonExclusive();
        }
    }

    @Deprecated
    public void endTransaction() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser == null) {
            internalEndTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new RoomDatabase$$ExternalSyntheticLambda1(this));
        }
    }

    public /* synthetic */ Object lambda$endTransaction$1$RoomDatabase(SupportSQLiteDatabase supportSQLiteDatabase) {
        internalEndTransaction();
        return null;
    }

    private void internalEndTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            this.mInvalidationTracker.refreshVersionsAsync();
        }
    }

    public Executor getQueryExecutor() {
        return this.mQueryExecutor;
    }

    public Executor getTransactionExecutor() {
        return this.mTransactionExecutor;
    }

    @Deprecated
    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(Runnable runnable) {
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(Callable<V> callable) {
        beginTransaction();
        try {
            V call = callable.call();
            setTransactionSuccessful();
            endTransaction();
            return call;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            SneakyThrow.reThrow(e2);
            endTransaction();
            return null;
        } catch (Throwable th) {
            endTransaction();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void internalInitInvalidationTracker(SupportSQLiteDatabase supportSQLiteDatabase) {
        this.mInvalidationTracker.internalInit(supportSQLiteDatabase);
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        /* access modifiers changed from: package-private */
        public JournalMode resolve(Context context) {
            ActivityManager activityManager;
            if (this != AUTOMATIC) {
                return this;
            }
            if (Build.VERSION.SDK_INT < 16 || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || isLowRamDevice(activityManager)) {
                return TRUNCATE;
            }
            return WRITE_AHEAD_LOGGING;
        }

        private static boolean isLowRamDevice(ActivityManager activityManager) {
            if (Build.VERSION.SDK_INT >= 19) {
                return SupportSQLiteCompat.Api19Impl.isLowRamDevice(activityManager);
            }
            return false;
        }
    }

    public static class Builder<T extends RoomDatabase> {
        private boolean mAllowDestructiveMigrationOnDowngrade;
        private boolean mAllowMainThreadQueries;
        private TimeUnit mAutoCloseTimeUnit;
        private long mAutoCloseTimeout = -1;
        private List<AutoMigrationSpec> mAutoMigrationSpecs;
        private ArrayList<Callback> mCallbacks;
        private final Context mContext;
        private String mCopyFromAssetPath;
        private File mCopyFromFile;
        private Callable<InputStream> mCopyFromInputStream;
        private final Class<T> mDatabaseClass;
        private SupportSQLiteOpenHelper.Factory mFactory;
        private JournalMode mJournalMode;
        private final MigrationContainer mMigrationContainer;
        private Set<Integer> mMigrationStartAndEndVersions;
        private Set<Integer> mMigrationsNotRequiredFrom;
        private Intent mMultiInstanceInvalidationIntent;
        private final String mName;
        private PrepackagedDatabaseCallback mPrepackagedDatabaseCallback;
        private QueryCallback mQueryCallback;
        private Executor mQueryCallbackExecutor;
        private Executor mQueryExecutor;
        private boolean mRequireMigration;
        private Executor mTransactionExecutor;
        private List<Object> mTypeConverters;

        Builder(Context context, Class<T> cls, String str) {
            this.mContext = context;
            this.mDatabaseClass = cls;
            this.mName = str;
            this.mJournalMode = JournalMode.AUTOMATIC;
            this.mRequireMigration = true;
            this.mMigrationContainer = new MigrationContainer();
        }

        public Builder<T> createFromAsset(String str) {
            this.mCopyFromAssetPath = str;
            return this;
        }

        public Builder<T> createFromAsset(String str, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            this.mPrepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.mCopyFromAssetPath = str;
            return this;
        }

        public Builder<T> createFromFile(File file) {
            this.mCopyFromFile = file;
            return this;
        }

        public Builder<T> createFromFile(File file, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            this.mPrepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.mCopyFromFile = file;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> callable) {
            this.mCopyFromInputStream = callable;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> callable, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            this.mPrepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.mCopyFromInputStream = callable;
            return this;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        public Builder<T> addMigrations(Migration... migrationArr) {
            if (this.mMigrationStartAndEndVersions == null) {
                this.mMigrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            this.mMigrationContainer.addMigrations(migrationArr);
            return this;
        }

        public Builder<T> addAutoMigrationSpec(AutoMigrationSpec autoMigrationSpec) {
            if (this.mAutoMigrationSpecs == null) {
                this.mAutoMigrationSpecs = new ArrayList();
            }
            this.mAutoMigrationSpecs.add(autoMigrationSpec);
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            this.mQueryExecutor = executor;
            return this;
        }

        public Builder<T> setTransactionExecutor(Executor executor) {
            this.mTransactionExecutor = executor;
            return this;
        }

        public Builder<T> enableMultiInstanceInvalidation() {
            this.mMultiInstanceInvalidationIntent = this.mName != null ? new Intent(this.mContext, MultiInstanceInvalidationService.class) : null;
            return this;
        }

        public Builder<T> setMultiInstanceInvalidationServiceIntent(Intent intent) {
            if (this.mName == null) {
                intent = null;
            }
            this.mMultiInstanceInvalidationIntent = intent;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            this.mAllowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationOnDowngrade() {
            this.mRequireMigration = true;
            this.mAllowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(int... iArr) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(iArr.length);
            }
            for (int valueOf : iArr) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(valueOf));
            }
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        public Builder<T> setQueryCallback(QueryCallback queryCallback, Executor executor) {
            this.mQueryCallback = queryCallback;
            this.mQueryCallbackExecutor = executor;
            return this;
        }

        public Builder<T> addTypeConverter(Object obj) {
            if (this.mTypeConverters == null) {
                this.mTypeConverters = new ArrayList();
            }
            this.mTypeConverters.add(obj);
            return this;
        }

        public Builder<T> setAutoCloseTimeout(long j, TimeUnit timeUnit) {
            if (j >= 0) {
                this.mAutoCloseTimeout = j;
                this.mAutoCloseTimeUnit = timeUnit;
                return this;
            }
            throw new IllegalArgumentException("autoCloseTimeout must be >= 0");
        }

        public T build() {
            Executor executor;
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.mDatabaseClass != null) {
                Executor executor2 = this.mQueryExecutor;
                if (executor2 == null && this.mTransactionExecutor == null) {
                    Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
                    this.mTransactionExecutor = iOThreadExecutor;
                    this.mQueryExecutor = iOThreadExecutor;
                } else if (executor2 != null && this.mTransactionExecutor == null) {
                    this.mTransactionExecutor = executor2;
                } else if (executor2 == null && (executor = this.mTransactionExecutor) != null) {
                    this.mQueryExecutor = executor;
                }
                Set<Integer> set = this.mMigrationStartAndEndVersions;
                if (!(set == null || this.mMigrationsNotRequiredFrom == null)) {
                    for (Integer next : set) {
                        if (this.mMigrationsNotRequiredFrom.contains(next)) {
                            throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + next);
                        }
                    }
                }
                AutoClosingRoomOpenHelperFactory autoClosingRoomOpenHelperFactory = this.mFactory;
                if (autoClosingRoomOpenHelperFactory == null) {
                    autoClosingRoomOpenHelperFactory = new FrameworkSQLiteOpenHelperFactory();
                }
                long j = this.mAutoCloseTimeout;
                if (j > 0) {
                    if (this.mName != null) {
                        autoClosingRoomOpenHelperFactory = new AutoClosingRoomOpenHelperFactory(autoClosingRoomOpenHelperFactory, new AutoCloser(j, this.mAutoCloseTimeUnit, this.mTransactionExecutor));
                    } else {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
                    }
                }
                String str = this.mCopyFromAssetPath;
                if (!(str == null && this.mCopyFromFile == null && this.mCopyFromInputStream == null)) {
                    if (this.mName != null) {
                        int i = 0;
                        int i2 = str == null ? 0 : 1;
                        File file = this.mCopyFromFile;
                        int i3 = i2 + (file == null ? 0 : 1);
                        Callable<InputStream> callable = this.mCopyFromInputStream;
                        if (callable != null) {
                            i = 1;
                        }
                        if (i3 + i == 1) {
                            autoClosingRoomOpenHelperFactory = new SQLiteCopyOpenHelperFactory(str, file, callable, autoClosingRoomOpenHelperFactory);
                        } else {
                            throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                    }
                }
                QueryCallback queryCallback = this.mQueryCallback;
                QueryInterceptorOpenHelperFactory queryInterceptorOpenHelperFactory = queryCallback != null ? new QueryInterceptorOpenHelperFactory(autoClosingRoomOpenHelperFactory, queryCallback, this.mQueryCallbackExecutor) : autoClosingRoomOpenHelperFactory;
                Context context = this.mContext;
                DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, this.mName, queryInterceptorOpenHelperFactory, this.mMigrationContainer, (List<Callback>) this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(context), this.mQueryExecutor, this.mTransactionExecutor, this.mMultiInstanceInvalidationIntent, this.mRequireMigration, this.mAllowDestructiveMigrationOnDowngrade, this.mMigrationsNotRequiredFrom, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, this.mPrepackagedDatabaseCallback, this.mTypeConverters, this.mAutoMigrationSpecs);
                T t = (RoomDatabase) Room.getGeneratedImplementation(this.mDatabaseClass, RoomDatabase.DB_IMPL_SUFFIX);
                t.init(databaseConfiguration);
                return t;
            } else {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
        }
    }

    public static class MigrationContainer {
        private HashMap<Integer, TreeMap<Integer, Migration>> mMigrations = new HashMap<>();

        public void addMigrations(Migration... migrationArr) {
            for (Migration addMigration : migrationArr) {
                addMigration(addMigration);
            }
        }

        public void addMigrations(List<Migration> list) {
            for (Migration addMigration : list) {
                addMigration(addMigration);
            }
        }

        private void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            TreeMap treeMap = this.mMigrations.get(Integer.valueOf(i));
            if (treeMap == null) {
                treeMap = new TreeMap();
                this.mMigrations.put(Integer.valueOf(i), treeMap);
            }
            Migration migration2 = (Migration) treeMap.get(Integer.valueOf(i2));
            if (migration2 != null) {
                Log.w("ROOM", "Overriding migration " + migration2 + " with " + migration);
            }
            treeMap.put(Integer.valueOf(i2), migration);
        }

        public Map<Integer, Map<Integer, Migration>> getMigrations() {
            return Collections.unmodifiableMap(this.mMigrations);
        }

        public List<Migration> findMigrationPath(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        private List<Migration> findUpMigrationPath(List<Migration> list, boolean z, int i, int i2) {
            Set set;
            boolean z2;
            do {
                if (z) {
                    if (i >= i2) {
                        return list;
                    }
                } else if (i <= i2) {
                    return list;
                }
                TreeMap treeMap = this.mMigrations.get(Integer.valueOf(i));
                if (treeMap != null) {
                    if (z) {
                        set = treeMap.descendingKeySet();
                    } else {
                        set = treeMap.keySet();
                    }
                    Iterator it = set.iterator();
                    while (true) {
                        z2 = true;
                        boolean z3 = false;
                        if (!it.hasNext()) {
                            z2 = false;
                            continue;
                            break;
                        }
                        int intValue = ((Integer) it.next()).intValue();
                        if (!z ? !(intValue < i2 || intValue >= i) : !(intValue > i2 || intValue <= i)) {
                            z3 = true;
                            continue;
                        }
                        if (z3) {
                            list.add((Migration) treeMap.get(Integer.valueOf(intValue)));
                            i = intValue;
                            continue;
                            break;
                        }
                    }
                } else {
                    return null;
                }
            } while (z2);
            return null;
        }
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
