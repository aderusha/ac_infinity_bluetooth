package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda16 */
public final /* synthetic */ class C0652xee9a718b implements Function {
    public final /* synthetic */ int f$0;

    public /* synthetic */ C0652xee9a718b(int i) {
        this.f$0 = i;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).setVersion(this.f$0);
    }
}
