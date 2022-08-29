package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@"}, mo27512d2 = {"runInIsolation", "", "priority", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "continuation"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SingleRunner", mo28222f = "SingleRunner.kt", mo28223i = {0}, mo28224l = {49}, mo28225m = "runInIsolation", mo28226n = {"this"}, mo28227s = {"L$0"})
/* compiled from: SingleRunner.kt */
final class SingleRunner$runInIsolation$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleRunner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleRunner$runInIsolation$1(SingleRunner singleRunner, Continuation continuation) {
        super(continuation);
        this.this$0 = singleRunner;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.runInIsolation(0, (Function1<? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
