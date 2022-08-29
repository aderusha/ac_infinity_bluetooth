package androidx.databinding;

import androidx.databinding.ViewDataBindingKtx;
import java.lang.ref.ReferenceQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\"\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u000b\u0012\u0002\b\u0003\u0018\u00010\u0001¨\u0006\u00010\u0001¨\u0006\u00012\u000e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u0006\u001a\u00020\u00072*\u0010\b\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00050\u0005 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\t0\tH\n¢\u0006\u0002\b\n"}, mo27512d2 = {"<anonymous>", "Landroidx/databinding/WeakListener;", "", "kotlin.jvm.PlatformType", "viewDataBinding", "Landroidx/databinding/ViewDataBinding;", "localFieldId", "", "referenceQueue", "Ljava/lang/ref/ReferenceQueue;", "create"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: ViewDataBindingKtx.kt */
final class ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1 implements CreateWeakListener {
    public static final ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1 INSTANCE = new ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1();

    ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1() {
    }

    public final WeakListener<Object> create(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
        Intrinsics.checkNotNullExpressionValue(referenceQueue, "referenceQueue");
        return new ViewDataBindingKtx.StateFlowListener(viewDataBinding, i, referenceQueue).getListener();
    }
}
