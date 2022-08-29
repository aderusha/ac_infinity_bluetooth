package com.eternal.framework.binding.viewadapter.viewgroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public final class ViewAdapter {
    public static void addViews(ViewGroup viewGroup, ItemBinding itemBinding, ObservableList<IBindingItemViewModel> observableList) {
        if (observableList != null && !observableList.isEmpty()) {
            viewGroup.removeAllViews();
            for (IBindingItemViewModel iBindingItemViewModel : observableList) {
                ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), itemBinding.layoutRes(), viewGroup, true);
                inflate.setVariable(itemBinding.variableId(), iBindingItemViewModel);
                iBindingItemViewModel.injecDataBinding(inflate);
            }
        }
    }
}
