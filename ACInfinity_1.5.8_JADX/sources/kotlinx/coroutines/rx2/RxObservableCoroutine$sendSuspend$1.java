package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo27512d2 = {"sendSuspend", "", "T", "element", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxObservableCoroutine", mo28222f = "RxObservable.kt", mo28223i = {0, 0}, mo28224l = {102}, mo28225m = "sendSuspend", mo28226n = {"this", "element"}, mo28227s = {"L$0", "L$1"})
/* compiled from: RxObservable.kt */
final class RxObservableCoroutine$sendSuspend$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RxObservableCoroutine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxObservableCoroutine$sendSuspend$1(RxObservableCoroutine rxObservableCoroutine, Continuation continuation) {
        super(continuation);
        this.this$0 = rxObservableCoroutine;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendSuspend(null, this);
    }
}
