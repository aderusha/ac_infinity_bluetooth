package androidx.paging;

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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.ContiguousPagedList$deferBoundaryCallbacks$1", mo28222f = "ContiguousPagedList.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: ContiguousPagedList.kt */
final class ContiguousPagedList$deferBoundaryCallbacks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $deferBegin;
    final /* synthetic */ boolean $deferEmpty;
    final /* synthetic */ boolean $deferEnd;
    int label;
    final /* synthetic */ ContiguousPagedList this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContiguousPagedList$deferBoundaryCallbacks$1(ContiguousPagedList contiguousPagedList, boolean z, boolean z2, boolean z3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contiguousPagedList;
        this.$deferEmpty = z;
        this.$deferBegin = z2;
        this.$deferEnd = z3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new ContiguousPagedList$deferBoundaryCallbacks$1(this.this$0, this.$deferEmpty, this.$deferBegin, this.$deferEnd, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContiguousPagedList$deferBoundaryCallbacks$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$deferEmpty) {
                this.this$0.getBoundaryCallback$paging_common().onZeroItemsLoaded();
            }
            if (this.$deferBegin) {
                this.this$0.boundaryCallbackBeginDeferred = true;
            }
            if (this.$deferEnd) {
                this.this$0.boundaryCallbackEndDeferred = true;
            }
            this.this$0.tryDispatchBoundaryCallbacks(false);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
