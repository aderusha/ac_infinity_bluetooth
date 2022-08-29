package androidx.paging;

import androidx.paging.PagingSource;
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
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1", mo28222f = "LegacyPageFetcher.kt", mo28223i = {0}, mo28224l = {53}, mo28225m = "invokeSuspend", mo28226n = {"$this$launch"}, mo28227s = {"L$0"})
/* compiled from: LegacyPageFetcher.kt */
final class LegacyPageFetcher$scheduleLoad$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PagingSource.LoadParams $params;
    final /* synthetic */ LoadType $type;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ LegacyPageFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyPageFetcher$scheduleLoad$1(LegacyPageFetcher legacyPageFetcher, PagingSource.LoadParams loadParams, LoadType loadType, Continuation continuation) {
        super(2, continuation);
        this.this$0 = legacyPageFetcher;
        this.$params = loadParams;
        this.$type = loadType;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        LegacyPageFetcher$scheduleLoad$1 legacyPageFetcher$scheduleLoad$1 = new LegacyPageFetcher$scheduleLoad$1(this.this$0, this.$params, this.$type, continuation);
        legacyPageFetcher$scheduleLoad$1.L$0 = obj;
        return legacyPageFetcher$scheduleLoad$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyPageFetcher$scheduleLoad$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            PagingSource source = this.this$0.getSource();
            PagingSource.LoadParams loadParams = this.$params;
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object load = source.load(loadParams, this);
            if (load == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = load;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final PagingSource.LoadResult loadResult = (PagingSource.LoadResult) obj;
        if (this.this$0.getSource().getInvalid()) {
            this.this$0.detach();
            return Unit.INSTANCE;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.this$0.notifyDispatcher, (CoroutineStart) null, new C04761(this, (Continuation) null), 2, (Object) null);
        return Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1$1", mo28222f = "LegacyPageFetcher.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.LegacyPageFetcher$scheduleLoad$1$1 */
    /* compiled from: LegacyPageFetcher.kt */
    static final class C04761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ LegacyPageFetcher$scheduleLoad$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            return new C04761(this.this$0, loadResult, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C04761) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PagingSource.LoadResult loadResult = loadResult;
                if (loadResult instanceof PagingSource.LoadResult.Page) {
                    this.this$0.this$0.onLoadSuccess(this.this$0.$type, (PagingSource.LoadResult.Page) loadResult);
                } else if (loadResult instanceof PagingSource.LoadResult.Error) {
                    this.this$0.this$0.onLoadError(this.this$0.$type, ((PagingSource.LoadResult.Error) loadResult).getThrowable());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
