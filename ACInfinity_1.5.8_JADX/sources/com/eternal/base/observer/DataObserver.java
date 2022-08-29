package com.eternal.base.observer;

import android.text.TextUtils;
import com.eternal.framework.http.base.BaseObserver;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.http.utils.ToastUtils;
import p014io.reactivex.disposables.Disposable;

public abstract class DataObserver<T> extends BaseObserver<BaseData<T>> {
    public void doOnCompleted() {
    }

    public void doOnSubscribe(Disposable disposable) {
    }

    /* access modifiers changed from: protected */
    public abstract void onError(String str);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(T t);

    public void doOnError(String str) {
        if (!isHideToast() && !TextUtils.isEmpty(str)) {
            ToastUtils.showToast(str);
        }
        onError(str);
    }

    public void doOnNext(BaseData<T> baseData) {
        int code = baseData.getCode();
        if (code == 200) {
            onSuccess(baseData.getData());
        } else if (code != 100001) {
            onError(baseData.getMsg());
        } else {
            onError("Controller connectivity issue, please try again later.");
        }
    }
}
