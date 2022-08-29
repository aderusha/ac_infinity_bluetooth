package p014io.reactivex.internal.operators.maybe;

import p014io.reactivex.Maybe;
import p014io.reactivex.Notification;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.internal.operators.mixed.MaterializeSingleObserver;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeMaterialize */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {
    final Maybe<T> source;

    public MaybeMaterialize(Maybe<T> maybe) {
        this.source = maybe;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.source.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
