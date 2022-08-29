package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H@¢\u0006\u0004\b\u0007\u0010\b"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/GenerationalViewportHint;", "Key", "", "Value", "previous", "next", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$3", mo28222f = "PageFetcherSnapshot.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$collectAsGenerationalViewportHints$3 extends SuspendLambda implements Function3<GenerationalViewportHint, GenerationalViewportHint, Continuation<? super GenerationalViewportHint>, Object> {
    final /* synthetic */ LoadType $loadType;
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$collectAsGenerationalViewportHints$3(LoadType loadType, Continuation continuation) {
        super(3, continuation);
        this.$loadType = loadType;
    }

    public final Continuation<Unit> create(GenerationalViewportHint generationalViewportHint, GenerationalViewportHint generationalViewportHint2, Continuation<? super GenerationalViewportHint> continuation) {
        Intrinsics.checkNotNullParameter(generationalViewportHint, "previous");
        Intrinsics.checkNotNullParameter(generationalViewportHint2, "next");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        PageFetcherSnapshot$collectAsGenerationalViewportHints$3 pageFetcherSnapshot$collectAsGenerationalViewportHints$3 = new PageFetcherSnapshot$collectAsGenerationalViewportHints$3(this.$loadType, continuation);
        pageFetcherSnapshot$collectAsGenerationalViewportHints$3.L$0 = generationalViewportHint;
        pageFetcherSnapshot$collectAsGenerationalViewportHints$3.L$1 = generationalViewportHint2;
        return pageFetcherSnapshot$collectAsGenerationalViewportHints$3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((PageFetcherSnapshot$collectAsGenerationalViewportHints$3) create((GenerationalViewportHint) obj, (GenerationalViewportHint) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            GenerationalViewportHint generationalViewportHint = (GenerationalViewportHint) this.L$0;
            GenerationalViewportHint generationalViewportHint2 = (GenerationalViewportHint) this.L$1;
            return PageFetcherSnapshotKt.shouldPrioritizeOver(generationalViewportHint2, generationalViewportHint, this.$loadType) ? generationalViewportHint2 : generationalViewportHint;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
