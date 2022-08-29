package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivitySupportBinding;
import com.eternal.account.model.SupportModel;
import com.eternal.base.BaseActivity;

public class SupportActivity extends BaseActivity<ActivitySupportBinding, SupportModel> {
    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_support;
    }

    public int initVariableId() {
        return C0977BR.supportModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((SupportModel) this.viewModel).init();
        initView();
    }

    public void onBackPressed() {
        ((SupportModel) this.viewModel).onBack.execute();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
