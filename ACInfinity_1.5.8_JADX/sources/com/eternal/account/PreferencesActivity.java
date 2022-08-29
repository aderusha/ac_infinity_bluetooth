package com.eternal.account;

import android.os.Bundle;
import com.eternal.account.databinding.ActivityCreateBinding;
import com.eternal.account.model.PreferencesModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ActivityEvent;

public class PreferencesActivity extends BaseActivity<ActivityCreateBinding, PreferencesModel> {
    private void initView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((PreferencesModel) this.viewModel).init(getIntent().getBooleanExtra(ActivityEvent.IS_PRIVACY, false));
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_preferences;
    }

    public int initVariableId() {
        return C0977BR.preferenceModel;
    }

    public void onBackPressed() {
        ((PreferencesModel) this.viewModel).onBack.execute();
    }
}
