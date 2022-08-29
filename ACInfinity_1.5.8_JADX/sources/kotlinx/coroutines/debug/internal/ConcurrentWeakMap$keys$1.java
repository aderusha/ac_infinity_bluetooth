package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00022\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0005\u001a\u0002H\u0003H\n"}, mo27512d2 = {"<anonymous>", "K", "", "V", "k", "<anonymous parameter 1>"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: ConcurrentWeakMap.kt */
final class ConcurrentWeakMap$keys$1 extends Lambda implements Function2<K, V, K> {
    public static final ConcurrentWeakMap$keys$1 INSTANCE = new ConcurrentWeakMap$keys$1();

    ConcurrentWeakMap$keys$1() {
        super(2);
    }

    public final K invoke(K k, V v) {
        return k;
    }
}
