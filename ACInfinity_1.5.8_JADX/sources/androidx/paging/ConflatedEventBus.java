package androidx.paging;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0004J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b0\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo27512d2 = {"Landroidx/paging/ConflatedEventBus;", "T", "", "initialValue", "(Ljava/lang/Object;)V", "flow", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Pair;", "", "send", "", "data", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ConflatedEventBus.kt */
public final class ConflatedEventBus<T> {
    private final Flow<T> flow;
    private final MutableStateFlow<Pair<Integer, T>> state;

    public ConflatedEventBus() {
        this((Object) null, 1, (DefaultConstructorMarker) null);
    }

    public ConflatedEventBus(T t) {
        MutableStateFlow<Pair<Integer, T>> MutableStateFlow = StateFlowKt.MutableStateFlow(new Pair(Integer.MIN_VALUE, t));
        this.state = MutableStateFlow;
        this.flow = new ConflatedEventBus$$special$$inlined$mapNotNull$1(MutableStateFlow);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ConflatedEventBus(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj);
    }

    public final Flow<T> getFlow() {
        return this.flow;
    }

    public final void send(T t) {
        Intrinsics.checkNotNullParameter(t, "data");
        this.state.setValue(new Pair(Integer.valueOf(((Number) this.state.getValue().getFirst()).intValue() + 1), t));
    }
}
