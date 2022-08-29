package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.FeedbackModel;
import com.eternal.account.model.PhotoItemModel;

public class ActivityFeedbackBindingImpl extends ActivityFeedbackBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEmailandroidTextAttrChanged;
    private InverseBindingListener etMessageandroidTextAttrChanged;
    private InverseBindingListener etTitleandroidTextAttrChanged;
    private long mDirtyFlags;
    private final RecyclerView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.email_title, 8);
        sparseIntArray.put(C0997R.C1000id.tv_title, 9);
        sparseIntArray.put(C0997R.C1000id.tv_message, 10);
        sparseIntArray.put(C0997R.C1000id.rl_attach, 11);
    }

    public ActivityFeedbackBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityFeedbackBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, objArr[8], objArr[2], objArr[4], objArr[3], objArr[6], objArr[7], objArr[11], objArr[0], objArr[1], objArr[10], objArr[9]);
        this.etEmailandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityFeedbackBindingImpl.this.etEmail);
                FeedbackModel feedbackModel = ActivityFeedbackBindingImpl.this.mFeedbackModel;
                boolean z = true;
                if (feedbackModel != null) {
                    MutableLiveData<String> mutableLiveData = feedbackModel.emailText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.etMessageandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityFeedbackBindingImpl.this.etMessage);
                FeedbackModel feedbackModel = ActivityFeedbackBindingImpl.this.mFeedbackModel;
                boolean z = true;
                if (feedbackModel != null) {
                    MutableLiveData<String> mutableLiveData = feedbackModel.messageText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.etTitleandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityFeedbackBindingImpl.this.etTitle);
                FeedbackModel feedbackModel = ActivityFeedbackBindingImpl.this.mFeedbackModel;
                boolean z = true;
                if (feedbackModel != null) {
                    MutableLiveData<String> mutableLiveData = feedbackModel.titleText;
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
        this.etEmail.setTag((Object) null);
        this.etMessage.setTag((Object) null);
        this.etTitle.setTag((Object) null);
        this.ibSend.setTag((Object) null);
        RecyclerView recyclerView = objArr[5];
        this.mboundView5 = recyclerView;
        recyclerView.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (C0977BR.feedbackModel != i) {
            return false;
        }
        setFeedbackModel((FeedbackModel) obj);
        return true;
    }

    public void setFeedbackModel(FeedbackModel feedbackModel) {
        this.mFeedbackModel = feedbackModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(C0977BR.feedbackModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeFeedbackModelItems((ObservableList) obj, i2);
        }
        if (i == 1) {
            return onChangeFeedbackModelTitleText((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeFeedbackModelEmailText((MutableLiveData) obj, i2);
        }
        if (i == 3) {
            return onChangeFeedbackModelShowLoading((MutableLiveData) obj, i2);
        }
        if (i == 4) {
            return onChangeFeedbackModelMessageText((MutableLiveData) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeFeedbackModelConfirmAble((MutableLiveData) obj, i2);
    }

    private boolean onChangeFeedbackModelItems(ObservableList<PhotoItemModel> observableList, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeFeedbackModelTitleText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeFeedbackModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeFeedbackModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeFeedbackModelMessageText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeFeedbackModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r40 = this;
            r1 = r40
            monitor-enter(r40)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x026e }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x026e }
            monitor-exit(r40)     // Catch:{ all -> 0x026e }
            com.eternal.account.model.FeedbackModel r0 = r1.mFeedbackModel
            r6 = 255(0xff, double:1.26E-321)
            long r6 = r6 & r2
            r14 = 194(0xc2, double:9.6E-322)
            r16 = 193(0xc1, double:9.54E-322)
            r18 = 196(0xc4, double:9.7E-322)
            r20 = 192(0xc0, double:9.5E-322)
            r9 = 0
            r24 = 0
            int r25 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r25 == 0) goto L_0x0177
            long r6 = r2 & r20
            int r25 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r25 == 0) goto L_0x0031
            if (r0 == 0) goto L_0x0031
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r6 = r0.messageTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onSend
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r12 = r0.emailTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r10 = r0.titleTextChanged
            goto L_0x0037
        L_0x0031:
            r6 = r24
            r7 = r6
            r10 = r7
            r12 = r10
            r13 = r12
        L_0x0037:
            long r29 = r2 & r16
            int r31 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r31 == 0) goto L_0x006b
            if (r0 == 0) goto L_0x0044
            androidx.databinding.ObservableList<com.eternal.account.model.PhotoItemModel> r11 = r0.items
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.account.model.PhotoItemModel> r8 = r0.itemBinding
            goto L_0x0047
        L_0x0044:
            r8 = r24
            r11 = r8
        L_0x0047:
            r1.updateRegistration((int) r9, (androidx.databinding.ObservableList) r11)
            if (r11 == 0) goto L_0x0051
            int r32 = r11.size()
            goto L_0x0053
        L_0x0051:
            r32 = 0
        L_0x0053:
            if (r32 <= 0) goto L_0x0058
            r32 = 1
            goto L_0x005a
        L_0x0058:
            r32 = 0
        L_0x005a:
            if (r31 == 0) goto L_0x0065
            if (r32 == 0) goto L_0x0061
            r33 = 512(0x200, double:2.53E-321)
            goto L_0x0063
        L_0x0061:
            r33 = 256(0x100, double:1.265E-321)
        L_0x0063:
            long r2 = r2 | r33
        L_0x0065:
            if (r32 == 0) goto L_0x0068
            goto L_0x006e
        L_0x0068:
            r31 = 8
            goto L_0x0070
        L_0x006b:
            r8 = r24
            r11 = r8
        L_0x006e:
            r31 = 0
        L_0x0070:
            long r32 = r2 & r14
            int r34 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x007b
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.titleText
            goto L_0x007d
        L_0x007b:
            r14 = r24
        L_0x007d:
            r15 = 1
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x008a
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x008c
        L_0x008a:
            r14 = r24
        L_0x008c:
            long r34 = r2 & r18
            int r15 = (r34 > r4 ? 1 : (r34 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00a6
            if (r0 == 0) goto L_0x0097
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.emailText
            goto L_0x0099
        L_0x0097:
            r15 = r24
        L_0x0099:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r15)
            if (r15 == 0) goto L_0x00a6
            java.lang.Object r9 = r15.getValue()
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x00a8
        L_0x00a6:
            r9 = r24
        L_0x00a8:
            r27 = 200(0xc8, double:9.9E-322)
            long r35 = r2 & r27
            int r15 = (r35 > r4 ? 1 : (r35 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00f7
            if (r0 == 0) goto L_0x00b5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showLoading
            goto L_0x00b7
        L_0x00b5:
            r4 = r24
        L_0x00b7:
            r5 = 3
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00c4
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00c6
        L_0x00c4:
            r4 = r24
        L_0x00c6:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x00de
            if (r4 == 0) goto L_0x00d6
            r37 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r37
            r37 = 32768(0x8000, double:1.61895E-319)
            goto L_0x00dc
        L_0x00d6:
            r37 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r37
            r37 = 16384(0x4000, double:8.0948E-320)
        L_0x00dc:
            long r2 = r2 | r37
        L_0x00de:
            if (r4 == 0) goto L_0x00e3
            r29 = 0
            goto L_0x00e5
        L_0x00e3:
            r29 = 8
        L_0x00e5:
            if (r4 == 0) goto L_0x00ec
            android.widget.Button r4 = r1.ibSend
            int r5 = com.eternal.account.C0997R.C0998color.color_transparency
            goto L_0x00f0
        L_0x00ec:
            android.widget.Button r4 = r1.ibSend
            int r5 = com.eternal.account.C0997R.C0998color.white
        L_0x00f0:
            int r4 = getColorFromResource(r4, r5)
            r25 = 208(0xd0, double:1.03E-321)
            goto L_0x00fc
        L_0x00f7:
            r4 = 0
            r25 = 208(0xd0, double:1.03E-321)
            r29 = 0
        L_0x00fc:
            long r37 = r2 & r25
            r35 = 0
            int r5 = (r37 > r35 ? 1 : (r37 == r35 ? 0 : -1))
            if (r5 == 0) goto L_0x0118
            if (r0 == 0) goto L_0x0109
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.messageText
            goto L_0x010b
        L_0x0109:
            r5 = r24
        L_0x010b:
            r15 = 4
            r1.updateLiveDataRegistration(r15, r5)
            if (r5 == 0) goto L_0x0118
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x011a
        L_0x0118:
            r5 = r24
        L_0x011a:
            r22 = 224(0xe0, double:1.107E-321)
            long r37 = r2 & r22
            r35 = 0
            int r15 = (r37 > r35 ? 1 : (r37 == r35 ? 0 : -1))
            if (r15 == 0) goto L_0x0160
            if (r0 == 0) goto L_0x012b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.confirmAble
            r37 = r4
            goto L_0x012f
        L_0x012b:
            r37 = r4
            r0 = r24
        L_0x012f:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x013c
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x013e
        L_0x013c:
            r0 = r24
        L_0x013e:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r15 == 0) goto L_0x014d
            if (r0 == 0) goto L_0x0149
            r38 = 8192(0x2000, double:4.0474E-320)
            goto L_0x014b
        L_0x0149:
            r38 = 4096(0x1000, double:2.0237E-320)
        L_0x014b:
            long r2 = r2 | r38
        L_0x014d:
            android.widget.Button r4 = r1.ibSend
            android.content.Context r4 = r4.getContext()
            if (r0 == 0) goto L_0x0158
            int r15 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            goto L_0x015a
        L_0x0158:
            int r15 = com.eternal.account.C0997R.C0999drawable.create_account_disable
        L_0x015a:
            android.graphics.drawable.Drawable r4 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r4, r15)
            r15 = r14
            goto L_0x0166
        L_0x0160:
            r37 = r4
            r15 = r14
            r4 = r24
            r0 = 0
        L_0x0166:
            r14 = r13
            r13 = r12
            r12 = r29
            r29 = r11
            r11 = r10
            r10 = r31
            r31 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r37
            goto L_0x0189
        L_0x0177:
            r4 = r24
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r11 = r9
            r13 = r11
            r14 = r13
            r15 = r14
            r29 = r15
            r31 = r29
            r0 = 0
            r5 = 0
            r10 = 0
            r12 = 0
        L_0x0189:
            long r20 = r2 & r20
            r35 = 0
            int r37 = (r20 > r35 ? 1 : (r20 == r35 ? 0 : -1))
            r20 = r10
            if (r37 == 0) goto L_0x01ad
            android.widget.EditText r10 = r1.etEmail
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r10, r13)
            android.widget.EditText r10 = r1.etMessage
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r10, r7)
            android.widget.EditText r7 = r1.etTitle
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r7, r11)
            android.widget.Button r7 = r1.ibSend
            r10 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r7, r8, r10)
            com.eternal.widget.guqiang.Toolbar r7 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r7, r14)
        L_0x01ad:
            long r7 = r2 & r18
            r10 = 0
            int r13 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r13 == 0) goto L_0x01ba
            android.widget.EditText r7 = r1.etEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r9)
        L_0x01ba:
            r7 = 128(0x80, double:6.32E-322)
            long r7 = r7 & r2
            int r9 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r9 == 0) goto L_0x020f
            android.widget.EditText r7 = r1.etEmail
            r8 = r24
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r8 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r8
            r9 = r24
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r9 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r9
            r10 = r24
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r10
            androidx.databinding.InverseBindingListener r11 = r1.etEmailandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r8, r9, r10, r11)
            android.widget.EditText r7 = r1.etMessage
            androidx.databinding.InverseBindingListener r11 = r1.etMessageandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r8, r9, r10, r11)
            android.widget.EditText r7 = r1.etTitle
            androidx.databinding.InverseBindingListener r11 = r1.etTitleandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r8, r9, r10, r11)
            androidx.recyclerview.widget.RecyclerView r7 = r1.mboundView5
            r8 = 1
            r9 = 0
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r8 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.grid(r8, r9, r9)
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r7, r8)
            com.eternal.widget.guqiang.Toolbar r7 = r1.toolBar
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            android.content.Context r8 = r8.getContext()
            int r9 = com.eternal.account.C0997R.C0999drawable.add_device_back_selector
            android.graphics.drawable.Drawable r8 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r8, r9)
            com.eternal.widget.guqiang.ToolbarAdapter.backRes(r7, r8)
            com.eternal.widget.guqiang.Toolbar r7 = r1.toolBar
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            android.content.res.Resources r8 = r8.getResources()
            int r9 = com.eternal.account.C0997R.string.feedback_title
            java.lang.String r8 = r8.getString(r9)
            com.eternal.widget.guqiang.ToolbarAdapter.setTitle(r7, r8)
        L_0x020f:
            r7 = 208(0xd0, double:1.03E-321)
            long r7 = r7 & r2
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x021d
            android.widget.EditText r7 = r1.etMessage
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r6)
        L_0x021d:
            r6 = 194(0xc2, double:9.6E-322)
            long r6 = r6 & r2
            int r8 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r8 == 0) goto L_0x0229
            android.widget.EditText r6 = r1.etTitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r15)
        L_0x0229:
            r6 = 224(0xe0, double:1.107E-321)
            long r6 = r6 & r2
            int r8 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r8 == 0) goto L_0x023a
            android.widget.Button r6 = r1.ibSend
            r6.setClickable(r0)
            android.widget.Button r0 = r1.ibSend
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r4)
        L_0x023a:
            r6 = 200(0xc8, double:9.9E-322)
            long r6 = r6 & r2
            int r0 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x024b
            android.widget.Button r0 = r1.ibSend
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r5)
            android.widget.ProgressBar r0 = r1.pbLoading
            r0.setVisibility(r12)
        L_0x024b:
            long r2 = r2 & r16
            int r0 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x026d
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView5
            r2 = r20
            r0.setVisibility(r2)
            androidx.recyclerview.widget.RecyclerView r9 = r1.mboundView5
            r12 = r24
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r12 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r12
            r13 = r24
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r13 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r13
            r14 = r24
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r14 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r14
            r10 = r31
            r11 = r29
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r9, r10, r11, r12, r13, r14)
        L_0x026d:
            return
        L_0x026e:
            r0 = move-exception
            monitor-exit(r40)     // Catch:{ all -> 0x026e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityFeedbackBindingImpl.executeBindings():void");
    }
}
