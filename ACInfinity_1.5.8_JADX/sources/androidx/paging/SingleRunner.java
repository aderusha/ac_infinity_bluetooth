package androidx.paging;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0003\u000f\u0010\u0011B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J9\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo27512d2 = {"Landroidx/paging/SingleRunner;", "", "cancelPreviousInEqualPriority", "", "(Z)V", "holder", "Landroidx/paging/SingleRunner$Holder;", "runInIsolation", "", "priority", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CancelIsolatedRunnerException", "Companion", "Holder", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SingleRunner.kt */
public final class SingleRunner {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_PRIORITY = 0;
    /* access modifiers changed from: private */
    public final Holder holder;

    public SingleRunner() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    public SingleRunner(boolean z) {
        this.holder = new Holder(this, z);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SingleRunner(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object runInIsolation(int r5, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.paging.SingleRunner$runInIsolation$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.SingleRunner$runInIsolation$1 r0 = (androidx.paging.SingleRunner$runInIsolation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.SingleRunner$runInIsolation$1 r0 = new androidx.paging.SingleRunner$runInIsolation$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r5 = r0.L$0
            androidx.paging.SingleRunner r5 = (androidx.paging.SingleRunner) r5
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ CancelIsolatedRunnerException -> 0x002e }
            goto L_0x0058
        L_0x002e:
            r6 = move-exception
            goto L_0x0050
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.paging.SingleRunner$runInIsolation$2 r7 = new androidx.paging.SingleRunner$runInIsolation$2     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            r2 = 0
            r7.<init>(r4, r5, r6, r2)     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            r0.L$0 = r4     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            r0.label = r3     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r7, r0)     // Catch:{ CancelIsolatedRunnerException -> 0x004e }
            if (r5 != r1) goto L_0x0058
            return r1
        L_0x004e:
            r6 = move-exception
            r5 = r4
        L_0x0050:
            androidx.paging.SingleRunner r7 = r6.getRunner()
            androidx.paging.SingleRunner r5 = (androidx.paging.SingleRunner) r5
            if (r7 != r5) goto L_0x005b
        L_0x0058:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x005b:
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.runInIsolation(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object runInIsolation$default(SingleRunner singleRunner, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return singleRunner.runInIsolation(i, function1, continuation);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/SingleRunner$CancelIsolatedRunnerException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "runner", "Landroidx/paging/SingleRunner;", "(Landroidx/paging/SingleRunner;)V", "getRunner", "()Landroidx/paging/SingleRunner;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: SingleRunner.kt */
    private static final class CancelIsolatedRunnerException extends CancellationException {
        private final SingleRunner runner;

        public CancelIsolatedRunnerException(SingleRunner singleRunner) {
            Intrinsics.checkNotNullParameter(singleRunner, "runner");
            this.runner = singleRunner;
        }

        public final SingleRunner getRunner() {
            return this.runner;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo27512d2 = {"Landroidx/paging/SingleRunner$Holder;", "", "singleRunner", "Landroidx/paging/SingleRunner;", "cancelPreviousInEqualPriority", "", "(Landroidx/paging/SingleRunner;Z)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "previous", "Lkotlinx/coroutines/Job;", "previousPriority", "", "onFinish", "", "job", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryEnqueue", "priority", "(ILkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: SingleRunner.kt */
    private static final class Holder {
        private final boolean cancelPreviousInEqualPriority;
        private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);
        private Job previous;
        private int previousPriority;
        private final SingleRunner singleRunner;

        public Holder(SingleRunner singleRunner2, boolean z) {
            Intrinsics.checkNotNullParameter(singleRunner2, "singleRunner");
            this.singleRunner = singleRunner2;
            this.cancelPreviousInEqualPriority = z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x005a  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0087 A[Catch:{ all -> 0x003d }] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0088 A[Catch:{ all -> 0x003d }] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object tryEnqueue(int r10, kotlinx.coroutines.Job r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
            /*
                r9 = this;
                boolean r0 = r12 instanceof androidx.paging.SingleRunner$Holder$tryEnqueue$1
                if (r0 == 0) goto L_0x0014
                r0 = r12
                androidx.paging.SingleRunner$Holder$tryEnqueue$1 r0 = (androidx.paging.SingleRunner$Holder$tryEnqueue$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r12 = r0.label
                int r12 = r12 - r2
                r0.label = r12
                goto L_0x0019
            L_0x0014:
                androidx.paging.SingleRunner$Holder$tryEnqueue$1 r0 = new androidx.paging.SingleRunner$Holder$tryEnqueue$1
                r0.<init>(r9, r12)
            L_0x0019:
                java.lang.Object r12 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L_0x005a
                if (r2 == r5) goto L_0x0048
                if (r2 != r3) goto L_0x0040
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$Holder r0 = (androidx.paging.SingleRunner.Holder) r0
                kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003d }
                goto L_0x00ad
            L_0x003d:
                r10 = move-exception
                goto L_0x00bb
            L_0x0040:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L_0x0048:
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r2 = r0.L$1
                kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
                java.lang.Object r6 = r0.L$0
                androidx.paging.SingleRunner$Holder r6 = (androidx.paging.SingleRunner.Holder) r6
                kotlin.ResultKt.throwOnFailure(r12)
                goto L_0x0073
            L_0x005a:
                kotlin.ResultKt.throwOnFailure(r12)
                kotlinx.coroutines.sync.Mutex r12 = r9.mutex
                r0.L$0 = r9
                r0.L$1 = r11
                r0.L$2 = r12
                r0.I$0 = r10
                r0.label = r5
                java.lang.Object r2 = r12.lock(r4, r0)
                if (r2 != r1) goto L_0x0070
                return r1
            L_0x0070:
                r6 = r9
                r2 = r11
                r11 = r12
            L_0x0073:
                kotlinx.coroutines.Job r12 = r6.previous     // Catch:{ all -> 0x003d }
                if (r12 == 0) goto L_0x008a
                boolean r7 = r12.isActive()     // Catch:{ all -> 0x003d }
                if (r7 == 0) goto L_0x008a
                int r7 = r6.previousPriority     // Catch:{ all -> 0x003d }
                if (r7 < r10) goto L_0x008a
                if (r7 != r10) goto L_0x0088
                boolean r7 = r6.cancelPreviousInEqualPriority     // Catch:{ all -> 0x003d }
                if (r7 == 0) goto L_0x0088
                goto L_0x008a
            L_0x0088:
                r5 = 0
                goto L_0x00b3
            L_0x008a:
                if (r12 == 0) goto L_0x0098
                androidx.paging.SingleRunner$CancelIsolatedRunnerException r7 = new androidx.paging.SingleRunner$CancelIsolatedRunnerException     // Catch:{ all -> 0x003d }
                androidx.paging.SingleRunner r8 = r6.singleRunner     // Catch:{ all -> 0x003d }
                r7.<init>(r8)     // Catch:{ all -> 0x003d }
                java.util.concurrent.CancellationException r7 = (java.util.concurrent.CancellationException) r7     // Catch:{ all -> 0x003d }
                r12.cancel((java.util.concurrent.CancellationException) r7)     // Catch:{ all -> 0x003d }
            L_0x0098:
                if (r12 == 0) goto L_0x00af
                r0.L$0 = r6     // Catch:{ all -> 0x003d }
                r0.L$1 = r2     // Catch:{ all -> 0x003d }
                r0.L$2 = r11     // Catch:{ all -> 0x003d }
                r0.I$0 = r10     // Catch:{ all -> 0x003d }
                r0.label = r3     // Catch:{ all -> 0x003d }
                java.lang.Object r12 = r12.join(r0)     // Catch:{ all -> 0x003d }
                if (r12 != r1) goto L_0x00ab
                return r1
            L_0x00ab:
                r1 = r2
                r0 = r6
            L_0x00ad:
                r6 = r0
                r2 = r1
            L_0x00af:
                r6.previous = r2     // Catch:{ all -> 0x003d }
                r6.previousPriority = r10     // Catch:{ all -> 0x003d }
            L_0x00b3:
                java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)     // Catch:{ all -> 0x003d }
                r11.unlock(r4)
                return r10
            L_0x00bb:
                r11.unlock(r4)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.Holder.tryEnqueue(int, kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x005a A[Catch:{ all -> 0x0067 }] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object onFinish(kotlinx.coroutines.Job r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.SingleRunner$Holder$onFinish$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                androidx.paging.SingleRunner$Holder$onFinish$1 r0 = (androidx.paging.SingleRunner$Holder$onFinish$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                androidx.paging.SingleRunner$Holder$onFinish$1 r0 = new androidx.paging.SingleRunner$Holder$onFinish$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                r4 = 0
                if (r2 == 0) goto L_0x0041
                if (r2 != r3) goto L_0x0039
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$Holder r0 = (androidx.paging.SingleRunner.Holder) r0
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                r6 = r1
                goto L_0x0056
            L_0x0039:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0041:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.sync.Mutex r7 = r5.mutex
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r3
                java.lang.Object r0 = r7.lock(r4, r0)
                if (r0 != r1) goto L_0x0055
                return r1
            L_0x0055:
                r0 = r5
            L_0x0056:
                kotlinx.coroutines.Job r1 = r0.previous     // Catch:{ all -> 0x0067 }
                if (r6 != r1) goto L_0x005f
                r6 = r4
                kotlinx.coroutines.Job r6 = (kotlinx.coroutines.Job) r6     // Catch:{ all -> 0x0067 }
                r0.previous = r6     // Catch:{ all -> 0x0067 }
            L_0x005f:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0067 }
                r7.unlock(r4)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L_0x0067:
                r6 = move-exception
                r7.unlock(r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.Holder.onFinish(kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo27512d2 = {"Landroidx/paging/SingleRunner$Companion;", "", "()V", "DEFAULT_PRIORITY", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: SingleRunner.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
