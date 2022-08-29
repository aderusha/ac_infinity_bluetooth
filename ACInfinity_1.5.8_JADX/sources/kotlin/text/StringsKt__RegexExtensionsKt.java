package kotlin.text;

import java.util.Set;
import kotlin.Metadata;

@Metadata(mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\b¨\u0006\u0007"}, mo27512d2 = {"toRegex", "Lkotlin/text/Regex;", "", "options", "", "Lkotlin/text/RegexOption;", "option", "kotlin-stdlib"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/text/StringsKt")
/* compiled from: RegexExtensions.kt */
class StringsKt__RegexExtensionsKt extends StringsKt__RegexExtensionsJVMKt {
    private static final Regex toRegex(String str) {
        return new Regex(str);
    }

    private static final Regex toRegex(String str, RegexOption regexOption) {
        return new Regex(str, regexOption);
    }

    private static final Regex toRegex(String str, Set<? extends RegexOption> set) {
        return new Regex(str, set);
    }
}
