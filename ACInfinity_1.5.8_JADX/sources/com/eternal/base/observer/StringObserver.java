package com.eternal.base.observer;

import android.text.TextUtils;
import com.eternal.framework.http.base.BaseObserver;
import com.eternal.framework.http.utils.ToastUtils;
import p014io.reactivex.disposables.Disposable;

public abstract class StringObserver extends BaseObserver<String> {
    public void doOnCompleted() {
    }

    public void doOnSubscribe(Disposable disposable) {
    }

    /* access modifiers changed from: protected */
    public abstract void onError(String str);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(String str);

    public void doOnError(String str) {
        if (!isHideToast() && !TextUtils.isEmpty(str)) {
            ToastUtils.showToast(str);
        }
        onError(str);
    }

    public void doOnNext(String str) {
        onSuccess(str);
    }
}
