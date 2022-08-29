package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, mo27512d2 = {"<anonymous>", "", "e"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: ExceptionsConstuctor.kt */
public final class ExceptionsConstuctorKt$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Function1<Throwable, Throwable> $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionsConstuctorKt$safeCtor$1(Function1<? super Throwable, ? extends Throwable> function1) {
        super(1);
        this.$block = function1;
    }

    public final Throwable invoke(Throwable th) {
        Object obj;
        Function1<Throwable, Throwable> function1 = this.$block;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m1023constructorimpl(function1.invoke(th));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m1023constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m1029isFailureimpl(obj)) {
            obj = null;
        }
        return (Throwable) obj;
    }
}
