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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "Landroidx/lifecycle/EmittedSource;", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.CoroutineLiveDataKt$addDisposableSource$2", mo28222f = "CoroutineLiveData.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: CoroutineLiveData.kt */
final class CoroutineLiveDataKt$addDisposableSource$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EmittedSource>, Object> {
    final /* synthetic */ LiveData $source;
    final /* synthetic */ MediatorLiveData $this_addDisposableSource;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f44p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineLiveDataKt$addDisposableSource$2(MediatorLiveData mediatorLiveData, LiveData liveData, Continuation continuation) {
        super(2, continuation);
        this.$this_addDisposableSource = mediatorLiveData;
        this.$source = liveData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CoroutineLiveDataKt$addDisposableSource$2 coroutineLiveDataKt$addDisposableSource$2 = new CoroutineLiveDataKt$addDisposableSource$2(this.$this_addDisposableSource, this.$source, continuation);
        coroutineLiveDataKt$addDisposableSource$2.f44p$ = (CoroutineScope) obj;
        return coroutineLiveDataKt$addDisposableSource$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CoroutineLiveDataKt$addDisposableSource$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$this_addDisposableSource.addSource(this.$source, new Observer<S>(this) {
                final /* synthetic */ CoroutineLiveDataKt$addDisposableSource$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onChanged(T t) {
                    this.this$0.$this_addDisposableSource.setValue(t);
                }
            });
            return new EmittedSource(this.$source, this.$this_addDisposableSource);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
