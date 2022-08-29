package com.eternal.device;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityGuideBinding;
import com.eternal.device.model.GuideModel;
import com.eternal.framework.bus.Messenger;

public class GuideActivity extends BaseActivity<ActivityGuideBinding, GuideModel> {
    byte deviceModel;
    byte deviceType;
    byte pageType;
    String socketIp;
    int socketPort;

    private void initMessage() {
    }

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_guide;
    }

    public int initVariableId() {
        return C1909BR.guideModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((GuideModel) this.viewModel).init(this.pageType, this.deviceType, this.socketIp, this.socketPort);
        initView(this.pageType, this.deviceType, this.deviceModel);
        initMessage();
    }

    private void initView(byte b, byte b2, byte b3) {
        if (b == 0) {
            if (b2 == 1) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_67);
            } else if (b2 == 2) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(b3 == 0 ? C1922R.mipmap.guide_76 : C1922R.mipmap.guide_75);
                ((ActivityGuideBinding) this.binding).ivTip.setScaleType(ImageView.ScaleType.FIT_START);
            } else if (b2 == 4) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_cb1);
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_130), 0);
                ((ActivityGuideBinding) this.binding).tvContent.setText("");
            } else if (b2 == 5) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_cb2);
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_130), 0);
                ((ActivityGuideBinding) this.binding).tvContent.setText("");
            } else if (b2 == 6) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_airtap);
            } else if (b2 == 9) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_controller_independent_port);
                ((ActivityGuideBinding) this.binding).ivTip.setScaleType(ImageView.ScaleType.FIT_START);
            } else if (b2 == 12) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_75_independ);
                ((ActivityGuideBinding) this.binding).ivTip.setScaleType(ImageView.ScaleType.FIT_START);
            } else if (b2 == 14) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_ca1);
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_130), 0);
                ((ActivityGuideBinding) this.binding).tvContent.setText("");
            } else if (b2 != 15) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_69);
                ((ActivityGuideBinding) this.binding).ivTip.setScaleType(ImageView.ScaleType.FIT_START);
                setImageMargin(0, getResources().getDimensionPixelSize(C1922R.dimen.dp_16));
            } else {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_ca2);
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_130), 0);
                ((ActivityGuideBinding) this.binding).tvContent.setText("");
            }
            ((ActivityGuideBinding) this.binding).tvTitle.setText("START PAIRING");
            if (b2 == 14 || b2 == 4 || b2 == 15 || b2 == 5) {
                ((ActivityGuideBinding) this.binding).tvContent.setText("Your hygrometer and mobile device \nmust be within 5 feet of distance.");
            } else {
                ((ActivityGuideBinding) this.binding).tvContent.setText("Your controller and mobile device \nmust be within 5 feet of distance.");
            }
            ((ActivityGuideBinding) this.binding).tvContent.setGravity(1);
        } else if (b == 1) {
            if (b2 == 7) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_69_2);
                ((ActivityGuideBinding) this.binding).tvTitle.setText("ACTIVATE BLUETOOTH");
                ((ActivityGuideBinding) this.binding).tvContent.setText("Hold the Port button until the Bluetooth icon \non the display starts flashing.");
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_82), 0);
            } else if (b2 == 8) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_70_2);
                ((ActivityGuideBinding) this.binding).tvTitle.setText("ACTIVATE Wi-Fi");
                ((ActivityGuideBinding) this.binding).tvContent.setText("Hold the Port button for 5 seconds, \nthen wait for the Wi-Fi icon to start flashing. ");
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_36), 0);
            } else if (b2 == 11) {
                ((ActivityGuideBinding) this.binding).ivTip.setImageResource(C1922R.mipmap.guide_g);
                ((ActivityGuideBinding) this.binding).tvTitle.setText("ACTIVATE BROADCASTING");
                ((ActivityGuideBinding) this.binding).tvContent.setText("Hold the Port button for 5 seconds, then wait for \nthe Bluetooth icon to start flashing.");
                setImageMargin(getResources().getDimensionPixelSize(C1922R.dimen.dp_36), 0);
            }
            ((ActivityGuideBinding) this.binding).tvContent.setGravity(17);
        }
    }

    private void setImageMargin(int i, int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((ActivityGuideBinding) this.binding).ivTip.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.leftMargin = i2;
        ((ActivityGuideBinding) this.binding).ivTip.setLayoutParams(layoutParams);
    }

    public void onBackPressed() {
        ((GuideModel) this.viewModel).onBack.execute();
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
