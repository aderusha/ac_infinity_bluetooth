package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda14 */
public final /* synthetic */ class C0650xee9a7189 implements Function {
    public static final /* synthetic */ C0650xee9a7189 INSTANCE = new C0650xee9a7189();

    private /* synthetic */ C0650xee9a7189() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteDatabase) obj).getMaximumSize());
    }
}
