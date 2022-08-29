package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, mo27512d2 = {"Landroidx/paging/NullPaddedDiffResult;", "", "diff", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "hasOverlap", "", "(Landroidx/recyclerview/widget/DiffUtil$DiffResult;Z)V", "getDiff", "()Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "getHasOverlap", "()Z", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: NullPaddedListDiffHelper.kt */
public final class NullPaddedDiffResult {
    private final DiffUtil.DiffResult diff;
    private final boolean hasOverlap;

    public NullPaddedDiffResult(DiffUtil.DiffResult diffResult, boolean z) {
        Intrinsics.checkNotNullParameter(diffResult, "diff");
        this.diff = diffResult;
        this.hasOverlap = z;
    }

    public final DiffUtil.DiffResult getDiff() {
        return this.diff;
    }

    public final boolean getHasOverlap() {
        return this.hasOverlap;
    }
}
