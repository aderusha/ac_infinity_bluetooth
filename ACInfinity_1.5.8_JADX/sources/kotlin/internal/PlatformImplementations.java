package kotlin.internal;

import com.eternal.framework.http.cookie.SerializableCookie;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;

@Metadata(mo27511d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u0013"}, mo27512d2 = {"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", "exception", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getSuppressed", "", "ReflectThrowable", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: PlatformImplementations.kt */
public class PlatformImplementations {

    @Metadata(mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo27512d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
    /* compiled from: PlatformImplementations.kt */
    private static final class ReflectThrowable {
        public static final ReflectThrowable INSTANCE = new ReflectThrowable();
        public static final Method addSuppressed;
        public static final Method getSuppressed;

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0046 A[LOOP:0: B:1:0x0015->B:10:0x0046, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[EDGE_INSN: B:20:0x004a->B:12:0x004a ?: BREAK  , SYNTHETIC] */
        static {
            /*
                kotlin.internal.PlatformImplementations$ReflectThrowable r0 = new kotlin.internal.PlatformImplementations$ReflectThrowable
                r0.<init>()
                INSTANCE = r0
                java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
                java.lang.reflect.Method[] r1 = r0.getMethods()
                java.lang.String r2 = "throwableMethods"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                int r2 = r1.length
                r3 = 0
                r4 = 0
            L_0x0015:
                java.lang.String r5 = "it"
                r6 = 0
                if (r4 >= r2) goto L_0x0049
                r7 = r1[r4]
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
                java.lang.String r8 = r7.getName()
                java.lang.String r9 = "addSuppressed"
                boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
                if (r8 == 0) goto L_0x0042
                java.lang.Class[] r8 = r7.getParameterTypes()
                java.lang.String r9 = "it.parameterTypes"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
                java.lang.Object r8 = kotlin.collections.ArraysKt.singleOrNull((T[]) r8)
                java.lang.Class r8 = (java.lang.Class) r8
                boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
                if (r8 == 0) goto L_0x0042
                r8 = 1
                goto L_0x0043
            L_0x0042:
                r8 = 0
            L_0x0043:
                if (r8 == 0) goto L_0x0046
                goto L_0x004a
            L_0x0046:
                int r4 = r4 + 1
                goto L_0x0015
            L_0x0049:
                r7 = r6
            L_0x004a:
                addSuppressed = r7
                int r0 = r1.length
            L_0x004d:
                if (r3 >= r0) goto L_0x0065
                r2 = r1[r3]
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
                java.lang.String r4 = r2.getName()
                java.lang.String r7 = "getSuppressed"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r7)
                if (r4 == 0) goto L_0x0062
                r6 = r2
                goto L_0x0065
            L_0x0062:
                int r3 = r3 + 1
                goto L_0x004d
            L_0x0065:
                getSuppressed = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementations.ReflectThrowable.<clinit>():void");
        }

        private ReflectThrowable() {
        }
    }

    public void addSuppressed(Throwable th, Throwable th2) {
        Intrinsics.checkNotNullParameter(th, "cause");
        Intrinsics.checkNotNullParameter(th2, "exception");
        Method method = ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(th, new Object[]{th2});
        }
    }

    public List<Throwable> getSuppressed(Throwable th) {
        Object invoke;
        Intrinsics.checkNotNullParameter(th, "exception");
        Method method = ReflectThrowable.getSuppressed;
        if (!(method == null || (invoke = method.invoke(th, new Object[0])) == null)) {
            Objects.requireNonNull(invoke, "null cannot be cast to non-null type kotlin.Array<kotlin.Throwable>");
            List<Throwable> asList = ArraysKt.asList((T[]) (Throwable[]) invoke);
            if (asList != null) {
                return asList;
            }
        }
        return CollectionsKt.emptyList();
    }

    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult, String str) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        Intrinsics.checkNotNullParameter(str, SerializableCookie.NAME);
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }
}
