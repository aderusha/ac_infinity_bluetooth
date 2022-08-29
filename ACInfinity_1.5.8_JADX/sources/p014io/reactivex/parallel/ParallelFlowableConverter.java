package p014io.reactivex.parallel;

/* renamed from: io.reactivex.parallel.ParallelFlowableConverter */
public interface ParallelFlowableConverter<T, R> {
    R apply(ParallelFlowable<T> parallelFlowable);
}
