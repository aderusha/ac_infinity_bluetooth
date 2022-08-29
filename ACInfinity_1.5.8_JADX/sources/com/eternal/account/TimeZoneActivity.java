package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivityTimeZoneBinding;
import com.eternal.account.model.ShareItemModel;
import com.eternal.account.model.TimeZoneModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ProgressEvent;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class TimeZoneActivity extends BaseActivity<ActivityTimeZoneBinding, TimeZoneModel> {
    public static final String SHOW_ACCEPT_DIALOG = "show accpet dialog";
    public static final String SHOW_DELETE_DIALOG = "show delete dialog";
    private Disposable progressEvent;
    boolean showShareDot;

    private void initView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMessage();
        register();
        ((TimeZoneModel) this.viewModel).init(this.showShareDot);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        unregister();
        super.onDestroy();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_time_zone;
    }

    public int initVariableId() {
        return C0977BR.timeZoneModel;
    }

    private void register() {
        this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
            public void accept(ProgressEvent progressEvent) throws Exception {
                if (progressEvent.Statue == 0) {
                    ((ActivityTimeZoneBinding) TimeZoneActivity.this.binding).toolBar.startProgress();
                } else if (progressEvent.Statue == 1) {
                    ((ActivityTimeZoneBinding) TimeZoneActivity.this.binding).toolBar.endProgress(progressEvent.callback);
                }
            }
        });
    }

    private void unregister() {
        if (this.progressEvent != null) {
            this.progressEvent = null;
        }
    }

    private void initMessage() {
        Messenger messenger = Messenger.getDefault();
        messenger.register((Object) this, (Object) "show accpet dialog", ShareItemModel.class, new BindingConsumer<ShareItemModel>() {
            public void call(ShareItemModel shareItemModel) {
            }
        });
        messenger.register((Object) this, (Object) "show delete dialog", ShareItemModel.class, new BindingConsumer<ShareItemModel>() {
            public void call(ShareItemModel shareItemModel) {
            }
        });
    }

    public void onBackPressed() {
        ((TimeZoneModel) this.viewModel).onBack.execute();
    }
}
