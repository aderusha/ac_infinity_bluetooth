package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import p014io.reactivex.Observable;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.OnItemBind;
import p018me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;

public class ShareModel extends BaseViewModel {
    DiffObservableList.Callback<ShareItemModel> diffCallback = new DiffObservableList.Callback<ShareItemModel>() {
        public boolean areItemsTheSame(ShareItemModel shareItemModel, ShareItemModel shareItemModel2) {
            if (shareItemModel.model == null || shareItemModel2.model == null) {
                if (!shareItemModel.isHeader || !shareItemModel2.isHeader || shareItemModel.headTxt.getValue() == null || shareItemModel2.headTxt.getValue() == null) {
                    return false;
                }
                return shareItemModel.headTxt.getValue().equals(shareItemModel2.headTxt.getValue());
            } else if (!shareItemModel.model.shareId.equals(shareItemModel2.model.shareId) || !shareItemModel.model.deviceId.equals(shareItemModel2.model.deviceId)) {
                return false;
            } else {
                return true;
            }
        }

        public boolean areContentsTheSame(ShareItemModel shareItemModel, ShareItemModel shareItemModel2) {
            if (shareItemModel.model == null || shareItemModel2.model == null) {
                if (!shareItemModel.isHeader || !shareItemModel2.isHeader || shareItemModel.headTxt.getValue() == null || shareItemModel2.headTxt.getValue() == null) {
                    return false;
                }
                return shareItemModel.headTxt.getValue().equals(shareItemModel2.headTxt.getValue());
            } else if (shareItemModel.model.isShare != shareItemModel2.model.isShare || !shareItemModel.model.deviceName.equals(shareItemModel2.model.deviceName)) {
                return false;
            } else {
                return true;
            }
        }
    };
    public final OnItemBind<ShareItemModel> itemBinding = new OnItemBind<ShareItemModel>() {
        public void onItemBind(ItemBinding itemBinding, int i, ShareItemModel shareItemModel) {
            itemBinding.set(C0977BR.itemShare, shareItemModel.isHeader ? C0997R.layout.item_header : C0997R.layout.item_share);
        }
    };
    public DiffObservableList<ShareItemModel> items = new DiffObservableList<>(this.diffCallback);
    public DiffObservableList<ShareItemModel> items2 = new DiffObservableList<>(this.diffCallback);
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ShareModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_INVITE).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation();
        }
    });
    public BindingCommand<Void> onWithOthers = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ShareModel.this.selectWithYou.setValue(false);
            ShareModel shareModel = ShareModel.this;
            shareModel.fetchDevices(shareModel.selectWithYou.getValue().booleanValue());
        }
    });
    public BindingCommand<Void> onWithYou = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ShareModel.this.selectWithYou.setValue(true);
            ShareModel shareModel = ShareModel.this;
            shareModel.fetchDevices(shareModel.selectWithYou.getValue().booleanValue());
        }
    });
    public MutableLiveData<Boolean> selectWithYou = new MutableLiveData<>();
    public MutableLiveData<Boolean> showDot = new MutableLiveData<>();
    public MutableLiveData<Boolean> showEmpty = new MutableLiveData<>();
    public MutableLiveData<Boolean> showEmpty2 = new MutableLiveData<>();

    public ShareModel(Application application) {
        super(application);
    }

    public void init(boolean z) {
        this.showDot.setValue(Boolean.valueOf(z));
        this.selectWithYou.setValue(false);
        RxBus.getDefault().post(new ProgressEvent((byte) 0));
        fetchDevices(false);
        fetchDevices(true);
    }

    /* access modifiers changed from: private */
    public void fetchDevices(final boolean z) {
        Observable<BaseData<List<NetDevice>>> observable;
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            if (z) {
                observable = ApiHelper.getDeviceApi().shareWithYouDevList(token);
            } else {
                observable = ApiHelper.getDeviceApi().shareWithOtherDevList(token);
            }
            observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<NetDevice>>() {
                /* access modifiers changed from: protected */
                public String setTag() {
                    Class<ShareModel> cls = ShareModel.class;
                    return "ShareModel";
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    ShareModel.this.showFailDialog(str);
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    if (z) {
                        ShareModel.this.showEmpty2.setValue(Boolean.valueOf(ShareModel.this.items2.isEmpty()));
                    } else {
                        ShareModel.this.showEmpty.setValue(Boolean.valueOf(ShareModel.this.items.isEmpty()));
                    }
                }

                /* access modifiers changed from: protected */
                public void onSuccess(List<NetDevice> list) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    if (list == null || list.size() == 0) {
                        list = new ArrayList<>();
                        if (z) {
                            ShareModel.this.showEmpty2.setValue(true);
                        } else {
                            ShareModel.this.showEmpty.setValue(true);
                        }
                    } else if (z) {
                        ShareModel.this.showEmpty2.setValue(false);
                    } else {
                        ShareModel.this.showEmpty.setValue(false);
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
                    boolean z = false;
                    for (List list3 : newLinkedHashMap.values()) {
                        for (int i = 0; i < list3.size(); i++) {
                            if (i == 0) {
                                String str = z ? "From " : "";
                                arrayList2.add(new ShareItemModel(str + ((NetDevice) list3.get(i)).appEmail, true));
                            }
                            arrayList2.add(new ShareItemModel((NetDevice) list3.get(i), z));
                            if (((NetDevice) list3.get(i)).isShare == 0) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        ShareModel.this.items2.update(arrayList2);
                        ShareModel.this.showDot.setValue(Boolean.valueOf(z));
                        RxBus.getDefault().post(new ActivityEvent(40, Boolean.valueOf(z)));
                        return;
                    }
                    ShareModel.this.items.update(arrayList2);
                }
            });
        }
    }

    public void acceptShare(String str) {
        ApiHelper.getDeviceApi().acceptShare(str).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                Class<ShareModel> cls = ShareModel.class;
                return "ShareModel";
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                ShareModel.this.showFailDialog(str);
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                ShareModel shareModel = ShareModel.this;
                shareModel.fetchDevices(shareModel.selectWithYou.getValue().booleanValue());
            }
        });
    }

    public void deleteShare(String str, String str2) {
        Observable<BaseData<Void>> observable;
        if (!TextUtils.isEmpty(UserCache.getInstance().getToken())) {
            if (this.selectWithYou.getValue() == Boolean.TRUE) {
                observable = ApiHelper.getDeviceApi().delShareDev(str, str2);
            } else {
                observable = ApiHelper.getDeviceApi().cancelShareDev(str, str2);
            }
            observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }

                /* access modifiers changed from: protected */
                public String setTag() {
                    Class<ShareModel> cls = ShareModel.class;
                    return "ShareModel";
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    ShareModel.this.showFailDialog(str);
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    ShareModel shareModel = ShareModel.this;
                    shareModel.fetchDevices(shareModel.selectWithYou.getValue().booleanValue());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        fetchDevices(false);
    }
}
