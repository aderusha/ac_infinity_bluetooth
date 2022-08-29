package com.eternal.data.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.eternal.data.C1765BR;
import com.eternal.data.C1835R;
import com.eternal.data.DataModel;

public class FragmentDataBindingImpl extends FragmentDataBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final RelativeLayout mboundView0;
    private final LinearLayout mboundView1;
    private final TextView mboundView10;
    private final ImageView mboundView11;
    private final RelativeLayout mboundView12;
    private final LinearLayout mboundView14;
    private final TextView mboundView15;
    private final ImageView mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView20;
    private final View mboundView28;
    private final View mboundView31;
    private final View mboundView34;
    private final TextView mboundView36;
    private final TextView mboundView37;
    private final TextView mboundView38;
    private final TextView mboundView39;
    private final LinearLayout mboundView4;
    private final TextView mboundView40;
    private final TextView mboundView41;
    private final TextView mboundView42;
    private final TextView mboundView43;
    private final TextView mboundView44;
    private final LinearLayout mboundView45;
    private final LinearLayout mboundView46;
    private final LinearLayout mboundView47;
    private final TextView mboundView48;
    private final TextView mboundView49;
    private final TextView mboundView5;
    private final ImageView mboundView51;
    private final ImageView mboundView6;
    private final RelativeLayout mboundView7;
    private final LinearLayout mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(75);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(22, new String[]{"layout_target", "layout_target", "layout_target"}, new int[]{52, 53, 54}, new int[]{C1835R.layout.layout_target, C1835R.layout.layout_target, C1835R.layout.layout_target});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1835R.C1838id.ll_root, 55);
        sparseIntArray.put(C1835R.C1838id.ll_tmp_title, 56);
        sparseIntArray.put(C1835R.C1838id.ll_hum_title, 57);
        sparseIntArray.put(C1835R.C1838id.tv_hum_unit, 58);
        sparseIntArray.put(C1835R.C1838id.ll_vpd_title, 59);
        sparseIntArray.put(C1835R.C1838id.tv_vpd_unit, 60);
        sparseIntArray.put(C1835R.C1838id.rl_power, 61);
        sparseIntArray.put(C1835R.C1838id.tv_power, 62);
        sparseIntArray.put(C1835R.C1838id.scroll, 63);
        sparseIntArray.put(C1835R.C1838id.ll_time_tool, 64);
        sparseIntArray.put(C1835R.C1838id.iv_start, 65);
        sparseIntArray.put(C1835R.C1838id.tv_start, 66);
        sparseIntArray.put(C1835R.C1838id.tv_end, 67);
        sparseIntArray.put(C1835R.C1838id.iv_end, 68);
        sparseIntArray.put(C1835R.C1838id.tv_distance, 69);
        sparseIntArray.put(C1835R.C1838id.iv_show, 70);
        sparseIntArray.put(C1835R.C1838id.graph_tmp, 71);
        sparseIntArray.put(C1835R.C1838id.bar_tmp, 72);
        sparseIntArray.put(C1835R.C1838id.bar_hum, 73);
        sparseIntArray.put(C1835R.C1838id.bar_vpd, 74);
    }

    public FragmentDataBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 75, sIncludes, sViewsWithIds));
    }

    private FragmentDataBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 35, objArr[73], objArr[72], objArr[74], objArr[23], objArr[71], objArr[68], objArr[70], objArr[24], objArr[65], objArr[19], objArr[53], objArr[52], objArr[54], objArr[22], objArr[57], objArr[55], objArr[64], objArr[56], objArr[59], objArr[61], objArr[50], objArr[63], objArr[21], objArr[25], objArr[69], objArr[67], objArr[8], objArr[32], objArr[58], objArr[27], objArr[26], objArr[62], objArr[66], objArr[3], objArr[2], objArr[29], objArr[13], objArr[35], objArr[60], objArr[18], objArr[33], objArr[30]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.clDistance.setTag((Object) null);
        this.ivShowFilter.setTag((Object) null);
        this.ivWindSpeed.setTag((Object) null);
        setContainedBinding(this.layoutTargetHum);
        setContainedBinding(this.layoutTargetTmp);
        setContainedBinding(this.layoutTargetVpd);
        this.llContent.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        LinearLayout linearLayout = objArr[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = objArr[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        ImageView imageView = objArr[11];
        this.mboundView11 = imageView;
        imageView.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[12];
        this.mboundView12 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        LinearLayout linearLayout2 = objArr[14];
        this.mboundView14 = linearLayout2;
        linearLayout2.setTag((Object) null);
        TextView textView2 = objArr[15];
        this.mboundView15 = textView2;
        textView2.setTag((Object) null);
        ImageView imageView2 = objArr[16];
        this.mboundView16 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView3 = objArr[17];
        this.mboundView17 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = objArr[20];
        this.mboundView20 = textView4;
        textView4.setTag((Object) null);
        View view2 = objArr[28];
        this.mboundView28 = view2;
        view2.setTag((Object) null);
        View view3 = objArr[31];
        this.mboundView31 = view3;
        view3.setTag((Object) null);
        View view4 = objArr[34];
        this.mboundView34 = view4;
        view4.setTag((Object) null);
        TextView textView5 = objArr[36];
        this.mboundView36 = textView5;
        textView5.setTag((Object) null);
        TextView textView6 = objArr[37];
        this.mboundView37 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = objArr[38];
        this.mboundView38 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = objArr[39];
        this.mboundView39 = textView8;
        textView8.setTag((Object) null);
        LinearLayout linearLayout3 = objArr[4];
        this.mboundView4 = linearLayout3;
        linearLayout3.setTag((Object) null);
        TextView textView9 = objArr[40];
        this.mboundView40 = textView9;
        textView9.setTag((Object) null);
        TextView textView10 = objArr[41];
        this.mboundView41 = textView10;
        textView10.setTag((Object) null);
        TextView textView11 = objArr[42];
        this.mboundView42 = textView11;
        textView11.setTag((Object) null);
        TextView textView12 = objArr[43];
        this.mboundView43 = textView12;
        textView12.setTag((Object) null);
        TextView textView13 = objArr[44];
        this.mboundView44 = textView13;
        textView13.setTag((Object) null);
        LinearLayout linearLayout4 = objArr[45];
        this.mboundView45 = linearLayout4;
        linearLayout4.setTag((Object) null);
        LinearLayout linearLayout5 = objArr[46];
        this.mboundView46 = linearLayout5;
        linearLayout5.setTag((Object) null);
        LinearLayout linearLayout6 = objArr[47];
        this.mboundView47 = linearLayout6;
        linearLayout6.setTag((Object) null);
        TextView textView14 = objArr[48];
        this.mboundView48 = textView14;
        textView14.setTag((Object) null);
        TextView textView15 = objArr[49];
        this.mboundView49 = textView15;
        textView15.setTag((Object) null);
        TextView textView16 = objArr[5];
        this.mboundView5 = textView16;
        textView16.setTag((Object) null);
        ImageView imageView3 = objArr[51];
        this.mboundView51 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = objArr[6];
        this.mboundView6 = imageView4;
        imageView4.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[7];
        this.mboundView7 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        LinearLayout linearLayout7 = objArr[9];
        this.mboundView9 = linearLayout7;
        linearLayout7.setTag((Object) null);
        this.scOverlay.setTag((Object) null);
        this.srl.setTag((Object) null);
        this.tvAvg.setTag((Object) null);
        this.tvHum.setTag((Object) null);
        this.tvHumRow.setTag((Object) null);
        this.tvMax.setTag((Object) null);
        this.tvMin.setTag((Object) null);
        this.tvTempUnit.setTag((Object) null);
        this.tvTmp.setTag((Object) null);
        this.tvTmpRow.setTag((Object) null);
        this.tvVpd.setTag((Object) null);
        this.tvVpdRow.setTag((Object) null);
        this.tvWindSpeed.setTag((Object) null);
        this.vLineHum.setTag((Object) null);
        this.vLineTemp.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 68719476736L;
            this.mDirtyFlags_1 = 0;
        }
        this.layoutTargetTmp.invalidateAll();
        this.layoutTargetHum.invalidateAll();
        this.layoutTargetVpd.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r6.layoutTargetHum.hasPendingBindings() == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        if (r6.layoutTargetVpd.hasPendingBindings() == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r6.layoutTargetTmp.hasPendingBindings() == false) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0031 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x002f
            long r0 = r6.mDirtyFlags_1     // Catch:{ all -> 0x0031 }
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0011
            goto L_0x002f
        L_0x0011:
            monitor-exit(r6)     // Catch:{ all -> 0x0031 }
            com.eternal.data.databinding.LayoutTargetBinding r0 = r6.layoutTargetTmp
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001b
            return r4
        L_0x001b:
            com.eternal.data.databinding.LayoutTargetBinding r0 = r6.layoutTargetHum
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0024
            return r4
        L_0x0024:
            com.eternal.data.databinding.LayoutTargetBinding r0 = r6.layoutTargetVpd
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x002d
            return r4
        L_0x002d:
            r0 = 0
            return r0
        L_0x002f:
            monitor-exit(r6)     // Catch:{ all -> 0x0031 }
            return r4
        L_0x0031:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0031 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.databinding.FragmentDataBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int i, Object obj) {
        if (C1765BR.model != i) {
            return false;
        }
        setModel((DataModel) obj);
        return true;
    }

    public void setModel(DataModel dataModel) {
        this.mModel = dataModel;
        synchronized (this) {
            this.mDirtyFlags |= 34359738368L;
        }
        notifyPropertyChanged(C1765BR.model);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.layoutTargetTmp.setLifecycleOwner(lifecycleOwner);
        this.layoutTargetHum.setLifecycleOwner(lifecycleOwner);
        this.layoutTargetVpd.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeModelHumGraphVisible((MutableLiveData) obj, i2);
            case 1:
                return onChangeModelMaxHum((ObservableField) obj, i2);
            case 2:
                return onChangeModelTargetHumTxt((MutableLiveData) obj, i2);
            case 3:
                return onChangeModelAvgTmp((ObservableField) obj, i2);
            case 4:
                return onChangeLayoutTargetTmp((LayoutTargetBinding) obj, i2);
            case 5:
                return onChangeModelFanSize((ObservableField) obj, i2);
            case 6:
                return onChangeModelTmpFlag((ObservableField) obj, i2);
            case 7:
                return onChangeModelVpdGraphVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeModelTargetTmpModelExpand((MutableLiveData) obj, i2);
            case 9:
                return onChangeModelTargetTmpTxt((MutableLiveData) obj, i2);
            case 10:
                return onChangeModelTargetVpdTxt((MutableLiveData) obj, i2);
            case 11:
                return onChangeModelAvgHum((ObservableField) obj, i2);
            case 12:
                return onChangeModelOverlayRes((MutableLiveData) obj, i2);
            case 13:
                return onChangeModelFanType((ObservableInt) obj, i2);
            case 14:
                return onChangeModelMinHum((ObservableField) obj, i2);
            case 15:
                return onChangeLayoutTargetVpd((LayoutTargetBinding) obj, i2);
            case 16:
                return onChangeModelMinVpd((ObservableField) obj, i2);
            case 17:
                return onChangeModelMaxVpd((ObservableField) obj, i2);
            case 18:
                return onChangeModelTargetHumModelExpand((MutableLiveData) obj, i2);
            case 19:
                return onChangeModelTmpSize((ObservableField) obj, i2);
            case 20:
                return onChangeModelHumVisible((MutableLiveData) obj, i2);
            case 21:
                return onChangeLayoutTargetHum((LayoutTargetBinding) obj, i2);
            case 22:
                return onChangeModelAvgVpd((ObservableField) obj, i2);
            case 23:
                return onChangeModelVpdVisible((MutableLiveData) obj, i2);
            case 24:
                return onChangeModelTargetVpdModelExpand((MutableLiveData) obj, i2);
            case 25:
                return onChangeModelPowerOff((ObservableBoolean) obj, i2);
            case 26:
                return onChangeModelVpdSize((ObservableField) obj, i2);
            case 27:
                return onChangeModelMinTmp((ObservableField) obj, i2);
            case 28:
                return onChangeModelMaxTmp((ObservableField) obj, i2);
            case 29:
                return onChangeModelTmpGraphVisible((MutableLiveData) obj, i2);
            case 30:
                return onChangeModelFanTypeTitle((ObservableField) obj, i2);
            case 31:
                return onChangeModelIsLoading((MutableLiveData) obj, i2);
            case 32:
                return onChangeModelPerSize((ObservableField) obj, i2);
            case 33:
                return onChangeModelOverlayVisible((MutableLiveData) obj, i2);
            case 34:
                return onChangeModelTfpVisible((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeModelHumGraphVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelMaxHum(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelTargetHumTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelAvgTmp(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeLayoutTargetTmp(LayoutTargetBinding layoutTargetBinding, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeModelFanSize(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeModelTmpFlag(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeModelVpdGraphVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeModelTargetTmpModelExpand(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeModelTargetTmpTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeModelTargetVpdTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelAvgHum(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelOverlayRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeModelFanType(ObservableInt observableInt, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelMinHum(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeLayoutTargetVpd(LayoutTargetBinding layoutTargetBinding, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelMinVpd(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelMaxVpd(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelTargetHumModelExpand(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeModelTmpSize(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeModelHumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeLayoutTargetHum(LayoutTargetBinding layoutTargetBinding, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeModelAvgVpd(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeModelVpdVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeModelTargetVpdModelExpand(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeModelPowerOff(ObservableBoolean observableBoolean, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeModelVpdSize(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeModelMinTmp(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeModelMaxTmp(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeModelTmpGraphVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeModelFanTypeTitle(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeModelIsLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeModelPerSize(ObservableField<String> observableField, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    private boolean onChangeModelOverlayVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        return true;
    }

    private boolean onChangeModelTfpVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17179869184L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0269  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x02fb  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x034f  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0368  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x03e5  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0437  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0450  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x045e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x051d  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0534  */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x056f  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0580  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0599  */
    /* JADX WARNING: Removed duplicated region for block: B:353:0x05aa  */
    /* JADX WARNING: Removed duplicated region for block: B:359:0x05c3  */
    /* JADX WARNING: Removed duplicated region for block: B:363:0x05d4  */
    /* JADX WARNING: Removed duplicated region for block: B:369:0x05ed  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x0600  */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x063f  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x0652  */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x0676  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0696  */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x06a6  */
    /* JADX WARNING: Removed duplicated region for block: B:425:0x06bf  */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x06d2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:444:0x0708  */
    /* JADX WARNING: Removed duplicated region for block: B:448:0x0718  */
    /* JADX WARNING: Removed duplicated region for block: B:465:0x0792  */
    /* JADX WARNING: Removed duplicated region for block: B:620:0x0ab3  */
    /* JADX WARNING: Removed duplicated region for block: B:621:0x0ab7  */
    /* JADX WARNING: Removed duplicated region for block: B:635:0x0ada  */
    /* JADX WARNING: Removed duplicated region for block: B:647:0x0af8  */
    /* JADX WARNING: Removed duplicated region for block: B:649:0x0afd  */
    /* JADX WARNING: Removed duplicated region for block: B:652:0x0b05  */
    /* JADX WARNING: Removed duplicated region for block: B:663:0x0b21  */
    /* JADX WARNING: Removed duplicated region for block: B:666:0x0b31  */
    /* JADX WARNING: Removed duplicated region for block: B:667:0x0b7b  */
    /* JADX WARNING: Removed duplicated region for block: B:670:0x0b8b  */
    /* JADX WARNING: Removed duplicated region for block: B:673:0x0b9c  */
    /* JADX WARNING: Removed duplicated region for block: B:676:0x0ba9  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:684:0x0bce  */
    /* JADX WARNING: Removed duplicated region for block: B:687:0x0bdb  */
    /* JADX WARNING: Removed duplicated region for block: B:695:0x0c08  */
    /* JADX WARNING: Removed duplicated region for block: B:698:0x0c19  */
    /* JADX WARNING: Removed duplicated region for block: B:701:0x0c26  */
    /* JADX WARNING: Removed duplicated region for block: B:704:0x0c4e  */
    /* JADX WARNING: Removed duplicated region for block: B:707:0x0c78  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:710:0x0ca4  */
    /* JADX WARNING: Removed duplicated region for block: B:711:0x0cb4  */
    /* JADX WARNING: Removed duplicated region for block: B:714:0x0cc0  */
    /* JADX WARNING: Removed duplicated region for block: B:717:0x0cd7  */
    /* JADX WARNING: Removed duplicated region for block: B:720:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:723:0x0d01  */
    /* JADX WARNING: Removed duplicated region for block: B:726:0x0d18  */
    /* JADX WARNING: Removed duplicated region for block: B:729:0x0d2f  */
    /* JADX WARNING: Removed duplicated region for block: B:732:0x0d46  */
    /* JADX WARNING: Removed duplicated region for block: B:735:0x0d5d  */
    /* JADX WARNING: Removed duplicated region for block: B:738:0x0d70  */
    /* JADX WARNING: Removed duplicated region for block: B:741:0x0d81  */
    /* JADX WARNING: Removed duplicated region for block: B:749:0x0da8  */
    /* JADX WARNING: Removed duplicated region for block: B:752:0x0db9  */
    /* JADX WARNING: Removed duplicated region for block: B:755:0x0dca  */
    /* JADX WARNING: Removed duplicated region for block: B:758:0x0dd7  */
    /* JADX WARNING: Removed duplicated region for block: B:761:0x0dfb  */
    /* JADX WARNING: Removed duplicated region for block: B:764:0x0e0c  */
    /* JADX WARNING: Removed duplicated region for block: B:767:0x0e1d  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:770:0x0e2e  */
    /* JADX WARNING: Removed duplicated region for block: B:773:0x0e3f  */
    /* JADX WARNING: Removed duplicated region for block: B:776:0x0e4c  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r112 = this;
            r1 = r112
            monitor-enter(r112)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0e63 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0e63 }
            long r6 = r1.mDirtyFlags_1     // Catch:{ all -> 0x0e63 }
            r1.mDirtyFlags_1 = r4     // Catch:{ all -> 0x0e63 }
            monitor-exit(r112)     // Catch:{ all -> 0x0e63 }
            com.eternal.data.DataModel r0 = r1.mModel
            r8 = 103486277642(0x181843480a, double:5.1129014599E-313)
            long r8 = r8 & r2
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x001d
            int r8 = com.eternal.data.C1835R.font.avenir_book
            goto L_0x001e
        L_0x001d:
            r8 = 0
        L_0x001e:
            r11 = 137436823535(0x1fffdf7fef, double:6.7902812982E-313)
            long r11 = r11 & r2
            r13 = 103079215104(0x1800000000, double:5.0927898983E-313)
            r17 = 103079215106(0x1800000002, double:5.0927898984E-313)
            r19 = 103616086016(0x1820000000, double:5.1193148457E-313)
            r21 = 103087603712(0x1800800000, double:5.0932043506E-313)
            r23 = 4194304(0x400000, double:2.0722615E-317)
            r25 = 4611686018427387904(0x4000000000000000, double:2.0)
            r27 = 103079216128(0x1800000400, double:5.0927899489E-313)
            r29 = 103079215108(0x1800000004, double:5.0927898985E-313)
            r31 = 103079215616(0x1800000200, double:5.0927899236E-313)
            r33 = 128(0x80, double:6.32E-322)
            r35 = 103080263680(0x1800100000, double:5.09284170485E-313)
            r37 = 103617134593(0x1820100001, double:5.1193666523E-313)
            r39 = 103080263681(0x1800100001, double:5.0928417049E-313)
            r41 = 103625523329(0x1820900081, double:5.1197811109E-313)
            r15 = 1
            r16 = 0
            int r45 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x07e3
            long r11 = r2 & r17
            int r45 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x0082
            if (r0 == 0) goto L_0x0074
            androidx.databinding.ObservableField<java.lang.String> r11 = r0.maxHum
            goto L_0x0076
        L_0x0074:
            r11 = r16
        L_0x0076:
            r1.updateRegistration((int) r15, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0082
            java.lang.Object r11 = r11.get()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x0084
        L_0x0082:
            r11 = r16
        L_0x0084:
            long r45 = r2 & r13
            int r12 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x00af
            if (r0 == 0) goto L_0x00af
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.showFilterPopup
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onTargetVpd
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onExport
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onCloseOverlay
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onTargetHum
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onTargetTmp
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r0.showPopup
            r50 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onRefresh
            r111 = r5
            r5 = r4
            r4 = r50
            r50 = r15
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r10
            r10 = r9
            r9 = r111
            goto L_0x00ba
        L_0x00af:
            r4 = r16
            r5 = r4
            r9 = r5
            r10 = r9
            r12 = r10
            r13 = r12
            r14 = r13
            r15 = r14
            r50 = r15
        L_0x00ba:
            long r51 = r2 & r29
            r48 = 0
            int r53 = (r51 > r48 ? 1 : (r51 == r48 ? 0 : -1))
            r51 = r4
            if (r53 == 0) goto L_0x00f0
            if (r0 == 0) goto L_0x00cb
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.targetHumTxt
            r52 = r5
            goto L_0x00cf
        L_0x00cb:
            r52 = r5
            r4 = r16
        L_0x00cf:
            r5 = 2
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00dc
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00de
        L_0x00dc:
            r4 = r16
        L_0x00de:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r53 == 0) goto L_0x00f5
            if (r5 == 0) goto L_0x00ea
            r53 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x00ed
        L_0x00ea:
            r53 = 524288(0x80000, double:2.590327E-318)
        L_0x00ed:
            long r6 = r6 | r53
            goto L_0x00f5
        L_0x00f0:
            r52 = r5
            r4 = r16
            r5 = 0
        L_0x00f5:
            r53 = 103079477248(0x1800040000, double:5.09280284995E-313)
            long r53 = r2 & r53
            r55 = 180(0xb4, float:2.52E-43)
            r48 = 0
            int r56 = (r53 > r48 ? 1 : (r53 == r48 ? 0 : -1))
            r53 = r4
            if (r56 == 0) goto L_0x014b
            if (r0 == 0) goto L_0x010b
            com.eternal.data.TargetModel r4 = r0.targetHumModel
            goto L_0x010d
        L_0x010b:
            r4 = r16
        L_0x010d:
            r54 = r5
            if (r4 == 0) goto L_0x0116
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r4.expand
            r57 = r4
            goto L_0x011a
        L_0x0116:
            r57 = r4
            r5 = r16
        L_0x011a:
            r4 = 18
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0128
            java.lang.Object r4 = r5.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x012a
        L_0x0128:
            r4 = r16
        L_0x012a:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            if (r4 != 0) goto L_0x0132
            r4 = 1
            goto L_0x0133
        L_0x0132:
            r4 = 0
        L_0x0133:
            if (r56 == 0) goto L_0x0141
            if (r4 == 0) goto L_0x013a
            r58 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x013f
        L_0x013a:
            r58 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x013f:
            long r2 = r2 | r58
        L_0x0141:
            if (r4 == 0) goto L_0x0145
            r4 = 0
            goto L_0x0147
        L_0x0145:
            r4 = 180(0xb4, float:2.52E-43)
        L_0x0147:
            r5 = r4
            r4 = r57
            goto L_0x0150
        L_0x014b:
            r54 = r5
            r4 = r16
            r5 = 0
        L_0x0150:
            r56 = 103079215112(0x1800000008, double:5.0927898987E-313)
            long r56 = r2 & r56
            r48 = 0
            int r58 = (r56 > r48 ? 1 : (r56 == r48 ? 0 : -1))
            r56 = r4
            if (r58 == 0) goto L_0x0177
            if (r0 == 0) goto L_0x0166
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.avgTmp
            r57 = r5
            goto L_0x016a
        L_0x0166:
            r57 = r5
            r4 = r16
        L_0x016a:
            r5 = 3
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0179
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x017b
        L_0x0177:
            r57 = r5
        L_0x0179:
            r4 = r16
        L_0x017b:
            r58 = 103079215136(0x1800000020, double:5.0927898999E-313)
            long r58 = r2 & r58
            r48 = 0
            int r5 = (r58 > r48 ? 1 : (r58 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x01a0
            if (r0 == 0) goto L_0x018f
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.fanSize
            r58 = r4
            goto L_0x0193
        L_0x018f:
            r58 = r4
            r5 = r16
        L_0x0193:
            r4 = 5
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01a2
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x01a4
        L_0x01a0:
            r58 = r4
        L_0x01a2:
            r4 = r16
        L_0x01a4:
            r59 = 103079215168(0x1800000040, double:5.0927899015E-313)
            long r59 = r2 & r59
            r48 = 0
            int r5 = (r59 > r48 ? 1 : (r59 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x01c9
            if (r0 == 0) goto L_0x01b8
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.tmpFlag
            r59 = r4
            goto L_0x01bc
        L_0x01b8:
            r59 = r4
            r5 = r16
        L_0x01bc:
            r4 = 6
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01cb
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x01cd
        L_0x01c9:
            r59 = r4
        L_0x01cb:
            r4 = r16
        L_0x01cd:
            long r60 = r2 & r31
            r48 = 0
            int r5 = (r60 > r48 ? 1 : (r60 == r48 ? 0 : -1))
            r60 = r4
            if (r5 == 0) goto L_0x0202
            if (r0 == 0) goto L_0x01de
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.targetTmpTxt
            r61 = r9
            goto L_0x01e2
        L_0x01de:
            r61 = r9
            r4 = r16
        L_0x01e2:
            r9 = 9
            r1.updateLiveDataRegistration(r9, r4)
            if (r4 == 0) goto L_0x01f0
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x01f2
        L_0x01f0:
            r4 = r16
        L_0x01f2:
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0207
            if (r9 == 0) goto L_0x01fd
            r62 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            goto L_0x01ff
        L_0x01fd:
            r62 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
        L_0x01ff:
            long r2 = r2 | r62
            goto L_0x0207
        L_0x0202:
            r61 = r9
            r4 = r16
            r9 = 0
        L_0x0207:
            long r62 = r2 & r27
            r48 = 0
            int r5 = (r62 > r48 ? 1 : (r62 == r48 ? 0 : -1))
            r62 = r4
            if (r5 == 0) goto L_0x023e
            if (r0 == 0) goto L_0x0218
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.targetVpdTxt
            r63 = r9
            goto L_0x021c
        L_0x0218:
            r63 = r9
            r4 = r16
        L_0x021c:
            r9 = 10
            r1.updateLiveDataRegistration(r9, r4)
            if (r4 == 0) goto L_0x022a
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x022c
        L_0x022a:
            r4 = r16
        L_0x022c:
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0243
            if (r9 == 0) goto L_0x0238
            r64 = 65536(0x10000, double:3.2379E-319)
            goto L_0x023b
        L_0x0238:
            r64 = 32768(0x8000, double:1.61895E-319)
        L_0x023b:
            long r6 = r6 | r64
            goto L_0x0243
        L_0x023e:
            r63 = r9
            r4 = r16
            r9 = 0
        L_0x0243:
            r64 = 103079217152(0x1800000800, double:5.0927899995E-313)
            long r64 = r2 & r64
            r48 = 0
            int r5 = (r64 > r48 ? 1 : (r64 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0269
            if (r0 == 0) goto L_0x0257
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.avgHum
            r64 = r4
            goto L_0x025b
        L_0x0257:
            r64 = r4
            r5 = r16
        L_0x025b:
            r4 = 11
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x026b
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x026d
        L_0x0269:
            r64 = r4
        L_0x026b:
            r4 = r16
        L_0x026d:
            r65 = 103079219200(0x1800001000, double:5.0927901007E-313)
            long r65 = r2 & r65
            r48 = 0
            int r5 = (r65 > r48 ? 1 : (r65 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x029a
            if (r0 == 0) goto L_0x0281
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r0.overlayRes
            r65 = r4
            goto L_0x0285
        L_0x0281:
            r65 = r4
            r5 = r16
        L_0x0285:
            r4 = 12
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0293
            java.lang.Object r4 = r5.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0295
        L_0x0293:
            r4 = r16
        L_0x0295:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x029d
        L_0x029a:
            r65 = r4
            r4 = 0
        L_0x029d:
            r66 = 103079223296(0x1800002000, double:5.09279030306E-313)
            long r66 = r2 & r66
            r48 = 0
            int r5 = (r66 > r48 ? 1 : (r66 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x02c1
            if (r0 == 0) goto L_0x02b1
            androidx.databinding.ObservableInt r5 = r0.fanType
            r66 = r4
            goto L_0x02b5
        L_0x02b1:
            r66 = r4
            r5 = r16
        L_0x02b5:
            r4 = 13
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x02c3
            int r4 = r5.get()
            goto L_0x02c4
        L_0x02c1:
            r66 = r4
        L_0x02c3:
            r4 = 0
        L_0x02c4:
            r67 = 103079231488(0x1800004000, double:5.0927907078E-313)
            long r67 = r2 & r67
            r48 = 0
            int r5 = (r67 > r48 ? 1 : (r67 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x02ea
            if (r0 == 0) goto L_0x02d8
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.minHum
            r67 = r4
            goto L_0x02dc
        L_0x02d8:
            r67 = r4
            r5 = r16
        L_0x02dc:
            r4 = 14
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x02ec
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x02ee
        L_0x02ea:
            r67 = r4
        L_0x02ec:
            r4 = r16
        L_0x02ee:
            r68 = 103079280640(0x1800010000, double:5.09279313623E-313)
            long r68 = r2 & r68
            r48 = 0
            int r5 = (r68 > r48 ? 1 : (r68 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0314
            if (r0 == 0) goto L_0x0302
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.minVpd
            r68 = r4
            goto L_0x0306
        L_0x0302:
            r68 = r4
            r5 = r16
        L_0x0306:
            r4 = 16
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0316
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0318
        L_0x0314:
            r68 = r4
        L_0x0316:
            r4 = r16
        L_0x0318:
            r69 = 103079346176(0x1800020000, double:5.09279637413E-313)
            long r69 = r2 & r69
            r48 = 0
            int r5 = (r69 > r48 ? 1 : (r69 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x033e
            if (r0 == 0) goto L_0x032c
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.maxVpd
            r69 = r4
            goto L_0x0330
        L_0x032c:
            r69 = r4
            r5 = r16
        L_0x0330:
            r4 = 17
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0340
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0342
        L_0x033e:
            r69 = r4
        L_0x0340:
            r4 = r16
        L_0x0342:
            r70 = 103079739392(0x1800080000, double:5.0928158016E-313)
            long r70 = r2 & r70
            r48 = 0
            int r5 = (r70 > r48 ? 1 : (r70 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0368
            if (r0 == 0) goto L_0x0356
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.tmpSize
            r70 = r4
            goto L_0x035a
        L_0x0356:
            r70 = r4
            r5 = r16
        L_0x035a:
            r4 = 19
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x036a
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x036c
        L_0x0368:
            r70 = r4
        L_0x036a:
            r4 = r16
        L_0x036c:
            long r71 = r2 & r39
            r48 = 0
            int r5 = (r71 > r48 ? 1 : (r71 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x03cd
            if (r0 == 0) goto L_0x037b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.humVisible
            r71 = r4
            goto L_0x037f
        L_0x037b:
            r71 = r4
            r5 = r16
        L_0x037f:
            r4 = 20
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x038d
            java.lang.Object r4 = r5.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x038f
        L_0x038d:
            r4 = r16
        L_0x038f:
            boolean r72 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            long r73 = r2 & r35
            r48 = 0
            int r75 = (r73 > r48 ? 1 : (r73 == r48 ? 0 : -1))
            if (r75 == 0) goto L_0x03aa
            if (r72 == 0) goto L_0x03a3
            r73 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x03a8
        L_0x03a3:
            r73 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x03a8:
            long r2 = r2 | r73
        L_0x03aa:
            long r73 = r2 & r39
            int r75 = (r73 > r48 ? 1 : (r73 == r48 ? 0 : -1))
            if (r75 != 0) goto L_0x03b6
            long r73 = r6 & r33
            int r75 = (r73 > r48 ? 1 : (r73 == r48 ? 0 : -1))
            if (r75 == 0) goto L_0x03bf
        L_0x03b6:
            if (r72 == 0) goto L_0x03bb
            long r2 = r2 | r25
            goto L_0x03bf
        L_0x03bb:
            r73 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r2 = r2 | r73
        L_0x03bf:
            long r73 = r2 & r35
            r48 = 0
            int r75 = (r73 > r48 ? 1 : (r73 == r48 ? 0 : -1))
            if (r75 == 0) goto L_0x03d6
            if (r72 == 0) goto L_0x03ca
            goto L_0x03d6
        L_0x03ca:
            r73 = 8
            goto L_0x03d8
        L_0x03cd:
            r71 = r4
            r48 = 0
            r4 = r16
            r5 = r4
            r72 = 0
        L_0x03d6:
            r73 = 0
        L_0x03d8:
            r74 = 103079215360(0x1800000100, double:5.09278991096E-313)
            long r74 = r2 & r74
            int r76 = (r74 > r48 ? 1 : (r74 == r48 ? 0 : -1))
            r74 = r4
            if (r76 == 0) goto L_0x0423
            if (r0 == 0) goto L_0x03ea
            com.eternal.data.TargetModel r4 = r0.targetTmpModel
            goto L_0x03ec
        L_0x03ea:
            r4 = r16
        L_0x03ec:
            r75 = r5
            if (r4 == 0) goto L_0x03f5
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r4.expand
            r47 = r4
            goto L_0x03f9
        L_0x03f5:
            r47 = r4
            r5 = r16
        L_0x03f9:
            r4 = 8
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0407
            java.lang.Object r5 = r5.getValue()
            java.lang.Integer r5 = (java.lang.Integer) r5
            goto L_0x0409
        L_0x0407:
            r5 = r16
        L_0x0409:
            int r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r5)
            if (r5 != 0) goto L_0x0411
            r5 = 1
            goto L_0x0412
        L_0x0411:
            r5 = 0
        L_0x0412:
            if (r76 == 0) goto L_0x041d
            if (r5 == 0) goto L_0x0419
            r76 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            goto L_0x041b
        L_0x0419:
            r76 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
        L_0x041b:
            long r2 = r2 | r76
        L_0x041d:
            if (r5 == 0) goto L_0x0420
            goto L_0x0429
        L_0x0420:
            r5 = 180(0xb4, float:2.52E-43)
            goto L_0x042a
        L_0x0423:
            r75 = r5
            r4 = 8
            r47 = r16
        L_0x0429:
            r5 = 0
        L_0x042a:
            r76 = 103083409408(0x1800400000, double:5.09299712447E-313)
            long r76 = r2 & r76
            r48 = 0
            int r78 = (r76 > r48 ? 1 : (r76 == r48 ? 0 : -1))
            if (r78 == 0) goto L_0x0450
            if (r0 == 0) goto L_0x043e
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.avgVpd
            r77 = r5
            goto L_0x0442
        L_0x043e:
            r77 = r5
            r4 = r16
        L_0x0442:
            r5 = 22
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0452
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0454
        L_0x0450:
            r77 = r5
        L_0x0452:
            r4 = r16
        L_0x0454:
            long r78 = r2 & r41
            r48 = 0
            int r5 = (r78 > r48 ? 1 : (r78 == r48 ? 0 : -1))
            r78 = r4
            if (r5 == 0) goto L_0x051d
            if (r0 == 0) goto L_0x0470
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpdVisible
            r79 = r4
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.tmpGraphVisible
            r80 = r10
            r111 = r9
            r9 = r4
            r4 = r79
            r79 = r111
            goto L_0x0477
        L_0x0470:
            r79 = r9
            r80 = r10
            r4 = r16
            r9 = r4
        L_0x0477:
            r10 = 23
            r1.updateLiveDataRegistration(r10, r4)
            r10 = 29
            r1.updateLiveDataRegistration(r10, r9)
            if (r4 == 0) goto L_0x048a
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x048c
        L_0x048a:
            r4 = r16
        L_0x048c:
            if (r9 == 0) goto L_0x0495
            java.lang.Object r9 = r9.getValue()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            goto L_0x0497
        L_0x0495:
            r9 = r16
        L_0x0497:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            boolean r9 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r9)
            long r81 = r2 & r21
            r48 = 0
            int r10 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r10 == 0) goto L_0x04b0
            if (r4 == 0) goto L_0x04ac
            r81 = 16384(0x4000, double:8.0948E-320)
            goto L_0x04ae
        L_0x04ac:
            r81 = 8192(0x2000, double:4.0474E-320)
        L_0x04ae:
            long r6 = r6 | r81
        L_0x04b0:
            if (r5 == 0) goto L_0x04bd
            if (r4 == 0) goto L_0x04b8
            r81 = 262144(0x40000, double:1.295163E-318)
            goto L_0x04bb
        L_0x04b8:
            r81 = 131072(0x20000, double:6.47582E-319)
        L_0x04bb:
            long r6 = r6 | r81
        L_0x04bd:
            long r81 = r2 & r19
            r48 = 0
            int r5 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x04d4
            if (r9 == 0) goto L_0x04cd
            r81 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            goto L_0x04d2
        L_0x04cd:
            r81 = 8796093022208(0x80000000000, double:4.345847379897E-311)
        L_0x04d2:
            long r2 = r2 | r81
        L_0x04d4:
            long r81 = r2 & r37
            int r5 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x04e5
            if (r9 == 0) goto L_0x04e1
            r43 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r2 = r2 | r43
            goto L_0x04e5
        L_0x04e1:
            r81 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r2 = r2 | r81
        L_0x04e5:
            long r81 = r2 & r41
            int r5 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x04f4
            if (r9 == 0) goto L_0x04f0
            r81 = 4
            goto L_0x04f2
        L_0x04f0:
            r81 = 2
        L_0x04f2:
            long r6 = r6 | r81
        L_0x04f4:
            long r81 = r6 & r23
            int r5 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0504
            if (r9 == 0) goto L_0x0501
            r81 = 256(0x100, double:1.265E-321)
            long r5 = r6 | r81
            goto L_0x0503
        L_0x0501:
            long r5 = r6 | r33
        L_0x0503:
            r6 = r5
        L_0x0504:
            long r81 = r2 & r21
            int r5 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0510
            if (r4 == 0) goto L_0x050d
            goto L_0x0510
        L_0x050d:
            r5 = 8
            goto L_0x0511
        L_0x0510:
            r5 = 0
        L_0x0511:
            long r81 = r2 & r19
            int r10 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            if (r10 == 0) goto L_0x0526
            if (r9 == 0) goto L_0x051a
            goto L_0x0526
        L_0x051a:
            r10 = 8
            goto L_0x0527
        L_0x051d:
            r79 = r9
            r80 = r10
            r48 = 0
            r4 = 0
            r5 = 0
            r9 = 0
        L_0x0526:
            r10 = 0
        L_0x0527:
            r81 = 103112769536(0x1802000000, double:5.09444770753E-313)
            long r81 = r2 & r81
            int r83 = (r81 > r48 ? 1 : (r81 == r48 ? 0 : -1))
            r81 = r4
            if (r83 == 0) goto L_0x056f
            if (r0 == 0) goto L_0x053b
            androidx.databinding.ObservableBoolean r4 = r0.powerOff
            r82 = r5
            goto L_0x053f
        L_0x053b:
            r82 = r5
            r4 = r16
        L_0x053f:
            r5 = 25
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x054b
            boolean r4 = r4.get()
            goto L_0x054c
        L_0x054b:
            r4 = 0
        L_0x054c:
            if (r83 == 0) goto L_0x0557
            if (r4 == 0) goto L_0x0553
            r83 = 1024(0x400, double:5.06E-321)
            goto L_0x0555
        L_0x0553:
            r83 = 512(0x200, double:2.53E-321)
        L_0x0555:
            long r6 = r6 | r83
        L_0x0557:
            if (r4 == 0) goto L_0x0562
            android.widget.TextView r4 = r1.mboundView20
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.data.C1835R.string.power_off
            goto L_0x056a
        L_0x0562:
            android.widget.TextView r4 = r1.mboundView20
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.data.C1835R.string.power_on
        L_0x056a:
            java.lang.String r4 = r4.getString(r5)
            goto L_0x0573
        L_0x056f:
            r82 = r5
            r4 = r16
        L_0x0573:
            r83 = 103146323968(0x1804000000, double:5.09610551674E-313)
            long r83 = r2 & r83
            r48 = 0
            int r5 = (r83 > r48 ? 1 : (r83 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0599
            if (r0 == 0) goto L_0x0587
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.vpdSize
            r83 = r4
            goto L_0x058b
        L_0x0587:
            r83 = r4
            r5 = r16
        L_0x058b:
            r4 = 26
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x059b
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x059d
        L_0x0599:
            r83 = r4
        L_0x059b:
            r4 = r16
        L_0x059d:
            r84 = 103213432832(0x1808000000, double:5.09942113516E-313)
            long r84 = r2 & r84
            r48 = 0
            int r5 = (r84 > r48 ? 1 : (r84 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x05c3
            if (r0 == 0) goto L_0x05b1
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.minTmp
            r84 = r4
            goto L_0x05b5
        L_0x05b1:
            r84 = r4
            r5 = r16
        L_0x05b5:
            r4 = 27
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x05c5
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x05c7
        L_0x05c3:
            r84 = r4
        L_0x05c5:
            r4 = r16
        L_0x05c7:
            r85 = 103347650560(0x1810000000, double:5.106052372E-313)
            long r85 = r2 & r85
            r48 = 0
            int r5 = (r85 > r48 ? 1 : (r85 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x05ed
            if (r0 == 0) goto L_0x05db
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.maxTmp
            r85 = r4
            goto L_0x05df
        L_0x05db:
            r85 = r4
            r5 = r16
        L_0x05df:
            r4 = 28
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x05ef
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x05f1
        L_0x05ed:
            r85 = r4
        L_0x05ef:
            r4 = r16
        L_0x05f1:
            r86 = 103095992320(0x1801000000, double:5.0936188029E-313)
            long r86 = r2 & r86
            r48 = 0
            int r5 = (r86 > r48 ? 1 : (r86 == r48 ? 0 : -1))
            r86 = r4
            if (r5 == 0) goto L_0x063f
            if (r0 == 0) goto L_0x0605
            com.eternal.data.TargetModel r4 = r0.targetVpdModel
            goto L_0x0607
        L_0x0605:
            r4 = r16
        L_0x0607:
            r87 = r9
            if (r4 == 0) goto L_0x0610
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r9 = r4.expand
            r88 = r4
            goto L_0x0614
        L_0x0610:
            r88 = r4
            r9 = r16
        L_0x0614:
            r4 = 24
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0622
            java.lang.Object r4 = r9.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0624
        L_0x0622:
            r4 = r16
        L_0x0624:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            if (r4 != 0) goto L_0x062c
            r4 = 1
            goto L_0x062d
        L_0x062c:
            r4 = 0
        L_0x062d:
            if (r5 == 0) goto L_0x0638
            if (r4 == 0) goto L_0x0634
            r89 = 4096(0x1000, double:2.0237E-320)
            goto L_0x0636
        L_0x0634:
            r89 = 2048(0x800, double:1.0118E-320)
        L_0x0636:
            long r6 = r6 | r89
        L_0x0638:
            if (r4 == 0) goto L_0x063c
            r55 = 0
        L_0x063c:
            r4 = r88
            goto L_0x0645
        L_0x063f:
            r87 = r9
            r4 = r16
            r55 = 0
        L_0x0645:
            r88 = 104152956928(0x1840000000, double:5.1458397931E-313)
            long r88 = r2 & r88
            r48 = 0
            int r5 = (r88 > r48 ? 1 : (r88 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0667
            if (r0 == 0) goto L_0x0657
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.fanTypeTitle
            goto L_0x0659
        L_0x0657:
            r5 = r16
        L_0x0659:
            r9 = 30
            r1.updateRegistration((int) r9, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0667
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0669
        L_0x0667:
            r5 = r16
        L_0x0669:
            r88 = 105226698752(0x1880000000, double:5.19888968786E-313)
            long r88 = r2 & r88
            r48 = 0
            int r9 = (r88 > r48 ? 1 : (r88 == r48 ? 0 : -1))
            if (r9 == 0) goto L_0x0696
            if (r0 == 0) goto L_0x067d
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.isLoading
            r88 = r4
            goto L_0x0681
        L_0x067d:
            r88 = r4
            r9 = r16
        L_0x0681:
            r4 = 31
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x068f
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0691
        L_0x068f:
            r4 = r16
        L_0x0691:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0699
        L_0x0696:
            r88 = r4
            r4 = 0
        L_0x0699:
            r89 = 107374182400(0x1900000000, double:5.3049894774E-313)
            long r89 = r2 & r89
            r48 = 0
            int r9 = (r89 > r48 ? 1 : (r89 == r48 ? 0 : -1))
            if (r9 == 0) goto L_0x06bf
            if (r0 == 0) goto L_0x06ad
            androidx.databinding.ObservableField<java.lang.String> r9 = r0.perSize
            r89 = r4
            goto L_0x06b1
        L_0x06ad:
            r89 = r4
            r9 = r16
        L_0x06b1:
            r4 = 32
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r9)
            if (r9 == 0) goto L_0x06c1
            java.lang.Object r4 = r9.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x06c3
        L_0x06bf:
            r89 = r4
        L_0x06c1:
            r4 = r16
        L_0x06c3:
            r90 = 111669149696(0x1a00000000, double:5.5171890565E-313)
            long r90 = r2 & r90
            r48 = 0
            int r9 = (r90 > r48 ? 1 : (r90 == r48 ? 0 : -1))
            r90 = r4
            if (r9 == 0) goto L_0x0708
            if (r0 == 0) goto L_0x06d9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.overlayVisible
            r91 = r5
            goto L_0x06dd
        L_0x06d9:
            r91 = r5
            r4 = r16
        L_0x06dd:
            r5 = 33
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x06eb
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x06ed
        L_0x06eb:
            r4 = r16
        L_0x06ed:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x0702
            if (r4 == 0) goto L_0x06fb
            r92 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            goto L_0x0700
        L_0x06fb:
            r92 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
        L_0x0700:
            long r2 = r2 | r92
        L_0x0702:
            if (r4 == 0) goto L_0x0705
            goto L_0x070a
        L_0x0705:
            r4 = 8
            goto L_0x070b
        L_0x0708:
            r91 = r5
        L_0x070a:
            r4 = 0
        L_0x070b:
            r92 = 120259084288(0x1c00000000, double:5.9415882147E-313)
            long r92 = r2 & r92
            r48 = 0
            int r5 = (r92 > r48 ? 1 : (r92 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0792
            if (r0 == 0) goto L_0x071f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.tfpVisible
            r92 = r4
            goto L_0x0723
        L_0x071f:
            r92 = r4
            r9 = r16
        L_0x0723:
            r4 = 34
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0731
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0733
        L_0x0731:
            r4 = r16
        L_0x0733:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0748
            if (r4 == 0) goto L_0x0741
            r93 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x0746
        L_0x0741:
            r93 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x0746:
            long r2 = r2 | r93
        L_0x0748:
            if (r4 == 0) goto L_0x074c
            r4 = 0
            goto L_0x074e
        L_0x074c:
            r4 = 8
        L_0x074e:
            r107 = r10
            r108 = r11
            r11 = r12
            r5 = r50
            r10 = r51
            r109 = r55
            r9 = r56
            r105 = r59
            r102 = r60
            r50 = r65
            r98 = r66
            r51 = r68
            r96 = r69
            r95 = r70
            r103 = r71
            r110 = r73
            r106 = r77
            r97 = r78
            r12 = r80
            r59 = r83
            r104 = r84
            r100 = r89
            r101 = r90
            r60 = r91
            r99 = r92
            r66 = r4
            r55 = r6
            r6 = r47
            r4 = r52
            r65 = r54
            r52 = r58
            r47 = r62
            r54 = r85
            r58 = r86
            goto L_0x07d7
        L_0x0792:
            r92 = r4
            r107 = r10
            r108 = r11
            r11 = r12
            r5 = r50
            r10 = r51
            r4 = r52
            r109 = r55
            r9 = r56
            r52 = r58
            r105 = r59
            r102 = r60
            r50 = r65
            r98 = r66
            r51 = r68
            r96 = r69
            r95 = r70
            r103 = r71
            r110 = r73
            r106 = r77
            r97 = r78
            r12 = r80
            r59 = r83
            r104 = r84
            r58 = r86
            r100 = r89
            r101 = r90
            r60 = r91
            r99 = r92
            r66 = 0
            r55 = r6
            r6 = r47
            r65 = r54
            r47 = r62
            r54 = r85
        L_0x07d7:
            r7 = r88
            r62 = r57
            r57 = r8
            r8 = r61
            r61 = r82
            goto L_0x0840
        L_0x07e3:
            r55 = r6
            r57 = r8
            r4 = r16
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r47 = r15
            r50 = r47
            r51 = r50
            r52 = r51
            r53 = r52
            r54 = r53
            r58 = r54
            r59 = r58
            r60 = r59
            r64 = r60
            r74 = r64
            r75 = r74
            r95 = r75
            r96 = r95
            r97 = r96
            r101 = r97
            r102 = r101
            r103 = r102
            r104 = r103
            r105 = r104
            r108 = r105
            r61 = 0
            r62 = 0
            r63 = 0
            r65 = 0
            r66 = 0
            r67 = 0
            r72 = 0
            r79 = 0
            r81 = 0
            r87 = 0
            r98 = 0
            r99 = 0
            r100 = 0
            r106 = 0
            r107 = 0
            r109 = 0
            r110 = 0
        L_0x0840:
            long r68 = r2 & r31
            r48 = 0
            int r70 = (r68 > r48 ? 1 : (r68 == r48 ? 0 : -1))
            if (r70 == 0) goto L_0x0862
            if (r63 == 0) goto L_0x085b
            r63 = r4
            android.widget.TextView r4 = r1.mboundView5
            android.content.res.Resources r4 = r4.getResources()
            r68 = r12
            int r12 = com.eternal.data.C1835R.string.target_tmp
            java.lang.String r4 = r4.getString(r12)
            goto L_0x0868
        L_0x085b:
            r63 = r4
            r68 = r12
            r4 = r47
            goto L_0x0868
        L_0x0862:
            r63 = r4
            r68 = r12
            r4 = r16
        L_0x0868:
            r43 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r43 = r2 & r43
            r48 = 0
            int r12 = (r43 > r48 ? 1 : (r43 == r48 ? 0 : -1))
            if (r12 != 0) goto L_0x088e
            r43 = 262274(0x40082, double:1.295806E-318)
            long r43 = r55 & r43
            int r47 = (r43 > r48 ? 1 : (r43 == r48 ? 0 : -1))
            if (r47 == 0) goto L_0x087c
            goto L_0x088e
        L_0x087c:
            r43 = r4
            r44 = r11
            r4 = r16
            r11 = r4
            r12 = r11
            r70 = r12
            r47 = 0
            r69 = 0
            r73 = 0
            goto L_0x0963
        L_0x088e:
            r43 = 2
            long r43 = r55 & r43
            int r47 = (r43 > r48 ? 1 : (r43 == r48 ? 0 : -1))
            r43 = r4
            if (r47 == 0) goto L_0x08b7
            if (r0 == 0) goto L_0x089f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.humGraphVisible
            r44 = r11
            goto L_0x08a3
        L_0x089f:
            r44 = r11
            r4 = r16
        L_0x08a3:
            r11 = 0
            r1.updateLiveDataRegistration(r11, r4)
            if (r4 == 0) goto L_0x08b0
            java.lang.Object r11 = r4.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            goto L_0x08b2
        L_0x08b0:
            r11 = r16
        L_0x08b2:
            boolean r69 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            goto L_0x08be
        L_0x08b7:
            r44 = r11
            r4 = r16
            r11 = r4
            r69 = 0
        L_0x08be:
            r70 = 262144(0x40000, double:1.295163E-318)
            long r70 = r55 & r70
            r48 = 0
            int r73 = (r70 > r48 ? 1 : (r70 == r48 ? 0 : -1))
            r70 = r4
            if (r73 == 0) goto L_0x08ea
            if (r0 == 0) goto L_0x08d2
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpdGraphVisible
            r71 = r11
            goto L_0x08d6
        L_0x08d2:
            r71 = r11
            r4 = r16
        L_0x08d6:
            r11 = 7
            r1.updateLiveDataRegistration(r11, r4)
            if (r4 == 0) goto L_0x08e3
            java.lang.Object r11 = r4.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            goto L_0x08e5
        L_0x08e3:
            r11 = r16
        L_0x08e5:
            boolean r73 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            goto L_0x08f1
        L_0x08ea:
            r71 = r11
            r4 = r16
            r11 = r4
            r73 = 0
        L_0x08f1:
            if (r12 != 0) goto L_0x0904
            r77 = 130(0x82, double:6.4E-322)
            long r77 = r55 & r77
            r48 = 0
            int r12 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x08fe
            goto L_0x0904
        L_0x08fe:
            r12 = r11
            r11 = r71
            r47 = 0
            goto L_0x0963
        L_0x0904:
            if (r0 == 0) goto L_0x090b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.humVisible
            r72 = r4
            goto L_0x090f
        L_0x090b:
            r72 = r4
            r12 = r75
        L_0x090f:
            r4 = 20
            r1.updateLiveDataRegistration(r4, r12)
            if (r12 == 0) goto L_0x091e
            java.lang.Object r4 = r12.getValue()
            r74 = r4
            java.lang.Boolean r74 = (java.lang.Boolean) r74
        L_0x091e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r74)
            long r74 = r2 & r35
            r48 = 0
            int r12 = (r74 > r48 ? 1 : (r74 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x0939
            if (r4 == 0) goto L_0x0932
            r74 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x0937
        L_0x0932:
            r74 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x0937:
            long r2 = r2 | r74
        L_0x0939:
            long r74 = r2 & r39
            int r12 = (r74 > r48 ? 1 : (r74 == r48 ? 0 : -1))
            if (r12 != 0) goto L_0x0945
            long r74 = r55 & r33
            int r12 = (r74 > r48 ? 1 : (r74 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x094e
        L_0x0945:
            if (r4 == 0) goto L_0x094a
            long r2 = r2 | r25
            goto L_0x094e
        L_0x094a:
            r74 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r2 = r2 | r74
        L_0x094e:
            if (r47 == 0) goto L_0x0958
            r12 = r69 & r4
            r47 = r12
            r12 = r11
            r11 = r71
            goto L_0x095d
        L_0x0958:
            r12 = r11
            r11 = r71
            r47 = 0
        L_0x095d:
            r111 = r72
            r72 = r4
            r4 = r111
        L_0x0963:
            long r74 = r2 & r27
            r48 = 0
            int r71 = (r74 > r48 ? 1 : (r74 == r48 ? 0 : -1))
            if (r71 == 0) goto L_0x0983
            r71 = r4
            if (r79 == 0) goto L_0x097e
            android.widget.TextView r4 = r1.mboundView15
            android.content.res.Resources r4 = r4.getResources()
            r74 = r11
            int r11 = com.eternal.data.C1835R.string.target_vpd
            java.lang.String r64 = r4.getString(r11)
            goto L_0x0980
        L_0x097e:
            r74 = r11
        L_0x0980:
            r4 = r64
            goto L_0x0989
        L_0x0983:
            r71 = r4
            r74 = r11
            r4 = r16
        L_0x0989:
            long r77 = r2 & r29
            r48 = 0
            int r11 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r11 == 0) goto L_0x09a9
            if (r65 == 0) goto L_0x09a4
            android.widget.TextView r11 = r1.mboundView10
            android.content.res.Resources r11 = r11.getResources()
            r64 = r12
            int r12 = com.eternal.data.C1835R.string.target_hum
            java.lang.String r11 = r11.getString(r12)
            r16 = r11
            goto L_0x09ab
        L_0x09a4:
            r64 = r12
            r16 = r53
            goto L_0x09ab
        L_0x09a9:
            r64 = r12
        L_0x09ab:
            r11 = r16
            long r77 = r2 & r37
            r48 = 0
            int r12 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x09ca
            if (r87 == 0) goto L_0x09ba
            r16 = r72
            goto L_0x09bc
        L_0x09ba:
            r16 = 0
        L_0x09bc:
            if (r12 == 0) goto L_0x09cc
            if (r16 == 0) goto L_0x09c5
            r77 = 1
            long r55 = r55 | r77
            goto L_0x09cc
        L_0x09c5:
            r77 = -9223372036854775808
            long r2 = r2 | r77
            goto L_0x09cc
        L_0x09ca:
            r16 = 0
        L_0x09cc:
            long r77 = r2 & r41
            r48 = 0
            int r12 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x0a1e
            if (r87 == 0) goto L_0x09d8
            r47 = 1
        L_0x09d8:
            if (r81 == 0) goto L_0x09db
            goto L_0x09dd
        L_0x09db:
            r73 = 0
        L_0x09dd:
            if (r12 == 0) goto L_0x09ee
            if (r47 == 0) goto L_0x09e7
            r77 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x09ec
        L_0x09e7:
            r77 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x09ec:
            long r2 = r2 | r77
        L_0x09ee:
            r77 = 103087603840(0x1800800080, double:5.09320435694E-313)
            long r77 = r2 & r77
            r48 = 0
            int r12 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x0a04
            if (r73 == 0) goto L_0x0a00
            r77 = 16
            goto L_0x0a02
        L_0x0a00:
            r77 = 8
        L_0x0a02:
            long r55 = r55 | r77
        L_0x0a04:
            long r77 = r2 & r41
            int r53 = (r77 > r48 ? 1 : (r77 == r48 ? 0 : -1))
            if (r53 == 0) goto L_0x0a14
            if (r73 == 0) goto L_0x0a0f
            long r55 = r55 | r23
            goto L_0x0a14
        L_0x0a0f:
            r77 = 2097152(0x200000, double:1.0361308E-317)
            long r55 = r55 | r77
        L_0x0a14:
            if (r12 == 0) goto L_0x0a1c
            if (r73 == 0) goto L_0x0a19
            goto L_0x0a1c
        L_0x0a19:
            r12 = 8
            goto L_0x0a23
        L_0x0a1c:
            r12 = 0
            goto L_0x0a23
        L_0x0a1e:
            r12 = 0
            r47 = 0
            r73 = 0
        L_0x0a23:
            long r25 = r2 & r25
            r48 = 0
            int r53 = (r25 > r48 ? 1 : (r25 == r48 ? 0 : -1))
            if (r53 != 0) goto L_0x0a3b
            r25 = 1
            long r25 = r55 & r25
            int r53 = (r25 > r48 ? 1 : (r25 == r48 ? 0 : -1))
            if (r53 == 0) goto L_0x0a34
            goto L_0x0a3b
        L_0x0a34:
            r26 = r4
            r25 = r12
            r4 = r69
            goto L_0x0a5b
        L_0x0a3b:
            r25 = r12
            if (r0 == 0) goto L_0x0a44
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.humGraphVisible
            r26 = r4
            goto L_0x0a48
        L_0x0a44:
            r26 = r4
            r12 = r70
        L_0x0a48:
            r4 = 0
            r1.updateLiveDataRegistration(r4, r12)
            if (r12 == 0) goto L_0x0a55
            java.lang.Object r4 = r12.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0a57
        L_0x0a55:
            r4 = r74
        L_0x0a57:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
        L_0x0a5b:
            r69 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            long r69 = r2 & r69
            r48 = 0
            int r12 = (r69 > r48 ? 1 : (r69 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x0a86
            if (r0 == 0) goto L_0x0a6d
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.vpdGraphVisible
            goto L_0x0a6f
        L_0x0a6d:
            r0 = r71
        L_0x0a6f:
            r12 = 7
            r1.updateLiveDataRegistration(r12, r0)
            if (r0 == 0) goto L_0x0a7d
            java.lang.Object r0 = r0.getValue()
            r12 = r0
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x0a7f
        L_0x0a7d:
            r12 = r64
        L_0x0a7f:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            r0 = r0 & r81
            goto L_0x0a87
        L_0x0a86:
            r0 = 0
        L_0x0a87:
            long r64 = r2 & r41
            r48 = 0
            int r12 = (r64 > r48 ? 1 : (r64 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x0aa3
            if (r47 == 0) goto L_0x0a92
            r0 = 1
        L_0x0a92:
            if (r12 == 0) goto L_0x0a9d
            if (r0 == 0) goto L_0x0a99
            r64 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            goto L_0x0a9b
        L_0x0a99:
            r64 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
        L_0x0a9b:
            long r2 = r2 | r64
        L_0x0a9d:
            if (r0 == 0) goto L_0x0aa0
            goto L_0x0aa3
        L_0x0aa0:
            r0 = 8
            goto L_0x0aa4
        L_0x0aa3:
            r0 = 0
        L_0x0aa4:
            long r64 = r2 & r39
            r48 = 0
            int r12 = (r64 > r48 ? 1 : (r64 == r48 ? 0 : -1))
            if (r12 != 0) goto L_0x0ab7
            long r33 = r55 & r33
            int r47 = (r33 > r48 ? 1 : (r33 == r48 ? 0 : -1))
            if (r47 == 0) goto L_0x0ab3
            goto L_0x0ab7
        L_0x0ab3:
            r12 = 0
            r33 = 0
            goto L_0x0ad2
        L_0x0ab7:
            if (r72 == 0) goto L_0x0abc
            r33 = r4
            goto L_0x0abe
        L_0x0abc:
            r33 = 0
        L_0x0abe:
            if (r12 == 0) goto L_0x0ac9
            if (r33 == 0) goto L_0x0ac5
            r64 = 64
            goto L_0x0ac7
        L_0x0ac5:
            r64 = 32
        L_0x0ac7:
            long r55 = r55 | r64
        L_0x0ac9:
            if (r12 == 0) goto L_0x0ad1
            if (r33 == 0) goto L_0x0ace
            goto L_0x0ad1
        L_0x0ace:
            r12 = 8
            goto L_0x0ad2
        L_0x0ad1:
            r12 = 0
        L_0x0ad2:
            long r64 = r2 & r37
            r48 = 0
            int r34 = (r64 > r48 ? 1 : (r64 == r48 ? 0 : -1))
            if (r34 == 0) goto L_0x0aef
            if (r16 == 0) goto L_0x0add
            goto L_0x0ade
        L_0x0add:
            r4 = 0
        L_0x0ade:
            if (r34 == 0) goto L_0x0ae9
            if (r4 == 0) goto L_0x0ae5
            r64 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            goto L_0x0ae7
        L_0x0ae5:
            r64 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
        L_0x0ae7:
            long r2 = r2 | r64
        L_0x0ae9:
            if (r4 == 0) goto L_0x0aec
            goto L_0x0aef
        L_0x0aec:
            r4 = 8
            goto L_0x0af0
        L_0x0aef:
            r4 = 0
        L_0x0af0:
            long r23 = r55 & r23
            r48 = 0
            int r16 = (r23 > r48 ? 1 : (r23 == r48 ? 0 : -1))
            if (r16 == 0) goto L_0x0afd
            if (r87 == 0) goto L_0x0aff
            r33 = 1
            goto L_0x0aff
        L_0x0afd:
            r33 = 0
        L_0x0aff:
            long r23 = r2 & r41
            int r16 = (r23 > r48 ? 1 : (r23 == r48 ? 0 : -1))
            if (r16 == 0) goto L_0x0b21
            if (r73 == 0) goto L_0x0b08
            goto L_0x0b0a
        L_0x0b08:
            r33 = 0
        L_0x0b0a:
            if (r16 == 0) goto L_0x0b15
            if (r33 == 0) goto L_0x0b11
            r23 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            goto L_0x0b13
        L_0x0b11:
            r23 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
        L_0x0b13:
            long r2 = r2 | r23
        L_0x0b15:
            if (r33 == 0) goto L_0x0b1a
            r76 = 0
            goto L_0x0b1c
        L_0x0b1a:
            r76 = 8
        L_0x0b1c:
            r16 = r4
            r4 = r76
            goto L_0x0b24
        L_0x0b21:
            r16 = r4
            r4 = 0
        L_0x0b24:
            r23 = 103079215104(0x1800000000, double:5.0927898983E-313)
            long r23 = r2 & r23
            r33 = 0
            int r46 = (r23 > r33 ? 1 : (r23 == r33 ? 0 : -1))
            if (r46 == 0) goto L_0x0b7b
            r23 = r4
            androidx.constraintlayout.widget.ConstraintLayout r4 = r1.clDistance
            r24 = r0
            r0 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r8, r0)
            android.widget.ImageView r4 = r1.ivShowFilter
            r8 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r8)
            com.eternal.data.databinding.LayoutTargetBinding r4 = r1.layoutTargetHum
            r4.setTargetModel(r9)
            com.eternal.data.databinding.LayoutTargetBinding r4 = r1.layoutTargetTmp
            r4.setTargetModel(r6)
            com.eternal.data.databinding.LayoutTargetBinding r4 = r1.layoutTargetVpd
            r4.setTargetModel(r7)
            android.widget.LinearLayout r4 = r1.mboundView14
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r14, r0)
            android.widget.LinearLayout r4 = r1.mboundView4
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r10, r0)
            android.widget.TextView r4 = r1.mboundView48
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r15, r5)
            android.widget.TextView r4 = r1.mboundView49
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r13, r5)
            android.widget.ImageView r4 = r1.mboundView51
            r6 = r44
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r6, r5)
            android.widget.LinearLayout r4 = r1.mboundView9
            r5 = r68
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r0)
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.srl
            r4 = r63
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.onRefreshCommand(r0, r4)
            goto L_0x0b7f
        L_0x0b7b:
            r24 = r0
            r23 = r4
        L_0x0b7f:
            r4 = 103079223296(0x1800002000, double:5.09279030306E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0b92
            android.widget.ImageView r0 = r1.ivWindSpeed
            r4 = r67
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r4)
        L_0x0b92:
            r4 = 120259084288(0x1c00000000, double:5.9415882147E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0ba3
            android.widget.LinearLayout r0 = r1.mboundView1
            r4 = r66
            r0.setVisibility(r4)
        L_0x0ba3:
            long r4 = r2 & r29
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0bae
            android.widget.TextView r0 = r1.mboundView10
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x0bae:
            r4 = 103079477248(0x1800040000, double:5.09280284995E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0bc8
            int r0 = getBuildSdkInt()
            r4 = 11
            if (r0 < r4) goto L_0x0bc8
            android.widget.ImageView r0 = r1.mboundView11
            r4 = r62
            float r4 = (float) r4
            r0.setRotation(r4)
        L_0x0bc8:
            long r4 = r2 & r21
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0bd5
            android.widget.RelativeLayout r0 = r1.mboundView12
            r4 = r61
            r0.setVisibility(r4)
        L_0x0bd5:
            long r4 = r2 & r27
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0be2
            android.widget.TextView r0 = r1.mboundView15
            r4 = r26
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0be2:
            r4 = 103095992320(0x1801000000, double:5.0936188029E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0bfc
            int r0 = getBuildSdkInt()
            r4 = 11
            if (r0 < r4) goto L_0x0bfc
            android.widget.ImageView r0 = r1.mboundView16
            r4 = r109
            float r4 = (float) r4
            r0.setRotation(r4)
        L_0x0bfc:
            r4 = 104152956928(0x1840000000, double:5.1458397931E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0c0f
            android.widget.TextView r0 = r1.mboundView17
            r4 = r60
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0c0f:
            r4 = 103112769536(0x1802000000, double:5.09444770753E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0c20
            android.widget.TextView r0 = r1.mboundView20
            r4 = r59
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0c20:
            long r4 = r2 & r19
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0c46
            android.view.View r0 = r1.mboundView28
            r10 = r107
            r0.setVisibility(r10)
            android.widget.TextView r0 = r1.mboundView36
            r0.setVisibility(r10)
            android.widget.TextView r0 = r1.mboundView37
            r0.setVisibility(r10)
            android.widget.TextView r0 = r1.mboundView38
            r0.setVisibility(r10)
            android.widget.LinearLayout r0 = r1.mboundView45
            r0.setVisibility(r10)
            android.widget.TextView r0 = r1.tvTmpRow
            r0.setVisibility(r10)
        L_0x0c46:
            long r4 = r2 & r39
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0c6c
            android.view.View r0 = r1.mboundView31
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView39
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView40
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView41
            r0.setVisibility(r12)
            android.widget.LinearLayout r0 = r1.mboundView46
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.tvHumRow
            r0.setVisibility(r12)
        L_0x0c6c:
            r4 = 103087603840(0x1800800080, double:5.09320435694E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0c98
            android.view.View r0 = r1.mboundView34
            r12 = r25
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView42
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView43
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.mboundView44
            r0.setVisibility(r12)
            android.widget.LinearLayout r0 = r1.mboundView47
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.tvVpdRow
            r0.setVisibility(r12)
        L_0x0c98:
            r4 = 103347650560(0x1810000000, double:5.106052372E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0cb4
            android.widget.TextView r0 = r1.mboundView36
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r8 = r57
            r5 = r58
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
            goto L_0x0cb6
        L_0x0cb4:
            r8 = r57
        L_0x0cb6:
            r4 = 103213432832(0x1808000000, double:5.09942113516E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0ccd
            android.widget.TextView r0 = r1.mboundView37
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r54
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0ccd:
            r4 = 103079215112(0x1800000008, double:5.0927898987E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0ce4
            android.widget.TextView r0 = r1.mboundView38
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r52
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0ce4:
            long r4 = r2 & r17
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0cf7
            android.widget.TextView r0 = r1.mboundView39
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r11 = r108
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r11, r4, r8)
        L_0x0cf7:
            r4 = 103079231488(0x1800004000, double:5.0927907078E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d0e
            android.widget.TextView r0 = r1.mboundView40
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r51
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0d0e:
            r4 = 103079217152(0x1800000800, double:5.0927899995E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d25
            android.widget.TextView r0 = r1.mboundView41
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r50
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0d25:
            r4 = 103079346176(0x1800020000, double:5.09279637413E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d3c
            android.widget.TextView r0 = r1.mboundView42
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r95
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0d3c:
            r4 = 103079280640(0x1800010000, double:5.09279313623E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d53
            android.widget.TextView r0 = r1.mboundView43
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r96
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0d53:
            r4 = 103083409408(0x1800400000, double:5.09299712447E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d6a
            android.widget.TextView r0 = r1.mboundView44
            int r4 = com.eternal.data.C1835R.C1836color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r5 = r97
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r4, r8)
        L_0x0d6a:
            long r4 = r2 & r31
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d77
            android.widget.TextView r0 = r1.mboundView5
            r4 = r43
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0d77:
            r4 = 103079219200(0x1800001000, double:5.0927901007E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0d88
            android.widget.ImageView r0 = r1.mboundView51
            r4 = r98
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.glideRes(r0, r4)
        L_0x0d88:
            r4 = 103079215360(0x1800000100, double:5.09278991096E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0da2
            int r0 = getBuildSdkInt()
            r4 = 11
            if (r0 < r4) goto L_0x0da2
            android.widget.ImageView r0 = r1.mboundView6
            r4 = r106
            float r4 = (float) r4
            r0.setRotation(r4)
        L_0x0da2:
            long r4 = r2 & r35
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0daf
            android.widget.RelativeLayout r0 = r1.mboundView7
            r4 = r110
            r0.setVisibility(r4)
        L_0x0daf:
            r4 = 111669149696(0x1a00000000, double:5.5171890565E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0dc0
            androidx.core.widget.NestedScrollView r0 = r1.scOverlay
            r4 = r99
            r0.setVisibility(r4)
        L_0x0dc0:
            r4 = 105226698752(0x1880000000, double:5.19888968786E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0dd1
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.srl
            r4 = r100
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.setRefreshing(r0, r4)
        L_0x0dd1:
            long r4 = r2 & r41
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0def
            android.widget.TextView r0 = r1.tvAvg
            r4 = r24
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.tvMax
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.tvMin
            r0.setVisibility(r4)
            android.view.View r0 = r1.vLineHum
            r4 = r23
            r0.setVisibility(r4)
        L_0x0def:
            r4 = 107374182400(0x1900000000, double:5.3049894774E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e02
            android.widget.TextView r0 = r1.tvHum
            r4 = r101
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e02:
            r4 = 103079215168(0x1800000040, double:5.0927899015E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e13
            android.widget.TextView r0 = r1.tvTempUnit
            r4 = r102
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e13:
            r4 = 103079739392(0x1800080000, double:5.0928158016E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e24
            android.widget.TextView r0 = r1.tvTmp
            r4 = r103
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e24:
            r4 = 103146323968(0x1804000000, double:5.09610551674E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e35
            android.widget.TextView r0 = r1.tvVpd
            r4 = r104
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e35:
            r4 = 103079215136(0x1800000020, double:5.0927898999E-313)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e46
            android.widget.TextView r0 = r1.tvWindSpeed
            r4 = r105
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e46:
            long r2 = r2 & r37
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e53
            android.view.View r0 = r1.vLineTemp
            r4 = r16
            r0.setVisibility(r4)
        L_0x0e53:
            com.eternal.data.databinding.LayoutTargetBinding r0 = r1.layoutTargetTmp
            executeBindingsOn(r0)
            com.eternal.data.databinding.LayoutTargetBinding r0 = r1.layoutTargetHum
            executeBindingsOn(r0)
            com.eternal.data.databinding.LayoutTargetBinding r0 = r1.layoutTargetVpd
            executeBindingsOn(r0)
            return
        L_0x0e63:
            r0 = move-exception
            monitor-exit(r112)     // Catch:{ all -> 0x0e63 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.databinding.FragmentDataBindingImpl.executeBindings():void");
    }
}
