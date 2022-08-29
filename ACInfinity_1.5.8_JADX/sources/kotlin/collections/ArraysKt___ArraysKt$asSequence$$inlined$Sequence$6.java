package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.sequences.Sequence;

@Metadata(mo27511d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004¸\u0006\u0000"}, mo27512d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: Sequences.kt */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6 implements Sequence<Float> {
    final /* synthetic */ float[] $this_asSequence$inlined;

    public ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6(float[] fArr) {
        this.$this_asSequence$inlined = fArr;
    }

    public Iterator<Float> iterator() {
        return ArrayIteratorsKt.iterator(this.$this_asSequence$inlined);
    }
}
