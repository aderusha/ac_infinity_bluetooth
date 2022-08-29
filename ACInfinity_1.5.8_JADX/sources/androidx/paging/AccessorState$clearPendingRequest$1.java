package androidx.paging;

import androidx.paging.AccessorState;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "it", "Landroidx/paging/AccessorState$PendingRequest;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
final class AccessorState$clearPendingRequest$1 extends Lambda implements Function1<AccessorState.PendingRequest<Key, Value>, Boolean> {
    final /* synthetic */ LoadType $loadType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AccessorState$clearPendingRequest$1(LoadType loadType) {
        super(1);
        this.$loadType = loadType;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((AccessorState.PendingRequest) obj));
    }

    public final boolean invoke(AccessorState.PendingRequest<Key, Value> pendingRequest) {
        Intrinsics.checkNotNullParameter(pendingRequest, "it");
        return pendingRequest.getLoadType() == this.$loadType;
    }
}
