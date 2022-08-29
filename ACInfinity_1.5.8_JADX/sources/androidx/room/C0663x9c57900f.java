package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda5 */
public final /* synthetic */ class C0663x9c57900f implements Function {
    public static final /* synthetic */ C0663x9c57900f INSTANCE = new C0663x9c57900f();

    private /* synthetic */ C0663x9c57900f() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).getAttachedDbs();
    }
}
