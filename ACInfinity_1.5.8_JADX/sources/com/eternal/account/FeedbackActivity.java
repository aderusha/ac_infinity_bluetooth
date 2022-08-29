package com.eternal.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.eternal.account.databinding.ActivityFeedbackBinding;
import com.eternal.account.model.FeedbackModel;
import com.eternal.account.model.PhotoItemModel;
import com.eternal.account.photoPicker.MyGlideEngine;
import com.eternal.base.BaseActivity;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.utils.Utils;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

public class FeedbackActivity extends BaseActivity<ActivityFeedbackBinding, FeedbackModel> {
    final int REQUEST_CODE_CHOOSE = 1001;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityFeedbackBinding) FeedbackActivity.this.binding).ibSend.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityFeedbackBinding) FeedbackActivity.this.binding).ibSend.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((FeedbackModel) this.viewModel).init();
        initView();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1) {
            for (String photoItemModel : Matisse.obtainPathResult(intent)) {
                ((FeedbackModel) this.viewModel).items.add(new PhotoItemModel(photoItemModel));
            }
        }
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_feedback;
    }

    public int initVariableId() {
        return C0977BR.feedbackModel;
    }

    private void initView() {
        RxView.clicks(((ActivityFeedbackBinding) this.binding).rlAttach).compose(new RxPermissions((FragmentActivity) this).ensure("android.permission.WRITE_EXTERNAL_STORAGE")).compose(bindToLifecycle()).subscribe(new FeedbackActivity$$ExternalSyntheticLambda0(this));
        new SoftKeyBroadManager(((ActivityFeedbackBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
    }

    public /* synthetic */ void lambda$initView$0$FeedbackActivity(Boolean bool) throws Exception {
        if (!bool.booleanValue()) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C0997R.string.permission_sdcard_denied));
        } else if (9 - ((FeedbackModel) this.viewModel).items.size() <= 0) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C0997R.string.tip_over_count_photos));
        } else {
            Matisse.from((Activity) this).choose(MimeType.ofAll(), false).countable(true).maxSelectable(9 - ((FeedbackModel) this.viewModel).items.size()).gridExpectedSize(getResources().getDimensionPixelSize(C0997R.dimen.dp_120)).restrictOrientation(1).thumbnailScale(0.85f).imageEngine(new MyGlideEngine()).theme(C0997R.style.matisse).autoHideToolbarOnSingleTap(true).forResult(1001);
        }
    }

    public void onBackPressed() {
        ((FeedbackModel) this.viewModel).onBack.execute();
    }
}
