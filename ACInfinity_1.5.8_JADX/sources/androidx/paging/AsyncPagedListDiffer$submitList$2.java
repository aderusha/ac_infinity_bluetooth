package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo27512d2 = {"<anonymous>", "", "T", "", "run"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagedListDiffer.kt */
final class AsyncPagedListDiffer$submitList$2 implements Runnable {
    final /* synthetic */ Runnable $commitCallback;
    final /* synthetic */ PagedList $newSnapshot;
    final /* synthetic */ PagedList $oldSnapshot;
    final /* synthetic */ PagedList $pagedList;
    final /* synthetic */ RecordingCallback $recordingCallback;
    final /* synthetic */ int $runGeneration;
    final /* synthetic */ AsyncPagedListDiffer this$0;

    AsyncPagedListDiffer$submitList$2(AsyncPagedListDiffer asyncPagedListDiffer, PagedList pagedList, PagedList pagedList2, int i, PagedList pagedList3, RecordingCallback recordingCallback, Runnable runnable) {
        this.this$0 = asyncPagedListDiffer;
        this.$oldSnapshot = pagedList;
        this.$newSnapshot = pagedList2;
        this.$runGeneration = i;
        this.$pagedList = pagedList3;
        this.$recordingCallback = recordingCallback;
        this.$commitCallback = runnable;
    }

    public final void run() {
        NullPaddedList nullPaddedList = this.$oldSnapshot.getNullPaddedList();
        NullPaddedList nullPaddedList2 = this.$newSnapshot.getNullPaddedList();
        DiffUtil.ItemCallback diffCallback = this.this$0.getConfig$paging_runtime_release().getDiffCallback();
        Intrinsics.checkNotNullExpressionValue(diffCallback, "config.diffCallback");
        final NullPaddedDiffResult computeDiff = NullPaddedListDiffHelperKt.computeDiff(nullPaddedList, nullPaddedList2, diffCallback);
        this.this$0.getMainThreadExecutor$paging_runtime_release().execute(new Runnable(this) {
            final /* synthetic */ AsyncPagedListDiffer$submitList$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                if (this.this$0.this$0.getMaxScheduledGeneration$paging_runtime_release() == this.this$0.$runGeneration) {
                    this.this$0.this$0.latchPagedList$paging_runtime_release(this.this$0.$pagedList, this.this$0.$newSnapshot, computeDiff, this.this$0.$recordingCallback, this.this$0.$oldSnapshot.lastLoad(), this.this$0.$commitCallback);
                }
            }
        });
    }
}
