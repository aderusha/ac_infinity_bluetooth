package androidx.paging;

import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u0001\"\b\b\u0000\u0010\n*\u00020\u0005*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a4\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u0001\"\b\b\u0000\u0010\n*\u00020\u0005*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e\u001a4\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u0001\"\b\b\u0000\u0010\n*\u00020\u0005*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010\"A\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, mo27512d2 = {"liveData", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagingData;", "Value", "Key", "", "Landroidx/paging/Pager;", "getLiveData", "(Landroidx/paging/Pager;)Landroidx/lifecycle/LiveData;", "cachedIn", "T", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "viewModel", "Landroidx/lifecycle/ViewModel;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "paging-runtime_release"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: PagingLiveData.kt */
public final class PagingLiveData {
    public static final <Key, Value> LiveData<PagingData<Value>> getLiveData(Pager<Key, Value> pager) {
        Intrinsics.checkNotNullParameter(pager, "$this$liveData");
        return FlowLiveDataConversions.asLiveData$default((Flow) pager.getFlow(), (CoroutineContext) null, 0, 3, (Object) null);
    }

    public static final <T> LiveData<PagingData<T>> cachedIn(LiveData<PagingData<T>> liveData, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(liveData, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        return FlowLiveDataConversions.asLiveData$default((Flow) CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), LifecycleKt.getCoroutineScope(lifecycle)), (CoroutineContext) null, 0, 3, (Object) null);
    }

    public static final <T> LiveData<PagingData<T>> cachedIn(LiveData<PagingData<T>> liveData, ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(liveData, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        return FlowLiveDataConversions.asLiveData$default((Flow) CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), ViewModelKt.getViewModelScope(viewModel)), (CoroutineContext) null, 0, 3, (Object) null);
    }

    public static final <T> LiveData<PagingData<T>> cachedIn(LiveData<PagingData<T>> liveData, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(liveData, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return FlowLiveDataConversions.asLiveData$default((Flow) CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), coroutineScope), (CoroutineContext) null, 0, 3, (Object) null);
    }
}
