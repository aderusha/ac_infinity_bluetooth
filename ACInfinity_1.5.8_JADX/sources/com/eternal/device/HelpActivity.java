package com.eternal.device;

import android.os.Bundle;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityHelpBinding;
import com.eternal.device.model.HelpModel;

public class HelpActivity extends BaseActivity<ActivityHelpBinding, HelpModel> {
    public static final String PAGE_TYPE = "page type";

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_help;
    }

    public int initVariableId() {
        return C1909BR.helpModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getByteExtra("page type", (byte) 0) == 1) {
            ((ActivityHelpBinding) this.binding).tvTitle1.setText("SELECT 2.4GHZ NETWORK");
            ((ActivityHelpBinding) this.binding).tvContent1.setText("Ensure you are not connected to a 5GHz network, which may have “5GHz” in its Wi-Fi network name.");
            ((ActivityHelpBinding) this.binding).tvTitle2.setText("WPA/WPA2 NOT SUPPORTED");
            ((ActivityHelpBinding) this.binding).tvContent2.setText("Move your mobile device closer to your controller and try again.");
            ((ActivityHelpBinding) this.binding).tvTitle3.setText("Wi-Fi BRIDGE AND EXTENDER");
            ((ActivityHelpBinding) this.binding).tvContent3.setText("Disconnect from your Wi-Fi bridge or extender; it may be creating unstable Wi-Fi signals. Connect to your primary router.");
            ((ActivityHelpBinding) this.binding).tvTitle4.setText("CORRECT WI-FI PASSWORD");
            ((ActivityHelpBinding) this.binding).tvContent4.setText("Make sure your Wi-Fi password is correct.");
            ((ActivityHelpBinding) this.binding).tvTitle4.setVisibility(0);
            ((ActivityHelpBinding) this.binding).tvContent4.setVisibility(0);
            ((ActivityHelpBinding) this.binding).tvNumber4.setVisibility(0);
        }
    }

    public void onBackPressed() {
        ((HelpModel) this.viewModel).onBack.execute();
    }
}
