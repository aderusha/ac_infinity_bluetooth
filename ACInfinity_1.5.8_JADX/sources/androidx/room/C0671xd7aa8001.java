package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda3 */
public final /* synthetic */ class C0671xd7aa8001 implements Function {
    public static final /* synthetic */ C0671xd7aa8001 INSTANCE = new C0671xd7aa8001();

    private /* synthetic */ C0671xd7aa8001() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteStatement) obj).simpleQueryForString();
    }
}
