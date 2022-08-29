package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda1 */
public final /* synthetic */ class C0669xd7aa7fff implements Function {
    public static final /* synthetic */ C0669xd7aa7fff INSTANCE = new C0669xd7aa7fff();

    private /* synthetic */ C0669xd7aa7fff() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteStatement) obj).execute();
    }
}
