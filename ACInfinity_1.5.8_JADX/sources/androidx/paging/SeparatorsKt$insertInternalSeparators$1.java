package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042.\u0010\u0005\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0007H@"}, mo27512d2 = {"insertInternalSeparators", "", "R", "T", "Landroidx/paging/TransformablePage;", "generator", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "continuation"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SeparatorsKt", mo28222f = "Separators.kt", mo28223i = {0, 0, 0, 0, 0}, mo28224l = {82}, mo28225m = "insertInternalSeparators", mo28226n = {"$this$insertInternalSeparators", "generator", "outputList", "outputIndices", "item"}, mo28227s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Separators.kt */
final class SeparatorsKt$insertInternalSeparators$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    SeparatorsKt$insertInternalSeparators$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SeparatorsKt.insertInternalSeparators((TransformablePage) null, (Function3) null, this);
    }
}
