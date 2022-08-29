package kotlinx.coroutines.rx2;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.disposables.Disposables;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxConvertKt$asFlow$1", mo28222f = "RxConvert.kt", mo28223i = {}, mo28224l = {95}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObservableSource $this_asFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxConvertKt$asFlow$1(ObservableSource observableSource, Continuation continuation) {
        super(2, continuation);
        this.$this_asFlow = observableSource;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RxConvertKt$asFlow$1 rxConvertKt$asFlow$1 = new RxConvertKt$asFlow$1(this.$this_asFlow, continuation);
        rxConvertKt$asFlow$1.L$0 = obj;
        return rxConvertKt$asFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RxConvertKt$asFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final AtomicReference atomicReference = new AtomicReference();
            this.$this_asFlow.subscribe(new RxConvertKt$asFlow$1$observer$1(producerScope, atomicReference));
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    Disposable disposable = (Disposable) atomicReference.getAndSet(Disposables.disposed());
                    if (disposable != null) {
                        disposable.dispose();
                    }
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
