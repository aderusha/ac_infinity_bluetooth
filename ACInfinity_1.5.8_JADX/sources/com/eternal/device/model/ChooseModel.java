package com.eternal.device.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.TipDialog;
import com.eternal.base.UserCache;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.PromptActivity;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.res.C2663R;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class ChooseModel extends BaseViewModel {
    private Disposable loginDis;
    public BindingCommand<Void> on67Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 1, (byte) 0);
        }
    });
    public BindingCommand<Void> on69Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 7, (byte) 0);
        }
    });
    public BindingCommand<Void> on69WiFiClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (TextUtils.isEmpty(UserCache.getInstance().getToken())) {
                ChooseModel.this.pushToLoginPromptPage();
                ChooseModel.this.registerEventBus();
                return;
            }
            ChooseModel.this.pushToGuidePage((byte) 8, (byte) 0, (byte) 1);
        }
    });
    public BindingCommand<Void> on70Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 11, (byte) 0, (byte) 1);
        }
    });
    public BindingCommand<Void> on75IndependentPortClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 12, (byte) 1);
        }
    });
    public BindingCommand<Void> on76Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 2, (byte) 0);
        }
    });
    public BindingCommand<Void> on76IndependentPortClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 9, (byte) 1);
        }
    });
    public BindingCommand<Void> onAirtapClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 6, (byte) 0);
        }
    });
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCA1Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 15, (byte) 0);
        }
    });
    public BindingCommand<Void> onCA2Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 14, (byte) 0);
        }
    });
    public BindingCommand<Void> onCB1Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 5, (byte) 0);
        }
    });
    public BindingCommand<Void> onCB2Click = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToGuidePage((byte) 4, (byte) 0);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Activity currentActivity = AppManager.getAppManager().currentActivity();
            TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), currentActivity.getString(C2663R.string.tip_exit_add_device), currentActivity.getString(C2663R.string.tip_exit_add_device_desc), currentActivity.getString(C2663R.string.tip_do_not_exit), currentActivity.getString(C2663R.string.tip_exit), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    ChooseModel.this.pushToMain();
                }
            });
        }
    });
    public BindingCommand<Void> onControllersClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToSecondChoosePage((byte) 0);
        }
    });
    public BindingCommand<Void> onHAVCClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToSecondChoosePage((byte) 3);
        }
    });
    public BindingCommand<Void> onHygrometersClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToSecondChoosePage((byte) 2);
        }
    });
    public BindingCommand<Void> onOutletsClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChooseModel.this.pushToSecondChoosePage((byte) 1);
        }
    });
    private byte pageType;

    public ChooseModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void pushToSecondChoosePage(byte b) {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_CHOOSE).withByte("page type", (byte) 1).withByte(ChooseActivity.DEVICE_SERIES, b).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void registerEventBus() {
        Disposable disposable = this.loginDis;
        if (disposable == null || disposable.isDisposed()) {
            this.loginDis = RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
                public void accept(ActivityEvent activityEvent) {
                    if (activityEvent.what == 20) {
                        AppManager.getAppManager().finishActivity((Class<?>) PromptActivity.class);
                        ChooseModel.this.pushToGuidePage((byte) 8, (byte) 0, (byte) 1);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void pushToGuidePage(byte b, byte b2, byte b3) {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_GUIDE).withByte(ActivityEvent.DEVICE_TYPE, b).withByte(ActivityEvent.DEVICE_MODEL, b2).withByte("page type", b3).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: private */
    public void pushToGuidePage(byte b, byte b2) {
        pushToGuidePage(b, b2, (byte) 0);
    }

    /* access modifiers changed from: private */
    public void pushToLoginPromptPage() {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_PROMPT).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
            }
        });
    }

    public void init(byte b) {
        this.pageType = b;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Disposable disposable = this.loginDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.loginDis.dispose();
        }
    }
}
