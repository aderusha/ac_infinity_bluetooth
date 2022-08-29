package com.eternal.start;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.router.RouterActivityPath;

public class StartActivity extends AppCompatActivity {
    private static final int DURATION = 4000;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
            return;
        }
        setContentView(C2760R.layout.activity_start);
        start();
    }

    private void start() {
        if (Build.VERSION.SDK_INT >= 31) {
            ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).navigation((Context) this, (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                    StartActivity.this.finish();
                }
            });
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById(C2760R.C2763id.iv_logo), "alpha", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(4000);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).navigation((Context) StartActivity.this, (NavigationCallback) new NavCallback() {
                    public void onArrival(Postcard postcard) {
                        StartActivity.this.finish();
                    }
                });
            }
        });
        ofFloat.start();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(findViewById(C2760R.C2763id.tv_copyright), "alpha", new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(4000);
        ofFloat2.start();
    }
}
