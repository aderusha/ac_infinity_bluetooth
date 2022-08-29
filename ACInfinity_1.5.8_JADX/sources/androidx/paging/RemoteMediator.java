package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ExperimentalPagingApi
@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002\u000f\u0010B\u0005¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo27512d2 = {"Landroidx/paging/RemoteMediator;", "Key", "", "Value", "()V", "initialize", "Landroidx/paging/RemoteMediator$InitializeAction;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Landroidx/paging/RemoteMediator$MediatorResult;", "loadType", "Landroidx/paging/LoadType;", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/LoadType;Landroidx/paging/PagingState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "InitializeAction", "MediatorResult", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RemoteMediator.kt */
public abstract class RemoteMediator<Key, Value> {

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo27512d2 = {"Landroidx/paging/RemoteMediator$InitializeAction;", "", "(Ljava/lang/String;I)V", "LAUNCH_INITIAL_REFRESH", "SKIP_INITIAL_REFRESH", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: RemoteMediator.kt */
    public enum InitializeAction {
        LAUNCH_INITIAL_REFRESH,
        SKIP_INITIAL_REFRESH
    }

    public Object initialize(Continuation<? super InitializeAction> continuation) {
        return InitializeAction.LAUNCH_INITIAL_REFRESH;
    }

    public abstract Object load(LoadType loadType, PagingState<Key, Value> pagingState, Continuation<? super MediatorResult> continuation);

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, mo27512d2 = {"Landroidx/paging/RemoteMediator$MediatorResult;", "", "()V", "Error", "Success", "Landroidx/paging/RemoteMediator$MediatorResult$Error;", "Landroidx/paging/RemoteMediator$MediatorResult$Success;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: RemoteMediator.kt */
    public static abstract class MediatorResult {
        private MediatorResult() {
        }

        public /* synthetic */ MediatorResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"Landroidx/paging/RemoteMediator$MediatorResult$Error;", "Landroidx/paging/RemoteMediator$MediatorResult;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: RemoteMediator.kt */
        public static final class Error extends MediatorResult {
            private final Throwable throwable;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public final Throwable getThrowable() {
                return this.throwable;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, mo27512d2 = {"Landroidx/paging/RemoteMediator$MediatorResult$Success;", "Landroidx/paging/RemoteMediator$MediatorResult;", "endOfPaginationReached", "", "(Z)V", "()Z", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: RemoteMediator.kt */
        public static final class Success extends MediatorResult {
            private final boolean endOfPaginationReached;

            public final boolean endOfPaginationReached() {
                return this.endOfPaginationReached;
            }

            public Success(boolean z) {
                super((DefaultConstructorMarker) null);
                this.endOfPaginationReached = z;
            }
        }
    }
}
