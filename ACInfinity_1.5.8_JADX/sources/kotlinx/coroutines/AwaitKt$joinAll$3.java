package kotlinx.coroutines;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.AwaitKt", mo28222f = "Await.kt", mo28223i = {}, mo28224l = {66}, mo28225m = "joinAll", mo28226n = {}, mo28227s = {})
/* compiled from: Await.kt */
final class AwaitKt$joinAll$3 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    AwaitKt$joinAll$3(Continuation<? super AwaitKt$joinAll$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AwaitKt.joinAll((Collection<? extends Job>) null, (Continuation<? super Unit>) this);
    }
}
