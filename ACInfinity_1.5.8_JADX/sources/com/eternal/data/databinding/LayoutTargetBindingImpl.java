package com.eternal.data.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.data.C1765BR;
import com.eternal.data.C1835R;
import com.eternal.data.TargetModel;

public class LayoutTargetBindingImpl extends LayoutTargetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener editTargetandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final Space mboundView12;
    private final ImageView mboundView13;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final ConstraintLayout mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1835R.C1838id.tv_analysis, 14);
    }

    public LayoutTargetBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private LayoutTargetBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, objArr[9], objArr[8], objArr[14], objArr[7], objArr[6]);
        this.editTargetandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LayoutTargetBindingImpl.this.editTarget);
                TargetModel targetModel = LayoutTargetBindingImpl.this.mTargetModel;
                boolean z = true;
                if (targetModel != null) {
                    MutableLiveData<String> mutableLiveData = targetModel.targetTxt;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        this.editTarget.setTag((Object) null);
        this.ivClose.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[1];
        this.mboundView1 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = objArr[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[11];
        this.mboundView11 = textView2;
        textView2.setTag((Object) null);
        Space space = objArr[12];
        this.mboundView12 = space;
        space.setTag((Object) null);
        ImageView imageView = objArr[13];
        this.mboundView13 = imageView;
        imageView.setTag((Object) null);
        TextView textView3 = objArr[2];
        this.mboundView2 = textView3;
        textView3.setTag((Object) null);
        ImageView imageView2 = objArr[3];
        this.mboundView3 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView4 = objArr[4];
        this.mboundView4 = textView4;
        textView4.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[5];
        this.mboundView5 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.tvScore.setTag((Object) null);
        this.tvTargetTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
        if (C1765BR.targetModel != i) {
            return false;
        }
        setTargetModel((TargetModel) obj);
        return true;
    }

    public void setTargetModel(TargetModel targetModel) {
        this.mTargetModel = targetModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(C1765BR.targetModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeTargetModelTargetTxt((MutableLiveData) obj, i2);
            case 1:
                return onChangeTargetModelExpand((MutableLiveData) obj, i2);
            case 2:
                return onChangeTargetModelUnitTxt((MutableLiveData) obj, i2);
            case 3:
                return onChangeTargetModelScoreTxt((MutableLiveData) obj, i2);
            case 4:
                return onChangeTargetModelTitleTxt((MutableLiveData) obj, i2);
            case 5:
                return onChangeTargetModelTitleTxt2((MutableLiveData) obj, i2);
            case 6:
                return onChangeTargetModelDescTxt((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeTargetModelTargetTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeTargetModelExpand(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeTargetModelUnitTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeTargetModelScoreTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeTargetModelTitleTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeTargetModelTitleTxt2(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeTargetModelDescTxt(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1765BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00fc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r62 = this;
            r1 = r62
            monitor-enter(r62)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0323 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0323 }
            monitor-exit(r62)     // Catch:{ all -> 0x0323 }
            com.eternal.data.TargetModel r0 = r1.mTargetModel
            r6 = 511(0x1ff, double:2.525E-321)
            long r6 = r6 & r2
            r12 = 262144(0x40000, double:1.295163E-318)
            r14 = 16384(0x4000, double:8.0948E-320)
            r16 = 131072(0x20000, double:6.47582E-319)
            r18 = 8192(0x2000, double:4.0474E-320)
            r20 = 416(0x1a0, double:2.055E-321)
            r22 = 400(0x190, double:1.976E-321)
            r24 = 392(0x188, double:1.937E-321)
            r26 = 4194304(0x400000, double:2.0722615E-317)
            r28 = 384(0x180, double:1.897E-321)
            r30 = 524288(0x80000, double:2.590327E-318)
            r32 = 388(0x184, double:1.917E-321)
            r34 = 385(0x181, double:1.9E-321)
            r10 = 2
            r38 = 450(0x1c2, double:2.223E-321)
            r40 = 386(0x182, double:1.907E-321)
            r11 = 1
            r43 = 0
            r8 = 0
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0197
            long r6 = r2 & r34
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0051
            if (r0 == 0) goto L_0x0043
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.targetTxt
            goto L_0x0045
        L_0x0043:
            r6 = r43
        L_0x0045:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x0051
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0053
        L_0x0051:
            r6 = r43
        L_0x0053:
            long r46 = r2 & r40
            int r7 = (r46 > r4 ? 1 : (r46 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x00cd
            if (r0 == 0) goto L_0x005e
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r9 = r0.expand
            goto L_0x0060
        L_0x005e:
            r9 = r43
        L_0x0060:
            r1.updateLiveDataRegistration(r11, r9)
            if (r9 == 0) goto L_0x006c
            java.lang.Object r46 = r9.getValue()
            java.lang.Integer r46 = (java.lang.Integer) r46
            goto L_0x006e
        L_0x006c:
            r46 = r43
        L_0x006e:
            int r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r46)
            if (r8 != r11) goto L_0x0077
            r48 = 1
            goto L_0x0079
        L_0x0077:
            r48 = 0
        L_0x0079:
            if (r8 != r10) goto L_0x007e
            r49 = 1
            goto L_0x0080
        L_0x007e:
            r49 = 0
        L_0x0080:
            if (r8 != 0) goto L_0x0084
            r8 = 1
            goto L_0x0085
        L_0x0084:
            r8 = 0
        L_0x0085:
            if (r7 == 0) goto L_0x0090
            if (r48 == 0) goto L_0x008c
            long r2 = r2 | r14
            long r2 = r2 | r12
            goto L_0x0090
        L_0x008c:
            long r2 = r2 | r18
            long r2 = r2 | r16
        L_0x0090:
            long r50 = r2 & r40
            int r7 = (r50 > r4 ? 1 : (r50 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x00a3
            if (r49 == 0) goto L_0x009e
            r44 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r44
            goto L_0x00a3
        L_0x009e:
            r36 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r36
        L_0x00a3:
            long r50 = r2 & r40
            int r7 = (r50 > r4 ? 1 : (r50 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x00b2
            if (r8 == 0) goto L_0x00ae
            r50 = 4096(0x1000, double:2.0237E-320)
            goto L_0x00b0
        L_0x00ae:
            r50 = 2048(0x800, double:1.0118E-320)
        L_0x00b0:
            long r2 = r2 | r50
        L_0x00b2:
            if (r48 == 0) goto L_0x00b6
            r7 = 0
            goto L_0x00b8
        L_0x00b6:
            r7 = 8
        L_0x00b8:
            if (r48 == 0) goto L_0x00bd
            r50 = 0
            goto L_0x00bf
        L_0x00bd:
            r50 = 180(0xb4, float:2.52E-43)
        L_0x00bf:
            if (r49 == 0) goto L_0x00c4
            r51 = 0
            goto L_0x00c6
        L_0x00c4:
            r51 = 8
        L_0x00c6:
            if (r8 == 0) goto L_0x00cb
            r8 = 8
            goto L_0x00db
        L_0x00cb:
            r8 = 0
            goto L_0x00db
        L_0x00cd:
            r9 = r43
            r46 = r9
            r7 = 0
            r8 = 0
            r48 = 0
            r49 = 0
            r50 = 0
            r51 = 0
        L_0x00db:
            long r52 = r2 & r32
            int r54 = (r52 > r4 ? 1 : (r52 == r4 ? 0 : -1))
            if (r54 == 0) goto L_0x00f4
            if (r0 == 0) goto L_0x00e6
            androidx.lifecycle.MutableLiveData<java.lang.String> r12 = r0.unitTxt
            goto L_0x00e8
        L_0x00e6:
            r12 = r43
        L_0x00e8:
            r1.updateLiveDataRegistration(r10, r12)
            if (r12 == 0) goto L_0x00f4
            java.lang.Object r12 = r12.getValue()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x00f6
        L_0x00f4:
            r12 = r43
        L_0x00f6:
            long r54 = r2 & r28
            int r13 = (r54 > r4 ? 1 : (r54 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0101
            if (r0 == 0) goto L_0x0101
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onClose
            goto L_0x0103
        L_0x0101:
            r13 = r43
        L_0x0103:
            long r54 = r2 & r24
            int r56 = (r54 > r4 ? 1 : (r54 == r4 ? 0 : -1))
            if (r56 == 0) goto L_0x011d
            if (r0 == 0) goto L_0x010e
            androidx.lifecycle.MutableLiveData<java.lang.String> r10 = r0.scoreTxt
            goto L_0x0110
        L_0x010e:
            r10 = r43
        L_0x0110:
            r14 = 3
            r1.updateLiveDataRegistration(r14, r10)
            if (r10 == 0) goto L_0x011d
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x011f
        L_0x011d:
            r10 = r43
        L_0x011f:
            long r14 = r2 & r22
            int r57 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r57 == 0) goto L_0x0139
            if (r0 == 0) goto L_0x012a
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.titleTxt
            goto L_0x012c
        L_0x012a:
            r14 = r43
        L_0x012c:
            r15 = 4
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x0139
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x013b
        L_0x0139:
            r14 = r43
        L_0x013b:
            long r57 = r2 & r20
            int r15 = (r57 > r4 ? 1 : (r57 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0155
            if (r0 == 0) goto L_0x0146
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.titleTxt2
            goto L_0x0148
        L_0x0146:
            r15 = r43
        L_0x0148:
            r11 = 5
            r1.updateLiveDataRegistration(r11, r15)
            if (r15 == 0) goto L_0x0155
            java.lang.Object r11 = r15.getValue()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x0157
        L_0x0155:
            r11 = r43
        L_0x0157:
            long r58 = r2 & r38
            int r15 = (r58 > r4 ? 1 : (r58 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x018d
            if (r0 == 0) goto L_0x0162
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.descTxt
            goto L_0x0164
        L_0x0162:
            r4 = r43
        L_0x0164:
            r5 = 6
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0171
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0173
        L_0x0171:
            r4 = r43
        L_0x0173:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r15 == 0) goto L_0x018a
            if (r5 == 0) goto L_0x0183
            r60 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r60
            long r2 = r2 | r26
            goto L_0x018a
        L_0x0183:
            long r2 = r2 | r30
            r60 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r60
        L_0x018a:
            r15 = r50
            goto L_0x0192
        L_0x018d:
            r4 = r43
            r15 = r50
            r5 = 0
        L_0x0192:
            r50 = r9
            r9 = r51
            goto L_0x01ac
        L_0x0197:
            r4 = r43
            r6 = r4
            r10 = r6
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r46 = r14
            r50 = r46
            r5 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r15 = 0
            r48 = 0
            r49 = 0
        L_0x01ac:
            r60 = 4718592(0x480000, double:2.331294E-317)
            long r60 = r2 & r60
            r58 = 0
            int r51 = (r60 > r58 ? 1 : (r60 == r58 ? 0 : -1))
            if (r51 == 0) goto L_0x0217
            if (r0 == 0) goto L_0x01bc
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.expand
            goto L_0x01be
        L_0x01bc:
            r0 = r50
        L_0x01be:
            r50 = r14
            r14 = 1
            r1.updateLiveDataRegistration(r14, r0)
            if (r0 == 0) goto L_0x01ce
            java.lang.Object r0 = r0.getValue()
            r46 = r0
            java.lang.Integer r46 = (java.lang.Integer) r46
        L_0x01ce:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r46)
            long r30 = r2 & r30
            r58 = 0
            int r46 = (r30 > r58 ? 1 : (r30 == r58 ? 0 : -1))
            if (r46 == 0) goto L_0x01f5
            if (r0 != r14) goto L_0x01df
            r48 = 1
            goto L_0x01e1
        L_0x01df:
            r48 = 0
        L_0x01e1:
            long r30 = r2 & r40
            int r14 = (r30 > r58 ? 1 : (r30 == r58 ? 0 : -1))
            if (r14 == 0) goto L_0x01f5
            if (r48 == 0) goto L_0x01f1
            r30 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r30
            r16 = 262144(0x40000, double:1.295163E-318)
            goto L_0x01f3
        L_0x01f1:
            long r2 = r2 | r18
        L_0x01f3:
            long r2 = r2 | r16
        L_0x01f5:
            long r16 = r2 & r26
            r18 = 0
            int r14 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r14 == 0) goto L_0x021b
            r14 = 2
            if (r0 != r14) goto L_0x0203
            r49 = 1
            goto L_0x0205
        L_0x0203:
            r49 = 0
        L_0x0205:
            long r16 = r2 & r40
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x021b
            if (r49 == 0) goto L_0x0211
            r16 = 65536(0x10000, double:3.2379E-319)
            goto L_0x0214
        L_0x0211:
            r16 = 32768(0x8000, double:1.61895E-319)
        L_0x0214:
            long r2 = r2 | r16
            goto L_0x021b
        L_0x0217:
            r50 = r14
            r18 = 0
        L_0x021b:
            long r16 = r2 & r38
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x025a
            if (r5 == 0) goto L_0x0225
            r14 = 1
            goto L_0x0227
        L_0x0225:
            r14 = r48
        L_0x0227:
            if (r5 == 0) goto L_0x022a
            goto L_0x022c
        L_0x022a:
            r49 = 0
        L_0x022c:
            if (r0 == 0) goto L_0x0239
            if (r14 == 0) goto L_0x0234
            r16 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x0237
        L_0x0234:
            r16 = 8388608(0x800000, double:4.144523E-317)
        L_0x0237:
            long r2 = r2 | r16
        L_0x0239:
            long r16 = r2 & r38
            r18 = 0
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x024a
            if (r49 == 0) goto L_0x0246
            r16 = 1024(0x400, double:5.06E-321)
            goto L_0x0248
        L_0x0246:
            r16 = 512(0x200, double:2.53E-321)
        L_0x0248:
            long r2 = r2 | r16
        L_0x024a:
            if (r14 == 0) goto L_0x024f
            r0 = 8
            goto L_0x0250
        L_0x024f:
            r0 = 0
        L_0x0250:
            if (r49 == 0) goto L_0x0255
            r42 = 0
            goto L_0x0257
        L_0x0255:
            r42 = 8
        L_0x0257:
            r5 = r42
            goto L_0x025c
        L_0x025a:
            r0 = 0
            r5 = 0
        L_0x025c:
            long r16 = r2 & r34
            r18 = 0
            int r14 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r14 == 0) goto L_0x0269
            android.widget.EditText r14 = r1.editTarget
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r14, r6)
        L_0x0269:
            r16 = 256(0x100, double:1.265E-321)
            long r16 = r2 & r16
            int r6 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r6 == 0) goto L_0x028b
            android.widget.EditText r6 = r1.editTarget
            r14 = r43
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r14 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r14
            r16 = r11
            r11 = r43
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r11 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r11
            r17 = r10
            r10 = r43
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r10
            r26 = r5
            androidx.databinding.InverseBindingListener r5 = r1.editTargetandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r14, r11, r10, r5)
            goto L_0x0291
        L_0x028b:
            r26 = r5
            r17 = r10
            r16 = r11
        L_0x0291:
            long r5 = r2 & r28
            int r10 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r10 == 0) goto L_0x02a8
            android.widget.ImageView r5 = r1.ivClose
            r6 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r13, r6)
            android.widget.ImageView r5 = r1.mboundView13
            r10 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r13, r10)
            android.widget.ImageView r5 = r1.mboundView3
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r13, r6)
        L_0x02a8:
            long r5 = r2 & r40
            int r10 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r10 == 0) goto L_0x02cb
            android.widget.LinearLayout r5 = r1.mboundView0
            r5.setVisibility(r8)
            android.widget.RelativeLayout r5 = r1.mboundView1
            r5.setVisibility(r7)
            androidx.constraintlayout.widget.ConstraintLayout r5 = r1.mboundView5
            r5.setVisibility(r9)
            int r5 = getBuildSdkInt()
            r6 = 11
            if (r5 < r6) goto L_0x02cb
            android.widget.ImageView r5 = r1.mboundView13
            float r6 = (float) r15
            r5.setRotation(r6)
        L_0x02cb:
            long r5 = r2 & r32
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x02d8
            android.widget.TextView r5 = r1.mboundView10
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r12)
        L_0x02d8:
            r5 = 448(0x1c0, double:2.213E-321)
            long r5 = r5 & r2
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x02e4
            android.widget.TextView r5 = r1.mboundView11
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r4)
        L_0x02e4:
            long r4 = r2 & r38
            int r6 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x02f6
            android.widget.TextView r4 = r1.mboundView11
            r4.setVisibility(r0)
            android.widget.Space r0 = r1.mboundView12
            r4 = r26
            r0.setVisibility(r4)
        L_0x02f6:
            long r4 = r2 & r24
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0308
            android.widget.TextView r0 = r1.mboundView2
            r10 = r17
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
            android.widget.TextView r0 = r1.tvScore
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x0308:
            long r4 = r2 & r20
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0315
            android.widget.TextView r0 = r1.mboundView4
            r11 = r16
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x0315:
            long r2 = r2 & r22
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0322
            android.widget.TextView r0 = r1.tvTargetTitle
            r14 = r50
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r14)
        L_0x0322:
            return
        L_0x0323:
            r0 = move-exception
            monitor-exit(r62)     // Catch:{ all -> 0x0323 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.databinding.LayoutTargetBindingImpl.executeBindings():void");
    }
}
