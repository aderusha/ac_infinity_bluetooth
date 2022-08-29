package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u0002H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "R", "", "T", "stash", "Landroidx/paging/TransformablePage;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: Separators.kt */
final class SeparatorState$onDrop$1 extends Lambda implements Function1<TransformablePage<T>, Boolean> {
    final /* synthetic */ IntRange $pageOffsetsToDrop;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SeparatorState$onDrop$1(IntRange intRange) {
        super(1);
        this.$pageOffsetsToDrop = intRange;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((TransformablePage) obj));
    }

    public final boolean invoke(TransformablePage<T> transformablePage) {
        Intrinsics.checkNotNullParameter(transformablePage, "stash");
        for (int contains : transformablePage.getOriginalPageOffsets()) {
            if (this.$pageOffsetsToDrop.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
