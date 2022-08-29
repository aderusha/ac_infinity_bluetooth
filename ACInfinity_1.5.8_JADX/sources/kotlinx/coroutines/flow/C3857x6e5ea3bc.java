package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharingDeferred$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C3857x6e5ea3bc implements FlowCollector<T> {
    final /* synthetic */ CoroutineScope $$this$launch$inlined;
    final /* synthetic */ CompletableDeferred $result$inlined;
    final /* synthetic */ Ref.ObjectRef $state$inlined;

    public C3857x6e5ea3bc(Ref.ObjectRef objectRef, CoroutineScope coroutineScope, CompletableDeferred completableDeferred) {
        this.$state$inlined = objectRef;
        this.$$this$launch$inlined = coroutineScope;
        this.$result$inlined = completableDeferred;
    }

    public Object emit(T t, Continuation<? super Unit> continuation) {
        Unit unit;
        MutableStateFlow mutableStateFlow = (MutableStateFlow) this.$state$inlined.element;
        if (mutableStateFlow == null) {
            unit = null;
        } else {
            mutableStateFlow.setValue(t);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            CoroutineScope coroutineScope = this.$$this$launch$inlined;
            Ref.ObjectRef objectRef = this.$state$inlined;
            T MutableStateFlow = StateFlowKt.MutableStateFlow(t);
            this.$result$inlined.complete(new ReadonlyStateFlow((StateFlow) MutableStateFlow, JobKt.getJob(coroutineScope.getCoroutineContext())));
            Unit unit2 = Unit.INSTANCE;
            objectRef.element = MutableStateFlow;
        }
        return Unit.INSTANCE;
    }
}
