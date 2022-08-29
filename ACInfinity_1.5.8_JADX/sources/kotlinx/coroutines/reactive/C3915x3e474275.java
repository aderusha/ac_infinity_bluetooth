package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "invoke", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/reactive/FlowSubscription$createInitialContinuation$1$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* renamed from: kotlinx.coroutines.reactive.FlowSubscription$createInitialContinuation$$inlined$Continuation$1$lambda$1 */
/* compiled from: ReactiveFlow.kt */
final /* synthetic */ class C3915x3e474275 extends FunctionReferenceImpl implements Function1<Continuation<? super Unit>, Object>, SuspendFunction {
    C3915x3e474275(FlowSubscription flowSubscription) {
        super(1, flowSubscription, FlowSubscription.class, "flowProcessing", "flowProcessing(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((FlowSubscription) this.receiver).flowProcessing(continuation);
    }
}
