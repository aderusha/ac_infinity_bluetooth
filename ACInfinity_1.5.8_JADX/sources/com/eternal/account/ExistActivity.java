package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivityCreateBinding;
import com.eternal.account.model.ExistModel;
import com.eternal.base.BaseActivity;

public class ExistActivity extends BaseActivity<ActivityCreateBinding, ExistModel> {
    private void initView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((ExistModel) this.viewModel).init();
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_exist;
    }

    public int initVariableId() {
        return C0977BR.existModel;
    }

    public void onBackPressed() {
        ((ExistModel) this.viewModel).onBack.execute();
    }
}
