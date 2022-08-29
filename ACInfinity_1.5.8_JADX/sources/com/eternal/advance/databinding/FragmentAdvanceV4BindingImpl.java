package com.eternal.advance.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.advance.C1200BR;
import com.eternal.advance.C1202R;
import com.eternal.advance.model.AdvanceModelV4;
import com.eternal.advance.model.ItemModelV4;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.textview.viewAdapter;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class FragmentAdvanceV4BindingImpl extends FragmentAdvanceV4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1202R.C1205id.tb_port, 9);
        sparseIntArray.put(C1202R.C1205id.ct_tab, 10);
        sparseIntArray.put(C1202R.C1205id.v_slide, 11);
    }

    public FragmentAdvanceV4BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private FragmentAdvanceV4BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[10], objArr[8], objArr[6], objArr[5], objArr[7], objArr[9], objArr[4], objArr[3], objArr[2], objArr[1], objArr[11]);
        this.mDirtyFlags = -1;
        this.ivbAddProgram.setTag((Object) null);
        this.listAlarms.setTag((Object) null);
        this.listAutomations.setTag((Object) null);
        this.listNotification.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.tvAlarms.setTag((Object) null);
        this.tvAutomations.setTag((Object) null);
        this.vClickAlarms.setTag((Object) null);
        this.vClickAutomation.setTag((Object) null);
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
        if (C1200BR.modelV4 != i) {
            return false;
        }
        setModelV4((AdvanceModelV4) obj);
        return true;
    }

    public void setModelV4(AdvanceModelV4 advanceModelV4) {
        this.mModelV4 = advanceModelV4;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(C1200BR.modelV4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeModelV4Notifications((ObservableList) obj, i2);
        }
        if (i == 1) {
            return onChangeModelV4SelectTab((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeModelV4Automations((ObservableList) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeModelV4Alarms((ObservableList) obj, i2);
    }

    private boolean onChangeModelV4Notifications(ObservableList<ItemModelV4> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelV4SelectTab(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelV4Automations(ObservableList<ItemModelV4> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelV4Alarms(ObservableList<ItemModelV4> observableList, int i) {
        if (i != C1200BR._all) {
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
        long j2;
        ObservableList<ItemModelV4> observableList;
        BindingCommand<Void> bindingCommand;
        int i;
        int i2;
        BindingCommand<Void> bindingCommand2;
        ObservableList<ItemModelV4> observableList2;
        int i3;
        BindingCommand<Void> bindingCommand3;
        ObservableList<ItemModelV4> observableList3;
        int i4;
        int i5;
        ItemBinding<ItemModelV4> itemBinding;
        ObservableList<ItemModelV4> observableList4;
        int i6;
        long j3;
        int i7;
        ObservableList<ItemModelV4> observableList5;
        long j4;
        int i8;
        long j5;
        long j6;
        long j7;
        long j8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AdvanceModelV4 advanceModelV4 = this.mModelV4;
        if ((63 & j) != 0) {
            ItemBinding<ItemModelV4> itemBinding2 = ((61 & j) == 0 || advanceModelV4 == null) ? null : advanceModelV4.itemBinding;
            if ((j & 48) == 0 || advanceModelV4 == null) {
                bindingCommand3 = null;
                bindingCommand2 = null;
                bindingCommand = null;
            } else {
                bindingCommand3 = advanceModelV4.onAdd;
                bindingCommand2 = advanceModelV4.onAlarms;
                bindingCommand = advanceModelV4.onAutomations;
            }
            if ((j & 49) != 0) {
                observableList = advanceModelV4 != null ? advanceModelV4.notifications : null;
                updateRegistration(0, (ObservableList) observableList);
            } else {
                observableList = null;
            }
            int i9 = ((j & 50) > 0 ? 1 : ((j & 50) == 0 ? 0 : -1));
            if (i9 != 0) {
                MutableLiveData<Integer> mutableLiveData = advanceModelV4 != null ? advanceModelV4.selectTab : null;
                boolean z = true;
                updateLiveDataRegistration(1, mutableLiveData);
                int safeUnbox = ViewDataBinding.safeUnbox(mutableLiveData != null ? mutableLiveData.getValue() : null);
                boolean z2 = safeUnbox == 0;
                boolean z3 = safeUnbox == 1;
                if (safeUnbox != 2) {
                    z = false;
                }
                if (i9 != 0) {
                    if (z2) {
                        j8 = j | 128;
                        j7 = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        j8 = j | 64;
                        j7 = PlaybackStateCompat.ACTION_PREPARE;
                    }
                    j = j8 | j7;
                }
                if ((j & 50) != 0) {
                    if (z3) {
                        j6 = j | 512;
                        j5 = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        j6 = j | 256;
                        j5 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                    j = j6 | j5;
                }
                if ((j & 50) != 0) {
                    j |= z ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
                i3 = getColorFromResource(this.tvAutomations, z2 ? C1202R.C1203color.color_15BAFF : C1202R.C1203color.white);
                i7 = z2 ? 0 : 8;
                i2 = z3 ? getColorFromResource(this.tvAlarms, C1202R.C1203color.color_15BAFF) : getColorFromResource(this.tvAlarms, C1202R.C1203color.white);
                i = z3 ? 0 : 8;
                i6 = z ? 0 : 8;
                j3 = 52;
            } else {
                i3 = 0;
                i7 = 0;
                i2 = 0;
                i = 0;
                j3 = 52;
                i6 = 0;
            }
            if ((j & j3) != 0) {
                if (advanceModelV4 != null) {
                    observableList5 = advanceModelV4.automations;
                    i8 = 2;
                } else {
                    i8 = 2;
                    observableList5 = null;
                }
                updateRegistration(i8, (ObservableList) observableList5);
                j4 = 56;
            } else {
                j4 = 56;
                observableList5 = null;
            }
            if ((j & j4) != 0) {
                ObservableList<ItemModelV4> observableList6 = advanceModelV4 != null ? advanceModelV4.alarms : null;
                updateRegistration(3, (ObservableList) observableList6);
                i5 = i7;
                i4 = i6;
                j2 = 48;
                observableList2 = observableList5;
                ItemBinding<ItemModelV4> itemBinding3 = itemBinding2;
                observableList3 = observableList6;
                itemBinding = itemBinding3;
            } else {
                itemBinding = itemBinding2;
                i5 = i7;
                i4 = i6;
                observableList3 = null;
                j2 = 48;
                observableList2 = observableList5;
            }
        } else {
            j2 = 48;
            itemBinding = null;
            i5 = 0;
            i4 = 0;
            observableList3 = null;
            bindingCommand3 = null;
            i3 = 0;
            observableList2 = null;
            bindingCommand2 = null;
            i2 = 0;
            i = 0;
            bindingCommand = null;
            observableList = null;
        }
        if ((j & j2) != 0) {
            observableList4 = observableList;
            ViewAdapter.onClickCommand(this.ivbAddProgram, bindingCommand3, false);
            ViewAdapter.onClickCommand(this.vClickAlarms, bindingCommand2, false);
            ViewAdapter.onClickCommand(this.vClickAutomation, bindingCommand, false);
        } else {
            observableList4 = observableList;
        }
        if ((32 & j) != 0) {
            BindingRecyclerViewAdapters.setLayoutManager(this.listAlarms, LayoutManagers.linear());
            BindingRecyclerViewAdapters.setLayoutManager(this.listAutomations, LayoutManagers.linear());
            BindingRecyclerViewAdapters.setLayoutManager(this.listNotification, LayoutManagers.linear());
        }
        if ((j & 50) != 0) {
            this.listAlarms.setVisibility(i);
            this.listAutomations.setVisibility(i5);
            this.listNotification.setVisibility(i4);
            viewAdapter.setTextColor(this.tvAlarms, i2);
            viewAdapter.setTextColor(this.tvAutomations, i3);
        }
        if ((56 & j) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.listAlarms, itemBinding, observableList3, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
        if ((52 & j) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.listAutomations, itemBinding, observableList2, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
        if ((j & 49) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.listNotification, itemBinding, observableList4, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
    }
}
