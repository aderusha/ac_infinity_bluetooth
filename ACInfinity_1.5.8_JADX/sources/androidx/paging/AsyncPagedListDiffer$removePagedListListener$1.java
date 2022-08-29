package androidx.paging;

import androidx.paging.AsyncPagedListDiffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u0002H\u0002 \u0006*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "it", "Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "kotlin.jvm.PlatformType", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagedListDiffer.kt */
final class AsyncPagedListDiffer$removePagedListListener$1 extends Lambda implements Function1<AsyncPagedListDiffer.PagedListListener<T>, Boolean> {
    final /* synthetic */ Function2 $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagedListDiffer$removePagedListListener$1(Function2 function2) {
        super(1);
        this.$callback = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((AsyncPagedListDiffer.PagedListListener) obj));
    }

    public final boolean invoke(AsyncPagedListDiffer.PagedListListener<T> pagedListListener) {
        return (pagedListListener instanceof AsyncPagedListDiffer.OnCurrentListChangedWrapper) && ((AsyncPagedListDiffer.OnCurrentListChangedWrapper) pagedListListener).getCallback() == this.$callback;
    }
}
