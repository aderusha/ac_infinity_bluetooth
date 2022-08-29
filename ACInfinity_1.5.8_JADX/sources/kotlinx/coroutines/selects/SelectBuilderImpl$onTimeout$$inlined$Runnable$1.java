package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.intrinsics.CancellableKt;

@Metadata(mo27511d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¨\u0006\u0002"}, mo27512d2 = {"<anonymous>", "", "kotlinx/coroutines/RunnableKt$Runnable$1"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Runnable.kt */
public final class SelectBuilderImpl$onTimeout$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ SelectBuilderImpl this$0;

    public SelectBuilderImpl$onTimeout$$inlined$Runnable$1(SelectBuilderImpl selectBuilderImpl, Function1 function1) {
        this.this$0 = selectBuilderImpl;
        this.$block$inlined = function1;
    }

    public final void run() {
        if (this.this$0.trySelect()) {
            CancellableKt.startCoroutineCancellable(this.$block$inlined, this.this$0.getCompletion());
        }
    }
}
