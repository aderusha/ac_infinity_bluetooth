package p015it.sephiroth.android.library.easing;

import android.os.Handler;
import android.os.SystemClock;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: it.sephiroth.android.library.easing.EasingManager */
public final class EasingManager {
    static final int FPS = 60;
    static final int FRAME_TIME = 16;
    static final Handler mHandler = new Handler();
    long mBase;
    int mDuration;
    Easing mEasing;
    EasingCallback mEasingCallback;
    double mEndValue;
    boolean mInverted;
    Method mMethod;
    boolean mRunning;
    double mStartValue;
    Ticker mTicker;
    String mToken = String.valueOf(System.currentTimeMillis());
    double mValue;

    /* renamed from: it.sephiroth.android.library.easing.EasingManager$EaseType */
    public enum EaseType {
        EaseIn,
        EaseOut,
        EaseInOut,
        EaseNone
    }

    /* renamed from: it.sephiroth.android.library.easing.EasingManager$EasingCallback */
    public interface EasingCallback {
        void onEasingFinished(double d);

        void onEasingStarted(double d);

        void onEasingValueChanged(double d, double d2);
    }

    public EasingManager(EasingCallback easingCallback) {
        this.mEasingCallback = easingCallback;
    }

    public void start(Class<? extends Easing> cls, EaseType easeType, double d, double d2, int i) {
        start(cls, easeType, d, d2, i, 0);
    }

    public void start(Class<? extends Easing> cls, EaseType easeType, double d, double d2, int i, long j) {
        if (!this.mRunning) {
            Easing createInstance = createInstance(cls);
            this.mEasing = createInstance;
            if (createInstance != null) {
                Method easingMethod = getEasingMethod(createInstance, easeType);
                this.mMethod = easingMethod;
                if (easingMethod != null) {
                    boolean z = d > d2;
                    this.mInverted = z;
                    if (z) {
                        this.mStartValue = d2;
                        this.mEndValue = d;
                    } else {
                        this.mStartValue = d;
                        this.mEndValue = d2;
                    }
                    this.mValue = this.mStartValue;
                    this.mDuration = i;
                    this.mBase = SystemClock.uptimeMillis() + j;
                    this.mRunning = true;
                    this.mTicker = new Ticker();
                    long uptimeMillis = SystemClock.uptimeMillis() + 16 + j;
                    if (j == 0) {
                        this.mEasingCallback.onEasingStarted(d);
                    } else {
                        mHandler.postAtTime(new TickerStart(d), this.mToken, uptimeMillis - 16);
                    }
                    mHandler.postAtTime(this.mTicker, this.mToken, uptimeMillis);
                }
            }
        }
    }

    public void stop() {
        this.mRunning = false;
        mHandler.removeCallbacks(this.mTicker, this.mToken);
    }

    /* access modifiers changed from: package-private */
    public Easing createInstance(Class<? extends Easing> cls) {
        try {
            return (Easing) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Method getEasingMethod(Easing easing, EaseType easeType) {
        String methodName = getMethodName(easeType);
        if (methodName != null) {
            try {
                return easing.getClass().getMethod(methodName, new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: it.sephiroth.android.library.easing.EasingManager$1 */
    static /* synthetic */ class C38011 {

        /* renamed from: $SwitchMap$it$sephiroth$android$library$easing$EasingManager$EaseType */
        static final /* synthetic */ int[] f1190xa5f66278;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                it.sephiroth.android.library.easing.EasingManager$EaseType[] r0 = p015it.sephiroth.android.library.easing.EasingManager.EaseType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1190xa5f66278 = r0
                it.sephiroth.android.library.easing.EasingManager$EaseType r1 = p015it.sephiroth.android.library.easing.EasingManager.EaseType.EaseIn     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1190xa5f66278     // Catch:{ NoSuchFieldError -> 0x001d }
                it.sephiroth.android.library.easing.EasingManager$EaseType r1 = p015it.sephiroth.android.library.easing.EasingManager.EaseType.EaseInOut     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1190xa5f66278     // Catch:{ NoSuchFieldError -> 0x0028 }
                it.sephiroth.android.library.easing.EasingManager$EaseType r1 = p015it.sephiroth.android.library.easing.EasingManager.EaseType.EaseNone     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1190xa5f66278     // Catch:{ NoSuchFieldError -> 0x0033 }
                it.sephiroth.android.library.easing.EasingManager$EaseType r1 = p015it.sephiroth.android.library.easing.EasingManager.EaseType.EaseOut     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p015it.sephiroth.android.library.easing.EasingManager.C38011.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public String getMethodName(EaseType easeType) {
        int i = C38011.f1190xa5f66278[easeType.ordinal()];
        if (i == 1) {
            return "easeIn";
        }
        if (i == 2) {
            return "easeInOut";
        }
        if (i == 3) {
            return "easeNone";
        }
        if (i != 4) {
            return null;
        }
        return "easeOut";
    }

    /* renamed from: it.sephiroth.android.library.easing.EasingManager$TickerStart */
    class TickerStart implements Runnable {
        double mValue;

        public TickerStart(double d) {
            this.mValue = d;
        }

        public void run() {
            EasingManager.this.mEasingCallback.onEasingStarted(this.mValue);
        }
    }

    /* renamed from: it.sephiroth.android.library.easing.EasingManager$Ticker */
    class Ticker implements Runnable {
        Ticker() {
        }

        public void run() {
            long j = EasingManager.this.mBase;
            long uptimeMillis = SystemClock.uptimeMillis() - j;
            double d = EasingManager.this.mValue;
            try {
                double doubleValue = ((Double) EasingManager.this.mMethod.invoke(EasingManager.this.mEasing, new Object[]{Long.valueOf(uptimeMillis), Double.valueOf(EasingManager.this.mStartValue), Double.valueOf(EasingManager.this.mEndValue), Integer.valueOf(EasingManager.this.mDuration)})).doubleValue();
                EasingManager.this.mValue = doubleValue;
                long j2 = j + ((long) ((((int) (uptimeMillis / 16)) + 1) * 16));
                if (uptimeMillis < ((long) EasingManager.this.mDuration)) {
                    EasingCallback easingCallback = EasingManager.this.mEasingCallback;
                    if (EasingManager.this.mInverted) {
                        doubleValue = EasingManager.this.mEndValue - doubleValue;
                    }
                    easingCallback.onEasingValueChanged(doubleValue, d);
                    EasingManager.mHandler.postAtTime(this, EasingManager.this.mToken, j2);
                    return;
                }
                EasingManager.this.mEasingCallback.onEasingFinished(EasingManager.this.mInverted ? EasingManager.this.mEndValue : EasingManager.this.mStartValue);
                EasingManager.this.mRunning = false;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }
}
