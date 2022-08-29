package com.eternal.account;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.transition.TransitionManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.account.databinding.ActivityShareBinding;
import com.eternal.account.model.ShareItemModel;
import com.eternal.account.model.ShareModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.global.ProgressEvent;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.Utils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class ShareActivity extends BaseActivity<ActivityShareBinding, ShareModel> {
    public static final String SHOW_ACCEPT_DIALOG = "show accpet dialog";
    public static final String SHOW_DELETE_DIALOG = "show delete dialog";
    /* access modifiers changed from: private */
    public ConstraintSet applyConstraintSet = new ConstraintSet();
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    private Disposable progressEvent;
    boolean showShareDot;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMessage();
        register();
        ((ShareModel) this.viewModel).init(this.showShareDot);
        initView();
        bindingEvent();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        unregister();
        super.onDestroy();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_share;
    }

    public int initVariableId() {
        return C0977BR.shareModel;
    }

    private void register() {
        this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
            public void accept(ProgressEvent progressEvent) throws Exception {
                if (progressEvent.Statue == 0) {
                    ((ActivityShareBinding) ShareActivity.this.binding).toolBar.startProgress();
                } else if (progressEvent.Statue == 1) {
                    ((ActivityShareBinding) ShareActivity.this.binding).toolBar.endProgress(progressEvent.callback);
                }
            }
        });
    }

    private void bindingEvent() {
        ((ShareModel) this.viewModel).selectWithYou.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                TransitionManager.beginDelayedTransition(((ActivityShareBinding) ShareActivity.this.binding).ctTab);
                if (bool.booleanValue()) {
                    ShareActivity.this.applyConstraintSet.connect(C0997R.C1000id.v_slide, 6, C0997R.C1000id.ll_with_you, 6, 0);
                    ShareActivity.this.applyConstraintSet.connect(C0997R.C1000id.v_slide, 7, C0997R.C1000id.ll_with_you, 7, 0);
                } else {
                    ShareActivity.this.applyConstraintSet.connect(C0997R.C1000id.v_slide, 6, C0997R.C1000id.tv_with_others, 6, 0);
                    ShareActivity.this.applyConstraintSet.connect(C0997R.C1000id.v_slide, 7, C0997R.C1000id.tv_with_others, 7, 0);
                }
                ShareActivity.this.applyConstraintSet.applyTo(((ActivityShareBinding) ShareActivity.this.binding).ctTab);
            }
        });
    }

    private void unregister() {
        Disposable disposable = this.progressEvent;
        if (disposable != null) {
            disposable.dispose();
            this.progressEvent = null;
        }
    }

    private void initMessage() {
        Messenger messenger = Messenger.getDefault();
        messenger.register((Object) this, (Object) "show accpet dialog", ShareItemModel.class, new BindingConsumer<ShareItemModel>() {
            public void call(final ShareItemModel shareItemModel) {
                ShareActivity shareActivity = ShareActivity.this;
                ShareActivity shareActivity2 = shareActivity;
                MaterialDialog unused = shareActivity.dialog = TipDialog.showTipDialog(shareActivity2, "Invitation", Utils.getString(C0997R.string.tip_invitation_content, shareItemModel.model.appEmail, shareItemModel.model.deviceName), ShareActivity.this.getResources().getString(C0997R.string.tip_decline), ShareActivity.this.getResources().getString(C0997R.string.tip_accept), true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ShareModel) ShareActivity.this.viewModel).deleteShare(shareItemModel.model.deviceId, shareItemModel.model.appEmail);
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ShareModel) ShareActivity.this.viewModel).acceptShare(shareItemModel.model.shareId);
                    }
                });
            }
        });
        messenger.register((Object) this, (Object) "show delete dialog", ShareItemModel.class, new BindingConsumer<ShareItemModel>() {
            public void call(final ShareItemModel shareItemModel) {
                String str;
                if (((ShareModel) ShareActivity.this.viewModel).selectWithYou.getValue() == Boolean.TRUE) {
                    ((ActivityShareBinding) ShareActivity.this.binding).srvWithYou.closeMenu();
                    str = "you";
                } else {
                    str = shareItemModel.model.appEmail;
                    ((ActivityShareBinding) ShareActivity.this.binding).srvWithOther.closeMenu();
                }
                ShareActivity shareActivity = ShareActivity.this;
                String string = shareActivity.getString(C0997R.string.tip_remove_device_title, new Object[]{shareItemModel.model.deviceName});
                MaterialDialog unused = shareActivity.dialog = TipDialog.showTipDialog(shareActivity, string, "Once removed, " + str + " will no longer be able to control \"" + shareItemModel.model.deviceName + "\".", ShareActivity.this.getResources().getString(C0997R.string.tip_cancel_lowercase), ShareActivity.this.getResources().getString(C0997R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ShareModel) ShareActivity.this.viewModel).deleteShare(shareItemModel.model.deviceId, shareItemModel.model.appEmail);
                    }
                });
            }
        });
    }

    private void initView() {
        this.applyConstraintSet.clone(((ActivityShareBinding) this.binding).ctTab);
    }

    public void onBackPressed() {
        ((ShareModel) this.viewModel).onBack.execute();
    }
}
