package com.eternal.account.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.CreateModel;

public class ActivityCreateBindingImpl extends ActivityCreateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEmailandroidTextAttrChanged;
    private InverseBindingListener etPwdandroidTextAttrChanged;
    private long mDirtyFlags;
    private final TextView mboundView10;
    private final View mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView8;
    private final View mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.create_email, 13);
        sparseIntArray.put(C0997R.C1000id.create_password, 14);
        sparseIntArray.put(C0997R.C1000id.create_tips_privacy, 15);
    }

    public ActivityCreateBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityCreateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 14, objArr[13], objArr[2], objArr[14], objArr[6], objArr[15], objArr[3], objArr[7], objArr[11], objArr[12], objArr[0], objArr[1]);
        this.etEmailandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityCreateBindingImpl.this.etEmail);
                CreateModel createModel = ActivityCreateBindingImpl.this.mCreateModel;
                boolean z = true;
                if (createModel != null) {
                    MutableLiveData<String> mutableLiveData = createModel.emailText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.etPwdandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityCreateBindingImpl.this.etPwd);
                CreateModel createModel = ActivityCreateBindingImpl.this.mCreateModel;
                boolean z = true;
                if (createModel != null) {
                    MutableLiveData<String> mutableLiveData = createModel.passwordText;
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
        this.createEmailTitle.setTag((Object) null);
        this.createPasswordTitle.setTag((Object) null);
        this.etEmail.setTag((Object) null);
        this.etPwd.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        TextView textView = objArr[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        View view2 = objArr[4];
        this.mboundView4 = view2;
        view2.setTag((Object) null);
        TextView textView2 = objArr[5];
        this.mboundView5 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = objArr[8];
        this.mboundView8 = textView3;
        textView3.setTag((Object) null);
        View view3 = objArr[9];
        this.mboundView9 = view3;
        view3.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
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
        if (C0977BR.createModel != i) {
            return false;
        }
        setCreateModel((CreateModel) obj);
        return true;
    }

    public void setCreateModel(CreateModel createModel) {
        this.mCreateModel = createModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        notifyPropertyChanged(C0977BR.createModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeCreateModelPwdColor((MutableLiveData) obj, i2);
            case 1:
                return onChangeCreateModelEmailColor((MutableLiveData) obj, i2);
            case 2:
                return onChangeCreateModelEmailText((MutableLiveData) obj, i2);
            case 3:
                return onChangeCreateModelPwdLineColor((MutableLiveData) obj, i2);
            case 4:
                return onChangeCreateModelEmailErrText((MutableLiveData) obj, i2);
            case 5:
                return onChangeCreateModelPasswordText((MutableLiveData) obj, i2);
            case 6:
                return onChangeCreateModelConfirmAble((MutableLiveData) obj, i2);
            case 7:
                return onChangeCreateModelShowLoading((MutableLiveData) obj, i2);
            case 8:
                return onChangeCreateModelTextVisiblePassword((MutableLiveData) obj, i2);
            case 9:
                return onChangeCreateModelEmailLineColor((MutableLiveData) obj, i2);
            case 10:
                return onChangeCreateModelPwdShowText((MutableLiveData) obj, i2);
            case 11:
                return onChangeCreateModelPwdErrText((MutableLiveData) obj, i2);
            case 12:
                return onChangeCreateModelPwdErrVisible((MutableLiveData) obj, i2);
            case 13:
                return onChangeCreateModelEmailErrVisible((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeCreateModelPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeCreateModelEmailColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeCreateModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeCreateModelPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeCreateModelEmailErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeCreateModelPasswordText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeCreateModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeCreateModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeCreateModelTextVisiblePassword(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeCreateModelEmailLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeCreateModelPwdShowText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeCreateModelPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeCreateModelPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeCreateModelEmailErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x020d  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0311  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0160  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r66 = this;
            r1 = r66
            monitor-enter(r66)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x046c }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x046c }
            monitor-exit(r66)     // Catch:{ all -> 0x046c }
            com.eternal.account.model.CreateModel r0 = r1.mCreateModel
            r6 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r2
            r16 = 51200(0xc800, double:2.5296E-319)
            r18 = 49280(0xc080, double:2.43476E-319)
            r20 = 49216(0xc040, double:2.4316E-319)
            r22 = 49408(0xc100, double:2.4411E-319)
            r24 = 49168(0xc010, double:2.4292E-319)
            r26 = 49184(0xc020, double:2.43E-319)
            r28 = 49160(0xc008, double:2.42883E-319)
            r8 = 1
            r30 = 49156(0xc004, double:2.42863E-319)
            r32 = 49152(0xc000, double:2.42843E-319)
            r34 = 49153(0xc001, double:2.4285E-319)
            r36 = 49154(0xc002, double:2.42853E-319)
            r9 = 0
            r38 = 0
            int r39 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r39 == 0) goto L_0x0338
            long r6 = r2 & r34
            int r39 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r39 == 0) goto L_0x0059
            if (r0 == 0) goto L_0x0044
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r6 = r0.pwdColor
            goto L_0x0046
        L_0x0044:
            r6 = r38
        L_0x0046:
            r1.updateLiveDataRegistration(r9, r6)
            if (r6 == 0) goto L_0x0052
            java.lang.Object r6 = r6.getValue()
            java.lang.Integer r6 = (java.lang.Integer) r6
            goto L_0x0054
        L_0x0052:
            r6 = r38
        L_0x0054:
            int r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r6)
            goto L_0x005a
        L_0x0059:
            r6 = 0
        L_0x005a:
            long r39 = r2 & r36
            int r7 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x007a
            if (r0 == 0) goto L_0x0065
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r7 = r0.emailColor
            goto L_0x0067
        L_0x0065:
            r7 = r38
        L_0x0067:
            r1.updateLiveDataRegistration(r8, r7)
            if (r7 == 0) goto L_0x0073
            java.lang.Object r7 = r7.getValue()
            java.lang.Integer r7 = (java.lang.Integer) r7
            goto L_0x0075
        L_0x0073:
            r7 = r38
        L_0x0075:
            int r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r7)
            goto L_0x007b
        L_0x007a:
            r7 = 0
        L_0x007b:
            long r39 = r2 & r30
            int r41 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0095
            if (r0 == 0) goto L_0x0086
            androidx.lifecycle.MutableLiveData<java.lang.String> r8 = r0.emailText
            goto L_0x0088
        L_0x0086:
            r8 = r38
        L_0x0088:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r8)
            if (r8 == 0) goto L_0x0095
            java.lang.Object r8 = r8.getValue()
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0097
        L_0x0095:
            r8 = r38
        L_0x0097:
            long r41 = r2 & r32
            int r9 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00aa
            if (r0 == 0) goto L_0x00aa
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r9 = r0.pwdTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onCreate
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r15 = r0.emailTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onPwdShow
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onBack
            goto L_0x00b0
        L_0x00aa:
            r9 = r38
            r10 = r9
            r11 = r10
            r14 = r11
            r15 = r14
        L_0x00b0:
            long r45 = r2 & r28
            int r47 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r47 == 0) goto L_0x00d1
            if (r0 == 0) goto L_0x00bb
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r12 = r0.pwdLineColor
            goto L_0x00bd
        L_0x00bb:
            r12 = r38
        L_0x00bd:
            r13 = 3
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x00ca
            java.lang.Object r12 = r12.getValue()
            java.lang.Integer r12 = (java.lang.Integer) r12
            goto L_0x00cc
        L_0x00ca:
            r12 = r38
        L_0x00cc:
            int r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r12)
            goto L_0x00d2
        L_0x00d1:
            r12 = 0
        L_0x00d2:
            long r47 = r2 & r24
            int r13 = (r47 > r4 ? 1 : (r47 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00ec
            if (r0 == 0) goto L_0x00dd
            androidx.lifecycle.MutableLiveData<java.lang.String> r13 = r0.emailErrText
            goto L_0x00df
        L_0x00dd:
            r13 = r38
        L_0x00df:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x00ec
            java.lang.Object r4 = r13.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00ee
        L_0x00ec:
            r4 = r38
        L_0x00ee:
            long r49 = r2 & r26
            r47 = 0
            int r5 = (r49 > r47 ? 1 : (r49 == r47 ? 0 : -1))
            if (r5 == 0) goto L_0x010a
            if (r0 == 0) goto L_0x00fb
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.passwordText
            goto L_0x00fd
        L_0x00fb:
            r5 = r38
        L_0x00fd:
            r13 = 5
            r1.updateLiveDataRegistration(r13, r5)
            if (r5 == 0) goto L_0x010a
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x010c
        L_0x010a:
            r5 = r38
        L_0x010c:
            long r49 = r2 & r20
            r47 = 0
            int r13 = (r49 > r47 ? 1 : (r49 == r47 ? 0 : -1))
            r49 = r4
            if (r13 == 0) goto L_0x0153
            if (r0 == 0) goto L_0x011d
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.confirmAble
            r50 = r5
            goto L_0x0121
        L_0x011d:
            r50 = r5
            r4 = r38
        L_0x0121:
            r5 = 6
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x012e
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0130
        L_0x012e:
            r4 = r38
        L_0x0130:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x0141
            if (r4 == 0) goto L_0x013c
            r51 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x013f
        L_0x013c:
            r51 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x013f:
            long r2 = r2 | r51
        L_0x0141:
            android.widget.Button r5 = r1.ibNext
            android.content.Context r5 = r5.getContext()
            if (r4 == 0) goto L_0x014c
            int r13 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            goto L_0x014e
        L_0x014c:
            int r13 = com.eternal.account.C0997R.C0999drawable.create_account_disable
        L_0x014e:
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r13)
            goto L_0x0158
        L_0x0153:
            r50 = r5
            r5 = r38
            r4 = 0
        L_0x0158:
            long r51 = r2 & r18
            r47 = 0
            int r53 = (r51 > r47 ? 1 : (r51 == r47 ? 0 : -1))
            if (r53 == 0) goto L_0x01b6
            if (r0 == 0) goto L_0x0167
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.showLoading
            r52 = r4
            goto L_0x016b
        L_0x0167:
            r52 = r4
            r13 = r38
        L_0x016b:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x0178
            java.lang.Object r4 = r13.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x017a
        L_0x0178:
            r4 = r38
        L_0x017a:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r53 == 0) goto L_0x0195
            if (r4 == 0) goto L_0x018b
            r53 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r53
            r53 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x0193
        L_0x018b:
            r53 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r53
            r53 = 4194304(0x400000, double:2.0722615E-317)
        L_0x0193:
            long r2 = r2 | r53
        L_0x0195:
            if (r4 == 0) goto L_0x0199
            r13 = 0
            goto L_0x019b
        L_0x0199:
            r13 = 8
        L_0x019b:
            if (r4 == 0) goto L_0x01a8
            android.widget.Button r4 = r1.ibNext
            r53 = r2
            int r2 = com.eternal.account.C0997R.C0998color.color_transparency
            int r2 = getColorFromResource(r4, r2)
            goto L_0x01b2
        L_0x01a8:
            r53 = r2
            android.widget.Button r2 = r1.ibNext
            int r3 = com.eternal.account.C0997R.C0998color.white
            int r2 = getColorFromResource(r2, r3)
        L_0x01b2:
            r4 = r2
            r2 = r53
            goto L_0x01ba
        L_0x01b6:
            r52 = r4
            r4 = 0
            r13 = 0
        L_0x01ba:
            long r53 = r2 & r22
            r47 = 0
            int r55 = (r53 > r47 ? 1 : (r53 == r47 ? 0 : -1))
            r53 = r4
            if (r55 == 0) goto L_0x01fc
            if (r0 == 0) goto L_0x01cb
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.textVisiblePassword
            r51 = r5
            goto L_0x01cf
        L_0x01cb:
            r51 = r5
            r4 = r38
        L_0x01cf:
            r5 = 8
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01dd
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01df
        L_0x01dd:
            r4 = r38
        L_0x01df:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r55 == 0) goto L_0x01f0
            if (r4 == 0) goto L_0x01eb
            r54 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x01ee
        L_0x01eb:
            r54 = 16777216(0x1000000, double:8.289046E-317)
        L_0x01ee:
            long r2 = r2 | r54
        L_0x01f0:
            if (r4 == 0) goto L_0x01f7
            android.text.method.HideReturnsTransformationMethod r4 = android.text.method.HideReturnsTransformationMethod.getInstance()
            goto L_0x0202
        L_0x01f7:
            android.text.method.PasswordTransformationMethod r4 = android.text.method.PasswordTransformationMethod.getInstance()
            goto L_0x0202
        L_0x01fc:
            r51 = r5
            r5 = 8
            r4 = r38
        L_0x0202:
            r45 = 49664(0xc200, double:2.45373E-319)
            long r54 = r2 & r45
            r47 = 0
            int r56 = (r54 > r47 ? 1 : (r54 == r47 ? 0 : -1))
            if (r56 == 0) goto L_0x022d
            if (r0 == 0) goto L_0x0214
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r0.emailLineColor
            r55 = r4
            goto L_0x0218
        L_0x0214:
            r55 = r4
            r5 = r38
        L_0x0218:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0226
            java.lang.Object r4 = r5.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0228
        L_0x0226:
            r4 = r38
        L_0x0228:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0230
        L_0x022d:
            r55 = r4
            r4 = 0
        L_0x0230:
            r43 = 50176(0xc400, double:2.479E-319)
            long r56 = r2 & r43
            r47 = 0
            int r5 = (r56 > r47 ? 1 : (r56 == r47 ? 0 : -1))
            if (r5 == 0) goto L_0x0254
            if (r0 == 0) goto L_0x0242
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.pwdShowText
            r56 = r4
            goto L_0x0246
        L_0x0242:
            r56 = r4
            r5 = r38
        L_0x0246:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0256
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0258
        L_0x0254:
            r56 = r4
        L_0x0256:
            r4 = r38
        L_0x0258:
            long r57 = r2 & r16
            r47 = 0
            int r5 = (r57 > r47 ? 1 : (r57 == r47 ? 0 : -1))
            if (r5 == 0) goto L_0x0279
            if (r0 == 0) goto L_0x0267
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.pwdErrText
            r57 = r4
            goto L_0x026b
        L_0x0267:
            r57 = r4
            r5 = r38
        L_0x026b:
            r4 = 11
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x027b
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x027d
        L_0x0279:
            r57 = r4
        L_0x027b:
            r4 = r38
        L_0x027d:
            r41 = 53248(0xd000, double:2.6308E-319)
            long r58 = r2 & r41
            r47 = 0
            int r5 = (r58 > r47 ? 1 : (r58 == r47 ? 0 : -1))
            r58 = r4
            if (r5 == 0) goto L_0x02bb
            if (r0 == 0) goto L_0x0291
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.pwdErrVisible
            r59 = r6
            goto L_0x0295
        L_0x0291:
            r59 = r6
            r4 = r38
        L_0x0295:
            r6 = 12
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x02a3
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x02a5
        L_0x02a3:
            r4 = r38
        L_0x02a5:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x02b5
            if (r4 == 0) goto L_0x02b1
            r5 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x02b4
        L_0x02b1:
            r5 = 1048576(0x100000, double:5.180654E-318)
        L_0x02b4:
            long r2 = r2 | r5
        L_0x02b5:
            if (r4 == 0) goto L_0x02b8
            goto L_0x02bd
        L_0x02b8:
            r4 = 8
            goto L_0x02be
        L_0x02bb:
            r59 = r6
        L_0x02bd:
            r4 = 0
        L_0x02be:
            r5 = 57344(0xe000, double:2.83317E-319)
            long r60 = r2 & r5
            r5 = 0
            int r62 = (r60 > r5 ? 1 : (r60 == r5 ? 0 : -1))
            if (r62 == 0) goto L_0x0311
            if (r0 == 0) goto L_0x02ce
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.emailErrVisible
            goto L_0x02d0
        L_0x02ce:
            r0 = r38
        L_0x02d0:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x02de
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x02e0
        L_0x02de:
            r0 = r38
        L_0x02e0:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r62 == 0) goto L_0x02f0
            if (r0 == 0) goto L_0x02ec
            r5 = 524288(0x80000, double:2.590327E-318)
            goto L_0x02ef
        L_0x02ec:
            r5 = 262144(0x40000, double:1.295163E-318)
        L_0x02ef:
            long r2 = r2 | r5
        L_0x02f0:
            if (r0 == 0) goto L_0x02f5
            r54 = 0
            goto L_0x02f7
        L_0x02f5:
            r54 = 8
        L_0x02f7:
            r63 = r12
            r6 = r14
            r12 = r51
            r0 = r52
            r5 = r53
            r64 = r54
            r51 = r56
            r53 = r58
            r52 = r4
            r14 = r7
            r54 = r13
            r7 = r15
            r4 = r55
            r13 = r59
            goto L_0x032a
        L_0x0311:
            r63 = r12
            r54 = r13
            r6 = r14
            r12 = r51
            r0 = r52
            r5 = r53
            r51 = r56
            r53 = r58
            r13 = r59
            r64 = 0
            r52 = r4
            r14 = r7
            r7 = r15
            r4 = r55
        L_0x032a:
            r15 = r8
            r8 = r11
            r11 = r50
            r50 = r49
            r49 = r57
            r65 = r10
            r10 = r9
            r9 = r65
            goto L_0x0356
        L_0x0338:
            r4 = r38
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r15 = r12
            r49 = r15
            r50 = r49
            r53 = r50
            r0 = 0
            r5 = 0
            r13 = 0
            r14 = 0
            r51 = 0
            r52 = 0
            r54 = 0
            r63 = 0
            r64 = 0
        L_0x0356:
            long r36 = r2 & r36
            r47 = 0
            int r55 = (r36 > r47 ? 1 : (r36 == r47 ? 0 : -1))
            r36 = r5
            if (r55 == 0) goto L_0x036a
            android.widget.TextView r5 = r1.createEmailTitle
            r5.setTextColor(r14)
            android.widget.EditText r5 = r1.etEmail
            r5.setTextColor(r14)
        L_0x036a:
            long r34 = r2 & r34
            int r5 = (r34 > r47 ? 1 : (r34 == r47 ? 0 : -1))
            if (r5 == 0) goto L_0x037a
            android.widget.TextView r5 = r1.createPasswordTitle
            r5.setTextColor(r13)
            android.widget.EditText r5 = r1.etPwd
            r5.setTextColor(r13)
        L_0x037a:
            long r13 = r2 & r32
            int r5 = (r13 > r47 ? 1 : (r13 == r47 ? 0 : -1))
            if (r5 == 0) goto L_0x039b
            android.widget.EditText r5 = r1.etEmail
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r5, r7)
            android.widget.EditText r5 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r5, r10)
            android.widget.Button r5 = r1.ibNext
            r7 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r6, r7)
            android.widget.TextView r5 = r1.mboundView8
            r6 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r9, r6)
            com.eternal.widget.guqiang.Toolbar r5 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r5, r8)
        L_0x039b:
            long r5 = r2 & r30
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x03a8
            android.widget.EditText r5 = r1.etEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r15)
        L_0x03a8:
            r5 = 32768(0x8000, double:1.61895E-319)
            long r5 = r5 & r2
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x03ca
            android.widget.EditText r5 = r1.etEmail
            r6 = r38
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r6
            r7 = r38
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r7 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r7
            r8 = r38
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r8 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r8
            androidx.databinding.InverseBindingListener r9 = r1.etEmailandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r5, r6, r7, r8, r9)
            android.widget.EditText r5 = r1.etPwd
            androidx.databinding.InverseBindingListener r9 = r1.etPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r5, r6, r7, r8, r9)
        L_0x03ca:
            long r5 = r2 & r26
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x03d7
            android.widget.EditText r5 = r1.etPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r11)
        L_0x03d7:
            long r5 = r2 & r22
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x03e4
            android.widget.EditText r5 = r1.etPwd
            android.text.method.TransformationMethod r4 = (android.text.method.TransformationMethod) r4
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r5, r4)
        L_0x03e4:
            long r4 = r2 & r20
            int r6 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x03f4
            android.widget.Button r4 = r1.ibNext
            r4.setClickable(r0)
            android.widget.Button r0 = r1.ibNext
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r12)
        L_0x03f4:
            long r4 = r2 & r18
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0408
            android.widget.Button r0 = r1.ibNext
            r4 = r36
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
            android.widget.ProgressBar r0 = r1.pbLoading
            r13 = r54
            r0.setVisibility(r13)
        L_0x0408:
            long r4 = r2 & r16
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0415
            android.widget.TextView r0 = r1.mboundView10
            r4 = r53
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0415:
            r4 = 53248(0xd000, double:2.6308E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0424
            android.widget.TextView r0 = r1.mboundView10
            r4 = r52
            r0.setVisibility(r4)
        L_0x0424:
            r4 = 49664(0xc200, double:2.45373E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0433
            android.view.View r0 = r1.mboundView4
            r4 = r51
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x0433:
            long r4 = r2 & r24
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0440
            android.widget.TextView r0 = r1.mboundView5
            r4 = r50
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0440:
            r4 = 57344(0xe000, double:2.83317E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x044f
            android.widget.TextView r0 = r1.mboundView5
            r4 = r64
            r0.setVisibility(r4)
        L_0x044f:
            r4 = 50176(0xc400, double:2.479E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x045e
            android.widget.TextView r0 = r1.mboundView8
            r4 = r49
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x045e:
            long r2 = r2 & r28
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x046b
            android.view.View r0 = r1.mboundView9
            r12 = r63
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r12)
        L_0x046b:
            return
        L_0x046c:
            r0 = move-exception
            monitor-exit(r66)     // Catch:{ all -> 0x046c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityCreateBindingImpl.executeBindings():void");
    }
}
