package p014io.reactivex;

/* renamed from: io.reactivex.SingleOperator */
public interface SingleOperator<Downstream, Upstream> {
    SingleObserver<? super Upstream> apply(SingleObserver<? super Downstream> singleObserver) throws Exception;
}
