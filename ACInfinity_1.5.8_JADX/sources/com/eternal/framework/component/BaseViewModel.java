package com.eternal.framework.component;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import com.trello.rxlifecycle2.LifecycleProvider;
import java.lang.ref.WeakReference;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class BaseViewModel extends AndroidViewModel implements Consumer<Disposable>, LifecycleObserver {
    private IActive active;
    private WeakReference<LifecycleProvider> lifecycle;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void registerRxBus() {
    }

    public void unregisterRxBus() {
    }

    public BaseViewModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: protected */
    public void addSubscribe(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    public void injectLifecycleProvider(LifecycleProvider lifecycleProvider) {
        this.lifecycle = new WeakReference<>(lifecycleProvider);
    }

    public LifecycleProvider getLifecycleProvider() {
        return (LifecycleProvider) this.lifecycle.get();
    }

    public void initActive(IActive iActive) {
        this.active = iActive;
    }

    public void showDialog(Disposable disposable) {
        showDialog("请稍后...", disposable);
    }

    public void showDialog(String str, Disposable disposable) {
        this.active.showDialog(str, disposable);
    }

    public void showFailDialog(String str) {
        this.active.showFailDialog(str);
    }

    public void showSuccessDialog(String str) {
        this.active.showSuccessDialog(str);
    }

    public void dismissDialog() {
        this.active.dismissDialog();
    }

    public void dialogDisposable(Disposable disposable) {
        this.active.dialogDisposable(disposable);
    }

    public void startActivity(Class<?> cls) {
        getApplication().startActivity(this.active.createIntent(cls));
    }

    public void startActivity(Intent intent) {
        getApplication().startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent createIntent = this.active.createIntent(cls);
        createIntent.putExtras(bundle);
        getApplication().startActivity(createIntent);
    }

    public void finish() {
        this.active.finish();
    }

    public void finishAnimate(int i, int i2) {
        this.active.finishAnimate(i, i2);
    }

    public void setTransition(int i, int i2) {
        this.active.setTransition(i, i2);
    }

    public void onBackPressed() {
        this.active.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void accept(Disposable disposable) {
        addSubscribe(disposable);
    }
}
