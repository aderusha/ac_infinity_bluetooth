package com.eternal.device;

import android.os.Bundle;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityLocationPermissionBinding;
import com.eternal.device.model.LocationPermissionModel;

public class LocationPermissionActivity extends BaseActivity<ActivityLocationPermissionBinding, LocationPermissionModel> {
    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_location_permission;
    }

    public int initVariableId() {
        return C1909BR.permissionModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onBackPressed() {
        ((LocationPermissionModel) this.viewModel).onBack.execute();
    }
}
