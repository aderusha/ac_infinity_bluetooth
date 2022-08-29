package androidx.room;

import android.content.ContentValues;
import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda20 */
public final /* synthetic */ class C0657xee9a71a4 implements Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ContentValues f$2;

    public /* synthetic */ C0657xee9a71a4(String str, int i, ContentValues contentValues) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = contentValues;
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((SupportSQLiteDatabase) obj).insert(this.f$0, this.f$1, this.f$2));
    }
}
