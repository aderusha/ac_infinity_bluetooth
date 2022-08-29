package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0007H@"}, mo27512d2 = {"onEvent", "", "R", "T", "event", "Landroidx/paging/PageEvent;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SeparatorState", mo28222f = "Separators.kt", mo28223i = {0, 1}, mo28224l = {213, 215}, mo28225m = "onEvent", mo28226n = {"this", "this"}, mo28227s = {"L$0", "L$0"})
/* compiled from: Separators.kt */
final class SeparatorState$onEvent$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SeparatorState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SeparatorState$onEvent$1(SeparatorState separatorState, Continuation continuation) {
        super(continuation);
        this.this$0 = separatorState;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onEvent((PageEvent) null, this);
    }
}
