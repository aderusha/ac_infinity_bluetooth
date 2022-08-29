package com.eternal.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.device.databinding.ActivityWifiBinding;
import com.eternal.device.model.WiFiModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.res.C2663R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WiFiActivity extends BaseActivity<ActivityWifiBinding, WiFiModel> {
    public static final String DISMISS_SHEET_DIALOG = "dismiss sheet dialog";
    public static final String SHOW_DIALOG = "show dialog";
    public static final String SHOW_SHEET_DIALOG = "show sheet dialog";
    BottomSheetDialog sheetDialog;
    String socketIp;
    int socketPort;

    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_wifi;
    }

    public int initVariableId() {
        return C1909BR.wifiModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((WiFiModel) this.viewModel).init(this.socketIp, this.socketPort);
        initView();
        initMessage();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((WiFiModel) this.viewModel).updateStateDelay();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", WiFiModel.class, new BindingConsumer<WiFiModel>() {
            public void call(WiFiModel wiFiModel) {
                View inflate = LayoutInflater.from(WiFiActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(WiFiActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                build.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((WiFiModel) WiFiActivity.this.viewModel).pushToMain();
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show sheet dialog", WiFiModel.class, new BindingConsumer<WiFiModel>() {
            public void call(WiFiModel wiFiModel) {
                WiFiActivity.this.showBottomSheet();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "dismiss sheet dialog", WiFiModel.class, new BindingConsumer<WiFiModel>() {
            public void call(WiFiModel wiFiModel) {
                WiFiActivity.this.dismissBottomSheet();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) ActivityEvent.BACK_WIFI_PAGE, Boolean.class, new BindingConsumer<Boolean>() {
            public void call(Boolean bool) {
                ((WiFiModel) WiFiActivity.this.viewModel).setBackThere(bool.booleanValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public void showBottomSheet() {
        ViewParent parent = ((ActivityWifiBinding) this.binding).llSheetDialog.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(((ActivityWifiBinding) this.binding).llSheetDialog);
        }
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, C2663R.style.dialog);
        this.sheetDialog = bottomSheetDialog;
        bottomSheetDialog.setContentView((View) ((ActivityWifiBinding) this.binding).llSheetDialog);
        this.sheetDialog.setCancelable(true);
        ((ActivityWifiBinding) this.binding).llSheetDialog.post(new Runnable() {
            public void run() {
                BottomSheetBehavior from = BottomSheetBehavior.from((View) ((ActivityWifiBinding) WiFiActivity.this.binding).llSheetDialog.getParent());
                from.setState(3);
                from.setHideable(false);
            }
        });
        this.sheetDialog.show();
    }

    /* access modifiers changed from: private */
    public void dismissBottomSheet() {
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
    }

    public void onBackPressed() {
        ((WiFiModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMassage();
        ((WiFiModel) this.viewModel).destroy();
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
        super.onDestroy();
    }
}
