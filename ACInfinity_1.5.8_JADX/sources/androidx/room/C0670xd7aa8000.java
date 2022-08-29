package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteStatement;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda2 */
public final /* synthetic */ class C0670xd7aa8000 implements Function {
    public static final /* synthetic */ C0670xd7aa8000 INSTANCE = new C0670xd7aa8000();

    private /* synthetic */ C0670xd7aa8000() {
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((SupportSQLiteStatement) obj).executeUpdateDelete());
    }
}
