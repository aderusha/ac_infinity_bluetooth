package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022.\u0010\u0003\u001a*\u0012\u000e\b\u0000\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u0002 \u0005*\u0014\u0012\u000e\b\u0000\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "subscriber", "Lorg/reactivestreams/Subscriber;", "kotlin.jvm.PlatformType", "subscribe"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: Publish.kt */
final class PublishKt$publishInternal$1<T> implements Publisher<T> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function2 $exceptionOnCancelHandler;
    final /* synthetic */ CoroutineScope $scope;

    PublishKt$publishInternal$1(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, Function2 function22) {
        this.$scope = coroutineScope;
        this.$context = coroutineContext;
        this.$exceptionOnCancelHandler = function2;
        this.$block = function22;
    }

    public final void subscribe(Subscriber<? super T> subscriber) {
        if (subscriber != null) {
            PublisherCoroutine publisherCoroutine = new PublisherCoroutine(CoroutineContextKt.newCoroutineContext(this.$scope, this.$context), subscriber, this.$exceptionOnCancelHandler);
            subscriber.onSubscribe(publisherCoroutine);
            publisherCoroutine.start(CoroutineStart.DEFAULT, publisherCoroutine, this.$block);
            return;
        }
        throw new NullPointerException("Subscriber cannot be null");
    }
}
