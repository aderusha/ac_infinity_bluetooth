package p014io.reactivex;

/* renamed from: io.reactivex.MaybeOperator */
public interface MaybeOperator<Downstream, Upstream> {
    MaybeObserver<? super Upstream> apply(MaybeObserver<? super Downstream> maybeObserver) throws Exception;
}
