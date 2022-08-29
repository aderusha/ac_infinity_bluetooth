package com.eternal.main.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.eternal.main.C2323BR;
import com.eternal.main.C2343R;
import com.eternal.main.model.PortModel;

public class LayoutPortBindingImpl extends LayoutPortBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2343R.C2346id.rl_port_wind, 10);
    }

    public LayoutPortBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private LayoutPortBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, objArr[6], objArr[7], objArr[1], objArr[10], objArr[5], objArr[4]);
        this.mDirtyFlags = -1;
        this.ivPortWindSpeed.setTag((Object) null);
        this.ivPortWindStatus.setTag((Object) null);
        this.ivTypeIcon.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = objArr[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[3];
        this.mboundView3 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = objArr[8];
        this.mboundView8 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = objArr[9];
        this.mboundView9 = textView4;
        textView4.setTag((Object) null);
        this.rlWindStatus.setTag((Object) null);
        this.tvPortWindSpeed.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
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
        if (C2323BR.port == i) {
            setPort((PortModel) obj);
        } else if (C2323BR.open != i) {
            return false;
        } else {
            setOpen((Boolean) obj);
        }
        return true;
    }

    public void setPort(PortModel portModel) {
        this.mPort = portModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(C2323BR.port);
        super.requestRebind();
    }

    public void setOpen(Boolean bool) {
        this.mOpen = bool;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangePortVisibility((ObservableBoolean) obj, i2);
            case 1:
                return onChangePortPowerOff((ObservableBoolean) obj, i2);
            case 2:
                return onChangePortWorkTypeVisibility((ObservableBoolean) obj, i2);
            case 3:
                return onChangePortName((ObservableField) obj, i2);
            case 4:
                return onChangePortIsConnet((ObservableBoolean) obj, i2);
            case 5:
                return onChangePortFanType((ObservableInt) obj, i2);
            case 6:
                return onChangePortDashVisibility((ObservableBoolean) obj, i2);
            case 7:
                return onChangePortIconVisible((ObservableBoolean) obj, i2);
            case 8:
                return onChangePortFanState((ObservableInt) obj, i2);
            case 9:
                return onChangePortWorkType((ObservableField) obj, i2);
            case 10:
                return onChangePortPowerVisibility((ObservableBoolean) obj, i2);
            case 11:
                return onChangePortFanSize((ObservableField) obj, i2);
            case 12:
                return onChangePortFanVisibility((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangePortVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePortPowerOff(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangePortWorkTypeVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangePortName(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangePortIsConnet(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangePortFanType(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangePortDashVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangePortIconVisible(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangePortFanState(ObservableInt observableInt, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangePortWorkType(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangePortPowerVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangePortFanSize(ObservableField<String> observableField, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangePortFanVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r63 = this;
            r1 = r63
            monitor-enter(r63)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0410 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0410 }
            monitor-exit(r63)     // Catch:{ all -> 0x0410 }
            com.eternal.main.model.PortModel r0 = r1.mPort
            r6 = 49151(0xbfff, double:2.4284E-319)
            long r6 = r6 & r2
            r16 = 41024(0xa040, double:2.02685E-319)
            r18 = 40992(0xa020, double:2.02527E-319)
            r20 = 41088(0xa080, double:2.03E-319)
            r22 = 40968(0xa008, double:2.0241E-319)
            r24 = 41216(0xa100, double:2.03634E-319)
            r26 = 40964(0xa004, double:2.0239E-319)
            r28 = 40962(0xa002, double:2.0238E-319)
            r30 = 40976(0xa010, double:2.0245E-319)
            r32 = 40961(0xa001, double:2.02374E-319)
            r34 = 131072(0x20000, double:6.47582E-319)
            r36 = 40960(0xa000, double:2.0237E-319)
            r38 = 65536(0x10000, double:3.2379E-319)
            r40 = 0
            r8 = 0
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x02ff
            long r6 = r2 & r36
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0046
            if (r0 == 0) goto L_0x0046
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onClick
            goto L_0x0048
        L_0x0046:
            r6 = r40
        L_0x0048:
            long r43 = r2 & r32
            int r9 = (r43 > r4 ? 1 : (r43 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0073
            if (r0 == 0) goto L_0x0053
            androidx.databinding.ObservableBoolean r10 = r0.visibility
            goto L_0x0055
        L_0x0053:
            r10 = r40
        L_0x0055:
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x005f
            boolean r10 = r10.get()
            goto L_0x0060
        L_0x005f:
            r10 = 0
        L_0x0060:
            if (r9 == 0) goto L_0x006d
            if (r10 == 0) goto L_0x0068
            r45 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x006b
        L_0x0068:
            r45 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x006b:
            long r2 = r2 | r45
        L_0x006d:
            if (r10 == 0) goto L_0x0070
            goto L_0x0073
        L_0x0070:
            r9 = 8
            goto L_0x0074
        L_0x0073:
            r9 = 0
        L_0x0074:
            long r10 = r2 & r28
            int r45 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x00b2
            if (r0 == 0) goto L_0x007f
            androidx.databinding.ObservableBoolean r10 = r0.powerOff
            goto L_0x0081
        L_0x007f:
            r10 = r40
        L_0x0081:
            r11 = 1
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x008c
            boolean r10 = r10.get()
            goto L_0x008d
        L_0x008c:
            r10 = 0
        L_0x008d:
            if (r45 == 0) goto L_0x009a
            if (r10 == 0) goto L_0x0095
            r45 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x0098
        L_0x0095:
            r45 = 4194304(0x400000, double:2.0722615E-317)
        L_0x0098:
            long r2 = r2 | r45
        L_0x009a:
            if (r10 == 0) goto L_0x00a5
            android.widget.TextView r10 = r1.mboundView8
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.main.C2343R.string.power_off
            goto L_0x00ad
        L_0x00a5:
            android.widget.TextView r10 = r1.mboundView8
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.main.C2343R.string.power_on
        L_0x00ad:
            java.lang.String r10 = r10.getString(r11)
            goto L_0x00b4
        L_0x00b2:
            r10 = r40
        L_0x00b4:
            long r45 = r2 & r26
            int r11 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00df
            if (r0 == 0) goto L_0x00bf
            androidx.databinding.ObservableBoolean r8 = r0.workTypeVisibility
            goto L_0x00c1
        L_0x00bf:
            r8 = r40
        L_0x00c1:
            r12 = 2
            r1.updateRegistration((int) r12, (androidx.databinding.Observable) r8)
            if (r8 == 0) goto L_0x00cc
            boolean r8 = r8.get()
            goto L_0x00cd
        L_0x00cc:
            r8 = 0
        L_0x00cd:
            if (r11 == 0) goto L_0x00d9
            if (r8 == 0) goto L_0x00d5
            r11 = 536870912(0x20000000, double:2.652494739E-315)
            goto L_0x00d8
        L_0x00d5:
            r11 = 268435456(0x10000000, double:1.32624737E-315)
        L_0x00d8:
            long r2 = r2 | r11
        L_0x00d9:
            if (r8 == 0) goto L_0x00dc
            goto L_0x00df
        L_0x00dc:
            r8 = 8
            goto L_0x00e0
        L_0x00df:
            r8 = 0
        L_0x00e0:
            long r11 = r2 & r22
            int r13 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00fa
            if (r0 == 0) goto L_0x00eb
            androidx.databinding.ObservableField<java.lang.String> r11 = r0.name
            goto L_0x00ed
        L_0x00eb:
            r11 = r40
        L_0x00ed:
            r12 = 3
            r1.updateRegistration((int) r12, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x00fa
            java.lang.Object r11 = r11.get()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x00fc
        L_0x00fa:
            r11 = r40
        L_0x00fc:
            long r12 = r2 & r30
            int r48 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x016e
            if (r0 == 0) goto L_0x0107
            androidx.databinding.ObservableBoolean r12 = r0.isConnet
            goto L_0x0109
        L_0x0107:
            r12 = r40
        L_0x0109:
            r13 = 4
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r12)
            if (r12 == 0) goto L_0x0114
            boolean r12 = r12.get()
            goto L_0x0115
        L_0x0114:
            r12 = 0
        L_0x0115:
            if (r48 == 0) goto L_0x0140
            if (r12 == 0) goto L_0x012d
            long r2 = r2 | r34
            r48 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r48
            r48 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r48
            r48 = 34359738368(0x800000000, double:1.69759663277E-313)
            goto L_0x013e
        L_0x012d:
            long r2 = r2 | r38
            r48 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r48
            r48 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r48
            r48 = 17179869184(0x400000000, double:8.4879831639E-314)
        L_0x013e:
            long r2 = r2 | r48
        L_0x0140:
            android.widget.TextView r13 = r1.tvPortWindSpeed
            if (r12 == 0) goto L_0x0147
            int r14 = com.eternal.main.C2343R.C2344color.color_FFFFFF
            goto L_0x0149
        L_0x0147:
            int r14 = com.eternal.main.C2343R.C2344color.color_707070
        L_0x0149:
            int r13 = getColorFromResource(r13, r14)
            android.widget.TextView r14 = r1.mboundView3
            if (r12 == 0) goto L_0x0154
            int r15 = com.eternal.main.C2343R.C2344color.white
            goto L_0x0156
        L_0x0154:
            int r15 = com.eternal.main.C2343R.C2344color.color_707070
        L_0x0156:
            int r14 = getColorFromResource(r14, r15)
            if (r12 == 0) goto L_0x0165
            android.widget.TextView r15 = r1.mboundView8
            int r7 = com.eternal.main.C2343R.C2344color.color_FFFFFF
            int r7 = getColorFromResource(r15, r7)
            goto L_0x0172
        L_0x0165:
            android.widget.TextView r7 = r1.mboundView8
            int r15 = com.eternal.main.C2343R.C2344color.color_707070
            int r7 = getColorFromResource(r7, r15)
            goto L_0x0172
        L_0x016e:
            r7 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0172:
            long r51 = r2 & r18
            int r15 = (r51 > r4 ? 1 : (r51 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x018a
            if (r0 == 0) goto L_0x017d
            androidx.databinding.ObservableInt r15 = r0.fanType
            goto L_0x017f
        L_0x017d:
            r15 = r40
        L_0x017f:
            r4 = 5
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x018a
            int r4 = r15.get()
            goto L_0x018b
        L_0x018a:
            r4 = 0
        L_0x018b:
            long r53 = r2 & r16
            r51 = 0
            int r5 = (r53 > r51 ? 1 : (r53 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01bd
            if (r0 == 0) goto L_0x019a
            androidx.databinding.ObservableBoolean r15 = r0.dashVisibility
            r53 = r4
            goto L_0x019e
        L_0x019a:
            r53 = r4
            r15 = r40
        L_0x019e:
            r4 = 6
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x01a9
            boolean r4 = r15.get()
            goto L_0x01aa
        L_0x01a9:
            r4 = 0
        L_0x01aa:
            if (r5 == 0) goto L_0x01b7
            if (r4 == 0) goto L_0x01b2
            r54 = 524288(0x80000, double:2.590327E-318)
            goto L_0x01b5
        L_0x01b2:
            r54 = 262144(0x40000, double:1.295163E-318)
        L_0x01b5:
            long r2 = r2 | r54
        L_0x01b7:
            if (r4 == 0) goto L_0x01ba
            goto L_0x01bf
        L_0x01ba:
            r4 = 8
            goto L_0x01c0
        L_0x01bd:
            r53 = r4
        L_0x01bf:
            r4 = 0
        L_0x01c0:
            long r54 = r2 & r20
            r51 = 0
            int r5 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01f6
            if (r0 == 0) goto L_0x01cf
            androidx.databinding.ObservableBoolean r15 = r0.iconVisible
            r54 = r4
            goto L_0x01d3
        L_0x01cf:
            r54 = r4
            r15 = r40
        L_0x01d3:
            r4 = 7
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x01de
            boolean r4 = r15.get()
            goto L_0x01df
        L_0x01de:
            r4 = 0
        L_0x01df:
            if (r5 == 0) goto L_0x01f0
            if (r4 == 0) goto L_0x01e9
            r55 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x01ee
        L_0x01e9:
            r55 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x01ee:
            long r2 = r2 | r55
        L_0x01f0:
            if (r4 == 0) goto L_0x01f3
            goto L_0x01f8
        L_0x01f3:
            r4 = 8
            goto L_0x01f9
        L_0x01f6:
            r54 = r4
        L_0x01f8:
            r4 = 0
        L_0x01f9:
            long r55 = r2 & r24
            r51 = 0
            int r5 = (r55 > r51 ? 1 : (r55 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0214
            if (r0 == 0) goto L_0x0206
            androidx.databinding.ObservableInt r5 = r0.fanState
            goto L_0x0208
        L_0x0206:
            r5 = r40
        L_0x0208:
            r15 = 8
            r1.updateRegistration((int) r15, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0216
            int r5 = r5.get()
            goto L_0x0217
        L_0x0214:
            r15 = 8
        L_0x0216:
            r5 = 0
        L_0x0217:
            r48 = 41472(0xa200, double:2.049E-319)
            long r55 = r2 & r48
            r50 = 0
            int r57 = (r55 > r50 ? 1 : (r55 == r50 ? 0 : -1))
            if (r57 == 0) goto L_0x023b
            if (r0 == 0) goto L_0x0229
            androidx.databinding.ObservableField<java.lang.String> r15 = r0.workType
            r55 = r4
            goto L_0x022d
        L_0x0229:
            r55 = r4
            r15 = r40
        L_0x022d:
            r4 = 9
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x023d
            java.lang.Object r4 = r15.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x023f
        L_0x023b:
            r55 = r4
        L_0x023d:
            r4 = r40
        L_0x023f:
            r46 = 41984(0xa400, double:2.0743E-319)
            long r56 = r2 & r46
            r51 = 0
            int r15 = (r56 > r51 ? 1 : (r56 == r51 ? 0 : -1))
            r56 = r4
            if (r15 == 0) goto L_0x027b
            if (r0 == 0) goto L_0x0253
            androidx.databinding.ObservableBoolean r4 = r0.powerVisibility
            r57 = r5
            goto L_0x0257
        L_0x0253:
            r57 = r5
            r4 = r40
        L_0x0257:
            r5 = 10
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0263
            boolean r4 = r4.get()
            goto L_0x0264
        L_0x0263:
            r4 = 0
        L_0x0264:
            if (r15 == 0) goto L_0x0275
            if (r4 == 0) goto L_0x026e
            r58 = 137438953472(0x2000000000, double:6.7903865311E-313)
            goto L_0x0273
        L_0x026e:
            r58 = 68719476736(0x1000000000, double:3.39519326554E-313)
        L_0x0273:
            long r2 = r2 | r58
        L_0x0275:
            if (r4 == 0) goto L_0x0278
            goto L_0x027d
        L_0x0278:
            r4 = 8
            goto L_0x027e
        L_0x027b:
            r57 = r5
        L_0x027d:
            r4 = 0
        L_0x027e:
            r43 = 43008(0xa800, double:2.1249E-319)
            long r58 = r2 & r43
            r51 = 0
            int r5 = (r58 > r51 ? 1 : (r58 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x029e
            if (r0 == 0) goto L_0x028e
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.fanSize
            goto L_0x0290
        L_0x028e:
            r5 = r40
        L_0x0290:
            r15 = 11
            r1.updateRegistration((int) r15, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x029e
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x02a0
        L_0x029e:
            r5 = r40
        L_0x02a0:
            r41 = 45056(0xb000, double:2.22606E-319)
            long r58 = r2 & r41
            r51 = 0
            int r15 = (r58 > r51 ? 1 : (r58 == r51 ? 0 : -1))
            if (r15 == 0) goto L_0x02e6
            if (r0 == 0) goto L_0x02b2
            androidx.databinding.ObservableBoolean r0 = r0.fanVisibility
            r58 = r4
            goto L_0x02b6
        L_0x02b2:
            r58 = r4
            r0 = r40
        L_0x02b6:
            r4 = 12
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x02c2
            boolean r0 = r0.get()
            goto L_0x02c3
        L_0x02c2:
            r0 = 0
        L_0x02c3:
            if (r15 == 0) goto L_0x02d0
            if (r0 == 0) goto L_0x02cb
            r59 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x02ce
        L_0x02cb:
            r59 = 16777216(0x1000000, double:8.289046E-317)
        L_0x02ce:
            long r2 = r2 | r59
        L_0x02d0:
            if (r0 == 0) goto L_0x02d5
            r50 = 0
            goto L_0x02d7
        L_0x02d5:
            r50 = 8
        L_0x02d7:
            r61 = r5
            r5 = r10
            r15 = r11
            r62 = r50
            r0 = r53
            r40 = r54
            r4 = r55
            r50 = r58
            goto L_0x02f6
        L_0x02e6:
            r58 = r4
            r61 = r5
            r5 = r10
            r15 = r11
            r0 = r53
            r40 = r54
            r4 = r55
            r50 = r58
            r62 = 0
        L_0x02f6:
            r11 = r6
            r10 = r9
            r6 = r56
            r9 = r8
            r8 = r7
            r7 = r57
            goto L_0x0315
        L_0x02ff:
            r5 = r40
            r6 = r5
            r11 = r6
            r15 = r11
            r61 = r15
            r0 = 0
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r40 = 0
            r50 = 0
            r62 = 0
        L_0x0315:
            long r38 = r2 & r38
            r51 = 0
            int r53 = (r38 > r51 ? 1 : (r38 == r51 ? 0 : -1))
            if (r53 == 0) goto L_0x0320
            int r38 = com.eternal.main.C2343R.mipmap.level_icon_grey
            goto L_0x0322
        L_0x0320:
            r38 = 0
        L_0x0322:
            long r34 = r2 & r34
            int r39 = (r34 > r51 ? 1 : (r34 == r51 ? 0 : -1))
            if (r39 == 0) goto L_0x032b
            int r34 = com.eternal.main.C2343R.mipmap.level_icon_normal
            goto L_0x032d
        L_0x032b:
            r34 = 0
        L_0x032d:
            long r30 = r2 & r30
            int r35 = (r30 > r51 ? 1 : (r30 == r51 ? 0 : -1))
            if (r35 == 0) goto L_0x033a
            if (r12 == 0) goto L_0x0337
            r38 = r34
        L_0x0337:
            r12 = r38
            goto L_0x033b
        L_0x033a:
            r12 = 0
        L_0x033b:
            r30 = r5
            if (r35 == 0) goto L_0x0353
            android.widget.ImageView r5 = r1.ivPortWindSpeed
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r5, r12)
            android.widget.TextView r5 = r1.mboundView3
            r5.setTextColor(r14)
            android.widget.TextView r5 = r1.mboundView8
            r5.setTextColor(r8)
            android.widget.TextView r5 = r1.tvPortWindSpeed
            r5.setTextColor(r13)
        L_0x0353:
            long r12 = r2 & r24
            r24 = 0
            int r5 = (r12 > r24 ? 1 : (r12 == r24 ? 0 : -1))
            if (r5 == 0) goto L_0x0360
            android.widget.ImageView r5 = r1.ivPortWindStatus
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r5, r7)
        L_0x0360:
            long r7 = r2 & r20
            int r5 = (r7 > r24 ? 1 : (r7 == r24 ? 0 : -1))
            if (r5 == 0) goto L_0x036b
            android.widget.ImageView r5 = r1.ivTypeIcon
            r5.setVisibility(r4)
        L_0x036b:
            long r4 = r2 & r18
            int r7 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r7 == 0) goto L_0x0376
            android.widget.ImageView r4 = r1.ivTypeIcon
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r4, r0)
        L_0x0376:
            long r4 = r2 & r32
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0381
            android.widget.LinearLayout r0 = r1.mboundView0
            r0.setVisibility(r10)
        L_0x0381:
            long r4 = r2 & r36
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x038d
            android.widget.LinearLayout r0 = r1.mboundView0
            r4 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r11, r4)
        L_0x038d:
            long r4 = r2 & r22
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0398
            android.widget.TextView r0 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        L_0x0398:
            r4 = 41472(0xa200, double:2.049E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03a5
            android.widget.TextView r0 = r1.mboundView3
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x03a5:
            long r4 = r2 & r26
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03b0
            android.widget.TextView r0 = r1.mboundView3
            r0.setVisibility(r9)
        L_0x03b0:
            long r4 = r2 & r28
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03bd
            android.widget.TextView r0 = r1.mboundView8
            r10 = r30
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x03bd:
            r4 = 41984(0xa400, double:2.0743E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03cc
            android.widget.TextView r0 = r1.mboundView8
            r4 = r50
            r0.setVisibility(r4)
        L_0x03cc:
            r4 = 32768(0x8000, double:1.61895E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03df
            android.widget.TextView r0 = r1.mboundView9
            int r4 = com.eternal.main.C2343R.C2344color.color_BFBFBF
            int r4 = getColorFromResource(r0, r4)
            r0.setTextColor(r4)
        L_0x03df:
            long r4 = r2 & r16
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x03ec
            android.widget.TextView r0 = r1.mboundView9
            r4 = r40
            r0.setVisibility(r4)
        L_0x03ec:
            r4 = 45056(0xb000, double:2.22606E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0400
            android.widget.RelativeLayout r0 = r1.rlWindStatus
            r4 = r62
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.tvPortWindSpeed
            r0.setVisibility(r4)
        L_0x0400:
            r4 = 43008(0xa800, double:2.1249E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r24 ? 1 : (r2 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x040f
            android.widget.TextView r0 = r1.tvPortWindSpeed
            r5 = r61
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r5)
        L_0x040f:
            return
        L_0x0410:
            r0 = move-exception
            monitor-exit(r63)     // Catch:{ all -> 0x0410 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.databinding.LayoutPortBindingImpl.executeBindings():void");
    }
}
