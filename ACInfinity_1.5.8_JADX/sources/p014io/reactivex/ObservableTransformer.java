package p014io.reactivex;

/* renamed from: io.reactivex.ObservableTransformer */
public interface ObservableTransformer<Upstream, Downstream> {
    ObservableSource<Downstream> apply(Observable<Upstream> observable);
}
