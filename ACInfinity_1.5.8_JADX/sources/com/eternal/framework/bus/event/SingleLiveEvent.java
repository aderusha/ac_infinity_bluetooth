package com.eternal.framework.bus.event;

import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveEvent";
    /* access modifiers changed from: private */
    public final AtomicBoolean mPending = new AtomicBoolean(false);

    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new Observer<T>() {
            public void onChanged(T t) {
                if (SingleLiveEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void setValue(T t) {
        this.mPending.set(true);
        super.setValue(t);
    }

    public void call() {
        setValue((Object) null);
    }
}
