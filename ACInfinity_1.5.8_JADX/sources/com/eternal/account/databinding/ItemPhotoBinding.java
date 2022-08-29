package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.PhotoItemModel;

public abstract class ItemPhotoBinding extends ViewDataBinding {
    @Bindable
    protected PhotoItemModel mItemPhoto;

    public abstract void setItemPhoto(PhotoItemModel photoItemModel);

    protected ItemPhotoBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public PhotoItemModel getItemPhoto() {
        return this.mItemPhoto;
    }

    public static ItemPhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemPhotoBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_photo, viewGroup, z, obj);
    }

    public static ItemPhotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPhotoBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemPhotoBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_photo, (ViewGroup) null, false, obj);
    }

    public static ItemPhotoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPhotoBinding bind(View view, Object obj) {
        return (ItemPhotoBinding) bind(obj, view, C0997R.layout.item_photo);
    }
}
