package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import java.util.Locale;

/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda1 */
public final /* synthetic */ class C0645x9c57900b implements Function {
    public final /* synthetic */ Locale f$0;

    public /* synthetic */ C0645x9c57900b(Locale locale) {
        this.f$0 = locale;
    }

    public final Object apply(Object obj) {
        return ((SupportSQLiteDatabase) obj).setLocale(this.f$0);
    }
}
