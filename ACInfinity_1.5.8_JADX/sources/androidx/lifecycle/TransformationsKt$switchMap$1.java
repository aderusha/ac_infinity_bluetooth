package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u00022\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0003H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "Y", "X", "it", "kotlin.jvm.PlatformType", "apply", "(Ljava/lang/Object;)Landroidx/lifecycle/LiveData;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
/* compiled from: Transformations.kt */
public final class TransformationsKt$switchMap$1<I, O> implements Function<X, LiveData<Y>> {
    final /* synthetic */ Function1 $transform;

    public TransformationsKt$switchMap$1(Function1 function1) {
        this.$transform = function1;
    }

    public final LiveData<Y> apply(X x) {
        return (LiveData) this.$transform.invoke(x);
    }
}
