package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n"}, mo27512d2 = {"<anonymous>", "", "T", "R"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Zip.kt */
final class FlowKt__ZipKt$combine$6$1 extends Lambda implements Function0<T[]> {
    final /* synthetic */ Flow<T>[] $flowArray;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$6$1(Flow<T>[] flowArr) {
        super(0);
        this.$flowArray = flowArr;
    }

    public final T[] invoke() {
        int length = this.$flowArray.length;
        Intrinsics.reifiedOperationMarker(0, "T?");
        return new Object[length];
    }
}
