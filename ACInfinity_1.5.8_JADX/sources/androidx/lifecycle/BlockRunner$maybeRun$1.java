package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.BlockRunner$maybeRun$1", mo28222f = "CoroutineLiveData.kt", mo28223i = {0, 0}, mo28224l = {176}, mo28225m = "invokeSuspend", mo28226n = {"$this$launch", "liveDataScope"}, mo28227s = {"L$0", "L$1"})
/* compiled from: CoroutineLiveData.kt */
final class BlockRunner$maybeRun$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f43p$;
    final /* synthetic */ BlockRunner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BlockRunner$maybeRun$1(BlockRunner blockRunner, Continuation continuation) {
        super(2, continuation);
        this.this$0 = blockRunner;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        BlockRunner$maybeRun$1 blockRunner$maybeRun$1 = new BlockRunner$maybeRun$1(this.this$0, continuation);
        blockRunner$maybeRun$1.f43p$ = (CoroutineScope) obj;
        return blockRunner$maybeRun$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BlockRunner$maybeRun$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f43p$;
            LiveDataScopeImpl liveDataScopeImpl = new LiveDataScopeImpl(this.this$0.liveData, coroutineScope.getCoroutineContext());
            Function2 access$getBlock$p = this.this$0.block;
            this.L$0 = coroutineScope;
            this.L$1 = liveDataScopeImpl;
            this.label = 1;
            if (access$getBlock$p.invoke(liveDataScopeImpl, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            LiveDataScopeImpl liveDataScopeImpl2 = (LiveDataScopeImpl) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.onDone.invoke();
        return Unit.INSTANCE;
    }
}
