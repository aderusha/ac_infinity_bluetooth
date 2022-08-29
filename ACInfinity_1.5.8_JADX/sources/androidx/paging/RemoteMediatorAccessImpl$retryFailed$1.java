package androidx.paging;

import androidx.paging.AccessorState;
import androidx.paging.LoadState;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "accessorState", "Landroidx/paging/AccessorState;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$retryFailed$1 extends Lambda implements Function1<AccessorState<Key, Value>, Unit> {
    final /* synthetic */ List $toBeStarted;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$retryFailed$1(List list) {
        super(1);
        this.$toBeStarted = list;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AccessorState) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(AccessorState<Key, Value> accessorState) {
        Intrinsics.checkNotNullParameter(accessorState, "accessorState");
        LoadStates computeLoadStates = accessorState.computeLoadStates();
        boolean z = computeLoadStates.getRefresh() instanceof LoadState.Error;
        accessorState.clearErrors();
        if (z) {
            this.$toBeStarted.add(LoadType.REFRESH);
            accessorState.setBlockState(LoadType.REFRESH, AccessorState.BlockState.UNBLOCKED);
        }
        if (computeLoadStates.getAppend() instanceof LoadState.Error) {
            if (!z) {
                this.$toBeStarted.add(LoadType.APPEND);
            }
            accessorState.clearPendingRequest(LoadType.APPEND);
        }
        if (computeLoadStates.getPrepend() instanceof LoadState.Error) {
            if (!z) {
                this.$toBeStarted.add(LoadType.PREPEND);
            }
            accessorState.clearPendingRequest(LoadType.PREPEND);
        }
    }
}
