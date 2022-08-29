package androidx.paging;

import androidx.paging.PageFetcherSnapshotState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Mutex;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$2", mo28222f = "PageFetcherSnapshot.kt", mo28223i = {0, 0}, mo28224l = {595, 218}, mo28225m = "invokeSuspend", mo28226n = {"this_$iv", "$this$withLock$iv$iv"}, mo28227s = {"L$0", "L$1"})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$startConsumingHints$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$startConsumingHints$2(PageFetcherSnapshot pageFetcherSnapshot, Continuation continuation) {
        super(2, continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PageFetcherSnapshot$startConsumingHints$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PageFetcherSnapshot$startConsumingHints$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: finally extract failed */
    public final Object invokeSuspend(Object obj) {
        PageFetcherSnapshotState.Holder holder;
        Mutex mutex;
        PageFetcherSnapshot pageFetcherSnapshot;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            pageFetcherSnapshot = this.this$0;
            holder = pageFetcherSnapshot.stateHolder;
            Mutex access$getLock$p = holder.lock;
            this.L$0 = holder;
            this.L$1 = access$getLock$p;
            this.L$2 = pageFetcherSnapshot;
            this.label = 1;
            if (access$getLock$p.lock((Object) null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = access$getLock$p;
        } else if (i == 1) {
            pageFetcherSnapshot = (PageFetcherSnapshot) this.L$2;
            mutex = (Mutex) this.L$1;
            holder = (PageFetcherSnapshotState.Holder) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            Flow<Integer> consumePrependGenerationIdAsFlow = holder.state.consumePrependGenerationIdAsFlow();
            mutex.unlock((Object) null);
            LoadType loadType = LoadType.PREPEND;
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 2;
            if (pageFetcherSnapshot.collectAsGenerationalViewportHints(consumePrependGenerationIdAsFlow, loadType, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock((Object) null);
            throw th;
        }
    }
}
