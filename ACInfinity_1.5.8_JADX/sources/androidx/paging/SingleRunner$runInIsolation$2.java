package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo27512d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SingleRunner$runInIsolation$2", mo28222f = "SingleRunner.kt", mo28223i = {0, 1}, mo28224l = {55, 59, 61, 61}, mo28225m = "invokeSuspend", mo28226n = {"myJob", "myJob"}, mo28227s = {"L$0", "L$0"})
/* compiled from: SingleRunner.kt */
final class SingleRunner$runInIsolation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ int $priority;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SingleRunner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleRunner$runInIsolation$2(SingleRunner singleRunner, int i, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = singleRunner;
        this.$priority = i;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        SingleRunner$runInIsolation$2 singleRunner$runInIsolation$2 = new SingleRunner$runInIsolation$2(this.this$0, this.$priority, this.$block, continuation);
        singleRunner$runInIsolation$2.L$0 = obj;
        return singleRunner$runInIsolation$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SingleRunner$runInIsolation$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x003c
            if (r1 == r5) goto L_0x0034
            if (r1 == r4) goto L_0x002a
            if (r1 == r3) goto L_0x0025
            if (r1 == r2) goto L_0x001c
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001c:
            java.lang.Object r0 = r8.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a2
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a3
        L_0x002a:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0032 }
            goto L_0x007e
        L_0x0032:
            r9 = move-exception
            goto L_0x0090
        L_0x0034:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0069
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            kotlin.coroutines.CoroutineContext r9 = r9.getCoroutineContext()
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.Key) r1
            kotlin.coroutines.CoroutineContext$Element r9 = r9.get(r1)
            if (r9 == 0) goto L_0x00a6
            kotlinx.coroutines.Job r9 = (kotlinx.coroutines.Job) r9
            androidx.paging.SingleRunner r1 = r8.this$0
            androidx.paging.SingleRunner$Holder r1 = r1.holder
            int r6 = r8.$priority
            r8.L$0 = r9
            r8.label = r5
            java.lang.Object r1 = r1.tryEnqueue(r6, r9, r8)
            if (r1 != r0) goto L_0x0066
            return r0
        L_0x0066:
            r7 = r1
            r1 = r9
            r9 = r7
        L_0x0069:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00a3
            kotlin.jvm.functions.Function1 r9 = r8.$block     // Catch:{ all -> 0x0032 }
            r8.L$0 = r1     // Catch:{ all -> 0x0032 }
            r8.label = r4     // Catch:{ all -> 0x0032 }
            java.lang.Object r9 = r9.invoke(r8)     // Catch:{ all -> 0x0032 }
            if (r9 != r0) goto L_0x007e
            return r0
        L_0x007e:
            androidx.paging.SingleRunner r9 = r8.this$0
            androidx.paging.SingleRunner$Holder r9 = r9.holder
            r2 = 0
            r8.L$0 = r2
            r8.label = r3
            java.lang.Object r9 = r9.onFinish(r1, r8)
            if (r9 != r0) goto L_0x00a3
            return r0
        L_0x0090:
            androidx.paging.SingleRunner r3 = r8.this$0
            androidx.paging.SingleRunner$Holder r3 = r3.holder
            r8.L$0 = r9
            r8.label = r2
            java.lang.Object r1 = r3.onFinish(r1, r8)
            if (r1 != r0) goto L_0x00a1
            return r0
        L_0x00a1:
            r0 = r9
        L_0x00a2:
            throw r0
        L_0x00a3:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00a6:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Internal error. coroutineScope should've created a job."
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner$runInIsolation$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
