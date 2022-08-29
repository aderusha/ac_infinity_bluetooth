package com.eternal.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.BaseRxActivity;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.component.IActive;
import com.eternal.widget.loadingdialog.DialogDismissListener;
import com.eternal.widget.loadingdialog.LoadingDialog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import p014io.reactivex.disposables.Disposable;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseRxActivity implements IActive {
    /* access modifiers changed from: protected */
    public V binding;
    private DialogDismissListener listener;
    private LoadingDialog loadingDialog;
    /* access modifiers changed from: protected */
    public VM viewModel;

    public void dialogDisposable(Disposable disposable) {
    }

    public abstract int initContentView(Bundle bundle);

    public abstract int initVariableId();

    public VM initViewModel() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initViewDataBinding(bundle);
        this.viewModel.registerRxBus();
        ARouter.getInstance().inject(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        dismissDialog();
        super.onDestroy();
        Messenger.getDefault().unregister(this.viewModel);
        VM vm = this.viewModel;
        if (vm != null) {
            vm.unregisterRxBus();
        }
        V v = this.binding;
        if (v != null) {
            v.unbind();
        }
    }

    private void initViewDataBinding(Bundle bundle) {
        Class cls;
        this.binding = DataBindingUtil.setContentView(this, initContentView(bundle));
        int initVariableId = initVariableId();
        VM initViewModel = initViewModel();
        this.viewModel = initViewModel;
        if (initViewModel == null) {
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
            } else {
                cls = BaseViewModel.class;
            }
            this.viewModel = (BaseViewModel) ViewModelProviders.m29of((FragmentActivity) this).get(cls);
        }
        this.binding.setVariable(initVariableId, this.viewModel);
        this.binding.setLifecycleOwner(this);
        getLifecycle().addObserver(this.viewModel);
        this.viewModel.injectLifecycleProvider(this);
        this.viewModel.initActive(this);
    }

    public Intent createIntent(Class<?> cls) {
        return new Intent(this, cls);
    }

    public void finishAnimate(int i, int i2) {
        finish();
        setTransition(i, i2);
    }

    public void setTransition(int i, int i2) {
        overridePendingTransition(i, i2);
    }

    public void showFailDialog(String str) {
        dismissDialog();
        LoadingDialog closeSuccessAnim = new LoadingDialog(this).setLoadingText(str).setLoadStyle(1).setFailedText(str).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
        this.loadingDialog.loadFailed();
    }

    public void showSuccessDialog(String str) {
        dismissDialog();
        LoadingDialog closeSuccessAnim = new LoadingDialog(this).setLoadingText(str).setLoadStyle(1).setSuccessText(str).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
        this.loadingDialog.loadSuccess();
    }

    public void showDialog(String str, Disposable disposable) {
        this.listener = new DialogDismissListener(disposable);
        LoadingDialog closeSuccessAnim = new LoadingDialog(this).setLoadingText(str).setLoadStyle(1).setDimissListener(this.listener).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
    }

    public void dismissDialog() {
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 != null) {
            loadingDialog2.close();
        }
    }
}
