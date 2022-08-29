package androidx.paging;

import androidx.paging.RemoteMediator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1", mo28222f = "RemoteMediatorAccessor.kt", mo28223i = {0}, mo28224l = {265}, mo28225m = "invokeSuspend", mo28226n = {"launchAppendPrepend"}, mo28227s = {"L$0"})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$launchRefresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RemoteMediatorAccessImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$launchRefresh$1(RemoteMediatorAccessImpl remoteMediatorAccessImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteMediatorAccessImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new RemoteMediatorAccessImpl$launchRefresh$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RemoteMediatorAccessImpl$launchRefresh$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            booleanRef2.element = false;
            this.L$0 = booleanRef2;
            this.label = 1;
            if (this.this$0.isolationRunner.runInIsolation(2, new C05611(this, (Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef = booleanRef2;
        } else if (i == 1) {
            booleanRef = (Ref.BooleanRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (booleanRef.element) {
            this.this$0.launchBoundary();
        }
        return Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1", mo28222f = "RemoteMediatorAccessor.kt", mo28223i = {}, mo28224l = {270}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1 */
    /* compiled from: RemoteMediatorAccessor.kt */
    static final class C05611 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ RemoteMediatorAccessImpl$launchRefresh$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            return new C05611(this.this$0, booleanRef2, continuation);
        }

        public final Object invoke(Object obj) {
            return ((C05611) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PagingState pagingState = (PagingState) this.this$0.this$0.accessorState.use(RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1.INSTANCE);
                if (pagingState != null) {
                    RemoteMediator access$getRemoteMediator$p = this.this$0.this$0.remoteMediator;
                    LoadType loadType = LoadType.REFRESH;
                    this.label = 1;
                    obj = access$getRemoteMediator$p.load(loadType, pagingState, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            RemoteMediator.MediatorResult mediatorResult = (RemoteMediator.MediatorResult) obj;
            Ref.BooleanRef booleanRef = booleanRef2;
            if (mediatorResult instanceof RemoteMediator.MediatorResult.Success) {
                z = ((Boolean) this.this$0.this$0.accessorState.use(new RemoteMediatorAccessImpl$launchRefresh$1$1$1$1(mediatorResult))).booleanValue();
            } else if (mediatorResult instanceof RemoteMediator.MediatorResult.Error) {
                z = ((Boolean) this.this$0.this$0.accessorState.use(new RemoteMediatorAccessImpl$launchRefresh$1$1$1$2(mediatorResult))).booleanValue();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            booleanRef.element = z;
            return Unit.INSTANCE;
        }
    }
}
