package kotlinx.coroutines.reactive;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/Mode;", "", "s", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getS", "()Ljava/lang/String;", "toString", "FIRST", "FIRST_OR_DEFAULT", "LAST", "SINGLE", "SINGLE_OR_DEFAULT", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Await.kt */
enum Mode {
    FIRST("awaitFirst"),
    FIRST_OR_DEFAULT("awaitFirstOrDefault"),
    LAST("awaitLast"),
    SINGLE("awaitSingle"),
    SINGLE_OR_DEFAULT("awaitSingleOrDefault");
    

    /* renamed from: s */
    private final String f1211s;

    private Mode(String str) {
        this.f1211s = str;
    }

    public final String getS() {
        return this.f1211s;
    }

    public String toString() {
        return this.f1211s;
    }
}
