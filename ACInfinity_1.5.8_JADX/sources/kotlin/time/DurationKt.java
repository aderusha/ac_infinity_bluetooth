package kotlin.time;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

@Metadata(mo27511d1 = {"\u0000B\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0003ø\u0001\u0000¢\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a \u00101\u001a\u00020\u00072\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0003ø\u0001\u0000¢\u0006\u0002\u00106\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00102\u001a\u000203H\u0002\u001a)\u00108\u001a\u00020\u0005*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a)\u0010=\u001a\u000203*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\b2\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\u00052\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a \u0010D\u001a\u00020\u0007*\u00020\b2\n\u0010E\u001a\u00060Fj\u0002`GH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a \u0010D\u001a\u00020\u0007*\u00020\u00052\n\u0010E\u001a\u00060Fj\u0002`GH\u0007ø\u0001\u0000¢\u0006\u0002\u0010I\u001a \u0010D\u001a\u00020\u0007*\u00020\u00012\n\u0010E\u001a\u00060Fj\u0002`GH\u0007ø\u0001\u0000¢\u0006\u0002\u0010J\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"!\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"!\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"!\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"!\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"!\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"!\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"!\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"!\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, mo27512d2 = {"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "skipWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "substringWhile", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "(DLjava/util/concurrent/TimeUnit;)J", "(ILjava/util/concurrent/TimeUnit;)J", "(JLjava/util/concurrent/TimeUnit;)J", "kotlin-stdlib"}, mo27513k = 2, mo27514mv = {1, 5, 1})
/* compiled from: Duration.kt */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    @Deprecated(message = "Use Duration.days() function instead.", replaceWith = @ReplaceWith(expression = "Duration.days(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getDays$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.days() function instead.", replaceWith = @ReplaceWith(expression = "Duration.days(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getDays$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.days() function instead.", replaceWith = @ReplaceWith(expression = "Duration.days(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getDays$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.hours() function instead.", replaceWith = @ReplaceWith(expression = "Duration.hours(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getHours$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.hours() function instead.", replaceWith = @ReplaceWith(expression = "Duration.hours(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getHours$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.hours() function instead.", replaceWith = @ReplaceWith(expression = "Duration.hours(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getHours$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.microseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.microseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMicroseconds$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.microseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.microseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMicroseconds$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.microseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.microseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMicroseconds$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.milliseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.milliseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMilliseconds$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.milliseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.milliseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMilliseconds$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.milliseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.milliseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMilliseconds$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.minutes() function instead.", replaceWith = @ReplaceWith(expression = "Duration.minutes(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMinutes$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.minutes() function instead.", replaceWith = @ReplaceWith(expression = "Duration.minutes(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMinutes$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.minutes() function instead.", replaceWith = @ReplaceWith(expression = "Duration.minutes(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getMinutes$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.nanoseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.nanoseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getNanoseconds$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.nanoseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.nanoseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getNanoseconds$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.nanoseconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.nanoseconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getNanoseconds$annotations(long j) {
    }

    @Deprecated(message = "Use Duration.seconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.seconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getSeconds$annotations(double d) {
    }

    @Deprecated(message = "Use Duration.seconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.seconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getSeconds$annotations(int i) {
    }

    @Deprecated(message = "Use Duration.seconds() function instead.", replaceWith = @ReplaceWith(expression = "Duration.seconds(this)", imports = {"kotlin.time.Duration"}))
    public static /* synthetic */ void getSeconds$annotations(long j) {
    }

    /* access modifiers changed from: private */
    public static final long millisToNanos(long j) {
        return j * ((long) NANOS_IN_MILLIS);
    }

    public static final long toDuration(int i, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (timeUnit.compareTo(TimeUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long) i, timeUnit, TimeUnit.NANOSECONDS));
        }
        return toDuration((long) i, timeUnit);
    }

    public static final long toDuration(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        long convertDurationUnitOverflow = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, TimeUnit.NANOSECONDS, timeUnit);
        if ((-convertDurationUnitOverflow) <= j && convertDurationUnitOverflow >= j) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(j, timeUnit, TimeUnit.NANOSECONDS));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit(j, timeUnit, TimeUnit.MILLISECONDS), -4611686018427387903L, (long) MAX_MILLIS));
    }

    public static final long toDuration(double d, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        double convertDurationUnit = DurationUnitKt.convertDurationUnit(d, timeUnit, TimeUnit.NANOSECONDS);
        if (!Double.isNaN(convertDurationUnit)) {
            long roundToLong = MathKt.roundToLong(convertDurationUnit);
            if (-4611686018426999999L <= roundToLong && MAX_NANOS >= roundToLong) {
                return durationOfNanos(roundToLong);
            }
            return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit(d, timeUnit, TimeUnit.MILLISECONDS)));
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
    }

    public static final long getNanoseconds(int i) {
        return toDuration(i, TimeUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(long j) {
        return toDuration(j, TimeUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(double d) {
        return toDuration(d, TimeUnit.NANOSECONDS);
    }

    public static final long getMicroseconds(int i) {
        return toDuration(i, TimeUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(long j) {
        return toDuration(j, TimeUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(double d) {
        return toDuration(d, TimeUnit.MICROSECONDS);
    }

    public static final long getMilliseconds(int i) {
        return toDuration(i, TimeUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(long j) {
        return toDuration(j, TimeUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(double d) {
        return toDuration(d, TimeUnit.MILLISECONDS);
    }

    public static final long getSeconds(int i) {
        return toDuration(i, TimeUnit.SECONDS);
    }

    public static final long getSeconds(long j) {
        return toDuration(j, TimeUnit.SECONDS);
    }

    public static final long getSeconds(double d) {
        return toDuration(d, TimeUnit.SECONDS);
    }

    public static final long getMinutes(int i) {
        return toDuration(i, TimeUnit.MINUTES);
    }

    public static final long getMinutes(long j) {
        return toDuration(j, TimeUnit.MINUTES);
    }

    public static final long getMinutes(double d) {
        return toDuration(d, TimeUnit.MINUTES);
    }

    public static final long getHours(int i) {
        return toDuration(i, TimeUnit.HOURS);
    }

    public static final long getHours(long j) {
        return toDuration(j, TimeUnit.HOURS);
    }

    public static final long getHours(double d) {
        return toDuration(d, TimeUnit.HOURS);
    }

    public static final long getDays(int i) {
        return toDuration(i, TimeUnit.DAYS);
    }

    public static final long getDays(long j) {
        return toDuration(j, TimeUnit.DAYS);
    }

    public static final long getDays(double d) {
        return toDuration(d, TimeUnit.DAYS);
    }

    /* renamed from: times-mvk6XK0  reason: not valid java name */
    private static final long m2402timesmvk6XK0(int i, long j) {
        return Duration.m2356timesUwyO8pc(j, i);
    }

    /* renamed from: times-kIfJnKk  reason: not valid java name */
    private static final long m2401timeskIfJnKk(double d, long j) {
        return Duration.m2355timesUwyO8pc(j, d);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x00ad A[EDGE_INSN: B:168:0x00ad->B:48:0x00ad ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009f A[LOOP:1: B:33:0x006f->B:46:0x009f, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long parseDuration(java.lang.String r26, boolean r27) {
        /*
            r6 = r26
            int r7 = r26.length()
            if (r7 == 0) goto L_0x02ff
            kotlin.time.Duration$Companion r0 = kotlin.time.Duration.Companion
            long r8 = r0.m2378getZEROUwyO8pc()
            r10 = 0
            char r0 = r6.charAt(r10)
            r1 = 43
            r2 = 45
            r11 = 1
            if (r0 == r1) goto L_0x001e
            if (r0 == r2) goto L_0x001e
            r12 = 0
            goto L_0x001f
        L_0x001e:
            r12 = 1
        L_0x001f:
            if (r12 <= 0) goto L_0x0023
            r13 = 1
            goto L_0x0024
        L_0x0023:
            r13 = 0
        L_0x0024:
            r0 = 2
            r14 = 0
            if (r13 == 0) goto L_0x0033
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r1 = kotlin.text.StringsKt.startsWith$default((java.lang.CharSequence) r1, (char) r2, (boolean) r10, (int) r0, (java.lang.Object) r14)
            if (r1 == 0) goto L_0x0033
            r15 = 1
            goto L_0x0034
        L_0x0033:
            r15 = 0
        L_0x0034:
            java.lang.String r5 = "No components"
            if (r7 <= r12) goto L_0x02f6
            char r1 = r6.charAt(r12)
            r2 = 80
            java.lang.String r4 = "(this as java.lang.String).substring(startIndex)"
            java.lang.String r3 = "Unexpected order of duration components"
            r16 = r5
            r5 = 48
            java.lang.String r10 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
            if (r1 != r2) goto L_0x017a
            int r12 = r12 + r11
            if (r12 == r7) goto L_0x0172
            r1 = r14
            java.util.concurrent.TimeUnit r1 = (java.util.concurrent.TimeUnit) r1
            r2 = 0
        L_0x0053:
            if (r12 >= r7) goto L_0x016e
            char r13 = r6.charAt(r12)
            r11 = 84
            if (r13 != r11) goto L_0x006e
            if (r2 != 0) goto L_0x0066
            int r12 = r12 + 1
            if (r12 == r7) goto L_0x0066
            r2 = 1
            r11 = 1
            goto L_0x0053
        L_0x0066:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x006e:
            r11 = r12
        L_0x006f:
            int r13 = r26.length()
            if (r11 >= r13) goto L_0x00a8
            char r13 = r6.charAt(r11)
            if (r5 > r13) goto L_0x0086
            r5 = 57
            if (r5 >= r13) goto L_0x0080
            goto L_0x0088
        L_0x0080:
            r17 = r7
            r18 = r15
            r15 = 2
            goto L_0x009a
        L_0x0086:
            r5 = 57
        L_0x0088:
            java.lang.String r16 = "+-."
            r5 = r16
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r17 = r7
            r18 = r15
            r7 = 0
            r15 = 2
            boolean r5 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (char) r13, (boolean) r7, (int) r15, (java.lang.Object) r14)
            if (r5 == 0) goto L_0x009c
        L_0x009a:
            r5 = 1
            goto L_0x009d
        L_0x009c:
            r5 = 0
        L_0x009d:
            if (r5 == 0) goto L_0x00ad
            int r11 = r11 + 1
            r7 = r17
            r15 = r18
            r5 = 48
            goto L_0x006f
        L_0x00a8:
            r17 = r7
            r18 = r15
            r15 = 2
        L_0x00ad:
            java.util.Objects.requireNonNull(r6, r0)
            java.lang.String r5 = r6.substring(r12, r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r10)
            r19 = r5
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19
            int r7 = r19.length()
            if (r7 != 0) goto L_0x00c3
            r7 = 1
            goto L_0x00c4
        L_0x00c3:
            r7 = 0
        L_0x00c4:
            if (r7 != 0) goto L_0x0166
            int r7 = r5.length()
            int r12 = r12 + r7
            r7 = r6
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r12 < 0) goto L_0x014d
            int r11 = kotlin.text.StringsKt.getLastIndex(r7)
            if (r12 > r11) goto L_0x014d
            char r7 = r7.charAt(r12)
            int r12 = r12 + 1
            java.util.concurrent.TimeUnit r7 = kotlin.time.DurationUnitKt.durationUnitByIsoChar(r7, r2)
            if (r1 == 0) goto L_0x00f4
            r11 = r7
            java.lang.Enum r11 = (java.lang.Enum) r11
            int r1 = r1.compareTo(r11)
            if (r1 <= 0) goto L_0x00ec
            goto L_0x00f4
        L_0x00ec:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r3)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x00f4:
            r20 = 46
            r21 = 0
            r22 = 0
            r23 = 6
            r24 = 0
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r19, (char) r20, (int) r21, (boolean) r22, (int) r23, (java.lang.Object) r24)
            java.util.concurrent.TimeUnit r11 = java.util.concurrent.TimeUnit.SECONDS
            if (r7 != r11) goto L_0x0136
            if (r1 <= 0) goto L_0x0136
            java.util.Objects.requireNonNull(r5, r0)
            r11 = 0
            java.lang.String r13 = r5.substring(r11, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r10)
            long r14 = parseOverLongIsoComponent(r13)
            long r13 = toDuration((long) r14, (java.util.concurrent.TimeUnit) r7)
            long r8 = kotlin.time.Duration.m2354plusLRDsOJo(r8, r13)
            java.util.Objects.requireNonNull(r5, r0)
            java.lang.String r1 = r5.substring(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            double r13 = java.lang.Double.parseDouble(r1)
            long r13 = toDuration((double) r13, (java.util.concurrent.TimeUnit) r7)
            long r8 = kotlin.time.Duration.m2354plusLRDsOJo(r8, r13)
            goto L_0x0142
        L_0x0136:
            long r13 = parseOverLongIsoComponent(r5)
            long r13 = toDuration((long) r13, (java.util.concurrent.TimeUnit) r7)
            long r8 = kotlin.time.Duration.m2354plusLRDsOJo(r8, r13)
        L_0x0142:
            r1 = r7
            r7 = r17
            r15 = r18
            r5 = 48
            r11 = 1
            r14 = 0
            goto L_0x0053
        L_0x014d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Missing unit for value "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0166:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x016e:
            r18 = r15
            goto L_0x02e7
        L_0x0172:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x017a:
            r17 = r7
            r18 = r15
            if (r27 != 0) goto L_0x02ee
            r5 = 0
            int r7 = r17 - r12
            r1 = 8
            int r7 = java.lang.Math.max(r7, r1)
            r14 = 1
            java.lang.String r2 = "Infinity"
            r1 = r0
            r15 = 57
            r0 = r26
            r11 = r1
            r1 = r12
            r25 = r3
            r3 = r5
            r5 = r4
            r4 = r7
            r7 = r5
            r15 = r16
            r5 = r14
            boolean r0 = kotlin.text.StringsKt.regionMatches((java.lang.String) r0, (int) r1, (java.lang.String) r2, (int) r3, (int) r4, (boolean) r5)
            if (r0 == 0) goto L_0x01aa
            kotlin.time.Duration$Companion r0 = kotlin.time.Duration.Companion
            long r8 = r0.m2376getINFINITEUwyO8pc()
            goto L_0x02e7
        L_0x01aa:
            r0 = 0
            r14 = r0
            java.util.concurrent.TimeUnit r14 = (java.util.concurrent.TimeUnit) r14
            r0 = r13 ^ 1
            if (r13 == 0) goto L_0x01d6
            char r1 = r6.charAt(r12)
            r2 = 40
            if (r1 != r2) goto L_0x01d6
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            char r1 = kotlin.text.StringsKt.last(r1)
            r2 = 41
            if (r1 != r2) goto L_0x01d6
            int r12 = r12 + 1
            int r0 = r17 + -1
            if (r12 == r0) goto L_0x01ce
            r1 = r0
            r0 = 1
            goto L_0x01d8
        L_0x01ce:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r15)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x01d6:
            r1 = r17
        L_0x01d8:
            r2 = 0
        L_0x01d9:
            if (r12 >= r1) goto L_0x02e7
            if (r2 == 0) goto L_0x01f5
            if (r0 == 0) goto L_0x01f5
        L_0x01df:
            int r2 = r26.length()
            if (r12 >= r2) goto L_0x01f5
            char r2 = r6.charAt(r12)
            r3 = 32
            if (r2 != r3) goto L_0x01ef
            r2 = 1
            goto L_0x01f0
        L_0x01ef:
            r2 = 0
        L_0x01f0:
            if (r2 == 0) goto L_0x01f5
            int r12 = r12 + 1
            goto L_0x01df
        L_0x01f5:
            r2 = r12
        L_0x01f6:
            int r3 = r26.length()
            if (r2 >= r3) goto L_0x0214
            char r3 = r6.charAt(r2)
            r4 = 48
            r5 = 57
            if (r4 > r3) goto L_0x0208
            if (r5 >= r3) goto L_0x020c
        L_0x0208:
            r13 = 46
            if (r3 != r13) goto L_0x020e
        L_0x020c:
            r3 = 1
            goto L_0x020f
        L_0x020e:
            r3 = 0
        L_0x020f:
            if (r3 == 0) goto L_0x0218
            int r2 = r2 + 1
            goto L_0x01f6
        L_0x0214:
            r4 = 48
            r5 = 57
        L_0x0218:
            java.util.Objects.requireNonNull(r6, r11)
            java.lang.String r2 = r6.substring(r12, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            r19 = r2
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19
            int r3 = r19.length()
            if (r3 != 0) goto L_0x022e
            r3 = 1
            goto L_0x022f
        L_0x022e:
            r3 = 0
        L_0x022f:
            if (r3 != 0) goto L_0x02df
            int r3 = r2.length()
            int r12 = r12 + r3
            r3 = r12
        L_0x0237:
            int r13 = r26.length()
            if (r3 >= r13) goto L_0x0254
            char r13 = r6.charAt(r3)
            r15 = 122(0x7a, float:1.71E-43)
            r4 = 97
            if (r4 <= r13) goto L_0x0248
            goto L_0x024c
        L_0x0248:
            if (r15 < r13) goto L_0x024c
            r4 = 1
            goto L_0x024d
        L_0x024c:
            r4 = 0
        L_0x024d:
            if (r4 == 0) goto L_0x0254
            int r3 = r3 + 1
            r4 = 48
            goto L_0x0237
        L_0x0254:
            java.util.Objects.requireNonNull(r6, r11)
            java.lang.String r3 = r6.substring(r12, r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            int r4 = r3.length()
            int r12 = r12 + r4
            java.util.concurrent.TimeUnit r3 = kotlin.time.DurationUnitKt.durationUnitByShortName(r3)
            if (r14 == 0) goto L_0x027d
            r4 = r3
            java.lang.Enum r4 = (java.lang.Enum) r4
            int r4 = r14.compareTo(r4)
            if (r4 <= 0) goto L_0x0273
            goto L_0x027d
        L_0x0273:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r4 = r25
            r0.<init>(r4)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x027d:
            r4 = r25
            r20 = 46
            r21 = 0
            r22 = 0
            r23 = 6
            r24 = 0
            int r13 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r19, (char) r20, (int) r21, (boolean) r22, (int) r23, (java.lang.Object) r24)
            if (r13 <= 0) goto L_0x02c9
            java.util.Objects.requireNonNull(r2, r11)
            r14 = 0
            java.lang.String r15 = r2.substring(r14, r13)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r10)
            long r5 = java.lang.Long.parseLong(r15)
            long r5 = toDuration((long) r5, (java.util.concurrent.TimeUnit) r3)
            long r5 = kotlin.time.Duration.m2354plusLRDsOJo(r8, r5)
            java.util.Objects.requireNonNull(r2, r11)
            java.lang.String r2 = r2.substring(r13)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)
            double r8 = java.lang.Double.parseDouble(r2)
            long r8 = toDuration((double) r8, (java.util.concurrent.TimeUnit) r3)
            long r5 = kotlin.time.Duration.m2354plusLRDsOJo(r5, r8)
            if (r12 < r1) goto L_0x02bf
            goto L_0x02d6
        L_0x02bf:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Fractional component must be last"
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x02c9:
            r14 = 0
            long r5 = java.lang.Long.parseLong(r2)
            long r5 = toDuration((long) r5, (java.util.concurrent.TimeUnit) r3)
            long r5 = kotlin.time.Duration.m2354plusLRDsOJo(r8, r5)
        L_0x02d6:
            r8 = r5
            r6 = r26
            r14 = r3
            r25 = r4
            r2 = 1
            goto L_0x01d9
        L_0x02df:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x02e7:
            if (r18 == 0) goto L_0x02ed
            long r8 = kotlin.time.Duration.m2370unaryMinusUwyO8pc(r8)
        L_0x02ed:
            return r8
        L_0x02ee:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x02f6:
            r15 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r15)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x02ff:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The string is empty"
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseDuration(java.lang.String, boolean):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long parseOverLongIsoComponent(java.lang.String r8) {
        /*
            int r0 = r8.length()
            r1 = 0
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 <= 0) goto L_0x001a
            java.lang.String r5 = "+-"
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            char r6 = r8.charAt(r4)
            boolean r5 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (char) r6, (boolean) r4, (int) r2, (java.lang.Object) r1)
            if (r5 == 0) goto L_0x001a
            r5 = 1
            goto L_0x001b
        L_0x001a:
            r5 = 0
        L_0x001b:
            int r0 = r0 - r5
            r6 = 16
            if (r0 <= r6) goto L_0x0074
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            r6 = r8
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = kotlin.text.StringsKt.getLastIndex(r6)
            r0.<init>(r5, r6)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r5 = r0 instanceof java.util.Collection
            if (r5 == 0) goto L_0x003d
            r5 = r0
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x003d
        L_0x003b:
            r0 = 1
            goto L_0x0061
        L_0x003d:
            java.util.Iterator r0 = r0.iterator()
        L_0x0041:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x003b
            r5 = r0
            kotlin.collections.IntIterator r5 = (kotlin.collections.IntIterator) r5
            int r5 = r5.nextInt()
            r6 = 57
            r7 = 48
            char r5 = r8.charAt(r5)
            if (r7 <= r5) goto L_0x0059
            goto L_0x005d
        L_0x0059:
            if (r6 < r5) goto L_0x005d
            r5 = 1
            goto L_0x005e
        L_0x005d:
            r5 = 0
        L_0x005e:
            if (r5 != 0) goto L_0x0041
            r0 = 0
        L_0x0061:
            if (r0 == 0) goto L_0x0074
            char r8 = r8.charAt(r4)
            r0 = 45
            if (r8 != r0) goto L_0x006e
            r0 = -9223372036854775808
            goto L_0x0073
        L_0x006e:
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x0073:
            return r0
        L_0x0074:
            java.lang.String r0 = "+"
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r8, r0, r4, r2, r1)
            if (r0 == 0) goto L_0x0080
            java.lang.String r8 = kotlin.text.StringsKt.drop((java.lang.String) r8, (int) r3)
        L_0x0080:
            long r0 = java.lang.Long.parseLong(r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseOverLongIsoComponent(java.lang.String):long");
    }

    /* access modifiers changed from: private */
    public static final int skipWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        while (i < str.length() && function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static final long nanosToMillis(long j) {
        return j / ((long) NANOS_IN_MILLIS);
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanos(long j) {
        return Duration.m2318constructorimpl(j << 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillis(long j) {
        return Duration.m2318constructorimpl((j << 1) + 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOf(long j, int i) {
        return Duration.m2318constructorimpl((j << 1) + ((long) i));
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long j) {
        if (-4611686018426999999L <= j && MAX_NANOS >= j) {
            return durationOfNanos(j);
        }
        return durationOfMillis(nanosToMillis(j));
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long j) {
        if (-4611686018426L <= j && MAX_NANOS_IN_MILLIS >= j) {
            return durationOfNanos(millisToNanos(j));
        }
        return durationOfMillis(RangesKt.coerceIn(j, -4611686018427387903L, (long) MAX_MILLIS));
    }

    private static final String substringWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        int i2 = i;
        while (i2 < str.length() && function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
            i2++;
        }
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }
}
