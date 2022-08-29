package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;

@Metadata(mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H@"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "cause", "", "attempt", ""}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6", mo28222f = "Errors.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Errors.kt */
final class FlowKt__ErrorsKt$retry$6 extends SuspendLambda implements Function4<FlowCollector<? super T>, Throwable, Long, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Function1<Throwable, Boolean> $predicate;
    final /* synthetic */ int $retries;
    /* synthetic */ long J$0;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__ErrorsKt$retry$6(Function1<? super Throwable, Boolean> function1, int i, Continuation<? super FlowKt__ErrorsKt$retry$6> continuation) {
        super(4, continuation);
        this.$predicate = function1;
        this.$retries = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((FlowCollector) obj, (Throwable) obj2, ((Number) obj3).longValue(), (Continuation<? super Boolean>) (Continuation) obj4);
    }

    public final Object invoke(FlowCollector<? super T> flowCollector, Throwable th, long j, Continuation<? super Boolean> continuation) {
        FlowKt__ErrorsKt$retry$6 flowKt__ErrorsKt$retry$6 = new FlowKt__ErrorsKt$retry$6(this.$predicate, this.$retries, continuation);
        flowKt__ErrorsKt$retry$6.L$0 = th;
        flowKt__ErrorsKt$retry$6.J$0 = j;
        return flowKt__ErrorsKt$retry$6.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.$predicate.invoke((Throwable) this.L$0).booleanValue() && this.J$0 < ((long) this.$retries));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
