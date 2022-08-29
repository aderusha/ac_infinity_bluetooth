package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\r\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0010"}, mo27512d2 = {"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: TimeSource.kt */
public abstract class TimeMark {
    /* renamed from: elapsedNow-UwyO8pc  reason: not valid java name */
    public abstract long m2405elapsedNowUwyO8pc();

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public TimeMark m2407plusLRDsOJo(long j) {
        return new AdjustedTimeMark(this, j, (DefaultConstructorMarker) null);
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public TimeMark m2406minusLRDsOJo(long j) {
        return m2407plusLRDsOJo(Duration.m2370unaryMinusUwyO8pc(j));
    }

    public final boolean hasPassedNow() {
        return !Duration.m2351isNegativeimpl(m2405elapsedNowUwyO8pc());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m2351isNegativeimpl(m2405elapsedNowUwyO8pc());
    }
}
