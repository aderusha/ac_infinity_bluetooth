package com.eternal.device.model;

import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.TimeUtil;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.WiFiActivity;
import com.eternal.device.WiFiConnectActivity;
import com.eternal.device.WiFiListActivity;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.schedulers.Schedulers;

public class AddWiFiModel extends BaseViewModel {
    private static final String TAG = "AddWiFiModel";
    private ValueAnimator animator;
    public MutableLiveData<String> btnText = new MutableLiveData<>();
    public MutableLiveData<Boolean> btnVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> cancelVisible = new MutableLiveData<>();
    public MutableLiveData<String> contentText = new MutableLiveData<>();
    public MutableLiveData<Boolean> contentVisible = new MutableLiveData<>();
    byte deviceType;
    private int index;
    /* access modifiers changed from: private */
    public boolean isCancel;
    /* access modifiers changed from: private */
    public boolean isConnected;
    private String mac;
    public MutableLiveData<Boolean> maxVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> minVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> normalVisible = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AddWiFiModel.this.cancel();
            AddWiFiModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(AddWiFiModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!AddWiFiModel.this.isConnected) {
                AddWiFiModel.this.addDevInfo();
            } else {
                AddWiFiModel.this.pushToMain();
            }
        }
    });
    public BindingCommand<Void> onReconnect = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AddWiFiModel.this.pushToWifiPage();
        }
    });
    public MutableLiveData<Boolean> reconnectVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> state = new MutableLiveData<>();
    public MutableLiveData<String> stateText = new MutableLiveData<>();
    private String typeName;

    public AddWiFiModel(Application application) {
        super(application);
        this.minVisible.setValue(false);
        this.normalVisible.setValue(false);
        this.maxVisible.setValue(false);
        this.index = -1;
        this.isConnected = false;
        this.cancelVisible.setValue(true);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 4});
        this.animator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue == 0) {
                    AddWiFiModel.this.maxVisible.setValue(false);
                    AddWiFiModel.this.normalVisible.setValue(false);
                    AddWiFiModel.this.minVisible.setValue(false);
                } else if (intValue == 1) {
                    AddWiFiModel.this.minVisible.setValue(true);
                } else if (intValue == 2) {
                    AddWiFiModel.this.normalVisible.setValue(true);
                } else {
                    AddWiFiModel.this.maxVisible.setValue(true);
                }
            }
        });
        this.animator.setRepeatCount(-1);
        this.animator.setDuration(2000);
    }

    public void pushToWifiPage() {
        AppManager.getAppManager().finishActivity((Class<?>) WiFiConnectActivity.class);
        AppManager.getAppManager().finishActivity((Class<?>) WiFiListActivity.class);
        AppManager.getAppManager().finishActivity((Class<?>) WiFiActivity.class);
        finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
    }

    public void pushToMain() {
        cancel();
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).withInt(ActivityEvent.DEVICE_INDEX, this.index).withString(ActivityEvent.DEVICE_MAC, this.mac).withAction(ActivityEvent.ACTION_ADD_DEVICE).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiListActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiConnectActivity.class);
                AddWiFiModel.this.finish();
            }
        });
    }

    public void init(String str, byte b, String str2) {
        this.mac = str;
        this.typeName = str2;
        this.deviceType = b;
        addDevInfo();
    }

    public void addDevInfo() {
        scanStart();
        fetch(20, 1, PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    /* access modifiers changed from: private */
    public void fetch(final int i, final int i2, final int i3) {
        ApiHelper.getDeviceApi().bindDev(UserCache.getInstance().getToken(), this.mac.replace(":", ""), TimeUtil.getCurrentTimeZoneNumber()).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return AddWiFiModel.TAG;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
                if (AddWiFiModel.this.isCancel || i2 >= i) {
                    Single.create(new SingleOnSubscribe<Void>() {
                        public void subscribe(SingleEmitter<Void> singleEmitter) throws Exception {
                            AddWiFiModel.this.scanEnd(false);
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
                    return;
                }
                SystemClock.sleep((long) i3);
                AddWiFiModel.this.fetch(i, i2 + 1, i3);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                Single.create(new SingleOnSubscribe<Void>() {
                    public void subscribe(SingleEmitter<Void> singleEmitter) throws Exception {
                        boolean unused = AddWiFiModel.this.isConnected = true;
                        AddWiFiModel.this.cancelVisible.setValue(false);
                        AddWiFiModel.this.scanEnd(true);
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
            }
        });
    }

    private void delDevNetworkInfo() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            KLog.m65e("配网 清除配网");
            ApiHelper.getDeviceApi().delDevNetWorkInfo(token, this.mac.replace(":", "")).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    AddWiFiModel.this.showFailDialog(str);
                }
            });
        }
    }

    private void scanStart() {
        if (!this.animator.isStarted()) {
            this.btnVisible.setValue(false);
            this.reconnectVisible.setValue(false);
            this.contentVisible.setValue(true);
            this.stateText.setValue(Utils.getString(C1922R.string.tip_device_connecting));
            this.contentText.setValue(Utils.getString(C1922R.string.tip_connect_time));
            this.state.setValue(Integer.valueOf(C1922R.mipmap.wifi_icon_white));
            this.animator.start();
        }
    }

    /* access modifiers changed from: private */
    public void scanEnd(boolean z) {
        this.animator.end();
        if (z) {
            this.state.setValue(Integer.valueOf(C1922R.mipmap.success));
            this.stateText.setValue(Utils.getString(C1922R.string.tip_connect_success, this.typeName, "New Device"));
            this.contentVisible.setValue(false);
            this.reconnectVisible.setValue(false);
            this.btnText.setValue("DONE");
        } else {
            this.state.setValue(Integer.valueOf(C1922R.mipmap.controller_69_err));
            this.stateText.setValue("CHECK THE CONTROLLER’S SCREEN");
            this.contentVisible.setValue(true);
            this.contentText.setValue("Reconnect with your 2.4GHz network or try again if the Wi-Fi icon is not flashing.");
            this.reconnectVisible.setValue(true);
            this.btnText.setValue("TRY AGAIN");
        }
        this.btnVisible.setValue(true);
        this.maxVisible.setValue(true);
        this.normalVisible.setValue(true);
        this.minVisible.setValue(true);
    }

    public void cancel() {
        this.isCancel = true;
        RxHttpUtils.cancel(TAG);
        if (!this.isConnected) {
            delDevNetworkInfo();
        }
    }
}
