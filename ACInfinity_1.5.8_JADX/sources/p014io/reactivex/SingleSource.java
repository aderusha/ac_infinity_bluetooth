package p014io.reactivex;

/* renamed from: io.reactivex.SingleSource */
public interface SingleSource<T> {
    void subscribe(SingleObserver<? super T> singleObserver);
}
