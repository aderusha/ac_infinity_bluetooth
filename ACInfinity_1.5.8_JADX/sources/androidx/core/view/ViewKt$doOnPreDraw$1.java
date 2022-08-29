package androidx.core.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(mo27511d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, mo27512d2 = {"<anonymous>", ""}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: View.kt */
public final class ViewKt$doOnPreDraw$1 implements Runnable {
    final /* synthetic */ Function1<View, Unit> $action;
    final /* synthetic */ View $this_doOnPreDraw;

    public ViewKt$doOnPreDraw$1(Function1<? super View, Unit> function1, View view) {
        this.$action = function1;
        this.$this_doOnPreDraw = view;
    }

    public final void run() {
        this.$action.invoke(this.$this_doOnPreDraw);
    }
}
