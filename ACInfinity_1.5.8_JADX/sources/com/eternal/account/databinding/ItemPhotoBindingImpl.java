package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0977BR;
import com.eternal.account.model.PhotoItemModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.image.ViewAdapter;

public class ItemPhotoBindingImpl extends ItemPhotoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView2;

    public ItemPhotoBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemPhotoBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ImageView imageView = objArr[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = objArr[2];
        this.mboundView2 = imageView2;
        imageView2.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (C0977BR.itemPhoto != i) {
            return false;
        }
        setItemPhoto((PhotoItemModel) obj);
        return true;
    }

    public void setItemPhoto(PhotoItemModel photoItemModel) {
        this.mItemPhoto = photoItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C0977BR.itemPhoto);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeItemPhotoThumbUrl((ObservableField) obj, i2);
    }

    private boolean onChangeItemPhotoThumbUrl(ObservableField<String> observableField, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        BindingCommand<Void> bindingCommand;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PhotoItemModel photoItemModel = this.mItemPhoto;
        String str = null;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (i != 0) {
            bindingCommand = ((j & 6) == 0 || photoItemModel == null) ? null : photoItemModel.onDelete;
            ObservableField<String> observableField = photoItemModel != null ? photoItemModel.thumbUrl : null;
            updateRegistration(0, (Observable) observableField);
            if (observableField != null) {
                str = observableField.get();
            }
        } else {
            bindingCommand = null;
        }
        if (i != 0) {
            ViewAdapter.loadThumbnail(this.mboundView1, str, 0, 0.0f);
        }
        if ((j & 6) != 0) {
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(this.mboundView2, bindingCommand, false);
        }
    }
}
