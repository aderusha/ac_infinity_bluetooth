package p014io.reactivex.internal.operators.completable;

import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.Notification;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.internal.operators.mixed.MaterializeSingleObserver;

/* renamed from: io.reactivex.internal.operators.completable.CompletableMaterialize */
public final class CompletableMaterialize<T> extends Single<Notification<T>> {
    final Completable source;

    public CompletableMaterialize(Completable completable) {
        this.source = completable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.source.subscribe((CompletableObserver) new MaterializeSingleObserver(singleObserver));
    }
}
