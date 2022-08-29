package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.Notification;
import p014io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableMaterialize */
public final class FlowableMaterialize<T> extends AbstractFlowableWithUpstream<T, Notification<T>> {
    public FlowableMaterialize(Flowable<T> flowable) {
        super(flowable);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super Notification<T>> subscriber) {
        this.source.subscribe(new MaterializeSubscriber(subscriber));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableMaterialize$MaterializeSubscriber */
    static final class MaterializeSubscriber<T> extends SinglePostCompleteSubscriber<T, Notification<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        MaterializeSubscriber(Subscriber<? super Notification<T>> subscriber) {
            super(subscriber);
        }

        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(Notification.createOnNext(t));
        }

        public void onError(Throwable th) {
            complete(Notification.createOnError(th));
        }

        public void onComplete() {
            complete(Notification.createOnComplete());
        }

        /* access modifiers changed from: protected */
        public void onDrop(Notification<T> notification) {
            if (notification.isOnError()) {
                RxJavaPlugins.onError(notification.getError());
            }
        }
    }
}
