package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

@Metadata(mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 5, 1})
@DebugMetadata(mo28221c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", mo28222f = "SlidingWindow.kt", mo28223i = {}, mo28224l = {34, 40, 49, 55, 58}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SlidingWindowKt$windowedIterator$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00b9 A[SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0059
            if (r1 == r6) goto L_0x0046
            if (r1 == r5) goto L_0x0041
            if (r1 == r4) goto L_0x002f
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            goto L_0x0041
        L_0x0019:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0021:
            java.lang.Object r1 = r12.L$1
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            java.lang.Object r4 = r12.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            goto L_0x015d
        L_0x002f:
            java.lang.Object r1 = r12.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r5 = r12.L$1
            kotlin.collections.RingBuffer r5 = (kotlin.collections.RingBuffer) r5
            java.lang.Object r8 = r12.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            goto L_0x0128
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x017c
        L_0x0046:
            int r1 = r12.I$0
            java.lang.Object r2 = r12.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r12.L$1
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.lang.Object r4 = r12.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            goto L_0x00a8
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlin.sequences.SequenceScope r13 = (kotlin.sequences.SequenceScope) r13
            int r1 = r12.$size
            r8 = 1024(0x400, float:1.435E-42)
            int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r8)
            int r8 = r12.$step
            int r9 = r12.$size
            int r8 = r8 - r9
            if (r8 < 0) goto L_0x00de
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r1)
            r1 = 0
            java.util.Iterator r3 = r12.$iterator
            r4 = r13
            r13 = r12
            r11 = r3
            r3 = r2
            r2 = r11
        L_0x007c:
            boolean r9 = r2.hasNext()
            if (r9 == 0) goto L_0x00b9
            java.lang.Object r9 = r2.next()
            if (r1 <= 0) goto L_0x008b
            int r1 = r1 + -1
            goto L_0x007c
        L_0x008b:
            r3.add(r9)
            int r9 = r3.size()
            int r10 = r13.$size
            if (r9 != r10) goto L_0x007c
            r13.L$0 = r4
            r13.L$1 = r3
            r13.L$2 = r2
            r13.I$0 = r8
            r13.label = r6
            java.lang.Object r1 = r4.yield(r3, r13)
            if (r1 != r0) goto L_0x00a7
            return r0
        L_0x00a7:
            r1 = r8
        L_0x00a8:
            boolean r8 = r13.$reuseBuffer
            if (r8 == 0) goto L_0x00b0
            r3.clear()
            goto L_0x00b7
        L_0x00b0:
            java.util.ArrayList r3 = new java.util.ArrayList
            int r8 = r13.$size
            r3.<init>(r8)
        L_0x00b7:
            r8 = r1
            goto L_0x007c
        L_0x00b9:
            r1 = r3
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r6
            if (r1 == 0) goto L_0x017c
            boolean r1 = r13.$partialWindows
            if (r1 != 0) goto L_0x00cf
            int r1 = r3.size()
            int r2 = r13.$size
            if (r1 != r2) goto L_0x017c
        L_0x00cf:
            r13.L$0 = r7
            r13.L$1 = r7
            r13.L$2 = r7
            r13.label = r5
            java.lang.Object r13 = r4.yield(r3, r13)
            if (r13 != r0) goto L_0x017c
            return r0
        L_0x00de:
            kotlin.collections.RingBuffer r5 = new kotlin.collections.RingBuffer
            r5.<init>(r1)
            java.util.Iterator r1 = r12.$iterator
            r8 = r13
            r13 = r12
        L_0x00e7:
            boolean r9 = r1.hasNext()
            if (r9 == 0) goto L_0x012e
            java.lang.Object r9 = r1.next()
            r5.add(r9)
            boolean r9 = r5.isFull()
            if (r9 == 0) goto L_0x00e7
            int r9 = r5.size()
            int r10 = r13.$size
            if (r9 >= r10) goto L_0x0107
            kotlin.collections.RingBuffer r5 = r5.expanded(r10)
            goto L_0x00e7
        L_0x0107:
            boolean r9 = r13.$reuseBuffer
            if (r9 == 0) goto L_0x010f
            r9 = r5
            java.util.List r9 = (java.util.List) r9
            goto L_0x0119
        L_0x010f:
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = r5
            java.util.Collection r10 = (java.util.Collection) r10
            r9.<init>(r10)
            java.util.List r9 = (java.util.List) r9
        L_0x0119:
            r13.L$0 = r8
            r13.L$1 = r5
            r13.L$2 = r1
            r13.label = r4
            java.lang.Object r9 = r8.yield(r9, r13)
            if (r9 != r0) goto L_0x0128
            return r0
        L_0x0128:
            int r9 = r13.$step
            r5.removeFirst(r9)
            goto L_0x00e7
        L_0x012e:
            boolean r1 = r13.$partialWindows
            if (r1 == 0) goto L_0x017c
            r1 = r5
            r4 = r8
        L_0x0134:
            int r5 = r1.size()
            int r8 = r13.$step
            if (r5 <= r8) goto L_0x0163
            boolean r5 = r13.$reuseBuffer
            if (r5 == 0) goto L_0x0144
            r5 = r1
            java.util.List r5 = (java.util.List) r5
            goto L_0x014e
        L_0x0144:
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r1
            java.util.Collection r8 = (java.util.Collection) r8
            r5.<init>(r8)
            java.util.List r5 = (java.util.List) r5
        L_0x014e:
            r13.L$0 = r4
            r13.L$1 = r1
            r13.L$2 = r7
            r13.label = r3
            java.lang.Object r5 = r4.yield(r5, r13)
            if (r5 != r0) goto L_0x015d
            return r0
        L_0x015d:
            int r5 = r13.$step
            r1.removeFirst(r5)
            goto L_0x0134
        L_0x0163:
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            r3 = r3 ^ r6
            if (r3 == 0) goto L_0x017c
            r13.L$0 = r7
            r13.L$1 = r7
            r13.L$2 = r7
            r13.label = r2
            java.lang.Object r13 = r4.yield(r1, r13)
            if (r13 != r0) goto L_0x017c
            return r0
        L_0x017c:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
