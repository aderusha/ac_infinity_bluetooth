package androidx.paging;

import androidx.paging.AccessorState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "it", "Landroidx/paging/AccessorState;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$initialize$2$1 extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
    public static final RemoteMediatorAccessImpl$initialize$2$1 INSTANCE = new RemoteMediatorAccessImpl$initialize$2$1();

    RemoteMediatorAccessImpl$initialize$2$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AccessorState) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(AccessorState<Key, Value> accessorState) {
        Intrinsics.checkNotNullParameter(accessorState, "it");
        accessorState.setBlockState(LoadType.APPEND, AccessorState.BlockState.REQUIRES_REFRESH);
        accessorState.setBlockState(LoadType.PREPEND, AccessorState.BlockState.REQUIRES_REFRESH);
    }
}
