package p014io.reactivex.internal.fuseable;

import org.reactivestreams.Publisher;

/* renamed from: io.reactivex.internal.fuseable.HasUpstreamPublisher */
public interface HasUpstreamPublisher<T> {
    Publisher<T> source();
}
