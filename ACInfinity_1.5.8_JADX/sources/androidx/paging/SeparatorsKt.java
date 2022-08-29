package androidx.paging;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\n\u001ak\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00010\u000e2\b\u0010\u0004\u001a\u0004\u0018\u0001H\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0011\u001aI\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u000e2\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0012\u001ax\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00150\u0014\"\b\b\u0000\u0010\u0002*\u0002H\r\"\b\b\u0001\u0010\r*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172.\u0010\u0018\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001ae\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00020\u00012.\u0010\u0018\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, mo27512d2 = {"separatorPage", "Landroidx/paging/TransformablePage;", "T", "", "separator", "originalPageOffsets", "", "hintOriginalPageOffset", "", "hintOriginalIndex", "(Ljava/lang/Object;[III)Landroidx/paging/TransformablePage;", "addSeparatorPage", "", "R", "", "adjacentPageBefore", "adjacentPageAfter", "(Ljava/util/List;Ljava/lang/Object;Landroidx/paging/TransformablePage;Landroidx/paging/TransformablePage;II)V", "(Ljava/util/List;Ljava/lang/Object;[III)V", "insertEventSeparators", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "generator", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "insertInternalSeparators", "(Landroidx/paging/TransformablePage;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: Separators.kt */
public final class SeparatorsKt {
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0087, code lost:
        r14 = (java.lang.Integer) kotlin.collections.CollectionsKt.first(r14);
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R, T extends R> java.lang.Object insertInternalSeparators(androidx.paging.TransformablePage<T> r12, kotlin.jvm.functions.Function3<? super T, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r13, kotlin.coroutines.Continuation<? super androidx.paging.TransformablePage<R>> r14) {
        /*
            boolean r0 = r14 instanceof androidx.paging.SeparatorsKt$insertInternalSeparators$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            androidx.paging.SeparatorsKt$insertInternalSeparators$1 r0 = (androidx.paging.SeparatorsKt$insertInternalSeparators$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            androidx.paging.SeparatorsKt$insertInternalSeparators$1 r0 = new androidx.paging.SeparatorsKt$insertInternalSeparators$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "null cannot be cast to non-null type androidx.paging.TransformablePage<R>"
            r4 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 != r4) goto L_0x0049
            int r12 = r0.I$1
            int r13 = r0.I$0
            java.lang.Object r2 = r0.L$4
            java.lang.Object r5 = r0.L$3
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r6 = r0.L$2
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function3 r7 = (kotlin.jvm.functions.Function3) r7
            java.lang.Object r8 = r0.L$0
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r1
            r1 = r0
            r0 = r7
            r7 = r6
            r6 = r5
            r5 = r10
            goto L_0x00df
        L_0x0049:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r14)
            java.util.List r14 = r12.getData()
            boolean r14 = r14.isEmpty()
            if (r14 == 0) goto L_0x0062
            java.util.Objects.requireNonNull(r12, r3)
            return r12
        L_0x0062:
            java.util.List r14 = r12.getData()
            int r14 = r14.size()
            int r14 = r14 + 4
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r14)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r14)
            java.util.List r14 = r12.getData()
            java.lang.Object r14 = kotlin.collections.CollectionsKt.first(r14)
            r2.add(r14)
            java.util.List r14 = r12.getHintOriginalIndices()
            if (r14 == 0) goto L_0x0094
            java.lang.Object r14 = kotlin.collections.CollectionsKt.first(r14)
            java.lang.Integer r14 = (java.lang.Integer) r14
            if (r14 == 0) goto L_0x0094
            int r14 = r14.intValue()
            goto L_0x0095
        L_0x0094:
            r14 = 0
        L_0x0095:
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)
            r5.add(r14)
            java.util.List r14 = r12.getData()
            int r14 = r14.size()
            r6 = r2
            r2 = r1
            r1 = r0
            r0 = r13
            r13 = r12
            r12 = r14
            r14 = 1
        L_0x00ab:
            if (r14 >= r12) goto L_0x00fc
            java.util.List r7 = r13.getData()
            java.lang.Object r7 = r7.get(r14)
            java.util.List r8 = r13.getData()
            int r9 = r14 + -1
            java.lang.Object r8 = r8.get(r9)
            r1.L$0 = r13
            r1.L$1 = r0
            r1.L$2 = r6
            r1.L$3 = r5
            r1.L$4 = r7
            r1.I$0 = r14
            r1.I$1 = r12
            r1.label = r4
            java.lang.Object r8 = r0.invoke(r8, r7, r1)
            if (r8 != r2) goto L_0x00d6
            return r2
        L_0x00d6:
            r10 = r8
            r8 = r13
            r13 = r14
            r14 = r10
            r11 = r5
            r5 = r2
            r2 = r7
            r7 = r6
            r6 = r11
        L_0x00df:
            if (r14 == 0) goto L_0x00eb
            r7.add(r14)
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13)
            r6.add(r14)
        L_0x00eb:
            r7.add(r2)
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13)
            r6.add(r14)
            int r14 = r13 + 1
            r2 = r5
            r5 = r6
            r6 = r7
            r13 = r8
            goto L_0x00ab
        L_0x00fc:
            int r12 = r6.size()
            java.util.List r14 = r13.getData()
            int r14 = r14.size()
            if (r12 != r14) goto L_0x010e
            java.util.Objects.requireNonNull(r13, r3)
            goto L_0x0120
        L_0x010e:
            androidx.paging.TransformablePage r12 = new androidx.paging.TransformablePage
            int[] r14 = r13.getOriginalPageOffsets()
            java.util.List r6 = (java.util.List) r6
            int r13 = r13.getHintOriginalPageOffset()
            java.util.List r5 = (java.util.List) r5
            r12.<init>(r14, r6, r13, r5)
            r13 = r12
        L_0x0120:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorsKt.insertInternalSeparators(androidx.paging.TransformablePage, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> TransformablePage<T> separatorPage(T t, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(t, "separator");
        Intrinsics.checkNotNullParameter(iArr, "originalPageOffsets");
        return new TransformablePage<>(iArr, CollectionsKt.listOf(t), i, CollectionsKt.listOf(Integer.valueOf(i2)));
    }

    public static final <T> void addSeparatorPage(List<TransformablePage<T>> list, T t, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "$this$addSeparatorPage");
        Intrinsics.checkNotNullParameter(iArr, "originalPageOffsets");
        if (t != null) {
            list.add(separatorPage(t, iArr, i, i2));
        }
    }

    public static final <R, T extends R> void addSeparatorPage(List<TransformablePage<R>> list, R r, TransformablePage<T> transformablePage, TransformablePage<T> transformablePage2, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "$this$addSeparatorPage");
        int[] iArr = null;
        int[] originalPageOffsets = transformablePage != null ? transformablePage.getOriginalPageOffsets() : null;
        if (transformablePage2 != null) {
            iArr = transformablePage2.getOriginalPageOffsets();
        }
        if (originalPageOffsets != null && iArr != null) {
            originalPageOffsets = CollectionsKt.toIntArray(CollectionsKt.sorted(ArraysKt.distinct(ArraysKt.plus(originalPageOffsets, iArr))));
        } else if (originalPageOffsets == null && iArr != null) {
            originalPageOffsets = iArr;
        } else if (originalPageOffsets == null || iArr != null) {
            throw new IllegalArgumentException("Separator page expected adjacentPageBefore or adjacentPageAfter, but both were null.");
        }
        addSeparatorPage(list, r, originalPageOffsets, i, i2);
    }

    public static final <T extends R, R> Flow<PageEvent<R>> insertEventSeparators(Flow<? extends PageEvent<T>> flow, TerminalSeparatorType terminalSeparatorType, Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(flow, "$this$insertEventSeparators");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(function3, "generator");
        return new SeparatorsKt$insertEventSeparators$$inlined$map$1(flow, new SeparatorState(terminalSeparatorType, new SeparatorsKt$insertEventSeparators$separatorState$1(function3, (Continuation) null)));
    }
}
