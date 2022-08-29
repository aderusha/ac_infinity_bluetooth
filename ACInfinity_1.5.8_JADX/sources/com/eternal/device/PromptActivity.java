package com.eternal.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityPromptBinding;
import com.eternal.device.model.PromptModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.res.C2663R;

public class PromptActivity extends BaseActivity<ActivityPromptBinding, PromptModel> {
    public static final String SHOW_DIALOG = "show dialog";

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_prompt;
    }

    public int initVariableId() {
        return C1909BR.promptModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((PromptModel) this.viewModel).init();
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", PromptModel.class, new BindingConsumer<PromptModel>() {
            public void call(PromptModel promptModel) {
                View inflate = LayoutInflater.from(PromptActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(PromptActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                build.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((PromptModel) PromptActivity.this.viewModel).pushToMain();
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
    }

    public void onBackPressed() {
        ((PromptModel) this.viewModel).onBack.execute();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        super.onDestroy();
    }
}
