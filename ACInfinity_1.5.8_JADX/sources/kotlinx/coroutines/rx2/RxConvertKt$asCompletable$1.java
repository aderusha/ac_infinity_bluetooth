package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo27512d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxConvertKt$asCompletable$1", mo28222f = "RxConvert.kt", mo28223i = {}, mo28224l = {31}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asCompletable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Job $this_asCompletable;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxConvertKt$asCompletable$1(Job job, Continuation continuation) {
        super(2, continuation);
        this.$this_asCompletable = job;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RxConvertKt$asCompletable$1(this.$this_asCompletable, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RxConvertKt$asCompletable$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Job job = this.$this_asCompletable;
            this.label = 1;
            if (job.join(this) == coroutine_suspended) {
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
