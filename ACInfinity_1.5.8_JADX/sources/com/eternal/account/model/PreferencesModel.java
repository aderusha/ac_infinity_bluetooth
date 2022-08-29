package com.eternal.account.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0997R;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.UserInfo;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.schedulers.Schedulers;

public class PreferencesModel extends BaseViewModel {
    public BindingCommand<Boolean> analyticsChange = new BindingCommand<>(new BindingConsumer<Boolean>() {
        public void call(Boolean bool) {
            if (bool != PreferencesModel.this.anonymousAnalytics.getValue()) {
                PreferencesModel.this.anonymousAnalytics.setValue(bool);
                if (PreferencesModel.this.isPrivacy.getValue() == Boolean.TRUE) {
                    PreferencesModel.this.commit();
                }
            }
        }
    });
    public MutableLiveData<Boolean> anonymousAnalytics = new MutableLiveData<>();
    public MutableLiveData<Boolean> bugReports = new MutableLiveData<>();
    public MutableLiveData<Boolean> emailSubscription = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPrivacy = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (PreferencesModel.this.isPrivacy.getValue() == Boolean.TRUE) {
                PreferencesModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
            }
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            PreferencesModel.this.commit();
        }
    });
    public BindingCommand<Boolean> reportsChange = new BindingCommand<>(new BindingConsumer<Boolean>() {
        public void call(Boolean bool) {
            if (bool != PreferencesModel.this.bugReports.getValue()) {
                PreferencesModel.this.bugReports.setValue(bool);
                if (PreferencesModel.this.isPrivacy.getValue() == Boolean.TRUE) {
                    PreferencesModel.this.commit();
                }
            }
        }
    });
    public BindingCommand<Boolean> subscriptionChange = new BindingCommand<>(new BindingConsumer<Boolean>() {
        public void call(Boolean bool) {
            if (bool != PreferencesModel.this.emailSubscription.getValue()) {
                PreferencesModel.this.emailSubscription.setValue(bool);
                if (PreferencesModel.this.isPrivacy.getValue() == Boolean.TRUE) {
                    PreferencesModel.this.commit();
                }
            }
        }
    });

    public PreferencesModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void commit() {
        int i = 1;
        int i2 = this.anonymousAnalytics.getValue() == Boolean.TRUE ? 1 : 0;
        int i3 = this.bugReports.getValue() == Boolean.TRUE ? 1 : 0;
        if (this.emailSubscription.getValue() != Boolean.TRUE) {
            i = 0;
        }
        ApiHelper.getAccountApi().updateAPPUser(UserCache.getInstance().getToken(), i2, i3, i).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<String>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                PreferencesModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(String str) {
                if (PreferencesModel.this.isPrivacy.getValue() == Boolean.FALSE) {
                    RxBus.getDefault().post(new ActivityEvent(20));
                    PreferencesModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
                }
            }
        });
    }

    private void query() {
        ApiHelper.getAccountApi().getByUser(UserCache.getInstance().getToken()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<UserInfo>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                PreferencesModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(UserInfo userInfo) {
                boolean z = true;
                PreferencesModel.this.anonymousAnalytics.setValue(Boolean.valueOf(userInfo.getIsAnalytics() != 0));
                PreferencesModel.this.bugReports.setValue(Boolean.valueOf(userInfo.getIsBugReportOpen() != 0));
                MutableLiveData<Boolean> mutableLiveData = PreferencesModel.this.emailSubscription;
                if (userInfo.getIsEmailRepost() == 0) {
                    z = false;
                }
                mutableLiveData.setValue(Boolean.valueOf(z));
            }
        });
    }

    public void init(boolean z) {
        this.isPrivacy.setValue(Boolean.valueOf(z));
        query();
    }
}
