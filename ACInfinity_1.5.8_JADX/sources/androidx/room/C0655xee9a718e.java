package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda19 */
public final /* synthetic */ class C0655xee9a718e implements Function {
    public final /* synthetic */ String f$0;

    public /* synthetic */ C0655xee9a718e(String str) {
        this.f$0 = str;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).execSQL(this.f$0);
    }
}
