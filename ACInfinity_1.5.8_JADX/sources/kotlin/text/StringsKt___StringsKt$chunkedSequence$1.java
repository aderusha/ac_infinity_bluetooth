package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo27512d2 = {"<anonymous>", "", "it", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 5, 1})
/* compiled from: _Strings.kt */
final class StringsKt___StringsKt$chunkedSequence$1 extends Lambda implements Function1<CharSequence, String> {
    public static final StringsKt___StringsKt$chunkedSequence$1 INSTANCE = new StringsKt___StringsKt$chunkedSequence$1();

    StringsKt___StringsKt$chunkedSequence$1() {
        super(1);
    }

    public final String invoke(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "it");
        return charSequence.toString();
    }
}
