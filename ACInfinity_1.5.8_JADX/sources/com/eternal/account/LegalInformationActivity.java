package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivityLegalInformationBinding;
import com.eternal.account.model.LegalInformationModel;
import com.eternal.base.BaseActivity;

public class LegalInformationActivity extends BaseActivity<ActivityLegalInformationBinding, LegalInformationModel> {
    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_legal_information;
    }

    public int initVariableId() {
        return C0977BR.legalModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LegalInformationModel) this.viewModel).init();
        initView();
    }

    public void onBackPressed() {
        ((LegalInformationModel) this.viewModel).onBack.execute();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
