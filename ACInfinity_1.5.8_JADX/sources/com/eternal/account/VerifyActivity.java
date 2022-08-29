package com.eternal.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.eternal.account.databinding.ActivityVerifyBinding;
import com.eternal.account.model.VerifyModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.SoftKeyBroadManager;

public class VerifyActivity extends BaseActivity<ActivityVerifyBinding, VerifyModel> {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        byte byteExtra = intent.getByteExtra(ActivityEvent.FROM_PAGE, (byte) 30);
        ((VerifyModel) this.viewModel).init(intent.getStringExtra("email"), intent.getStringExtra(ActivityEvent.PASSWORD), byteExtra);
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_verify;
    }

    public int initVariableId() {
        return C0977BR.verifyModel;
    }

    private void initView() {
        ((ActivityVerifyBinding) this.binding).pvCode.requestFocus();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SoftKeyBroadManager.openKeyboard(((ActivityVerifyBinding) VerifyActivity.this.binding).pvCode);
            }
        }, 600);
    }

    public void onBackPressed() {
        ((VerifyModel) this.viewModel).onBack.execute();
    }
}
