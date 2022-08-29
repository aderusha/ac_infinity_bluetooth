package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda11 */
public final /* synthetic */ class C0647xee9a7186 implements Function {
    public final /* synthetic */ int f$0;

    public /* synthetic */ C0647xee9a7186(int i) {
        this.f$0 = i;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).setMaxSqlCacheSize(this.f$0);
    }
}
