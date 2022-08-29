package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u0002H\u0002 \u0003*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00042\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u0002H\u0005 \u0003*\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "V", "kotlin.jvm.PlatformType", "", "T", "it", "apply"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: PositionalDataSource.kt */
final class PositionalDataSource$mapByPage$1<I, O> implements Function<List<? extends T>, List<? extends V>> {
    final /* synthetic */ Function1 $function;

    PositionalDataSource$mapByPage$1(Function1 function1) {
        this.$function = function1;
    }

    public final List<V> apply(List<? extends T> list) {
        Function1 function1 = this.$function;
        Intrinsics.checkNotNullExpressionValue(list, "it");
        return (List) function1.invoke(list);
    }
}
