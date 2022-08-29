package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivitySearchBinding;
import com.eternal.account.model.SearchModel;
import com.eternal.account.model.ShareItemModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ProgressEvent;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchModel> {
    public static final String SHOW_ACCEPT_DIALOG = "show accpet dialog";
    public static final String SHOW_DELETE_DIALOG = "show delete dialog";
    String devId;
    private Disposable progressEvent;
    String timeZone;

    private void initView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMessage();
        register();
        ((SearchModel) this.viewModel).init(this.devId, this.timeZone);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        unregister();
        super.onDestroy();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_search;
    }

    public int initVariableId() {
        return C0977BR.searchModel;
    }

    private void register() {
        this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
            public void accept(ProgressEvent progressEvent) throws Exception {
                if (progressEvent.Statue == 0) {
                    ((ActivitySearchBinding) SearchActivity.this.binding).toolBar.startProgress();
                } else if (progressEvent.Statue == 1) {
                    ((ActivitySearchBinding) SearchActivity.this.binding).toolBar.endProgress(progressEvent.callback);
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
        ((SearchModel) this.viewModel).onBack.execute();
    }
}
