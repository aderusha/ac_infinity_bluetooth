package kotlin.time;

import kotlin.Metadata;

@Metadata(mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0006"}, mo27512d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: TimeSource.kt */
public interface TimeSource {
    public static final Companion Companion = Companion.$$INSTANCE;

    TimeMark markNow();

    @Metadata(mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo27512d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource;", "()V", "markNow", "Lkotlin/time/TimeMark;", "toString", "", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
    /* compiled from: TimeSource.kt */
    public static final class Monotonic implements TimeSource {
        public static final Monotonic INSTANCE = new Monotonic();
        private final /* synthetic */ MonotonicTimeSource $$delegate_0 = MonotonicTimeSource.INSTANCE;

        public TimeMark markNow() {
            return this.$$delegate_0.markNow();
        }

        private Monotonic() {
        }

        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }
    }

    @Metadata(mo27511d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo27512d2 = {"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
    /* compiled from: TimeSource.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
