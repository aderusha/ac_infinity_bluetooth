package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0002\u0007\n\u0005\b20\u0001¨\u0006\t"}, mo27512d2 = {"transaction", "T", "Landroid/database/sqlite/SQLiteDatabase;", "exclusive", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroid/database/sqlite/SQLiteDatabase;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SQLiteDatabase.kt */
public final class SQLiteDatabaseKt {
    public static /* synthetic */ Object transaction$default(SQLiteDatabase sQLiteDatabase, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(function1, "body");
        if (z) {
            sQLiteDatabase.beginTransaction();
        } else {
            sQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            Object invoke = function1.invoke(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            sQLiteDatabase.endTransaction();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T transaction(SQLiteDatabase sQLiteDatabase, boolean z, Function1<? super SQLiteDatabase, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(function1, "body");
        if (z) {
            sQLiteDatabase.beginTransaction();
        } else {
            sQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            T invoke = function1.invoke(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            sQLiteDatabase.endTransaction();
            InlineMarker.finallyEnd(1);
        }
    }
}
