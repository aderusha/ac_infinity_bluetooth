package com.eternal.account.databinding;

import android.graphics.drawable.Drawable;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.PreferencesModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityPreferencesBindingImpl extends ActivityPreferencesBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_analytics, 7);
        sparseIntArray.put(C0997R.C1000id.tv_reports, 8);
        sparseIntArray.put(C0997R.C1000id.tv_subscription, 9);
    }

    public ActivityPreferencesBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityPreferencesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[6], objArr[0], objArr[2], objArr[3], objArr[4], objArr[1], objArr[7], objArr[8], objArr[9]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        TextView textView = objArr[5];
        this.mboundView5 = textView;
        textView.setTag((Object) null);
        this.root.setTag((Object) null);
        this.swAnalytics.setTag((Object) null);
        this.swReports.setTag((Object) null);
        this.swSubscription.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (C0977BR.preferenceModel != i) {
            return false;
        }
        setPreferenceModel((PreferencesModel) obj);
        return true;
    }

    public void setPreferenceModel(PreferencesModel preferencesModel) {
        this.mPreferenceModel = preferencesModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(C0977BR.preferenceModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangePreferenceModelAnonymousAnalytics((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangePreferenceModelBugReports((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangePreferenceModelEmailSubscription((MutableLiveData) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangePreferenceModelIsPrivacy((MutableLiveData) obj, i2);
    }

    private boolean onChangePreferenceModelAnonymousAnalytics(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePreferenceModelBugReports(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangePreferenceModelEmailSubscription(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangePreferenceModelIsPrivacy(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        BindingCommand<Boolean> bindingCommand;
        BindingCommand<Boolean> bindingCommand2;
        boolean z;
        boolean z2;
        Drawable drawable;
        BindingCommand<Boolean> bindingCommand3;
        BindingCommand<Void> bindingCommand4;
        boolean z3;
        BindingCommand<Void> bindingCommand5;
        String str;
        int i;
        BindingCommand<Void> bindingCommand6;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PreferencesModel preferencesModel = this.mPreferenceModel;
        if ((63 & j) != 0) {
            if ((j & 49) != 0) {
                MutableLiveData<Boolean> mutableLiveData = preferencesModel != null ? preferencesModel.anonymousAnalytics : null;
                updateLiveDataRegistration(0, mutableLiveData);
                z3 = ViewDataBinding.safeUnbox(mutableLiveData != null ? mutableLiveData.getValue() : null);
            } else {
                z3 = false;
            }
            if ((j & 48) == 0 || preferencesModel == null) {
                bindingCommand4 = null;
                bindingCommand3 = null;
                bindingCommand6 = null;
                bindingCommand2 = null;
                bindingCommand = null;
            } else {
                bindingCommand4 = preferencesModel.onBack;
                bindingCommand6 = preferencesModel.onNext;
                bindingCommand2 = preferencesModel.reportsChange;
                bindingCommand = preferencesModel.subscriptionChange;
                bindingCommand3 = preferencesModel.analyticsChange;
            }
            if ((j & 50) != 0) {
                MutableLiveData<Boolean> mutableLiveData2 = preferencesModel != null ? preferencesModel.bugReports : null;
                updateLiveDataRegistration(1, mutableLiveData2);
                z2 = ViewDataBinding.safeUnbox(mutableLiveData2 != null ? mutableLiveData2.getValue() : null);
            } else {
                z2 = false;
            }
            if ((j & 52) != 0) {
                MutableLiveData<Boolean> mutableLiveData3 = preferencesModel != null ? preferencesModel.emailSubscription : null;
                updateLiveDataRegistration(2, mutableLiveData3);
                z = ViewDataBinding.safeUnbox(mutableLiveData3 != null ? mutableLiveData3.getValue() : null);
            } else {
                z = false;
            }
            int i2 = ((j & 56) > 0 ? 1 : ((j & 56) == 0 ? 0 : -1));
            if (i2 != 0) {
                MutableLiveData<Boolean> mutableLiveData4 = preferencesModel != null ? preferencesModel.isPrivacy : null;
                updateLiveDataRegistration(3, mutableLiveData4);
                boolean safeUnbox = ViewDataBinding.safeUnbox(mutableLiveData4 != null ? mutableLiveData4.getValue() : null);
                if (i2 != 0) {
                    if (safeUnbox) {
                        j3 = j | 128 | 512;
                        j2 = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        j3 = j | 64 | 256;
                        j2 = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                    j = j3 | j2;
                }
                Drawable drawable2 = AppCompatResources.getDrawable(this.toolBar.getContext(), safeUnbox ? C0997R.C0999drawable.add_device_back_selector : C0997R.C0999drawable.transparent);
                str = safeUnbox ? this.toolBar.getResources().getString(C0997R.string.privacy_title) : this.toolBar.getResources().getString(C0997R.string.preferences_title);
                i = safeUnbox ? 8 : 0;
                bindingCommand5 = bindingCommand6;
                drawable = drawable2;
            } else {
                bindingCommand5 = bindingCommand6;
                i = 0;
                str = null;
                drawable = null;
            }
        } else {
            i = 0;
            str = null;
            bindingCommand5 = null;
            z3 = false;
            bindingCommand4 = null;
            bindingCommand3 = null;
            drawable = null;
            z2 = false;
            z = false;
            bindingCommand2 = null;
            bindingCommand = null;
        }
        if ((j & 56) != 0) {
            this.ibNext.setVisibility(i);
            this.mboundView5.setVisibility(i);
            ToolbarAdapter.backRes(this.toolBar, drawable);
            ToolbarAdapter.setTitle(this.toolBar, str);
        }
        if ((48 & j) != 0) {
            ViewAdapter.onClickCommand(this.ibNext, bindingCommand5, false);
            com.eternal.framework.binding.viewadapter.mswitch.ViewAdapter.onCheckedChangeCommand(this.swAnalytics, bindingCommand3);
            com.eternal.framework.binding.viewadapter.mswitch.ViewAdapter.onCheckedChangeCommand(this.swReports, bindingCommand2);
            com.eternal.framework.binding.viewadapter.mswitch.ViewAdapter.onCheckedChangeCommand(this.swSubscription, bindingCommand);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand4);
        }
        if ((49 & j) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.swAnalytics, z3);
        }
        if ((50 & j) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.swReports, z2);
        }
        if ((j & 52) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.swSubscription, z);
        }
    }
}
