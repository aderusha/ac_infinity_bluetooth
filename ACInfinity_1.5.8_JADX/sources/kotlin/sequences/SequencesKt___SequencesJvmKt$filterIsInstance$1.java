package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, mo27512d2 = {"<anonymous>", "", "R", "it", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 5, 1})
/* compiled from: _SequencesJvm.kt */
final class SequencesKt___SequencesJvmKt$filterIsInstance$1 extends Lambda implements Function1<Object, Boolean> {
    final /* synthetic */ Class $klass;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesJvmKt$filterIsInstance$1(Class cls) {
        super(1);
        this.$klass = cls;
    }

    public final boolean invoke(Object obj) {
        return this.$klass.isInstance(obj);
    }
}
