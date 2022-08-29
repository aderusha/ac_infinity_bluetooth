package com.eternal.base.exception;

import androidx.fragment.app.FragmentActivity;
import com.eternal.base.data.ble.BleServer;
import com.eternal.framework.component.AppManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.ObservableTransformer;
import p014io.reactivex.functions.Function;

public class ExceptionHandle {
    public static <T> ObservableTransformer<T, T> handle() {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            public ObservableSource<?> apply(final Throwable th) throws Exception {
                                if (th instanceof NotEnableBluetoothException) {
                                    return new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity()).request("android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION").map(new Function<Boolean, Observable>() {
                                        public Observable apply(Boolean bool) {
                                            if (!bool.booleanValue()) {
                                                return Observable.error(th);
                                            }
                                            BleServer.getInstance().enable();
                                            return Observable.empty();
                                        }
                                    });
                                }
                                return Observable.error(th);
                            }
                        });
                    }
                });
            }
        };
    }
}
