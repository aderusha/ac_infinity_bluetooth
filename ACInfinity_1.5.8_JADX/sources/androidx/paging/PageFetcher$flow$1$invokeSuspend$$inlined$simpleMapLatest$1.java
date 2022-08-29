package androidx.paging;

import androidx.paging.PageFetcher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/FlowExtKt$simpleMapLatest$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1", mo28222f = "PageFetcher.kt", mo28223i = {}, mo28224l = {105}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: FlowExt.kt */
public final class PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1 extends SuspendLambda implements Function3<FlowCollector<? super PagingData<Value>>, PageFetcher.GenerationInfo<Key, Value>, Continuation<? super Unit>, Object> {
    final /* synthetic */ RemoteMediatorAccessor $remoteMediatorAccessor$inlined;
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PageFetcher$flow$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1(Continuation continuation, PageFetcher$flow$1 pageFetcher$flow$1, RemoteMediatorAccessor remoteMediatorAccessor) {
        super(3, continuation);
        this.this$0 = pageFetcher$flow$1;
        this.$remoteMediatorAccessor$inlined = remoteMediatorAccessor;
    }

    public final Continuation<Unit> create(FlowCollector<? super PagingData<Value>> flowCollector, PageFetcher.GenerationInfo<Key, Value> generationInfo, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNullParameter(flowCollector, "$this$create");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1 pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1 = new PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1(continuation, this.this$0, this.$remoteMediatorAccessor$inlined);
        pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1.L$0 = flowCollector;
        pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1.L$1 = generationInfo;
        return pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1) create((FlowCollector) obj, obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Continuation continuation = this;
            PageFetcher.GenerationInfo generationInfo = (PageFetcher.GenerationInfo) this.L$1;
            PagingData pagingData = new PagingData(this.this$0.this$0.injectRemoteEvents(generationInfo.getSnapshot(), this.$remoteMediatorAccessor$inlined), new PageFetcher.PagerUiReceiver(this.this$0.this$0, generationInfo.getSnapshot(), this.this$0.this$0.retryEvents));
            this.label = 1;
            if (((FlowCollector) this.L$0).emit(pagingData, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
