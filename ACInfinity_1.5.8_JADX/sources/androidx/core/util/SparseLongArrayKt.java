package androidx.core.util;

import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\b\u001aH\u0010\f\u001a\u00020\r*\u00020\u000226\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\r0\u000fH\bø\u0001\u0000\u001a\u001d\u0010\u0012\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000bH\b\u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015H\bø\u0001\u0000\u001a\r\u0010\u0016\u001a\u00020\u0006*\u00020\u0002H\b\u001a\r\u0010\u0017\u001a\u00020\u0006*\u00020\u0002H\b\u001a\f\u0010\u0018\u001a\u00020\u0019*\u00020\u0002H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0002\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0007\u001a\u001c\u0010\u001d\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\u001d\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\n\u001a\f\u0010\u001f\u001a\u00020 *\u00020\u0002H\u0007\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006!"}, mo27512d2 = {"size", "", "Landroid/util/SparseLongArray;", "getSize", "(Landroid/util/SparseLongArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/LongIterator;", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SparseLongArray.kt */
public final class SparseLongArrayKt {
    public static final int getSize(SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.size();
    }

    public static final boolean contains(SparseLongArray sparseLongArray, int i) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.indexOfKey(i) >= 0;
    }

    public static final void set(SparseLongArray sparseLongArray, int i, long j) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        sparseLongArray.put(i, j);
    }

    public static final SparseLongArray plus(SparseLongArray sparseLongArray, SparseLongArray sparseLongArray2) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(sparseLongArray2, "other");
        SparseLongArray sparseLongArray3 = new SparseLongArray(sparseLongArray.size() + sparseLongArray2.size());
        putAll(sparseLongArray3, sparseLongArray);
        putAll(sparseLongArray3, sparseLongArray2);
        return sparseLongArray3;
    }

    public static final boolean containsKey(SparseLongArray sparseLongArray, int i) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.indexOfKey(i) >= 0;
    }

    public static final boolean containsValue(SparseLongArray sparseLongArray, long j) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.indexOfValue(j) >= 0;
    }

    public static final long getOrDefault(SparseLongArray sparseLongArray, int i, long j) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.get(i, j);
    }

    public static final long getOrElse(SparseLongArray sparseLongArray, int i, Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(function0, "defaultValue");
        int indexOfKey = sparseLongArray.indexOfKey(i);
        return indexOfKey >= 0 ? sparseLongArray.valueAt(indexOfKey) : function0.invoke().longValue();
    }

    public static final boolean isEmpty(SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.size() == 0;
    }

    public static final boolean isNotEmpty(SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return sparseLongArray.size() != 0;
    }

    public static final boolean remove(SparseLongArray sparseLongArray, int i, long j) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        int indexOfKey = sparseLongArray.indexOfKey(i);
        if (indexOfKey < 0 || j != sparseLongArray.valueAt(indexOfKey)) {
            return false;
        }
        sparseLongArray.removeAt(indexOfKey);
        return true;
    }

    public static final void forEach(SparseLongArray sparseLongArray, Function2<? super Integer, ? super Long, Unit> function2) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(function2, "action");
        int size = sparseLongArray.size();
        if (size > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                function2.invoke(Integer.valueOf(sparseLongArray.keyAt(i)), Long.valueOf(sparseLongArray.valueAt(i)));
                if (i2 < size) {
                    i = i2;
                } else {
                    return;
                }
            }
        }
    }

    public static final IntIterator keyIterator(SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return new SparseLongArrayKt$keyIterator$1(sparseLongArray);
    }

    public static final LongIterator valueIterator(SparseLongArray sparseLongArray) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        return new SparseLongArrayKt$valueIterator$1(sparseLongArray);
    }

    public static final void putAll(SparseLongArray sparseLongArray, SparseLongArray sparseLongArray2) {
        Intrinsics.checkNotNullParameter(sparseLongArray, "<this>");
        Intrinsics.checkNotNullParameter(sparseLongArray2, "other");
        int size = sparseLongArray2.size();
        if (size > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                sparseLongArray.put(sparseLongArray2.keyAt(i), sparseLongArray2.valueAt(i));
                if (i2 < size) {
                    i = i2;
                } else {
                    return;
                }
            }
        }
    }
}
