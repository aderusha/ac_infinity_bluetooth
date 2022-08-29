package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0012\u0010\u0003\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, mo27512d2 = {"Lkotlinx/coroutines/flow/StateFlow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "value", "getValue", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: StateFlow.kt */
public interface StateFlow<T> extends SharedFlow<T> {
    T getValue();
}
