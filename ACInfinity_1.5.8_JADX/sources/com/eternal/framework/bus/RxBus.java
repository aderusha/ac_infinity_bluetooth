package com.eternal.framework.bus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.subjects.PublishSubject;
import p014io.reactivex.subjects.Subject;

public class RxBus {
    private static volatile RxBus mDefaultInstance;
    private final Subject<Object> mBus = PublishSubject.create().toSerialized();
    private final Map<Class<?>, Object> mStickyEventMap = new ConcurrentHashMap();

    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    public void post(Object obj) {
        this.mBus.onNext(obj);
    }

    public <T> Observable<T> toObservable(Class<T> cls) {
        return this.mBus.ofType(cls);
    }

    public boolean hasObservers() {
        return this.mBus.hasObservers();
    }

    public void reset() {
        mDefaultInstance = null;
    }

    public void postSticky(Object obj) {
        synchronized (this.mStickyEventMap) {
            this.mStickyEventMap.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public <T> Observable<T> toObservableSticky(final Class<T> cls) {
        synchronized (this.mStickyEventMap) {
            Observable<U> ofType = this.mBus.ofType(cls);
            final Object obj = this.mStickyEventMap.get(cls);
            if (obj == null) {
                return ofType;
            }
            Observable<T> merge = Observable.merge(ofType, (Observable<U>) Observable.create(new ObservableOnSubscribe<T>() {
                public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
                    observableEmitter.onNext(cls.cast(obj));
                }
            }));
            return merge;
        }
    }

    public <T> T getStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.mStickyEventMap) {
            cast = cls.cast(this.mStickyEventMap.get(cls));
        }
        return cast;
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.mStickyEventMap) {
            cast = cls.cast(this.mStickyEventMap.remove(cls));
        }
        return cast;
    }

    public void removeAllStickyEvents() {
        synchronized (this.mStickyEventMap) {
            this.mStickyEventMap.clear();
        }
    }
}
