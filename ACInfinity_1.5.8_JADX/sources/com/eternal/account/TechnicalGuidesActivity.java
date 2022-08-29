package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivityTechnicalGuidesBinding;
import com.eternal.account.model.TechnicalGuidesModel;
import com.eternal.base.BaseActivity;

public class TechnicalGuidesActivity extends BaseActivity<ActivityTechnicalGuidesBinding, TechnicalGuidesModel> {
    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_technical_guides;
    }

    public int initVariableId() {
        return C0977BR.technicalGuides;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((TechnicalGuidesModel) this.viewModel).init();
        initView();
    }

    public void onBackPressed() {
        ((TechnicalGuidesModel) this.viewModel).onBack.execute();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
