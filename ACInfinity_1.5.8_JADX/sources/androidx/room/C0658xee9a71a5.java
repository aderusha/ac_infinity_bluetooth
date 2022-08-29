package androidx.room;

import android.content.ContentValues;
import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda21 */
public final /* synthetic */ class C0658xee9a71a5 implements Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ContentValues f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Object[] f$4;

    public /* synthetic */ C0658xee9a71a5(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = contentValues;
        this.f$3 = str2;
        this.f$4 = objArr;
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((SupportSQLiteDatabase) obj).update(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4));
    }
}
