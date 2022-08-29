package kotlinx.coroutines.rx2;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import p014io.reactivex.ObservableEmitter;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxConvertKt$asObservable$1$job$1", mo28222f = "RxConvert.kt", mo28223i = {0}, mo28224l = {167}, mo28225m = "invokeSuspend", mo28226n = {"$this$launch"}, mo28227s = {"L$0"})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asObservable$1$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObservableEmitter $emitter;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RxConvertKt$asObservable$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxConvertKt$asObservable$1$job$1(RxConvertKt$asObservable$1 rxConvertKt$asObservable$1, ObservableEmitter observableEmitter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rxConvertKt$asObservable$1;
        this.$emitter = observableEmitter;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RxConvertKt$asObservable$1$job$1 rxConvertKt$asObservable$1$job$1 = new RxConvertKt$asObservable$1$job$1(this.this$0, this.$emitter, continuation);
        rxConvertKt$asObservable$1$job$1.L$0 = obj;
        return rxConvertKt$asObservable$1$job$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RxConvertKt$asObservable$1$job$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th;
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                this.L$0 = coroutineScope2;
                this.label = 1;
                if (this.this$0.$this_asObservable.collect(new C3918x5ce6a488(this), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                coroutineScope = coroutineScope2;
                th = th3;
                if (th instanceof CancellationException) {
                    this.$emitter.onComplete();
                } else if (!this.$emitter.tryOnError(th)) {
                    RxCancellableKt.handleUndeliverableException(th, coroutineScope.getCoroutineContext());
                }
                return Unit.INSTANCE;
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$emitter.onComplete();
        return Unit.INSTANCE;
    }
}
