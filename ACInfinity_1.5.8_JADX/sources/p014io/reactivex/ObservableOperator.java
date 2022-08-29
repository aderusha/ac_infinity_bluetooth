package p014io.reactivex;

/* renamed from: io.reactivex.ObservableOperator */
public interface ObservableOperator<Downstream, Upstream> {
    Observer<? super Upstream> apply(Observer<? super Downstream> observer) throws Exception;
}
