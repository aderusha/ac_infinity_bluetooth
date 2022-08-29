package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingState;", "Key", "Value", "", "it", "Landroidx/paging/AccessorState;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1 extends Lambda implements Function1<AccessorState<Key, Value>, PagingState<Key, Value>> {
    public static final RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1 INSTANCE = new RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1();

    RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1() {
        super(1);
    }

    public final PagingState<Key, Value> invoke(AccessorState<Key, Value> accessorState) {
        Intrinsics.checkNotNullParameter(accessorState, "it");
        return accessorState.getPendingRefresh();
    }
}
