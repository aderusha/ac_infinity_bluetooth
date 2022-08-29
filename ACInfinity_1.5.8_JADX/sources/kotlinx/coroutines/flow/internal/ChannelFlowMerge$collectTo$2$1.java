package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Semaphore;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1", mo28222f = "Merge.kt", mo28223i = {}, mo28224l = {69}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Merge.kt */
final class ChannelFlowMerge$collectTo$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendingCollector<T> $collector;
    final /* synthetic */ Flow<T> $inner;
    final /* synthetic */ Semaphore $semaphore;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelFlowMerge$collectTo$2$1(Flow<? extends T> flow, SendingCollector<T> sendingCollector, Semaphore semaphore, Continuation<? super ChannelFlowMerge$collectTo$2$1> continuation) {
        super(2, continuation);
        this.$inner = flow;
        this.$collector = sendingCollector;
        this.$semaphore = semaphore;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ChannelFlowMerge$collectTo$2$1(this.$inner, this.$collector, this.$semaphore, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChannelFlowMerge$collectTo$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$inner.collect(this.$collector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.$semaphore.release();
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$semaphore.release();
        return Unit.INSTANCE;
    }
}
