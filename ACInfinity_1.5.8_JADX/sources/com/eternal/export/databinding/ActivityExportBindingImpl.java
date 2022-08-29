package com.eternal.export.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.export.C2122BR;
import com.eternal.export.C2164R;
import com.eternal.export.ExportModel;

public class ActivityExportBindingImpl extends ActivityExportBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2164R.C2167id.tv_frequency, 5);
        sparseIntArray.put(C2164R.C2167id.frequency_1, 6);
        sparseIntArray.put(C2164R.C2167id.frequency_15, 7);
        sparseIntArray.put(C2164R.C2167id.frequency_30, 8);
        sparseIntArray.put(C2164R.C2167id.frequency_60, 9);
        sparseIntArray.put(C2164R.C2167id.frequency_720, 10);
        sparseIntArray.put(C2164R.C2167id.frequency_1440, 11);
        sparseIntArray.put(C2164R.C2167id.tv_start_time, 12);
        sparseIntArray.put(C2164R.C2167id.ll_start_time, 13);
        sparseIntArray.put(C2164R.C2167id.wv_start_month, 14);
        sparseIntArray.put(C2164R.C2167id.wv_start_day, 15);
        sparseIntArray.put(C2164R.C2167id.wv_start_year, 16);
        sparseIntArray.put(C2164R.C2167id.wv_start_hour, 17);
        sparseIntArray.put(C2164R.C2167id.wv_start_min, 18);
        sparseIntArray.put(C2164R.C2167id.wv_start_am_pm, 19);
        sparseIntArray.put(C2164R.C2167id.v_line, 20);
        sparseIntArray.put(C2164R.C2167id.tv_end_time, 21);
        sparseIntArray.put(C2164R.C2167id.ll_end_time, 22);
        sparseIntArray.put(C2164R.C2167id.wv_end_month, 23);
        sparseIntArray.put(C2164R.C2167id.wv_end_day, 24);
        sparseIntArray.put(C2164R.C2167id.wv_end_year, 25);
        sparseIntArray.put(C2164R.C2167id.wv_end_hour, 26);
        sparseIntArray.put(C2164R.C2167id.wv_end_min, 27);
        sparseIntArray.put(C2164R.C2167id.wv_end_am_pm, 28);
    }

    public ActivityExportBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private ActivityExportBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[3], objArr[6], objArr[11], objArr[7], objArr[8], objArr[9], objArr[10], objArr[22], objArr[13], objArr[2], objArr[1], objArr[21], objArr[5], objArr[12], objArr[4], objArr[20], objArr[28], objArr[24], objArr[26], objArr[27], objArr[23], objArr[25], objArr[19], objArr[15], objArr[17], objArr[18], objArr[14], objArr[16]);
        this.mDirtyFlags = -1;
        this.btnConfirm.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.rgFrequency.setTag((Object) null);
        this.toolbar.setTag((Object) null);
        this.txtConnectTime.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (C2122BR.model != i) {
            return false;
        }
        setModel((ExportModel) obj);
        return true;
    }

    public void setModel(ExportModel exportModel) {
        this.mModel = exportModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(C2122BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeModelTimeVisible((MutableLiveData) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeModelTimeText((MutableLiveData) obj, i2);
    }

    private boolean onChangeModelTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2122BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelTimeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2122BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r22 = this;
            r1 = r22
            monitor-enter(r22)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x00af }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00af }
            monitor-exit(r22)     // Catch:{ all -> 0x00af }
            com.eternal.export.ExportModel r0 = r1.mModel
            r6 = 15
            long r6 = r6 & r2
            r8 = 13
            r10 = 14
            r12 = 12
            r14 = 0
            int r16 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x007e
            long r6 = r2 & r12
            int r16 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0029
            if (r0 == 0) goto L_0x0029
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onExport
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onBack
            android.widget.RadioGroup$OnCheckedChangeListener r15 = r0.frequencyListener
            goto L_0x002c
        L_0x0029:
            r6 = 0
            r7 = 0
            r15 = 0
        L_0x002c:
            long r17 = r2 & r8
            int r19 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r19 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x0037
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.timeVisible
            goto L_0x0038
        L_0x0037:
            r8 = 0
        L_0x0038:
            r1.updateLiveDataRegistration(r14, r8)
            if (r8 == 0) goto L_0x0044
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0045
        L_0x0044:
            r8 = 0
        L_0x0045:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r19 == 0) goto L_0x0054
            if (r8 == 0) goto L_0x0050
            r19 = 32
            goto L_0x0052
        L_0x0050:
            r19 = 16
        L_0x0052:
            long r2 = r2 | r19
        L_0x0054:
            if (r8 == 0) goto L_0x0057
            goto L_0x005a
        L_0x0057:
            r8 = 8
            goto L_0x005b
        L_0x005a:
            r8 = 0
        L_0x005b:
            long r19 = r2 & r10
            int r9 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x007a
            if (r0 == 0) goto L_0x0066
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.timeText
            goto L_0x0067
        L_0x0066:
            r0 = 0
        L_0x0067:
            r9 = 1
            r1.updateLiveDataRegistration(r9, r0)
            if (r0 == 0) goto L_0x007a
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r21 = r6
            r6 = r0
            r0 = r15
            r15 = r21
            goto L_0x0083
        L_0x007a:
            r0 = r15
            r15 = r6
            r6 = 0
            goto L_0x0083
        L_0x007e:
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r15 = 0
        L_0x0083:
            long r12 = r12 & r2
            int r9 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0097
            android.widget.Button r9 = r1.btnConfirm
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r15, r14)
            android.widget.RadioGroup r9 = r1.rgFrequency
            com.eternal.framework.binding.viewadapter.radiogroup.ViewAdapter.onCheckedChanged(r9, r0)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolbar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r7)
        L_0x0097:
            long r9 = r2 & r10
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a2
            android.widget.TextView r0 = r1.txtConnectTime
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x00a2:
            r6 = 13
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00ae
            android.widget.TextView r0 = r1.txtConnectTime
            r0.setVisibility(r8)
        L_0x00ae:
            return
        L_0x00af:
            r0 = move-exception
            monitor-exit(r22)     // Catch:{ all -> 0x00af }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.export.databinding.ActivityExportBindingImpl.executeBindings():void");
    }
}
