package kotlin.sequences;

import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Z"}, mo27513k = 3, mo27514mv = {1, 5, 1})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$minus$4$iterator$1 extends Lambda implements Function1<T, Boolean> {
    final /* synthetic */ HashSet $other;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$minus$4$iterator$1(HashSet hashSet) {
        super(1);
        this.$other = hashSet;
    }

    public final boolean invoke(T t) {
        return this.$other.contains(t);
    }
}
