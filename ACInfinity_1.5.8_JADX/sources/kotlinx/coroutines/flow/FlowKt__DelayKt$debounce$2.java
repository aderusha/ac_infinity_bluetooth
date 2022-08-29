package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n"}, mo27512d2 = {"<anonymous>", "", "T", "it"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounce$2 extends Lambda implements Function1<T, Long> {
    final /* synthetic */ long $timeoutMillis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounce$2(long j) {
        super(1);
        this.$timeoutMillis = j;
    }

    public final Long invoke(T t) {
        return Long.valueOf(this.$timeoutMillis);
    }
}
