package androidx.paging;

import androidx.paging.RxPagedListBuilder;
import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0002*\u00020\u0003\"\b\b\u0003\u0010\u0004*\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "run"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.RxPagedListBuilder$PagingObservableOnSubscribe$refreshRetryCallback$1 */
/* compiled from: RxPagedListBuilder.kt */
final class C0563xed62ccbd implements Runnable {
    final /* synthetic */ RxPagedListBuilder.PagingObservableOnSubscribe this$0;

    C0563xed62ccbd(RxPagedListBuilder.PagingObservableOnSubscribe pagingObservableOnSubscribe) {
        this.this$0 = pagingObservableOnSubscribe;
    }

    public final void run() {
        this.this$0.invalidate(true);
    }
}
