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
import kotlinx.coroutines.DisposableHandle;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "Lkotlinx/coroutines/DisposableHandle;", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.LiveDataScopeImpl$emitSource$2", mo28222f = "CoroutineLiveData.kt", mo28223i = {0}, mo28224l = {94}, mo28225m = "invokeSuspend", mo28226n = {"$this$withContext"}, mo28227s = {"L$0"})
/* compiled from: CoroutineLiveData.kt */
final class LiveDataScopeImpl$emitSource$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DisposableHandle>, Object> {
    final /* synthetic */ LiveData $source;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f56p$;
    final /* synthetic */ LiveDataScopeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LiveDataScopeImpl$emitSource$2(LiveDataScopeImpl liveDataScopeImpl, LiveData liveData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = liveDataScopeImpl;
        this.$source = liveData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        LiveDataScopeImpl$emitSource$2 liveDataScopeImpl$emitSource$2 = new LiveDataScopeImpl$emitSource$2(this.this$0, this.$source, continuation);
        liveDataScopeImpl$emitSource$2.f56p$ = (CoroutineScope) obj;
        return liveDataScopeImpl$emitSource$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LiveDataScopeImpl$emitSource$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f56p$;
            CoroutineLiveData target$lifecycle_livedata_ktx_release = this.this$0.getTarget$lifecycle_livedata_ktx_release();
            LiveData liveData = this.$source;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = target$lifecycle_livedata_ktx_release.emitSource$lifecycle_livedata_ktx_release(liveData, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
