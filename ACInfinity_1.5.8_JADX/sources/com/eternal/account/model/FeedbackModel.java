package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.base.TipDialog;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.observer.DataObserver;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class FeedbackModel extends BaseViewModel {
    public static final String DELETE_ITEM = "delete item";
    boolean commitSuccess;
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<String> deviceName = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public BindingCommand<String> emailTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            FeedbackModel feedbackModel = FeedbackModel.this;
            feedbackModel.onTextChanged(str, feedbackModel.titleText.getValue(), FeedbackModel.this.messageText.getValue());
        }
    });
    public ItemBinding<PhotoItemModel> itemBinding = ItemBinding.m1008of(C0977BR.itemPhoto, C0997R.layout.item_photo);
    public ObservableList<PhotoItemModel> items = new ObservableArrayList();
    public MutableLiveData<String> messageText = new MutableLiveData<>();
    public BindingCommand<String> messageTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            FeedbackModel feedbackModel = FeedbackModel.this;
            feedbackModel.onTextChanged(feedbackModel.emailText.getValue(), FeedbackModel.this.titleText.getValue(), str);
        }
    });
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (FeedbackModel.this.commitSuccess) {
                FeedbackModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
            } else if (!TextUtils.isEmpty(FeedbackModel.this.emailText.getValue()) || !TextUtils.isEmpty(FeedbackModel.this.emailText.getValue()) || !TextUtils.isEmpty(FeedbackModel.this.emailText.getValue()) || FeedbackModel.this.items.size() > 0) {
                TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), "Discard Feedback", "Are you sure you want to discard this feedback?", "Stay", "Discard", true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        FeedbackModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
                    }
                });
            } else {
                FeedbackModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
            }
        }
    });
    public BindingCommand<Void> onSend = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            FeedbackModel.this.send();
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> titleText = new MutableLiveData<>();
    public BindingCommand<String> titleTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            FeedbackModel feedbackModel = FeedbackModel.this;
            feedbackModel.onTextChanged(feedbackModel.emailText.getValue(), str, FeedbackModel.this.messageText.getValue());
        }
    });

    public FeedbackModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str, String str2, String str3) {
        this.confirmAble.setValue(Boolean.valueOf(RegexUtils.isEmail(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)));
    }

    public void send() {
        String value = this.emailText.getValue();
        String value2 = this.titleText.getValue();
        String value3 = this.messageText.getValue();
        this.showLoading.setValue(true);
        MultipartBody.Builder addFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("", "");
        for (PhotoItemModel photoItemModel : this.items) {
            String str = photoItemModel.thumbUrl.get();
            if (str != null) {
                File file = new File(str);
                addFormDataPart.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }
        }
        ApiHelper.getAccountApi().addUserFeedBack(value, value2, value3, "android, v" + AppUtils.getAppVersionName(), addFormDataPart.build().parts()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                FeedbackModel.this.showLoading.setValue(false);
                FeedbackModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                FeedbackModel.this.showLoading.setValue(false);
                FeedbackModel.this.commitSuccess = true;
                TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), "Thank You!", "We’ve received your feedback. Thank you for helping us make the AC Infinity App better. ", "", Utils.getString(C0997R.string.f105OK), true, false, (View.OnClickListener) null, new View.OnClickListener() {
                    public void onClick(View view) {
                        FeedbackModel.this.onBack.execute();
                    }
                });
            }
        });
    }

    public void init() {
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
        initEmail();
    }

    private void initEmail() {
        addSubscribe(Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                singleEmitter.onSuccess(UserCache.getInstance().getEmail());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m983io()).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if (!TextUtils.isEmpty(str)) {
                    FeedbackModel.this.emailText.setValue(str);
                }
            }
        }));
    }

    public void registerRxBus() {
        Messenger.getDefault().register((Object) this, (Object) "delete item", PhotoItemModel.class, new BindingConsumer<PhotoItemModel>() {
            public void call(PhotoItemModel photoItemModel) {
                FeedbackModel.this.items.remove(photoItemModel);
            }
        });
    }

    public void unregisterRxBus() {
        super.unregisterRxBus();
    }
}
