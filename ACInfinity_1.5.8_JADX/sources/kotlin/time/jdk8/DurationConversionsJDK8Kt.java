package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0007"}, mo27512d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27515pn = "kotlin.time")
/* compiled from: DurationConversions.kt */
public final class DurationConversionsJDK8Kt {
    private static final long toKotlinDuration(Duration duration) {
        return kotlin.time.Duration.m2354plusLRDsOJo(kotlin.time.Duration.Companion.m2400secondsUwyO8pc(duration.getSeconds()), kotlin.time.Duration.Companion.m2392nanosecondsUwyO8pc(duration.getNano()));
    }

    /* renamed from: toJavaDuration-LRDsOJo  reason: not valid java name */
    private static final Duration m2412toJavaDurationLRDsOJo(long j) {
        Duration ofSeconds = Duration.ofSeconds(kotlin.time.Duration.m2339getInWholeSecondsimpl(j), (long) kotlin.time.Duration.m2341getNanosecondsComponentimpl(j));
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toComponents { seconds, …, nanoseconds.toLong()) }");
        return ofSeconds;
    }
}
