package com.eternal.device.model;

import android.app.Application;
import android.content.Context;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.WiFiActivity;
import com.eternal.device.utils.wifimanager.IWifi;
import com.eternal.device.utils.wifimanager.IWifiManager;
import com.eternal.device.utils.wifimanager.OnWifiChangeListener;
import com.eternal.device.utils.wifimanager.OnWifiConnectListener;
import com.eternal.device.utils.wifimanager.OnWifiStateChangeListener;
import com.eternal.device.utils.wifimanager.State;
import com.eternal.device.utils.wifimanager.WifiManager;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class WiFiListModel extends BaseViewModel implements OnWifiChangeListener, OnWifiConnectListener, OnWifiStateChangeListener {
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public ItemBinding<ItemModel> itemBinding = ItemBinding.m1008of(C1909BR.item, C1922R.layout.item_wifi);
    public ObservableList<ItemModel> items = new ObservableArrayList();
    IWifiManager manager;
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiListModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiListModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onHelp = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Device.PAGE_HELP).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).withByte("page type", (byte) 1).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onRefresh = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiListModel.this.manager.scanWifi();
            WiFiListModel.this.addSubscribe(Single.timer(5, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                public void accept(Long l) throws Exception {
                    WiFiListModel.this.isLoading.setValue(false);
                }
            }));
        }
    });
    private String socketIp;
    private int socketPort;

    public void onConnectChanged(boolean z) {
    }

    public void onStateChanged(State state) {
    }

    public WiFiListModel(Application application) {
        super(application);
    }

    public void init(String str, int i) {
        this.socketIp = str;
        this.socketPort = i;
        IWifiManager create = WifiManager.create(AppManager.getAppManager().currentActivity());
        this.manager = create;
        create.setOnWifiChangeListener(this);
        this.manager.setOnWifiConnectListener(this);
        this.manager.setOnWifiStateChangeListener(this);
    }

    public void unregisterReceiver() {
        IWifiManager iWifiManager = this.manager;
        if (iWifiManager != null) {
            iWifiManager.destroy();
        }
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiActivity.class);
                WiFiListModel.this.finish();
            }
        });
    }

    public void onWifiChanged(List<IWifi> list) {
        this.items.clear();
        for (IWifi itemModel : list) {
            this.items.add(new ItemModel(itemModel, this.socketIp, this.socketPort));
        }
    }
}
