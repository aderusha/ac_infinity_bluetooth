package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda18 */
public final /* synthetic */ class C0654xee9a718d implements Function {
    public final /* synthetic */ long f$0;

    public /* synthetic */ C0654xee9a718d(long j) {
        this.f$0 = j;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).setPageSize(this.f$0);
    }
}
