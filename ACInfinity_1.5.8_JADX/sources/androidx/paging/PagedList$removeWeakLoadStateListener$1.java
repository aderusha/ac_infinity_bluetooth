package androidx.paging;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00060\u0005H\n¢\u0006\u0002\b\n"}, mo27512d2 = {"<anonymous>", "", "T", "", "it", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: PagedList.kt */
final class PagedList$removeWeakLoadStateListener$1 extends Lambda implements Function1<WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>>, Boolean> {
    final /* synthetic */ Function2 $listener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagedList$removeWeakLoadStateListener$1(Function2 function2) {
        super(1);
        this.$listener = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((WeakReference<Function2<LoadType, LoadState, Unit>>) (WeakReference) obj));
    }

    public final boolean invoke(WeakReference<Function2<LoadType, LoadState, Unit>> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "it");
        return weakReference.get() == null || ((Function2) weakReference.get()) == this.$listener;
    }
}
