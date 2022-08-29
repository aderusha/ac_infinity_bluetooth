package p014io.reactivex.internal.operators.completable;

import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.internal.operators.completable.CompletableFromObservable */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableFromObservable$CompletableFromObservableObserver */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        /* renamed from: co */
        final CompletableObserver f1098co;

        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.f1098co = completableObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.f1098co.onSubscribe(disposable);
        }

        public void onError(Throwable th) {
            this.f1098co.onError(th);
        }

        public void onComplete() {
            this.f1098co.onComplete();
        }
    }
}
