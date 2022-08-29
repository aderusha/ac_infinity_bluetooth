package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.InviteActivity;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class InviteModel extends BaseViewModel {
    static final String ITEM_SELECT = "select item";
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    String devId;
    public MutableLiveData<Integer> emailColor = new MutableLiveData<>();
    public MutableLiveData<String> emailErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> emailErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> emailLineColor = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public BindingCommand<String> emailTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            InviteModel inviteModel = InviteModel.this;
            inviteModel.refreshState(str, inviteModel.hasSelect);
        }
    });
    boolean hasSelect;
    public ItemBinding<InviteItemModel> itemBinding = ItemBinding.m1008of(C0977BR.itemInvite, C0997R.layout.item_invite);
    public ObservableList<InviteItemModel> items = new ObservableArrayList();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            InviteModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(InviteModel.this, InviteActivity.SHOW_SEND_DIALOG);
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();

    public void registerRxBus() {
    }

    public InviteModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void refrehSelect() {
        boolean z;
        Iterator it = this.items.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((InviteItemModel) it.next()).isSelect.get()) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        this.hasSelect = z;
        refreshState(this.emailText.getValue(), this.hasSelect);
    }

    /* access modifiers changed from: private */
    public void refreshState(String str, boolean z) {
        boolean isEmail = RegexUtils.isEmail(str);
        if (isEmail && this.emailErrVisible.getValue() == Boolean.TRUE) {
            this.emailErrVisible.setValue(Boolean.FALSE);
            this.emailLineColor.setValue(this.emailColor.getValue());
        }
        this.confirmAble.setValue(Boolean.valueOf(isEmail && z));
    }

    public void shareDev() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            String[] selectDeviceId = getSelectDeviceId();
            if (selectDeviceId.length != 0) {
                this.showLoading.setValue(true);
                ApiHelper.getDeviceApi().shareDev(token, this.emailText.getValue(), selectDeviceId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<Void>>() {
                    /* access modifiers changed from: protected */
                    public void onError(String str) {
                        InviteModel.this.showLoading.setValue(false);
                        InviteModel.this.emailErrText.setValue(str);
                        InviteModel.this.emailErrVisible.setValue(true);
                    }

                    /* access modifiers changed from: protected */
                    public void onSuccess(BaseData<Void> baseData) {
                        InviteModel.this.showLoading.setValue(false);
                        if (baseData.getCode() == 200) {
                            if (!TextUtils.isEmpty(baseData.getMsg())) {
                                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), baseData.getMsg());
                            }
                            InviteModel.this.onBack.execute();
                            return;
                        }
                        InviteModel.this.emailErrText.setValue(baseData.getMsg());
                        InviteModel.this.emailErrVisible.setValue(true);
                    }
                });
            }
        }
    }

    public void init(String str) {
        this.devId = str;
        initMessenger();
        fetchDevices();
        this.emailLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.emailColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
    }

    private void fetchDevices() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getDeviceApi().devInfoListAll(token).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<NetDevice>>() {
                /* access modifiers changed from: protected */
                public String setTag() {
                    Class<InviteModel> cls = InviteModel.class;
                    return "InviteModel";
                }

                public void doOnSubscribe(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    InviteModel.this.showFailDialog(str);
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }

                /* access modifiers changed from: protected */
                public void onSuccess(List<NetDevice> list) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (i < list.size()) {
                        NetDevice netDevice = list.get(i);
                        if (netDevice.isShare == 0) {
                            arrayList.add(new InviteItemModel(netDevice, TextUtils.isEmpty(InviteModel.this.devId) ? i == 0 : InviteModel.this.devId.equalsIgnoreCase(netDevice.deviceId)));
                        }
                        i++;
                    }
                    InviteModel.this.items.addAll(arrayList);
                    InviteModel.this.refrehSelect();
                }
            });
        }
    }

    private void initMessenger() {
        Messenger.getDefault().register((Object) this, (Object) ITEM_SELECT, InviteItemModel.class, new BindingConsumer<InviteItemModel>() {
            public void call(InviteItemModel inviteItemModel) {
                InviteModel.this.refrehSelect();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    public String getDeviceNames() {
        StringBuffer stringBuffer = new StringBuffer();
        for (InviteItemModel inviteItemModel : this.items) {
            if (inviteItemModel.isSelect.get()) {
                stringBuffer.append(", ");
                stringBuffer.append(inviteItemModel.model.deviceName);
            }
        }
        return stringBuffer.length() > 0 ? stringBuffer.substring(2) : "";
    }

    public String[] getSelectDeviceId() {
        ArrayList arrayList = new ArrayList();
        for (InviteItemModel inviteItemModel : this.items) {
            if (inviteItemModel.isSelect.get()) {
                arrayList.add(inviteItemModel.model.deviceId);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
