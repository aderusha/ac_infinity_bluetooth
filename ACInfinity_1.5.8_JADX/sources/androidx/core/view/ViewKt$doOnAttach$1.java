package androidx.core.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, mo27512d2 = {"androidx/core/view/ViewKt$doOnAttach$1", "Landroid/view/View$OnAttachStateChangeListener;", "onViewAttachedToWindow", "", "view", "Landroid/view/View;", "onViewDetachedFromWindow", "core-ktx_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: View.kt */
public final class ViewKt$doOnAttach$1 implements View.OnAttachStateChangeListener {
    final /* synthetic */ Function1<View, Unit> $action;
    final /* synthetic */ View $this_doOnAttach;

    public void onViewDetachedFromWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public ViewKt$doOnAttach$1(View view, Function1<? super View, Unit> function1) {
        this.$this_doOnAttach = view;
        this.$action = function1;
    }

    public void onViewAttachedToWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
        this.$action.invoke(view);
    }
}
