package com.eternal.framework.component;

import android.content.Intent;
import p014io.reactivex.disposables.Disposable;

public interface IActive {
    Intent createIntent(Class<?> cls);

    void dialogDisposable(Disposable disposable);

    void dismissDialog();

    void finish();

    void finishAnimate(int i, int i2);

    void onBackPressed();

    void setTransition(int i, int i2);

    void showDialog(String str, Disposable disposable);

    void showFailDialog(String str);

    void showSuccessDialog(String str);
}
