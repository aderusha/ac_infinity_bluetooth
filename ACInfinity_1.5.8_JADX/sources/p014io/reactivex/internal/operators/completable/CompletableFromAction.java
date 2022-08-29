package p014io.reactivex.internal.operators.completable;

import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.disposables.Disposables;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.completable.CompletableFromAction */
public final class CompletableFromAction extends Completable {
    final Action run;

    public CompletableFromAction(Action action) {
        this.run = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        Disposable empty = Disposables.empty();
        completableObserver.onSubscribe(empty);
        try {
            this.run.run();
            if (!empty.isDisposed()) {
                completableObserver.onComplete();
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (!empty.isDisposed()) {
                completableObserver.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
