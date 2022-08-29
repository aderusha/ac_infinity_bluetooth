package com.eternal.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.account.databinding.ActivityInviteBinding;
import com.eternal.account.model.InviteModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.RegexUtils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class InviteActivity extends BaseActivity<ActivityInviteBinding, InviteModel> {
    public static final String SHOW_SEND_DIALOG = "show send dialog";
    String devId;
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    private Disposable progressEvent;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityInviteBinding) InviteActivity.this.binding).ibNext.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityInviteBinding) InviteActivity.this.binding).ibNext.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        register();
        ((InviteModel) this.viewModel).init(this.devId);
        initView();
        initMessage();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        unregister();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        super.onDestroy();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_invite;
    }

    public int initVariableId() {
        return C0977BR.inviteModel;
    }

    private void register() {
        this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
            public void accept(ProgressEvent progressEvent) throws Exception {
                if (progressEvent.Statue == 0) {
                    ((ActivityInviteBinding) InviteActivity.this.binding).toolBar.startProgress();
                } else if (progressEvent.Statue == 1) {
                    ((ActivityInviteBinding) InviteActivity.this.binding).toolBar.endProgress(progressEvent.callback);
                }
            }
        });
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) SHOW_SEND_DIALOG, InviteModel.class, new BindingConsumer<InviteModel>() {
            public void call(InviteModel inviteModel) {
                InviteActivity inviteActivity = InviteActivity.this;
                MaterialDialog unused = inviteActivity.dialog = TipDialog.showTipDialog(inviteActivity, "Send an Invite", "Share your \"" + inviteModel.getDeviceNames() + "\" with " + inviteModel.emailText.getValue() + "?", InviteActivity.this.getResources().getString(C0997R.string.tip_cancel_lowercase), InviteActivity.this.getResources().getString(C0997R.string.tip_send_lowercase), true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((InviteModel) InviteActivity.this.viewModel).shareDev();
                    }
                });
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

    private void initView() {
        new SoftKeyBroadManager(((ActivityInviteBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        ((ActivityInviteBinding) this.binding).etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityInviteBinding) InviteActivity.this.binding).etEmail.getId()) {
                    return;
                }
                if (z) {
                    ((InviteModel) InviteActivity.this.viewModel).emailColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((InviteModel) InviteActivity.this.viewModel).emailErrVisible.getValue() != Boolean.TRUE) {
                        ((InviteModel) InviteActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((InviteModel) InviteActivity.this.viewModel).emailText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((InviteModel) InviteActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((InviteModel) InviteActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((InviteModel) InviteActivity.this.viewModel).emailColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isEmail(value)) {
                    ((InviteModel) InviteActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((InviteModel) InviteActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((InviteModel) InviteActivity.this.viewModel).emailColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((InviteModel) InviteActivity.this.viewModel).emailErrText.setValue("Please enter a valid email address.");
                    ((InviteModel) InviteActivity.this.viewModel).emailErrVisible.setValue(true);
                    ((InviteModel) InviteActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((InviteModel) InviteActivity.this.viewModel).emailColor.setValue(Integer.valueOf(InviteActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((InviteModel) this.viewModel).onBack.execute();
    }
}
