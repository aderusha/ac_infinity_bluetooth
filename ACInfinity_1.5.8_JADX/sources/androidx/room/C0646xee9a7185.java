package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda10 */
public final /* synthetic */ class C0646xee9a7185 implements Function {
    public static final /* synthetic */ C0646xee9a7185 INSTANCE = new C0646xee9a7185();

    private /* synthetic */ C0646xee9a7185() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDbLockedByCurrentThread());
    }
}
