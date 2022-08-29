package androidx.room;

import androidx.sqlite.p005db.SupportSQLiteOpenHelper;

interface DelegatingOpenHelper {
    SupportSQLiteOpenHelper getDelegate();
}
