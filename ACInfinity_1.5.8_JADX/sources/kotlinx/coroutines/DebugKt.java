package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo27511d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\b\u001a\b\u0010\u0016\u001a\u00020\u0013H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u0014\u0010\u000f\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0003\"\u000e\u0010\u0011\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo27512d2 = {"ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG", "getDEBUG", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "assert", "", "value", "Lkotlin/Function0;", "resetCoroutineId", "kotlinx-coroutines-core"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Debug.kt */
public final class DebugKt {
    private static final boolean ASSERTIONS_ENABLED = false;
    private static final AtomicLong COROUTINE_ID = new AtomicLong(0);
    private static final boolean DEBUG;
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";
    private static final boolean RECOVER_STACK_TRACES;
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r1.equals(DEBUG_PROPERTY_VALUE_AUTO) != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r1.equals(DEBUG_PROPERTY_VALUE_ON) != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (r1.equals("") != false) goto L_0x0046;
     */
    static {
        /*
            java.lang.Class<kotlinx.coroutines.CoroutineId> r0 = kotlinx.coroutines.CoroutineId.class
            r0 = 0
            ASSERTIONS_ENABLED = r0
            java.lang.String r1 = "kotlinx.coroutines.debug"
            java.lang.String r1 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r1)
            r2 = 1
            if (r1 == 0) goto L_0x006a
            int r3 = r1.hashCode()
            if (r3 == 0) goto L_0x003e
            r4 = 3551(0xddf, float:4.976E-42)
            if (r3 == r4) goto L_0x0035
            r4 = 109935(0x1ad6f, float:1.54052E-40)
            if (r3 == r4) goto L_0x002b
            r4 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r3 != r4) goto L_0x0048
            java.lang.String r3 = "auto"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0048
            goto L_0x006a
        L_0x002b:
            java.lang.String r3 = "off"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0048
            r1 = 0
            goto L_0x006e
        L_0x0035:
            java.lang.String r3 = "on"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0048
            goto L_0x0046
        L_0x003e:
            java.lang.String r3 = ""
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0048
        L_0x0046:
            r1 = 1
            goto L_0x006e
        L_0x0048:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
            r0.append(r2)
            r0.append(r1)
            r1 = 39
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x006a:
            boolean r1 = getASSERTIONS_ENABLED()
        L_0x006e:
            DEBUG = r1
            if (r1 == 0) goto L_0x007b
            java.lang.String r1 = "kotlinx.coroutines.stacktrace.recovery"
            boolean r1 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r1, r2)
            if (r1 == 0) goto L_0x007b
            r0 = 1
        L_0x007b:
            RECOVER_STACK_TRACES = r0
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r1 = 0
            r0.<init>(r1)
            COROUTINE_ID = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.<clinit>():void");
    }

    public static final boolean getASSERTIONS_ENABLED() {
        return ASSERTIONS_ENABLED;
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    public static final boolean getRECOVER_STACK_TRACES() {
        return RECOVER_STACK_TRACES;
    }

    public static final AtomicLong getCOROUTINE_ID() {
        return COROUTINE_ID;
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0);
    }

    /* renamed from: assert  reason: not valid java name */
    private static final void m2417assert(Function0<Boolean> function0) {
        if (getASSERTIONS_ENABLED() && !function0.invoke().booleanValue()) {
            throw new AssertionError();
        }
    }
}
