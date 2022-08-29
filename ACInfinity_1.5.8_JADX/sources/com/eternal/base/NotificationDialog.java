package com.eternal.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.framework.component.BaseRxActivity;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.framework.utils.ScreenUtils;
import com.eternal.framework.utils.Utils;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Completable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.schedulers.Schedulers;

public class NotificationDialog {
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    /* access modifiers changed from: private */
    public Disposable disposable;
    private int duration = 500;
    private int height = ConvertUtils.dp2px(95.0f);
    private TextView info;
    /* access modifiers changed from: private */
    public Intent intent;
    private View root;
    private int sense;
    private int widthDiver = ConvertUtils.dp2px(20.0f);

    public NotificationDialog(Context context) {
        initView(context);
        MaterialDialog build = new MaterialDialog.Builder(context).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).autoDismiss(false).canceledOnTouchOutside(false).customView(this.root, false).build();
        this.dialog = build;
        Window window = build.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ScreenUtils.getAppScreenWidth(context) - this.widthDiver;
        attributes.height = -2;
        attributes.dimAmount = 0.0f;
        attributes.flags = 8;
        window.setAttributes(attributes);
        window.setGravity(48);
        ViewGroup.LayoutParams layoutParams = this.root.getLayoutParams();
        layoutParams.height = -2;
        this.root.setLayoutParams(layoutParams);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C1323R.layout.pop_notification, (ViewGroup) null);
        this.root = inflate;
        this.info = (TextView) inflate.findViewById(C1323R.C1326id.notify_info);
        this.root.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NotificationDialog.this.intent != null) {
                    NotificationDialog.this.disposable.dispose();
                    Utils.getContext().startActivity(NotificationDialog.this.intent);
                }
                NotificationDialog.this.dismiss();
            }
        });
    }

    public NotificationDialog setDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.dialog.setOnDismissListener(onDismissListener);
        return this;
    }

    public NotificationDialog setSense(int i) {
        this.sense = i;
        return this;
    }

    public NotificationDialog setIntent(Intent intent2) {
        this.intent = intent2;
        return this;
    }

    public boolean isShowing() {
        return this.dialog.isShowing();
    }

    public NotificationDialog setMessage(String str) {
        this.info.setText(str);
        return this;
    }

    public void show(String str) {
        this.info.setText(str);
        this.dialog.show();
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-this.height), 0.0f);
        translateAnimation.setDuration((long) this.duration);
        this.root.startAnimation(translateAnimation);
        this.disposable = Completable.timer((long) this.sense, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m983io()).subscribe((Action) new Action() {
            public void run() {
                NotificationDialog.this.dismiss();
            }
        });
    }

    public void show() {
        if (this.root.getContext() instanceof BaseRxActivity) {
            ((BaseRxActivity) this.root.getContext()).showNotifyDialog(this.dialog);
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-this.height), 0.0f);
        translateAnimation.setDuration((long) this.duration);
        this.root.startAnimation(translateAnimation);
        this.disposable = Completable.timer((long) this.sense, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m983io()).subscribe((Action) new Action() {
            public void run() {
                NotificationDialog.this.dismiss();
            }
        });
    }

    public void dismiss() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-this.height));
        translateAnimation.setDuration((long) this.duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                NotificationDialog.this.dialog.dismiss();
            }
        });
        this.root.startAnimation(translateAnimation);
    }
}
