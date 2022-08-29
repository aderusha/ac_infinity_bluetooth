package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda2 */
public final /* synthetic */ class C0656x9c57900c implements Function {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ C0656x9c57900c(boolean z) {
        this.f$0 = z;
    }

    public final Object apply(Object obj) {
        return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setForeignKeyConstraintsEnabled$12(this.f$0, (SupportSQLiteDatabase) obj);
    }
}
