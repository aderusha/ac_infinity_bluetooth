package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda0 */
public final /* synthetic */ class C0668xd7aa7ffe implements Function {
    public final /* synthetic */ AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement f$0;
    public final /* synthetic */ Function f$1;

    public /* synthetic */ C0668xd7aa7ffe(AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement autoClosingSupportSqliteStatement, Function function) {
        this.f$0 = autoClosingSupportSqliteStatement;
        this.f$1 = function;
    }

    public final Object apply(Object obj) {
        return this.f$0.mo9734xdabdfde4(this.f$1, (SupportSQLiteDatabase) obj);
    }
}
