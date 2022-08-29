package com.eternal.device.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.TipDialog;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.NetServe;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.AppUtils;
import com.eternal.res.C2663R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.List;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class GuideModel extends BaseViewModel {
    /* access modifiers changed from: private */
    public byte deviceType;
    public MutableLiveData<Boolean> helpVisible = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            GuideModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Activity currentActivity = AppManager.getAppManager().currentActivity();
            TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), currentActivity.getString(C2663R.string.tip_exit_add_device), currentActivity.getString(C2663R.string.tip_exit_add_device_desc), currentActivity.getString(C2663R.string.tip_do_not_exit), currentActivity.getString(C2663R.string.tip_exit), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    GuideModel.this.pushToMain();
                }
            });
        }
    });
    public BindingCommand<Void> onHelp = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Device.PAGE_HELP).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).withByte("page type", (byte) 0).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (GuideModel.this.pageType == 0) {
                if (GuideModel.this.deviceType == 7) {
                    ARouter.getInstance().build(RouterActivityPath.Device.PAGE_GUIDE).withByte("page type", (byte) 1).withByte(ActivityEvent.DEVICE_TYPE, GuideModel.this.deviceType).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
                } else if (GuideModel.this.deviceType != 8) {
                    GuideModel guideModel = GuideModel.this;
                    guideModel.pushToAddPage(guideModel.deviceType);
                }
            } else if (GuideModel.this.deviceType != 8) {
                GuideModel guideModel2 = GuideModel.this;
                guideModel2.pushToAddPage(guideModel2.deviceType);
            } else if (TextUtils.isEmpty(GuideModel.this.socketIp) || GuideModel.this.socketPort == 0) {
                GuideModel.this.querySocketIp(true);
            } else {
                GuideModel.this.pushToWiFiPage();
            }
        }
    });
    public BindingCommand<Void> onSetting = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            GuideModel.toSelfSetting(AppManager.getAppManager().currentActivity());
        }
    });
    /* access modifiers changed from: private */
    public byte pageType;
    public MutableLiveData<Boolean> permissionVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String socketIp;
    /* access modifiers changed from: private */
    public int socketPort;

    public GuideModel(Application application) {
        super(application);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        String[] permissionStrings = getPermissionStrings(this.deviceType);
        if (permissionStrings != null) {
            boolean lacksPermissions = AppUtils.lacksPermissions(AppManager.getAppManager().currentActivity(), permissionStrings);
            if (this.permissionVisible.getValue() != null) {
                this.permissionVisible.setValue(Boolean.valueOf(!lacksPermissions));
            }
        }
    }

    /* access modifiers changed from: private */
    public void pushToAddPage(byte b) {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_ADD).withByte(ActivityEvent.DEVICE_TYPE, b).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: private */
    public void pushToWiFiPage() {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_WIFI).withString(ActivityEvent.SOCKET_IP, this.socketIp).withInt(ActivityEvent.SOCKET_PORT, this.socketPort).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
            }
        });
    }

    public void querySocketIp(final boolean z) {
        if (z) {
            this.showLoading.setValue(true);
        }
        ApiHelper.getDeviceApi().getSockerIp().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<NetServe>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                if (z) {
                    GuideModel.this.showLoading.setValue(false);
                    GuideModel.this.showFailDialog(str);
                }
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<NetServe> baseData) {
                if (z) {
                    GuideModel.this.showLoading.setValue(false);
                }
                if (baseData.getCode() == 200 && baseData.getData() != null && !TextUtils.isEmpty(baseData.getData().socketIp)) {
                    String unused = GuideModel.this.socketIp = baseData.getData().socketIp;
                    int unused2 = GuideModel.this.socketPort = baseData.getData().socketPort;
                    if (z) {
                        GuideModel.this.pushToWiFiPage();
                    }
                } else if (z) {
                    GuideModel.this.showFailDialog(baseData.getMsg());
                }
            }
        });
    }

    public void init(byte b, byte b2, String str, int i) {
        this.pageType = b;
        this.deviceType = b2;
        this.socketIp = str;
        this.socketPort = i;
        if (b2 == 8) {
            querySocketIp(false);
            this.helpVisible.setValue(true);
        }
        initPermissions(b2);
    }

    public static void toSelfSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), (String) null));
        context.startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void initPermissions(byte b) {
        String[] permissionStrings = getPermissionStrings(b);
        if (permissionStrings != null) {
            addSubscribe(new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity()).request(permissionStrings).toList().subscribe(new Consumer<List<Boolean>>() {
                public void accept(List<Boolean> list) throws Exception {
                    GuideModel.this.permissionVisible.setValue(Boolean.valueOf(!list.get(0).booleanValue()));
                }
            }));
        }
    }

    private String[] getPermissionStrings(byte b) {
        if (b == 8) {
            if (Build.VERSION.SDK_INT < 30) {
                return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
            }
            return null;
        } else if (Build.VERSION.SDK_INT >= 31) {
            return new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
        } else {
            return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
    }
}
