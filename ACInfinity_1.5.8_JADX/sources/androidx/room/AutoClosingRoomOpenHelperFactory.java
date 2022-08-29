package androidx.room;

import androidx.sqlite.p005db.SupportSQLiteOpenHelper;

final class AutoClosingRoomOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final AutoCloser mAutoCloser;
    private final SupportSQLiteOpenHelper.Factory mDelegate;

    AutoClosingRoomOpenHelperFactory(SupportSQLiteOpenHelper.Factory factory, AutoCloser autoCloser) {
        this.mDelegate = factory;
        this.mAutoCloser = autoCloser;
    }

    public AutoClosingRoomOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new AutoClosingRoomOpenHelper(this.mDelegate.create(configuration), this.mAutoCloser);
    }
}
