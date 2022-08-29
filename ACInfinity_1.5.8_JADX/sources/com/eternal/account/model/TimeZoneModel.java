package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class TimeZoneModel extends BaseViewModel {
    public final ItemBinding<TimeZoneItemModel> itemBinding = ItemBinding.m1008of(C0977BR.itemTimeZone, C0997R.layout.item_time_zone);
    public ObservableList<TimeZoneItemModel> items = new ObservableArrayList();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            TimeZoneModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    MutableLiveData<Boolean> showEmpty = new MutableLiveData<>();

    public void deleteShare(String str) {
    }

    public void init(boolean z) {
    }

    public TimeZoneModel(Application application) {
        super(application);
    }

    private void fetchDevices() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getDeviceApi().devInfoListAll(token).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<NetDevice>>() {
                public void doOnSubscribe(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }

                /* access modifiers changed from: protected */
                public String setTag() {
                    Class<TimeZoneModel> cls = TimeZoneModel.class;
                    return "TimeZoneModel";
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    TimeZoneModel.this.showFailDialog(str);
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }

                /* access modifiers changed from: protected */
                public void onSuccess(List<NetDevice> list) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    if (list == null || list.size() == 0) {
                        list = new ArrayList<>();
                    }
                    LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
                    for (NetDevice next : list) {
                        if (!newLinkedHashMap.containsKey(next.appEmail)) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(next);
                            newLinkedHashMap.put(next.getAppEmail(), arrayList);
                        } else {
                            List list2 = (List) newLinkedHashMap.get(next.appEmail);
                            if (list2 != null) {
                                list2.add(next);
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (List list3 : newLinkedHashMap.values()) {
                        for (int i = 0; i < list3.size(); i++) {
                            arrayList2.add(new TimeZoneItemModel((NetDevice) list3.get(i)));
                        }
                    }
                    TimeZoneModel.this.items.clear();
                    TimeZoneModel.this.items.addAll(arrayList2);
                }
            });
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        fetchDevices();
    }
}
