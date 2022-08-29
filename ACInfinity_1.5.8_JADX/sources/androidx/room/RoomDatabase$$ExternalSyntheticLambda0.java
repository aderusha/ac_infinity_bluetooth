package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p005db.SupportSQLiteDatabase;

public final /* synthetic */ class RoomDatabase$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ RoomDatabase f$0;

    public /* synthetic */ RoomDatabase$$ExternalSyntheticLambda0(RoomDatabase roomDatabase) {
        this.f$0 = roomDatabase;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$beginTransaction$0$RoomDatabase((SupportSQLiteDatabase) obj);
    }
}
