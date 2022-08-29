package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a)\u0010\u0004\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0005\u001a\u0002H\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a#\u0010\u000b\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a!\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a5\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u0001H\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a!\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a)\u0010\u0012\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0005\u001a\u0002H\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0013\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a!\u0010\u0014\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo27512d2 = {"awaitFirst", "T", "Lorg/reactivestreams/Publisher;", "(Lorg/reactivestreams/Publisher;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstOrDefault", "default", "(Lorg/reactivestreams/Publisher;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstOrElse", "defaultValue", "Lkotlin/Function0;", "(Lorg/reactivestreams/Publisher;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstOrNull", "awaitLast", "awaitOne", "mode", "Lkotlinx/coroutines/reactive/Mode;", "(Lorg/reactivestreams/Publisher;Lkotlinx/coroutines/reactive/Mode;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSingle", "awaitSingleOrDefault", "awaitSingleOrElse", "awaitSingleOrNull", "kotlinx-coroutines-reactive"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: Await.kt */
public final class AwaitKt {

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Mode.FIRST.ordinal()] = 1;
            iArr[Mode.FIRST_OR_DEFAULT.ordinal()] = 2;
            iArr[Mode.LAST.ordinal()] = 3;
            iArr[Mode.SINGLE.ordinal()] = 4;
            iArr[Mode.SINGLE_OR_DEFAULT.ordinal()] = 5;
        }
    }

    public static final <T> Object awaitFirst(Publisher<T> publisher, Continuation<? super T> continuation) {
        return awaitOne$default(publisher, Mode.FIRST, (Object) null, continuation, 2, (Object) null);
    }

    public static final <T> Object awaitFirstOrDefault(Publisher<T> publisher, T t, Continuation<? super T> continuation) {
        return awaitOne(publisher, Mode.FIRST_OR_DEFAULT, t, continuation);
    }

    public static final <T> Object awaitFirstOrNull(Publisher<T> publisher, Continuation<? super T> continuation) {
        return awaitOne$default(publisher, Mode.FIRST_OR_DEFAULT, (Object) null, continuation, 2, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: kotlin.jvm.functions.Function0<? extends T>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object awaitFirstOrElse(org.reactivestreams.Publisher<T> r7, kotlin.jvm.functions.Function0<? extends T> r8, kotlin.coroutines.Continuation<? super T> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.reactive.AwaitKt$awaitFirstOrElse$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.reactive.AwaitKt$awaitFirstOrElse$1 r0 = (kotlinx.coroutines.reactive.AwaitKt$awaitFirstOrElse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.AwaitKt$awaitFirstOrElse$1 r0 = new kotlinx.coroutines.reactive.AwaitKt$awaitFirstOrElse$1
            r0.<init>(r9)
        L_0x0019:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0038
            if (r1 != r2) goto L_0x0030
            java.lang.Object r7 = r4.L$0
            r8 = r7
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x004d
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.reactive.Mode r9 = kotlinx.coroutines.reactive.Mode.FIRST_OR_DEFAULT
            r3 = 0
            r5 = 2
            r6 = 0
            r4.L$0 = r8
            r4.label = r2
            r1 = r7
            r2 = r9
            java.lang.Object r9 = awaitOne$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r0) goto L_0x004d
            return r0
        L_0x004d:
            if (r9 == 0) goto L_0x0050
            goto L_0x0054
        L_0x0050:
            java.lang.Object r9 = r8.invoke()
        L_0x0054:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.AwaitKt.awaitFirstOrElse(org.reactivestreams.Publisher, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> Object awaitLast(Publisher<T> publisher, Continuation<? super T> continuation) {
        return awaitOne$default(publisher, Mode.LAST, (Object) null, continuation, 2, (Object) null);
    }

    public static final <T> Object awaitSingle(Publisher<T> publisher, Continuation<? super T> continuation) {
        return awaitOne$default(publisher, Mode.SINGLE, (Object) null, continuation, 2, (Object) null);
    }

    public static final <T> Object awaitSingleOrDefault(Publisher<T> publisher, T t, Continuation<? super T> continuation) {
        return awaitOne(publisher, Mode.SINGLE_OR_DEFAULT, t, continuation);
    }

    public static final <T> Object awaitSingleOrNull(Publisher<T> publisher, Continuation<? super T> continuation) {
        return awaitOne$default(publisher, Mode.SINGLE_OR_DEFAULT, (Object) null, continuation, 2, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: kotlin.jvm.functions.Function0<? extends T>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object awaitSingleOrElse(org.reactivestreams.Publisher<T> r7, kotlin.jvm.functions.Function0<? extends T> r8, kotlin.coroutines.Continuation<? super T> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.reactive.AwaitKt$awaitSingleOrElse$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.reactive.AwaitKt$awaitSingleOrElse$1 r0 = (kotlinx.coroutines.reactive.AwaitKt$awaitSingleOrElse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.AwaitKt$awaitSingleOrElse$1 r0 = new kotlinx.coroutines.reactive.AwaitKt$awaitSingleOrElse$1
            r0.<init>(r9)
        L_0x0019:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0038
            if (r1 != r2) goto L_0x0030
            java.lang.Object r7 = r4.L$0
            r8 = r7
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x004d
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.reactive.Mode r9 = kotlinx.coroutines.reactive.Mode.SINGLE_OR_DEFAULT
            r3 = 0
            r5 = 2
            r6 = 0
            r4.L$0 = r8
            r4.label = r2
            r1 = r7
            r2 = r9
            java.lang.Object r9 = awaitOne$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r0) goto L_0x004d
            return r0
        L_0x004d:
            if (r9 == 0) goto L_0x0050
            goto L_0x0054
        L_0x0050:
            java.lang.Object r9 = r8.invoke()
        L_0x0054:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.AwaitKt.awaitSingleOrElse(org.reactivestreams.Publisher, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object awaitOne$default(Publisher publisher, Mode mode, Object obj, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        return awaitOne(publisher, mode, obj, continuation);
    }

    static final /* synthetic */ <T> Object awaitOne(Publisher<T> publisher, Mode mode, T t, Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReactiveFlowKt.injectCoroutineContext(publisher, cancellableContinuation.getContext()).subscribe(new AwaitKt$awaitOne$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuation, publisher, mode, t));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
