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
import kotlinx.coroutines.Deferred;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo27512d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxConvertKt$asMaybe$1", mo28222f = "RxConvert.kt", mo28223i = {}, mo28224l = {48}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asMaybe$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Deferred $this_asMaybe;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxConvertKt$asMaybe$1(Deferred deferred, Continuation continuation) {
        super(2, continuation);
        this.$this_asMaybe = deferred;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RxConvertKt$asMaybe$1(this.$this_asMaybe, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RxConvertKt$asMaybe$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Deferred deferred = this.$this_asMaybe;
            this.label = 1;
            obj = deferred.await(this);
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
