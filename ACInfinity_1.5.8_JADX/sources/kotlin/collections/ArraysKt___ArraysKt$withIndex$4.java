package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010(\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo27512d2 = {"<anonymous>", "", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 5, 1})
/* compiled from: _Arrays.kt */
final class ArraysKt___ArraysKt$withIndex$4 extends Lambda implements Function0<Iterator<? extends Integer>> {
    final /* synthetic */ int[] $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$4(int[] iArr) {
        super(0);
        this.$this_withIndex = iArr;
    }

    public final Iterator<Integer> invoke() {
        return ArrayIteratorsKt.iterator(this.$this_withIndex);
    }
}
