package p014io.reactivex.internal.util;

import org.reactivestreams.Subscriber;

/* renamed from: io.reactivex.internal.util.QueueDrain */
public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> subscriber, T t);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int i);

    long produced(long j);

    long requested();
}
