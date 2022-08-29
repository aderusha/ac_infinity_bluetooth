package com.alibaba.android.arouter.facade;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.core.app.ActivityOptionsCompat;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.widget.wheelview.common.WheelConstants;
import java.io.Serializable;
import java.util.ArrayList;

public final class Postcard extends RouteMeta {
    private String action;
    private int enterAnim;
    private int exitAnim;
    private int flags;
    private boolean greenChannel;
    private Bundle mBundle;
    private Bundle optionsCompat;
    private IProvider provider;
    private SerializationService serializationService;
    private Object tag;
    private int timeout;
    private Uri uri;

    public Bundle getOptionsBundle() {
        return this.optionsCompat;
    }

    public int getEnterAnim() {
        return this.enterAnim;
    }

    public int getExitAnim() {
        return this.exitAnim;
    }

    public IProvider getProvider() {
        return this.provider;
    }

    public Postcard setProvider(IProvider iProvider) {
        this.provider = iProvider;
        return this;
    }

    public Postcard() {
        this((String) null, (String) null);
    }

    public Postcard(String str, String str2) {
        this(str, str2, (Uri) null, (Bundle) null);
    }

    public Postcard(String str, String str2, Uri uri2, Bundle bundle) {
        this.flags = -1;
        this.timeout = WheelConstants.WHEEL_SCROLL_DELAY_DURATION;
        this.enterAnim = -1;
        this.exitAnim = -1;
        setPath(str);
        setGroup(str2);
        setUri(uri2);
        this.mBundle = bundle == null ? new Bundle() : bundle;
    }

    public boolean isGreenChannel() {
        return this.greenChannel;
    }

    public Object getTag() {
        return this.tag;
    }

    public Postcard setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public Bundle getExtras() {
        return this.mBundle;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public Postcard setTimeout(int i) {
        this.timeout = i;
        return this;
    }

    public Uri getUri() {
        return this.uri;
    }

    public Postcard setUri(Uri uri2) {
        this.uri = uri2;
        return this;
    }

    public Object navigation() {
        return navigation((Context) null);
    }

    public Object navigation(Context context) {
        return navigation(context, (NavigationCallback) null);
    }

    public Object navigation(Context context, NavigationCallback navigationCallback) {
        return ARouter.getInstance().navigation(context, this, -1, navigationCallback);
    }

    public void navigation(Activity activity, int i) {
        navigation(activity, i, (NavigationCallback) null);
    }

    public void navigation(Activity activity, int i, NavigationCallback navigationCallback) {
        ARouter.getInstance().navigation(activity, this, i, navigationCallback);
    }

    public Postcard greenChannel() {
        this.greenChannel = true;
        return this;
    }

    public Postcard with(Bundle bundle) {
        if (bundle != null) {
            this.mBundle = bundle;
        }
        return this;
    }

    public Postcard withFlags(int i) {
        this.flags = i;
        return this;
    }

    public Postcard addFlags(int i) {
        this.flags = i | this.flags;
        return this;
    }

    public int getFlags() {
        return this.flags;
    }

    public Postcard withObject(String str, Object obj) {
        SerializationService serializationService2 = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        this.serializationService = serializationService2;
        this.mBundle.putString(str, serializationService2.object2Json(obj));
        return this;
    }

    public Postcard withString(String str, String str2) {
        this.mBundle.putString(str, str2);
        return this;
    }

    public Postcard withBoolean(String str, boolean z) {
        this.mBundle.putBoolean(str, z);
        return this;
    }

    public Postcard withShort(String str, short s) {
        this.mBundle.putShort(str, s);
        return this;
    }

    public Postcard withInt(String str, int i) {
        this.mBundle.putInt(str, i);
        return this;
    }

    public Postcard withLong(String str, long j) {
        this.mBundle.putLong(str, j);
        return this;
    }

    public Postcard withDouble(String str, double d) {
        this.mBundle.putDouble(str, d);
        return this;
    }

    public Postcard withByte(String str, byte b) {
        this.mBundle.putByte(str, b);
        return this;
    }

    public Postcard withChar(String str, char c) {
        this.mBundle.putChar(str, c);
        return this;
    }

    public Postcard withFloat(String str, float f) {
        this.mBundle.putFloat(str, f);
        return this;
    }

    public Postcard withCharSequence(String str, CharSequence charSequence) {
        this.mBundle.putCharSequence(str, charSequence);
        return this;
    }

    public Postcard withParcelable(String str, Parcelable parcelable) {
        this.mBundle.putParcelable(str, parcelable);
        return this;
    }

    public Postcard withParcelableArray(String str, Parcelable[] parcelableArr) {
        this.mBundle.putParcelableArray(str, parcelableArr);
        return this;
    }

    public Postcard withParcelableArrayList(String str, ArrayList<? extends Parcelable> arrayList) {
        this.mBundle.putParcelableArrayList(str, arrayList);
        return this;
    }

    public Postcard withSparseParcelableArray(String str, SparseArray<? extends Parcelable> sparseArray) {
        this.mBundle.putSparseParcelableArray(str, sparseArray);
        return this;
    }

    public Postcard withIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        this.mBundle.putIntegerArrayList(str, arrayList);
        return this;
    }

    public Postcard withStringArrayList(String str, ArrayList<String> arrayList) {
        this.mBundle.putStringArrayList(str, arrayList);
        return this;
    }

    public Postcard withCharSequenceArrayList(String str, ArrayList<CharSequence> arrayList) {
        this.mBundle.putCharSequenceArrayList(str, arrayList);
        return this;
    }

    public Postcard withSerializable(String str, Serializable serializable) {
        this.mBundle.putSerializable(str, serializable);
        return this;
    }

    public Postcard withByteArray(String str, byte[] bArr) {
        this.mBundle.putByteArray(str, bArr);
        return this;
    }

    public Postcard withShortArray(String str, short[] sArr) {
        this.mBundle.putShortArray(str, sArr);
        return this;
    }

    public Postcard withCharArray(String str, char[] cArr) {
        this.mBundle.putCharArray(str, cArr);
        return this;
    }

    public Postcard withFloatArray(String str, float[] fArr) {
        this.mBundle.putFloatArray(str, fArr);
        return this;
    }

    public Postcard withCharSequenceArray(String str, CharSequence[] charSequenceArr) {
        this.mBundle.putCharSequenceArray(str, charSequenceArr);
        return this;
    }

    public Postcard withBundle(String str, Bundle bundle) {
        this.mBundle.putBundle(str, bundle);
        return this;
    }

    public Postcard withTransition(int i, int i2) {
        this.enterAnim = i;
        this.exitAnim = i2;
        return this;
    }

    public Postcard withOptionsCompat(ActivityOptionsCompat activityOptionsCompat) {
        if (activityOptionsCompat != null) {
            this.optionsCompat = activityOptionsCompat.toBundle();
        }
        return this;
    }

    public String toString() {
        return "Postcard{uri=" + this.uri + ", tag=" + this.tag + ", mBundle=" + this.mBundle + ", flags=" + this.flags + ", timeout=" + this.timeout + ", provider=" + this.provider + ", greenChannel=" + this.greenChannel + ", optionsCompat=" + this.optionsCompat + ", enterAnim=" + this.enterAnim + ", exitAnim=" + this.exitAnim + "}\n" + super.toString();
    }

    public String getAction() {
        return this.action;
    }

    public Postcard withAction(String str) {
        this.action = str;
        return this;
    }
}
