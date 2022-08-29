package androidx.paging.multicast;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B`\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u001b\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u000f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R/\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, mo27512d2 = {"Landroidx/paging/multicast/Multicaster;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "bufferSize", "", "source", "Lkotlinx/coroutines/flow/Flow;", "piggybackingDownstream", "", "onEach", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "keepUpstreamAlive", "(Lkotlinx/coroutines/CoroutineScope;ILkotlinx/coroutines/flow/Flow;ZLkotlin/jvm/functions/Function2;Z)V", "channelManager", "Landroidx/paging/multicast/ChannelManager;", "getChannelManager", "()Landroidx/paging/multicast/ChannelManager;", "channelManager$delegate", "Lkotlin/Lazy;", "flow", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "Lkotlin/jvm/functions/Function2;", "close", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Multicaster.kt */
public final class Multicaster<T> {
    private final Lazy channelManager$delegate;
    private final Flow<T> flow;
    /* access modifiers changed from: private */
    public final boolean keepUpstreamAlive;
    /* access modifiers changed from: private */
    public final Function2<T, Continuation<? super Unit>, Object> onEach;
    /* access modifiers changed from: private */
    public final boolean piggybackingDownstream;
    /* access modifiers changed from: private */
    public final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final Flow<T> source;

    /* access modifiers changed from: private */
    public final ChannelManager<T> getChannelManager() {
        return (ChannelManager) this.channelManager$delegate.getValue();
    }

    public Multicaster(CoroutineScope coroutineScope, int i, Flow<? extends T> flow2, boolean z, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, boolean z2) {
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(flow2, "source");
        Intrinsics.checkNotNullParameter(function2, "onEach");
        this.scope = coroutineScope;
        this.source = flow2;
        this.piggybackingDownstream = z;
        this.onEach = function2;
        this.keepUpstreamAlive = z2;
        this.channelManager$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Multicaster$channelManager$2(this, i));
        this.flow = FlowKt.flow(new Multicaster$flow$1(this, (Continuation) null));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Multicaster(CoroutineScope coroutineScope, int i, Flow flow2, boolean z, Function2 function2, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, (i2 & 2) != 0 ? 0 : i, flow2, (i2 & 8) != 0 ? false : z, function2, (i2 & 32) != 0 ? false : z2);
    }

    public final Flow<T> getFlow() {
        return this.flow;
    }

    public final Object close(Continuation<? super Unit> continuation) {
        Object close = getChannelManager().close(continuation);
        if (close == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return close;
        }
        return Unit.INSTANCE;
    }
}
