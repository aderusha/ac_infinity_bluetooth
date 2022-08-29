package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u0002H\u0002 \u0003*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0005*\u00020\u0004\"\b\b\u0002\u0010\u0006*\u00020\u0004\"\b\b\u0003\u0010\u0005*\u00020\u0004\"\b\b\u0004\u0010\u0006*\u00020\u00042\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u0002H\u0006 \u0003*\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\b"}, mo27512d2 = {"<anonymous>", "", "ToValue", "kotlin.jvm.PlatformType", "", "Key", "Value", "list", "apply"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: DataSource.kt */
final class DataSource$Factory$map$2<I, O> implements Function<List<? extends Value>, List<? extends ToValue>> {
    final /* synthetic */ Function1 $function;

    DataSource$Factory$map$2(Function1 function1) {
        this.$function = function1;
    }

    public final List<ToValue> apply(List<? extends Value> list) {
        Intrinsics.checkNotNullExpressionValue(list, "list");
        Iterable<Object> iterable = list;
        Function1 function1 = this.$function;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object invoke : iterable) {
            arrayList.add(function1.invoke(invoke));
        }
        return (List) arrayList;
    }
}
