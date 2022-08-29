package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0006\b\u0001\u0010\u0002 \u00002\u0006\u0010\u0003\u001a\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "R", "T", "it", "Lkotlinx/coroutines/sync/Mutex;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.reactive.PublisherCoroutine$registerSelectClause2$1", mo28222f = "Publish.kt", mo28223i = {}, mo28224l = {125}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Publish.kt */
final class PublisherCoroutine$registerSelectClause2$1 extends SuspendLambda implements Function2<Mutex, Continuation<? super R>, Object> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ Object $element;
    int label;
    final /* synthetic */ PublisherCoroutine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PublisherCoroutine$registerSelectClause2$1(PublisherCoroutine publisherCoroutine, Object obj, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = publisherCoroutine;
        this.$element = obj;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PublisherCoroutine$registerSelectClause2$1(this.this$0, this.$element, this.$block, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PublisherCoroutine$registerSelectClause2$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.doLockedNext(this.$element);
            Function2 function2 = this.$block;
            PublisherCoroutine publisherCoroutine = this.this$0;
            this.label = 1;
            obj = function2.invoke(publisherCoroutine, this);
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
