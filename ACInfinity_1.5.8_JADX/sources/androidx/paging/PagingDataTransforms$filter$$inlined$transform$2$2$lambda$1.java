package androidx.paging;

import androidx.paging.PagingDataTransforms$filter$$inlined$transform$2;
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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PageEvent;", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/PagingDataTransforms$filter$2$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$filter$2$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {108}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagingDataTransforms.kt */
final class PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<T>>, Object> {
    final /* synthetic */ PageEvent $event;
    int label;
    final /* synthetic */ PagingDataTransforms$filter$$inlined$transform$2.C05202 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1(PageEvent pageEvent, Continuation continuation, PagingDataTransforms$filter$$inlined$transform$2.C05202 r3) {
        super(2, continuation);
        this.$event = pageEvent;
        this.this$0 = r3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1(this.$event, continuation, this.this$0);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/PagingDataTransforms$filter$2$1$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$filter$2$1$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1$1 */
    /* compiled from: PagingDataTransforms.kt */
    static final class C05221 extends SuspendLambda implements Function2<T, Continuation<? super Boolean>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PagingDataTransforms$filter$$inlined$transform$2$2$lambda$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            C05221 r0 = new C05221(this.this$0, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C05221) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return this.$predicate$inlined.invoke(this.L$0);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$event.filter(new C05221(this, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
