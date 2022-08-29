package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda22 */
public final /* synthetic */ class C0659xee9a71a6 implements Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Object[] f$2;

    public /* synthetic */ C0659xee9a71a6(String str, String str2, Object[] objArr) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = objArr;
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((SupportSQLiteDatabase) obj).delete(this.f$0, this.f$1, this.f$2));
    }
}
