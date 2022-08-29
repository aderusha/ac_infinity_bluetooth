package androidx.core.view;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(mo27511d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, mo27512d2 = {"<anonymous>", ""}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: View.kt */
public final class ViewKt$postOnAnimationDelayed$runnable$1 implements Runnable {
    final /* synthetic */ Function0<Unit> $action;

    public ViewKt$postOnAnimationDelayed$runnable$1(Function0<Unit> function0) {
        this.$action = function0;
    }

    public final void run() {
        this.$action.invoke();
    }
}
