package p014io.reactivex.internal.fuseable;

/* renamed from: io.reactivex.internal.fuseable.SimpleQueue */
public interface SimpleQueue<T> {
    void clear();

    boolean isEmpty();

    boolean offer(T t);

    boolean offer(T t, T t2);

    T poll() throws Exception;
}
