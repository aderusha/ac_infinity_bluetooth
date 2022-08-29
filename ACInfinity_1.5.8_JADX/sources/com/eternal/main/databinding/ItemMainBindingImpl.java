package com.eternal.main.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.eternal.main.C2323BR;
import com.eternal.main.C2343R;
import com.eternal.main.model.ItemModel;
import com.eternal.widget.ExpandableLayout;
import com.google.common.primitives.Longs;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

public class ItemMainBindingImpl extends ItemMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private long mDirtyFlags_2;
    private final ConstraintLayout mboundView0;
    private final LinearLayout mboundView10;
    private final LinearLayout mboundView11;
    private final ImageView mboundView12;
    private final RelativeLayout mboundView17;
    private final RelativeLayout mboundView21;
    private final Space mboundView26;
    private final RelativeLayout mboundView27;
    private final TextView mboundView28;
    private final LinearLayout mboundView3;
    private final Space mboundView32;
    private final TextView mboundView34;
    private final LinearLayout mboundView35;
    private final TextView mboundView37;
    private final ImageView mboundView38;
    private final Space mboundView40;
    private final View mboundView41;
    private final ExpandableLayout mboundView42;
    private final LinearLayout mboundView43;
    private final LinearLayout mboundView44;
    private final LinearLayout mboundView45;
    private final LinearLayout mboundView46;
    private final View mboundView47;
    private final ImageView mboundView48;
    private final ImageView mboundView6;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(65);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(43, new String[]{"layout_port"}, new int[]{49}, new int[]{C2343R.layout.layout_port});
        includedLayouts.setIncludes(45, new String[]{"layout_port", "layout_port", "layout_port", "layout_port", "layout_port"}, new int[]{50, 51, 52, 53, 54}, new int[]{C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port});
        includedLayouts.setIncludes(46, new String[]{"layout_port", "layout_port", "layout_port", "layout_port", "layout_port"}, new int[]{55, 56, 57, 58, 59}, new int[]{C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port, C2343R.layout.layout_port});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2343R.C2346id.cl_content, 60);
        sparseIntArray.put(C2343R.C2346id.ll_device_type, 61);
        sparseIntArray.put(C2343R.C2346id.rl_time, 62);
        sparseIntArray.put(C2343R.C2346id.pb_loading, 63);
        sparseIntArray.put(C2343R.C2346id.tv_power, 64);
    }

    public ItemMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 65, sIncludes, sViewsWithIds));
    }

    private ItemMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 65, objArr[60], objArr[9], objArr[7], objArr[20], objArr[16], objArr[4], objArr[24], objArr[8], objArr[30], objArr[31], objArr[2], objArr[61], objArr[63], objArr[50], objArr[55], objArr[51], objArr[56], objArr[52], objArr[57], objArr[53], objArr[58], objArr[54], objArr[59], objArr[49], objArr[33], objArr[62], objArr[1], objArr[5], objArr[18], objArr[36], objArr[19], objArr[64], objArr[15], objArr[13], objArr[14], objArr[22], objArr[23], objArr[29], objArr[39], objArr[25]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.mDirtyFlags_2 = -1;
        this.deviceType.setTag((Object) null);
        this.ivBlueIcon.setTag((Object) null);
        this.ivHumStatus.setTag((Object) null);
        this.ivTempStatus.setTag((Object) null);
        this.ivTypeIcon.setTag((Object) null);
        this.ivVpdstatus.setTag((Object) null);
        this.ivWifiIcon.setTag((Object) null);
        this.ivWindSpeed.setTag((Object) null);
        this.ivWindStatus.setTag((Object) null);
        this.llContent.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        LinearLayout linearLayout = objArr[10];
        this.mboundView10 = linearLayout;
        linearLayout.setTag((Object) null);
        LinearLayout linearLayout2 = objArr[11];
        this.mboundView11 = linearLayout2;
        linearLayout2.setTag((Object) null);
        ImageView imageView = objArr[12];
        this.mboundView12 = imageView;
        imageView.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[17];
        this.mboundView17 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[21];
        this.mboundView21 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        Space space = objArr[26];
        this.mboundView26 = space;
        space.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[27];
        this.mboundView27 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        TextView textView = objArr[28];
        this.mboundView28 = textView;
        textView.setTag((Object) null);
        LinearLayout linearLayout3 = objArr[3];
        this.mboundView3 = linearLayout3;
        linearLayout3.setTag((Object) null);
        Space space2 = objArr[32];
        this.mboundView32 = space2;
        space2.setTag((Object) null);
        TextView textView2 = objArr[34];
        this.mboundView34 = textView2;
        textView2.setTag((Object) null);
        LinearLayout linearLayout4 = objArr[35];
        this.mboundView35 = linearLayout4;
        linearLayout4.setTag((Object) null);
        TextView textView3 = objArr[37];
        this.mboundView37 = textView3;
        textView3.setTag((Object) null);
        ImageView imageView2 = objArr[38];
        this.mboundView38 = imageView2;
        imageView2.setTag((Object) null);
        Space space3 = objArr[40];
        this.mboundView40 = space3;
        space3.setTag((Object) null);
        View view2 = objArr[41];
        this.mboundView41 = view2;
        view2.setTag((Object) null);
        ExpandableLayout expandableLayout = objArr[42];
        this.mboundView42 = expandableLayout;
        expandableLayout.setTag((Object) null);
        LinearLayout linearLayout5 = objArr[43];
        this.mboundView43 = linearLayout5;
        linearLayout5.setTag((Object) null);
        LinearLayout linearLayout6 = objArr[44];
        this.mboundView44 = linearLayout6;
        linearLayout6.setTag((Object) null);
        LinearLayout linearLayout7 = objArr[45];
        this.mboundView45 = linearLayout7;
        linearLayout7.setTag((Object) null);
        LinearLayout linearLayout8 = objArr[46];
        this.mboundView46 = linearLayout8;
        linearLayout8.setTag((Object) null);
        View view3 = objArr[47];
        this.mboundView47 = view3;
        view3.setTag((Object) null);
        ImageView imageView3 = objArr[48];
        this.mboundView48 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = objArr[6];
        this.mboundView6 = imageView4;
        imageView4.setTag((Object) null);
        setContainedBinding(this.rlPort0);
        setContainedBinding(this.rlPort1);
        setContainedBinding(this.rlPort2);
        setContainedBinding(this.rlPort3);
        setContainedBinding(this.rlPort4);
        setContainedBinding(this.rlPort5);
        setContainedBinding(this.rlPort6);
        setContainedBinding(this.rlPort7);
        setContainedBinding(this.rlPort8);
        setContainedBinding(this.rlPort9);
        setContainedBinding(this.rlPortAll);
        this.rlPower.setTag((Object) null);
        this.tvDel.setTag((Object) null);
        this.tvDeviceName.setTag((Object) null);
        this.tvHum.setTag((Object) null);
        this.tvHum2.setTag((Object) null);
        this.tvHumUnit.setTag((Object) null);
        this.tvTempUnit.setTag((Object) null);
        this.tvTime.setTag((Object) null);
        this.tvTmp.setTag((Object) null);
        this.tvVpd.setTag((Object) null);
        this.tvVpdUnit.setTag((Object) null);
        this.tvWindSpeed.setTag((Object) null);
        this.vrsHum.setTag((Object) null);
        this.vrsTmp.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 0;
            this.mDirtyFlags_1 = 4;
            this.mDirtyFlags_2 = 0;
        }
        this.rlPortAll.invalidateAll();
        this.rlPort0.invalidateAll();
        this.rlPort2.invalidateAll();
        this.rlPort4.invalidateAll();
        this.rlPort6.invalidateAll();
        this.rlPort8.invalidateAll();
        this.rlPort1.invalidateAll();
        this.rlPort3.invalidateAll();
        this.rlPort5.invalidateAll();
        this.rlPort7.invalidateAll();
        this.rlPort9.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r6.rlPortAll.hasPendingBindings() == false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        if (r6.rlPort0.hasPendingBindings() == false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r6.rlPort2.hasPendingBindings() == false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        if (r6.rlPort4.hasPendingBindings() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r6.rlPort6.hasPendingBindings() == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004b, code lost:
        if (r6.rlPort8.hasPendingBindings() == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        if (r6.rlPort1.hasPendingBindings() == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0056, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005d, code lost:
        if (r6.rlPort3.hasPendingBindings() == false) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0066, code lost:
        if (r6.rlPort5.hasPendingBindings() == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006f, code lost:
        if (r6.rlPort7.hasPendingBindings() == false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0071, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0078, code lost:
        if (r6.rlPort9.hasPendingBindings() == false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x007f }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x007d
            long r0 = r6.mDirtyFlags_1     // Catch:{ all -> 0x007f }
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x007d
            long r0 = r6.mDirtyFlags_2     // Catch:{ all -> 0x007f }
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0017
            goto L_0x007d
        L_0x0017:
            monitor-exit(r6)     // Catch:{ all -> 0x007f }
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPortAll
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0021
            return r4
        L_0x0021:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort0
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x002a
            return r4
        L_0x002a:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort2
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0033
            return r4
        L_0x0033:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort4
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003c
            return r4
        L_0x003c:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort6
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0045
            return r4
        L_0x0045:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort8
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x004e
            return r4
        L_0x004e:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort1
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0057
            return r4
        L_0x0057:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort3
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0060
            return r4
        L_0x0060:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort5
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0069
            return r4
        L_0x0069:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort7
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0072
            return r4
        L_0x0072:
            com.eternal.main.databinding.LayoutPortBinding r0 = r6.rlPort9
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x007b
            return r4
        L_0x007b:
            r0 = 0
            return r0
        L_0x007d:
            monitor-exit(r6)     // Catch:{ all -> 0x007f }
            return r4
        L_0x007f:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x007f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.databinding.ItemMainBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int i, Object obj) {
        if (C2323BR.item != i) {
            return false;
        }
        setItem((ItemModel) obj);
        return true;
    }

    public void setItem(ItemModel itemModel) {
        this.mItem = itemModel;
        synchronized (this) {
            this.mDirtyFlags_1 |= 2;
        }
        notifyPropertyChanged(C2323BR.item);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.rlPortAll.setLifecycleOwner(lifecycleOwner);
        this.rlPort0.setLifecycleOwner(lifecycleOwner);
        this.rlPort2.setLifecycleOwner(lifecycleOwner);
        this.rlPort4.setLifecycleOwner(lifecycleOwner);
        this.rlPort6.setLifecycleOwner(lifecycleOwner);
        this.rlPort8.setLifecycleOwner(lifecycleOwner);
        this.rlPort1.setLifecycleOwner(lifecycleOwner);
        this.rlPort3.setLifecycleOwner(lifecycleOwner);
        this.rlPort5.setLifecycleOwner(lifecycleOwner);
        this.rlPort7.setLifecycleOwner(lifecycleOwner);
        this.rlPort9.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeItemFanState((ObservableInt) obj, i2);
            case 1:
                return onChangeItemTmpUnitColor((ObservableInt) obj, i2);
            case 2:
                return onChangeItemFanTypeTitle((ObservableField) obj, i2);
            case 3:
                return onChangeItemHumColor((ObservableInt) obj, i2);
            case 4:
                return onChangeRlPort0((LayoutPortBinding) obj, i2);
            case 5:
                return onChangeItemTmp((ObservableInt) obj, i2);
            case 6:
                return onChangeRlPort1((LayoutPortBinding) obj, i2);
            case 7:
                return onChangeItemPowerVisibility((ObservableBoolean) obj, i2);
            case 8:
                return onChangeItemTime((ObservableField) obj, i2);
            case 9:
                return onChangeItemTmpFlag((ObservableField) obj, i2);
            case 10:
                return onChangeItemHumClose((ObservableBoolean) obj, i2);
            case 11:
                return onChangeItemOpen((ObservableBoolean) obj, i2);
            case 12:
                return onChangeItemVpdState((ObservableInt) obj, i2);
            case 13:
                return onChangeItemShareIcon((ObservableInt) obj, i2);
            case 14:
                return onChangeItemHum((ObservableInt) obj, i2);
            case 15:
                return onChangeItemTmpState((ObservableInt) obj, i2);
            case 16:
                return onChangeItemTimeIcon((ObservableInt) obj, i2);
            case 17:
                return onChangeItemTmpClose((ObservableBoolean) obj, i2);
            case 18:
                return onChangeRlPort4((LayoutPortBinding) obj, i2);
            case 19:
                return onChangeItemFanSize((ObservableField) obj, i2);
            case 20:
                return onChangeRlPortAll((LayoutPortBinding) obj, i2);
            case 21:
                return onChangeItemVpdSize((ObservableField) obj, i2);
            case 22:
                return onChangeItemVpdVisibility((ObservableBoolean) obj, i2);
            case 23:
                return onChangeRlPort5((LayoutPortBinding) obj, i2);
            case 24:
                return onChangeItemIsShare((MutableLiveData) obj, i2);
            case 25:
                return onChangeItemIsConnectWiFi((ObservableBoolean) obj, i2);
            case 26:
                return onChangeItemTmpHighClose((ObservableBoolean) obj, i2);
            case 27:
                return onChangeItemPerState((ObservableInt) obj, i2);
            case 28:
                return onChangeItemFanVisibility((ObservableBoolean) obj, i2);
            case 29:
                return onChangeItemMinTmp((ObservableInt) obj, i2);
            case 30:
                return onChangeRlPort2((LayoutPortBinding) obj, i2);
            case 31:
                return onChangeItemTmpColor((ObservableInt) obj, i2);
            case 32:
                return onChangeRlPort3((LayoutPortBinding) obj, i2);
            case 33:
                return onChangeItemPerSize((ObservableField) obj, i2);
            case 34:
                return onChangeItemDistanceHum((ObservableInt) obj, i2);
            case 35:
                return onChangeItemShowLoading((ObservableBoolean) obj, i2);
            case 36:
                return onChangeRlPort8((LayoutPortBinding) obj, i2);
            case 37:
                return onChangeItemLowHum((ObservableInt) obj, i2);
            case 38:
                return onChangeItemFanType((ObservableInt) obj, i2);
            case 39:
                return onChangeItemIsConnet((ObservableBoolean) obj, i2);
            case 40:
                return onChangeItemHighHum((ObservableInt) obj, i2);
            case 41:
                return onChangeRlPort9((LayoutPortBinding) obj, i2);
            case 42:
                return onChangeItemMasterVisibility((ObservableBoolean) obj, i2);
            case 43:
                return onChangeItemAllPortVisible((ObservableBoolean) obj, i2);
            case 44:
                return onChangeItemHighTmp((ObservableInt) obj, i2);
            case 45:
                return onChangeRlPort6((LayoutPortBinding) obj, i2);
            case 46:
                return onChangeItemHumLowClose((ObservableBoolean) obj, i2);
            case 47:
                return onChangeItemHumUnit((ObservableField) obj, i2);
            case 48:
                return onChangeRlPort7((LayoutPortBinding) obj, i2);
            case 49:
                return onChangeItemRangeVisibility((ObservableBoolean) obj, i2);
            case 50:
                return onChangeItemHumVisibility((ObservableBoolean) obj, i2);
            case 51:
                return onChangeItemWifiIconVisible((ObservableBoolean) obj, i2);
            case 52:
                return onChangeItemTmpUnit((ObservableField) obj, i2);
            case 53:
                return onChangeItemMinHum((ObservableInt) obj, i2);
            case 54:
                return onChangeItemHumHighClose((ObservableBoolean) obj, i2);
            case 55:
                return onChangeItemLowTmp((ObservableInt) obj, i2);
            case 56:
                return onChangeItemPowerOff((ObservableBoolean) obj, i2);
            case 57:
                return onChangeItemTmpSize((ObservableField) obj, i2);
            case 58:
                return onChangeItemTypeIcon((MutableLiveData) obj, i2);
            case 59:
                return onChangeItemBlueIconVisible((ObservableBoolean) obj, i2);
            case 60:
                return onChangeItemDistanceTmp((ObservableInt) obj, i2);
            case 61:
                return onChangeItemDevice((ObservableField) obj, i2);
            case 62:
                return onChangeItemHumUnitColor((ObservableInt) obj, i2);
            case 63:
                return onChangeItemName((ObservableField) obj, i2);
            case 64:
                return onChangeItemTmpLowClose((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeItemFanState(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemTmpUnitColor(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemFanTypeTitle(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeItemHumColor(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeRlPort0(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeItemTmp(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeRlPort1(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeItemPowerVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeItemTime(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeItemTmpFlag(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeItemHumClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeItemOpen(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeItemVpdState(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeItemShareIcon(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeItemHum(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeItemTmpState(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeItemTimeIcon(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeItemTmpClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeRlPort4(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeItemFanSize(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeRlPortAll(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeItemVpdSize(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeItemVpdVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeRlPort5(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeItemIsShare(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeItemIsConnectWiFi(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeItemTmpHighClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeItemPerState(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeItemFanVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeItemMinTmp(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeRlPort2(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeItemTmpColor(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeRlPort3(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    private boolean onChangeItemPerSize(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        return true;
    }

    private boolean onChangeItemDistanceHum(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17179869184L;
        }
        return true;
    }

    private boolean onChangeItemShowLoading(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 34359738368L;
        }
        return true;
    }

    private boolean onChangeRlPort8(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 68719476736L;
        }
        return true;
    }

    private boolean onChangeItemLowHum(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 137438953472L;
        }
        return true;
    }

    private boolean onChangeItemFanType(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 274877906944L;
        }
        return true;
    }

    private boolean onChangeItemIsConnet(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 549755813888L;
        }
        return true;
    }

    private boolean onChangeItemHighHum(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1099511627776L;
        }
        return true;
    }

    private boolean onChangeRlPort9(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2199023255552L;
        }
        return true;
    }

    private boolean onChangeItemMasterVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4398046511104L;
        }
        return true;
    }

    private boolean onChangeItemAllPortVisible(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8796093022208L;
        }
        return true;
    }

    private boolean onChangeItemHighTmp(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17592186044416L;
        }
        return true;
    }

    private boolean onChangeRlPort6(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 35184372088832L;
        }
        return true;
    }

    private boolean onChangeItemHumLowClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 70368744177664L;
        }
        return true;
    }

    private boolean onChangeItemHumUnit(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 140737488355328L;
        }
        return true;
    }

    private boolean onChangeRlPort7(LayoutPortBinding layoutPortBinding, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 281474976710656L;
        }
        return true;
    }

    private boolean onChangeItemRangeVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 562949953421312L;
        }
        return true;
    }

    private boolean onChangeItemHumVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1125899906842624L;
        }
        return true;
    }

    private boolean onChangeItemWifiIconVisible(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2251799813685248L;
        }
        return true;
    }

    private boolean onChangeItemTmpUnit(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4503599627370496L;
        }
        return true;
    }

    private boolean onChangeItemMinHum(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 9007199254740992L;
        }
        return true;
    }

    private boolean onChangeItemHumHighClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 18014398509481984L;
        }
        return true;
    }

    private boolean onChangeItemLowTmp(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 36028797018963968L;
        }
        return true;
    }

    private boolean onChangeItemPowerOff(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 72057594037927936L;
        }
        return true;
    }

    private boolean onChangeItemTmpSize(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 144115188075855872L;
        }
        return true;
    }

    private boolean onChangeItemTypeIcon(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 288230376151711744L;
        }
        return true;
    }

    private boolean onChangeItemBlueIconVisible(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 576460752303423488L;
        }
        return true;
    }

    private boolean onChangeItemDistanceTmp(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= LockFreeTaskQueueCore.FROZEN_MASK;
        }
        return true;
    }

    private boolean onChangeItemDevice(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= LockFreeTaskQueueCore.CLOSED_MASK;
        }
        return true;
    }

    private boolean onChangeItemHumUnitColor(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= Longs.MAX_POWER_OF_TWO;
        }
        return true;
    }

    private boolean onChangeItemName(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= Long.MIN_VALUE;
        }
        return true;
    }

    private boolean onChangeItemTmpLowClose(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags_1 |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:1082:0x1239  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:1104:0x131e  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02f0  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x034a  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0376  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0379  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03a2  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03a5  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03d1  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x03ff  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0427  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x042d  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x046f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x04bc  */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x04bf  */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x04c7  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x04dc  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x04e2  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x0526  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x0529  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0531  */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x054b  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x054e  */
    /* JADX WARNING: Removed duplicated region for block: B:335:0x0579  */
    /* JADX WARNING: Removed duplicated region for block: B:336:0x057c  */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x05a9  */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x05ac  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x011e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:359:0x05d7  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x05de  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0636  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x0639  */
    /* JADX WARNING: Removed duplicated region for block: B:397:0x0664  */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0667  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0179 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:409:0x0692  */
    /* JADX WARNING: Removed duplicated region for block: B:411:0x06a5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x08ab  */
    /* JADX WARNING: Removed duplicated region for block: B:506:0x08b0  */
    /* JADX WARNING: Removed duplicated region for block: B:518:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:519:0x08e4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01a3 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:545:0x0948  */
    /* JADX WARNING: Removed duplicated region for block: B:546:0x094f  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:571:0x099e  */
    /* JADX WARNING: Removed duplicated region for block: B:572:0x09a1  */
    /* JADX WARNING: Removed duplicated region for block: B:583:0x09cc  */
    /* JADX WARNING: Removed duplicated region for block: B:584:0x09cf  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:595:0x09f9  */
    /* JADX WARNING: Removed duplicated region for block: B:597:0x09ff  */
    /* JADX WARNING: Removed duplicated region for block: B:618:0x0a43  */
    /* JADX WARNING: Removed duplicated region for block: B:620:0x0a47  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01c7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:639:0x0a85  */
    /* JADX WARNING: Removed duplicated region for block: B:641:0x0a89  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:662:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:663:0x0acd  */
    /* JADX WARNING: Removed duplicated region for block: B:666:0x0ad5  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:672:0x0aee  */
    /* JADX WARNING: Removed duplicated region for block: B:673:0x0af1  */
    /* JADX WARNING: Removed duplicated region for block: B:684:0x0b19  */
    /* JADX WARNING: Removed duplicated region for block: B:685:0x0b1c  */
    /* JADX WARNING: Removed duplicated region for block: B:696:0x0b44  */
    /* JADX WARNING: Removed duplicated region for block: B:697:0x0b4a  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:721:0x0ba3  */
    /* JADX WARNING: Removed duplicated region for block: B:722:0x0ba6  */
    /* JADX WARNING: Removed duplicated region for block: B:725:0x0bae  */
    /* JADX WARNING: Removed duplicated region for block: B:731:0x0bc7  */
    /* JADX WARNING: Removed duplicated region for block: B:732:0x0bcb  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01eb A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:744:0x0bf9  */
    /* JADX WARNING: Removed duplicated region for block: B:746:0x0c00  */
    /* JADX WARNING: Removed duplicated region for block: B:768:0x0c42  */
    /* JADX WARNING: Removed duplicated region for block: B:769:0x0c45  */
    /* JADX WARNING: Removed duplicated region for block: B:772:0x0c4d  */
    /* JADX WARNING: Removed duplicated region for block: B:779:0x0c65  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:780:0x0c68  */
    /* JADX WARNING: Removed duplicated region for block: B:783:0x0c70  */
    /* JADX WARNING: Removed duplicated region for block: B:789:0x0c87  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:790:0x0c8a  */
    /* JADX WARNING: Removed duplicated region for block: B:801:0x0cb2  */
    /* JADX WARNING: Removed duplicated region for block: B:802:0x0cb6  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:823:0x0e25  */
    /* JADX WARNING: Removed duplicated region for block: B:829:0x0e3b  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:833:0x0e49  */
    /* JADX WARNING: Removed duplicated region for block: B:843:0x0e6b  */
    /* JADX WARNING: Removed duplicated region for block: B:847:0x0e79  */
    /* JADX WARNING: Removed duplicated region for block: B:853:0x0e8f  */
    /* JADX WARNING: Removed duplicated region for block: B:857:0x0e9a  */
    /* JADX WARNING: Removed duplicated region for block: B:858:0x0e9d  */
    /* JADX WARNING: Removed duplicated region for block: B:861:0x0ea4  */
    /* JADX WARNING: Removed duplicated region for block: B:862:0x0ea7  */
    /* JADX WARNING: Removed duplicated region for block: B:865:0x0eb6  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:871:0x0ecc  */
    /* JADX WARNING: Removed duplicated region for block: B:875:0x0ed7  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:881:0x0eed  */
    /* JADX WARNING: Removed duplicated region for block: B:885:0x0ef8  */
    /* JADX WARNING: Removed duplicated region for block: B:896:0x0f1f  */
    /* JADX WARNING: Removed duplicated region for block: B:897:0x0f24  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:917:0x0f70  */
    /* JADX WARNING: Removed duplicated region for block: B:918:0x0f72  */
    /* JADX WARNING: Removed duplicated region for block: B:924:0x0f88  */
    /* JADX WARNING: Removed duplicated region for block: B:925:0x0f8a  */
    /* JADX WARNING: Removed duplicated region for block: B:931:0x0fa0  */
    /* JADX WARNING: Removed duplicated region for block: B:932:0x0fa4  */
    /* JADX WARNING: Removed duplicated region for block: B:938:0x0fb6  */
    /* JADX WARNING: Removed duplicated region for block: B:939:0x0fba  */
    /* JADX WARNING: Removed duplicated region for block: B:946:0x0fd5  */
    /* JADX WARNING: Removed duplicated region for block: B:947:0x0fd8  */
    /* JADX WARNING: Removed duplicated region for block: B:954:0x0ff1  */
    /* JADX WARNING: Removed duplicated region for block: B:955:0x0ff4  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:962:0x100d  */
    /* JADX WARNING: Removed duplicated region for block: B:963:0x1015  */
    /* JADX WARNING: Removed duplicated region for block: B:968:0x1027  */
    /* JADX WARNING: Removed duplicated region for block: B:969:0x102c  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:972:0x1036  */
    /* JADX WARNING: Removed duplicated region for block: B:973:0x1039  */
    /* JADX WARNING: Removed duplicated region for block: B:976:0x1041  */
    /* JADX WARNING: Removed duplicated region for block: B:977:0x1044  */
    /* JADX WARNING: Removed duplicated region for block: B:980:0x1051  */
    /* JADX WARNING: Removed duplicated region for block: B:981:0x1054  */
    /* JADX WARNING: Removed duplicated region for block: B:985:0x105f  */
    /* JADX WARNING: Removed duplicated region for block: B:986:0x1066  */
    /* JADX WARNING: Removed duplicated region for block: B:995:0x1089  */
    /* JADX WARNING: Removed duplicated region for block: B:996:0x108c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r169 = this;
            r1 = r169
            monitor-enter(r169)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x1568 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x1568 }
            long r6 = r1.mDirtyFlags_1     // Catch:{ all -> 0x1568 }
            r1.mDirtyFlags_1 = r4     // Catch:{ all -> 0x1568 }
            long r8 = r1.mDirtyFlags_2     // Catch:{ all -> 0x1568 }
            r1.mDirtyFlags_2 = r4     // Catch:{ all -> 0x1568 }
            monitor-exit(r169)     // Catch:{ all -> 0x1568 }
            com.eternal.main.model.ItemModel r0 = r1.mItem
            r10 = -318932469940305(0xfffeddeebf6bffaf, double:NaN)
            long r10 = r10 & r2
            r16 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
            r18 = 512(0x200, double:2.53E-321)
            r20 = 256(0x100, double:1.265E-321)
            r22 = 128(0x80, double:6.32E-322)
            r24 = 16777216(0x1000000, double:8.289046E-317)
            r26 = 32
            r28 = 268435456(0x10000000, double:1.32624737E-315)
            r30 = 8
            r32 = 4194304(0x400000, double:2.0722615E-317)
            r34 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            r36 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            r38 = 4611686018427387904(0x4000000000000000, double:2.0)
            r40 = 1
            r42 = 7
            r14 = 1
            r46 = 6
            r15 = 0
            int r49 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r49 != 0) goto L_0x00dc
            long r10 = r6 & r42
            int r49 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r49 == 0) goto L_0x004f
            goto L_0x00dc
        L_0x004f:
            r58 = r6
            r60 = r8
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r51 = 0
            r56 = 0
            r57 = 0
            r62 = 0
            r63 = 0
            r64 = 0
            r65 = 0
            r66 = 0
            r67 = 0
            r68 = 0
            r69 = 0
            r70 = 0
            r71 = 0
            r72 = 0
            r73 = 0
            r74 = 0
            r75 = 0
            r76 = 0
            r77 = 0
            r101 = 0
            r123 = 0
            r124 = 0
            r125 = 0
            r126 = 0
            r127 = 0
            r128 = 0
            r129 = 0
            r130 = 0
            r131 = 0
            r132 = 0
            r133 = 0
            r134 = 0
            r135 = 0
            r136 = 0
            r137 = 0
            r138 = 0
            r139 = 0
            r140 = 0
            r141 = 0
            r142 = 0
            r143 = 0
            r144 = 0
            r145 = 0
            r146 = 0
            r147 = 0
            r148 = 0
            r149 = 0
            r150 = 0
            r151 = 0
            r152 = 0
            r153 = 0
            r154 = 0
            r155 = 0
            r156 = 0
            r157 = 0
            r158 = 0
            r159 = 0
            r160 = 0
            r161 = 0
            r162 = 0
            r163 = 0
            r164 = 0
            goto L_0x0df3
        L_0x00dc:
            long r10 = r2 & r40
            int r49 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r49 != 0) goto L_0x00e8
            long r10 = r6 & r46
            int r49 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r49 == 0) goto L_0x00f8
        L_0x00e8:
            if (r0 == 0) goto L_0x00ed
            androidx.databinding.ObservableInt r10 = r0.fanState
            goto L_0x00ee
        L_0x00ed:
            r10 = 0
        L_0x00ee:
            r1.updateRegistration((int) r15, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x00f8
            int r10 = r10.get()
            goto L_0x00f9
        L_0x00f8:
            r10 = 0
        L_0x00f9:
            r49 = 2
            long r49 = r2 & r49
            int r11 = (r49 > r4 ? 1 : (r49 == r4 ? 0 : -1))
            if (r11 != 0) goto L_0x0107
            long r49 = r6 & r46
            int r11 = (r49 > r4 ? 1 : (r49 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0117
        L_0x0107:
            if (r0 == 0) goto L_0x010c
            androidx.databinding.ObservableInt r11 = r0.tmpUnitColor
            goto L_0x010d
        L_0x010c:
            r11 = 0
        L_0x010d:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0117
            int r11 = r11.get()
            goto L_0x0118
        L_0x0117:
            r11 = 0
        L_0x0118:
            long r49 = r6 & r46
            int r51 = (r49 > r4 ? 1 : (r49 == r4 ? 0 : -1))
            if (r51 == 0) goto L_0x0159
            if (r0 == 0) goto L_0x0159
            com.eternal.main.model.PortModel r14 = r0.portModel1
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onExpand
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onDetail
            com.eternal.main.model.PortModel r13 = r0.portModel9
            com.eternal.main.model.PortModel r4 = r0.portModel2
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r0.onDelete
            r56 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel3
            r57 = r4
            com.eternal.main.model.PortModel r4 = r0.portModelAll
            r58 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel4
            r59 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel5
            r60 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel6
            r61 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel7
            r62 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel0
            r63 = r4
            com.eternal.main.model.PortModel r4 = r0.portModel8
            r168 = r5
            r5 = r4
            r4 = r62
            r62 = r15
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r168
            goto L_0x016f
        L_0x0159:
            r4 = 0
            r5 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
        L_0x016f:
            r64 = 4
            long r64 = r2 & r64
            r54 = 0
            int r66 = (r64 > r54 ? 1 : (r64 == r54 ? 0 : -1))
            if (r66 != 0) goto L_0x0181
            if (r51 == 0) goto L_0x017c
            goto L_0x0181
        L_0x017c:
            r64 = r4
            r65 = r5
            goto L_0x019a
        L_0x0181:
            r64 = r4
            if (r0 == 0) goto L_0x018a
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.fanTypeTitle
            r65 = r5
            goto L_0x018d
        L_0x018a:
            r65 = r5
            r4 = 0
        L_0x018d:
            r5 = 2
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x019a
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x019a:
            r4 = 0
        L_0x019b:
            long r66 = r2 & r30
            r54 = 0
            int r5 = (r66 > r54 ? 1 : (r66 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x01a9
            if (r51 == 0) goto L_0x01a6
            goto L_0x01a9
        L_0x01a6:
            r66 = r4
            goto L_0x01be
        L_0x01a9:
            if (r0 == 0) goto L_0x01b0
            androidx.databinding.ObservableInt r5 = r0.humColor
            r66 = r4
            goto L_0x01b3
        L_0x01b0:
            r66 = r4
            r5 = 0
        L_0x01b3:
            r4 = 3
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01be
            int r4 = r5.get()
            goto L_0x01bf
        L_0x01be:
            r4 = 0
        L_0x01bf:
            long r67 = r2 & r26
            r54 = 0
            int r5 = (r67 > r54 ? 1 : (r67 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x01cd
            if (r51 == 0) goto L_0x01ca
            goto L_0x01cd
        L_0x01ca:
            r67 = r4
            goto L_0x01e2
        L_0x01cd:
            if (r0 == 0) goto L_0x01d4
            androidx.databinding.ObservableInt r5 = r0.tmp
            r67 = r4
            goto L_0x01d7
        L_0x01d4:
            r67 = r4
            r5 = 0
        L_0x01d7:
            r4 = 5
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01e2
            int r4 = r5.get()
            goto L_0x01e3
        L_0x01e2:
            r4 = 0
        L_0x01e3:
            long r68 = r2 & r22
            r54 = 0
            int r70 = (r68 > r54 ? 1 : (r68 == r54 ? 0 : -1))
            if (r70 != 0) goto L_0x01f2
            if (r51 == 0) goto L_0x01ee
            goto L_0x01f2
        L_0x01ee:
            r69 = r4
        L_0x01f0:
            r4 = 0
            goto L_0x021c
        L_0x01f2:
            if (r0 == 0) goto L_0x01f9
            androidx.databinding.ObservableBoolean r5 = r0.powerVisibility
            r69 = r4
            goto L_0x01fc
        L_0x01f9:
            r69 = r4
            r5 = 0
        L_0x01fc:
            r4 = 7
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0207
            boolean r4 = r5.get()
            goto L_0x0208
        L_0x0207:
            r4 = 0
        L_0x0208:
            if (r70 != 0) goto L_0x020c
            if (r51 == 0) goto L_0x0217
        L_0x020c:
            if (r4 == 0) goto L_0x0212
            r70 = 1073741824(0x40000000, double:5.304989477E-315)
            goto L_0x0215
        L_0x0212:
            r70 = 536870912(0x20000000, double:2.652494739E-315)
        L_0x0215:
            long r6 = r6 | r70
        L_0x0217:
            if (r4 == 0) goto L_0x021a
            goto L_0x01f0
        L_0x021a:
            r4 = 8
        L_0x021c:
            long r70 = r2 & r20
            r54 = 0
            int r5 = (r70 > r54 ? 1 : (r70 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0230
            long r70 = r6 & r46
            int r5 = (r70 > r54 ? 1 : (r70 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x022b
            goto L_0x0230
        L_0x022b:
            r51 = r4
            r4 = 8
            goto L_0x024a
        L_0x0230:
            if (r0 == 0) goto L_0x0239
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.time
            r51 = r4
            r4 = 8
            goto L_0x023e
        L_0x0239:
            r51 = r4
            r4 = 8
            r5 = 0
        L_0x023e:
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x024a
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x024b
        L_0x024a:
            r5 = 0
        L_0x024b:
            long r70 = r2 & r18
            r54 = 0
            int r68 = (r70 > r54 ? 1 : (r70 == r54 ? 0 : -1))
            if (r68 != 0) goto L_0x025d
            long r70 = r6 & r46
            int r68 = (r70 > r54 ? 1 : (r70 == r54 ? 0 : -1))
            if (r68 == 0) goto L_0x025a
            goto L_0x025d
        L_0x025a:
            r70 = r5
            goto L_0x0275
        L_0x025d:
            if (r0 == 0) goto L_0x0264
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.tmpFlag
            r70 = r5
            goto L_0x0267
        L_0x0264:
            r70 = r5
            r4 = 0
        L_0x0267:
            r5 = 9
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0275
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0276
        L_0x0275:
            r4 = 0
        L_0x0276:
            r71 = 2048(0x800, double:1.0118E-320)
            long r71 = r2 & r71
            r54 = 0
            int r5 = (r71 > r54 ? 1 : (r71 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0291
            long r71 = r6 & r46
            int r73 = (r71 > r54 ? 1 : (r71 == r54 ? 0 : -1))
            if (r73 == 0) goto L_0x0287
            goto L_0x0291
        L_0x0287:
            r71 = r4
            r72 = r10
            r4 = 0
            r5 = 0
            r10 = 0
            r73 = 0
            goto L_0x02df
        L_0x0291:
            r71 = r4
            if (r0 == 0) goto L_0x029a
            androidx.databinding.ObservableBoolean r4 = r0.open
            r72 = r10
            goto L_0x029d
        L_0x029a:
            r72 = r10
            r4 = 0
        L_0x029d:
            r10 = 11
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x02a9
            boolean r4 = r4.get()
            goto L_0x02aa
        L_0x02a9:
            r4 = 0
        L_0x02aa:
            if (r5 != 0) goto L_0x02b4
            long r73 = r6 & r46
            r54 = 0
            int r5 = (r73 > r54 ? 1 : (r73 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x02cb
        L_0x02b4:
            if (r4 == 0) goto L_0x02c1
            r73 = 1024(0x400, double:5.06E-321)
            long r5 = r6 | r73
            r73 = 4096(0x1000, double:2.0237E-320)
            long r5 = r5 | r73
            r73 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            goto L_0x02c9
        L_0x02c1:
            long r5 = r6 | r18
            r73 = 2048(0x800, double:1.0118E-320)
            long r5 = r5 | r73
            r73 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
        L_0x02c9:
            long r6 = r5 | r73
        L_0x02cb:
            if (r4 == 0) goto L_0x02d0
            r5 = 8
            goto L_0x02d1
        L_0x02d0:
            r5 = 0
        L_0x02d1:
            if (r4 == 0) goto L_0x02d5
            r10 = 0
            goto L_0x02d7
        L_0x02d5:
            r10 = 180(0xb4, float:2.52E-43)
        L_0x02d7:
            r73 = r4 ^ 1
            if (r4 == 0) goto L_0x02dd
            r4 = 0
            goto L_0x02df
        L_0x02dd:
            r4 = 8
        L_0x02df:
            r74 = 4096(0x1000, double:2.0237E-320)
            long r74 = r2 & r74
            r54 = 0
            int r76 = (r74 > r54 ? 1 : (r74 == r54 ? 0 : -1))
            if (r76 != 0) goto L_0x02f5
            long r74 = r6 & r46
            int r76 = (r74 > r54 ? 1 : (r74 == r54 ? 0 : -1))
            if (r76 == 0) goto L_0x02f0
            goto L_0x02f5
        L_0x02f0:
            r74 = r4
            r75 = r5
            goto L_0x030d
        L_0x02f5:
            r74 = r4
            if (r0 == 0) goto L_0x02fe
            androidx.databinding.ObservableInt r4 = r0.vpdState
            r75 = r5
            goto L_0x0301
        L_0x02fe:
            r75 = r5
            r4 = 0
        L_0x0301:
            r5 = 12
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x030d
            int r4 = r4.get()
            goto L_0x030e
        L_0x030d:
            r4 = 0
        L_0x030e:
            r76 = 8192(0x2000, double:4.0474E-320)
            long r76 = r2 & r76
            r54 = 0
            int r5 = (r76 > r54 ? 1 : (r76 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0322
            long r76 = r6 & r46
            int r5 = (r76 > r54 ? 1 : (r76 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x031f
            goto L_0x0322
        L_0x031f:
            r76 = r4
            goto L_0x0338
        L_0x0322:
            if (r0 == 0) goto L_0x0329
            androidx.databinding.ObservableInt r5 = r0.shareIcon
            r76 = r4
            goto L_0x032c
        L_0x0329:
            r76 = r4
            r5 = 0
        L_0x032c:
            r4 = 13
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0338
            int r4 = r5.get()
            goto L_0x0339
        L_0x0338:
            r4 = 0
        L_0x0339:
            r77 = 16384(0x4000, double:8.0948E-320)
            long r77 = r2 & r77
            r54 = 0
            int r5 = (r77 > r54 ? 1 : (r77 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x034d
            long r77 = r6 & r46
            int r5 = (r77 > r54 ? 1 : (r77 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x034a
            goto L_0x034d
        L_0x034a:
            r77 = r4
            goto L_0x0363
        L_0x034d:
            if (r0 == 0) goto L_0x0354
            androidx.databinding.ObservableInt r5 = r0.hum
            r77 = r4
            goto L_0x0357
        L_0x0354:
            r77 = r4
            r5 = 0
        L_0x0357:
            r4 = 14
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0363
            int r4 = r5.get()
            goto L_0x0364
        L_0x0363:
            r4 = 0
        L_0x0364:
            r78 = 32768(0x8000, double:1.61895E-319)
            long r78 = r2 & r78
            r54 = 0
            int r5 = (r78 > r54 ? 1 : (r78 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0379
            long r78 = r6 & r46
            int r5 = (r78 > r54 ? 1 : (r78 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0376
            goto L_0x0379
        L_0x0376:
            r78 = r4
            goto L_0x038f
        L_0x0379:
            if (r0 == 0) goto L_0x0380
            androidx.databinding.ObservableInt r5 = r0.tmpState
            r78 = r4
            goto L_0x0383
        L_0x0380:
            r78 = r4
            r5 = 0
        L_0x0383:
            r4 = 15
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x038f
            int r4 = r5.get()
            goto L_0x0390
        L_0x038f:
            r4 = 0
        L_0x0390:
            r79 = 65536(0x10000, double:3.2379E-319)
            long r79 = r2 & r79
            r54 = 0
            int r5 = (r79 > r54 ? 1 : (r79 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x03a5
            long r79 = r6 & r46
            int r5 = (r79 > r54 ? 1 : (r79 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x03a2
            goto L_0x03a5
        L_0x03a2:
            r79 = r4
            goto L_0x03bb
        L_0x03a5:
            if (r0 == 0) goto L_0x03ac
            androidx.databinding.ObservableInt r5 = r0.timeIcon
            r79 = r4
            goto L_0x03af
        L_0x03ac:
            r79 = r4
            r5 = 0
        L_0x03af:
            r4 = 16
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x03bb
            int r4 = r5.get()
            goto L_0x03bc
        L_0x03bb:
            r4 = 0
        L_0x03bc:
            r80 = 524288(0x80000, double:2.590327E-318)
            long r80 = r2 & r80
            r54 = 0
            int r5 = (r80 > r54 ? 1 : (r80 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x03d1
            long r80 = r6 & r46
            int r5 = (r80 > r54 ? 1 : (r80 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x03ce
            goto L_0x03d1
        L_0x03ce:
            r80 = r4
            goto L_0x03e9
        L_0x03d1:
            if (r0 == 0) goto L_0x03d8
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.fanSize
            r80 = r4
            goto L_0x03db
        L_0x03d8:
            r80 = r4
            r5 = 0
        L_0x03db:
            r4 = 19
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x03e9
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x03ea
        L_0x03e9:
            r4 = 0
        L_0x03ea:
            r81 = 2097152(0x200000, double:1.0361308E-317)
            long r81 = r2 & r81
            r54 = 0
            int r5 = (r81 > r54 ? 1 : (r81 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x03ff
            long r81 = r6 & r46
            int r5 = (r81 > r54 ? 1 : (r81 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x03fc
            goto L_0x03ff
        L_0x03fc:
            r81 = r4
            goto L_0x0417
        L_0x03ff:
            if (r0 == 0) goto L_0x0406
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.vpdSize
            r81 = r4
            goto L_0x0409
        L_0x0406:
            r81 = r4
            r5 = 0
        L_0x0409:
            r4 = 21
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0417
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0418
        L_0x0417:
            r4 = 0
        L_0x0418:
            long r82 = r2 & r32
            r54 = 0
            int r5 = (r82 > r54 ? 1 : (r82 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x042d
            long r82 = r6 & r46
            int r84 = (r82 > r54 ? 1 : (r82 == r54 ? 0 : -1))
            if (r84 == 0) goto L_0x0427
            goto L_0x042d
        L_0x0427:
            r82 = r4
            r83 = r10
        L_0x042b:
            r4 = 0
            goto L_0x045c
        L_0x042d:
            r82 = r4
            if (r0 == 0) goto L_0x0436
            androidx.databinding.ObservableBoolean r4 = r0.vpdVisibility
            r83 = r10
            goto L_0x0439
        L_0x0436:
            r83 = r10
            r4 = 0
        L_0x0439:
            r10 = 22
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0445
            boolean r4 = r4.get()
            goto L_0x0446
        L_0x0445:
            r4 = 0
        L_0x0446:
            if (r5 != 0) goto L_0x0450
            long r84 = r6 & r46
            r54 = 0
            int r5 = (r84 > r54 ? 1 : (r84 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0457
        L_0x0450:
            if (r4 == 0) goto L_0x0455
            long r6 = r6 | r20
            goto L_0x0457
        L_0x0455:
            long r6 = r6 | r22
        L_0x0457:
            if (r4 == 0) goto L_0x045a
            goto L_0x042b
        L_0x045a:
            r4 = 8
        L_0x045c:
            long r84 = r2 & r24
            r54 = 0
            int r5 = (r84 > r54 ? 1 : (r84 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x046f
            long r84 = r6 & r46
            int r10 = (r84 > r54 ? 1 : (r84 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x046b
            goto L_0x046f
        L_0x046b:
            r84 = r4
        L_0x046d:
            r4 = 0
            goto L_0x04a9
        L_0x046f:
            if (r0 == 0) goto L_0x0476
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.isShare
            r84 = r4
            goto L_0x0479
        L_0x0476:
            r84 = r4
            r10 = 0
        L_0x0479:
            r4 = 24
            r1.updateLiveDataRegistration(r4, r10)
            if (r10 == 0) goto L_0x0487
            java.lang.Object r4 = r10.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0488
        L_0x0487:
            r4 = 0
        L_0x0488:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 != 0) goto L_0x0496
            long r85 = r6 & r46
            r54 = 0
            int r5 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x04a5
        L_0x0496:
            if (r4 == 0) goto L_0x049e
            r85 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x04a3
        L_0x049e:
            r85 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x04a3:
            long r6 = r6 | r85
        L_0x04a5:
            if (r4 == 0) goto L_0x04a8
            goto L_0x046d
        L_0x04a8:
            r4 = 4
        L_0x04a9:
            r85 = 134217728(0x8000000, double:6.63123685E-316)
            long r85 = r2 & r85
            r54 = 0
            int r5 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x04ba
            long r85 = r6 & r46
            int r5 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x04cc
        L_0x04ba:
            if (r0 == 0) goto L_0x04bf
            androidx.databinding.ObservableInt r5 = r0.perState
            goto L_0x04c0
        L_0x04bf:
            r5 = 0
        L_0x04c0:
            r10 = 27
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x04cc
            int r5 = r5.get()
            goto L_0x04cd
        L_0x04cc:
            r5 = 0
        L_0x04cd:
            long r85 = r2 & r28
            r54 = 0
            int r10 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x04e2
            long r85 = r6 & r46
            int r87 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r87 == 0) goto L_0x04dc
            goto L_0x04e2
        L_0x04dc:
            r85 = r4
            r86 = r5
        L_0x04e0:
            r4 = 0
            goto L_0x0513
        L_0x04e2:
            r85 = r4
            if (r0 == 0) goto L_0x04eb
            androidx.databinding.ObservableBoolean r4 = r0.fanVisibility
            r86 = r5
            goto L_0x04ee
        L_0x04eb:
            r86 = r5
            r4 = 0
        L_0x04ee:
            r5 = 28
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x04fa
            boolean r4 = r4.get()
            goto L_0x04fb
        L_0x04fa:
            r4 = 0
        L_0x04fb:
            if (r10 != 0) goto L_0x0505
            long r87 = r6 & r46
            r54 = 0
            int r5 = (r87 > r54 ? 1 : (r87 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x050e
        L_0x0505:
            if (r4 == 0) goto L_0x050c
            r87 = 64
            long r8 = r8 | r87
            goto L_0x050e
        L_0x050c:
            long r8 = r8 | r26
        L_0x050e:
            if (r4 == 0) goto L_0x0511
            goto L_0x04e0
        L_0x0511:
            r4 = 8
        L_0x0513:
            r87 = 536870912(0x20000000, double:2.652494739E-315)
            long r87 = r2 & r87
            r54 = 0
            int r5 = (r87 > r54 ? 1 : (r87 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0524
            long r87 = r6 & r46
            int r5 = (r87 > r54 ? 1 : (r87 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0536
        L_0x0524:
            if (r0 == 0) goto L_0x0529
            androidx.databinding.ObservableInt r5 = r0.minTmp
            goto L_0x052a
        L_0x0529:
            r5 = 0
        L_0x052a:
            r10 = 29
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0536
            int r5 = r5.get()
            goto L_0x0537
        L_0x0536:
            r5 = 0
        L_0x0537:
            r87 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r87 = r2 & r87
            r54 = 0
            int r10 = (r87 > r54 ? 1 : (r87 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x054e
            long r87 = r6 & r46
            int r10 = (r87 > r54 ? 1 : (r87 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x054b
            goto L_0x054e
        L_0x054b:
            r87 = r4
            goto L_0x0564
        L_0x054e:
            if (r0 == 0) goto L_0x0555
            androidx.databinding.ObservableInt r10 = r0.tmpColor
            r87 = r4
            goto L_0x0558
        L_0x0555:
            r87 = r4
            r10 = 0
        L_0x0558:
            r4 = 31
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0564
            int r4 = r10.get()
            goto L_0x0565
        L_0x0564:
            r4 = 0
        L_0x0565:
            r88 = 8589934592(0x200000000, double:4.243991582E-314)
            long r88 = r2 & r88
            r54 = 0
            int r10 = (r88 > r54 ? 1 : (r88 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x057c
            long r88 = r6 & r46
            int r10 = (r88 > r54 ? 1 : (r88 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0579
            goto L_0x057c
        L_0x0579:
            r88 = r4
            goto L_0x0594
        L_0x057c:
            if (r0 == 0) goto L_0x0583
            androidx.databinding.ObservableField<java.lang.String> r10 = r0.perSize
            r88 = r4
            goto L_0x0586
        L_0x0583:
            r88 = r4
            r10 = 0
        L_0x0586:
            r4 = 33
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0594
            java.lang.Object r4 = r10.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0595
        L_0x0594:
            r4 = 0
        L_0x0595:
            r89 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r89 = r2 & r89
            r54 = 0
            int r10 = (r89 > r54 ? 1 : (r89 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x05ac
            long r89 = r6 & r46
            int r10 = (r89 > r54 ? 1 : (r89 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x05a9
            goto L_0x05ac
        L_0x05a9:
            r89 = r4
            goto L_0x05c2
        L_0x05ac:
            if (r0 == 0) goto L_0x05b3
            androidx.databinding.ObservableInt r10 = r0.distanceHum
            r89 = r4
            goto L_0x05b6
        L_0x05b3:
            r89 = r4
            r10 = 0
        L_0x05b6:
            r4 = 34
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x05c2
            int r4 = r10.get()
            goto L_0x05c3
        L_0x05c2:
            r4 = 0
        L_0x05c3:
            r52 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r90 = r2 & r52
            r54 = 0
            int r10 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x05de
            long r90 = r6 & r46
            int r92 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r92 == 0) goto L_0x05d7
            goto L_0x05de
        L_0x05d7:
            r90 = r4
            r91 = r5
            r4 = 0
            r5 = 0
            goto L_0x0622
        L_0x05de:
            r90 = r4
            if (r0 == 0) goto L_0x05e7
            androidx.databinding.ObservableBoolean r4 = r0.showLoading
            r91 = r5
            goto L_0x05ea
        L_0x05e7:
            r91 = r5
            r4 = 0
        L_0x05ea:
            r5 = 35
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x05f6
            boolean r4 = r4.get()
            goto L_0x05f7
        L_0x05f6:
            r4 = 0
        L_0x05f7:
            if (r10 != 0) goto L_0x0601
            long r92 = r6 & r46
            r54 = 0
            int r5 = (r92 > r54 ? 1 : (r92 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0617
        L_0x0601:
            if (r4 == 0) goto L_0x060b
            r92 = 262144(0x40000, double:1.295163E-318)
            long r5 = r6 | r92
            r92 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x0615
        L_0x060b:
            r92 = 131072(0x20000, double:6.47582E-319)
            long r5 = r6 | r92
            r92 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x0615:
            long r6 = r5 | r92
        L_0x0617:
            if (r4 == 0) goto L_0x061b
            r5 = 4
            goto L_0x061c
        L_0x061b:
            r5 = 0
        L_0x061c:
            if (r4 == 0) goto L_0x0620
            r4 = 0
            goto L_0x0622
        L_0x0620:
            r4 = 8
        L_0x0622:
            r92 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r92 = r2 & r92
            r54 = 0
            int r10 = (r92 > r54 ? 1 : (r92 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0639
            long r92 = r6 & r46
            int r10 = (r92 > r54 ? 1 : (r92 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0636
            goto L_0x0639
        L_0x0636:
            r92 = r4
            goto L_0x064f
        L_0x0639:
            if (r0 == 0) goto L_0x0640
            androidx.databinding.ObservableInt r10 = r0.lowHum
            r92 = r4
            goto L_0x0643
        L_0x0640:
            r92 = r4
            r10 = 0
        L_0x0643:
            r4 = 37
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x064f
            int r4 = r10.get()
            goto L_0x0650
        L_0x064f:
            r4 = 0
        L_0x0650:
            r93 = 274877906944(0x4000000000, double:1.358077306218E-312)
            long r93 = r2 & r93
            r54 = 0
            int r10 = (r93 > r54 ? 1 : (r93 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0667
            long r93 = r6 & r46
            int r10 = (r93 > r54 ? 1 : (r93 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0664
            goto L_0x0667
        L_0x0664:
            r93 = r4
            goto L_0x067d
        L_0x0667:
            if (r0 == 0) goto L_0x066e
            androidx.databinding.ObservableInt r10 = r0.fanType
            r93 = r4
            goto L_0x0671
        L_0x066e:
            r93 = r4
            r10 = 0
        L_0x0671:
            r4 = 38
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x067d
            int r4 = r10.get()
            goto L_0x067e
        L_0x067d:
            r4 = 0
        L_0x067e:
            r94 = 18085317110268928(0x40408006020400, double:1.8080898422457283E-307)
            long r94 = r2 & r94
            r54 = 0
            int r10 = (r94 > r54 ? 1 : (r94 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x06a5
            long r94 = r6 & r42
            int r10 = (r94 > r54 ? 1 : (r94 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0692
            goto L_0x06a5
        L_0x0692:
            r94 = r4
            r95 = r5
            r4 = 0
        L_0x0697:
            r5 = 0
            r10 = 0
            r96 = 0
            r97 = 0
            r98 = 0
            r99 = 0
            r100 = 0
            goto L_0x0897
        L_0x06a5:
            if (r0 == 0) goto L_0x06ac
            androidx.databinding.ObservableBoolean r10 = r0.isConnet
            r94 = r4
            goto L_0x06af
        L_0x06ac:
            r94 = r4
            r10 = 0
        L_0x06af:
            r4 = 39
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x06bb
            boolean r4 = r10.get()
            goto L_0x06bc
        L_0x06bb:
            r4 = 0
        L_0x06bc:
            r95 = 549755813888(0x8000000000, double:2.716154612436E-312)
            long r95 = r2 & r95
            r54 = 0
            int r10 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x06cf
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x0710
        L_0x06cf:
            if (r4 == 0) goto L_0x06f4
            r95 = 64
            long r6 = r6 | r95
            r95 = 65536(0x10000, double:3.2379E-319)
            long r6 = r6 | r95
            r95 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r6 = r6 | r95
            long r6 = r6 | r34
            r95 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r6 = r6 | r95
            r95 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r6 = r6 | r95
            r95 = 16
            long r8 = r8 | r95
            r95 = 1024(0x400, double:5.06E-321)
            long r8 = r8 | r95
            goto L_0x0710
        L_0x06f4:
            long r6 = r6 | r26
            r95 = 32768(0x8000, double:1.61895E-319)
            long r6 = r6 | r95
            r95 = 8589934592(0x200000000, double:4.243991582E-314)
            long r6 = r6 | r95
            long r6 = r6 | r36
            r95 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r6 = r6 | r95
            r95 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r6 = r6 | r95
            long r8 = r8 | r30
            long r8 = r8 | r18
        L_0x0710:
            r95 = 549789368320(0x8002000000, double:2.716320393357E-312)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x0723
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x0737
        L_0x0723:
            if (r4 == 0) goto L_0x072d
            r95 = 1048576(0x100000, double:5.180654E-318)
            long r6 = r6 | r95
            long r6 = r6 | r32
            goto L_0x0737
        L_0x072d:
            r95 = 524288(0x80000, double:2.590327E-318)
            long r6 = r6 | r95
            r95 = 2097152(0x200000, double:1.0361308E-317)
            long r6 = r6 | r95
        L_0x0737:
            if (r10 != 0) goto L_0x0741
            long r95 = r6 & r42
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x074b
        L_0x0741:
            if (r4 == 0) goto L_0x0746
            long r6 = r6 | r24
            goto L_0x074b
        L_0x0746:
            r95 = 8388608(0x800000, double:4.144523E-317)
            long r6 = r6 | r95
        L_0x074b:
            r95 = 18014948265295872(0x40008000000000, double:1.780276379174756E-307)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x075e
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x0768
        L_0x075e:
            if (r4 == 0) goto L_0x0763
            long r6 = r6 | r28
            goto L_0x0768
        L_0x0763:
            r95 = 134217728(0x8000000, double:6.63123685E-316)
            long r6 = r6 | r95
        L_0x0768:
            r95 = 70918499991552(0x408000000000, double:3.50383945004186E-310)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x077b
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x078a
        L_0x077b:
            if (r4 == 0) goto L_0x0783
            r95 = 4294967296(0x100000000, double:2.121995791E-314)
            goto L_0x0788
        L_0x0783:
            r95 = 2147483648(0x80000000, double:1.0609978955E-314)
        L_0x0788:
            long r6 = r6 | r95
        L_0x078a:
            r95 = 549755944960(0x8000020000, double:2.716155260017E-312)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x079d
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x07ac
        L_0x079d:
            if (r4 == 0) goto L_0x07a5
            r95 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            goto L_0x07aa
        L_0x07a5:
            r95 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
        L_0x07aa:
            long r6 = r6 | r95
        L_0x07ac:
            r95 = 549822922752(0x8004000000, double:2.71648617428E-312)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x07bf
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x07c8
        L_0x07bf:
            if (r4 == 0) goto L_0x07c4
            long r6 = r6 | r38
            goto L_0x07c8
        L_0x07c4:
            r95 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r6 = r6 | r95
        L_0x07c8:
            r95 = 549755814912(0x8000000400, double:2.716154617495E-312)
            long r95 = r2 & r95
            r54 = 0
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 != 0) goto L_0x07db
            long r95 = r6 & r46
            int r97 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r97 == 0) goto L_0x07e4
        L_0x07db:
            if (r4 == 0) goto L_0x07e0
            long r8 = r8 | r40
            goto L_0x07e4
        L_0x07e0:
            r95 = -9223372036854775808
            long r6 = r6 | r95
        L_0x07e4:
            if (r10 != 0) goto L_0x07f3
            long r95 = r6 & r46
            r54 = 0
            int r10 = (r95 > r54 ? 1 : (r95 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x07ef
            goto L_0x07f3
        L_0x07ef:
            r95 = r5
            goto L_0x0697
        L_0x07f3:
            if (r4 == 0) goto L_0x0800
            android.widget.TextView r10 = r1.tvWindSpeed
            r95 = r5
            int r5 = com.eternal.main.C2343R.C2344color.white
            int r5 = getColorFromResource(r10, r5)
            goto L_0x080a
        L_0x0800:
            r95 = r5
            android.widget.TextView r5 = r1.tvWindSpeed
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r5 = getColorFromResource(r5, r10)
        L_0x080a:
            if (r4 == 0) goto L_0x0817
            android.widget.TextView r10 = r1.tvVpd
            r96 = r5
            int r5 = com.eternal.main.C2343R.C2344color.white
            int r5 = getColorFromResource(r10, r5)
            goto L_0x0821
        L_0x0817:
            r96 = r5
            android.widget.TextView r5 = r1.tvVpd
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r5 = getColorFromResource(r5, r10)
        L_0x0821:
            if (r4 == 0) goto L_0x082e
            android.widget.TextView r10 = r1.tvTime
            r97 = r5
            int r5 = com.eternal.main.C2343R.C2344color.color_BFBFBF
            int r5 = getColorFromResource(r10, r5)
            goto L_0x0838
        L_0x082e:
            r97 = r5
            android.widget.TextView r5 = r1.tvTime
            int r10 = com.eternal.main.C2343R.C2344color.color_FF6A6A
            int r5 = getColorFromResource(r5, r10)
        L_0x0838:
            if (r4 == 0) goto L_0x0845
            android.widget.TextView r10 = r1.tvDeviceName
            r98 = r5
            int r5 = com.eternal.main.C2343R.C2344color.white
            int r5 = getColorFromResource(r10, r5)
            goto L_0x084f
        L_0x0845:
            r98 = r5
            android.widget.TextView r5 = r1.tvDeviceName
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r5 = getColorFromResource(r5, r10)
        L_0x084f:
            if (r4 == 0) goto L_0x085c
            android.widget.TextView r10 = r1.deviceType
            r99 = r5
            int r5 = com.eternal.main.C2343R.C2344color.color_BFBFBF
            int r5 = getColorFromResource(r10, r5)
            goto L_0x0866
        L_0x085c:
            r99 = r5
            android.widget.TextView r5 = r1.deviceType
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r5 = getColorFromResource(r5, r10)
        L_0x0866:
            if (r4 == 0) goto L_0x0873
            android.widget.TextView r10 = r1.tvVpdUnit
            r100 = r5
            int r5 = com.eternal.main.C2343R.C2344color.color_BFBFBF
            int r5 = getColorFromResource(r10, r5)
            goto L_0x087d
        L_0x0873:
            r100 = r5
            android.widget.TextView r5 = r1.tvVpdUnit
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r5 = getColorFromResource(r5, r10)
        L_0x087d:
            if (r4 == 0) goto L_0x088a
            android.widget.TextView r10 = r1.mboundView34
            r101 = r4
            int r4 = com.eternal.main.C2343R.C2344color.white
            int r4 = getColorFromResource(r10, r4)
            goto L_0x0894
        L_0x088a:
            r101 = r4
            android.widget.TextView r4 = r1.mboundView34
            int r10 = com.eternal.main.C2343R.C2344color.color_707070
            int r4 = getColorFromResource(r4, r10)
        L_0x0894:
            r10 = r4
            r4 = r101
        L_0x0897:
            r101 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r101 = r2 & r101
            r54 = 0
            int r103 = (r101 > r54 ? 1 : (r101 == r54 ? 0 : -1))
            if (r103 != 0) goto L_0x08b0
            long r101 = r6 & r46
            int r103 = (r101 > r54 ? 1 : (r101 == r54 ? 0 : -1))
            if (r103 == 0) goto L_0x08ab
            goto L_0x08b0
        L_0x08ab:
            r101 = r4
            r102 = r5
            goto L_0x08c8
        L_0x08b0:
            r101 = r4
            if (r0 == 0) goto L_0x08b9
            androidx.databinding.ObservableInt r4 = r0.highHum
            r102 = r5
            goto L_0x08bc
        L_0x08b9:
            r102 = r5
            r4 = 0
        L_0x08bc:
            r5 = 40
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x08c8
            int r4 = r4.get()
            goto L_0x08c9
        L_0x08c8:
            r4 = 0
        L_0x08c9:
            r103 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r103 = r2 & r103
            r54 = 0
            int r5 = (r103 > r54 ? 1 : (r103 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x08e4
            long r103 = r6 & r46
            int r105 = (r103 > r54 ? 1 : (r103 == r54 ? 0 : -1))
            if (r105 == 0) goto L_0x08dd
            goto L_0x08e4
        L_0x08dd:
            r103 = r4
            r104 = r10
            r4 = 0
            r5 = 0
            goto L_0x0939
        L_0x08e4:
            r103 = r4
            if (r0 == 0) goto L_0x08ed
            androidx.databinding.ObservableBoolean r4 = r0.masterVisibility
            r104 = r10
            goto L_0x08f0
        L_0x08ed:
            r104 = r10
            r4 = 0
        L_0x08f0:
            r10 = 42
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x08fc
            boolean r4 = r4.get()
            goto L_0x08fd
        L_0x08fc:
            r4 = 0
        L_0x08fd:
            if (r5 != 0) goto L_0x0907
            long r105 = r6 & r46
            r54 = 0
            int r5 = (r105 > r54 ? 1 : (r105 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x091c
        L_0x0907:
            if (r4 == 0) goto L_0x0913
            r105 = 16
            long r5 = r6 | r105
            r105 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x091a
        L_0x0913:
            long r5 = r6 | r30
            r105 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x091a:
            long r6 = r5 | r105
        L_0x091c:
            if (r4 == 0) goto L_0x0920
            r5 = 0
            goto L_0x0922
        L_0x0920:
            r5 = 8
        L_0x0922:
            if (r4 == 0) goto L_0x092d
            android.widget.LinearLayout r4 = r1.llContent
            android.content.Context r4 = r4.getContext()
            int r10 = com.eternal.main.C2343R.C2345drawable.rectang_top_radiu_5_242425_shape
            goto L_0x0935
        L_0x092d:
            android.widget.LinearLayout r4 = r1.llContent
            android.content.Context r4 = r4.getContext()
            int r10 = com.eternal.main.C2343R.C2345drawable.rectang_10_242425_shape
        L_0x0935:
            android.graphics.drawable.Drawable r4 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r4, r10)
        L_0x0939:
            long r105 = r2 & r36
            r54 = 0
            int r10 = (r105 > r54 ? 1 : (r105 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x094f
            long r105 = r6 & r46
            int r107 = (r105 > r54 ? 1 : (r105 == r54 ? 0 : -1))
            if (r107 == 0) goto L_0x0948
            goto L_0x094f
        L_0x0948:
            r105 = r4
            r106 = r5
            r4 = 0
            r5 = 0
            goto L_0x098f
        L_0x094f:
            r105 = r4
            if (r0 == 0) goto L_0x0958
            androidx.databinding.ObservableBoolean r4 = r0.allPortVisible
            r106 = r5
            goto L_0x095b
        L_0x0958:
            r106 = r5
            r4 = 0
        L_0x095b:
            r5 = 43
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0967
            boolean r4 = r4.get()
            goto L_0x0968
        L_0x0967:
            r4 = 0
        L_0x0968:
            if (r10 != 0) goto L_0x0972
            long r107 = r6 & r46
            r54 = 0
            int r5 = (r107 > r54 ? 1 : (r107 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0983
        L_0x0972:
            if (r4 == 0) goto L_0x097b
            r107 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r6 = r6 | r107
            r107 = 4
            goto L_0x0981
        L_0x097b:
            r107 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r6 = r6 | r107
            r107 = 2
        L_0x0981:
            long r8 = r8 | r107
        L_0x0983:
            if (r4 == 0) goto L_0x0987
            r5 = 0
            goto L_0x0989
        L_0x0987:
            r5 = 8
        L_0x0989:
            if (r4 == 0) goto L_0x098e
            r4 = 8
            goto L_0x098f
        L_0x098e:
            r4 = 0
        L_0x098f:
            long r107 = r2 & r34
            r54 = 0
            int r10 = (r107 > r54 ? 1 : (r107 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x09a1
            long r107 = r6 & r46
            int r10 = (r107 > r54 ? 1 : (r107 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x099e
            goto L_0x09a1
        L_0x099e:
            r107 = r4
            goto L_0x09b7
        L_0x09a1:
            if (r0 == 0) goto L_0x09a8
            androidx.databinding.ObservableInt r10 = r0.highTmp
            r107 = r4
            goto L_0x09ab
        L_0x09a8:
            r107 = r4
            r10 = 0
        L_0x09ab:
            r4 = 44
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x09b7
            int r4 = r10.get()
            goto L_0x09b8
        L_0x09b7:
            r4 = 0
        L_0x09b8:
            r108 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            long r108 = r2 & r108
            r54 = 0
            int r10 = (r108 > r54 ? 1 : (r108 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x09cf
            long r108 = r6 & r46
            int r10 = (r108 > r54 ? 1 : (r108 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x09cc
            goto L_0x09cf
        L_0x09cc:
            r108 = r4
            goto L_0x09e7
        L_0x09cf:
            if (r0 == 0) goto L_0x09d6
            androidx.databinding.ObservableField<java.lang.String> r10 = r0.humUnit
            r108 = r4
            goto L_0x09d9
        L_0x09d6:
            r108 = r4
            r10 = 0
        L_0x09d9:
            r4 = 47
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x09e7
            java.lang.Object r4 = r10.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x09e8
        L_0x09e7:
            r4 = 0
        L_0x09e8:
            r109 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r109 = r2 & r109
            r54 = 0
            int r10 = (r109 > r54 ? 1 : (r109 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x09ff
            long r109 = r6 & r46
            int r111 = (r109 > r54 ? 1 : (r109 == r54 ? 0 : -1))
            if (r111 == 0) goto L_0x09f9
            goto L_0x09ff
        L_0x09f9:
            r109 = r4
            r110 = r5
        L_0x09fd:
            r4 = 0
            goto L_0x0a32
        L_0x09ff:
            r109 = r4
            if (r0 == 0) goto L_0x0a08
            androidx.databinding.ObservableBoolean r4 = r0.rangeVisibility
            r110 = r5
            goto L_0x0a0b
        L_0x0a08:
            r110 = r5
            r4 = 0
        L_0x0a0b:
            r5 = 49
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0a17
            boolean r4 = r4.get()
            goto L_0x0a18
        L_0x0a17:
            r4 = 0
        L_0x0a18:
            if (r10 != 0) goto L_0x0a22
            long r111 = r6 & r46
            r54 = 0
            int r5 = (r111 > r54 ? 1 : (r111 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0a2d
        L_0x0a22:
            if (r4 == 0) goto L_0x0a28
            r111 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x0a2b
        L_0x0a28:
            r111 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x0a2b:
            long r6 = r6 | r111
        L_0x0a2d:
            if (r4 == 0) goto L_0x0a30
            goto L_0x09fd
        L_0x0a30:
            r4 = 8
        L_0x0a32:
            r111 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r111 = r2 & r111
            r54 = 0
            int r5 = (r111 > r54 ? 1 : (r111 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0a47
            long r111 = r6 & r46
            int r10 = (r111 > r54 ? 1 : (r111 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0a43
            goto L_0x0a47
        L_0x0a43:
            r111 = r4
        L_0x0a45:
            r4 = 0
            goto L_0x0a74
        L_0x0a47:
            if (r0 == 0) goto L_0x0a4e
            androidx.databinding.ObservableBoolean r10 = r0.humVisibility
            r111 = r4
            goto L_0x0a51
        L_0x0a4e:
            r111 = r4
            r10 = 0
        L_0x0a51:
            r4 = 50
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0a5d
            boolean r4 = r10.get()
            goto L_0x0a5e
        L_0x0a5d:
            r4 = 0
        L_0x0a5e:
            if (r5 != 0) goto L_0x0a68
            long r112 = r6 & r46
            r54 = 0
            int r5 = (r112 > r54 ? 1 : (r112 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0a6f
        L_0x0a68:
            if (r4 == 0) goto L_0x0a6d
            long r8 = r8 | r20
            goto L_0x0a6f
        L_0x0a6d:
            long r8 = r8 | r22
        L_0x0a6f:
            if (r4 == 0) goto L_0x0a72
            goto L_0x0a45
        L_0x0a72:
            r4 = 8
        L_0x0a74:
            r112 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r112 = r2 & r112
            r54 = 0
            int r5 = (r112 > r54 ? 1 : (r112 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0a89
            long r112 = r6 & r46
            int r10 = (r112 > r54 ? 1 : (r112 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0a85
            goto L_0x0a89
        L_0x0a85:
            r112 = r4
        L_0x0a87:
            r4 = 0
            goto L_0x0ab8
        L_0x0a89:
            if (r0 == 0) goto L_0x0a90
            androidx.databinding.ObservableBoolean r10 = r0.wifiIconVisible
            r112 = r4
            goto L_0x0a93
        L_0x0a90:
            r112 = r4
            r10 = 0
        L_0x0a93:
            r4 = 51
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0a9f
            boolean r4 = r10.get()
            goto L_0x0aa0
        L_0x0a9f:
            r4 = 0
        L_0x0aa0:
            if (r5 != 0) goto L_0x0aaa
            long r113 = r6 & r46
            r54 = 0
            int r5 = (r113 > r54 ? 1 : (r113 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0ab3
        L_0x0aaa:
            if (r4 == 0) goto L_0x0aaf
            r113 = 16384(0x4000, double:8.0948E-320)
            goto L_0x0ab1
        L_0x0aaf:
            r113 = 8192(0x2000, double:4.0474E-320)
        L_0x0ab1:
            long r6 = r6 | r113
        L_0x0ab3:
            if (r4 == 0) goto L_0x0ab6
            goto L_0x0a87
        L_0x0ab6:
            r4 = 8
        L_0x0ab8:
            r113 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r113 = r2 & r113
            r54 = 0
            int r5 = (r113 > r54 ? 1 : (r113 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0ac8
            long r113 = r6 & r46
            int r5 = (r113 > r54 ? 1 : (r113 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0adc
        L_0x0ac8:
            if (r0 == 0) goto L_0x0acd
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.tmpUnit
            goto L_0x0ace
        L_0x0acd:
            r5 = 0
        L_0x0ace:
            r10 = 52
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0adc
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0add
        L_0x0adc:
            r5 = 0
        L_0x0add:
            r113 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            long r113 = r2 & r113
            r54 = 0
            int r10 = (r113 > r54 ? 1 : (r113 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0af1
            long r113 = r6 & r46
            int r10 = (r113 > r54 ? 1 : (r113 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0aee
            goto L_0x0af1
        L_0x0aee:
            r113 = r4
            goto L_0x0b07
        L_0x0af1:
            if (r0 == 0) goto L_0x0af8
            androidx.databinding.ObservableInt r10 = r0.minHum
            r113 = r4
            goto L_0x0afb
        L_0x0af8:
            r113 = r4
            r10 = 0
        L_0x0afb:
            r4 = 53
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0b07
            int r4 = r10.get()
            goto L_0x0b08
        L_0x0b07:
            r4 = 0
        L_0x0b08:
            r114 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r114 = r2 & r114
            r54 = 0
            int r10 = (r114 > r54 ? 1 : (r114 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0b1c
            long r114 = r6 & r46
            int r10 = (r114 > r54 ? 1 : (r114 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0b19
            goto L_0x0b1c
        L_0x0b19:
            r114 = r4
            goto L_0x0b32
        L_0x0b1c:
            if (r0 == 0) goto L_0x0b23
            androidx.databinding.ObservableInt r10 = r0.lowTmp
            r114 = r4
            goto L_0x0b26
        L_0x0b23:
            r114 = r4
            r10 = 0
        L_0x0b26:
            r4 = 55
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0b32
            int r4 = r10.get()
            goto L_0x0b33
        L_0x0b32:
            r4 = 0
        L_0x0b33:
            r115 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r115 = r2 & r115
            r54 = 0
            int r10 = (r115 > r54 ? 1 : (r115 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0b4a
            long r115 = r6 & r46
            int r117 = (r115 > r54 ? 1 : (r115 == r54 ? 0 : -1))
            if (r117 == 0) goto L_0x0b44
            goto L_0x0b4a
        L_0x0b44:
            r115 = r4
            r116 = r5
            r4 = 0
            goto L_0x0b93
        L_0x0b4a:
            r115 = r4
            if (r0 == 0) goto L_0x0b53
            androidx.databinding.ObservableBoolean r4 = r0.powerOff
            r116 = r5
            goto L_0x0b56
        L_0x0b53:
            r116 = r5
            r4 = 0
        L_0x0b56:
            r5 = 56
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0b62
            boolean r4 = r4.get()
            goto L_0x0b63
        L_0x0b62:
            r4 = 0
        L_0x0b63:
            if (r10 != 0) goto L_0x0b6d
            long r117 = r6 & r46
            r54 = 0
            int r5 = (r117 > r54 ? 1 : (r117 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0b7c
        L_0x0b6d:
            if (r4 == 0) goto L_0x0b75
            r117 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x0b7a
        L_0x0b75:
            r117 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x0b7a:
            long r6 = r6 | r117
        L_0x0b7c:
            if (r4 == 0) goto L_0x0b87
            android.widget.TextView r4 = r1.mboundView34
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.main.C2343R.string.power_off
            goto L_0x0b8f
        L_0x0b87:
            android.widget.TextView r4 = r1.mboundView34
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.main.C2343R.string.power_on
        L_0x0b8f:
            java.lang.String r4 = r4.getString(r5)
        L_0x0b93:
            long r117 = r2 & r16
            r54 = 0
            int r5 = (r117 > r54 ? 1 : (r117 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0ba1
            long r117 = r6 & r46
            int r5 = (r117 > r54 ? 1 : (r117 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0bb5
        L_0x0ba1:
            if (r0 == 0) goto L_0x0ba6
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.tmpSize
            goto L_0x0ba7
        L_0x0ba6:
            r5 = 0
        L_0x0ba7:
            r10 = 57
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0bb5
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0bb6
        L_0x0bb5:
            r5 = 0
        L_0x0bb6:
            r44 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            long r117 = r2 & r44
            r54 = 0
            int r10 = (r117 > r54 ? 1 : (r117 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0bcb
            long r117 = r6 & r46
            int r10 = (r117 > r54 ? 1 : (r117 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0bc7
            goto L_0x0bcb
        L_0x0bc7:
            r117 = r4
            r4 = 0
            goto L_0x0be8
        L_0x0bcb:
            if (r0 == 0) goto L_0x0bd2
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r10 = r0.typeIcon
            r117 = r4
            goto L_0x0bd5
        L_0x0bd2:
            r117 = r4
            r10 = 0
        L_0x0bd5:
            r4 = 58
            r1.updateLiveDataRegistration(r4, r10)
            if (r10 == 0) goto L_0x0be3
            java.lang.Object r4 = r10.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0be4
        L_0x0be3:
            r4 = 0
        L_0x0be4:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
        L_0x0be8:
            r118 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            long r118 = r2 & r118
            r54 = 0
            int r10 = (r118 > r54 ? 1 : (r118 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0c00
            long r118 = r6 & r46
            int r120 = (r118 > r54 ? 1 : (r118 == r54 ? 0 : -1))
            if (r120 == 0) goto L_0x0bf9
            goto L_0x0c00
        L_0x0bf9:
            r118 = r4
            r119 = r5
        L_0x0bfd:
            r68 = 0
            goto L_0x0c31
        L_0x0c00:
            r118 = r4
            if (r0 == 0) goto L_0x0c09
            androidx.databinding.ObservableBoolean r4 = r0.blueIconVisible
            r119 = r5
            goto L_0x0c0c
        L_0x0c09:
            r119 = r5
            r4 = 0
        L_0x0c0c:
            r5 = 59
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0c18
            boolean r4 = r4.get()
            goto L_0x0c19
        L_0x0c18:
            r4 = 0
        L_0x0c19:
            if (r10 != 0) goto L_0x0c23
            long r120 = r6 & r46
            r54 = 0
            int r5 = (r120 > r54 ? 1 : (r120 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0c2c
        L_0x0c23:
            if (r4 == 0) goto L_0x0c28
            r120 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            goto L_0x0c2a
        L_0x0c28:
            r120 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
        L_0x0c2a:
            long r6 = r6 | r120
        L_0x0c2c:
            if (r4 == 0) goto L_0x0c2f
            goto L_0x0bfd
        L_0x0c2f:
            r68 = 8
        L_0x0c31:
            r4 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r4 = r4 & r2
            r54 = 0
            int r10 = (r4 > r54 ? 1 : (r4 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0c40
            long r4 = r6 & r46
            int r10 = (r4 > r54 ? 1 : (r4 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0c52
        L_0x0c40:
            if (r0 == 0) goto L_0x0c45
            androidx.databinding.ObservableInt r4 = r0.distanceTmp
            goto L_0x0c46
        L_0x0c45:
            r4 = 0
        L_0x0c46:
            r5 = 60
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0c52
            int r4 = r4.get()
            goto L_0x0c53
        L_0x0c52:
            r4 = 0
        L_0x0c53:
            r120 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r120 = r2 & r120
            r54 = 0
            int r5 = (r120 > r54 ? 1 : (r120 == r54 ? 0 : -1))
            if (r5 != 0) goto L_0x0c63
            long r120 = r6 & r46
            int r5 = (r120 > r54 ? 1 : (r120 == r54 ? 0 : -1))
            if (r5 == 0) goto L_0x0c77
        L_0x0c63:
            if (r0 == 0) goto L_0x0c68
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.device
            goto L_0x0c69
        L_0x0c68:
            r5 = 0
        L_0x0c69:
            r10 = 61
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0c77
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0c78
        L_0x0c77:
            r5 = 0
        L_0x0c78:
            long r120 = r2 & r38
            r54 = 0
            int r10 = (r120 > r54 ? 1 : (r120 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0c8a
            long r120 = r6 & r46
            int r10 = (r120 > r54 ? 1 : (r120 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0c87
            goto L_0x0c8a
        L_0x0c87:
            r120 = r4
            goto L_0x0ca0
        L_0x0c8a:
            if (r0 == 0) goto L_0x0c91
            androidx.databinding.ObservableInt r10 = r0.humUnitColor
            r120 = r4
            goto L_0x0c94
        L_0x0c91:
            r120 = r4
            r10 = 0
        L_0x0c94:
            r4 = 62
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0ca0
            int r4 = r10.get()
            goto L_0x0ca1
        L_0x0ca0:
            r4 = 0
        L_0x0ca1:
            r121 = -9223372036854775808
            long r121 = r2 & r121
            r54 = 0
            int r10 = (r121 > r54 ? 1 : (r121 == r54 ? 0 : -1))
            if (r10 != 0) goto L_0x0cb6
            long r121 = r6 & r46
            int r10 = (r121 > r54 ? 1 : (r121 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x0cb2
            goto L_0x0cb6
        L_0x0cb2:
            r121 = r4
            goto L_0x0d5e
        L_0x0cb6:
            if (r0 == 0) goto L_0x0cbd
            androidx.databinding.ObservableField<java.lang.String> r10 = r0.name
            r121 = r4
            goto L_0x0cc0
        L_0x0cbd:
            r121 = r4
            r10 = 0
        L_0x0cc0:
            r4 = 63
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0d5e
            java.lang.Object r4 = r10.get()
            java.lang.String r4 = (java.lang.String) r4
            r145 = r11
            r156 = r56
            r157 = r57
            r158 = r58
            r159 = r59
            r160 = r60
            r161 = r61
            r162 = r62
            r163 = r63
            r123 = r67
            r10 = r68
            r138 = r69
            r140 = r70
            r125 = r71
            r164 = r73
            r63 = r75
            r146 = r76
            r57 = r77
            r133 = r78
            r152 = r80
            r128 = r81
            r127 = r82
            r144 = r83
            r154 = r84
            r56 = r85
            r155 = r87
            r126 = r88
            r124 = r89
            r129 = r90
            r142 = r91
            r151 = r92
            r131 = r93
            r148 = r94
            r150 = r95
            r130 = r103
            r76 = r105
            r149 = r106
            r62 = r107
            r136 = r108
            r134 = r109
            r139 = r110
            r153 = r112
            r147 = r113
            r132 = r114
            r137 = r115
            r143 = r116
            r67 = r117
            r11 = r118
            r141 = r119
            r135 = r120
            r58 = r6
            r60 = r8
            r69 = r12
            r70 = r14
            r73 = r15
            r68 = r51
            r71 = r65
            r75 = r66
            r77 = r72
            r15 = r96
            r12 = r97
            r8 = r98
            r7 = r100
            r9 = r102
            r6 = r104
            r66 = r111
            r65 = r121
            r51 = r4
            r14 = r5
            r72 = r64
            r64 = r74
            r4 = r79
            goto L_0x0ded
        L_0x0d5e:
            r145 = r11
            r156 = r56
            r157 = r57
            r158 = r58
            r159 = r59
            r160 = r60
            r161 = r61
            r162 = r62
            r163 = r63
            r123 = r67
            r10 = r68
            r138 = r69
            r140 = r70
            r125 = r71
            r164 = r73
            r63 = r75
            r146 = r76
            r57 = r77
            r133 = r78
            r4 = r79
            r152 = r80
            r128 = r81
            r127 = r82
            r144 = r83
            r154 = r84
            r56 = r85
            r155 = r87
            r126 = r88
            r124 = r89
            r129 = r90
            r142 = r91
            r151 = r92
            r131 = r93
            r148 = r94
            r150 = r95
            r130 = r103
            r76 = r105
            r149 = r106
            r62 = r107
            r136 = r108
            r134 = r109
            r139 = r110
            r153 = r112
            r147 = r113
            r132 = r114
            r137 = r115
            r143 = r116
            r67 = r117
            r11 = r118
            r141 = r119
            r135 = r120
            r58 = r6
            r60 = r8
            r69 = r12
            r70 = r14
            r73 = r15
            r68 = r51
            r71 = r65
            r75 = r66
            r77 = r72
            r15 = r96
            r12 = r97
            r8 = r98
            r7 = r100
            r9 = r102
            r6 = r104
            r66 = r111
            r65 = r121
            r51 = 0
            r14 = r5
            r72 = r64
            r64 = r74
        L_0x0ded:
            r5 = r86
            r74 = r13
            r13 = r99
        L_0x0df3:
            long r60 = r60 & r40
            r54 = 0
            int r78 = (r60 > r54 ? 1 : (r60 == r54 ? 0 : -1))
            if (r78 == 0) goto L_0x0e13
            r60 = r11
            if (r0 == 0) goto L_0x0e04
            androidx.databinding.ObservableBoolean r11 = r0.humClose
            r61 = r4
            goto L_0x0e07
        L_0x0e04:
            r61 = r4
            r11 = 0
        L_0x0e07:
            r4 = 10
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0e17
            boolean r4 = r11.get()
            goto L_0x0e18
        L_0x0e13:
            r61 = r4
            r60 = r11
        L_0x0e17:
            r4 = 0
        L_0x0e18:
            r78 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            long r78 = r58 & r78
            r54 = 0
            int r11 = (r78 > r54 ? 1 : (r78 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0e3b
            if (r0 == 0) goto L_0x0e2c
            androidx.databinding.ObservableBoolean r11 = r0.tmpClose
            r78 = r4
            goto L_0x0e2f
        L_0x0e2c:
            r78 = r4
            r11 = 0
        L_0x0e2f:
            r4 = 17
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0e3d
            boolean r4 = r11.get()
            goto L_0x0e3e
        L_0x0e3b:
            r78 = r4
        L_0x0e3d:
            r4 = 0
        L_0x0e3e:
            r79 = 5242880(0x500000, double:2.590327E-317)
            long r79 = r58 & r79
            r54 = 0
            int r11 = (r79 > r54 ? 1 : (r79 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0e6b
            if (r0 == 0) goto L_0x0e50
            androidx.databinding.ObservableBoolean r11 = r0.isConnectWiFi
            r79 = r4
            goto L_0x0e53
        L_0x0e50:
            r79 = r4
            r11 = 0
        L_0x0e53:
            r4 = 25
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0e5f
            boolean r4 = r11.get()
            goto L_0x0e60
        L_0x0e5f:
            r4 = 0
        L_0x0e60:
            long r80 = r58 & r32
            r54 = 0
            int r11 = (r80 > r54 ? 1 : (r80 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0e70
            r11 = r4 ^ 1
            goto L_0x0e71
        L_0x0e6b:
            r79 = r4
            r54 = 0
            r4 = 0
        L_0x0e70:
            r11 = 0
        L_0x0e71:
            long r80 = r58 & r38
            int r82 = (r80 > r54 ? 1 : (r80 == r54 ? 0 : -1))
            r80 = r4
            if (r82 == 0) goto L_0x0e8f
            if (r0 == 0) goto L_0x0e80
            androidx.databinding.ObservableBoolean r4 = r0.tmpHighClose
            r81 = r11
            goto L_0x0e83
        L_0x0e80:
            r81 = r11
            r4 = 0
        L_0x0e83:
            r11 = 26
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0e91
            boolean r4 = r4.get()
            goto L_0x0e92
        L_0x0e8f:
            r81 = r11
        L_0x0e91:
            r4 = 0
        L_0x0e92:
            long r82 = r58 & r36
            r54 = 0
            int r11 = (r82 > r54 ? 1 : (r82 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0e9d
            int r11 = com.eternal.main.C2343R.mipmap.expand_grey
            goto L_0x0e9e
        L_0x0e9d:
            r11 = 0
        L_0x0e9e:
            long r82 = r58 & r34
            int r84 = (r82 > r54 ? 1 : (r82 == r54 ? 0 : -1))
            if (r84 == 0) goto L_0x0ea7
            int r82 = com.eternal.main.C2343R.mipmap.expand
            goto L_0x0ea9
        L_0x0ea7:
            r82 = 0
        L_0x0ea9:
            r83 = 4294967296(0x100000000, double:2.121995791E-314)
            long r83 = r58 & r83
            int r85 = (r83 > r54 ? 1 : (r83 == r54 ? 0 : -1))
            r83 = r4
            if (r85 == 0) goto L_0x0ecc
            if (r0 == 0) goto L_0x0ebd
            androidx.databinding.ObservableBoolean r4 = r0.humLowClose
            r84 = r11
            goto L_0x0ec0
        L_0x0ebd:
            r84 = r11
            r4 = 0
        L_0x0ec0:
            r11 = 46
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0ece
            boolean r4 = r4.get()
            goto L_0x0ecf
        L_0x0ecc:
            r84 = r11
        L_0x0ece:
            r4 = 0
        L_0x0ecf:
            long r85 = r58 & r28
            r54 = 0
            int r11 = (r85 > r54 ? 1 : (r85 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0eed
            if (r0 == 0) goto L_0x0ede
            androidx.databinding.ObservableBoolean r11 = r0.humHighClose
            r85 = r4
            goto L_0x0ee1
        L_0x0ede:
            r85 = r4
            r11 = 0
        L_0x0ee1:
            r4 = 54
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0eef
            boolean r4 = r11.get()
            goto L_0x0ef0
        L_0x0eed:
            r85 = r4
        L_0x0eef:
            r4 = 0
        L_0x0ef0:
            long r86 = r58 & r24
            r54 = 0
            int r11 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r11 == 0) goto L_0x0f0a
            if (r0 == 0) goto L_0x0efd
            androidx.databinding.ObservableBoolean r0 = r0.tmpLowClose
            goto L_0x0efe
        L_0x0efd:
            r0 = 0
        L_0x0efe:
            r11 = 64
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0f0a
            boolean r0 = r0.get()
            goto L_0x0f0b
        L_0x0f0a:
            r0 = 0
        L_0x0f0b:
            r86 = 549789368320(0x8002000000, double:2.716320393357E-312)
            long r86 = r2 & r86
            r54 = 0
            int r11 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r11 != 0) goto L_0x0f24
            long r86 = r58 & r46
            int r48 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r48 == 0) goto L_0x0f1f
            goto L_0x0f24
        L_0x0f1f:
            r80 = 0
            r81 = 0
            goto L_0x0f5c
        L_0x0f24:
            if (r101 == 0) goto L_0x0f27
            goto L_0x0f29
        L_0x0f27:
            r80 = 0
        L_0x0f29:
            if (r101 == 0) goto L_0x0f2c
            goto L_0x0f2e
        L_0x0f2c:
            r81 = 0
        L_0x0f2e:
            if (r11 != 0) goto L_0x0f38
            long r86 = r58 & r46
            r54 = 0
            int r48 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r48 == 0) goto L_0x0f41
        L_0x0f38:
            if (r80 == 0) goto L_0x0f3f
            r44 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            long r58 = r58 | r44
            goto L_0x0f41
        L_0x0f3f:
            long r58 = r58 | r16
        L_0x0f41:
            if (r11 != 0) goto L_0x0f4b
            long r86 = r58 & r46
            r54 = 0
            int r48 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r48 == 0) goto L_0x0f5c
        L_0x0f4b:
            if (r81 == 0) goto L_0x0f55
            r86 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r58 = r58 | r86
            goto L_0x0f5c
        L_0x0f55:
            r52 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r58 = r58 | r52
        L_0x0f5c:
            r86 = 549755813888(0x8000000000, double:2.716154612436E-312)
            long r86 = r2 & r86
            r54 = 0
            int r48 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r48 != 0) goto L_0x0f72
            long r86 = r58 & r42
            int r88 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r88 == 0) goto L_0x0f70
            goto L_0x0f72
        L_0x0f70:
            r0 = 0
            goto L_0x0f76
        L_0x0f72:
            if (r101 == 0) goto L_0x0f75
            goto L_0x0f76
        L_0x0f75:
            r0 = 1
        L_0x0f76:
            r86 = 18014948265295872(0x40008000000000, double:1.780276379174756E-307)
            long r86 = r2 & r86
            int r88 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r88 != 0) goto L_0x0f8a
            long r86 = r58 & r46
            int r89 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r89 == 0) goto L_0x0f88
            goto L_0x0f8a
        L_0x0f88:
            r4 = 0
            goto L_0x0f8e
        L_0x0f8a:
            if (r101 == 0) goto L_0x0f8d
            goto L_0x0f8e
        L_0x0f8d:
            r4 = 1
        L_0x0f8e:
            r86 = 70918499991552(0x408000000000, double:3.50383945004186E-310)
            long r86 = r2 & r86
            int r89 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r89 != 0) goto L_0x0fa4
            long r86 = r58 & r46
            int r90 = (r86 > r54 ? 1 : (r86 == r54 ? 0 : -1))
            if (r90 == 0) goto L_0x0fa0
            goto L_0x0fa4
        L_0x0fa0:
            r86 = r0
            r0 = 0
            goto L_0x0fad
        L_0x0fa4:
            if (r101 == 0) goto L_0x0fa7
            goto L_0x0fa9
        L_0x0fa7:
            r85 = 1
        L_0x0fa9:
            r86 = r0
            r0 = r85
        L_0x0fad:
            if (r48 != 0) goto L_0x0fba
            long r90 = r58 & r46
            int r85 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r85 == 0) goto L_0x0fb6
            goto L_0x0fba
        L_0x0fb6:
            r84 = r0
            r0 = 0
            goto L_0x0fc3
        L_0x0fba:
            if (r101 == 0) goto L_0x0fbd
            goto L_0x0fbf
        L_0x0fbd:
            r82 = r84
        L_0x0fbf:
            r84 = r0
            r0 = r82
        L_0x0fc3:
            r90 = 549755944960(0x8000020000, double:2.716155260017E-312)
            long r90 = r2 & r90
            int r82 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r82 != 0) goto L_0x0fd8
            long r90 = r58 & r46
            int r85 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r85 == 0) goto L_0x0fd5
            goto L_0x0fd8
        L_0x0fd5:
            r165 = 0
            goto L_0x0fdf
        L_0x0fd8:
            if (r101 == 0) goto L_0x0fdb
            goto L_0x0fdd
        L_0x0fdb:
            r79 = 1
        L_0x0fdd:
            r165 = r79
        L_0x0fdf:
            r90 = 549822922752(0x8004000000, double:2.71648617428E-312)
            long r90 = r2 & r90
            int r79 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r79 != 0) goto L_0x0ff4
            long r90 = r58 & r46
            int r85 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r85 == 0) goto L_0x0ff1
            goto L_0x0ff4
        L_0x0ff1:
            r166 = 0
            goto L_0x0ffb
        L_0x0ff4:
            if (r101 == 0) goto L_0x0ff7
            goto L_0x0ff9
        L_0x0ff7:
            r83 = 1
        L_0x0ff9:
            r166 = r83
        L_0x0ffb:
            r90 = 549755814912(0x8000000400, double:2.716154617495E-312)
            long r90 = r2 & r90
            int r83 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r83 != 0) goto L_0x1015
            long r90 = r58 & r46
            int r85 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r85 == 0) goto L_0x100d
            goto L_0x1015
        L_0x100d:
            r52 = 34359738368(0x800000000, double:1.69759663277E-313)
            r167 = 0
            goto L_0x1021
        L_0x1015:
            if (r101 == 0) goto L_0x1018
            goto L_0x101a
        L_0x1018:
            r78 = 1
        L_0x101a:
            r167 = r78
            r52 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x1021:
            long r90 = r58 & r52
            int r78 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r78 == 0) goto L_0x102c
            int r78 = com.eternal.main.C2343R.mipmap.bluetooth_icon_grey
            r44 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            goto L_0x1030
        L_0x102c:
            r44 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            r78 = 0
        L_0x1030:
            long r90 = r58 & r44
            int r85 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r85 == 0) goto L_0x1039
            int r85 = com.eternal.main.C2343R.mipmap.wifi_icon
            goto L_0x103b
        L_0x1039:
            r85 = 0
        L_0x103b:
            long r90 = r58 & r16
            int r87 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r87 == 0) goto L_0x1044
            int r87 = com.eternal.main.C2343R.mipmap.wifi_icon_grey
            goto L_0x1046
        L_0x1044:
            r87 = 0
        L_0x1046:
            r90 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r90 = r58 & r90
            int r92 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r92 == 0) goto L_0x1054
            int r90 = com.eternal.main.C2343R.mipmap.bluetooth_icon
            goto L_0x1056
        L_0x1054:
            r90 = 0
        L_0x1056:
            if (r11 != 0) goto L_0x1066
            long r91 = r58 & r46
            int r93 = (r91 > r54 ? 1 : (r91 == r54 ? 0 : -1))
            if (r93 == 0) goto L_0x105f
            goto L_0x1066
        L_0x105f:
            r78 = r4
            r80 = r5
            r4 = 0
            r5 = 0
            goto L_0x1078
        L_0x1066:
            if (r81 == 0) goto L_0x1069
            goto L_0x106b
        L_0x1069:
            r90 = r78
        L_0x106b:
            if (r80 == 0) goto L_0x106e
            goto L_0x1070
        L_0x106e:
            r85 = r87
        L_0x1070:
            r78 = r4
            r80 = r5
            r5 = r85
            r4 = r90
        L_0x1078:
            r90 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r90 = r2 & r90
            r54 = 0
            int r81 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r81 != 0) goto L_0x108c
            long r90 = r58 & r46
            int r81 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r81 == 0) goto L_0x1089
            goto L_0x108c
        L_0x1089:
            r81 = r10
            goto L_0x1093
        L_0x108c:
            r81 = r10
            android.widget.TextView r10 = r1.deviceType
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r14)
        L_0x1093:
            if (r48 != 0) goto L_0x109b
            long r90 = r58 & r46
            int r10 = (r90 > r54 ? 1 : (r90 == r54 ? 0 : -1))
            if (r10 == 0) goto L_0x10c3
        L_0x109b:
            android.widget.TextView r10 = r1.deviceType
            r10.setTextColor(r7)
            android.widget.TextView r7 = r1.mboundView34
            r7.setTextColor(r6)
            android.widget.ImageView r6 = r1.mboundView48
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r6, r0)
            android.widget.TextView r0 = r1.tvDeviceName
            r0.setTextColor(r13)
            android.widget.TextView r0 = r1.tvTime
            r0.setTextColor(r8)
            android.widget.TextView r0 = r1.tvVpd
            r0.setTextColor(r12)
            android.widget.TextView r0 = r1.tvVpdUnit
            r0.setTextColor(r9)
            android.widget.TextView r0 = r1.tvWindSpeed
            r0.setTextColor(r15)
        L_0x10c3:
            if (r11 != 0) goto L_0x10cd
            long r6 = r58 & r46
            r8 = 0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x10d7
        L_0x10cd:
            android.widget.ImageView r0 = r1.ivBlueIcon
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r4)
            android.widget.ImageView r0 = r1.ivWifiIcon
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r5)
        L_0x10d7:
            r4 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x10e6
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x10ed
        L_0x10e6:
            android.widget.ImageView r0 = r1.ivBlueIcon
            r15 = r81
            r0.setVisibility(r15)
        L_0x10ed:
            r4 = 134217728(0x8000000, double:6.63123685E-316)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x10fb
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1107
        L_0x10fb:
            android.widget.ImageView r0 = r1.ivHumStatus
            r15 = r80
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
            android.widget.ImageView r0 = r1.mboundView38
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x1107:
            r4 = 32768(0x8000, double:1.61895E-319)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x1117
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x111e
        L_0x1117:
            android.widget.ImageView r0 = r1.ivTempStatus
            r15 = r61
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x111e:
            r4 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x112b
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1132
        L_0x112b:
            android.widget.ImageView r0 = r1.ivTypeIcon
            r15 = r60
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x1132:
            r4 = 4096(0x1000, double:2.0237E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x113f
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1146
        L_0x113f:
            android.widget.ImageView r0 = r1.ivVpdstatus
            r15 = r146
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x1146:
            r4 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x1153
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x115a
        L_0x1153:
            android.widget.ImageView r0 = r1.ivWifiIcon
            r15 = r147
            r0.setVisibility(r15)
        L_0x115a:
            r4 = 274877906944(0x4000000000, double:1.358077306218E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x116a
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1171
        L_0x116a:
            android.widget.ImageView r0 = r1.ivWindSpeed
            r15 = r148
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x1171:
            long r4 = r2 & r40
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x117d
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1184
        L_0x117d:
            android.widget.ImageView r0 = r1.ivWindStatus
            r15 = r77
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x1184:
            r4 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x1194
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x11a7
        L_0x1194:
            android.widget.LinearLayout r0 = r1.llContent
            r4 = r76
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r0, r4)
            android.view.View r0 = r1.mboundView47
            r15 = r149
            r0.setVisibility(r15)
            android.widget.ImageView r0 = r1.mboundView48
            r0.setVisibility(r15)
        L_0x11a7:
            r4 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x11b9
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x11c7
        L_0x11b9:
            android.widget.LinearLayout r0 = r1.mboundView10
            r15 = r151
            r0.setVisibility(r15)
            android.widget.LinearLayout r0 = r1.mboundView11
            r15 = r150
            r0.setVisibility(r15)
        L_0x11c7:
            r4 = 65536(0x10000, double:3.2379E-319)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x11d7
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x11de
        L_0x11d7:
            android.widget.ImageView r0 = r1.mboundView12
            r15 = r152
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r15)
        L_0x11de:
            r4 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x11eb
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x11f2
        L_0x11eb:
            android.widget.RelativeLayout r0 = r1.mboundView17
            r15 = r153
            r0.setVisibility(r15)
        L_0x11f2:
            long r4 = r2 & r32
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x11fe
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1205
        L_0x11fe:
            android.widget.RelativeLayout r0 = r1.mboundView21
            r15 = r154
            r0.setVisibility(r15)
        L_0x1205:
            long r4 = r2 & r28
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x1211
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x121d
        L_0x1211:
            android.widget.Space r0 = r1.mboundView26
            r15 = r155
            r0.setVisibility(r15)
            android.widget.RelativeLayout r0 = r1.mboundView27
            r0.setVisibility(r15)
        L_0x121d:
            r4 = 4
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x122c
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1233
        L_0x122c:
            android.widget.TextView r0 = r1.mboundView28
            r4 = r75
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x1233:
            long r4 = r58 & r46
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x12a3
            android.widget.LinearLayout r4 = r1.mboundView3
            r13 = r74
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r13, r5)
            android.widget.LinearLayout r4 = r1.mboundView44
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r13, r5)
            android.widget.ImageView r4 = r1.mboundView48
            r5 = r162
            r6 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort0
            r5 = r163
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort1
            r15 = r73
            r4.setPort(r15)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort2
            r5 = r156
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort3
            r5 = r157
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort4
            r5 = r159
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort5
            r5 = r160
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort6
            r5 = r161
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort7
            r5 = r72
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort8
            r5 = r71
            r4.setPort(r5)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPort9
            r14 = r70
            r4.setPort(r14)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPortAll
            r5 = r158
            r4.setPort(r5)
            android.widget.TextView r4 = r1.tvDel
            r12 = r69
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r12, r5)
        L_0x12a3:
            long r4 = r2 & r22
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x12ad
            if (r0 == 0) goto L_0x12b9
        L_0x12ad:
            android.widget.Space r4 = r1.mboundView32
            r15 = r68
            r4.setVisibility(r15)
            android.widget.RelativeLayout r4 = r1.rlPower
            r4.setVisibility(r15)
        L_0x12b9:
            r4 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x12c4
            if (r0 == 0) goto L_0x12cb
        L_0x12c4:
            android.widget.TextView r4 = r1.mboundView34
            r5 = r67
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
        L_0x12cb:
            r4 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x12d4
            if (r0 == 0) goto L_0x12e0
        L_0x12d4:
            android.widget.LinearLayout r4 = r1.mboundView35
            r15 = r66
            r4.setVisibility(r15)
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r4.setVisibility(r15)
        L_0x12e0:
            long r4 = r2 & r38
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x12ea
            if (r0 == 0) goto L_0x12f6
        L_0x12ea:
            android.widget.TextView r4 = r1.mboundView37
            r15 = r65
            r4.setTextColor(r15)
            android.widget.TextView r4 = r1.tvHumUnit
            r4.setTextColor(r15)
        L_0x12f6:
            r4 = 2048(0x800, double:1.0118E-320)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1301
            if (r0 == 0) goto L_0x1326
        L_0x1301:
            android.widget.Space r4 = r1.mboundView40
            r15 = r64
            r4.setVisibility(r15)
            android.view.View r4 = r1.mboundView41
            r15 = r63
            r4.setVisibility(r15)
            com.eternal.widget.ExpandableLayout r4 = r1.mboundView42
            r15 = r164
            com.eternal.widget.ExpandableLayout.setExpanded((com.eternal.widget.ExpandableLayout) r4, (boolean) r15)
            int r4 = getBuildSdkInt()
            r5 = 11
            if (r4 < r5) goto L_0x1326
            android.widget.ImageView r4 = r1.mboundView48
            r15 = r144
            float r5 = (float) r15
            r4.setRotation(r5)
        L_0x1326:
            long r4 = r2 & r36
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1330
            if (r0 == 0) goto L_0x1342
        L_0x1330:
            android.widget.LinearLayout r4 = r1.mboundView44
            r15 = r62
            r4.setVisibility(r15)
            com.eternal.main.databinding.LayoutPortBinding r4 = r1.rlPortAll
            android.view.View r4 = r4.getRoot()
            r15 = r139
            r4.setVisibility(r15)
        L_0x1342:
            r4 = 8192(0x2000, double:4.0474E-320)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x134d
            if (r0 == 0) goto L_0x1354
        L_0x134d:
            android.widget.ImageView r4 = r1.mboundView6
            r15 = r57
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r4, r15)
        L_0x1354:
            long r4 = r2 & r24
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x135c
            if (r0 == 0) goto L_0x1363
        L_0x135c:
            android.widget.ImageView r4 = r1.mboundView6
            r15 = r56
            r4.setVisibility(r15)
        L_0x1363:
            r4 = -9223372036854775808
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x136c
            if (r0 == 0) goto L_0x1373
        L_0x136c:
            android.widget.TextView r4 = r1.tvDeviceName
            r5 = r51
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
        L_0x1373:
            long r4 = r2 & r30
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x137b
            if (r0 == 0) goto L_0x1387
        L_0x137b:
            android.widget.TextView r4 = r1.tvHum
            r15 = r123
            r4.setTextColor(r15)
            android.widget.TextView r4 = r1.tvHum2
            r4.setTextColor(r15)
        L_0x1387:
            r4 = 8589934592(0x200000000, double:4.243991582E-314)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1395
            if (r0 == 0) goto L_0x13a1
        L_0x1395:
            android.widget.TextView r4 = r1.tvHum
            r5 = r124
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textSmallDecimal(r4, r5)
            android.widget.TextView r4 = r1.tvHum2
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textSmallDecimal(r4, r5)
        L_0x13a1:
            long r4 = r2 & r18
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13ab
            if (r0 == 0) goto L_0x13b2
        L_0x13ab:
            android.widget.TextView r4 = r1.tvTempUnit
            r5 = r125
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
        L_0x13b2:
            r4 = 2
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13bb
            if (r0 == 0) goto L_0x13c2
        L_0x13bb:
            android.widget.TextView r4 = r1.tvTempUnit
            r11 = r145
            r4.setTextColor(r11)
        L_0x13c2:
            long r4 = r2 & r20
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13ca
            if (r0 == 0) goto L_0x13d1
        L_0x13ca:
            android.widget.TextView r4 = r1.tvTime
            r5 = r140
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
        L_0x13d1:
            r4 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13dd
            if (r0 == 0) goto L_0x13e4
        L_0x13dd:
            android.widget.TextView r4 = r1.tvTmp
            r15 = r126
            r4.setTextColor(r15)
        L_0x13e4:
            long r4 = r2 & r16
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13ec
            if (r0 == 0) goto L_0x13f3
        L_0x13ec:
            android.widget.TextView r4 = r1.tvTmp
            r5 = r141
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textSmallDecimal(r4, r5)
        L_0x13f3:
            r4 = 2097152(0x200000, double:1.0361308E-317)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x13fd
            if (r0 == 0) goto L_0x1404
        L_0x13fd:
            android.widget.TextView r4 = r1.tvVpd
            r5 = r127
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textSmallDecimal(r4, r5)
        L_0x1404:
            r4 = 524288(0x80000, double:2.590327E-318)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x140e
            if (r0 == 0) goto L_0x1415
        L_0x140e:
            android.widget.TextView r4 = r1.tvWindSpeed
            r5 = r128
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
        L_0x1415:
            r4 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1421
            if (r0 == 0) goto L_0x1428
        L_0x1421:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r15 = r129
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setDistance(r4, r15)
        L_0x1428:
            r4 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1434
            if (r0 == 0) goto L_0x143b
        L_0x1434:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r15 = r130
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setHigh(r4, r15)
        L_0x143b:
            if (r88 != 0) goto L_0x143f
            if (r0 == 0) goto L_0x1446
        L_0x143f:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r5 = r78
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setHighClose(r4, r5)
        L_0x1446:
            r4 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1454
            if (r0 == 0) goto L_0x145b
        L_0x1454:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r15 = r131
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setLow(r4, r15)
        L_0x145b:
            if (r89 != 0) goto L_0x145f
            if (r0 == 0) goto L_0x1466
        L_0x145f:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r5 = r84
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setLowClose(r4, r5)
        L_0x1466:
            r4 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1471
            if (r0 == 0) goto L_0x1478
        L_0x1471:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r15 = r132
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setMin(r4, r15)
        L_0x1478:
            if (r83 != 0) goto L_0x147c
            if (r0 == 0) goto L_0x1483
        L_0x147c:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r5 = r167
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setNowClose(r4, r5)
        L_0x1483:
            r4 = 16384(0x4000, double:8.0948E-320)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x148e
            if (r0 == 0) goto L_0x1495
        L_0x148e:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r15 = r133
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setProgress(r4, r15)
        L_0x1495:
            r4 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x14a1
            if (r0 == 0) goto L_0x14a8
        L_0x14a1:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsHum
            r5 = r134
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setUnit(r4, r5)
        L_0x14a8:
            r4 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x14b1
            if (r0 == 0) goto L_0x14b8
        L_0x14b1:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r15 = r135
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setDistance(r4, r15)
        L_0x14b8:
            long r4 = r2 & r34
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x14c0
            if (r0 == 0) goto L_0x14c7
        L_0x14c0:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r15 = r136
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setHigh(r4, r15)
        L_0x14c7:
            if (r79 != 0) goto L_0x14cb
            if (r0 == 0) goto L_0x14d2
        L_0x14cb:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r5 = r166
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setHighClose(r4, r5)
        L_0x14d2:
            r4 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r4 = r4 & r2
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x14dd
            if (r0 == 0) goto L_0x14e4
        L_0x14dd:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r15 = r137
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setLow(r4, r15)
        L_0x14e4:
            if (r48 != 0) goto L_0x14ec
            long r4 = r58 & r42
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x14f3
        L_0x14ec:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r5 = r86
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setLowClose(r4, r5)
        L_0x14f3:
            r4 = 536870912(0x20000000, double:2.652494739E-315)
            long r4 = r4 & r2
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x14fd
            if (r0 == 0) goto L_0x1504
        L_0x14fd:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r15 = r142
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setMin(r4, r15)
        L_0x1504:
            if (r82 != 0) goto L_0x1508
            if (r0 == 0) goto L_0x150f
        L_0x1508:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r5 = r165
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setNowClose(r4, r5)
        L_0x150f:
            long r4 = r2 & r26
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x1519
            if (r0 == 0) goto L_0x1520
        L_0x1519:
            com.eternal.widget.guqiang.ValueRangeSlider r4 = r1.vrsTmp
            r15 = r138
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setProgress(r4, r15)
        L_0x1520:
            r4 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r2 = r2 & r4
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x1529
            if (r0 == 0) goto L_0x1530
        L_0x1529:
            com.eternal.widget.guqiang.ValueRangeSlider r0 = r1.vrsTmp
            r2 = r143
            com.eternal.widget.guqiang.ValueRangeSliderAdapter.setUnit(r0, r2)
        L_0x1530:
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPortAll
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort0
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort2
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort4
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort6
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort8
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort1
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort3
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort5
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort7
            executeBindingsOn(r0)
            com.eternal.main.databinding.LayoutPortBinding r0 = r1.rlPort9
            executeBindingsOn(r0)
            return
        L_0x1568:
            r0 = move-exception
            monitor-exit(r169)     // Catch:{ all -> 0x1568 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.databinding.ItemMainBindingImpl.executeBindings():void");
    }
}
