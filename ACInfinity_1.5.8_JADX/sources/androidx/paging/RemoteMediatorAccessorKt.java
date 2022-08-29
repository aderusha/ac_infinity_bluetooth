package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0000¨\u0006\t"}, mo27512d2 = {"RemoteMediatorAccessor", "Landroidx/paging/RemoteMediatorAccessor;", "Key", "Value", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "delegate", "Landroidx/paging/RemoteMediator;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
public final class RemoteMediatorAccessorKt {
    public static final <Key, Value> RemoteMediatorAccessor<Key, Value> RemoteMediatorAccessor(CoroutineScope coroutineScope, RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(remoteMediator, "delegate");
        return new RemoteMediatorAccessImpl<>(coroutineScope, remoteMediator);
    }
}
