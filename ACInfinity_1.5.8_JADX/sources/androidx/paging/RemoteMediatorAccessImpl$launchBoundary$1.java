package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1", mo28222f = "RemoteMediatorAccessor.kt", mo28223i = {}, mo28224l = {337}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$launchBoundary$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteMediatorAccessImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$launchBoundary$1(RemoteMediatorAccessImpl remoteMediatorAccessImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteMediatorAccessImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new RemoteMediatorAccessImpl$launchBoundary$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RemoteMediatorAccessImpl$launchBoundary$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.isolationRunner.runInIsolation(1, new C05571(this, (Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1", mo28222f = "RemoteMediatorAccessor.kt", mo28223i = {0}, mo28224l = {342}, mo28225m = "invokeSuspend", mo28226n = {"loadType"}, mo28227s = {"L$0"})
    /* renamed from: androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1 */
    /* compiled from: RemoteMediatorAccessor.kt */
    static final class C05571 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ RemoteMediatorAccessImpl$launchBoundary$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            return new C05571(this.this$0, continuation);
        }

        public final Object invoke(Object obj) {
            return ((C05571) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0036  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 1
                if (r1 == 0) goto L_0x001e
                if (r1 != r2) goto L_0x0016
                java.lang.Object r1 = r7.L$0
                androidx.paging.LoadType r1 = (androidx.paging.LoadType) r1
                kotlin.ResultKt.throwOnFailure(r8)
                r3 = r1
                r1 = r0
                r0 = r7
                goto L_0x0059
            L_0x0016:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L_0x001e:
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r7
            L_0x0022:
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1 r1 = r8.this$0
                androidx.paging.RemoteMediatorAccessImpl r1 = r1.this$0
                androidx.paging.AccessorStateHolder r1 = r1.accessorState
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1 r3 = androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1.C05571.C05581.INSTANCE
                kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                java.lang.Object r1 = r1.use(r3)
                kotlin.Pair r1 = (kotlin.Pair) r1
                if (r1 == 0) goto L_0x008b
                java.lang.Object r3 = r1.component1()
                androidx.paging.LoadType r3 = (androidx.paging.LoadType) r3
                java.lang.Object r1 = r1.component2()
                androidx.paging.PagingState r1 = (androidx.paging.PagingState) r1
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1 r4 = r8.this$0
                androidx.paging.RemoteMediatorAccessImpl r4 = r4.this$0
                androidx.paging.RemoteMediator r4 = r4.remoteMediator
                r8.L$0 = r3
                r8.label = r2
                java.lang.Object r1 = r4.load(r3, r1, r8)
                if (r1 != r0) goto L_0x0055
                return r0
            L_0x0055:
                r6 = r0
                r0 = r8
                r8 = r1
                r1 = r6
            L_0x0059:
                androidx.paging.RemoteMediator$MediatorResult r8 = (androidx.paging.RemoteMediator.MediatorResult) r8
                boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Success
                if (r4 == 0) goto L_0x0072
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1 r4 = r0.this$0
                androidx.paging.RemoteMediatorAccessImpl r4 = r4.this$0
                androidx.paging.AccessorStateHolder r4 = r4.accessorState
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$2 r5 = new androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$2
                r5.<init>(r3, r8)
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
                r4.use(r5)
                goto L_0x0088
            L_0x0072:
                boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Error
                if (r4 == 0) goto L_0x0088
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1 r4 = r0.this$0
                androidx.paging.RemoteMediatorAccessImpl r4 = r4.this$0
                androidx.paging.AccessorStateHolder r4 = r4.accessorState
                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$3 r5 = new androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$3
                r5.<init>(r3, r8)
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
                r4.use(r5)
            L_0x0088:
                r8 = r0
                r0 = r1
                goto L_0x0022
            L_0x008b:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1.C05571.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
