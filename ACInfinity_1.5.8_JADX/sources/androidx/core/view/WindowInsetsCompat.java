package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class WindowInsetsCompat {
    public static final WindowInsetsCompat CONSUMED;
    private static final String TAG = "WindowInsetsCompat";
    private final Impl mImpl;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            CONSUMED = Impl30.CONSUMED;
        } else {
            CONSUMED = Impl.CONSUMED;
        }
    }

    private WindowInsetsCompat(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(this, windowInsets);
        } else if (Build.VERSION.SDK_INT >= 29) {
            this.mImpl = new Impl29(this, windowInsets);
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new Impl28(this, windowInsets);
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.mImpl = new Impl21(this, windowInsets);
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.mImpl = new Impl20(this, windowInsets);
        } else {
            this.mImpl = new Impl(this);
        }
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat != null) {
            Impl impl = windowInsetsCompat.mImpl;
            if (Build.VERSION.SDK_INT >= 30 && (impl instanceof Impl30)) {
                this.mImpl = new Impl30(this, (Impl30) impl);
            } else if (Build.VERSION.SDK_INT >= 29 && (impl instanceof Impl29)) {
                this.mImpl = new Impl29(this, (Impl29) impl);
            } else if (Build.VERSION.SDK_INT >= 28 && (impl instanceof Impl28)) {
                this.mImpl = new Impl28(this, (Impl28) impl);
            } else if (Build.VERSION.SDK_INT >= 21 && (impl instanceof Impl21)) {
                this.mImpl = new Impl21(this, (Impl21) impl);
            } else if (Build.VERSION.SDK_INT < 20 || !(impl instanceof Impl20)) {
                this.mImpl = new Impl(this);
            } else {
                this.mImpl = new Impl20(this, (Impl20) impl);
            }
            impl.copyWindowDataInto(this);
            return;
        }
        this.mImpl = new Impl(this);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets) {
        return toWindowInsetsCompat(windowInsets, (View) null);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets, View view) {
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat((WindowInsets) Preconditions.checkNotNull(windowInsets));
        if (view != null && ViewCompat.isAttachedToWindow(view)) {
            windowInsetsCompat.setRootWindowInsets(ViewCompat.getRootWindowInsets(view));
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
        }
        return windowInsetsCompat;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.mImpl.getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.mImpl.getSystemWindowInsets().top;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.mImpl.getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.mImpl.getSystemWindowInsets().bottom;
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.mImpl.getSystemWindowInsets().equals(Insets.NONE);
    }

    public boolean hasInsets() {
        return !getInsets(Type.all()).equals(Insets.NONE) || !getInsetsIgnoringVisibility(Type.all() ^ Type.ime()).equals(Insets.NONE) || getDisplayCutout() != null;
    }

    public boolean isConsumed() {
        return this.mImpl.isConsumed();
    }

    public boolean isRound() {
        return this.mImpl.isRound();
    }

    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.mImpl.consumeSystemWindowInsets();
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        return new Builder(this).setSystemWindowInsets(Insets.m4of(i, i2, i3, i4)).build();
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        return new Builder(this).setSystemWindowInsets(Insets.m5of(rect)).build();
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.mImpl.getStableInsets().top;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.mImpl.getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.mImpl.getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.mImpl.getStableInsets().bottom;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !this.mImpl.getStableInsets().equals(Insets.NONE);
    }

    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.mImpl.consumeStableInsets();
    }

    public DisplayCutoutCompat getDisplayCutout() {
        return this.mImpl.getDisplayCutout();
    }

    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.mImpl.consumeDisplayCutout();
    }

    @Deprecated
    public Insets getSystemWindowInsets() {
        return this.mImpl.getSystemWindowInsets();
    }

    @Deprecated
    public Insets getStableInsets() {
        return this.mImpl.getStableInsets();
    }

    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return this.mImpl.getMandatorySystemGestureInsets();
    }

    @Deprecated
    public Insets getTappableElementInsets() {
        return this.mImpl.getTappableElementInsets();
    }

    @Deprecated
    public Insets getSystemGestureInsets() {
        return this.mImpl.getSystemGestureInsets();
    }

    public WindowInsetsCompat inset(Insets insets) {
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public WindowInsetsCompat inset(int i, int i2, int i3, int i4) {
        return this.mImpl.inset(i, i2, i3, i4);
    }

    public Insets getInsets(int i) {
        return this.mImpl.getInsets(i);
    }

    public Insets getInsetsIgnoringVisibility(int i) {
        return this.mImpl.getInsetsIgnoringVisibility(i);
    }

    public boolean isVisible(int i) {
        return this.mImpl.isVisible(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowInsetsCompat)) {
            return false;
        }
        return ObjectsCompat.equals(this.mImpl, ((WindowInsetsCompat) obj).mImpl);
    }

    public int hashCode() {
        Impl impl = this.mImpl;
        if (impl == null) {
            return 0;
        }
        return impl.hashCode();
    }

    public WindowInsets toWindowInsets() {
        Impl impl = this.mImpl;
        if (impl instanceof Impl20) {
            return ((Impl20) impl).mPlatformInsets;
        }
        return null;
    }

    private static class Impl {
        static final WindowInsetsCompat CONSUMED = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        final WindowInsetsCompat mHost;

        /* access modifiers changed from: package-private */
        public void copyRootViewBounds(View view) {
        }

        /* access modifiers changed from: package-private */
        public void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat) {
        }

        /* access modifiers changed from: package-private */
        public DisplayCutoutCompat getDisplayCutout() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean isConsumed() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean isRound() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisible(int i) {
            return true;
        }

        public void setOverriddenInsets(Insets[] insetsArr) {
        }

        /* access modifiers changed from: package-private */
        public void setRootViewData(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        }

        public void setStableInsets(Insets insets) {
        }

        Impl(WindowInsetsCompat windowInsetsCompat) {
            this.mHost = windowInsetsCompat;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeSystemWindowInsets() {
            return this.mHost;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeStableInsets() {
            return this.mHost;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeDisplayCutout() {
            return this.mHost;
        }

        /* access modifiers changed from: package-private */
        public Insets getSystemWindowInsets() {
            return Insets.NONE;
        }

        /* access modifiers changed from: package-private */
        public Insets getStableInsets() {
            return Insets.NONE;
        }

        /* access modifiers changed from: package-private */
        public Insets getSystemGestureInsets() {
            return getSystemWindowInsets();
        }

        /* access modifiers changed from: package-private */
        public Insets getMandatorySystemGestureInsets() {
            return getSystemWindowInsets();
        }

        /* access modifiers changed from: package-private */
        public Insets getTappableElementInsets() {
            return getSystemWindowInsets();
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat inset(int i, int i2, int i3, int i4) {
            return CONSUMED;
        }

        /* access modifiers changed from: package-private */
        public Insets getInsets(int i) {
            return Insets.NONE;
        }

        /* access modifiers changed from: package-private */
        public Insets getInsetsIgnoringVisibility(int i) {
            if ((i & 8) == 0) {
                return Insets.NONE;
            }
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl)) {
                return false;
            }
            Impl impl = (Impl) obj;
            if (isRound() != impl.isRound() || isConsumed() != impl.isConsumed() || !ObjectsCompat.equals(getSystemWindowInsets(), impl.getSystemWindowInsets()) || !ObjectsCompat.equals(getStableInsets(), impl.getStableInsets()) || !ObjectsCompat.equals(getDisplayCutout(), impl.getDisplayCutout())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ObjectsCompat.hash(Boolean.valueOf(isRound()), Boolean.valueOf(isConsumed()), getSystemWindowInsets(), getStableInsets(), getDisplayCutout());
        }
    }

    private static class Impl20 extends Impl {
        private static Class<?> sAttachInfoClass = null;
        private static Field sAttachInfoField = null;
        private static Method sGetViewRootImplMethod = null;
        private static Field sVisibleInsetsField = null;
        private static boolean sVisibleRectReflectionFetched = false;
        private Insets[] mOverriddenInsets;
        final WindowInsets mPlatformInsets;
        Insets mRootViewVisibleInsets;
        private WindowInsetsCompat mRootWindowInsets;
        private Insets mSystemWindowInsets;

        Impl20(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat);
            this.mSystemWindowInsets = null;
            this.mPlatformInsets = windowInsets;
        }

        Impl20(WindowInsetsCompat windowInsetsCompat, Impl20 impl20) {
            this(windowInsetsCompat, new WindowInsets(impl20.mPlatformInsets));
        }

        /* access modifiers changed from: package-private */
        public boolean isRound() {
            return this.mPlatformInsets.isRound();
        }

        public Insets getInsets(int i) {
            return getInsets(i, false);
        }

        public Insets getInsetsIgnoringVisibility(int i) {
            return getInsets(i, true);
        }

        /* access modifiers changed from: package-private */
        public boolean isVisible(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0 && !isTypeVisible(i2)) {
                    return false;
                }
            }
            return true;
        }

        private Insets getInsets(int i, boolean z) {
            Insets insets = Insets.NONE;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    insets = Insets.max(insets, getInsetsForType(i2, z));
                }
            }
            return insets;
        }

        /* access modifiers changed from: protected */
        public Insets getInsetsForType(int i, boolean z) {
            DisplayCutoutCompat displayCutoutCompat;
            if (i != 1) {
                Insets insets = null;
                if (i != 2) {
                    if (i == 8) {
                        Insets[] insetsArr = this.mOverriddenInsets;
                        if (insetsArr != null) {
                            insets = insetsArr[Type.indexOf(8)];
                        }
                        if (insets != null) {
                            return insets;
                        }
                        Insets systemWindowInsets = getSystemWindowInsets();
                        Insets rootStableInsets = getRootStableInsets();
                        if (systemWindowInsets.bottom > rootStableInsets.bottom) {
                            return Insets.m4of(0, 0, 0, systemWindowInsets.bottom);
                        }
                        Insets insets2 = this.mRootViewVisibleInsets;
                        if (insets2 == null || insets2.equals(Insets.NONE) || this.mRootViewVisibleInsets.bottom <= rootStableInsets.bottom) {
                            return Insets.NONE;
                        }
                        return Insets.m4of(0, 0, 0, this.mRootViewVisibleInsets.bottom);
                    } else if (i == 16) {
                        return getSystemGestureInsets();
                    } else {
                        if (i == 32) {
                            return getMandatorySystemGestureInsets();
                        }
                        if (i == 64) {
                            return getTappableElementInsets();
                        }
                        if (i != 128) {
                            return Insets.NONE;
                        }
                        WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
                        if (windowInsetsCompat != null) {
                            displayCutoutCompat = windowInsetsCompat.getDisplayCutout();
                        } else {
                            displayCutoutCompat = getDisplayCutout();
                        }
                        if (displayCutoutCompat != null) {
                            return Insets.m4of(displayCutoutCompat.getSafeInsetLeft(), displayCutoutCompat.getSafeInsetTop(), displayCutoutCompat.getSafeInsetRight(), displayCutoutCompat.getSafeInsetBottom());
                        }
                        return Insets.NONE;
                    }
                } else if (z) {
                    Insets rootStableInsets2 = getRootStableInsets();
                    Insets stableInsets = getStableInsets();
                    return Insets.m4of(Math.max(rootStableInsets2.left, stableInsets.left), 0, Math.max(rootStableInsets2.right, stableInsets.right), Math.max(rootStableInsets2.bottom, stableInsets.bottom));
                } else {
                    Insets systemWindowInsets2 = getSystemWindowInsets();
                    WindowInsetsCompat windowInsetsCompat2 = this.mRootWindowInsets;
                    if (windowInsetsCompat2 != null) {
                        insets = windowInsetsCompat2.getStableInsets();
                    }
                    int i2 = systemWindowInsets2.bottom;
                    if (insets != null) {
                        i2 = Math.min(i2, insets.bottom);
                    }
                    return Insets.m4of(systemWindowInsets2.left, 0, systemWindowInsets2.right, i2);
                }
            } else if (z) {
                return Insets.m4of(0, Math.max(getRootStableInsets().top, getSystemWindowInsets().top), 0, 0);
            } else {
                return Insets.m4of(0, getSystemWindowInsets().top, 0, 0);
            }
        }

        /* access modifiers changed from: protected */
        public boolean isTypeVisible(int i) {
            if (!(i == 1 || i == 2)) {
                if (i == 4) {
                    return false;
                }
                if (!(i == 8 || i == 128)) {
                    return true;
                }
            }
            return !getInsetsForType(i, false).equals(Insets.NONE);
        }

        /* access modifiers changed from: package-private */
        public final Insets getSystemWindowInsets() {
            if (this.mSystemWindowInsets == null) {
                this.mSystemWindowInsets = Insets.m4of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
            }
            return this.mSystemWindowInsets;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat inset(int i, int i2, int i3, int i4) {
            Builder builder = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets));
            builder.setSystemWindowInsets(WindowInsetsCompat.insetInsets(getSystemWindowInsets(), i, i2, i3, i4));
            builder.setStableInsets(WindowInsetsCompat.insetInsets(getStableInsets(), i, i2, i3, i4));
            return builder.build();
        }

        /* access modifiers changed from: package-private */
        public void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat) {
            windowInsetsCompat.setRootWindowInsets(this.mRootWindowInsets);
            windowInsetsCompat.setRootViewData(this.mRootViewVisibleInsets);
        }

        /* access modifiers changed from: package-private */
        public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
            this.mRootWindowInsets = windowInsetsCompat;
        }

        /* access modifiers changed from: package-private */
        public void setRootViewData(Insets insets) {
            this.mRootViewVisibleInsets = insets;
        }

        private Insets getRootStableInsets() {
            WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
            if (windowInsetsCompat != null) {
                return windowInsetsCompat.getStableInsets();
            }
            return Insets.NONE;
        }

        /* access modifiers changed from: package-private */
        public void copyRootViewBounds(View view) {
            Insets visibleInsets = getVisibleInsets(view);
            if (visibleInsets == null) {
                visibleInsets = Insets.NONE;
            }
            setRootViewData(visibleInsets);
        }

        private Insets getVisibleInsets(View view) {
            if (Build.VERSION.SDK_INT < 30) {
                if (!sVisibleRectReflectionFetched) {
                    loadReflectionField();
                }
                Method method = sGetViewRootImplMethod;
                if (!(method == null || sAttachInfoClass == null || sVisibleInsetsField == null)) {
                    try {
                        Object invoke = method.invoke(view, new Object[0]);
                        if (invoke == null) {
                            Log.w(WindowInsetsCompat.TAG, "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                            return null;
                        }
                        Rect rect = (Rect) sVisibleInsetsField.get(sAttachInfoField.get(invoke));
                        if (rect != null) {
                            return Insets.m5of(rect);
                        }
                        return null;
                    } catch (ReflectiveOperationException e) {
                        Log.e(WindowInsetsCompat.TAG, "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                    }
                }
                return null;
            }
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }

        public void setOverriddenInsets(Insets[] insetsArr) {
            this.mOverriddenInsets = insetsArr;
        }

        private static void loadReflectionField() {
            try {
                sGetViewRootImplMethod = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                sAttachInfoClass = cls;
                sVisibleInsetsField = cls.getDeclaredField("mVisibleInsets");
                sAttachInfoField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
                sVisibleInsetsField.setAccessible(true);
                sAttachInfoField.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.e(WindowInsetsCompat.TAG, "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
            sVisibleRectReflectionFetched = true;
        }

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            return Objects.equals(this.mRootViewVisibleInsets, ((Impl20) obj).mRootViewVisibleInsets);
        }
    }

    private static class Impl21 extends Impl20 {
        private Insets mStableInsets = null;

        Impl21(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        Impl21(WindowInsetsCompat windowInsetsCompat, Impl21 impl21) {
            super(windowInsetsCompat, (Impl20) impl21);
            this.mStableInsets = impl21.mStableInsets;
        }

        /* access modifiers changed from: package-private */
        public boolean isConsumed() {
            return this.mPlatformInsets.isConsumed();
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeStableInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeSystemWindowInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
        }

        /* access modifiers changed from: package-private */
        public final Insets getStableInsets() {
            if (this.mStableInsets == null) {
                this.mStableInsets = Insets.m4of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
            }
            return this.mStableInsets;
        }

        public void setStableInsets(Insets insets) {
            this.mStableInsets = insets;
        }
    }

    private static class Impl28 extends Impl21 {
        Impl28(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        Impl28(WindowInsetsCompat windowInsetsCompat, Impl28 impl28) {
            super(windowInsetsCompat, (Impl21) impl28);
        }

        /* access modifiers changed from: package-private */
        public DisplayCutoutCompat getDisplayCutout() {
            return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat consumeDisplayCutout() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl28)) {
                return false;
            }
            Impl28 impl28 = (Impl28) obj;
            if (!Objects.equals(this.mPlatformInsets, impl28.mPlatformInsets) || !Objects.equals(this.mRootViewVisibleInsets, impl28.mRootViewVisibleInsets)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.mPlatformInsets.hashCode();
        }
    }

    private static class Impl29 extends Impl28 {
        private Insets mMandatorySystemGestureInsets = null;
        private Insets mSystemGestureInsets = null;
        private Insets mTappableElementInsets = null;

        public void setStableInsets(Insets insets) {
        }

        Impl29(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        Impl29(WindowInsetsCompat windowInsetsCompat, Impl29 impl29) {
            super(windowInsetsCompat, (Impl28) impl29);
        }

        /* access modifiers changed from: package-private */
        public Insets getSystemGestureInsets() {
            if (this.mSystemGestureInsets == null) {
                this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
            }
            return this.mSystemGestureInsets;
        }

        /* access modifiers changed from: package-private */
        public Insets getMandatorySystemGestureInsets() {
            if (this.mMandatorySystemGestureInsets == null) {
                this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
            }
            return this.mMandatorySystemGestureInsets;
        }

        /* access modifiers changed from: package-private */
        public Insets getTappableElementInsets() {
            if (this.mTappableElementInsets == null) {
                this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
            }
            return this.mTappableElementInsets;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat inset(int i, int i2, int i3, int i4) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(i, i2, i3, i4));
        }
    }

    static Insets insetInsets(Insets insets, int i, int i2, int i3, int i4) {
        int max = Math.max(0, insets.left - i);
        int max2 = Math.max(0, insets.top - i2);
        int max3 = Math.max(0, insets.right - i3);
        int max4 = Math.max(0, insets.bottom - i4);
        if (max == i && max2 == i2 && max3 == i3 && max4 == i4) {
            return insets;
        }
        return Insets.m4of(max, max2, max3, max4);
    }

    private static class Impl30 extends Impl29 {
        static final WindowInsetsCompat CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        /* access modifiers changed from: package-private */
        public final void copyRootViewBounds(View view) {
        }

        Impl30(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        Impl30(WindowInsetsCompat windowInsetsCompat, Impl30 impl30) {
            super(windowInsetsCompat, (Impl29) impl30);
        }

        public Insets getInsets(int i) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl30.toPlatformType(i)));
        }

        public Insets getInsetsIgnoringVisibility(int i) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl30.toPlatformType(i)));
        }

        public boolean isVisible(int i) {
            return this.mPlatformInsets.isVisible(TypeImpl30.toPlatformType(i));
        }
    }

    public static final class Builder {
        private final BuilderImpl mImpl;

        public Builder() {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mImpl = new BuilderImpl30();
            } else if (Build.VERSION.SDK_INT >= 29) {
                this.mImpl = new BuilderImpl29();
            } else if (Build.VERSION.SDK_INT >= 20) {
                this.mImpl = new BuilderImpl20();
            } else {
                this.mImpl = new BuilderImpl();
            }
        }

        public Builder(WindowInsetsCompat windowInsetsCompat) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mImpl = new BuilderImpl30(windowInsetsCompat);
            } else if (Build.VERSION.SDK_INT >= 29) {
                this.mImpl = new BuilderImpl29(windowInsetsCompat);
            } else if (Build.VERSION.SDK_INT >= 20) {
                this.mImpl = new BuilderImpl20(windowInsetsCompat);
            } else {
                this.mImpl = new BuilderImpl(windowInsetsCompat);
            }
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets insets) {
            this.mImpl.setSystemWindowInsets(insets);
            return this;
        }

        @Deprecated
        public Builder setSystemGestureInsets(Insets insets) {
            this.mImpl.setSystemGestureInsets(insets);
            return this;
        }

        @Deprecated
        public Builder setMandatorySystemGestureInsets(Insets insets) {
            this.mImpl.setMandatorySystemGestureInsets(insets);
            return this;
        }

        @Deprecated
        public Builder setTappableElementInsets(Insets insets) {
            this.mImpl.setTappableElementInsets(insets);
            return this;
        }

        public Builder setInsets(int i, Insets insets) {
            this.mImpl.setInsets(i, insets);
            return this;
        }

        public Builder setInsetsIgnoringVisibility(int i, Insets insets) {
            this.mImpl.setInsetsIgnoringVisibility(i, insets);
            return this;
        }

        public Builder setVisible(int i, boolean z) {
            this.mImpl.setVisible(i, z);
            return this;
        }

        @Deprecated
        public Builder setStableInsets(Insets insets) {
            this.mImpl.setStableInsets(insets);
            return this;
        }

        public Builder setDisplayCutout(DisplayCutoutCompat displayCutoutCompat) {
            this.mImpl.setDisplayCutout(displayCutoutCompat);
            return this;
        }

        public WindowInsetsCompat build() {
            return this.mImpl.build();
        }
    }

    private static class BuilderImpl {
        private final WindowInsetsCompat mInsets;
        Insets[] mInsetsTypeMask;

        /* access modifiers changed from: package-private */
        public void setDisplayCutout(DisplayCutoutCompat displayCutoutCompat) {
        }

        /* access modifiers changed from: package-private */
        public void setMandatorySystemGestureInsets(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setStableInsets(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setSystemGestureInsets(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setSystemWindowInsets(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setTappableElementInsets(Insets insets) {
        }

        /* access modifiers changed from: package-private */
        public void setVisible(int i, boolean z) {
        }

        BuilderImpl() {
            this(new WindowInsetsCompat((WindowInsetsCompat) null));
        }

        BuilderImpl(WindowInsetsCompat windowInsetsCompat) {
            this.mInsets = windowInsetsCompat;
        }

        /* access modifiers changed from: package-private */
        public void setInsets(int i, Insets insets) {
            if (this.mInsetsTypeMask == null) {
                this.mInsetsTypeMask = new Insets[9];
            }
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    this.mInsetsTypeMask[Type.indexOf(i2)] = insets;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setInsetsIgnoringVisibility(int i, Insets insets) {
            if (i == 8) {
                throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
            }
        }

        /* access modifiers changed from: protected */
        public final void applyInsetTypes() {
            Insets[] insetsArr = this.mInsetsTypeMask;
            if (insetsArr != null) {
                Insets insets = insetsArr[Type.indexOf(1)];
                Insets insets2 = this.mInsetsTypeMask[Type.indexOf(2)];
                if (insets2 == null) {
                    insets2 = this.mInsets.getInsets(2);
                }
                if (insets == null) {
                    insets = this.mInsets.getInsets(1);
                }
                setSystemWindowInsets(Insets.max(insets, insets2));
                Insets insets3 = this.mInsetsTypeMask[Type.indexOf(16)];
                if (insets3 != null) {
                    setSystemGestureInsets(insets3);
                }
                Insets insets4 = this.mInsetsTypeMask[Type.indexOf(32)];
                if (insets4 != null) {
                    setMandatorySystemGestureInsets(insets4);
                }
                Insets insets5 = this.mInsetsTypeMask[Type.indexOf(64)];
                if (insets5 != null) {
                    setTappableElementInsets(insets5);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat build() {
            applyInsetTypes();
            return this.mInsets;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOverriddenInsets(Insets[] insetsArr) {
        this.mImpl.setOverriddenInsets(insetsArr);
    }

    private static class BuilderImpl20 extends BuilderImpl {
        private static Constructor<WindowInsets> sConstructor = null;
        private static boolean sConstructorFetched = false;
        private static Field sConsumedField = null;
        private static boolean sConsumedFieldFetched = false;
        private WindowInsets mPlatformInsets;
        private Insets mStableInsets;

        BuilderImpl20() {
            this.mPlatformInsets = createWindowInsetsInstance();
        }

        BuilderImpl20(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            this.mPlatformInsets = windowInsetsCompat.toWindowInsets();
        }

        /* access modifiers changed from: package-private */
        public void setSystemWindowInsets(Insets insets) {
            WindowInsets windowInsets = this.mPlatformInsets;
            if (windowInsets != null) {
                this.mPlatformInsets = windowInsets.replaceSystemWindowInsets(insets.left, insets.top, insets.right, insets.bottom);
            }
        }

        /* access modifiers changed from: package-private */
        public void setStableInsets(Insets insets) {
            this.mStableInsets = insets;
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
            windowInsetsCompat.setOverriddenInsets(this.mInsetsTypeMask);
            windowInsetsCompat.setStableInsets(this.mStableInsets);
            return windowInsetsCompat;
        }

        private static WindowInsets createWindowInsetsInstance() {
            if (!sConsumedFieldFetched) {
                try {
                    sConsumedField = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e) {
                    Log.i(WindowInsetsCompat.TAG, "Could not retrieve WindowInsets.CONSUMED field", e);
                }
                sConsumedFieldFetched = true;
            }
            Field field = sConsumedField;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get((Object) null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e2) {
                    Log.i(WindowInsetsCompat.TAG, "Could not get value from WindowInsets.CONSUMED field", e2);
                }
            }
            if (!sConstructorFetched) {
                try {
                    sConstructor = WindowInsets.class.getConstructor(new Class[]{Rect.class});
                } catch (ReflectiveOperationException e3) {
                    Log.i(WindowInsetsCompat.TAG, "Could not retrieve WindowInsets(Rect) constructor", e3);
                }
                sConstructorFetched = true;
            }
            Constructor<WindowInsets> constructor = sConstructor;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Object[]{new Rect()});
                } catch (ReflectiveOperationException e4) {
                    Log.i(WindowInsetsCompat.TAG, "Could not invoke WindowInsets(Rect) constructor", e4);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setStableInsets(Insets insets) {
        this.mImpl.setStableInsets(insets);
    }

    private static class BuilderImpl29 extends BuilderImpl {
        final WindowInsets.Builder mPlatBuilder;

        BuilderImpl29() {
            this.mPlatBuilder = new WindowInsets.Builder();
        }

        BuilderImpl29(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            WindowInsets.Builder builder;
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                builder = new WindowInsets.Builder(windowInsets);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.mPlatBuilder = builder;
        }

        /* access modifiers changed from: package-private */
        public void setSystemWindowInsets(Insets insets) {
            this.mPlatBuilder.setSystemWindowInsets(insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setSystemGestureInsets(Insets insets) {
            this.mPlatBuilder.setSystemGestureInsets(insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setMandatorySystemGestureInsets(Insets insets) {
            this.mPlatBuilder.setMandatorySystemGestureInsets(insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setTappableElementInsets(Insets insets) {
            this.mPlatBuilder.setTappableElementInsets(insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setStableInsets(Insets insets) {
            this.mPlatBuilder.setStableInsets(insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setDisplayCutout(DisplayCutoutCompat displayCutoutCompat) {
            this.mPlatBuilder.setDisplayCutout(displayCutoutCompat != null ? displayCutoutCompat.unwrap() : null);
        }

        /* access modifiers changed from: package-private */
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
            windowInsetsCompat.setOverriddenInsets(this.mInsetsTypeMask);
            return windowInsetsCompat;
        }
    }

    private static class BuilderImpl30 extends BuilderImpl29 {
        BuilderImpl30() {
        }

        BuilderImpl30(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }

        /* access modifiers changed from: package-private */
        public void setInsets(int i, Insets insets) {
            this.mPlatBuilder.setInsets(TypeImpl30.toPlatformType(i), insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setInsetsIgnoringVisibility(int i, Insets insets) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl30.toPlatformType(i), insets.toPlatformInsets());
        }

        /* access modifiers changed from: package-private */
        public void setVisible(int i, boolean z) {
            this.mPlatBuilder.setVisible(TypeImpl30.toPlatformType(i), z);
        }
    }

    public static final class Type {
        static final int CAPTION_BAR = 4;
        static final int DISPLAY_CUTOUT = 128;
        static final int FIRST = 1;
        static final int IME = 8;
        static final int LAST = 256;
        static final int MANDATORY_SYSTEM_GESTURES = 32;
        static final int NAVIGATION_BARS = 2;
        static final int SIZE = 9;
        static final int STATUS_BARS = 1;
        static final int SYSTEM_GESTURES = 16;
        static final int TAPPABLE_ELEMENT = 64;
        static final int WINDOW_DECOR = 256;

        @Retention(RetentionPolicy.SOURCE)
        public @interface InsetsType {
        }

        static int all() {
            return -1;
        }

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 7;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }

        private Type() {
        }

        static int indexOf(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 16) {
                return 4;
            }
            if (i == 32) {
                return 5;
            }
            if (i == 64) {
                return 6;
            }
            if (i == 128) {
                return 7;
            }
            if (i == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i);
        }
    }

    private static final class TypeImpl30 {
        private TypeImpl30() {
        }

        static int toPlatformType(int i) {
            int i2;
            int i3 = 0;
            for (int i4 = 1; i4 <= 256; i4 <<= 1) {
                if ((i & i4) != 0) {
                    if (i4 == 1) {
                        i2 = WindowInsets.Type.statusBars();
                    } else if (i4 == 2) {
                        i2 = WindowInsets.Type.navigationBars();
                    } else if (i4 == 4) {
                        i2 = WindowInsets.Type.captionBar();
                    } else if (i4 == 8) {
                        i2 = WindowInsets.Type.ime();
                    } else if (i4 == 16) {
                        i2 = WindowInsets.Type.systemGestures();
                    } else if (i4 == 32) {
                        i2 = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i4 == 64) {
                        i2 = WindowInsets.Type.tappableElement();
                    } else if (i4 == 128) {
                        i2 = WindowInsets.Type.displayCutout();
                    }
                    i3 |= i2;
                }
            }
            return i3;
        }
    }

    /* access modifiers changed from: package-private */
    public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        this.mImpl.setRootWindowInsets(windowInsetsCompat);
    }

    /* access modifiers changed from: package-private */
    public void setRootViewData(Insets insets) {
        this.mImpl.setRootViewData(insets);
    }

    /* access modifiers changed from: package-private */
    public void copyRootViewBounds(View view) {
        this.mImpl.copyRootViewBounds(view);
    }

    static class Api21ReflectionHolder {
        private static Field sContentInsets;
        private static boolean sReflectionSucceeded = true;
        private static Field sStableInsets;
        private static Field sViewAttachInfoField;

        private Api21ReflectionHolder() {
        }

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                sViewAttachInfoField = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                sStableInsets = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                sContentInsets = declaredField3;
                declaredField3.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.w(WindowInsetsCompat.TAG, "Failed to get visible insets from AttachInfo " + e.getMessage(), e);
            }
        }

        public static WindowInsetsCompat getRootWindowInsets(View view) {
            if (sReflectionSucceeded && view.isAttachedToWindow()) {
                try {
                    Object obj = sViewAttachInfoField.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) sStableInsets.get(obj);
                        Rect rect2 = (Rect) sContentInsets.get(obj);
                        if (!(rect == null || rect2 == null)) {
                            WindowInsetsCompat build = new Builder().setStableInsets(Insets.m5of(rect)).setSystemWindowInsets(Insets.m5of(rect2)).build();
                            build.setRootWindowInsets(build);
                            build.copyRootViewBounds(view.getRootView());
                            return build;
                        }
                    }
                } catch (IllegalAccessException e) {
                    Log.w(WindowInsetsCompat.TAG, "Failed to get insets from AttachInfo. " + e.getMessage(), e);
                }
            }
            return null;
        }
    }
}
