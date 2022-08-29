package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda6 */
public final /* synthetic */ class C0664x9c579010 implements Function {
    public static final /* synthetic */ C0664x9c579010 INSTANCE = new C0664x9c579010();

    private /* synthetic */ C0664x9c579010() {
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).getPath();
    }
}
