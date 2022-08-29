package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@"}, mo27512d2 = {"emitSource", "", "T", "source", "Landroidx/lifecycle/LiveData;", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/DisposableHandle;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.CoroutineLiveData", mo28222f = "CoroutineLiveData.kt", mo28223i = {0, 0, 1, 1}, mo28224l = {227, 228}, mo28225m = "emitSource$lifecycle_livedata_ktx_release", mo28226n = {"this", "source", "this", "source"}, mo28227s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: CoroutineLiveData.kt */
final class CoroutineLiveData$emitSource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CoroutineLiveData this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineLiveData$emitSource$1(CoroutineLiveData coroutineLiveData, Continuation continuation) {
        super(continuation);
        this.this$0 = coroutineLiveData;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emitSource$lifecycle_livedata_ktx_release((LiveData) null, this);
    }
}
