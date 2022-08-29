package androidx.room;

import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;

class SQLiteCopyOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final String mCopyFromAssetPath;
    private final File mCopyFromFile;
    private final Callable<InputStream> mCopyFromInputStream;
    private final SupportSQLiteOpenHelper.Factory mDelegate;

    SQLiteCopyOpenHelperFactory(String str, File file, Callable<InputStream> callable, SupportSQLiteOpenHelper.Factory factory) {
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mCopyFromInputStream = callable;
        this.mDelegate = factory;
    }

    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, configuration.callback.version, this.mDelegate.create(configuration));
    }
}
