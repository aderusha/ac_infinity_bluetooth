package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 5, 1})
/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$flatten$2 extends Lambda implements Function1<Iterable<? extends T>, Iterator<? extends T>> {
    public static final SequencesKt__SequencesKt$flatten$2 INSTANCE = new SequencesKt__SequencesKt$flatten$2();

    SequencesKt__SequencesKt$flatten$2() {
        super(1);
    }

    public final Iterator<T> invoke(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "it");
        return iterable.iterator();
    }
}
