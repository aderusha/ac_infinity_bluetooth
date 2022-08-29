package androidx.paging;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/UiReceiver;", "", "accessHint", "", "viewportHint", "Landroidx/paging/ViewportHint;", "refresh", "retry", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: UiReceiver.kt */
public interface UiReceiver {
    void accessHint(ViewportHint viewportHint);

    void refresh();

    void retry();
}
