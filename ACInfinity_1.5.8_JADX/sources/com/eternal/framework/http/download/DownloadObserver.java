package com.eternal.framework.http.download;

import com.eternal.framework.http.base.BaseObserver;
import com.eternal.framework.http.manage.RxHttpManager;
import java.io.IOException;
import okhttp3.ResponseBody;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public abstract class DownloadObserver extends BaseObserver<ResponseBody> {
    /* access modifiers changed from: private */
    public String destFileDir;
    /* access modifiers changed from: private */
    public String fileName;

    public void doOnCompleted() {
    }

    /* access modifiers changed from: protected */
    public abstract void onError(String str);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(long j, long j2, float f, boolean z, String str);

    public DownloadObserver(String str) {
        this.fileName = str;
    }

    public DownloadObserver(String str, String str2) {
        this.fileName = str;
        this.destFileDir = str2;
    }

    public void doOnError(String str) {
        onError(str);
    }

    public void doOnSubscribe(Disposable disposable) {
        RxHttpManager.get().add(setTag(), disposable);
    }

    public void doOnNext(ResponseBody responseBody) {
        Observable.just(responseBody).subscribeOn(Schedulers.m983io()).subscribe(new Observer<ResponseBody>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(ResponseBody responseBody) {
                try {
                    new DownloadManager().saveFile(responseBody, DownloadObserver.this.fileName, DownloadObserver.this.destFileDir, new ProgressListener() {
                        public void onResponseProgress(long j, long j2, int i, boolean z, String str) {
                            final long j3 = j;
                            final long j4 = j2;
                            final int i2 = i;
                            final boolean z2 = z;
                            final String str2 = str;
                            Observable.just(Integer.valueOf(i)).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                                public void accept(Integer num) throws Exception {
                                    DownloadObserver.this.onSuccess(j3, j4, (float) i2, z2, str2);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    Observable.just(e.getMessage()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                        public void accept(String str) throws Exception {
                            DownloadObserver.this.doOnError(str);
                        }
                    });
                }
            }
        });
    }
}
