package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.util.concurrent.CancellationException;
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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo27512d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.LifecycleCoroutineScopeImpl$register$1", mo28222f = "Lifecycle.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Lifecycle.kt */
final class LifecycleCoroutineScopeImpl$register$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f54p$;
    final /* synthetic */ LifecycleCoroutineScopeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LifecycleCoroutineScopeImpl$register$1(LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lifecycleCoroutineScopeImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        LifecycleCoroutineScopeImpl$register$1 lifecycleCoroutineScopeImpl$register$1 = new LifecycleCoroutineScopeImpl$register$1(this.this$0, continuation);
        lifecycleCoroutineScopeImpl$register$1.f54p$ = (CoroutineScope) obj;
        return lifecycleCoroutineScopeImpl$register$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LifecycleCoroutineScopeImpl$register$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f54p$;
            if (this.this$0.getLifecycle$lifecycle_runtime_ktx_release().getCurrentState().compareTo(Lifecycle.State.INITIALIZED) >= 0) {
                this.this$0.getLifecycle$lifecycle_runtime_ktx_release().addObserver(this.this$0);
            } else {
                JobKt__JobKt.cancel$default(coroutineScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
