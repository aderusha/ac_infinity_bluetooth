package com.eternal.device;

import android.content.Intent;
import android.os.Bundle;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityChooseBinding;
import com.eternal.device.model.ChooseModel;
import com.eternal.framework.bus.Messenger;

public class ChooseActivity extends BaseActivity<ActivityChooseBinding, ChooseModel> {
    public static final String DEVICE_SERIES = "device series";
    public static final String PAGE_TYPE = "page type";
    public static final byte SMART_CONTROLLER = 0;
    public static final byte SMART_HAVC = 3;
    public static final byte SMART_OUTLETS = 1;
    public static final byte SMART_THERMO_HYGROMETERS = 2;

    private void initMessage() {
    }

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_choose;
    }

    public int initVariableId() {
        return C1909BR.chooseModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        byte byteExtra = intent.getByteExtra("page type", (byte) 0);
        byte byteExtra2 = intent.getByteExtra(DEVICE_SERIES, (byte) 0);
        ((ChooseModel) this.viewModel).init(byteExtra);
        initView(byteExtra, byteExtra2);
        initMessage();
    }

    private void initView(byte b, byte b2) {
        if (b == 0) {
            ((ActivityChooseBinding) this.binding).llLayoutChoose1.setVisibility(0);
        } else if (b2 == 0) {
            ((ActivityChooseBinding) this.binding).ll67.setVisibility(0);
            ((ActivityChooseBinding) this.binding).ll69.setVisibility(0);
            ((ActivityChooseBinding) this.binding).ll69Wifi.setVisibility(0);
            ((ActivityChooseBinding) this.binding).ll70.setVisibility(0);
        } else if (b2 == 1) {
            ((ActivityChooseBinding) this.binding).ll76.setVisibility(0);
        } else if (b2 == 2) {
            ((ActivityChooseBinding) this.binding).llCa1.setVisibility(0);
            ((ActivityChooseBinding) this.binding).llCa2.setVisibility(0);
            ((ActivityChooseBinding) this.binding).llCb1.setVisibility(0);
            ((ActivityChooseBinding) this.binding).llCb2.setVisibility(0);
        } else if (b2 == 3) {
            ((ActivityChooseBinding) this.binding).llAirtap.setVisibility(0);
        }
    }

    public void onBackPressed() {
        ((ChooseModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMassage();
        super.onDestroy();
    }
}
