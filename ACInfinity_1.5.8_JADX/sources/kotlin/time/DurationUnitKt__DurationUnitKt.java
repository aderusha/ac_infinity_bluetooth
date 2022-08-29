package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnitKt;

@Metadata(mo27511d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u0014\u0010\u0007\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\b\u001a\u00020\tH\u0001\u001a\u0010\u0010\b\u001a\u00020\t*\u00060\u0001j\u0002`\u0002H\u0001¨\u0006\n"}, mo27512d2 = {"durationUnitByIsoChar", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/time/DurationUnitKt")
/* compiled from: DurationUnit.kt */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {
    public static final String shortName(TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "$this$shortName");
        switch (DurationUnitKt.WhenMappings.$EnumSwitchMapping$0[timeUnit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "m";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new IllegalStateException(("Unknown unit: " + timeUnit).toString());
        }
    }

    public static final TimeUnit durationUnitByShortName(String str) {
        Intrinsics.checkNotNullParameter(str, "shortName");
        int hashCode = str.hashCode();
        if (hashCode != 100) {
            if (hashCode != 104) {
                if (hashCode != 109) {
                    if (hashCode != 115) {
                        if (hashCode != 3494) {
                            if (hashCode != 3525) {
                                if (hashCode == 3742 && str.equals("us")) {
                                    return TimeUnit.MICROSECONDS;
                                }
                            } else if (str.equals("ns")) {
                                return TimeUnit.NANOSECONDS;
                            }
                        } else if (str.equals("ms")) {
                            return TimeUnit.MILLISECONDS;
                        }
                    } else if (str.equals("s")) {
                        return TimeUnit.SECONDS;
                    }
                } else if (str.equals("m")) {
                    return TimeUnit.MINUTES;
                }
            } else if (str.equals("h")) {
                return TimeUnit.HOURS;
            }
        } else if (str.equals("d")) {
            return TimeUnit.DAYS;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + str);
    }

    public static final TimeUnit durationUnitByIsoChar(char c, boolean z) {
        if (!z) {
            if (c == 'D') {
                return TimeUnit.DAYS;
            }
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + c);
        } else if (c == 'H') {
            return TimeUnit.HOURS;
        } else {
            if (c == 'M') {
                return TimeUnit.MINUTES;
            }
            if (c == 'S') {
                return TimeUnit.SECONDS;
            }
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + c);
        }
    }
}
