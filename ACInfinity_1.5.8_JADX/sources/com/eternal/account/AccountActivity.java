package com.eternal.account;

import android.os.Bundle;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.account.databinding.ActivityAccountBinding;
import com.eternal.account.model.AccountModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.bus.Messenger;

public class AccountActivity extends BaseActivity<ActivityAccountBinding, AccountModel> {
    public static final String SHOW_DELETE_ACCOUNT_DIALOG = "show delete account";
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    boolean showShareDot;

    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_account;
    }

    public int initVariableId() {
        return C0977BR.accountModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AccountModel) this.viewModel).init(this.showShareDot);
        initView();
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) SHOW_DELETE_ACCOUNT_DIALOG, (BindingAction) new BindingAction() {
            public void call() {
                AccountActivity accountActivity = AccountActivity.this;
                MaterialDialog unused = accountActivity.dialog = TipDialog.showTipDialog(accountActivity, accountActivity.getString(C0997R.string.tip_delete_account), AccountActivity.this.getString(C0997R.string.tip_delete_account_content), AccountActivity.this.getResources().getString(C0997R.string.tip_cancel_lowercase), AccountActivity.this.getResources().getString(C0997R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((AccountModel) AccountActivity.this.viewModel).deleteAccount();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        ((AccountModel) this.viewModel).onBack.execute();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        Messenger.getDefault().unregister(this);
        super.onDestroy();
    }
}
