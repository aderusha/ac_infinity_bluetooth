package androidx.paging;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u00012\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00032\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0003H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "R", "", "T", "before", "after", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$insertSeparators$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {261}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagingDataTransforms.kt */
final class PagingDataTransforms$insertSeparators$1 extends SuspendLambda implements Function3<T, T, Continuation<? super R>, Object> {
    final /* synthetic */ Executor $executor;
    final /* synthetic */ Function2 $generator;
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataTransforms$insertSeparators$1(Executor executor, Function2 function2, Continuation continuation) {
        super(3, continuation);
        this.$executor = executor;
        this.$generator = function2;
    }

    public final Continuation<Unit> create(T t, T t2, Continuation<? super R> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        PagingDataTransforms$insertSeparators$1 pagingDataTransforms$insertSeparators$1 = new PagingDataTransforms$insertSeparators$1(this.$executor, this.$generator, continuation);
        pagingDataTransforms$insertSeparators$1.L$0 = t;
        pagingDataTransforms$insertSeparators$1.L$1 = t2;
        return pagingDataTransforms$insertSeparators$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((PagingDataTransforms$insertSeparators$1) create(obj, obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0001*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "R", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$insertSeparators$1$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.PagingDataTransforms$insertSeparators$1$1 */
    /* compiled from: PagingDataTransforms.kt */
    static final class C05541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        int label;
        final /* synthetic */ PagingDataTransforms$insertSeparators$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            return new C05541(this.this$0, obj2, obj3, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C05541) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return this.this$0.$generator.invoke(obj2, obj3);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Object obj2 = this.L$0;
            final Object obj3 = this.L$1;
            this.L$0 = null;
            this.label = 1;
            obj = BuildersKt.withContext(ExecutorsKt.from(this.$executor), new C05541(this, (Continuation) null), this);
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
