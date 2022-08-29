package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0007H@"}, mo27512d2 = {"onInsert", "", "R", "T", "event", "Landroidx/paging/PageEvent$Insert;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SeparatorState", mo28222f = "Separators.kt", mo28223i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9}, mo28224l = {303, 366, 378, 384, 396, 405, 427, 436, 449, 460}, mo28225m = "onInsert", mo28226n = {"this", "event", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "lastStash", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "lastNonEmptyPage", "eventTerminatesEnd", "eventEmpty", "this", "event", "outList", "stashOutList", "pageBefore"}, mo28227s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Separators.kt */
final class SeparatorState$onInsert$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SeparatorState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SeparatorState$onInsert$1(SeparatorState separatorState, Continuation continuation) {
        super(continuation);
        this.this$0 = separatorState;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onInsert((PageEvent.Insert) null, this);
    }
}
