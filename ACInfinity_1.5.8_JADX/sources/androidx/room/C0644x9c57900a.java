package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda0 */
public final /* synthetic */ class C0644x9c57900a implements Function {
    public final /* synthetic */ int f$0;

    public /* synthetic */ C0644x9c57900a(int i) {
        this.f$0 = i;
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).needUpgrade(this.f$0));
    }
}
