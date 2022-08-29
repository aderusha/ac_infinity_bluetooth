package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(mo27511d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a#\u0010\u0002\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001ao\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0004*\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u000628\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00000\bH\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\"\u001a\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo27512d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "close", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/internal/Segment;", "S", "", "id", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "prev", "createNewSegment", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "findSegmentInternal", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "getCLOSED$annotations", "()V", "", "POINTERS_SHIFT", "I", "kotlinx-coroutines-core"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: ConcurrentLinkedList.kt */
public final class ConcurrentLinkedListKt {
    /* access modifiers changed from: private */
    public static final Symbol CLOSED = new Symbol("CLOSED");
    private static final int POINTERS_SHIFT = 16;

    private static /* synthetic */ void getCLOSED$annotations() {
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super S, ? extends S>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <S extends kotlinx.coroutines.internal.Segment<S>> java.lang.Object findSegmentInternal(S r4, long r5, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super S, ? extends S> r7) {
        /*
        L_0x0000:
            long r0 = r4.getId()
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 < 0) goto L_0x0014
            boolean r0 = r4.getRemoved()
            if (r0 == 0) goto L_0x000f
            goto L_0x0014
        L_0x000f:
            java.lang.Object r4 = kotlinx.coroutines.internal.SegmentOrClosed.m2483constructorimpl(r4)
            return r4
        L_0x0014:
            r0 = r4
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r0
            java.lang.Object r0 = r0.getNextOrClosed()
            kotlinx.coroutines.internal.Symbol r1 = CLOSED
            if (r0 != r1) goto L_0x002a
            kotlinx.coroutines.internal.Symbol r4 = CLOSED
            java.lang.Object r4 = kotlinx.coroutines.internal.SegmentOrClosed.m2483constructorimpl(r4)
            return r4
        L_0x002a:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r0
            kotlinx.coroutines.internal.Segment r0 = (kotlinx.coroutines.internal.Segment) r0
            if (r0 == 0) goto L_0x0032
        L_0x0030:
            r4 = r0
            goto L_0x0000
        L_0x0032:
            long r0 = r4.getId()
            r2 = 1
            long r0 = r0 + r2
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Object r0 = r7.invoke(r0, r4)
            kotlinx.coroutines.internal.Segment r0 = (kotlinx.coroutines.internal.Segment) r0
            r1 = r0
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r1
            boolean r1 = r4.trySetNext(r1)
            if (r1 == 0) goto L_0x0000
            boolean r1 = r4.getRemoved()
            if (r1 == 0) goto L_0x0030
            r4.remove()
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ConcurrentLinkedListKt.findSegmentInternal(kotlinx.coroutines.internal.Segment, long, kotlin.jvm.functions.Function2):java.lang.Object");
    }

    public static final <N extends ConcurrentLinkedListNode<N>> N close(N n) {
        while (true) {
            N access$getNextOrClosed = n.getNextOrClosed();
            if (access$getNextOrClosed == CLOSED) {
                return n;
            }
            N n2 = (ConcurrentLinkedListNode) access$getNextOrClosed;
            if (n2 != null) {
                n = n2;
            } else if (n.markAsClosed()) {
                return n;
            }
        }
    }
}
