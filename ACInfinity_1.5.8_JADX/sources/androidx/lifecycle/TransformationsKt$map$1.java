package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0004\n\u0002\b\u0007\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00012\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "Y", "X", "it", "kotlin.jvm.PlatformType", "apply", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
/* compiled from: Transformations.kt */
public final class TransformationsKt$map$1<I, O> implements Function<X, Y> {
    final /* synthetic */ Function1 $transform;

    public TransformationsKt$map$1(Function1 function1) {
        this.$transform = function1;
    }

    public final Y apply(X x) {
        return this.$transform.invoke(x);
    }
}
