package com.eternal.about;

import android.os.Bundle;
import com.eternal.about.databinding.ActivityAboutBinding;
import com.eternal.base.BaseActivity;
import com.eternal.framework.utils.AppUtils;

public class AboutActivity extends BaseActivity<ActivityAboutBinding, AboutModel> {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((ActivityAboutBinding) this.binding).tvVersion.setText("Version " + AppUtils.getAppVersionName() + " | Build " + AppUtils.getAppVersionCode());
    }

    public int initContentView(Bundle bundle) {
        return C0969R.layout.activity_about;
    }

    public int initVariableId() {
        return C0968BR.model;
    }

    public void onBackPressed() {
        ((AboutModel) this.viewModel).onBack.execute();
    }
}
