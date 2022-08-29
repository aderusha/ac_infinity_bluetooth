package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda12 */
public final /* synthetic */ class C0648xee9a7187 implements Function {
    public static final /* synthetic */ C0648xee9a7187 INSTANCE = new C0648xee9a7187();

    private /* synthetic */ C0648xee9a7187() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).isReadOnly());
    }
}
