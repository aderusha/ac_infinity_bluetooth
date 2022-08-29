package p014io.reactivex.internal.operators.completable;

import java.util.concurrent.Callable;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.completable.CompletableDefer */
public final class CompletableDefer extends Completable {
    final Callable<? extends CompletableSource> completableSupplier;

    public CompletableDefer(Callable<? extends CompletableSource> callable) {
        this.completableSupplier = callable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        try {
            ((CompletableSource) ObjectHelper.requireNonNull(this.completableSupplier.call(), "The completableSupplier returned a null CompletableSource")).subscribe(completableObserver);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
        }
    }
}
