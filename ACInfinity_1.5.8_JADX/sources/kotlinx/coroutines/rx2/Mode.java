package kotlinx.coroutines.rx2;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/Mode;", "", "s", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getS", "()Ljava/lang/String;", "toString", "FIRST", "FIRST_OR_DEFAULT", "LAST", "SINGLE", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxAwait.kt */
enum Mode {
    FIRST("awaitFirst"),
    FIRST_OR_DEFAULT("awaitFirstOrDefault"),
    LAST("awaitLast"),
    SINGLE("awaitSingle");
    

    /* renamed from: s */
    private final String f1212s;

    private Mode(String str) {
        this.f1212s = str;
    }

    public final String getS() {
        return this.f1212s;
    }

    public String toString() {
        return this.f1212s;
    }
}
