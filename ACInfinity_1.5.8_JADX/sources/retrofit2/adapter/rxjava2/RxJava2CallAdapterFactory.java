package retrofit2.adapter.rxjava2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import javax.annotation.Nullable;
import p014io.reactivex.Completable;
import p014io.reactivex.Flowable;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.Scheduler;
import p014io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class RxJava2CallAdapterFactory extends CallAdapter.Factory {
    private final boolean isAsync;
    @Nullable
    private final Scheduler scheduler;

    public static RxJava2CallAdapterFactory create() {
        return new RxJava2CallAdapterFactory((Scheduler) null, false);
    }

    public static RxJava2CallAdapterFactory createAsync() {
        return new RxJava2CallAdapterFactory((Scheduler) null, true);
    }

    public static RxJava2CallAdapterFactory createWithScheduler(Scheduler scheduler2) {
        Objects.requireNonNull(scheduler2, "scheduler == null");
        return new RxJava2CallAdapterFactory(scheduler2, false);
    }

    private RxJava2CallAdapterFactory(@Nullable Scheduler scheduler2, boolean z) {
        this.scheduler = scheduler2;
        this.isAsync = z;
    }

    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        boolean z;
        boolean z2;
        Type type2;
        Class<?> rawType = getRawType(type);
        if (rawType == Completable.class) {
            return new RxJava2CallAdapter(Void.class, this.scheduler, this.isAsync, false, true, false, false, false, true);
        }
        boolean z3 = rawType == Flowable.class;
        boolean z4 = rawType == Single.class;
        boolean z5 = rawType == Maybe.class;
        if (rawType != Observable.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? !z4 ? z5 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound = getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType2 = getRawType(parameterUpperBound);
        if (rawType2 == Response.class) {
            if (parameterUpperBound instanceof ParameterizedType) {
                type2 = getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
                z2 = false;
            } else {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
        } else if (rawType2 != Result.class) {
            type2 = parameterUpperBound;
            z2 = false;
            z = true;
            return new RxJava2CallAdapter(type2, this.scheduler, this.isAsync, z2, z, z3, z4, z5, false);
        } else if (parameterUpperBound instanceof ParameterizedType) {
            type2 = getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
            z2 = true;
        } else {
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        }
        z = false;
        return new RxJava2CallAdapter(type2, this.scheduler, this.isAsync, z2, z, z3, z4, z5, false);
    }
}
