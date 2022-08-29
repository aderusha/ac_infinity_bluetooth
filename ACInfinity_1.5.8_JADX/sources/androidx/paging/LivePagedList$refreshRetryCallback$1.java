package androidx.paging;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "run"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: LivePagedList.kt */
final class LivePagedList$refreshRetryCallback$1 implements Runnable {
    final /* synthetic */ LivePagedList this$0;

    LivePagedList$refreshRetryCallback$1(LivePagedList livePagedList) {
        this.this$0 = livePagedList;
    }

    public final void run() {
        this.this$0.invalidate(true);
    }
}
