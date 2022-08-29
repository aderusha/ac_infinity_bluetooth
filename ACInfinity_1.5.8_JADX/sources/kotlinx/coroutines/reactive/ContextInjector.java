package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/ContextInjector;", "", "injectCoroutineContext", "Lorg/reactivestreams/Publisher;", "T", "publisher", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ContextInjector.kt */
public interface ContextInjector {
    <T> Publisher<T> injectCoroutineContext(Publisher<T> publisher, CoroutineContext coroutineContext);
}
