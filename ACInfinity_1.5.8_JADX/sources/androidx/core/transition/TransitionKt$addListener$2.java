package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, mo27512d2 = {"<anonymous>", "", "it", "Landroid/transition/Transition;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Transition.kt */
public final class TransitionKt$addListener$2 extends Lambda implements Function1<Transition, Unit> {
    public static final TransitionKt$addListener$2 INSTANCE = new TransitionKt$addListener$2();

    public TransitionKt$addListener$2() {
        super(1);
    }

    public final void invoke(Transition transition) {
        Intrinsics.checkNotNullParameter(transition, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Transition) obj);
        return Unit.INSTANCE;
    }
}
