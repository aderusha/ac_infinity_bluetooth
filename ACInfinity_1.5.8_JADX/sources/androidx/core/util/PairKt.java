package androidx.core.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\u0010\u0004\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0002\u0010\u0006\u001a*\u0010\u0007\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\u0010\u0004\u001a*\u0010\u0007\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0002\u0010\u0006\u001a1\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\tH\b\u001a1\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\tH\b\u001a1\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003H\b\u001a1\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005H\b¨\u0006\f"}, mo27512d2 = {"component1", "F", "S", "Landroid/util/Pair;", "(Landroid/util/Pair;)Ljava/lang/Object;", "Landroidx/core/util/Pair;", "(Landroidx/core/util/Pair;)Ljava/lang/Object;", "component2", "toAndroidPair", "Lkotlin/Pair;", "toAndroidXPair", "toKotlinPair", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Pair.kt */
public final class PairKt {
    public static final <F, S> F component1(Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return pair.first;
    }

    public static final <F, S> S component2(Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return pair.second;
    }

    public static final <F, S> Pair<F, S> toKotlinPair(Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return new Pair<>(pair.first, pair.second);
    }

    public static final <F, S> Pair<F, S> toAndroidXPair(Pair<? extends F, ? extends S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return new Pair<>(pair.getFirst(), pair.getSecond());
    }

    public static final <F, S> F component1(android.util.Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return pair.first;
    }

    public static final <F, S> S component2(android.util.Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return pair.second;
    }

    public static final <F, S> Pair<F, S> toKotlinPair(android.util.Pair<F, S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return new Pair<>(pair.first, pair.second);
    }

    public static final <F, S> android.util.Pair<F, S> toAndroidPair(Pair<? extends F, ? extends S> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return new android.util.Pair<>(pair.getFirst(), pair.getSecond());
    }
}
