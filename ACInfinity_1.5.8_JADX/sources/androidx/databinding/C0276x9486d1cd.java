package androidx.databinding;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.databinding.ViewDataBindingKtx$StateFlowListener$startCollection$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0276x9486d1cd implements FlowCollector<Object> {
    final /* synthetic */ ViewDataBindingKtx$StateFlowListener$startCollection$1 this$0;

    public C0276x9486d1cd(ViewDataBindingKtx$StateFlowListener$startCollection$1 viewDataBindingKtx$StateFlowListener$startCollection$1) {
        this.this$0 = viewDataBindingKtx$StateFlowListener$startCollection$1;
    }

    public Object emit(Object obj, Continuation continuation) {
        Unit unit;
        ViewDataBinding binder = this.this$0.this$0.listener.getBinder();
        if (binder != null) {
            binder.handleFieldChange(this.this$0.this$0.listener.mLocalFieldId, this.this$0.this$0.listener.getTarget(), 0);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return unit;
        }
        return Unit.INSTANCE;
    }
}
