package p014io.reactivex;

/* renamed from: io.reactivex.MaybeSource */
public interface MaybeSource<T> {
    void subscribe(MaybeObserver<? super T> maybeObserver);
}
