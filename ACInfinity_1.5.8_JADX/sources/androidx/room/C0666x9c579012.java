package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda8 */
public final /* synthetic */ class C0666x9c579012 implements Function {
    public static final /* synthetic */ C0666x9c579012 INSTANCE = new C0666x9c579012();

    private /* synthetic */ C0666x9c579012() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).inTransaction());
    }
}
