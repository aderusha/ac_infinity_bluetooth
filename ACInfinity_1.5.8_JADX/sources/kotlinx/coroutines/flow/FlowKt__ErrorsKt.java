package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

@Metadata(mo27511d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012B\u0010\u0003\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\b\u0016\u001a\u001b\u0010\u0017\u001a\u00020\u0013*\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\b\u0019\u001aB\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001a>\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001ac\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020!23\b\u0002\u0010\u001c\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\"ø\u0001\u0000¢\u0006\u0002\u0010#\u001a}\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012W\u0010\u001c\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0%¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010'*\\\b\u0007\u0010(\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001d2\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dB6\b)\u0012\n\b*\u0012\u0006\b\n0+8,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u001c\b/\u0012\u0018\b\u000bB\u0014\b0\u0012\u0006\b1\u0012\u0002\b\f\u0012\b\b2\u0012\u0004\b\b(3\u0002\u0004\n\u0002\b\u0019¨\u00064"}, mo27512d2 = {"catch", "Lkotlinx/coroutines/flow/Flow;", "T", "action", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "catchImpl", "collector", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCancellationCause", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "isSameExceptionAs", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "onErrorCollect", "fallback", "predicate", "Lkotlin/Function1;", "retry", "retries", "", "", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "ExceptionPredicate", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "ERROR", "message", "Use (Throwable) -> Boolean functional type", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "(Throwable) -> Boolean", "kotlinx-coroutines-core"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 48, mo27517xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Errors.kt */
final /* synthetic */ class FlowKt__ErrorsKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use (Throwable) -> Boolean functional type", replaceWith = @ReplaceWith(expression = "(Throwable) -> Boolean", imports = {}))
    public static /* synthetic */ void ExceptionPredicate$annotations() {
    }

    public static /* synthetic */ Flow onErrorCollect$default(Flow flow, Flow flow2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = FlowKt__ErrorsKt$onErrorCollect$1.INSTANCE;
        }
        return FlowKt.onErrorCollect(flow, flow2, function1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", imports = {}))
    public static final <T> Flow<T> onErrorCollect(Flow<? extends T> flow, Flow<? extends T> flow2, Function1<? super Throwable, Boolean> function1) {
        return FlowKt.m2461catch(flow, new FlowKt__ErrorsKt$onErrorCollect$2(function1, flow2, (Continuation<? super FlowKt__ErrorsKt$onErrorCollect$2>) null));
    }

    public static /* synthetic */ Flow retry$default(Flow flow, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        if ((i & 2) != 0) {
            function2 = new FlowKt__ErrorsKt$retry$1((Continuation<? super FlowKt__ErrorsKt$retry$1>) null);
        }
        return FlowKt.retry(flow, j, (Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object>) function2);
    }

    public static final <T> Flow<T> retry(Flow<? extends T> flow, long j, Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        if (j > 0) {
            return FlowKt.retryWhen(flow, new FlowKt__ErrorsKt$retry$3(j, function2, (Continuation<? super FlowKt__ErrorsKt$retry$3>) null));
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Expected positive amount of retries, but had ", Long.valueOf(j)).toString());
    }

    public static /* synthetic */ Flow retry$default(Flow flow, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            function1 = FlowKt__ErrorsKt$retry$4.INSTANCE;
        }
        return retry(flow, i, function1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "binary compatibility with retries: Int preview version")
    public static final /* synthetic */ Flow retry(Flow flow, int i, Function1 function1) {
        if (i > 0) {
            return FlowKt.retryWhen(flow, new FlowKt__ErrorsKt$retry$6(function1, i, (Continuation<? super FlowKt__ErrorsKt$retry$6>) null));
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Expected positive amount of retries, but had ", Integer.valueOf(i)).toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object catchImpl(kotlinx.coroutines.flow.Flow<? extends T> r4, kotlinx.coroutines.flow.FlowCollector<? super T> r5, kotlin.coroutines.Continuation<? super java.lang.Throwable> r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x002e }
            goto L_0x0052
        L_0x002e:
            r5 = move-exception
            goto L_0x0056
        L_0x0030:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1 r2 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1     // Catch:{ all -> 0x0054 }
            r2.<init>(r5, r6)     // Catch:{ all -> 0x0054 }
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2     // Catch:{ all -> 0x0054 }
            r0.L$0 = r6     // Catch:{ all -> 0x0054 }
            r0.label = r3     // Catch:{ all -> 0x0054 }
            java.lang.Object r4 = r4.collect(r2, r0)     // Catch:{ all -> 0x0054 }
            if (r4 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r4 = 0
            return r4
        L_0x0054:
            r5 = move-exception
            r4 = r6
        L_0x0056:
            T r4 = r4.element
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            boolean r4 = isSameExceptionAs$FlowKt__ErrorsKt(r5, r4)
            if (r4 != 0) goto L_0x006b
            kotlin.coroutines.CoroutineContext r4 = r0.getContext()
            boolean r4 = isCancellationCause$FlowKt__ErrorsKt(r5, r4)
            if (r4 != 0) goto L_0x006b
            return r5
        L_0x006b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl(kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable th, CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job == null || !job.isCancelled()) {
            return false;
        }
        return isSameExceptionAs$FlowKt__ErrorsKt(th, job.getCancellationException());
    }

    /* renamed from: catch  reason: not valid java name */
    public static final <T> Flow<T> m2468catch(Flow<? extends T> flow, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(flow, function3);
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> flow, Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function4) {
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(flow, function4);
    }

    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable th, Throwable th2) {
        if (th2 != null) {
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                th2 = StackTraceRecoveryKt.unwrapImpl(th2);
            }
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                th = StackTraceRecoveryKt.unwrapImpl(th);
            }
            if (Intrinsics.areEqual((Object) th2, (Object) th)) {
                return true;
            }
        }
        return false;
    }
}
