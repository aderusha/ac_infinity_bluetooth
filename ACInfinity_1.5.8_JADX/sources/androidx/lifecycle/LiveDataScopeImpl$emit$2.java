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
@DebugMetadata(mo28221c = "androidx.lifecycle.LiveDataScopeImpl$emit$2", mo28222f = "CoroutineLiveData.kt", mo28223i = {0}, mo28224l = {98}, mo28225m = "invokeSuspend", mo28226n = {"$this$withContext"}, mo28227s = {"L$0"})
/* compiled from: CoroutineLiveData.kt */
final class LiveDataScopeImpl$emit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $value;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f55p$;
    final /* synthetic */ LiveDataScopeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LiveDataScopeImpl$emit$2(LiveDataScopeImpl liveDataScopeImpl, Object obj, Continuation continuation) {
        super(2, continuation);
        this.this$0 = liveDataScopeImpl;
        this.$value = obj;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        LiveDataScopeImpl$emit$2 liveDataScopeImpl$emit$2 = new LiveDataScopeImpl$emit$2(this.this$0, this.$value, continuation);
        liveDataScopeImpl$emit$2.f55p$ = (CoroutineScope) obj;
        return liveDataScopeImpl$emit$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LiveDataScopeImpl$emit$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f55p$;
            CoroutineLiveData target$lifecycle_livedata_ktx_release = this.this$0.getTarget$lifecycle_livedata_ktx_release();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (target$lifecycle_livedata_ktx_release.clearSource$lifecycle_livedata_ktx_release(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getTarget$lifecycle_livedata_ktx_release().setValue(this.$value);
        return Unit.INSTANCE;
    }
}
