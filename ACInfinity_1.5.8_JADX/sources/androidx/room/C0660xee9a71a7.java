package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda23 */
public final /* synthetic */ class C0660xee9a71a7 implements Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Object[] f$1;

    public /* synthetic */ C0660xee9a71a7(String str, Object[] objArr) {
        this.f$0 = str;
        this.f$1 = objArr;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).execSQL(this.f$0, this.f$1);
    }
}
