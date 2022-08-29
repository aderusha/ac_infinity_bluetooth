package androidx.paging;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J1\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0002\u0010\u00112\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u0002H\u00110\u0013¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo27512d2 = {"Landroidx/paging/AccessorStateHolder;", "Key", "", "Value", "()V", "_loadStates", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/paging/LoadStates;", "internalState", "Landroidx/paging/AccessorState;", "loadStates", "Lkotlinx/coroutines/flow/StateFlow;", "getLoadStates", "()Lkotlinx/coroutines/flow/StateFlow;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "use", "R", "block", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediatorAccessor.kt */
final class AccessorStateHolder<Key, Value> {
    private final MutableStateFlow<LoadStates> _loadStates = StateFlowKt.MutableStateFlow(LoadStates.Companion.getIDLE());
    private final AccessorState<Key, Value> internalState = new AccessorState<>();
    private final ReentrantLock lock = new ReentrantLock();

    public final StateFlow<LoadStates> getLoadStates() {
        return this._loadStates;
    }

    public final <R> R use(Function1<? super AccessorState<Key, Value>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            R invoke = function1.invoke(this.internalState);
            this._loadStates.setValue(this.internalState.computeLoadStates());
            return invoke;
        } finally {
            lock2.unlock();
        }
    }
}
