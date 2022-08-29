package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SafeCollector.kt */
/* synthetic */ class SafeCollectorKt$emitFun$1 extends FunctionReferenceImpl implements Function3<FlowCollector<? super Object>, Object, Unit>, SuspendFunction {
    public static final SafeCollectorKt$emitFun$1 INSTANCE = new SafeCollectorKt$emitFun$1();

    SafeCollectorKt$emitFun$1() {
        super(3, FlowCollector.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(FlowCollector<Object> flowCollector, Object obj, Continuation<? super Unit> continuation) {
        return flowCollector.emit(obj, continuation);
    }
}
