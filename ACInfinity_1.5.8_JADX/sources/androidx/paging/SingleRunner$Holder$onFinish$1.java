package androidx.paging;

import androidx.paging.SingleRunner;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo27512d2 = {"onFinish", "", "job", "Lkotlinx/coroutines/Job;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SingleRunner$Holder", mo28222f = "SingleRunner.kt", mo28223i = {0, 0, 0}, mo28224l = {140}, mo28225m = "onFinish", mo28226n = {"this", "job", "$this$withLock$iv"}, mo28227s = {"L$0", "L$1", "L$2"})
/* compiled from: SingleRunner.kt */
final class SingleRunner$Holder$onFinish$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleRunner.Holder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleRunner$Holder$onFinish$1(SingleRunner.Holder holder, Continuation continuation) {
        super(continuation);
        this.this$0 = holder;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onFinish((Job) null, this);
    }
}
