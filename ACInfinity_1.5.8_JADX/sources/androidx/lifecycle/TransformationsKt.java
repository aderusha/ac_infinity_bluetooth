package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\b\u001a;\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\b\u0004\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006H\b\u001aA\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001a\b\u0004\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00010\u0006H\b¨\u0006\b"}, mo27512d2 = {"distinctUntilChanged", "Landroidx/lifecycle/LiveData;", "X", "map", "Y", "transform", "Lkotlin/Function1;", "switchMap", "lifecycle-livedata-ktx_release"}, mo27513k = 2, mo27514mv = {1, 1, 15})
/* compiled from: Transformations.kt */
public final class TransformationsKt {
    public static final <X, Y> LiveData<Y> map(LiveData<X> liveData, Function1<? super X, ? extends Y> function1) {
        Intrinsics.checkParameterIsNotNull(liveData, "$this$map");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        LiveData<Y> map = Transformations.map(liveData, new TransformationsKt$map$1(function1));
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(this) { transform(it) }");
        return map;
    }

    public static final <X, Y> LiveData<Y> switchMap(LiveData<X> liveData, Function1<? super X, ? extends LiveData<Y>> function1) {
        Intrinsics.checkParameterIsNotNull(liveData, "$this$switchMap");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        LiveData<Y> switchMap = Transformations.switchMap(liveData, new TransformationsKt$switchMap$1(function1));
        Intrinsics.checkExpressionValueIsNotNull(switchMap, "Transformations.switchMap(this) { transform(it) }");
        return switchMap;
    }

    public static final <X> LiveData<X> distinctUntilChanged(LiveData<X> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "$this$distinctUntilChanged");
        LiveData<X> distinctUntilChanged = Transformations.distinctUntilChanged(liveData);
        Intrinsics.checkExpressionValueIsNotNull(distinctUntilChanged, "Transformations.distinctUntilChanged(this)");
        return distinctUntilChanged;
    }
}
