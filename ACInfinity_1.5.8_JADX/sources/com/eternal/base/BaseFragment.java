package com.eternal.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.BaseRxFragment;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.component.IActive;
import com.eternal.widget.loadingdialog.DialogDismissListener;
import com.eternal.widget.loadingdialog.LoadingDialog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import p014io.reactivex.disposables.Disposable;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseRxFragment implements IActive {
    /* access modifiers changed from: protected */
    public V binding;
    private DialogDismissListener listener;
    private LoadingDialog loadingDialog;
    /* access modifiers changed from: protected */
    public VM viewModel;

    public abstract int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public abstract int initVariableId();

    public VM initViewModel() {
        return null;
    }

    public void showFailDialog(String str) {
        dismissDialog();
        LoadingDialog closeSuccessAnim = new LoadingDialog(getActivity()).setLoadingText(str).setLoadStyle(1).setFailedText(str).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
        this.loadingDialog.loadFailed();
    }

    public void showSuccessDialog(String str) {
        dismissDialog();
        LoadingDialog closeSuccessAnim = new LoadingDialog(getActivity()).setLoadingText(str).setLoadStyle(1).setSuccessText(str).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
        this.loadingDialog.loadSuccess();
    }

    public void showDialog(String str, Disposable disposable) {
        this.listener = new DialogDismissListener(disposable);
        LoadingDialog closeSuccessAnim = new LoadingDialog(getActivity()).setLoadingText(str).setLoadStyle(1).setDimissListener(this.listener).closeFailedAnim().closeSuccessAnim();
        this.loadingDialog = closeSuccessAnim;
        closeSuccessAnim.show();
    }

    public void dismissDialog() {
        if (this.loadingDialog != null && isAdded()) {
            this.loadingDialog.close();
        }
    }

    public void dialogDisposable(Disposable disposable) {
        DialogDismissListener dialogDismissListener = this.listener;
        if (dialogDismissListener != null) {
            dialogDismissListener.setDisposable(disposable);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        V inflate = DataBindingUtil.inflate(layoutInflater, initContentView(layoutInflater, viewGroup, bundle), viewGroup, false);
        this.binding = inflate;
        return inflate.getRoot();
    }

    public void onDestroyView() {
        dismissDialog();
        super.onDestroyView();
        Messenger.getDefault().unregister(this.viewModel);
        VM vm = this.viewModel;
        if (vm != null) {
            vm.unregisterRxBus();
            getLifecycle().removeObserver(this.viewModel);
        }
        V v = this.binding;
        if (v != null) {
            v.unbind();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initViewDataBinding();
        this.viewModel.registerRxBus();
        ARouter.getInstance().inject(this);
    }

    private void initViewDataBinding() {
        Class cls;
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
            this.viewModel = (BaseViewModel) ViewModelProviders.m27of((Fragment) this).get(cls);
        }
        this.binding.setVariable(initVariableId, this.viewModel);
        this.binding.setLifecycleOwner(this);
        getLifecycle().addObserver(this.viewModel);
        this.viewModel.injectLifecycleProvider(this);
        this.viewModel.initActive(this);
    }

    public Intent createIntent(Class<?> cls) {
        return new Intent(getContext(), cls);
    }

    public void finish() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public void finishAnimate(int i, int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(i, i2);
        }
    }

    public void setTransition(int i, int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public void onBackPressed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }
}
