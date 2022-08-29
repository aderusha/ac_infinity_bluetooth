package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0002*\u00020\u00012(\u0010\u0003\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00042\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b0\u0005H@"}, mo27512d2 = {"flatMap", "", "T", "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "continuation", "Landroidx/paging/PageEvent;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageEvent$Insert", mo28222f = "PageEvent.kt", mo28223i = {0, 0, 0, 0, 0, 0, 0, 0}, mo28224l = {86}, mo28225m = "flatMap", mo28226n = {"transform", "this_$iv$iv", "destination$iv$iv$iv", "it", "originalIndices", "data", "index$iv", "index"}, mo28227s = {"L$0", "L$1", "L$3", "L$5", "L$6", "L$7", "I$0", "I$1"})
/* compiled from: PageEvent.kt */
final class PageEvent$Insert$flatMap$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageEvent.Insert this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageEvent$Insert$flatMap$1(PageEvent.Insert insert, Continuation continuation) {
        super(continuation);
        this.this$0 = insert;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.flatMap((Function2) null, this);
    }
}
