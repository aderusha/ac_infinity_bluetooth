package androidx.paging;

import androidx.paging.DataSource;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.LegacyPagingSource$sam$androidx_paging_DataSource_InvalidatedCallback$0 */
/* compiled from: LegacyPagingSource.kt */
final class C0481xc5136f96 implements DataSource.InvalidatedCallback, FunctionAdapter {
    private final /* synthetic */ Function0 function;

    C0481xc5136f96(Function0 function0) {
        this.function = function0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof DataSource.InvalidatedCallback) && (obj instanceof FunctionAdapter) && Intrinsics.areEqual((Object) this.function, (Object) ((FunctionAdapter) obj).getFunctionDelegate());
    }

    public Function getFunctionDelegate() {
        return this.function;
    }

    public int hashCode() {
        return this.function.hashCode();
    }

    public final /* synthetic */ void onInvalidated() {
        Intrinsics.checkNotNullExpressionValue(this.function.invoke(), "invoke(...)");
    }
}
