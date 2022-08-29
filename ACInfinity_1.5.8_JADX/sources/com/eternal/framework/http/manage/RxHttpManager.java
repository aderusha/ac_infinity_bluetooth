package com.eternal.framework.http.manage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;

public class RxHttpManager implements IRxHttpManager<Object> {
    private static RxHttpManager mInstance;
    private HashMap<Object, CompositeDisposable> mMaps = new HashMap<>();

    public static RxHttpManager get() {
        if (mInstance == null) {
            synchronized (RxHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new RxHttpManager();
                }
            }
        }
        return mInstance;
    }

    private RxHttpManager() {
    }

    public void add(Object obj, Disposable disposable) {
        if (obj != null) {
            CompositeDisposable compositeDisposable = this.mMaps.get(obj);
            if (compositeDisposable == null) {
                CompositeDisposable compositeDisposable2 = new CompositeDisposable();
                compositeDisposable2.add(disposable);
                this.mMaps.put(obj, compositeDisposable2);
                return;
            }
            compositeDisposable.add(disposable);
        }
    }

    public void remove(Object obj) {
        if (obj != null && !this.mMaps.isEmpty()) {
            this.mMaps.remove(obj);
        }
    }

    public void cancel(Object obj) {
        if (obj != null && !this.mMaps.isEmpty() && this.mMaps.get(obj) != null && !this.mMaps.get(obj).isDisposed()) {
            this.mMaps.get(obj).dispose();
            this.mMaps.remove(obj);
        }
    }

    public void cancel(Object... objArr) {
        if (objArr != null) {
            for (Object cancel : objArr) {
                cancel(cancel);
            }
        }
    }

    public void cancelAll() {
        if (!this.mMaps.isEmpty()) {
            Iterator<Map.Entry<Object, CompositeDisposable>> it = this.mMaps.entrySet().iterator();
            while (it.hasNext()) {
                CompositeDisposable compositeDisposable = (CompositeDisposable) it.next().getValue();
                if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
                    compositeDisposable.dispose();
                    it.remove();
                }
            }
        }
    }
}
