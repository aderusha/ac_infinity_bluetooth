package androidx.paging;

import androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2;
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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u0004*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PageEvent;", "R", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/PagingDataTransforms$flatMap$2$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$flatMap$2$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {83}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagingDataTransforms.kt */
final class PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<R>>, Object> {
    final /* synthetic */ PageEvent $event;
    int label;
    final /* synthetic */ PagingDataTransforms$flatMap$$inlined$transform$2.C05252 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1(PageEvent pageEvent, Continuation continuation, PagingDataTransforms$flatMap$$inlined$transform$2.C05252 r3) {
        super(2, continuation);
        this.$event = pageEvent;
        this.this$0 = r3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1(this.$event, continuation, this.this$0);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"<anonymous>", "", "R", "T", "", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/PagingDataTransforms$flatMap$2$1$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$flatMap$2$1$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1$1 */
    /* compiled from: PagingDataTransforms.kt */
    static final class C05271 extends SuspendLambda implements Function2<T, Continuation<? super Iterable<? extends R>>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            C05271 r0 = new C05271(this.this$0, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C05271) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return this.$transform$inlined$1.invoke(this.L$0);
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
            obj = this.$event.flatMap(new C05271(this, (Continuation) null), this);
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
